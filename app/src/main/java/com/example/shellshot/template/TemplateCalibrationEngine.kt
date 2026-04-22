package com.example.shellshot.template

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

data class CalibratedTemplateGeometry(
    val visibleBounds: ScreenRect,
    val screenRect: ScreenRect,
    val contentClipRect: ScreenRect,
    val safeTopBand: ScreenRect?,
    val topSuppressionRect: ScreenRect?,
    val topHoleOverlayRect: ScreenRect?,
    val topFeatureAvoidRect: ScreenRect?,
    val templateTopFeature: TopFeatureAnchor?,
)

object TemplateCalibrationEngine {
    const val DEFAULT_OUTWARD_BLEED_PX = 2
    private const val DEFAULT_CONTENT_CLIP_INSET_LEFT = 1
    private const val DEFAULT_CONTENT_CLIP_INSET_TOP = 1
    private const val DEFAULT_CONTENT_CLIP_INSET_RIGHT = 2
    private const val DEFAULT_CONTENT_CLIP_INSET_BOTTOM = 2

    fun fitRect(
        corners: List<CalibrationCorner>,
        canvasWidth: Int,
        canvasHeight: Int,
        outwardBleedPx: Int,
    ): ScreenRect {
        val ordered = corners.normalizedFor(canvasWidth, canvasHeight).associateBy { it.id }
        val topLeft = ordered[CalibrationCornerId.TOP_LEFT] ?: CalibrationCorner(CalibrationCornerId.TOP_LEFT, 0f, 0f)
        val topRight = ordered[CalibrationCornerId.TOP_RIGHT] ?: CalibrationCorner(CalibrationCornerId.TOP_RIGHT, canvasWidth.toFloat(), 0f)
        val bottomRight = ordered[CalibrationCornerId.BOTTOM_RIGHT] ?: CalibrationCorner(CalibrationCornerId.BOTTOM_RIGHT, canvasWidth.toFloat(), canvasHeight.toFloat())
        val bottomLeft = ordered[CalibrationCornerId.BOTTOM_LEFT] ?: CalibrationCorner(CalibrationCornerId.BOTTOM_LEFT, 0f, canvasHeight.toFloat())

        val left = floor(minOf(topLeft.x, bottomLeft.x) - outwardBleedPx).toInt()
        val top = floor(minOf(topLeft.y, topRight.y) - outwardBleedPx).toInt()
        val right = ceil(maxOf(topRight.x, bottomRight.x) + outwardBleedPx).toInt()
        val bottom = ceil(maxOf(bottomLeft.y, bottomRight.y) + outwardBleedPx).toInt()
        return ScreenRect(left, top, right, bottom).normalizeWithinCanvas(canvasWidth, canvasHeight)
    }

    fun buildContentClipRect(
        visibleBounds: ScreenRect,
        canvasWidth: Int,
        canvasHeight: Int,
    ): ScreenRect {
        return ScreenRect(
            left = visibleBounds.left + DEFAULT_CONTENT_CLIP_INSET_LEFT,
            top = visibleBounds.top + DEFAULT_CONTENT_CLIP_INSET_TOP,
            right = visibleBounds.right - DEFAULT_CONTENT_CLIP_INSET_RIGHT,
            bottom = visibleBounds.bottom - DEFAULT_CONTENT_CLIP_INSET_BOTTOM,
        ).normalizeWithinCanvas(canvasWidth, canvasHeight)
    }

    fun buildGeometry(
        draft: TemplateImportDraft,
        forSave: Boolean,
    ): CalibratedTemplateGeometry {
        val visibleBounds = fitRect(
            corners = draft.corners,
            canvasWidth = draft.outputWidth.coerceAtLeast(2),
            canvasHeight = draft.outputHeight.coerceAtLeast(2),
            outwardBleedPx = if (forSave) DEFAULT_OUTWARD_BLEED_PX else 0,
        )
        val base = draft.baseVisibleBounds
        val contentClipRect = buildContentClipRect(
            visibleBounds = visibleBounds,
            canvasWidth = draft.outputWidth.coerceAtLeast(2),
            canvasHeight = draft.outputHeight.coerceAtLeast(2),
        )
        return CalibratedTemplateGeometry(
            visibleBounds = visibleBounds,
            screenRect = visibleBounds,
            contentClipRect = contentClipRect,
            safeTopBand = draft.baseSafeTopBand?.scaleFrom(base, visibleBounds, draft.outputWidth, draft.outputHeight),
            topSuppressionRect = draft.baseTopSuppressionRect?.scaleFrom(base, visibleBounds, draft.outputWidth, draft.outputHeight),
            topHoleOverlayRect = draft.baseTopHoleOverlayRect?.scaleFrom(base, visibleBounds, draft.outputWidth, draft.outputHeight),
            topFeatureAvoidRect = draft.baseTopFeatureAvoidRect?.scaleFrom(base, visibleBounds, draft.outputWidth, draft.outputHeight),
            templateTopFeature = draft.baseTemplateTopFeature?.scaleFrom(base, visibleBounds),
        )
    }

    private fun ScreenRect.scaleFrom(
        base: ScreenRect,
        target: ScreenRect,
        canvasWidth: Int,
        canvasHeight: Int,
    ): ScreenRect {
        val scaleX = target.width.toFloat() / base.width.coerceAtLeast(1)
        val scaleY = target.height.toFloat() / base.height.coerceAtLeast(1)
        return ScreenRect(
            left = (target.left + (left - base.left) * scaleX).roundToInt(),
            top = (target.top + (top - base.top) * scaleY).roundToInt(),
            right = (target.left + (right - base.left) * scaleX).roundToInt(),
            bottom = (target.top + (bottom - base.top) * scaleY).roundToInt(),
        ).normalizeWithinCanvas(canvasWidth.coerceAtLeast(2), canvasHeight.coerceAtLeast(2))
    }

    private fun TopFeatureAnchor.scaleFrom(base: ScreenRect, target: ScreenRect): TopFeatureAnchor {
        val scaleX = target.width.toFloat() / base.width.coerceAtLeast(1)
        val scaleY = target.height.toFloat() / base.height.coerceAtLeast(1)
        return copy(
            centerX = target.left + (centerX - base.left) * scaleX,
            centerY = target.top + (centerY - base.top) * scaleY,
            width = width * scaleX,
            height = height * scaleY,
        )
    }
}

internal fun ScreenRect.normalizeWithinCanvas(width: Int, height: Int): ScreenRect {
    val safeLeft = left.coerceAtLeast(0).coerceAtMost(width - 2)
    val safeTop = top.coerceAtLeast(0).coerceAtMost(height - 2)
    val safeRight = right.coerceAtLeast(safeLeft + 1).coerceAtMost(width)
    val safeBottom = bottom.coerceAtLeast(safeTop + 1).coerceAtMost(height)
    return ScreenRect(safeLeft, safeTop, safeRight, safeBottom)
}

internal fun ScreenRect.insetForContentClipCanvas(width: Int, height: Int): ScreenRect =
    TemplateCalibrationEngine.buildContentClipRect(this, width, height)
