package com.example.shellshot.template

import android.content.Context
import android.os.SystemClock
import android.view.Surface
import android.view.WindowManager

class DeviceCaptureProfileProvider(
    private val context: Context,
) {
    fun readProfile(): DeviceCaptureProfile {
        val windowManager = context.getSystemService(WindowManager::class.java)
        val bounds = windowManager.maximumWindowMetrics.bounds
        val densityDpi = context.resources.configuration.densityDpi
        val mode = runCatching { context.display?.mode }.getOrNull()
            ?: runCatching { @Suppress("DEPRECATION") windowManager.defaultDisplay.mode }.getOrNull()
        val rotation = runCatching { context.display?.rotation }.getOrNull()
            ?: runCatching { @Suppress("DEPRECATION") windowManager.defaultDisplay.rotation }.getOrDefault(Surface.ROTATION_0)

        val captureWidth = bounds.width().coerceAtLeast(1)
        val captureHeight = bounds.height().coerceAtLeast(1)
        return DeviceCaptureProfile(
            captureWidth = captureWidth,
            captureHeight = captureHeight,
            captureAspectRatio = captureWidth / captureHeight.toFloat(),
            densityDpi = densityDpi,
            physicalModeWidth = mode?.physicalWidth,
            physicalModeHeight = mode?.physicalHeight,
            rotationAtCalibration = rotation,
            updatedAt = System.currentTimeMillis().takeIf { it > 0 } ?: SystemClock.elapsedRealtime(),
        )
    }
}
