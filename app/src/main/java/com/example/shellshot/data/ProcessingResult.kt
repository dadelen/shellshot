package com.example.shellshot.data

enum class ProcessingStatus {
    Success,
    Failed,
    Skipped,
}

data class ProcessingResult(
    val processedAtMillis: Long,
    val sourcePath: String,
    val outputPath: String?,
    val templateId: String,
    val triggerSource: String?,
    val deletedOriginal: Boolean,
    val deleteMessage: String?,
    val status: ProcessingStatus,
    val detailMessage: String?,
)
