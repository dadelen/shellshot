package com.example.shellshot.template

import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.roundToInt

data class TemplateAppliedAdjustment(
    val visibleBounds: ScreenRect,
    val screenRect: ScreenRect,
    val contentClipRect: ScreenRect,
    val safeTopBand: ScreenRect?,
    val topSuppressionRect: ScreenRect?,
    val topHoleOverlayRect: ScreenRect?,
    val topFeatureAvoidRect: ScreenRect?,
    val templateTopFeature: TopFeatureAnchor?,
    val warnings: List<String>,
)

object TemplateAdjustmentApplier {
    const val IMPORT_PIPELINE_VERSION = 2
    const val MANUAL_ADJUST_VERSION = 1

    fun apply(
        baseVisibleBounds: ScreenRect,
        baseContentClipRect: ScreenRect,
        baseSafeTopBand: ScreenRect?,
        baseTopSuppressionRect: ScreenRect?,
        baseTopHoleOverlayRect: ScreenRect?,
        baseTopFeatureAvoidRect: ScreenRect?,
        baseTemplateTopFeature: TopFeatureAnchor?,
        params: TemplateAdjustmentParams,
        canvasWidth: Int,
        canvasHeight: Int,
        forSave: Boolean,
    ): TemplateAppliedAdjustment {
        val snapped = params.snapped
        val visibleBounds = transformRect(
            rect = baseVisibleBounds,
            base = baseVisibleBounds,
            params = snapped,
            canvasWidth = canvasWidth,
            canvasHeight = canvasHeight,
            outward = true,
        )
        val screenRect = visibleBounds
        val rightInset = baseContentClipRect.insetRightFrom(baseVisibleBounds) +
            snapped.clipInsetRight +
            if (forSave) SAVE_EXTRA_RIGHT_CLIP_INSET else 0
        val bottomInset = baseContentClipRect.insetBottomFrom(baseVisibleBounds) +
            snapped.clipInsetBottom +
            if (forSave) SAVE_EXTRA_BOTTOM_CLIP_INSET else 0
        val contentClipRect = ScreenRect(
            left = visibleBounds.left + baseContentClipRect.insetLeftFrom(baseVisibleBounds) + if (forSave) SAVE_EXTRA_LEFT_CLIP_INSET else 0,
            top = visibleBounds.top + baseContentClipRect.insetTopFrom(baseVisibleBounds),
            right = visibleBounds.right - rightInset,
            bottom = visibleBounds.bottom - bottomInset,
        ).normalizedWithin(canvasWidth, canvasHeight)
        val safeTopBand = baseSafeTopBand?.let {
            transformRect(it, baseVisibleBounds, snapped, canvasWidth, canvasHeight, outward = true)
        }
        val topSuppressionRect = baseTopSuppressionRect?.let {
            transformTopRect(it, baseVisibleBounds, snapped, canvasWidth, canvasHeight)
        }
        val topHoleOverlayRect = baseTopHoleOverlayRect?.let {
            transformTopRect(it, baseVisibleBounds, snapped, canvasWidth, canvasHeight)
        }
        val topFeatureAvoidRect = baseTopFeatureAvoidRect?.let {
            transformTopRect(it, baseVisibleBounds, snapped, canvasWidth, canvasHeight)
        }
        val topFeature = baseTemplateTopFeature?.let { feature ->
            val baseCenterX = baseVisibleBounds.left + baseVisibleBounds.width / 2f
            val baseCenterY = baseVisibleBounds.top + baseVisibleBounds.height / 2f
            TopFeatureAnchor(
                type = feature.type,
                centerX = baseCenterX + (feature.centerX - baseCenterX) * snapped.scale + snapped.offsetX,
                centerY = baseCenterY + (feature.centerY - baseCenterY) * snapped.scale +
                    snapped.offsetY + snapped.topFeatureOffsetY,
                width = feature.width * snapped.scale,
                height = feature.height * snapped.scale,
                confidence = feature.confidence,
            )
        }

        return TemplateAppliedAdjustment(
            visibleBounds = visibleBounds,
            screenRect = screenRect,
            contentClipRect = contentClipRect,
            safeTopBand = safeTopBand,
            topSuppressionRect = topSuppressionRect,
            topHoleOverlayRect = topHoleOverlayRect,
            topFeatureAvoidRect = topFeatureAvoidRect,
            templateTopFeature = topFeature,
            warnings = buildWarnings(
                visibleBounds = visibleBounds,
                screenRect = screenRect,
                contentClipRect = contentClipRect,
                safeTopBand = safeTopBand,
                topFeature = topFeature,
                canvasWidth = canvasWidth,
                canvasHeight = canvasHeight,
            ),
        )
    }

