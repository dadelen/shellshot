package com.example.shellshot.template

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Shader
import kotlin.math.roundToInt

class TemplatePreviewRenderer {
    fun renderPreview(frame: Bitmap, geometry: CalibratedTemplateGeometry): Bitmap {
        val preview = Bitmap.createBitmap(frame.width, frame.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(preview)
        val screenBounds = geometry.contentClipRect.takeIf { it.width > 0 && it.height > 0 } ?: geometry.screenRect

        val clipPath = if (geometry.calibrationCorners.size == 4) {
            buildCornerPath(geometry.calibrationCorners)
        } else {
            Path().apply {
                addRoundRect(
                    RectF(
                        screenBounds.left.toFloat(),
                        screenBounds.top.toFloat(),
                        screenBounds.right.toFloat(),
                        screenBounds.bottom.toFloat(),
                    ),
                    (screenBounds.width * 0.08f).coerceAtLeast(18f),
                    (screenBounds.width * 0.08f).coerceAtLeast(18f),
                    Path.Direction.CW,
                )
            }
        }

        canvas.save()
        canvas.clipPath(clipPath)
        drawDemoScreen(canvas, screenBounds)
        canvas.restore()
        canvas.drawBitmap(frame, 0f, 0f, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            isFilterBitmap = true
            isDither = true
        })
        return preview
    }

    private fun drawDemoScreen(canvas: Canvas, bounds: ScreenRect) {
        val rect = RectF(
            bounds.left.toFloat(),
            bounds.top.toFloat(),
            bounds.right.toFloat(),
            bounds.bottom.toFloat(),
        )
        val width = rect.width()
        val height = rect.height()
        if (width <= 1f || height <= 1f) return

        canvas.drawRect(
            rect,
            Paint(Paint.ANTI_ALIAS_FLAG).apply {
                shader = LinearGradient(
                    rect.left,
                    rect.top,
                    rect.right,
                    rect.bottom,
                    intArrayOf(
                        Color.parseColor("#0B1020"),
                        Color.parseColor("#16213E"),
                        Color.parseColor("#0F3460"),
                    ),
                    null,
                    Shader.TileMode.CLAMP,
                )
            },
        )

        val padX = width * 0.06f
        val padY = height * 0.06f
        val chipHeight = height * 0.075f
        val chipRadius = chipHeight * 0.45f
        val cardRadius = width * 0.05f

        val topChip = RectF(
            rect.left + padX,
            rect.top + padY,
            rect.left + padX + width * 0.42f,
            rect.top + padY + chipHeight,
        )
        canvas.drawRoundRect(topChip, chipRadius, chipRadius, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(235, 255, 255, 255)
        })

