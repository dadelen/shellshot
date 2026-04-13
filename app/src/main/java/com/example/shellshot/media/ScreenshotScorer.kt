package com.example.shellshot.media

import java.io.File
import kotlin.math.abs

object ScreenshotRules {
    val mediaStoreDisplayNamePatterns = listOf(
        "Screenshot%",
        "screenshot%",
        "ScreenShot%",
        "ScreenCapture%",
        "screen_capture%",
        "screencap%",
        "%\u622a\u5c4f%",
        "%\u622a\u56fe%",
        "%\u622a\u5716%",
    )

    private val screenshotPathKeywords = listOf(
        "pictures/screenshots/",
        "dcim/screenshots/",
        "pictures/screencapture/",
        "dcim/screencapture/",
        "pictures/screenshots/",
        "dcim/screenshots/",
        "\u622a\u5c4f",
        "\u622a\u56fe",
        "\u622a\u5716",
    )
    private val screenshotFileKeywords = listOf(
        "screenshot",
        "screen_shot",
        "screen-shot",
        "screen capture",
        "screencapture",
        "screencap",
        "screen",
        "\u622a\u5c4f",
        "\u622a\u56fe",
        "\u622a\u5716",
    )

    fun isPotentialScreenshotFile(
        absolutePath: String,
        displayName: String,
        screenshotRelativePath: String,
    ): Boolean {
        val relativePath = ScreenshotDirectories.relativePathFromAbsolutePath(absolutePath)
        if (
            ScreenshotDirectories.isGeneratedByApp(displayName) ||
            ScreenshotDirectories.isOutputLocation(
                absolutePath = absolutePath,
                relativePath = relativePath,
                bucketName = File(absolutePath).parentFile?.name,
            )
        ) {
            return false
        }
        if (!ScreenshotDirectories.looksLikeImageFile(displayName)) {
            return false
        }
        if (!ScreenshotDirectories.isScreenshotSource(absolutePath, relativePath, screenshotRelativePath)) {
            return false
        }

        return true
    }

    fun isEligibleScreenshotCandidate(
        candidate: ScreenshotCandidate,
        screenshotRelativePath: String,
        nowMillis: Long = System.currentTimeMillis(),
        recentWindowMillis: Long = 30_000L,
    ): Boolean {
        return isPotentialScreenshotFile(
            absolutePath = candidate.absolutePath,
            displayName = candidate.displayName,
            screenshotRelativePath = screenshotRelativePath,
        ) &&
            isRecentCandidate(candidate, nowMillis, recentWindowMillis) &&
            candidate.sizeBytes > 0L &&
            !candidate.isPending
    }

    fun isRecentCandidate(
        candidate: ScreenshotCandidate,
        nowMillis: Long = System.currentTimeMillis(),
        windowMillis: Long = 30_000L,
    ): Boolean {
        return abs(nowMillis - candidate.capturedAtMillis) <= windowMillis
    }

    fun matchReason(candidate: ScreenshotCandidate, screenshotRelativePath: String): String {
        val reasons = buildList {
            if (
                ScreenshotDirectories.isScreenshotSource(
                    absolutePath = candidate.absolutePath,
                    relativePath = candidate.relativePath,
                    configuredRelativePath = screenshotRelativePath,
                )
            ) {
                add("configured_screenshot_directory")
            }
            if (containsKeyword(candidate.relativePath, screenshotPathKeywords)) add("screenshot_keyword_directory")
            if (containsKeyword(candidate.displayName, screenshotFileKeywords)) add("screenshot_file_name")
            if (
                ScreenshotDirectories.isOutputLocation(
                    absolutePath = candidate.absolutePath,
                    relativePath = candidate.relativePath,
                    bucketName = candidate.bucketName,
                )
            ) {
                add("output_directory")
            }
            if (ScreenshotDirectories.isGeneratedByApp(candidate.displayName)) add("generated_by_app")
        }
        return reasons.joinToString(" + ").ifBlank { "no_match" }
    }

    fun inferMimeType(file: File): String {
        return when (file.extension.lowercase()) {
            "png" -> "image/png"
            "jpg", "jpeg" -> "image/jpeg"
            "webp" -> "image/webp"
            else -> "image/*"
        }
    }

    private fun containsKeyword(value: String?, keywords: List<String>): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        return keywords.any { keyword -> value.contains(keyword, ignoreCase = true) }
    }
}