    private fun transformTopRect(
        rect: ScreenRect,
        base: ScreenRect,
        params: TemplateAdjustmentParams,
        canvasWidth: Int,
        canvasHeight: Int,
    ): ScreenRect {
        val transformed = transformRect(rect, base, params, canvasWidth, canvasHeight, outward = true)
        return transformed.copy(
            top = transformed.top + params.topFeatureOffsetY,
            bottom = transformed.bottom + params.topFeatureOffsetY,
        ).normalizedWithin(canvasWidth, canvasHeight)
    }

    private fun transformRect(
        rect: ScreenRect,
        base: ScreenRect,
        params: TemplateAdjustmentParams,
        canvasWidth: Int,
        canvasHeight: Int,
        outward: Boolean,
    ): ScreenRect {
        val cx = base.left + base.width / 2f
        val cy = base.top + base.height / 2f
        val left = cx + (rect.left - cx) * params.scale + params.offsetX
        val top = cy + (rect.top - cy) * params.scale + params.offsetY
        val right = cx + (rect.right - cx) * params.scale + params.offsetX
        val bottom = cy + (rect.bottom - cy) * params.scale + params.offsetY
        return if (outward) {
            ScreenRect(
                left = floor(left).roundToInt(),
                top = floor(top).roundToInt(),
                right = ceil(right).roundToInt(),
                bottom = ceil(bottom).roundToInt(),
            )
        } else {
            ScreenRect(
                left = left.roundToInt(),
                top = top.roundToInt(),
                right = right.roundToInt(),
                bottom = bottom.roundToInt(),
            )
        }.normalizedWithin(canvasWidth, canvasHeight)
    }

    private fun buildWarnings(
        visibleBounds: ScreenRect,
        screenRect: ScreenRect,
        contentClipRect: ScreenRect,
        safeTopBand: ScreenRect?,
        topFeature: TopFeatureAnchor?,
        canvasWidth: Int,
        canvasHeight: Int,
    ): List<String> = buildList {
        val rightReserve = visibleBounds.right - contentClipRect.right
        val bottomReserve = visibleBounds.bottom - contentClipRect.bottom
        if (rightReserve < 3 || canvasWidth - visibleBounds.right <= 1) {
            add("右侧可能露白，建议收紧 1px")
        }
        if (bottomReserve < 4 || canvasHeight - visibleBounds.bottom <= 1) {
            add("底边建议再 inward 1px")
        }
        if (topFeature != null && safeTopBand != null && topFeature.centerY + topFeature.height / 2f >= safeTopBand.bottom - 1) {
            add("顶部安全区不足，可能压孔")
        }
        val areaRatio = (screenRect.width * screenRect.height).toFloat() / (canvasWidth * canvasHeight).coerceAtLeast(1)
        if (areaRatio !in 0.32f..0.94f) {
            add("屏幕可见区偏大，建议缩小")
        }
    }

    private fun ScreenRect.insetLeftFrom(base: ScreenRect): Int = (left - base.left).coerceAtLeast(0)
    private fun ScreenRect.insetTopFrom(base: ScreenRect): Int = (top - base.top).coerceAtLeast(0)
    private fun ScreenRect.insetRightFrom(base: ScreenRect): Int = (base.right - right).coerceAtLeast(0)
    private fun ScreenRect.insetBottomFrom(base: ScreenRect): Int = (base.bottom - bottom).coerceAtLeast(0)

    private const val SAVE_EXTRA_LEFT_CLIP_INSET = 0
    private const val SAVE_EXTRA_RIGHT_CLIP_INSET = 1
    private const val SAVE_EXTRA_BOTTOM_CLIP_INSET = 1
}
