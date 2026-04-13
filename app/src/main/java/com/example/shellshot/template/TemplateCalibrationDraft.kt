package com.example.shellshot.template

data class TemplateCalibrationDraft(
    val sourceImagePath: String,
    val templateName: String,
    val outputWidth: Int,
    val outputHeight: Int,
    val previewScreenshotWidth: Int,
    val previewScreenshotHeight: Int,
    val detectedScreenRect: ScreenRect,
    val detectionSummary: String,
    val leftInset: Int = 0,
    val topInset: Int = 0,
    val rightInset: Int = 0,
    val bottomInset: Int = 0,
    val shrinkPixels: Int = 1,
) {
    val finalScreenRect: ScreenRect
        get() = ScreenRect(
            left = detectedScreenRect.left + leftInset + shrinkPixels,
            top = detectedScreenRect.top + topInset + shrinkPixels,
            right = detectedScreenRect.right - rightInset - shrinkPixels,
            bottom = detectedScreenRect.bottom - bottomInset - shrinkPixels,
        )
}

data class TemplateCalibrationResult(
    val success: Boolean,
    val templateId: String? = null,
    val message: String,
)
