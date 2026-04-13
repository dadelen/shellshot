package com.example.shellshot.data

import android.content.Context
import java.io.File
import java.nio.charset.StandardCharsets
import java.util.Base64

class LogRepository(
    context: Context,
    private val appStateStore: AppStateStore,
) {
    private val logFile = File(context.filesDir, "shellshot_logs.txt")
    private val lock = Any()

    init {
        hydrateRecentLogs()
    }

    fun append(entry: LogEntry) {
        synchronized(lock) {
            appStateStore.pushLog(entry)
            logFile.parentFile?.mkdirs()
            logFile.appendText(serialize(entry) + "\n")
            trimLogFileIfNeeded()
        }
    }

    private fun hydrateRecentLogs() {
        if (!logFile.exists()) {
            return
        }

        synchronized(lock) {
            logFile.readLines()
                .takeLast(MAX_LOG_COUNT)
                .mapNotNull(::deserialize)
                .forEach(appStateStore::pushLog)
        }
    }

    private fun trimLogFileIfNeeded() {
        val lines = logFile.readLines()
        if (lines.size <= MAX_PERSISTED_COUNT) {
            return
        }
        logFile.writeText(lines.takeLast(MAX_PERSISTED_COUNT).joinToString(separator = "\n", postfix = "\n"))
    }

    private fun serialize(entry: LogEntry): String {
        return listOf(
            entry.timestampMillis.toString(),
            entry.level.name,
            encode(entry.tag),
            encode(entry.message),
        ).joinToString("|")
    }

    private fun deserialize(raw: String): LogEntry? {
        val parts = raw.split("|")
        if (parts.size < 4) {
            return null
        }

        return runCatching {
            LogEntry(
                timestampMillis = parts[0].toLong(),
                level = LogLevel.valueOf(parts[1]),
                tag = decode(parts[2]),
                message = decode(parts[3]),
            )
        }.getOrNull()
    }

    private fun encode(value: String): String {
        return Base64.getEncoder().encodeToString(value.toByteArray(StandardCharsets.UTF_8))
    }

    private fun decode(value: String): String {
        return String(Base64.getDecoder().decode(value), StandardCharsets.UTF_8)
    }

    private companion object {
        const val MAX_LOG_COUNT = 100
        const val MAX_PERSISTED_COUNT = 400
    }
}
