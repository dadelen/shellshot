package com.example.shellshot.media

import android.os.Environment
import com.example.shellshot.data.OutputNamingStrategy
import java.io.File

object ScreenshotDirectories {
    const val OUTPUT_PREFIX = "shellshot_"
    const val LEGACY_OUTPUT_PREFIX = "autoshell_"
    const val OUTPUT_SUFFIX = "_shell"
    const val OUTPUT_RELATIVE_PATH = "Pictures/AutoShell/"
    const val DEFAULT_SCREENSHOT_RELATIVE_PATH = "Pictures/Screenshots/"

    private val generatedPrefixes = listOf(
        OUTPUT_PREFIX,
        LEGACY_OUTPUT_PREFIX,
    )
    private val presetRelativePaths = listOf(
        DEFAULT_SCREENSHOT_RELATIVE_PATH,
        "DCIM/Screenshots/",
        "Pictures/ScreenShots/",
        "DCIM/ScreenShots/",
        "Pictures/Screenshot/",
        "DCIM/Screenshot/",
    )
    private val screenshotPathKeywords = listOf(
        "screenshot",
        "screen_shot",
        "screen-shot",
        "screencapture",
        "screen capture",
        "screencap",
        "截屏",
        "截图",
        "截圖",
    )

    fun screenshotDirectory(configuredRelativePath: String): File {
        return toAbsoluteDirectory(resolveScreenshotRelativePath(configuredRelativePath))
    }

    fun candidateDirectories(configuredRelativePath: String): List<File> = listOf(screenshotDirectory(configuredRelativePath))

    fun presetScreenshotRelativePaths(): List<String> {
        return presetRelativePaths
            .map(::resolveScreenshotRelativePath)
            .distinctBy(String::lowercase)
    }

    @Deprecated("Use screenshotDirectory(configuredRelativePath)")
    fun primaryScreenshotDirectory(): File = screenshotDirectory(DEFAULT_SCREENSHOT_RELATIVE_PATH)

    @Deprecated("Use candidateDirectories(configuredRelativePath)")
    fun candidateDirectories(): List<File> = candidateDirectories(DEFAULT_SCREENSHOT_RELATIVE_PATH)

    fun outputDirectory(): File = toAbsoluteDirectory(OUTPUT_RELATIVE_PATH)

    fun normalizeRelativePath(relativePath: String?): String? {
        if (relativePath.isNullOrBlank()) {
            return null
        }

        return relativePath
            .replace('\\', '/')
            .removePrefix("/")
            .trim()
            .takeIf { it.isNotBlank() }
            ?.let { path -> if (path.endsWith("/")) path else "$path/" }
    }

    fun resolveScreenshotRelativePath(relativePath: String?): String {
        return normalizeRelativePath(relativePath) ?: DEFAULT_SCREENSHOT_RELATIVE_PATH
    }

    fun relativePathFromAbsolutePath(absolutePath: String): String? {
        val rootPath = externalRoot().absolutePath.replace('\\', '/').removeSuffix("/")
        val normalizedPath = absolutePath.replace('\\', '/')
        if (!normalizedPath.startsWith(rootPath, ignoreCase = true)) {
            return null
        }

        return normalizeRelativePath(
            normalizedPath.removePrefix(rootPath).removePrefix("/").substringBeforeLast("/", ""),
        )
    }

    fun toAbsoluteDirectory(relativePath: String): File {
        return File(externalRoot(), relativePath.removeSuffix("/"))
    }

    fun toAbsolutePath(relativePath: String?, displayName: String?): String? {
        val normalizedPath = normalizeRelativePath(relativePath) ?: return null
        val safeDisplayName = displayName?.takeIf { it.isNotBlank() } ?: return null
        return File(externalRoot(), normalizedPath + safeDisplayName).absolutePath
    }

    fun normalizeAbsolutePath(absolutePath: String?): String? {
        return absolutePath
            ?.replace('\\', '/')
            ?.trim()
            ?.takeIf { it.isNotBlank() }
            ?.lowercase()
    }

    fun looksLikeImageFile(fileName: String?): Boolean {
        if (fileName.isNullOrBlank()) {
            return false
        }

        val lowerCaseName = fileName.lowercase()
        return lowerCaseName.endsWith(".png") ||
            lowerCaseName.endsWith(".jpg") ||
            lowerCaseName.endsWith(".jpeg") ||
            lowerCaseName.endsWith(".webp")
    }

