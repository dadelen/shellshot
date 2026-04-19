package com.example.shellshot.processor.pipeline

import android.graphics.Path
import android.graphics.RectF
import com.example.shellshot.template.ScaleMode
import com.example.shellshot.template.ScreenRect
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.StatusBarSafeZones
import com.example.shellshot.template.TopFeatureAnchor

class CalibratedComposeResolver {
    var lastMetrics: CalibratedComposeMetrics? = null
        private set

    fun solve(
        srcW: Float,
        srcH: Float,
        geometry: ResolvedGeometry,
        overscan: Float,
        composeMode: ComposeMode,
    ): RectF {
        val target = geometry.visibleBounds
        val modeOverscanCap = when (composeMode) {
            ComposeMode.STANDARD -> 2.5f
            ComposeMode.SAFE -> 1.5f
            ComposeMode.MINIMAL -> 0.75f
        } * geometry.scaleToOutput
        val effectiveOverscan = overscan
            .coerceAtLeast(0f)
            .coerceAtMost(modeOverscanCap)
        val targetW = target.width() + effectiveOverscan * 2f
        val targetH = target.height() + effectiveOverscan * 2f
        val scale = maxOf(targetW / srcW, targetH / srcH)
        val drawW = srcW * scale
        val drawH = srcH * scale
        val rect = RectF(
            target.centerX() - drawW / 2f,
            target.centerY() - drawH / 2f,
            target.centerX() + drawW / 2f,
            target.centerY() + drawH / 2f,
        )
        val aligned = RectF(
            kotlin.math.floor(rect.left).toFloat(),
            kotlin.math.floor(rect.top).toFloat(),
            kotlin.math.ceil(rect.right).toFloat(),
            kotlin.math.ceil(rect.bottom).toFloat(),
        )
        lastMetrics = CalibratedComposeMetrics(
            overscanPx = effectiveOverscan,
            scale = scale,
            drawRect = aligned,
        )
        return aligned
    }
}

class GeometryResolver {
    fun resolve(template: ShellTemplate, outputWidth: Int, outputHeight: Int): ResolvedGeometry {
        val dx = outputWidth / template.effectiveLogicalWidth(outputWidth).toFloat()
        val dy = outputHeight / template.effectiveLogicalHeight(outputHeight).toFloat()
        val scale = (dx + dy) / 2f
        
        // 新模板以 screenMask 推导出的 visibleBounds 作为唯一几何真相。
        val screenRect = mapRect(template.visibleBounds ?: template.screenRect, dx, dy).apply {
            val inset = template.screenInsetPx * scale
            val bleed = template.maskBleedPx * scale
            inset(inset - bleed, inset - bleed)
        }
        val visibleBounds = screenRect
        val fallbackSafeTopBand = buildFallbackSafeTopBand(
            screenRect = visibleBounds,
            topFeature = template.templateTopFeature?.let { mapTopFeature(it, dx, dy) },
            scale = scale,
        )
        val safeTopBand = template.safeTopBand?.let { mapRect(it, dx, dy) } ?: fallbackSafeTopBand
        val contentClipRect = template.contentClipRect?.let { mapRect(it, dx, dy) }
            ?: buildFallbackContentClipRect(visibleBounds, scale)
        
        val resolvedTopFeature = template.templateTopFeature?.let { mapTopFeature(it, dx, dy) }

        // 映射特征抑制区：模板顶部结构负责最终外观，截图顶部结构只用于对齐。
        val topSuppressionRect = template.topSuppressionRect?.let { mapRect(it, dx, dy) }
            ?: resolvedTopFeature?.let { buildSuppressionRect(it) }
        val suppressionPath = topSuppressionRect?.let { rect ->
            buildSuppressionPath(rect)
        }
        val topHoleOverlayRect = template.topHoleOverlayRect?.let { mapRect(it, dx, dy) }
        val topFeatureAvoidRect = template.topFeatureAvoidRect?.let { mapRect(it, dx, dy) }
            ?: topSuppressionRect
        val statusBarSafeZones = template.statusBarSafeZones?.let { mapStatusBarSafeZones(it, dx, dy) }
        
        return ResolvedGeometry(
            screenRect = screenRect,
            visibleBounds = visibleBounds,
            safeTopBand = safeTopBand,
            contentClipRect = contentClipRect,
            contentClipPath = Path().apply { addRect(contentClipRect, Path.Direction.CW) },
            screenPath = Path().apply { addRoundRect(screenRect, template.cornerRadius * scale, template.cornerRadius * scale, Path.Direction.CW) },
            topSuppressionPath = suppressionPath,
            topSuppressionRect = topSuppressionRect,
            topHoleOverlayRect = topHoleOverlayRect,
            topFeatureAvoidRect = topFeatureAvoidRect,
            statusBarSafeZones = statusBarSafeZones,
            cutouts = template.cutouts,
            templateTopFeature = resolvedTopFeature,
            scaleX = dx,
            scaleY = dy,
            scaleToOutput = scale,
            safeTopInset = safeTopBand.bottom
        )
    }

