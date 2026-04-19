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
        const val DEBUG_MIN_INTERVAL_MS = 2_000L
        const val SAME_DEBUG_SUPPRESS_WINDOW_MS = 8_000L
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
            "开始解码",
            "解码成功",
            "解码目标命中",
            "解码目标失败",
            "开始合成",
            "合成成功 path=",
            "开始保存",
            "保存成功 output=",
            "任务已就绪",
            "复用已确认候选",
            "已确认候选失效",
            "候选任务入队",
            "跳过入队",
            "媒体库兜底未发现截图",
            "watchdog 探测跳过",
            "配置路径候选不可用",
            "稳定性检查已暂停",
            "文件仍未稳定",
            "合成指标",
            "compose debug",
        )

        val importantDebugFragments = listOf(
            "套壳成功",
            "合成后验警告",
            "合成失败",
            "保存失败",
            "删除原图失败",
            "任务终态失败",
            "任务待重试",
            "任务跳过",
            "模板导入",
            "模板预处理",
            "已删除用户模板",
            "保存",
            "启动监听",
            "停止监听",
            "监听服务",
            "权限",
        )
    }
}
