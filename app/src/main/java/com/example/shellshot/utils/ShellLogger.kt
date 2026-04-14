package com.example.shellshot.utils

import android.util.Log
import com.example.shellshot.data.LogLevel
import com.example.shellshot.data.LogRepository
import com.example.shellshot.data.LogEntry

class ShellLogger(
    private val logRepository: LogRepository,
) {
    private val lock = Any()
    private val recentDebugAtByKey = LinkedHashMap<String, Long>()
    private var lastPersistedDebugAt: Long = 0L

    fun d(tag: String, message: String) {
        Log.d(tag, message)
        val now = System.currentTimeMillis()
        if (!shouldPersistDebug(tag = tag, message = message, now = now)) {
            return
        }
        logRepository.append(
            LogEntry(
                timestampMillis = now,
                level = LogLevel.Debug,
                tag = tag,
                message = message,
            ),
        )
    }

    fun e(tag: String, message: String, throwable: Throwable? = null) {
        Log.e(tag, message, throwable)
        val renderedMessage = buildString {
            append(message)
            if (throwable != null) {
                append(" | ")
                append(throwable.javaClass.simpleName)
                if (!throwable.message.isNullOrBlank()) {
                    append(": ")
                    append(throwable.message)
                }
            }
        }

        logRepository.append(
            LogEntry(
                timestampMillis = System.currentTimeMillis(),
                level = LogLevel.Error,
                tag = tag,
                message = renderedMessage,
            ),
        )
    }

    private fun shouldPersistDebug(tag: String, message: String, now: Long): Boolean = synchronized(lock) {
        val normalized = message.trim()
        if (normalized.isBlank() || isNoisyDebug(tag, normalized)) {
            return@synchronized false
        }

        val key = "${tag.substringAfterLast('.')}:${normalized.take(120)}"
        val lastSameAt = recentDebugAtByKey[key]
        if (lastSameAt != null && now - lastSameAt < SAME_DEBUG_SUPPRESS_WINDOW_MS) {
            return@synchronized false
        }

        val important = isImportantDebug(normalized)
        if (!important && now - lastPersistedDebugAt < DEBUG_MIN_INTERVAL_MS) {
            return@synchronized false
        }

        recentDebugAtByKey[key] = now
        while (recentDebugAtByKey.size > RECENT_DEBUG_KEY_LIMIT) {
            val first = recentDebugAtByKey.keys.firstOrNull() ?: break
            recentDebugAtByKey.remove(first)
        }
        lastPersistedDebugAt = now
        true
    }

    private fun isNoisyDebug(tag: String, message: String): Boolean {
        val normalizedTag = tag.substringAfterLast('.')
        return noisyDebugFragments.any { fragment -> message.contains(fragment, ignoreCase = true) } ||
            noisyDebugTags.any { noisyTag -> normalizedTag.equals(noisyTag, ignoreCase = true) }
    }

    private fun isImportantDebug(message: String): Boolean {
        return importantDebugFragments.any { fragment -> message.contains(fragment, ignoreCase = true) }
    }

    private companion object {
        const val DEBUG_MIN_INTERVAL_MS = 650L
        const val SAME_DEBUG_SUPPRESS_WINDOW_MS = 4_000L
        const val RECENT_DEBUG_KEY_LIMIT = 64

        val noisyDebugTags = setOf(
            "ScreenState",
        )

        val noisyDebugFragments = listOf(
            "cleanup:onDestroy",
            "Idle polling cancelled",
            "Screen state listener stopped",
            "屏幕状态监听已停止",
            "监听链已挂起 reason=cleanup:onDestroy",
            "服务已销毁 explicitStop=true reason=onDestroy",
            "Skip self restart",
        )

        val importantDebugFragments = listOf(
            "截图",
            "模板",
            "保存",
            "完成",
            "启动",
            "停止",
            "监听",
            "权限",
            "screenshot",
            "template",
            "saved",
            "started",
            "stopped",
        )
    }
}
