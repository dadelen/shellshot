package com.example.shellshot.media

import android.content.ContentResolver
import android.content.Context
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import com.example.shellshot.data.OutputNamingStrategy
import com.example.shellshot.utils.ShellLogger
import java.io.File
import java.io.FileOutputStream
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume

data class OutputWriteResult(
    val absolutePath: String,
    val scannedUri: Uri?,
)

class OutputRepository(
    private val context: Context,
    private val contentResolver: ContentResolver,
    private val logger: ShellLogger,
) {
    suspend fun save(
        bitmap: Bitmap,
        sourceCandidate: ScreenshotCandidate,
        namingStrategy: OutputNamingStrategy,
    ): OutputWriteResult = withContext(Dispatchers.IO) {
        val outputDirectory = ScreenshotDirectories.outputDirectory()
        if (!outputDirectory.exists() && !outputDirectory.mkdirs()) {
            error("Unable to create output directory: ${outputDirectory.absolutePath}")
        }

        val outputFile = buildUniqueOutputFile(
            parentDirectory = outputDirectory,
            sourceDisplayName = sourceCandidate.displayName,
            namingStrategy = namingStrategy,
        )

        FileOutputStream(outputFile).use { outputStream ->
            check(bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)) {
                "Bitmap compression failed for ${outputFile.absolutePath}"
            }
        }

        val scannedUri = scanFile(outputFile)
        logger.d(
            TAG,
            "成品已保存 source=${sourceCandidate.absolutePath} output=${outputFile.absolutePath} relativePath=${ScreenshotDirectories.OUTPUT_RELATIVE_PATH}",
        )
        OutputWriteResult(
            absolutePath = outputFile.absolutePath,
            scannedUri = scannedUri,
        )
    }

    suspend fun deleteOriginal(candidate: ScreenshotCandidate): DeleteResult = withContext(Dispatchers.IO) {
        val sourceFile = File(candidate.absolutePath)

        runCatching {
            if (sourceFile.exists() && sourceFile.delete()) {
                scanFile(sourceFile)
                return@withContext DeleteResult(
                    success = true,
                    message = "原图已通过文件系统删除",
                )
            }
        }.onFailure { throwable ->
            logger.e(TAG, "文件系统删除原图失败 path=${candidate.absolutePath}", throwable)
        }

        candidate.uri?.let { uri ->
            val deletedRows = runCatching {
                contentResolver.delete(uri, null, null)
            }.onFailure { throwable ->
                logger.e(TAG, "MediaStore 删除原图失败 uri=$uri", throwable)
            }.getOrDefault(0)

            if (deletedRows > 0) {
                return@withContext DeleteResult(
                    success = true,
                    message = "原图已通过 MediaStore 删除",
                )
            }
        }

        DeleteResult(
            success = false,
            message = "原图未删除",
        )
    }

    private fun buildUniqueOutputFile(
        parentDirectory: File,
        sourceDisplayName: String,
        namingStrategy: OutputNamingStrategy,
    ): File {
        val desiredName = ScreenshotDirectories.buildOutputFileName(sourceDisplayName, namingStrategy)
        val firstCandidate = File(parentDirectory, desiredName)
        if (!firstCandidate.exists()) {
            return firstCandidate
        }

        val baseName = desiredName.substringBeforeLast('.')
        val extension = desiredName.substringAfterLast('.', "png")
        var index = 2
        while (true) {
            val candidate = File(parentDirectory, "${baseName}_$index.$extension")
            if (!candidate.exists()) {
                return candidate
            }
            index += 1
        }
    }

    private suspend fun scanFile(file: File): Uri? = suspendCancellableCoroutine { continuation ->
        MediaScannerConnection.scanFile(
            context,
            arrayOf(file.absolutePath),
            null,
        ) { _, uri ->
            if (continuation.isActive) {
                continuation.resume(uri)
            }
        }
    }

    private companion object {
        const val TAG = "OutputRepository"
    }
}
