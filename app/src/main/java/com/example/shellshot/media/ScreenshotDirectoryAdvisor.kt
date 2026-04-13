package com.example.shellshot.media

import com.example.shellshot.utils.ShellLogger
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

data class ScreenshotDirectoryRecommendation(
    val relativePath: String,
    val absolutePath: String,
    val sourceLabel: String,
    val reason: String,
)

class ScreenshotDirectoryAdvisor(
    private val mediaStoreRepository: MediaStoreRepository,
    private val logger: ShellLogger,
) {
    suspend fun detectRecommendations(
        currentRelativePath: String,
        limit: Int = DEFAULT_RECOMMENDATION_LIMIT,
    ): List<ScreenshotDirectoryRecommendation> = withContext(Dispatchers.IO) {
        val normalizedCurrent = ScreenshotDirectories.resolveScreenshotRelativePath(currentRelativePath)
        val accumulators = linkedMapOf<String, RecommendationAccumulator>()

        ScreenshotDirectories.presetScreenshotRelativePaths().forEach { relativePath ->
            val recentFiles = ScreenshotDirectories.recentScreenshotFiles(
                screenshotRelativePath = relativePath,
                limit = PRESET_SCAN_SAMPLE_LIMIT,
                recentWindowMillis = PRESET_SCAN_WINDOW_MILLIS,
            )
            if (recentFiles.isNotEmpty()) {
                accumulators.getOrPut(relativePath.lowercase()) {
                    RecommendationAccumulator(relativePath = relativePath)
                }.notePresetHit(recentFiles)
            }
        }

        val mediaStoreCandidates = runCatching {
            mediaStoreRepository.queryRecentImageCandidates(limit = MEDIASTORE_SCAN_LIMIT)
        }.onFailure { throwable ->
            logger.e(TAG, "自动检测截图目录时读取 MediaStore 失败", throwable)
        }.getOrDefault(emptyList())

        mediaStoreCandidates.forEach { candidate ->
            val relativePath = ScreenshotDirectories.normalizeRelativePath(candidate.relativePath) ?: return@forEach
            if (!isLikelyRecommendationCandidate(candidate, relativePath)) {
                return@forEach
            }
            accumulators.getOrPut(relativePath.lowercase()) {
                RecommendationAccumulator(relativePath = relativePath)
            }.noteMediaStoreHit(candidate)
        }

        val recommendations = accumulators.values
            .filter { accumulator ->
                val directory = File(accumulator.absolutePath)
                directory.exists() && directory.isDirectory
            }
            .sortedWith(
                compareByDescending<RecommendationAccumulator> { it.score }
                    .thenByDescending { it.latestCapturedAtMillis }
                    .thenBy { if (it.relativePath.equals(normalizedCurrent, ignoreCase = true)) 0 else 1 }
                    .thenBy { it.relativePath.lowercase() },
            )
            .take(limit)
            .map(RecommendationAccumulator::toRecommendation)

        logger.d(
            TAG,
            "截图目录推荐 count=${recommendations.size} current=$normalizedCurrent recommendations=${recommendations.joinToString { it.relativePath }}",
        )
        recommendations
    }

    private fun isLikelyRecommendationCandidate(
        candidate: ScreenshotCandidate,
        relativePath: String,
    ): Boolean {
        if (!ScreenshotDirectories.looksLikeImageFile(candidate.displayName)) {
            return false
        }
        if (candidate.isPending) {
            return false
        }
        if (ScreenshotDirectories.isGeneratedByApp(candidate.displayName)) {
            return false
        }
        if (
            ScreenshotDirectories.isOutputLocation(
                absolutePath = candidate.absolutePath,
                relativePath = candidate.relativePath,
                bucketName = candidate.bucketName,
            )
        ) {
            return false
        }
        if (
            !ScreenshotDirectories.looksLikeScreenshotRelativePath(
                relativePath = relativePath,
                bucketName = candidate.bucketName,
            ) &&
            !ScreenshotDirectories.looksLikeScreenshotFileName(candidate.displayName)
        ) {
            return false
        }

        val directory = ScreenshotDirectories.toAbsoluteDirectory(relativePath)
        return directory.exists() && directory.isDirectory
    }

    private data class RecommendationAccumulator(
        val relativePath: String,
        val absolutePath: String = ScreenshotDirectories.toAbsoluteDirectory(relativePath).absolutePath,
        var presetHitCount: Int = 0,
        var mediaStoreHitCount: Int = 0,
        var latestCapturedAtMillis: Long = 0L,
    ) {
        val score: Int
            get() = presetHitCount * 40 + mediaStoreHitCount * 25

        fun notePresetHit(recentFiles: List<File>) {
            presetHitCount += recentFiles.size
            latestCapturedAtMillis = maxOf(latestCapturedAtMillis, recentFiles.maxOfOrNull(File::lastModified) ?: 0L)
        }

        fun noteMediaStoreHit(candidate: ScreenshotCandidate) {
            mediaStoreHitCount += 1
            latestCapturedAtMillis = maxOf(latestCapturedAtMillis, candidate.capturedAtMillis)
        }

        fun toRecommendation(): ScreenshotDirectoryRecommendation {
            val sourceLabel = when {
                mediaStoreHitCount > 0 && presetHitCount > 0 -> "综合推荐"
                mediaStoreHitCount > 0 -> "最近截图"
                else -> "常用目录"
            }
            val reason = when {
                mediaStoreHitCount > 0 && presetHitCount > 0 ->
                    "MediaStore 最近命中 $mediaStoreHitCount 张，目录内发现 $presetHitCount 个最近文件"
                mediaStoreHitCount > 0 ->
                    "MediaStore 最近命中 $mediaStoreHitCount 张"
                else ->
                    "目录内发现 $presetHitCount 个最近文件"
            }
            return ScreenshotDirectoryRecommendation(
                relativePath = relativePath,
                absolutePath = absolutePath,
                sourceLabel = sourceLabel,
                reason = reason,
            )
        }
    }

    private companion object {
        const val TAG = "ScreenshotDirectoryAdvisor"
        const val MEDIASTORE_SCAN_LIMIT = 48
        const val PRESET_SCAN_SAMPLE_LIMIT = 6
        const val PRESET_SCAN_WINDOW_MILLIS = 45L * 24L * 60L * 60L * 1000L
        const val DEFAULT_RECOMMENDATION_LIMIT = 4
    }
}
