package com.example.shellshot.queue

import android.content.Context
import com.example.shellshot.data.AppPrefs
import com.example.shellshot.media.ScreenshotCandidate
import com.example.shellshot.media.ScreenshotDirectories
import java.io.File
import java.util.UUID
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class QueuedScreenshotTaskStore(
    context: Context,
    private val appPrefs: AppPrefs,
) {
    private val storeFile = File(context.filesDir, "screenshot_task_queue.json")
    private val mutex = Mutex()
    private val json = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }
    private val recentSuccessfulSignatures = linkedMapOf<String, Long>()

    suspend fun enqueueDetected(
        absolutePath: String,
        displayName: String,
        relativePath: String?,
        changedUri: String?,
        source: String,
    ): EnqueueTaskResult = withContext(Dispatchers.IO) {
        val settings = appPrefs.currentSettings()
        val screenshotRelativePath = settings.screenshotRelativePath
        mutex.withLock {
            val normalizedPath = ScreenshotDirectories.normalizeAbsolutePath(absolutePath)
                ?: return@withLock EnqueueTaskResult(false, "invalid_path")
            val file = File(absolutePath)
            val recentProcessedKey = ScreenshotCandidate.buildDedupeKey(
                absolutePath = absolutePath,
                displayName = displayName,
                sizeBytes = file.length().takeIf { it > 0L } ?: 0L,
                lastModifiedMillis = file.lastModified().takeIf { it > 0L } ?: 0L,
            )
            val recentProcessedPathKey = ScreenshotCandidate.buildProcessedPathKey(absolutePath)
            val ingressSignature = ScreenshotCandidate.buildIngressSignature(
                displayName = displayName,
                relativePath = relativePath,
                sizeBytes = file.length().takeIf { it > 0L } ?: 0L,
                lastModifiedMillis = file.lastModified().takeIf { it > 0L } ?: 0L,
            )

            if (ScreenshotDirectories.isGeneratedByApp(displayName)) {
                return@withLock EnqueueTaskResult(false, "generated_by_app")
            }
            if (ScreenshotDirectories.isOutputLocation(absolutePath = absolutePath, relativePath = relativePath)) {
                return@withLock EnqueueTaskResult(false, "output_directory")
            }
            if (!ScreenshotDirectories.isScreenshotSource(absolutePath, relativePath, screenshotRelativePath)) {
                return@withLock EnqueueTaskResult(false, "outside_configured_directory")
            }
            if (
                recentProcessedKey in settings.recentProcessedKeys ||
                (recentProcessedPathKey != null && recentProcessedPathKey in settings.recentProcessedKeys)
            ) {
                return@withLock EnqueueTaskResult(false, "already_processed")
            }

            val snapshot = readSnapshotLocked()
            val now = System.currentTimeMillis()
            pruneRecentSuccessfulSignaturesLocked(now)
            if (ingressSignature in recentSuccessfulSignatures) {
                return@withLock EnqueueTaskResult(false, "recent_success_cache")
            }
            val existing = snapshot.tasks.firstOrNull {
                !it.status.isTerminal() && (
                    ScreenshotDirectories.normalizeAbsolutePath(it.absolutePath) == normalizedPath ||
                        (!it.ingressSignature.isNullOrBlank() && it.ingressSignature == ingressSignature) ||
                        (!it.finalCandidateSignature.isNullOrBlank() && it.finalCandidateSignature == ingressSignature)
                    )
            }

            if (existing != null) {
                val refreshedStatus = when (existing.status) {
                    ScreenshotTaskStatus.COMPOSING -> ScreenshotTaskStatus.COMPOSING
                    ScreenshotTaskStatus.READY -> ScreenshotTaskStatus.READY
                    else -> ScreenshotTaskStatus.DETECTED
                }
                val refreshed = existing.copy(
                    displayName = displayName,
                    relativePath = relativePath,
                    changedUri = changedUri ?: existing.changedUri,
                    ingressSignature = ingressSignature,
                    updatedAtMillis = now,
                    nextAttemptAtMillis = minOf(existing.nextAttemptAtMillis, now),
                    status = refreshedStatus,
                    lastError = null,
                    preparedCandidate = when (refreshedStatus) {
                        ScreenshotTaskStatus.READY,
                        ScreenshotTaskStatus.COMPOSING,
                        -> existing.preparedCandidate

                        else -> null
                    },
                )
                writeSnapshotLocked(
                    snapshot.copy(
                        tasks = snapshot.tasks.map { if (it.id == existing.id) refreshed else it }.takeLast(MAX_TASKS),
                    ),
                )
                return@withLock EnqueueTaskResult(true, "task_refreshed", refreshed)
            }

            val task = QueuedScreenshotTask(
                id = UUID.randomUUID().toString(),
                absolutePath = absolutePath,
                displayName = displayName,
                relativePath = relativePath,
                changedUri = changedUri,
                source = source,
                ingressSignature = ingressSignature,
                status = ScreenshotTaskStatus.DETECTED,
                detectedAtMillis = now,
                updatedAtMillis = now,
                nextAttemptAtMillis = now,
            )
            writeSnapshotLocked(snapshot.copy(tasks = (snapshot.tasks + task).takeLast(MAX_TASKS)))
            EnqueueTaskResult(true, "queued", task)
        }
    }

    suspend fun recoverAfterRestart(): List<QueuedScreenshotTask> = withContext(Dispatchers.IO) {
        mutex.withLock {
            val now = System.currentTimeMillis()
            val snapshot = readSnapshotLocked()
            val recovered = snapshot.tasks.map { task ->
                when (task.status) {
                    ScreenshotTaskStatus.COMPOSING -> task.copy(
                        status = ScreenshotTaskStatus.READY,
                        updatedAtMillis = now,
                        nextAttemptAtMillis = now,
                        lastError = "recovered_after_restart",
                    )

                    else -> task
                }
            }
            writeSnapshotLocked(snapshot.copy(tasks = recovered.takeLast(MAX_TASKS)))
            recovered.filterNot { it.status.isTerminal() }
        }
    }

    suspend fun peekNextProcessableTask(): QueuedScreenshotTask? = withContext(Dispatchers.IO) {
        mutex.withLock {
            val now = System.currentTimeMillis()
            readSnapshotLocked().tasks
                .filter { task ->
                    !task.status.isTerminal() && task.nextAttemptAtMillis <= now
                }
                .sortedWith(
                    compareBy<QueuedScreenshotTask> { statusPriority(it.status) }
                        .thenBy { it.nextAttemptAtMillis }
                        .thenBy { it.detectedAtMillis },
                )
                .firstOrNull()
        }
    }

    suspend fun markWaitStable(taskId: String, retryCount: Int, message: String, nextAttemptAtMillis: Long) {
        updateTask(taskId) { task ->
            task.copy(
                status = ScreenshotTaskStatus.WAIT_STABLE,
                retryCount = retryCount,
                lastError = message,
                preparedCandidate = null,
                updatedAtMillis = System.currentTimeMillis(),
                nextAttemptAtMillis = nextAttemptAtMillis,
            )
        }
    }

    suspend fun markReady(taskId: String, preparedCandidate: PreparedScreenshotCandidate) {
        updateTask(taskId) { task ->
            task.copy(
                status = ScreenshotTaskStatus.READY,
                ingressSignature = preparedCandidate.ingressSignature,
                finalCandidateSignature = preparedCandidate.finalCandidateSignature,
                preparedCandidate = preparedCandidate,
                updatedAtMillis = System.currentTimeMillis(),
                nextAttemptAtMillis = System.currentTimeMillis(),
                lastError = null,
            )
        }
    }

    suspend fun markComposing(taskId: String) {
        updateTask(taskId) { task ->
            task.copy(
                status = ScreenshotTaskStatus.COMPOSING,
                updatedAtMillis = System.currentTimeMillis(),
                nextAttemptAtMillis = System.currentTimeMillis(),
                lastError = null,
            )
        }
    }

    suspend fun markSaved(taskId: String, outputPath: String, deleteAttempted: Boolean, deleteMessage: String?) {
        updateTask(taskId) { task ->
            task.copy(
                status = ScreenshotTaskStatus.SAVED,
                outputPath = outputPath,
                deleteAttempted = deleteAttempted,
                deleteSucceeded = false,
                deleteMessage = deleteMessage,
                updatedAtMillis = System.currentTimeMillis(),
                nextAttemptAtMillis = Long.MAX_VALUE,
                lastError = null,
            )
        }
    }

    suspend fun markDeleteDone(taskId: String, outputPath: String, deleteMessage: String?) {
        updateTask(taskId) { task ->
            task.copy(
                status = ScreenshotTaskStatus.DELETE_DONE,
                outputPath = outputPath,
                deleteAttempted = true,
                deleteSucceeded = true,
                deleteMessage = deleteMessage,
                updatedAtMillis = System.currentTimeMillis(),
                nextAttemptAtMillis = Long.MAX_VALUE,
                lastError = null,
            )
        }
    }

    suspend fun markFailedRetryable(taskId: String, retryCount: Int, message: String, nextAttemptAtMillis: Long) {
        updateTask(taskId) { task ->
            task.copy(
                status = ScreenshotTaskStatus.FAILED_RETRYABLE,
                retryCount = retryCount,
                lastError = message,
                preparedCandidate = null,
                updatedAtMillis = System.currentTimeMillis(),
                nextAttemptAtMillis = nextAttemptAtMillis,
            )
        }
    }

    suspend fun markFailedFinal(taskId: String, retryCount: Int, message: String) {
        updateTask(taskId) { task ->
            task.copy(
                status = ScreenshotTaskStatus.FAILED_FINAL,
                retryCount = retryCount,
                lastError = message,
                preparedCandidate = null,
                updatedAtMillis = System.currentTimeMillis(),
                nextAttemptAtMillis = Long.MAX_VALUE,
            )
        }
    }

    suspend fun shouldSkipForResolvedCandidate(
        taskId: String,
        candidate: ScreenshotCandidate,
    ): Boolean = withContext(Dispatchers.IO) {
        mutex.withLock {
            val now = System.currentTimeMillis()
            pruneRecentSuccessfulSignaturesLocked(now)
            val signatures = listOf(
                candidate.finalCandidateSignature,
                candidate.ingressSignature,
            )
            if (signatures.any { it in recentSuccessfulSignatures }) {
                return@withLock true
            }

            val snapshot = readSnapshotLocked()
            snapshot.tasks.any { task ->
                task.id != taskId &&
                    !task.status.isTerminal() &&
                    listOfNotNull(task.finalCandidateSignature, task.ingressSignature).any { it in signatures }
            }
        }
    }

    suspend fun rememberSuccessfulCandidate(task: QueuedScreenshotTask, candidate: ScreenshotCandidate) {
        withContext(Dispatchers.IO) {
            mutex.withLock {
                val now = System.currentTimeMillis()
                pruneRecentSuccessfulSignaturesLocked(now)
                listOfNotNull(
                    task.ingressSignature,
                    task.finalCandidateSignature,
                    candidate.ingressSignature,
                    candidate.finalCandidateSignature,
                ).forEach { signature ->
                    recentSuccessfulSignatures.remove(signature)
                    recentSuccessfulSignatures[signature] = now
                }
                trimRecentSuccessfulSignaturesLocked()
            }
        }
    }

    suspend fun removeTerminalTasks() = withContext(Dispatchers.IO) {
        mutex.withLock {
            val snapshot = readSnapshotLocked()
            writeSnapshotLocked(snapshot.copy(tasks = snapshot.tasks.filterNot { it.status.isTerminal() }))
        }
    }

    private suspend fun updateTask(
        taskId: String,
        transform: (QueuedScreenshotTask) -> QueuedScreenshotTask,
    ) = withContext(Dispatchers.IO) {
        mutex.withLock {
            val snapshot = readSnapshotLocked()
            writeSnapshotLocked(
                snapshot.copy(
                    tasks = snapshot.tasks.map { task ->
                        if (task.id == taskId) transform(task) else task
                    }.takeLast(MAX_TASKS),
                ),
            )
        }
    }

    private fun statusPriority(status: ScreenshotTaskStatus): Int = when (status) {
        ScreenshotTaskStatus.READY -> 0
        ScreenshotTaskStatus.DETECTED -> 1
        ScreenshotTaskStatus.WAIT_STABLE -> 2
        ScreenshotTaskStatus.FAILED_RETRYABLE -> 3
        ScreenshotTaskStatus.COMPOSING -> 4
        ScreenshotTaskStatus.SAVED,
        ScreenshotTaskStatus.DELETE_DONE,
        ScreenshotTaskStatus.FAILED_FINAL,
        -> 5
    }

    private fun readSnapshotLocked(): ScreenshotTaskSnapshot {
        if (!storeFile.exists()) {
            return ScreenshotTaskSnapshot()
        }
        return runCatching {
            json.decodeFromString<ScreenshotTaskSnapshot>(storeFile.readText())
        }.getOrDefault(ScreenshotTaskSnapshot())
    }

    private fun writeSnapshotLocked(snapshot: ScreenshotTaskSnapshot) {
        storeFile.parentFile?.mkdirs()
        storeFile.writeText(json.encodeToString(snapshot))
    }

    private fun pruneRecentSuccessfulSignaturesLocked(now: Long) {
        recentSuccessfulSignatures.entries.removeAll { (_, savedAt) ->
            now - savedAt > RECENT_SUCCESS_CACHE_TTL_MILLIS
        }
        trimRecentSuccessfulSignaturesLocked()
    }

    private fun trimRecentSuccessfulSignaturesLocked() {
        while (recentSuccessfulSignatures.size > MAX_RECENT_SUCCESS_SIGNATURES) {
            val oldestKey = recentSuccessfulSignatures.entries.firstOrNull()?.key ?: break
            recentSuccessfulSignatures.remove(oldestKey)
        }
    }

    private fun ScreenshotTaskStatus.isTerminal(): Boolean = when (this) {
        ScreenshotTaskStatus.SAVED,
        ScreenshotTaskStatus.DELETE_DONE,
        ScreenshotTaskStatus.FAILED_FINAL,
        -> true

        ScreenshotTaskStatus.DETECTED,
        ScreenshotTaskStatus.WAIT_STABLE,
        ScreenshotTaskStatus.READY,
        ScreenshotTaskStatus.COMPOSING,
        ScreenshotTaskStatus.FAILED_RETRYABLE,
        -> false
    }

    private companion object {
        const val MAX_TASKS = 120
        const val MAX_RECENT_SUCCESS_SIGNATURES = 64
        const val RECENT_SUCCESS_CACHE_TTL_MILLIS = 90_000L
    }
}
