package com.example.shellshot.template

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import kotlin.math.roundToInt

class TemplatePreviewRenderer {
    fun renderPreview(frame: Bitmap, geometry: CalibratedTemplateGeometry): Bitmap {
        val preview = frame.copy(Bitmap.Config.ARGB_8888, true)
        val canvas = Canvas(preview)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = 2f
            color = Color.argb(150, 70, 140, 255)
        }
        canvas.drawRect(
            geometry.visibleBounds.left.toFloat(),
            geometry.visibleBounds.top.toFloat(),
            geometry.visibleBounds.right.toFloat(),
            geometry.visibleBounds.bottom.toFloat(),
            paint,
        )
        geometry.templateTopFeature?.let { feature ->
            paint.color = Color.argb(170, 255, 214, 64)
            canvas.drawOval(
                feature.centerX - feature.width / 2f,
                feature.centerY - feature.height / 2f,
                feature.centerX + feature.width / 2f,
                feature.centerY + feature.height / 2f,
                paint,
            )
        }
        return preview
    }

    fun renderAdjustedMask(
        baseMask: Bitmap,
        baseVisibleBounds: ScreenRect,
        geometry: CalibratedTemplateGeometry,
    ): Bitmap {
        val width = baseMask.width
        val height = baseMask.height
        val sourcePixels = IntArray(width * height)
        baseMask.getPixels(sourcePixels, 0, width, 0, 0, width, height)
        val outputPixels = IntArray(width * height) { Color.TRANSPARENT }

        val scaleX = geometry.screenRect.width.toFloat() / baseVisibleBounds.width.coerceAtLeast(1)
        val scaleY = geometry.screenRect.height.toFloat() / baseVisibleBounds.height.coerceAtLeast(1)
        for (y in geometry.screenRect.top until geometry.screenRect.bottom) {
            for (x in geometry.screenRect.left until geometry.screenRect.right) {
                val sourceX = (baseVisibleBounds.left + (x - geometry.screenRect.left) / scaleX).roundToInt()
                val sourceY = (baseVisibleBounds.top + (y - geometry.screenRect.top) / scaleY).roundToInt()
                if (sourceX !in 0 until width || sourceY !in 0 until height) continue
                val source = sourcePixels[sourceY * width + sourceX]
                val alpha = Color.alpha(source).coerceAtLeast(Color.red(source))
                if (alpha >= MASK_TIGHTEN_THRESHOLD) {
                    outputPixels[y * width + x] = Color.WHITE
                }
            }
        }

        val tightened = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(outputPixels, 0, width, 0, 0, width, height)
        }
        return expandMaskInsideRect(tightened, geometry.screenRect, radius = 1)
    }

    private fun expandMaskInsideRect(source: Bitmap, bounds: ScreenRect, radius: Int): Bitmap {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        val output = IntArray(width * height) { Color.TRANSPARENT }
        source.getPixels(pixels, 0, width, 0, 0, width, height)
        for (y in bounds.top until bounds.bottom) {
            for (x in bounds.left until bounds.right) {
                var visible = false
                for (yy in (y - radius).coerceAtLeast(bounds.top)..(y + radius).coerceAtMost(bounds.bottom - 1)) {
                    for (xx in (x - radius).coerceAtLeast(bounds.left)..(x + radius).coerceAtMost(bounds.right - 1)) {
                        if (Color.alpha(pixels[yy * width + xx]) >= MASK_TIGHTEN_THRESHOLD) {
                            visible = true
                        }
                    }
                }
                if (visible) output[y * width + x] = Color.WHITE
            }
        }
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(output, 0, width, 0, 0, width, height)
        }
    }

    private companion object {
        const val MASK_TIGHTEN_THRESHOLD = 160
    }
}
