package com.example.shellshot.queue

import android.graphics.Bitmap
import android.net.Uri
import com.example.shellshot.data.AppPrefs
import com.example.shellshot.data.AppStateStore
import com.example.shellshot.data.MonitoringPhase
import com.example.shellshot.data.ProcessingResult
import com.example.shellshot.data.ProcessingStatus
import com.example.shellshot.media.BitmapLoader
import com.example.shellshot.media.DeleteResult
import com.example.shellshot.media.LatestScreenshotResolver
import com.example.shellshot.media.OutputRepository
import com.example.shellshot.media.ResolveQueuedTaskResult
import com.example.shellshot.media.ResolutionSource
import com.example.shellshot.media.ScreenshotCandidate
import com.example.shellshot.media.ScreenshotDirectories
import com.example.shellshot.media.ScreenshotRules
import com.example.shellshot.media.ScreenshotStabilityChecker
import com.example.shellshot.service.AutoShellMode
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.TemplateRepository
import com.example.shellshot.utils.ShellLogger
import java.io.File
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

class QueuedScreenshotWorker(
    private val appPrefs: AppPrefs,
    private val appStateStore: AppStateStore,
    private val taskStore: QueuedScreenshotTaskStore,
    private val templateRepository: TemplateRepository,
    private val latestScreenshotResolver: LatestScreenshotResolver,
    private val screenshotStabilityChecker: ScreenshotStabilityChecker,
    private val bitmapLoader: BitmapLoader,
    private val outputRepository: OutputRepository,
    private val shellComposeEngine: com.example.shellshot.processor.ShellComposeEngine,
    private val logger: ShellLogger,
) {
    private val workerMutex = Mutex()

    suspend fun runNextTask(): Boolean = workerMutex.withLock {
        val task = taskStore.peekNextProcessableTask() ?: return@withLock false
        when (task.status) {
            ScreenshotTaskStatus.DETECTED,
            ScreenshotTaskStatus.WAIT_STABLE,
            ScreenshotTaskStatus.FAILED_RETRYABLE,
            -> prepareTask(task)

            ScreenshotTaskStatus.READY -> composeTask(task)

            ScreenshotTaskStatus.COMPOSING,
            ScreenshotTaskStatus.SAVED,
            ScreenshotTaskStatus.DELETE_DONE,
            ScreenshotTaskStatus.FAILED_FINAL,
            -> false
        }
    }

    private suspend fun prepareTask(task: QueuedScreenshotTask): Boolean {
        if (!canProcessCurrentTask()) {
            deferTaskUntilMonitoringActive(task)
            return true
        }

        val settings = appPrefs.currentSettings()
        val screenshotRelativePath = settings.screenshotRelativePath
        appStateStore.setMonitoringPhase(MonitoringPhase.WaitingFileStable)
        val resolution = resolveTask(task)
        val candidate = resolution.candidate ?: run {
            handleFailure(task, classifyPrepareResolutionFailure(task, resolution, screenshotRelativePath))
            return true
        }
        if (taskStore.shouldSkipForResolvedCandidate(task.id, candidate)) {
            skipAlreadyProcessedTask(task, candidate, "resolved_signature_collapsed")
            return true
        }
        if (isRecentlyProcessed(task, candidate, settings.recentProcessedKeys)) {
            skipAlreadyProcessedTask(task, candidate, resolution.reason)
            return true
        }

        return when (val stableResult = screenshotStabilityChecker.awaitStableCandidate(candidate, ::canProcessCurrentTask)) {
            is ScreenshotStabilityChecker.Result.Stable -> {
                taskStore.markReady(
                    taskId = task.id,
                    preparedCandidate = stableResult.candidate.toPreparedCandidate(
                        resolutionSource = resolution.source,
                        resolutionReason = resolution.reason,
                    ),
                )
                logger.d(
                    TAG,
                    "任务已就绪 path=${stableResult.candidate.absolutePath} source=${resolution.source} reason=${resolution.reason}",
                )
                true
            }

            ScreenshotStabilityChecker.Result.Suspended -> {
                deferTaskUntilMonitoringActive(task)
                true
            }

            ScreenshotStabilityChecker.Result.Unstable -> {
                handleFailure(
                    task = task,
                    decision = retryableFailure(
                        message = "文件未稳定",
                        basis = "burst_not_ready source=${resolution.source} reason=${resolution.reason}",
                        queueState = RetryQueueState.WaitStable,
                        exhaustedPhase = MonitoringPhase.FileNotStable,
                        statusOnPublish = ProcessingStatus.Skipped,
                    ),
                )
                true
            }
        }
    }

    private suspend fun composeTask(task: QueuedScreenshotTask): Boolean {
        appStateStore.setMonitoringPhase(MonitoringPhase.Composing)
        taskStore.markComposing(task.id)

        val settings = appPrefs.currentSettings()
        val resolution = resolveTaskForCompose(task)
        val candidate = resolution.candidate ?: run {
            handleFailure(task, classifyComposeResolutionFailure(task, resolution, settings.screenshotRelativePath))
            return true
        }
        if (taskStore.shouldSkipForResolvedCandidate(task.id, candidate)) {
            skipAlreadyProcessedTask(task, candidate, "compose_signature_collapsed")
            return true
        }
        if (isRecentlyProcessed(task, candidate, settings.recentProcessedKeys)) {
            skipAlreadyProcessedTask(task, candidate, resolution.reason)
            return true
        }

        val template = templateRepository.getTemplateById(settings.selectedTemplateId) ?: run {
            handleFailure(
                task = task,
                decision = finalFailure(
                    message = "未找到可用模板",
                    basis = "template_missing",
                    phase = MonitoringPhase.ComposeFailed,
                    statusOnPublish = ProcessingStatus.Failed,
                ),
            )
            return true
        }

        val invalidTemplateReason = validateTemplate(template)
        if (invalidTemplateReason != null) {
            handleFailure(
                task = task,
                decision = finalFailure(
                    message = "模板参数不合法",
                    basis = "template_invalid:$invalidTemplateReason",
                    phase = MonitoringPhase.ComposeFailed,
                    statusOnPublish = ProcessingStatus.Failed,
                ),
            )
            return true
        }

        var sourceBitmap: Bitmap? = null
        var composedBitmap: Bitmap? = null
        try {
            logger.d(
                TAG,
                "开始解码 path=${candidate.absolutePath} source=${resolution.source} uri=${candidate.uri}",
            )
            sourceBitmap = runCatching {
                loadCandidateBitmap(
                    candidate = candidate,
                    resolutionSource = resolution.source,
                    template = template,
                )
            }.getOrElse { throwable ->
                handleFailure(task, classifyDecodeFailure(candidate, throwable))
                return true
            }
                logger.d(TAG, "解码成功 path=${candidate.absolutePath} source=${resolution.source}")

                logger.d(TAG, "开始合成 path=${candidate.absolutePath} template=${template.id}")
            composedBitmap = runCatching {
                shellComposeEngine.compose(sourceBitmap, template, task.id)
            }.onFailure { throwable ->
                logger.e(TAG, "合成失败 path=${candidate.absolutePath}", throwable)
            }.getOrElse {
                handleFailure(
                    task = task,
                    decision = finalFailure(
                        message = "模板参数不合法或合成失败",
                        basis = "compose_failure:${it::class.simpleName ?: "unknown"}",
                        phase = MonitoringPhase.ComposeFailed,
                        statusOnPublish = ProcessingStatus.Failed,
                    ),
                )
                return true
            }
                logger.d(TAG, "合成成功 path=${candidate.absolutePath}")

            appStateStore.setMonitoringPhase(MonitoringPhase.Saving)
                logger.d(TAG, "开始保存 path=${candidate.absolutePath}")
            val output = runCatching {
                outputRepository.save(
                    bitmap = composedBitmap,
                    sourceCandidate = candidate,
                    namingStrategy = settings.outputNamingStrategy,
                )
            }.onFailure { throwable ->
                logger.e(TAG, "保存失败 path=${candidate.absolutePath}", throwable)
            }.getOrElse {
                handleFailure(
                    task = task,
                    decision = finalFailure(
                        message = "输出保存失败",
                        basis = "save_unrecoverable:${it::class.simpleName ?: "unknown"}",
                        phase = MonitoringPhase.SaveFailed,
                        statusOnPublish = ProcessingStatus.Failed,
                    ),
                )
                return true
            }
                logger.d(TAG, "保存成功 output=${output.absolutePath}")

            ScreenshotCandidate.buildProcessedPathKey(task.absolutePath)?.let { pathKey ->
                appPrefs.addRecentProcessedKey(pathKey)
            }
            ScreenshotCandidate.buildProcessedPathKey(candidate.absolutePath)?.let { pathKey ->
                appPrefs.addRecentProcessedKey(pathKey)
            }
            appPrefs.addRecentProcessedKey(candidate.dedupeKey)
            taskStore.rememberSuccessfulCandidate(task, candidate)
            val deleteResult = deleteOriginalIfNeeded(
                autoDeleteOriginal = settings.autoDeleteOriginal,
                candidate = candidate,
            )
            if (deleteResult.success) {
                taskStore.markDeleteDone(task.id, output.absolutePath, deleteResult.message)
            } else {
                taskStore.markSaved(
                    taskId = task.id,
                    outputPath = output.absolutePath,
                    deleteAttempted = settings.autoDeleteOriginal,
                    deleteMessage = deleteResult.message,
                )
            }

            publishSuccessResult(
                task = task,
                candidate = candidate,
                templateId = template.id,
                outputPath = output.absolutePath,
                deleteResult = deleteResult,
                detailMessage = ScreenshotRules.matchReason(candidate, settings.screenshotRelativePath),
            )
            logger.d(
                TAG,
                "套壳成功 path=${candidate.absolutePath} source=${resolution.source} output=${output.absolutePath} deleteOriginal=${deleteResult.success}",
            )
            appStateStore.setMonitoringPhase(MonitoringPhase.Monitoring)
            return true
        } finally {
            sourceBitmap?.takeIf { !it.isRecycled }?.recycle()
            composedBitmap?.takeIf { !it.isRecycled }?.recycle()
            if (appStateStore.runtimeState.value.monitoringActive) {
                appStateStore.setMonitoringPhase(MonitoringPhase.Monitoring)
            }
        }
    }

    private suspend fun resolveTask(task: QueuedScreenshotTask): ResolveQueuedTaskResult {
        return latestScreenshotResolver.resolveQueuedTaskDetailed(
            absolutePath = task.absolutePath,
            displayName = task.displayName,
            relativePath = task.relativePath,
            changedUri = task.changedUri?.let(Uri::parse),
        )
    }

    private suspend fun resolveTaskForCompose(task: QueuedScreenshotTask): ResolveQueuedTaskResult {
        val preparedResolution = task.preparedCandidate?.toReusableResolutionOrNull()
        if (preparedResolution != null) {
            logger.d(
                TAG,
                "复用已确认候选 path=${preparedResolution.candidate?.absolutePath} source=${preparedResolution.source} reason=${preparedResolution.reason}",
            )
            return preparedResolution
        }
        if (task.preparedCandidate != null) {
            logger.d(
                TAG,
                "已确认候选失效，回退重新解析 path=${task.absolutePath}",
            )
        }
        return resolveTask(task)
    }

    private fun classifyPrepareResolutionFailure(
        task: QueuedScreenshotTask,
        resolution: ResolveQueuedTaskResult,
        screenshotRelativePath: String,
    ): TaskFailureDecision {
        return when {
            task.absolutePath.isBlank() || task.displayName.isBlank() ->
                finalFailure(
                    message = "输入路径或文件名无效",
                    basis = "invalid_input",
                    phase = MonitoringPhase.FileNotStable,
                    statusOnPublish = ProcessingStatus.Failed,
                )

            ScreenshotDirectories.isGeneratedByApp(task.displayName) ||
                ScreenshotDirectories.isOutputLocation(task.absolutePath, task.relativePath) ||
                !ScreenshotDirectories.looksLikeImageFile(task.displayName) ||
                !ScreenshotDirectories.isScreenshotSource(task.absolutePath, task.relativePath, screenshotRelativePath) ->
                finalFailure(
                    message = "明确不是截图",
                    basis = "not_screenshot path=${task.absolutePath}",
                    phase = MonitoringPhase.FileNotStable,
                    statusOnPublish = ProcessingStatus.Skipped,
                )

            else ->
                retryableFailure(
                    message = "候选截图尚未准备好",
                    basis = "resolver_none source=${resolution.source} reason=${resolution.reason}",
                    queueState = RetryQueueState.WaitStable,
                    exhaustedPhase = MonitoringPhase.FileNotStable,
                    statusOnPublish = ProcessingStatus.Skipped,
                )
        }
    }

    private fun classifyComposeResolutionFailure(
        task: QueuedScreenshotTask,
        resolution: ResolveQueuedTaskResult,
        screenshotRelativePath: String,
    ): TaskFailureDecision {
        return when {
            task.absolutePath.isBlank() || task.displayName.isBlank() ->
                finalFailure(
                    message = "输入路径或文件名无效",
                    basis = "compose_invalid_input",
                    phase = MonitoringPhase.ComposeFailed,
                    statusOnPublish = ProcessingStatus.Failed,
                )

            !ScreenshotDirectories.looksLikeImageFile(task.displayName) ||
                !ScreenshotDirectories.isScreenshotSource(task.absolutePath, task.relativePath, screenshotRelativePath) ->
                finalFailure(
                    message = "明确不是截图",
                    basis = "compose_not_screenshot path=${task.absolutePath}",
                    phase = MonitoringPhase.ComposeFailed,
                    statusOnPublish = ProcessingStatus.Skipped,
                )

            else ->
                finalFailure(
                    message = "截图文件已不可用",
                    basis = "compose_candidate_missing source=${resolution.source} reason=${resolution.reason}",
                    phase = MonitoringPhase.ComposeFailed,
                    statusOnPublish = ProcessingStatus.Failed,
                )
        }
    }

    private fun classifyDecodeFailure(
        candidate: ScreenshotCandidate,
        throwable: Throwable,
    ): TaskFailureDecision {
            logger.e(TAG, "截图解码失败 path=${candidate.absolutePath}", throwable)
        return retryableFailure(
            message = "截图解码暂时失败",
            basis = "decode_transient:${throwable::class.simpleName ?: "unknown"}",
            queueState = RetryQueueState.FailedRetryable,
            exhaustedPhase = MonitoringPhase.ComposeFailed,
            statusOnPublish = ProcessingStatus.Failed,
        )
    }

    private suspend fun loadCandidateBitmap(
        candidate: ScreenshotCandidate,
        resolutionSource: ResolutionSource,
        template: ShellTemplate,
    ): Bitmap {
        val targets = buildDecodeTargets(candidate, resolutionSource)
        require(targets.isNotEmpty()) {
            "No decode target available for ${candidate.absolutePath}"
        }

        var lastThrowable: Throwable? = null
        targets.forEach { target ->
            runCatching {
                when (target) {
                    is DecodeTarget.PathTarget -> bitmapLoader.loadBitmap(
                        absolutePath = target.absolutePath,
                        targetWidth = template.screenRect.width,
                        targetHeight = template.screenRect.height,
                    )

                    is DecodeTarget.UriTarget -> bitmapLoader.loadBitmap(
                        uri = target.uri,
                        targetWidth = template.screenRect.width,
                        targetHeight = template.screenRect.height,
                    )
                }
            }.onSuccess { bitmap ->
            logger.d(TAG, "解码目标命中 target=${target.describe()} source=$resolutionSource")
                return bitmap
            }.onFailure { throwable ->
                lastThrowable = throwable
            logger.e(TAG, "解码目标失败 target=${target.describe()} source=$resolutionSource", throwable)
            }
        }

        throw checkNotNull(lastThrowable)
    }

    private fun buildDecodeTargets(
        candidate: ScreenshotCandidate,
        resolutionSource: ResolutionSource,
    ): List<DecodeTarget> {
        val targets = mutableListOf<DecodeTarget>()
        if (resolutionSource == ResolutionSource.MEDIASTORE_FALLBACK && candidate.uri != null) {
            targets += DecodeTarget.UriTarget(candidate.uri)
        }
        if (candidate.absolutePath.isNotBlank()) {
            targets += DecodeTarget.PathTarget(candidate.absolutePath)
        }
        if (resolutionSource != ResolutionSource.MEDIASTORE_FALLBACK && candidate.uri != null) {
            targets += DecodeTarget.UriTarget(candidate.uri)
        }
        return targets.distinctBy { it.key() }
    }

    private fun validateTemplate(template: ShellTemplate): String? {
        return when {
            template.frameAsset.isBlank() -> "frame_asset_blank"
            template.screenRect.width <= 0 || template.screenRect.height <= 0 -> "screen_rect_invalid"
            template.hasExplicitOutputSize && (template.outputWidth <= 0 || template.outputHeight <= 0) -> "output_size_invalid"
            else -> null
        }
    }

    private fun ScreenshotCandidate.toPreparedCandidate(
        resolutionSource: ResolutionSource,
        resolutionReason: String,
    ): PreparedScreenshotCandidate {
        return PreparedScreenshotCandidate(
            absolutePath = absolutePath,
            displayName = displayName,
            relativePath = relativePath,
            uri = uri?.toString(),
            lastModifiedMillis = lastModifiedMillis,
            sizeBytes = sizeBytes,
            mimeType = mimeType,
            width = width,
            height = height,
            ingressSignature = ingressSignature,
            finalCandidateSignature = finalCandidateSignature,
            resolutionSource = resolutionSource.name,
            resolutionReason = resolutionReason,
        )
    }

    private fun PreparedScreenshotCandidate.toReusableResolutionOrNull(): ResolveQueuedTaskResult? {
        val parsedSource = runCatching { enumValueOf<ResolutionSource>(resolutionSource) }.getOrNull() ?: return null
        val parsedUri = uri?.takeIf { it.isNotBlank() }?.let(Uri::parse)
        val hasPath = absolutePath.isNotBlank()
        if (!hasPath && parsedUri == null) {
            return null
        }

        if (hasPath) {
            val file = File(absolutePath)
            if (file.exists()) {
                if (!file.canRead() || file.length() <= 0L) {
                    return null
                }
                if (sizeBytes > 0L && file.length() != sizeBytes) {
                    return null
                }
                if (lastModifiedMillis > 0L && file.lastModified() != lastModifiedMillis) {
                    return null
                }
            } else if (parsedUri == null) {
                return null
            }
        }

        return ResolveQueuedTaskResult(
            candidate = ScreenshotCandidate(
                absolutePath = absolutePath,
                displayName = displayName,
                relativePath = relativePath,
                lastModifiedMillis = lastModifiedMillis,
                sizeBytes = sizeBytes,
                mimeType = mimeType,
                width = width,
                height = height,
                uri = parsedUri,
                isPending = false,
            ),
            source = parsedSource,
            reason = resolutionReason,
        )
    }

    private suspend fun deleteOriginalIfNeeded(
        autoDeleteOriginal: Boolean,
        candidate: ScreenshotCandidate,
    ): DeleteResult {
        if (!autoDeleteOriginal) {
            return DeleteResult(
                success = false,
                message = "未启用删除原图",
            )
        }

        return runCatching {
            outputRepository.deleteOriginal(candidate)
        }.onFailure { throwable ->
            logger.e(TAG, "删除原图失败 path=${candidate.absolutePath}", throwable)
        }.getOrElse { throwable ->
            DeleteResult(
                success = false,
                message = throwable.message ?: "删除原图异常",
            )
        }.also { result ->
            if (result.success) {
            logger.d(TAG, "删除原图成功 path=${candidate.absolutePath}")
            } else {
            logger.d(TAG, "删除原图失败 path=${candidate.absolutePath} reason=${result.message}")
            }
        }
    }

    private suspend fun handleFailure(
        task: QueuedScreenshotTask,
        decision: TaskFailureDecision,
    ) {
        when (decision) {
            is TaskFailureDecision.Final -> {
                val finalRetryCount = task.retryCount + 1
                taskStore.markFailedFinal(
                    taskId = task.id,
                    retryCount = finalRetryCount,
                    message = decision.message,
                )
                logger.d(
                    TAG,
            "任务终态失败 category=${decision.category} basis=${decision.basis} retry=$finalRetryCount reason=${decision.message} path=${task.absolutePath}",
                )
                appStateStore.setMonitoringPhase(decision.phase)
                publishResult(task, decision.statusOnPublish, decision.message)
            }

            is TaskFailureDecision.Retryable -> {
                val nextRetryCount = task.retryCount + 1
                if (nextRetryCount >= MAX_RETRYABLE_ATTEMPTS) {
                    taskStore.markFailedFinal(
                        taskId = task.id,
                        retryCount = nextRetryCount,
                        message = decision.message,
                    )
                    logger.d(
                        TAG,
            "任务终态失败 category=${decision.category} basis=${decision.basis} retry=$nextRetryCount reason=${decision.message} exhausted=true path=${task.absolutePath}",
                    )
                    appStateStore.setMonitoringPhase(decision.exhaustedPhase)
                    publishResult(task, decision.statusOnPublish, decision.message)
                    return
                }

                val delayMillis = retryDelayMillis(nextRetryCount)
                val nextAttemptAtMillis = System.currentTimeMillis() + delayMillis
                when (decision.queueState) {
                    RetryQueueState.WaitStable -> {
                        taskStore.markWaitStable(
                            taskId = task.id,
                            retryCount = nextRetryCount,
                            message = decision.message,
                            nextAttemptAtMillis = nextAttemptAtMillis,
                        )
                        appStateStore.setMonitoringPhase(MonitoringPhase.WaitingFileStable)
                    }

                    RetryQueueState.FailedRetryable -> {
                        taskStore.markFailedRetryable(
                            taskId = task.id,
                            retryCount = nextRetryCount,
                            message = decision.message,
                            nextAttemptAtMillis = nextAttemptAtMillis,
                        )
                        appStateStore.setMonitoringPhase(MonitoringPhase.Monitoring)
                    }
                }

                logger.d(
                    TAG,
            "任务待重试 category=${decision.category} basis=${decision.basis} queueState=${decision.queueState} retry=$nextRetryCount nextDelayMs=$delayMillis nextAttemptAt=$nextAttemptAtMillis reason=${decision.message} path=${task.absolutePath}",
                )
                publishResult(task, decision.statusOnPublish, decision.message)
            }
        }
    }

    private fun retryableFailure(
        message: String,
        basis: String,
        queueState: RetryQueueState,
        exhaustedPhase: MonitoringPhase,
        statusOnPublish: ProcessingStatus,
    ): TaskFailureDecision.Retryable {
        return TaskFailureDecision.Retryable(
            message = message,
            basis = basis,
            queueState = queueState,
            exhaustedPhase = exhaustedPhase,
            statusOnPublish = statusOnPublish,
        )
    }

    private fun finalFailure(
        message: String,
        basis: String,
        phase: MonitoringPhase,
        statusOnPublish: ProcessingStatus,
    ): TaskFailureDecision.Final {
        return TaskFailureDecision.Final(
            message = message,
            basis = basis,
            phase = phase,
            statusOnPublish = statusOnPublish,
        )
    }

    private fun retryDelayMillis(nextRetryCount: Int): Long {
        return RETRYABLE_BACKOFF_MILLIS.getOrElse(nextRetryCount - 1) { RETRYABLE_BACKOFF_MILLIS.last() }
    }

    private fun canProcessCurrentTask(): Boolean {
        return appStateStore.runtimeState.value.mode == AutoShellMode.ACTIVE
    }

    private fun isRecentlyProcessed(
        task: QueuedScreenshotTask,
        candidate: ScreenshotCandidate,
        recentProcessedKeys: List<String>,
    ): Boolean {
        if (recentProcessedKeys.isEmpty()) {
            return false
        }

        return listOfNotNull(
            ScreenshotCandidate.buildProcessedPathKey(task.absolutePath),
            ScreenshotCandidate.buildProcessedPathKey(candidate.absolutePath),
            candidate.dedupeKey,
        ).any { it in recentProcessedKeys }
    }

    private suspend fun skipAlreadyProcessedTask(
        task: QueuedScreenshotTask,
        candidate: ScreenshotCandidate,
        reason: String,
    ) {
        taskStore.markFailedFinal(
            taskId = task.id,
            retryCount = task.retryCount,
            message = "already_processed",
        )
        logger.d(
            TAG,
            "任务跳过 category=${TaskFailureCategory.FINAL} basis=already_processed reason=$reason taskPath=${task.absolutePath} candidatePath=${candidate.absolutePath}",
        )
        appStateStore.setMonitoringPhase(MonitoringPhase.Monitoring)
        publishResult(task, ProcessingStatus.Skipped, "already_processed")
    }

    private suspend fun deferTaskUntilMonitoringActive(task: QueuedScreenshotTask) {
        val nextAttemptAtMillis = System.currentTimeMillis() + SUSPENDED_WAIT_DELAY_MILLIS
        taskStore.markWaitStable(
            taskId = task.id,
            retryCount = task.retryCount,
            message = "monitoring_suspended",
            nextAttemptAtMillis = nextAttemptAtMillis,
        )
        logger.d(
            TAG,
            "任务延后 category=${TaskFailureCategory.RETRYABLE} basis=suspend_interrupted retry=${task.retryCount} nextAttemptAt=$nextAttemptAtMillis path=${task.absolutePath} mode=${appStateStore.runtimeState.value.mode}",
        )
        appStateStore.setMonitoringPhase(MonitoringPhase.Monitoring)
    }

    private fun publishSuccessResult(
        task: QueuedScreenshotTask,
        candidate: ScreenshotCandidate,
        templateId: String,
        outputPath: String,
        deleteResult: DeleteResult,
        detailMessage: String,
    ) {
        appStateStore.setLastProcessingResult(
            ProcessingResult(
                processedAtMillis = System.currentTimeMillis(),
                sourcePath = candidate.absolutePath,
                outputPath = outputPath,
                templateId = templateId,
                triggerSource = task.source,
                deletedOriginal = deleteResult.success,
                deleteMessage = deleteResult.message,
                status = ProcessingStatus.Success,
                detailMessage = detailMessage,
            ),
        )
    }

    private fun publishResult(
        task: QueuedScreenshotTask,
        status: ProcessingStatus,
        detailMessage: String,
    ) {
        appStateStore.setLastProcessingResult(
            ProcessingResult(
                processedAtMillis = System.currentTimeMillis(),
                sourcePath = task.absolutePath,
                outputPath = task.outputPath,
                templateId = appStateStore.runtimeState.value.lastProcessingResult?.templateId.orEmpty(),
                triggerSource = task.source,
                deletedOriginal = false,
                deleteMessage = null,
                status = status,
                detailMessage = detailMessage,
            ),
        )
    }

    private enum class RetryQueueState {
        WaitStable,
        FailedRetryable,
    }

    private enum class TaskFailureCategory {
        RETRYABLE,
        FINAL,
    }

    private sealed class TaskFailureDecision(
        val category: TaskFailureCategory,
        open val message: String,
        open val basis: String,
        open val statusOnPublish: ProcessingStatus,
    ) {
        data class Retryable(
            override val message: String,
            override val basis: String,
            val queueState: RetryQueueState,
            val exhaustedPhase: MonitoringPhase,
            override val statusOnPublish: ProcessingStatus,
        ) : TaskFailureDecision(
            category = TaskFailureCategory.RETRYABLE,
            message = message,
            basis = basis,
            statusOnPublish = statusOnPublish,
        )

        data class Final(
            override val message: String,
            override val basis: String,
            val phase: MonitoringPhase,
            override val statusOnPublish: ProcessingStatus,
        ) : TaskFailureDecision(
            category = TaskFailureCategory.FINAL,
            message = message,
            basis = basis,
            statusOnPublish = statusOnPublish,
        )
    }

    private sealed class DecodeTarget {
        data class PathTarget(val absolutePath: String) : DecodeTarget()

        data class UriTarget(val uri: Uri) : DecodeTarget()

        fun key(): String = when (this) {
            is PathTarget -> "path:$absolutePath"
            is UriTarget -> "uri:$uri"
        }

        fun describe(): String = when (this) {
            is PathTarget -> absolutePath
            is UriTarget -> uri.toString()
        }
    }

    private companion object {
        const val TAG = "QueueWorker"
        const val SUSPENDED_WAIT_DELAY_MILLIS = 200L
        const val MAX_RETRYABLE_ATTEMPTS = 8
        val RETRYABLE_BACKOFF_MILLIS = longArrayOf(200L, 500L, 1_000L, 2_000L, 4_000L, 4_000L, 4_000L)
    }
}