        val smallChip = RectF(
            rect.right - padX - width * 0.16f,
            rect.top + padY,
            rect.right - padX,
            rect.top + padY + chipHeight,
        )
        canvas.drawRoundRect(smallChip, chipRadius, chipRadius, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(110, 255, 255, 255)
        })

        val heroCard = RectF(
            rect.left + padX,
            rect.top + padY + chipHeight + height * 0.045f,
            rect.right - padX,
            rect.top + padY + chipHeight + height * 0.33f,
        )
        canvas.drawRoundRect(heroCard, cardRadius, cardRadius, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            shader = LinearGradient(
                heroCard.left,
                heroCard.top,
                heroCard.right,
                heroCard.bottom,
                intArrayOf(
                    Color.parseColor("#78E3FF"),
                    Color.parseColor("#8F7CFF"),
                ),
                null,
                Shader.TileMode.CLAMP,
            )
        })

        val linePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(180, 255, 255, 255)
            strokeCap = Paint.Cap.ROUND
            strokeWidth = (height * 0.012f).coerceAtLeast(4f)
        }
        canvas.drawLine(
            heroCard.left + width * 0.08f,
            heroCard.top + heroCard.height() * 0.34f,
            heroCard.left + width * 0.46f,
            heroCard.top + heroCard.height() * 0.34f,
            linePaint,
        )
        linePaint.alpha = 120
        canvas.drawLine(
            heroCard.left + width * 0.08f,
            heroCard.top + heroCard.height() * 0.53f,
            heroCard.left + width * 0.56f,
            heroCard.top + heroCard.height() * 0.53f,
            linePaint,
        )

        val metricsTop = heroCard.bottom + height * 0.05f
        val metricWidth = (width - padX * 2f - width * 0.04f) / 2f
        repeat(2) { index ->
            val left = rect.left + padX + index * (metricWidth + width * 0.04f)
            val metricCard = RectF(
                left,
                metricsTop,
                left + metricWidth,
                metricsTop + height * 0.12f,
            )
            canvas.drawRoundRect(metricCard, cardRadius, cardRadius, Paint(Paint.ANTI_ALIAS_FLAG).apply {
                color = Color.argb(58, 255, 255, 255)
            })
        }

        val chartTop = metricsTop + height * 0.16f
        val chartBottom = rect.bottom - padY
        val chartLeft = rect.left + padX
        val chartRight = rect.right - padX
        val chartRect = RectF(chartLeft, chartTop, chartRight, chartBottom)
        canvas.drawRoundRect(chartRect, cardRadius, cardRadius, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(42, 255, 255, 255)
        })

        val chartPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            style = Paint.Style.STROKE
            strokeWidth = (height * 0.01f).coerceAtLeast(3f)
            strokeCap = Paint.Cap.ROUND
            strokeJoin = Paint.Join.ROUND
            shader = LinearGradient(
                chartRect.left,
                chartRect.top,
                chartRect.right,
                chartRect.bottom,
                intArrayOf(
                    Color.parseColor("#86F7C4"),
                    Color.parseColor("#5FD3FF"),
                ),
                null,
                Shader.TileMode.CLAMP,
            )
        }
        val chartPath = Path().apply {
            moveTo(chartRect.left + chartRect.width() * 0.08f, chartRect.bottom - chartRect.height() * 0.28f)
            cubicTo(
                chartRect.left + chartRect.width() * 0.24f,
                chartRect.bottom - chartRect.height() * 0.62f,
                chartRect.left + chartRect.width() * 0.42f,
                chartRect.bottom - chartRect.height() * 0.18f,
                chartRect.left + chartRect.width() * 0.58f,
                chartRect.bottom - chartRect.height() * 0.44f,
            )
            cubicTo(
                chartRect.left + chartRect.width() * 0.72f,
                chartRect.bottom - chartRect.height() * 0.68f,
                chartRect.left + chartRect.width() * 0.84f,
                chartRect.bottom - chartRect.height() * 0.36f,
                chartRect.left + chartRect.width() * 0.92f,
                chartRect.bottom - chartRect.height() * 0.2f,
            )
        }
        canvas.drawPath(chartPath, chartPaint)
    }

    fun renderAdjustedMask(
        baseMask: Bitmap,
        baseVisibleBounds: ScreenRect,
        geometry: CalibratedTemplateGeometry,
    ): Bitmap {
        if (geometry.calibrationCorners.size == 4) {
            return renderAdjustedMaskWithQuad(
                baseMask = baseMask,
                baseVisibleBounds = baseVisibleBounds,
                geometry = geometry,
            )
        }

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

    private fun renderAdjustedMaskWithQuad(
        baseMask: Bitmap,
        baseVisibleBounds: ScreenRect,
        geometry: CalibratedTemplateGeometry,
    ): Bitmap {
        val width = baseMask.width
        val height = baseMask.height
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val matrix = Matrix()
        val src = floatArrayOf(
            baseVisibleBounds.left.toFloat(), baseVisibleBounds.top.toFloat(),
            baseVisibleBounds.right.toFloat(), baseVisibleBounds.top.toFloat(),
            baseVisibleBounds.right.toFloat(), baseVisibleBounds.bottom.toFloat(),
            baseVisibleBounds.left.toFloat(), baseVisibleBounds.bottom.toFloat(),
        )
        val dst = geometry.calibrationCorners.toPointArray()
        matrix.setPolyToPoly(src, 0, dst, 0, 4)
        val clipPath = buildCornerPath(geometry.calibrationCorners)
        canvas.save()
        canvas.clipPath(clipPath)
        canvas.drawBitmap(baseMask, matrix, Paint(Paint.ANTI_ALIAS_FLAG).apply {
            isFilterBitmap = true
            isDither = true
        })
        canvas.restore()
        return expandMaskInsideRect(output, geometry.screenRect, radius = 1)
    }

    private fun buildCornerPath(corners: List<CalibrationCorner>): Path {
        val ordered = corners.associateBy { it.id }
        val topLeft = ordered[CalibrationCornerId.TOP_LEFT]
        val topRight = ordered[CalibrationCornerId.TOP_RIGHT]
        val bottomRight = ordered[CalibrationCornerId.BOTTOM_RIGHT]
        val bottomLeft = ordered[CalibrationCornerId.BOTTOM_LEFT]
        return Path().apply {
            if (topLeft != null && topRight != null && bottomRight != null && bottomLeft != null) {
                moveTo(topLeft.x, topLeft.y)
                lineTo(topRight.x, topRight.y)
                lineTo(bottomRight.x, bottomRight.y)
                lineTo(bottomLeft.x, bottomLeft.y)
                close()
            } else {
                addRect(
                    geometryRectFallback(corners),
                    Path.Direction.CW,
                )
            }
        }
    }

    private fun geometryRectFallback(corners: List<CalibrationCorner>): android.graphics.RectF {
        val xs = corners.map { it.x }
        val ys = corners.map { it.y }
        return android.graphics.RectF(
            xs.minOrNull() ?: 0f,
            ys.minOrNull() ?: 0f,
            xs.maxOrNull() ?: 1f,
            ys.maxOrNull() ?: 1f,
        )
    }

    private fun List<CalibrationCorner>.toPointArray(): FloatArray {
        val ordered = associateBy { it.id }
        return floatArrayOf(
            ordered[CalibrationCornerId.TOP_LEFT]?.x ?: 0f,
            ordered[CalibrationCornerId.TOP_LEFT]?.y ?: 0f,
            ordered[CalibrationCornerId.TOP_RIGHT]?.x ?: 0f,
            ordered[CalibrationCornerId.TOP_RIGHT]?.y ?: 0f,
            ordered[CalibrationCornerId.BOTTOM_RIGHT]?.x ?: 0f,
            ordered[CalibrationCornerId.BOTTOM_RIGHT]?.y ?: 0f,
            ordered[CalibrationCornerId.BOTTOM_LEFT]?.x ?: 0f,
            ordered[CalibrationCornerId.BOTTOM_LEFT]?.y ?: 0f,
        )
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
