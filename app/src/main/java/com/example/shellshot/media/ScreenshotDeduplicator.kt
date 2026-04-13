package com.example.shellshot.media

import android.net.Uri
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

/**
 * Small in-memory deduplicator that protects the unified processing entry.
 *
 * We intentionally keep only a short time window so duplicate callbacks from FileObserver and
 * MediaStore are coalesced, while new screenshots taken a few seconds later still pass through.
 */
class ScreenshotDeduplicator {
    private val mutex = Mutex()
    private val inFlightPaths = mutableMapOf<String, Long>()
    private val processedPaths = mutableMapOf<String, Long>()
    private val processedUris = mutableMapOf<String, Long>()
    private val ignorePaths = mutableMapOf<String, Long>()

    suspend fun tryBegin(candidate: ScreenshotCandidate): BeginDecision = mutex.withLock {
        cleanupExpiredLocked()

        val pathKey = normalizePath(candidate.absolutePath) ?: return@withLock BeginDecision.Accepted
        val uriKey = normalizeUri(candidate.uri)

        when {
            pathKey in ignorePaths -> BeginDecision.Skipped("忽略路径")
            pathKey in inFlightPaths -> BeginDecision.Skipped("正在处理中")
            pathKey in processedPaths -> BeginDecision.Skipped("重复路径")
            uriKey != null && uriKey in processedUris -> BeginDecision.Skipped("重复 Uri")
            else -> {
                inFlightPaths[pathKey] = System.currentTimeMillis()
                BeginDecision.Accepted
            }
        }
    }

    suspend fun finishProcessing(candidate: ScreenshotCandidate) = mutex.withLock {
        val pathKey = normalizePath(candidate.absolutePath) ?: return@withLock
        inFlightPaths.remove(pathKey)
        cleanupExpiredLocked()
    }

    suspend fun markProcessed(
        candidate: ScreenshotCandidate,
        outputPath: String,
        outputUri: Uri?,
    ) = mutex.withLock {
        val now = System.currentTimeMillis()
        cleanupExpiredLocked(now)

        normalizePath(candidate.absolutePath)?.let { pathKey ->
            inFlightPaths.remove(pathKey)
            processedPaths[pathKey] = now
        }
        normalizeUri(candidate.uri)?.let { uriKey ->
            processedUris[uriKey] = now
        }
        normalizePath(outputPath)?.let { outputPathKey ->
            ignorePaths[outputPathKey] = now
        }
        normalizeUri(outputUri)?.let { outputUriKey ->
            processedUris[outputUriKey] = now
        }
    }

    private fun cleanupExpiredLocked(now: Long = System.currentTimeMillis()) {
        inFlightPaths.entries.removeAll { now - it.value > WINDOW_MILLIS }
        processedPaths.entries.removeAll { now - it.value > WINDOW_MILLIS }
        processedUris.entries.removeAll { now - it.value > WINDOW_MILLIS }
        ignorePaths.entries.removeAll { now - it.value > WINDOW_MILLIS }
    }

    private fun normalizePath(path: String?): String? = ScreenshotDirectories.normalizeAbsolutePath(path)

    private fun normalizeUri(uri: Uri?): String? = uri?.toString()?.trim()?.lowercase()?.takeIf { it.isNotBlank() }

    sealed interface BeginDecision {
        data object Accepted : BeginDecision

        data class Skipped(val reason: String) : BeginDecision
    }

    private companion object {
        const val WINDOW_MILLIS = 10_000L
    }
}
