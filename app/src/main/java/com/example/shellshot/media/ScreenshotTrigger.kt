package com.example.shellshot.media

import android.net.Uri

data class ScreenshotTrigger(
    val source: Source,
    val changedUri: Uri? = null,
    val absolutePath: String? = null,
    val displayName: String? = null,
    val relativePath: String? = null,
    val eventMask: Int? = null,
) {
    val sourceLabel: String
        get() = source.label

    enum class Source(val label: String) {
        FileObserver("file_observer"),
        MediaStoreFallback("media_store_fallback"),
        MediaStoreRecovery("media_store_recovery"),
        StartupRecovery("startup_recovery"),
        DirectoryRecovery("directory_recovery"),
    }

    companion object {
        fun fileObserver(
            absolutePath: String,
            displayName: String,
            relativePath: String?,
            eventMask: Int,
        ): ScreenshotTrigger = ScreenshotTrigger(
            source = Source.FileObserver,
            absolutePath = absolutePath,
            displayName = displayName,
            relativePath = relativePath,
            eventMask = eventMask,
        )

        fun mediaFallback(changedUri: Uri?): ScreenshotTrigger = ScreenshotTrigger(
            source = Source.MediaStoreFallback,
            changedUri = changedUri,
        )

        fun mediaStoreRecovery(changedUri: Uri?): ScreenshotTrigger = ScreenshotTrigger(
            source = Source.MediaStoreRecovery,
            changedUri = changedUri,
        )

        fun startupRecovery(): ScreenshotTrigger = ScreenshotTrigger(
            source = Source.StartupRecovery,
        )

        fun directoryRecovery(
            absolutePath: String,
            displayName: String,
            relativePath: String?,
        ): ScreenshotTrigger = ScreenshotTrigger(
            source = Source.DirectoryRecovery,
            absolutePath = absolutePath,
            displayName = displayName,
            relativePath = relativePath,
        )
    }
}