    private fun mapRect(rect: ScreenRect, dx: Float, dy: Float): RectF {
        return RectF(
            rect.left * dx,
            rect.top * dy,
            rect.right * dx,
            rect.bottom * dy,
        )
    }

    private fun mapTopFeature(feature: TopFeatureAnchor, dx: Float, dy: Float): TopFeatureAnchor {
        return feature.copy(
            centerX = feature.centerX * dx,
            centerY = feature.centerY * dy,
            width = feature.width * dx,
            height = feature.height * dy,
        )
    }

    private fun mapStatusBarSafeZones(zones: StatusBarSafeZones, dx: Float, dy: Float): ResolvedStatusBarSafeZones {
        return ResolvedStatusBarSafeZones(
            left = mapRect(zones.left, dx, dy),
            right = mapRect(zones.right, dx, dy),
            avoid = mapRect(zones.avoid, dx, dy),
        )
    }

    private fun buildFallbackSafeTopBand(
        screenRect: RectF,
        topFeature: TopFeatureAnchor?,
        scale: Float,
    ): RectF {
        val bottom = topFeature?.let { it.centerY + it.height * 0.85f + 4f * scale }
            ?: (screenRect.top + 28f * scale)
        return RectF(
            screenRect.left,
            screenRect.top,
            screenRect.right,
            bottom.coerceIn(screenRect.top + 1f, screenRect.bottom),
        )
    }

    private fun buildFallbackContentClipRect(screenRect: RectF, scale: Float): RectF {
        return RectF(screenRect).apply {
            inset(3f * scale, 0f)
            bottom -= 12f * scale
        }
    }

    private fun buildSuppressionRect(feature: TopFeatureAnchor): RectF {
        val bleed = maxOf(feature.width, feature.height) * 0.08f
        val w = feature.width + bleed
        val h = feature.height + bleed
        val cx = feature.centerX
        val cy = feature.centerY
        return RectF(cx - w/2f, cy - h/2f, cx + w/2f, cy + h/2f)
    }

    private fun buildSuppressionPath(rect: RectF): Path {
        val aspect = rect.width() / rect.height()
        return Path().apply {
            if (aspect < 1.5f) {
                addCircle(rect.centerX(), rect.centerY(), maxOf(rect.width(), rect.height()) / 2f, Path.Direction.CW)
            } else {
                addRoundRect(rect, rect.height() / 2f, rect.height() / 2f, Path.Direction.CW)
            }
        }
    }
}

class CoverRectSolver {
    var lastMetrics: CoverRectMetrics? = null
        private set

