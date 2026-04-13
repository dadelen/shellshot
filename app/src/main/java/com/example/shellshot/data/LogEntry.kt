package com.example.shellshot.data

enum class LogLevel {
    Debug,
    Error,
}

data class LogEntry(
    val timestampMillis: Long,
    val level: LogLevel,
    val tag: String,
    val message: String,
)

