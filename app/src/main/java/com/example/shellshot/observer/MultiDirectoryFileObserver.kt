package com.example.shellshot.observer

import android.os.FileObserver
import com.example.shellshot.media.ScreenshotDirectories
import com.example.shellshot.utils.ShellLogger
import java.io.File

data class DirectoryWatchEvent(
    val directory: File,
    val relativePath: String?,
    val fileName: String,
    val eventMask: Int,
    val absolutePath: String,
)

class ScreenshotDirectoryWatcher(
    private val directory: File,
    private val logger: ShellLogger,
    private val onEvent: (DirectoryWatchEvent) -> Unit,
) {
    @Suppress("DEPRECATION")
    private val observer = object : FileObserver(directory.absolutePath, WATCH_MASK) {
        override fun onEvent(event: Int, path: String?) {
            val fileName = path?.takeIf { it.isNotBlank() } ?: return
            if (!ScreenshotDirectories.looksLikeImageFile(fileName)) {
                return
            }

            val eventRecord = DirectoryWatchEvent(
                directory = directory,
                relativePath = ScreenshotDirectories.relativePathFromAbsolutePath(directory.absolutePath),
                fileName = fileName,
                eventMask = event,
                absolutePath = File(directory, fileName).absolutePath,
            )
            onEvent(eventRecord)
        }
    }

    fun start() {
        observer.startWatching()
    }

    fun stop() {
        observer.stopWatching()
    }

    companion object {
        private const val WATCH_MASK =
            FileObserver.CREATE or FileObserver.CLOSE_WRITE or FileObserver.MOVED_TO
        private const val TAG = "ScreenshotWatcher"

        private fun eventMaskLabel(mask: Int): String = buildList {
            if (mask and FileObserver.CREATE != 0) add("CREATE")
            if (mask and FileObserver.CLOSE_WRITE != 0) add("CLOSE_WRITE")
            if (mask and FileObserver.MOVED_TO != 0) add("MOVED_TO")
        }.joinToString(separator = "|").ifBlank { mask.toString() }
    }
}

class MultiDirectoryFileObserver(
    private val logger: ShellLogger,
    private val onEvent: (DirectoryWatchEvent) -> Unit,
) {
    private val watchers = linkedMapOf<String, ScreenshotDirectoryWatcher>()

    fun refreshDirectories(
        candidateDirectories: List<File>,
        forceRebind: Boolean = false,
    ): List<File> {
        val existingKeys = candidateDirectories
            .filter { it.exists() && it.isDirectory }
            .associateBy { it.absolutePath.lowercase() }

        if (forceRebind && watchers.isNotEmpty()) {
            watchers.values.forEach(ScreenshotDirectoryWatcher::stop)
            watchers.clear()
            logger.d(TAG, "目录监听已强制重绑 force=true")
        }

        watchers.keys.toList().forEach { key ->
            if (key !in existingKeys) {
                watchers.remove(key)?.stop()
                logger.d(TAG, "目录监听已移除 path=$key")
            }
        }

        existingKeys.values.forEach { directory ->
            val key = directory.absolutePath.lowercase()
            if (key in watchers) {
                return@forEach
            }

            val watcher = ScreenshotDirectoryWatcher(
                directory = directory,
                logger = logger,
                onEvent = onEvent,
            )
            watcher.start()
            watchers[key] = watcher
            logger.d(TAG, "目录监听已注册 path=${directory.absolutePath}")
        }

        return watchers.keys.map(::File)
    }

    fun stopAll() {
        watchers.values.forEach(ScreenshotDirectoryWatcher::stop)
        watchers.clear()
    }

    fun activeDirectories(): List<String> = watchers.keys.toList()

    private companion object {
        const val TAG = "MultiDirObserver"
    }
}
