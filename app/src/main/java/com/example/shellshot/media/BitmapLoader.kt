package com.example.shellshot.media

import android.content.ContentResolver
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import com.example.shellshot.utils.ShellLogger
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.max

class BitmapLoader(
    private val contentResolver: ContentResolver,
    private val logger: ShellLogger,
) {
    suspend fun loadBitmap(
        uri: Uri,
        targetWidth: Int,
        targetHeight: Int,
    ): Bitmap = withContext(Dispatchers.IO) {
        decodeBitmap(
            source = ImageDecoder.createSource(contentResolver, uri),
            sourceDescription = uri.toString(),
            targetWidth = targetWidth,
            targetHeight = targetHeight,
        )
    }

    suspend fun loadBitmap(
        absolutePath: String,
        targetWidth: Int,
        targetHeight: Int,
    ): Bitmap = withContext(Dispatchers.IO) {
        decodeBitmap(
            source = ImageDecoder.createSource(File(absolutePath)),
            sourceDescription = absolutePath,
            targetWidth = targetWidth,
            targetHeight = targetHeight,
        )
    }

    private fun decodeBitmap(
        source: ImageDecoder.Source,
        sourceDescription: String,
        targetWidth: Int,
        targetHeight: Int,
    ): Bitmap {
        val safeTargetWidth = max(1, targetWidth)
        val safeTargetHeight = max(1, targetHeight)

        return try {
            ImageDecoder.decodeBitmap(source) { decoder, imageInfo, _ ->
                val sampleSize = computeSampleSize(
                    sourceWidth = imageInfo.size.width,
                    sourceHeight = imageInfo.size.height,
                    targetWidth = safeTargetWidth,
                    targetHeight = safeTargetHeight,
                )
                decoder.allocator = ImageDecoder.ALLOCATOR_SOFTWARE
                decoder.isMutableRequired = false
                decoder.setTargetSampleSize(sampleSize)
            }.also { bitmap ->
                logger.d(
                    TAG,
                    "解码成功 source=$sourceDescription size=${bitmap.width}x${bitmap.height}",
                )
            }
        } catch (throwable: Throwable) {
            logger.e(TAG, "解码失败 source=$sourceDescription", throwable)
            throw throwable
        }
    }

    private fun computeSampleSize(
        sourceWidth: Int,
        sourceHeight: Int,
        targetWidth: Int,
        targetHeight: Int,
    ): Int {
        var sampleSize = 1
        var currentWidth = sourceWidth
        var currentHeight = sourceHeight

        while (currentWidth / 2 >= targetWidth && currentHeight / 2 >= targetHeight) {
            currentWidth /= 2
            currentHeight /= 2
            sampleSize *= 2
        }

        return max(1, sampleSize)
    }

    private companion object {
        const val TAG = "BitmapLoader"
    }
}
