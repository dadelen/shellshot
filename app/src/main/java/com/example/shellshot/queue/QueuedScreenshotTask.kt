package com.example.shellshot.queue

import kotlinx.serialization.Serializable

@Serializable
enum class ScreenshotTaskStatus {
    DETECTED,
    WAIT_STABLE,
    READY,
    COMPOSING,
    SAVED,
    DELETE_DONE,
    FAILED_RETRYABLE,
    FAILED_FINAL,
}

@Serializable
data class PreparedScreenshotCandidate(
    val absolutePath: String,
    val displayName: String,
    val relativePath: String?,
    val uri: String? = null,
    val lastModifiedMillis: Long,
    val sizeBytes: Long,
    val mimeType: String? = null,
    val width: Int? = null,
    val height: Int? = null,
    val ingressSignature: String = "",
    val finalCandidateSignature: String = "",
    val resolutionSource: String,
    val resolutionReason: String,
)

@Serializable
data class QueuedScreenshotTask(
    val id: String,
    val absolutePath: String,
    val displayName: String,
    val relativePath: String?,
    val changedUri: String? = null,
    val source: String,
    val ingressSignature: String? = null,
    val finalCandidateSignature: String? = null,
    val status: ScreenshotTaskStatus,
    val detectedAtMillis: Long,
    val updatedAtMillis: Long,
    val nextAttemptAtMillis: Long,
    val retryCount: Int = 0,
    val lastError: String? = null,
    val preparedCandidate: PreparedScreenshotCandidate? = null,
    val outputPath: String? = null,
    val deleteAttempted: Boolean = false,
    val deleteSucceeded: Boolean = false,
    val deleteMessage: String? = null,
)

@Serializable
data class ScreenshotTaskSnapshot(
    val tasks: List<QueuedScreenshotTask> = emptyList(),
)

data class EnqueueTaskResult(
    val accepted: Boolean,
    val reason: String,
    val task: QueuedScreenshotTask? = null,
)