    fun looksLikeScreenshotRelativePath(
        relativePath: String?,
        bucketName: String? = null,
    ): Boolean {
        val normalizedPath = normalizeRelativePath(relativePath)
        return containsScreenshotKeyword(normalizedPath) || containsScreenshotKeyword(bucketName)
    }

    fun looksLikeScreenshotFileName(fileName: String?): Boolean {
        return containsScreenshotKeyword(fileName)
    }

    fun isGeneratedByApp(fileName: String?): Boolean {
        if (fileName.isNullOrBlank()) {
            return false
        }

        return generatedPrefixes.any { prefix ->
            fileName.startsWith(prefix, ignoreCase = true)
        } ||
            fileName.substringBeforeLast('.').endsWith(OUTPUT_SUFFIX, ignoreCase = true)
    }

    fun isOutputRelativePath(relativePath: String?): Boolean {
        val normalizedPath = normalizeRelativePath(relativePath) ?: return false
        return normalizedPath.startsWith(OUTPUT_RELATIVE_PATH, ignoreCase = true)
    }

    fun isOutputAbsolutePath(absolutePath: String?): Boolean {
        val normalizedPath = normalizeAbsolutePath(absolutePath) ?: return false
        val outputRoot = normalizeAbsolutePath(outputDirectory().absolutePath) ?: return false
        return normalizedPath.startsWith(outputRoot)
    }

    fun isOutputLocation(
        absolutePath: String?,
        relativePath: String?,
        bucketName: String? = null,
    ): Boolean {
        if (isOutputRelativePath(relativePath) || isOutputAbsolutePath(absolutePath)) {
            return true
        }

        return bucketName.equals(outputDirectory().name, ignoreCase = true)
    }

    fun isScreenshotRelativePath(
        relativePath: String?,
        configuredRelativePath: String,
    ): Boolean {
        val normalizedPath = normalizeRelativePath(relativePath) ?: return false
        return normalizedPath.equals(resolveScreenshotRelativePath(configuredRelativePath), ignoreCase = true)
    }

    fun isScreenshotAbsolutePath(
        absolutePath: String?,
        configuredRelativePath: String,
    ): Boolean {
        val normalizedPath = normalizeAbsolutePath(absolutePath) ?: return false
        val screenshotRoot = normalizeAbsolutePath(screenshotDirectory(configuredRelativePath).absolutePath) ?: return false
        return normalizedPath.startsWith(screenshotRoot)
    }

    fun isScreenshotSource(
        absolutePath: String?,
        relativePath: String?,
        configuredRelativePath: String,
    ): Boolean {
        return isScreenshotRelativePath(relativePath, configuredRelativePath) ||
            isScreenshotAbsolutePath(absolutePath, configuredRelativePath)
    }

    fun buildOutputFileName(
        sourceDisplayName: String,
        namingStrategy: OutputNamingStrategy,
    ): String {
        val baseName = sourceDisplayName.substringBeforeLast('.').ifBlank { "screenshot" }
        return when (namingStrategy) {
            OutputNamingStrategy.Prefix -> "${OUTPUT_PREFIX}${baseName}.png"
            OutputNamingStrategy.Suffix -> "${baseName}${OUTPUT_SUFFIX}.png"
        }
    }

    fun recentScreenshotFiles(
        screenshotRelativePath: String,
        limit: Int,
        recentWindowMillis: Long,
        minLastModifiedMillis: Long? = null,
        nowMillis: Long = System.currentTimeMillis(),
    ): List<File> {
        val directory = screenshotDirectory(screenshotRelativePath)
        if (!directory.exists() || !directory.isDirectory) {
            return emptyList()
        }

        return directory.listFiles()
            ?.asSequence()
            ?.filter { file ->
                val lastModified = file.lastModified()
                file.isFile &&
                    looksLikeImageFile(file.name) &&
                    !isGeneratedByApp(file.name) &&
                    nowMillis - lastModified in 0..recentWindowMillis &&
                    (minLastModifiedMillis == null || lastModified >= minLastModifiedMillis)
            }
            ?.sortedByDescending(File::lastModified)
            ?.distinctBy { normalizeAbsolutePath(it.absolutePath) }
            ?.take(limit)
            ?.toList()
            .orEmpty()
    }

    private fun containsScreenshotKeyword(value: String?): Boolean {
        if (value.isNullOrBlank()) {
            return false
        }
        return screenshotPathKeywords.any { keyword ->
            value.contains(keyword, ignoreCase = true)
        }
    }

    private fun externalRoot(): File = Environment.getExternalStorageDirectory()
}
