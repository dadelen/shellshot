package com.example.shellshot.template

import android.graphics.Bitmap
import android.graphics.Color
import kotlin.math.abs

object CalibrationSnapEngine {
    private const val DEFAULT_SEARCH_RADIUS = 16
    private const val MIN_EDGE_SCORE = 32f

    fun snap(bitmap: Bitmap, x: Float, y: Float, radius: Int = DEFAULT_SEARCH_RADIUS): CalibrationSnapResult {
        val width = bitmap.width
        val height = bitmap.height
        val clampedX = x.coerceIn(0f, (width - 1).toFloat())
        val clampedY = y.coerceIn(0f, (height - 1).toFloat())
        val pixels = IntArray(width * height)
        bitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        var bestX = clampedX
        var bestY = clampedY
        var bestScore = edgeScore(pixels, width, height, clampedX.toInt(), clampedY.toInt())

        val startX = (clampedX.toInt() - radius).coerceAtLeast(1)
        val endX = (clampedX.toInt() + radius).coerceAtMost(width - 2)
        val startY = (clampedY.toInt() - radius).coerceAtLeast(1)
        val endY = (clampedY.toInt() + radius).coerceAtMost(height - 2)
        for (py in startY..endY) {
            for (px in startX..endX) {
                val score = edgeScore(pixels, width, height, px, py) - (abs(px - clampedX) + abs(py - clampedY)) * 0.45f
                if (score > bestScore) {
                    bestScore = score
                    bestX = px.toFloat()
                    bestY = py.toFloat()
                }
            }
        }

        return CalibrationSnapResult(
            x = bestX,
            y = bestY,
            snapped = bestScore >= MIN_EDGE_SCORE && (bestX != clampedX || bestY != clampedY),
            score = bestScore,
        )
    }

    private fun edgeScore(pixels: IntArray, width: Int, height: Int, x: Int, y: Int): Float {
        val center = alphaAt(pixels, width, height, x, y)
        val left = alphaAt(pixels, width, height, x - 1, y)
        val right = alphaAt(pixels, width, height, x + 1, y)
        val top = alphaAt(pixels, width, height, x, y - 1)
        val bottom = alphaAt(pixels, width, height, x, y + 1)
        val horizontal = abs(right - left)
        val vertical = abs(bottom - top)
        val contrastBias = maxOf(abs(center - left), abs(center - right), abs(center - top), abs(center - bottom))
        return horizontal + vertical + contrastBias * 0.5f
    }

    private fun alphaAt(pixels: IntArray, width: Int, height: Int, x: Int, y: Int): Float {
        val safeX = x.coerceIn(0, width - 1)
        val safeY = y.coerceIn(0, height - 1)
        return Color.alpha(pixels[safeY * width + safeX]).toFloat()
    }
}
