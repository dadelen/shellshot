package com.example.shellshot.template

data class TemplateCalibrationDraft(
    val sourceImagePath: String,
    val templateName: String,
    val outputWidth: Int,
    val outputHeight: Int,
    val captureProfile: DeviceCaptureProfile,
    val detectedScreenRect: ScreenRect,
    val detectionSummary: String,
    val overlayCenterX: Float,
    val overlayCenterY: Float,
    val overlayWidth: Float,
    val overlayHeight: Float,
    val overlayCornerRadius: Float,
    val showGuides: Boolean = true,
    val defaultOverlayCenterX: Float = overlayCenterX,
    val defaultOverlayCenterY: Float = overlayCenterY,
    val defaultOverlayWidth: Float = overlayWidth,
    val defaultOverlayHeight: Float = overlayHeight,
    val defaultOverlayCornerRadius: Float = overlayCornerRadius,
) {
    val overlayAspectRatio: Float
        get() = captureProfile.captureAspectRatio

    val finalScreenRect: ScreenRect
        get() {
            val halfWidth = overlayWidth / 2f
            val halfHeight = overlayHeight / 2f
            return ScreenRect(
                left = kotlin.math.floor(overlayCenterX - halfWidth).toInt(),
                top = kotlin.math.floor(overlayCenterY - halfHeight).toInt(),
                right = kotlin.math.ceil(overlayCenterX + halfWidth).toInt(),
                bottom = kotlin.math.ceil(overlayCenterY + halfHeight).toInt(),
            ).normalizedWithin(outputWidth, outputHeight)
        }

    val finalContentClipRect: ScreenRect
        get() = finalScreenRect.insetForContentClip(outputWidth, outputHeight)
}

data class TemplateCalibrationResult(
    val success: Boolean,
    val templateId: String? = null,
    val message: String,
)

internal fun ScreenRect.normalizedWithin(width: Int, height: Int): ScreenRect {
    val left = left.coerceAtLeast(0).coerceAtMost(width - 2)
    val top = top.coerceAtLeast(0).coerceAtMost(height - 2)
    val right = right.coerceAtLeast(left + 1).coerceAtMost(width)
    val bottom = bottom.coerceAtLeast(top + 1).coerceAtMost(height)
    return ScreenRect(left = left, top = top, right = right, bottom = bottom)
}

internal fun ScreenRect.insetForContentClip(width: Int, height: Int): ScreenRect {
    return ScreenRect(
        left = left + 1,
        top = top + 1,
        right = right - 2,
        bottom = bottom - 3,
    ).normalizedWithin(width, height)
}