    /**
     * 求解截图内容绘制的目标区域，包含过扫、裁剪及动态受限 Y 轴补偿。
     */
    fun solve(
        srcW: Float,
        srcH: Float,
        geometry: ResolvedGeometry,
        sourceAnchor: TopFeatureAnchor?,
        targetAnchor: TopFeatureAnchor?,
        sourceStatusBarBand: ScreenRect? = null,
        overscan: Float,
        scaleMode: ScaleMode = ScaleMode.CENTER_CROP,
        contentOffsetX: Float = 0f,
        contentOffsetY: Float = 0f,
        contentScaleAdjust: Float = 0f,
        alignTopFeature: Boolean = true,
        alignTopFeatureX: Boolean = true,
        composeMode: ComposeMode = ComposeMode.SAFE,
    ): RectF {
        val targetRect = geometry.visibleBounds
        val centerCropScale = maxOf(targetRect.width() / srcW, targetRect.height() / srcH)
        // 运行时唯一策略：CENTER_CROP。FIT_CENTER 旧值只保留反序列化兼容，不参与正式求解。
        val requestedScale = centerCropScale * (1f + contentScaleAdjust)
        val baseScale = maxOf(centerCropScale, requestedScale)
        val finalScale = baseScale
        val modeConfig = CoverRectSolverConfig.forMode(composeMode)
        val recommendedOverscanPx = if (overscan > 0f) overscan else modeConfig.defaultRecommendedOverscanPx * geometry.scaleToOutput
        val effectiveOverscan = recommendedOverscanPx
            .coerceAtLeast(0f)
            .coerceAtMost(modeConfig.maxOverscanPx * geometry.scaleToOutput)
        val statusBarBandCheck = validateStatusBarBand(
            band = sourceStatusBarBand,
            sourceAnchor = sourceAnchor,
            srcW = srcW,
            srcH = srcH,
        )
        // sourceStatusBarBand is intentionally debug/veto telemetry only.
        // Its detector is coarse and can overestimate the top band on busy wallpapers,
        // so it must not drive or disable the actual feature-to-feature alignment.
        val yAlignmentWeight = TOP_FEATURE_Y_ALIGNMENT_WEIGHT
        
        val drawW = srcW * finalScale + effectiveOverscan * 2f
        val drawH = srcH * finalScale + effectiveOverscan * 2f
        
        val baseRect = RectF(
            targetRect.centerX() - drawW / 2f + contentOffsetX,
            targetRect.centerY() - drawH / 2f + contentOffsetY,
            targetRect.centerX() + drawW / 2f + contentOffsetX,
            targetRect.centerY() + drawH / 2f + contentOffsetY,
        )
        val result = RectF(baseRect)
        
        // Dynamic Alignment with Anchor
        var yClampTriggered = false
        var statusBarSafeCheckTriggered = false
        var featureDx = 0f
        var featureDy = 0f
        var wantDy = 0f
        var appliedDx = 0f
        var appliedDy = 0f
        var topAlignmentRejected = false
        val canAlignTopFeature = alignTopFeature && composeMode == ComposeMode.STANDARD
        val bottomReservePx = maxOf(
            targetRect.height() * modeConfig.bottomReserveRatio,
            modeConfig.minBottomReservePx * geometry.scaleToOutput,
            effectiveOverscan,
        )
        if (canAlignTopFeature && sourceAnchor != null && targetAnchor != null) {
            val drawScaleX = drawW / srcW
            val drawScaleY = drawH / srcH
            val mappedSourceX = baseRect.left + sourceAnchor.centerX * drawScaleX
            val mappedSourceY = baseRect.top + sourceAnchor.centerY * drawScaleY
            
            featureDx = targetAnchor.centerX - mappedSourceX
            val maxDx = minOf(targetRect.width() * modeConfig.maxDxRatio, modeConfig.maxDxPx * geometry.scaleToOutput)
            val safeDx = if (alignTopFeatureX) featureDx.coerceIn(-maxDx, maxDx) else 0f
            
            featureDy = targetAnchor.centerY - mappedSourceY
            wantDy = featureDy * yAlignmentWeight
            val maxYOffsetDown = minOf(targetRect.height() * modeConfig.maxDyDownRatio, modeConfig.maxDyDownPx * geometry.scaleToOutput)
            val maxYOffsetUp = minOf(targetRect.height() * modeConfig.maxDyUpRatio, modeConfig.maxDyUpPx * geometry.scaleToOutput)
            val maxDownByBottomReserve = (baseRect.bottom - (geometry.contentClipRect.bottom + bottomReservePx))
                .coerceAtLeast(0f)
            val safeYOffset = wantDy
                .coerceIn(-maxYOffsetUp, maxYOffsetDown)
                .coerceAtMost(maxDownByBottomReserve)

            yClampTriggered = safeYOffset != wantDy
            val alignedRect = RectF(baseRect).apply { offset(safeDx, safeYOffset) }
            val bottomLossRatio = estimateBottomLossRatio(alignedRect, targetRect)
            val coversTarget = covers(alignedRect, geometry.contentClipRect)
            if (bottomLossRatio <= modeConfig.maxBottomLossRatio && coversTarget) {
                result.set(alignedRect)
                appliedDx = safeDx
                appliedDy = safeYOffset
            } else {
                val dyByBottomLoss = maxDyForBottomLoss(
                    baseRect = baseRect,
                    target = targetRect,
                    maxBottomLossRatio = modeConfig.maxBottomLossRatio,
                )
                val reducedDy = safeYOffset.coerceAtMost(dyByBottomLoss)
                val reducedRect = RectF(baseRect).apply { offset(safeDx, reducedDy) }
                if (reducedDy != safeYOffset &&
                    reducedDy != 0f &&
                    estimateBottomLossRatio(reducedRect, targetRect) <= modeConfig.maxBottomLossRatio &&
                    covers(reducedRect, geometry.contentClipRect)
                ) {
                    result.set(reducedRect)
                    appliedDx = safeDx
                    appliedDy = reducedDy
                    yClampTriggered = true
                } else {
                    val noDxRect = RectF(baseRect).apply { offset(0f, reducedDy) }
                    if (reducedDy != 0f &&
                        estimateBottomLossRatio(noDxRect, targetRect) <= modeConfig.maxBottomLossRatio &&
                        covers(noDxRect, geometry.contentClipRect)
                    ) {
                        result.set(noDxRect)
                        appliedDy = reducedDy
                        yClampTriggered = true
                    } else {
                        topAlignmentRejected = true
                        yClampTriggered = true
                    }
                }
            }
        }
        if (!topAlignmentRejected && geometry.topFeatureAvoidRect != null && geometry.statusBarSafeZones != null) {
            val minTop = geometry.visibleBounds.top - effectiveOverscan
            if (result.top < minTop) {
                result.offset(0f, minTop - result.top)
                yClampTriggered = true
                statusBarSafeCheckTriggered = true
            }
        }
        
        // Coverage correction 只做平移兜底，禁止触发二次 scale。
        // 如果纠偏会让顶部对齐侵蚀底部安全区，则放弃顶部对齐并回退 baseRect。
        val coverageTarget = RectF(geometry.contentClipRect)
        var dx = 0f; var dy = 0f
        if (result.left > coverageTarget.left) dx = coverageTarget.left - result.left
        else if (result.right < coverageTarget.right) dx = coverageTarget.right - result.right
        
        if (result.top > coverageTarget.top) dy = coverageTarget.top - result.top
        else if (result.bottom < coverageTarget.bottom) dy = coverageTarget.bottom - result.bottom
        if (dx != 0f || dy != 0f) {
            val corrected = RectF(result).apply { offset(dx, dy) }
            if (canAlignTopFeature && estimateBottomLossRatio(corrected, targetRect) > modeConfig.maxBottomLossRatio) {
                result.set(baseRect)
                topAlignmentRejected = true
                appliedDx = 0f
                appliedDy = 0f
                dx = 0f
                dy = 0f
            } else {
                result.set(corrected)
            }
        }
        val finalBottomLossRatio = estimateBottomLossRatio(result, targetRect)
        lastMetrics = CoverRectMetrics(
            composeMode = composeMode,
            overscan = effectiveOverscan,
            scale = finalScale,
            baseScale = baseScale,
            finalScale = finalScale,
            scaleDelta = finalScale - baseScale,
            recommendedOverscanPx = recommendedOverscanPx,
            overscanPx = effectiveOverscan,
            featureDx = featureDx,
            featureDy = featureDy,
            wantDx = featureDx,
            wantDy = wantDy,
            appliedDx = appliedDx,
            appliedDy = appliedDy,
            bottomReservePx = bottomReservePx,
            bottomLossRatio = finalBottomLossRatio,
            maxBottomLossRatio = modeConfig.maxBottomLossRatio,
            yClampTriggered = yClampTriggered,
            coverageCorrectionTriggered = dx != 0f || dy != 0f,
            topAlignmentRejected = topAlignmentRejected,
            statusBarSafeCheckTriggered = statusBarSafeCheckTriggered,
            statusBarBandVetoTriggered = statusBarBandCheck.isVetoed,
            statusBarBandVetoReason = statusBarBandCheck.reason,
        )
        
        // Pixel Alignment
        return RectF(
            kotlin.math.floor(result.left).toFloat(),
            kotlin.math.floor(result.top).toFloat(),
            kotlin.math.ceil(result.right).toFloat(),
            kotlin.math.ceil(result.bottom).toFloat()
        )
    }

