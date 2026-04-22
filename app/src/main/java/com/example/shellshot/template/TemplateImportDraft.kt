package com.example.shellshot.template

import kotlinx.serialization.Serializable

@Serializable
enum class CalibrationCornerId {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_RIGHT,
    BOTTOM_LEFT,
}

@Serializable
data class CalibrationCorner(
    val id: CalibrationCornerId,
    val x: Float,
    val y: Float,
)

data class CalibrationAutoInitResult(
    val initialCorners: List<CalibrationCorner>,
    val detectedBounds: ScreenRect,
    val confidence: Float,
    val source: String,
)

data class CalibrationSnapResult(
    val x: Float,
    val y: Float,
    val snapped: Boolean,
    val score: Float,
)

data class CalibrationMagnifierState(
    val activeCornerId: CalibrationCornerId? = null,
    val fingerX: Float = 0f,
    val fingerY: Float = 0f,
    val focusX: Float = 0f,
    val focusY: Float = 0f,
    val visible: Boolean = false,
)

enum class TemplateAdjustCheckMode {
    NORMAL,
    MASK_ONLY,
    CHECKERBOARD,
    EDGE_HIGHLIGHT,
}

enum class TemplateAdjustLoupeTarget {
    TOP_LEFT,
    TOP_RIGHT,
    BOTTOM_LEFT,
    BOTTOM_RIGHT,
    TOP_FEATURE,
}

enum class TemplateAdjustLayer {
    VISIBLE_BOUNDS,
    SCREEN_RECT,
    SCREEN_MASK_BOUNDS,
    CONTENT_CLIP_RECT,
    TEMPLATE_TOP_FEATURE,
    TOP_SUPPRESSION_RECT,
    CUTOUTS,
    LOGICAL_CANVAS_BOUNDS,
}

data class TemplateAdjustmentParams(
    val offsetX: Int = 0,
    val offsetY: Int = 0,
    val scale: Float = 1f,
    val topFeatureOffsetY: Int = 0,
    val clipInsetRight: Int = 1,
    val clipInsetBottom: Int = 1,
) {
    val snapped: TemplateAdjustmentParams
        get() = copy(
            scale = (scale * 1000f).toInt() / 1000f,
            clipInsetRight = clipInsetRight.coerceAtLeast(0),
            clipInsetBottom = clipInsetBottom.coerceAtLeast(0),
        )
}

