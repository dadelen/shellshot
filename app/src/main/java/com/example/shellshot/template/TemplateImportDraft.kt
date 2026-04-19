package com.example.shellshot.template

data class TemplateImportDraft(
    val sourceImagePath: String,
    val templateName: String,
    val validationWarning: String? = null,
    val outputWidth: Int = 0,
    val outputHeight: Int = 0,
    val captureProfile: DeviceCaptureProfile = DeviceCaptureProfile(1, 1, 1f, 0, null, null, 0, 0L),
    val detectedScreenRect: ScreenRect = ScreenRect(0, 0, 1, 1),
    val detectionSummary: String = "",
    val overlayCenterX: Float = 0f,
    val overlayCenterY: Float = 0f,
    val overlayWidth: Float = 1f,
    val overlayHeight: Float = 1f,
    val overlayCornerRadius: Float = 0f,
    val showGuides: Boolean = true,
    val defaultOverlayCenterX: Float = overlayCenterX,
    val defaultOverlayCenterY: Float = overlayCenterY,
    val defaultOverlayWidth: Float = overlayWidth,
    val defaultOverlayHeight: Float = overlayHeight,
    val defaultOverlayCornerRadius: Float = overlayCornerRadius,
) {
    val overlayAspectRatio: Float
        get() = captureProfile.captureAspectRatio.takeIf { it > 0f } ?: 1f

    val finalScreenRect: ScreenRect
        get() {
            val halfWidth = overlayWidth / 2f
            val halfHeight = overlayHeight / 2f
            return ScreenRect(
                left = kotlin.math.floor(overlayCenterX - halfWidth).toInt(),
                top = kotlin.math.floor(overlayCenterY - halfHeight).toInt(),
                right = kotlin.math.ceil(overlayCenterX + halfWidth).toInt(),
                bottom = kotlin.math.ceil(overlayCenterY + halfHeight).toInt(),
            ).normalizedWithin(outputWidth.coerceAtLeast(2), outputHeight.coerceAtLeast(2))
        }

    val finalContentClipRect: ScreenRect
        get() = finalScreenRect.insetForContentClip(outputWidth.coerceAtLeast(2), outputHeight.coerceAtLeast(2))
}