    private fun covers(rect: RectF, target: RectF): Boolean {
        return rect.left <= target.left &&
            rect.top <= target.top &&
            rect.right >= target.right &&
            rect.bottom >= target.bottom
    }

    private fun estimateBottomLossRatio(rect: RectF, target: RectF): Float {
        val loss = (rect.bottom - target.bottom).coerceAtLeast(0f)
        return (loss / rect.height().coerceAtLeast(1f)).coerceIn(0f, 1f)
    }

    private fun maxDyForBottomLoss(
        baseRect: RectF,
        target: RectF,
        maxBottomLossRatio: Float,
    ): Float {
        val maxAllowedLossPx = baseRect.height().coerceAtLeast(1f) * maxBottomLossRatio
        return (target.bottom + maxAllowedLossPx - baseRect.bottom).coerceAtLeast(0f)
    }

    private fun validateStatusBarBand(
        band: ScreenRect?,
        sourceAnchor: TopFeatureAnchor?,
        srcW: Float,
        srcH: Float,
    ): StatusBarBandCheck {
        if (band == null) return StatusBarBandCheck(isVetoed = false, reason = null)
        if (srcW <= 0f || srcH <= 0f) {
            return StatusBarBandCheck(isVetoed = true, reason = "invalid_source_size")
        }
        val heightRatio = band.height / srcH
        if (heightRatio !in MIN_STATUS_BAND_HEIGHT_RATIO..MAX_STATUS_BAND_HEIGHT_RATIO) {
            return StatusBarBandCheck(isVetoed = true, reason = "status_band_height_ratio=$heightRatio")
        }
        if (band.top < 0 || band.top > srcH * MAX_STATUS_BAND_TOP_RATIO) {
            return StatusBarBandCheck(isVetoed = true, reason = "status_band_top=${band.top}")
        }
        if (band.bottom > srcH * MAX_STATUS_BAND_BOTTOM_RATIO) {
            return StatusBarBandCheck(isVetoed = true, reason = "status_band_bottom=${band.bottom}")
        }
        val horizontalCoverageRatio = band.width / srcW
        if (horizontalCoverageRatio < MIN_STATUS_BAND_WIDTH_RATIO) {
            return StatusBarBandCheck(isVetoed = true, reason = "status_band_width_ratio=$horizontalCoverageRatio")
        }
        if (sourceAnchor != null) {
            val allowedTop = band.top - srcH * STATUS_FEATURE_TOP_TOLERANCE_RATIO
            val allowedBottom = band.bottom + srcH * STATUS_FEATURE_BOTTOM_TOLERANCE_RATIO
            if (sourceAnchor.centerY !in allowedTop..allowedBottom) {
                return StatusBarBandCheck(isVetoed = true, reason = "feature_outside_status_band")
            }
        }
        return StatusBarBandCheck(isVetoed = false, reason = null)
    }