data class TemplateImportDraft(
    val sourceImagePath: String,
    val templateName: String,
    val validationWarning: String? = null,
    val outputWidth: Int = 0,
    val outputHeight: Int = 0,
    val captureProfile: DeviceCaptureProfile = DeviceCaptureProfile(1, 1, 1f, 0, null, null, 0, 0L),
    val detectedScreenRect: ScreenRect = ScreenRect(0, 0, 1, 1),
    val detectionSummary: String = "",
    val corners: List<CalibrationCorner> = defaultCornersFor(ScreenRect(0, 0, 1, 1)),
    val defaultCorners: List<CalibrationCorner> = corners,
    val cornerRadiusPx: Float = 0f,
    val defaultCornerRadiusPx: Float = cornerRadiusPx,
    val autoInitSource: String = "fallback",
    val autoInitConfidence: Float = 0f,
    val snapEnabled: Boolean = true,
    val showGuides: Boolean = true,
    val baseVisibleBounds: ScreenRect = detectedScreenRect,
    val baseContentClipRect: ScreenRect = detectedScreenRect.insetForContentClipCanvas(
        outputWidth.coerceAtLeast(2),
        outputHeight.coerceAtLeast(2),
    ),
    val baseSafeTopBand: ScreenRect? = null,
    val baseTopSuppressionRect: ScreenRect? = null,
    val baseTopHoleOverlayRect: ScreenRect? = null,
    val baseTopFeatureAvoidRect: ScreenRect? = null,
    val baseTemplateTopFeature: TopFeatureAnchor? = null,
    // Legacy expert params kept only for compatibility with still-linked code paths.
    val overlayCenterX: Float = detectedScreenRect.left + detectedScreenRect.width / 2f,
    val overlayCenterY: Float = detectedScreenRect.top + detectedScreenRect.height / 2f,
    val overlayWidth: Float = detectedScreenRect.width.toFloat().coerceAtLeast(1f),
    val overlayHeight: Float = detectedScreenRect.height.toFloat().coerceAtLeast(1f),
    val overlayCornerRadius: Float = cornerRadiusPx,
    val adjustmentParams: TemplateAdjustmentParams = TemplateAdjustmentParams(),
    val checkMode: TemplateAdjustCheckMode = TemplateAdjustCheckMode.NORMAL,
    val loupeTarget: TemplateAdjustLoupeTarget = TemplateAdjustLoupeTarget.TOP_RIGHT,
    val loupeZoom: Int = 4,
    val visibleLayers: Set<TemplateAdjustLayer> = setOf(
        TemplateAdjustLayer.VISIBLE_BOUNDS,
        TemplateAdjustLayer.SCREEN_RECT,
        TemplateAdjustLayer.CONTENT_CLIP_RECT,
        TemplateAdjustLayer.TEMPLATE_TOP_FEATURE,
        TemplateAdjustLayer.TOP_SUPPRESSION_RECT,
    ),
    val defaultOverlayCenterX: Float = overlayCenterX,
    val defaultOverlayCenterY: Float = overlayCenterY,
    val defaultOverlayWidth: Float = overlayWidth,
    val defaultOverlayHeight: Float = overlayHeight,
    val defaultOverlayCornerRadius: Float = overlayCornerRadius,
) {
    val overlayAspectRatio: Float
        get() = captureProfile.captureAspectRatio.takeIf { it > 0f } ?: 1f

    val normalizedCorners: List<CalibrationCorner>
        get() = corners.normalizedFor(outputWidth.coerceAtLeast(2), outputHeight.coerceAtLeast(2))

    val fittedRect: ScreenRect
        get() = TemplateCalibrationEngine.fitRect(
            corners = normalizedCorners,
            canvasWidth = outputWidth.coerceAtLeast(2),
            canvasHeight = outputHeight.coerceAtLeast(2),
            outwardBleedPx = 0,
        )

    val finalScreenRect: ScreenRect
        get() = TemplateCalibrationEngine.fitRect(
            corners = normalizedCorners,
            canvasWidth = outputWidth.coerceAtLeast(2),
            canvasHeight = outputHeight.coerceAtLeast(2),
            outwardBleedPx = TemplateCalibrationEngine.DEFAULT_OUTWARD_BLEED_PX,
        )

    val finalContentClipRect: ScreenRect
        get() = TemplateCalibrationEngine.buildContentClipRect(
            visibleBounds = finalScreenRect,
            canvasWidth = outputWidth.coerceAtLeast(2),
            canvasHeight = outputHeight.coerceAtLeast(2),
        )
}

internal fun defaultCornersFor(rect: ScreenRect): List<CalibrationCorner> {
    return listOf(
        CalibrationCorner(CalibrationCornerId.TOP_LEFT, rect.left.toFloat(), rect.top.toFloat()),
        CalibrationCorner(CalibrationCornerId.TOP_RIGHT, rect.right.toFloat(), rect.top.toFloat()),
        CalibrationCorner(CalibrationCornerId.BOTTOM_RIGHT, rect.right.toFloat(), rect.bottom.toFloat()),
        CalibrationCorner(CalibrationCornerId.BOTTOM_LEFT, rect.left.toFloat(), rect.bottom.toFloat()),
    )
}

internal fun List<CalibrationCorner>.normalizedFor(width: Int, height: Int): List<CalibrationCorner> {
    return map { corner ->
        corner.copy(
            x = corner.x.coerceIn(0f, width.toFloat()),
            y = corner.y.coerceIn(0f, height.toFloat()),
        )
    }
}
