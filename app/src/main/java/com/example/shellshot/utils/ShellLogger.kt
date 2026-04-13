package com.example.shellshot.utils

import android.util.Log
import com.example.shellshot.data.LogLevel
import com.example.shellshot.data.LogRepository
import com.example.shellshot.data.LogEntry

class ShellLogger(
    private val logRepository: LogRepository,
) {
    fun d(tag: String, message: String) {
        Log.d(tag, message)
        logRepository.append(
            LogEntry(
                timestampMillis = System.currentTimeMillis(),
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
}
