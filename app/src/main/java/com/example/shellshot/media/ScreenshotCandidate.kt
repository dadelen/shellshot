package com.example.shellshot.media

import android.net.Uri
import java.io.File

data class ScreenshotCandidate(
    val absolutePath: String,
    val displayName: String,
    val relativePath: String?,
    val lastModifiedMillis: Long,
    val sizeBytes: Long,
    val mimeType: String?,
    val width: Int? = null,
    val height: Int? = null,
    val uri: Uri? = null,
    val dateAddedSec: Long? = null,
    val dateTakenMs: Long? = null,
    val isPending: Boolean = false,
) {
    val parentDirectory: String
        get() = File(absolutePath).parent.orEmpty()

    val bucketName: String
        get() = File(parentDirectory).name

    val capturedAtMillis: Long
        get() = dateTakenMs?.takeIf { it > 0L } ?: lastModifiedMillis

    val dedupeKey: String
        get() = buildDedupeKey(
            absolutePath = absolutePath,
            displayName = displayName,
            sizeBytes = sizeBytes,
            lastModifiedMillis = lastModifiedMillis,
        )

    val ingressSignature: String
        get() = buildIngressSignature(
            displayName = displayName,
            relativePath = relativePath,
            sizeBytes = sizeBytes,
            lastModifiedMillis = lastModifiedMillis,
        )

    val finalCandidateSignature: String
        get() = buildFinalCandidateSignature(
            displayName = displayName,
            relativePath = relativePath,
            sizeBytes = sizeBytes,
            lastModifiedMillis = lastModifiedMillis,
            width = width,
            height = height,
        )

    companion object {
        fun buildProcessedPathKey(absolutePath: String): String? {
            val normalizedPath = ScreenshotDirectories.normalizeAbsolutePath(absolutePath) ?: return null
            return "path:$normalizedPath"
        }

        fun buildDedupeKey(
            absolutePath: String,
            displayName: String,
            sizeBytes: Long,
            lastModifiedMillis: Long,
        ): String = buildString {
            append(absolutePath.lowercase())
            append('|')
            append(displayName.lowercase())
            append('|')
            append(sizeBytes)
            append('|')
            append(lastModifiedMillis)
        }

        fun buildIngressSignature(
            displayName: String,
            relativePath: String?,
            sizeBytes: Long,
            lastModifiedMillis: Long,
        ): String = buildString {
            append(displayName.lowercase())
            append('|')
            append(relativePath.orEmpty().lowercase())
            append('|')
            append(sizeBytes)
            append('|')
            append(lastModifiedMillis)
        }

        fun buildFinalCandidateSignature(
            displayName: String,
            relativePath: String?,
            sizeBytes: Long,
            lastModifiedMillis: Long,
            width: Int?,
            height: Int?,
        ): String = buildString {
            append(displayName.lowercase())
            append('|')
            append(relativePath.orEmpty().lowercase())
            append('|')
            append(sizeBytes)
            append('|')
            append(lastModifiedMillis)
            append('|')
            append(width ?: 0)
            append('x')
            append(height ?: 0)
        }
    }
}
