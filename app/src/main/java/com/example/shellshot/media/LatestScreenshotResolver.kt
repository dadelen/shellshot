package com.example.shellshot.media

import android.net.Uri
import com.example.shellshot.data.AppPrefs
import com.example.shellshot.utils.ShellLogger
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

enum class ResolutionSource {
    FIXED_PATH_PRIMARY,
    MEDIASTORE_FALLBACK,
    NONE,
}

data class ResolveQueuedTaskResult(
    val candidate: ScreenshotCandidate?,
    val source: ResolutionSource,
    val reason: String,
)

class LatestScreenshotResolver(
    private val appPrefs: AppPrefs,
    private val mediaStoreRepository: MediaStoreRepository,
    private val logger: ShellLogger,
) {
    suspend fun resolveQueuedTask(
        absolutePath: String,
        displayName: String,
        relativePath: String?,
        changedUri: Uri?,
    ): ScreenshotCandidate? {
        return resolveQueuedTaskDetailed(
            absolutePath = absolutePath,
            displayName = displayName,
            relativePath = relativePath,
            changedUri = changedUri,
        ).candidate
    }

    suspend fun resolveQueuedTaskDetailed(
        absolutePath: String,
        displayName: String,
        relativePath: String?,
        changedUri: Uri?,
    ): ResolveQueuedTaskResult = withContext(Dispatchers.Default) {
        val screenshotRelativePath = appPrefs.currentSettings().screenshotRelativePath
        logger.d(
            TAG,
            "开始解析候选 fixedPath=$absolutePath displayName=$displayName relativePath=$relativePath configuredDir=$screenshotRelativePath changedUri=$changedUri",
        )

        val configuredPathEvaluation = evaluateConfiguredPathCandidate(
            absolutePath = absolutePath,
            displayName = displayName,
            relativePath = relativePath,
            screenshotRelativePath = screenshotRelativePath,
        )
        logConfiguredPathEvaluation(configuredPathEvaluation)

        if (configuredPathEvaluation.acceptedCandidate != null) {
            return@withContext logAndReturnSelection(
                ResolveQueuedTaskResult(
                    candidate = configuredPathEvaluation.acceptedCandidate,
                    source = ResolutionSource.FIXED_PATH_PRIMARY,
                    reason = configuredPathEvaluation.reason,
                ),
            )
        }

        if (!shouldEnableMediaStoreFallback(configuredPathEvaluation.reason)) {
            logger.d(
                TAG,
                "配置路径候选被拒绝且不启用 MediaStore reason=${configuredPathEvaluation.reason}",
            )
            return@withContext logAndReturnSelection(
                ResolveQueuedTaskResult(
                    candidate = null,
                    source = ResolutionSource.NONE,
                    reason = configuredPathEvaluation.reason,
                ),
            )
        }

        logger.d(
            TAG,
            "启用 MediaStore 兜底 reason=${configuredPathEvaluation.reason} fixedPath=$absolutePath configuredDir=$screenshotRelativePath changedUri=$changedUri",
        )
        val mediaStoreCandidates = mediaStoreRepository.queryRecentImageCandidates(
            limit = RECENT_LIMIT,
            changedUri = changedUri,
        ).filter { candidate ->
            ScreenshotRules.isEligibleScreenshotCandidate(
                candidate = candidate,
                screenshotRelativePath = screenshotRelativePath,
            )
        }
        val mediaStoreCandidateSummary = mediaStoreCandidates.joinToString(separator = " ; ") { summarizeCandidate(it) }
        logger.d(
            TAG,
            "MediaStore 输入候选 count=${mediaStoreCandidates.size} configuredDir=$screenshotRelativePath candidates=$mediaStoreCandidateSummary",
        )

        val selectedFallback = mediaStoreCandidates
            .sortedWith(
                compareByDescending<ScreenshotCandidate> { changedUri != null && it.uri == changedUri }
                    .thenByDescending { !displayName.isBlank() && it.displayName.equals(displayName, ignoreCase = true) }
                    .thenByDescending { it.capturedAtMillis },
            )
            .firstOrNull()

        if (selectedFallback != null) {
            return@withContext logAndReturnSelection(
                ResolveQueuedTaskResult(
                    candidate = selectedFallback,
                    source = ResolutionSource.MEDIASTORE_FALLBACK,
                    reason = configuredPathEvaluation.reason,
                ),
            )
        }

        logger.d(
            TAG,
            "MediaStore 未命中可用候选 reason=${configuredPathEvaluation.reason} fixedPath=$absolutePath changedUri=$changedUri",
        )
        logAndReturnSelection(
            ResolveQueuedTaskResult(
                candidate = null,
                source = ResolutionSource.NONE,
                reason = REASON_MEDIASTORE_NO_MATCH,
            ),
        )
    }

    private fun evaluateConfiguredPathCandidate(
        absolutePath: String,
        displayName: String,
        relativePath: String?,
        screenshotRelativePath: String,
    ): ConfiguredPathEvaluation {
        if (absolutePath.isBlank() || displayName.isBlank()) {
            return ConfiguredPathEvaluation(reason = REASON_INVALID_INPUT)
        }

        val resolvedRelativePath = relativePath ?: ScreenshotDirectories.relativePathFromAbsolutePath(absolutePath)
        if (ScreenshotDirectories.isGeneratedByApp(displayName)) {
            return ConfiguredPathEvaluation(reason = REASON_GENERATED_BY_APP)
        }
        if (
            ScreenshotDirectories.isOutputLocation(
                absolutePath = absolutePath,
                relativePath = resolvedRelativePath,
                bucketName = File(absolutePath).parentFile?.name,
            )
        ) {
            return ConfiguredPathEvaluation(reason = REASON_OUTPUT_DIRECTORY)
        }
        if (!ScreenshotDirectories.looksLikeImageFile(displayName)) {
            return ConfiguredPathEvaluation(reason = REASON_UNSUPPORTED_EXTENSION)
        }
        if (!ScreenshotDirectories.isScreenshotSource(absolutePath, resolvedRelativePath, screenshotRelativePath)) {
            return ConfiguredPathEvaluation(reason = REASON_NOT_CONFIGURED_SCREENSHOT_PATH)
        }

        val file = File(absolutePath)
        val candidate = ScreenshotCandidate(
            absolutePath = absolutePath,
            displayName = displayName,
            relativePath = resolvedRelativePath,
            lastModifiedMillis = file.lastModified().takeIf { it > 0L } ?: System.currentTimeMillis(),
            sizeBytes = file.length().takeIf { it > 0L } ?: 0L,
            mimeType = ScreenshotRules.inferMimeType(file),
            uri = null,
            isPending = false,
        )

        return when {
            !file.exists() -> ConfiguredPathEvaluation(rejectedCandidate = candidate, reason = REASON_CONFIGURED_FILE_MISSING)
            !file.canRead() -> ConfiguredPathEvaluation(rejectedCandidate = candidate, reason = REASON_CONFIGURED_FILE_UNREADABLE)
            isLikelyTemporaryFile(candidate) -> ConfiguredPathEvaluation(rejectedCandidate = candidate, reason = REASON_CONFIGURED_TEMPORARY_FILE)
            hasClearlyAbnormalMetadata(candidate) -> ConfiguredPathEvaluation(rejectedCandidate = candidate, reason = REASON_CONFIGURED_METADATA_ABNORMAL)
            else -> ConfiguredPathEvaluation(acceptedCandidate = candidate, reason = REASON_FIXED_PRIMARY_ACCEPTED)
        }
    }

    private fun shouldEnableMediaStoreFallback(reason: String): Boolean {
        return reason == REASON_CONFIGURED_FILE_MISSING ||
            reason == REASON_CONFIGURED_FILE_UNREADABLE ||
            reason == REASON_CONFIGURED_TEMPORARY_FILE ||
            reason == REASON_CONFIGURED_METADATA_ABNORMAL
    }

    private fun hasClearlyAbnormalMetadata(candidate: ScreenshotCandidate): Boolean {
        return candidate.relativePath.isNullOrBlank() ||
            candidate.lastModifiedMillis <= 0L ||
            candidate.displayName.isBlank()
    }

    private fun isLikelyTemporaryFile(candidate: ScreenshotCandidate): Boolean {
        val name = candidate.displayName.lowercase()
        val path = candidate.absolutePath.lowercase()
        return name.startsWith(".") ||
            "pending" in name ||
            "pending" in path ||
            ".tmp" in name ||
            ".tmp" in path ||
            "temp" in name ||
            "temp" in path
    }

    private fun logConfiguredPathEvaluation(evaluation: ConfiguredPathEvaluation) {
        val candidate = evaluation.acceptedCandidate ?: evaluation.rejectedCandidate
        if (candidate == null) {
            logger.d(TAG, "配置路径候选不可用 reason=${evaluation.reason}")
            return
        }

        val file = File(candidate.absolutePath)
        logger.d(
            TAG,
            "配置路径候选评估 path=${candidate.absolutePath} exists=${file.exists()} readable=${file.canRead()} size=${file.length().takeIf { file.exists() } ?: 0L} relativePath=${candidate.relativePath} reason=${evaluation.reason}",
        )
    }

    private fun summarizeCandidate(candidate: ScreenshotCandidate): String {
        return "path=${candidate.absolutePath},uri=${candidate.uri},size=${candidate.sizeBytes},capturedAt=${candidate.capturedAtMillis}"
    }

    private fun logAndReturnSelection(result: ResolveQueuedTaskResult): ResolveQueuedTaskResult {
        logger.d(
            TAG,
            "最终选择 source=${result.source} reason=${result.reason} path=${result.candidate?.absolutePath} uri=${result.candidate?.uri}",
        )
        return result
    }

    private data class ConfiguredPathEvaluation(
        val acceptedCandidate: ScreenshotCandidate? = null,
        val rejectedCandidate: ScreenshotCandidate? = null,
        val reason: String,
    )

    private companion object {
        const val TAG = "LatestShotResolver"
        const val RECENT_LIMIT = 4

        const val REASON_FIXED_PRIMARY_ACCEPTED = "fixed_primary_accepted"
        const val REASON_INVALID_INPUT = "invalid_input"
        const val REASON_GENERATED_BY_APP = "generated_by_app"
        const val REASON_OUTPUT_DIRECTORY = "output_directory"
        const val REASON_UNSUPPORTED_EXTENSION = "unsupported_extension"
        const val REASON_NOT_CONFIGURED_SCREENSHOT_PATH = "not_configured_screenshot_path"
        const val REASON_CONFIGURED_FILE_MISSING = "configured_file_missing"
        const val REASON_CONFIGURED_FILE_UNREADABLE = "configured_file_unreadable"
        const val REASON_CONFIGURED_TEMPORARY_FILE = "configured_temporary_file"
        const val REASON_CONFIGURED_METADATA_ABNORMAL = "configured_metadata_abnormal"
        const val REASON_MEDIASTORE_NO_MATCH = "mediastore_no_match"
    }
}