    private data class StatusBarBandCheck(
        val isVetoed: Boolean,
        val reason: String?,
    )

    private data class CoverRectSolverConfig(
        val maxBottomLossRatio: Float,
        val bottomReserveRatio: Float,
        val minBottomReservePx: Float,
        val allowTopAlignScaleDelta: Float,
        val defaultRecommendedOverscanPx: Float,
        val maxOverscanPx: Float,
        val maxDxPx: Float,
        val maxDxRatio: Float,
        val maxDyDownPx: Float,
        val maxDyDownRatio: Float,
        val maxDyUpPx: Float,
        val maxDyUpRatio: Float,
    ) {
        companion object {
            fun forMode(mode: ComposeMode): CoverRectSolverConfig = when (mode) {
                ComposeMode.STANDARD -> CoverRectSolverConfig(
                    maxBottomLossRatio = 0.05f,
                    bottomReserveRatio = 0.035f,
                    minBottomReservePx = 8f,
                    allowTopAlignScaleDelta = 0f,
                    defaultRecommendedOverscanPx = 1.5f,
                    maxOverscanPx = 3f,
                    maxDxPx = 28f,
                    maxDxRatio = 0.030f,
                    maxDyDownPx = 42f,
                    maxDyDownRatio = 0.035f,
                    maxDyUpPx = 24f,
                    maxDyUpRatio = 0.020f,
                )
                ComposeMode.SAFE -> CoverRectSolverConfig(
                    maxBottomLossRatio = 0.03f,
                    bottomReserveRatio = 0.035f,
                    minBottomReservePx = 10f,
                    allowTopAlignScaleDelta = 0f,
                    defaultRecommendedOverscanPx = 1f,
                    maxOverscanPx = 2f,
                    maxDxPx = 0f,
                    maxDxRatio = 0f,
                    maxDyDownPx = 0f,
                    maxDyDownRatio = 0f,
                    maxDyUpPx = 0f,
                    maxDyUpRatio = 0f,
                )
                ComposeMode.MINIMAL -> CoverRectSolverConfig(
                    maxBottomLossRatio = 0.03f,
                    bottomReserveRatio = 0.035f,
                    minBottomReservePx = 10f,
                    allowTopAlignScaleDelta = 0f,
                    defaultRecommendedOverscanPx = 0.75f,
                    maxOverscanPx = 1.5f,
                    maxDxPx = 0f,
                    maxDxRatio = 0f,
                    maxDyDownPx = 0f,
                    maxDyDownRatio = 0f,
                    maxDyUpPx = 0f,
                    maxDyUpRatio = 0f,
                )
            }
        }
    }

    private companion object {
        const val TOP_FEATURE_Y_ALIGNMENT_WEIGHT = 0.45f
        const val MIN_STATUS_BAND_HEIGHT_RATIO = 0.010f
        const val MAX_STATUS_BAND_HEIGHT_RATIO = 0.060f
        const val MAX_STATUS_BAND_TOP_RATIO = 0.080f
        const val MAX_STATUS_BAND_BOTTOM_RATIO = 0.180f
        const val MIN_STATUS_BAND_WIDTH_RATIO = 0.60f
        const val STATUS_FEATURE_TOP_TOLERANCE_RATIO = 0.030f
        const val STATUS_FEATURE_BOTTOM_TOLERANCE_RATIO = 0.080f
    }
}
