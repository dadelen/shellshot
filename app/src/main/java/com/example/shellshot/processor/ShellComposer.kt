package com.example.shellshot.processor

import android.graphics.Bitmap
import android.graphics.BlendMode
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.os.SystemClock
import androidx.core.graphics.PathParser
import com.example.shellshot.processor.pipeline.CalibratedComposeResolver
import com.example.shellshot.processor.pipeline.ComposeMode
import com.example.shellshot.processor.pipeline.CoverRectSolver
import com.example.shellshot.processor.pipeline.GeometryResolver
import com.example.shellshot.processor.pipeline.ResolvedGeometry
import com.example.shellshot.template.CutoutRegion
import com.example.shellshot.template.ScreenRect
import com.example.shellshot.template.ScreenshotAnalysis
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.TemplateRepository
import com.example.shellshot.template.TopFeatureAnchor
import com.example.shellshot.template.TopFeatureType
import com.example.shellshot.utils.ShellLogger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ShellComposer(
    private val templateRepository: TemplateRepository,
    private val logger: ShellLogger,
    private val screenshotAnalyzer: ScreenshotAnalyzer = ScreenshotAnalyzer(),
    private val geometryResolver: GeometryResolver = GeometryResolver(),
    private val coverRectSolver: CoverRectSolver = CoverRectSolver(),
    private val calibratedComposeResolver: CalibratedComposeResolver = CalibratedComposeResolver(),
) {

    suspend fun compose(
        sourceBitmap: Bitmap,
        frameBitmap: Bitmap,
        template: ShellTemplate,
        screenMaskBitmap: Bitmap? = null,
        taskId: String? = null,
    ): Bitmap = withContext(Dispatchers.Default) {
        composeInternal(
            sourceBitmap = sourceBitmap,
            frameBitmap = frameBitmap,
            template = template,
            screenMaskBitmap = screenMaskBitmap,
            taskId = taskId,
        )
    }

    suspend fun compose(
        sourceBitmap: Bitmap,
        template: ShellTemplate,
        taskId: String? = null,
    ): Bitmap {
        val frameBitmap = templateRepository.loadFrameBitmap(template)
        val screenMaskBitmap = templateRepository.loadScreenMaskBitmap(template)
        try {
            return compose(
                sourceBitmap = sourceBitmap,
                frameBitmap = frameBitmap,
                template = template,
                screenMaskBitmap = screenMaskBitmap,
                taskId = taskId,
            )
        } finally {
            if (!frameBitmap.isRecycled) frameBitmap.recycle()
            if (screenMaskBitmap != null && !screenMaskBitmap.isRecycled) screenMaskBitmap.recycle()
        }
    }

    private fun composeInternal(
        sourceBitmap: Bitmap,
        frameBitmap: Bitmap,
        template: ShellTemplate,
        screenMaskBitmap: Bitmap?,
        taskId: String?,
    ): Bitmap {
        val startedAt = SystemClock.elapsedRealtime()
        val outputWidth = template.outputWidth.takeIf { it > 0 } ?: frameBitmap.width
        val outputHeight = template.outputHeight.takeIf { it > 0 } ?: frameBitmap.height
        
        val outputBitmap = Bitmap.createBitmap(outputWidth, outputHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(outputBitmap)
        canvas.drawColor(parseColor(template.backgroundColor))

        // 1. Resolve Geometry mapping Logical Space -> Output Space
        val geometry = geometryResolver.resolve(template, outputWidth, outputHeight)

        val composeMode = if (template.calibrationEnabled) ComposeMode.STANDARD else DEFAULT_COMPOSE_MODE
        val currentCaptureProfile = runCatching { templateRepository.currentDeviceCaptureProfile() }.getOrNull()
        val calibrationProfileChanged = template.calibration?.captureProfile
            ?.let { saved -> currentCaptureProfile?.let { saved.hasMeaningfulChangeFrom(it) } }
            ?: false

        // 2. Analyze Screenshot for telemetry only when calibration is available.
        val sourceAnalysis: ScreenshotAnalysis = screenshotAnalyzer.analyze(
            bitmap = sourceBitmap,
            templatePrior = geometry.templateTopFeature?.let { feature ->
                buildTopFeaturePrior(feature, geometry.visibleBounds)
            },
        )
        val detectedSourceTopFeature: TopFeatureAnchor? = sourceAnalysis.topFeature
        val sourceTopFeatureIsFullyReliable = detectedSourceTopFeature
            ?.let { isReliableSourceTopFeature(it, sourceBitmap) }
            ?: false
        val sourceTopFeature: TopFeatureAnchor? = detectedSourceTopFeature
            ?.takeIf {
                sourceTopFeatureIsFullyReliable || isReliableSourceTopFeatureForVerticalAlignment(it, sourceBitmap)
            }
        val renderMode = resolveTopRenderMode(
            sourceTopFeature = sourceTopFeature,
            templateTopFeature = template.templateTopFeature,
        )
        val shouldAlignTopFeature = sourceTopFeature != null && geometry.templateTopFeature != null
        val allowTopFeatureAlignment = !template.calibrationEnabled && shouldAlignTopFeature && composeMode == ComposeMode.STANDARD

        // 3. Calibrated templates bypass aggressive runtime alignment. The saved overlay is the
        // source of truth; top feature detection remains debug telemetry only.
        val contentDrawRect = if (template.calibrationEnabled) {
            calibratedComposeResolver.solve(
                srcW = sourceBitmap.width.toFloat(),
                srcH = sourceBitmap.height.toFloat(),
                geometry = geometry,
                overscan = template.contentOverscanPx * geometry.scaleToOutput,
                composeMode = composeMode,
            )
        } else {
            coverRectSolver.solve(
                srcW = sourceBitmap.width.toFloat(),
                srcH = sourceBitmap.height.toFloat(),
                geometry = geometry,
                sourceAnchor = sourceTopFeature,
                targetAnchor = geometry.templateTopFeature,
                sourceStatusBarBand = sourceAnalysis.sourceStatusBarBand,
                overscan = template.contentOverscanPx * geometry.scaleToOutput,
                scaleMode = template.scaleMode,
                contentOffsetX = template.contentOffsetX * geometry.scaleX,
                contentOffsetY = template.contentOffsetY * geometry.scaleY,
                contentScaleAdjust = template.contentScaleAdjust,
                alignTopFeature = allowTopFeatureAlignment,
                alignTopFeatureX = sourceTopFeatureIsFullyReliable,
                composeMode = composeMode,
            )
        }
        val metrics = if (template.calibrationEnabled) null else coverRectSolver.lastMetrics
        val calibratedMetrics = calibratedComposeResolver.lastMetrics
        val sourceTopScoreX = sourceTopFeature?.let { topFeatureScoreX(it, sourceBitmap) } ?: 0f
        val sourceTopScoreY = sourceTopFeature?.let { topFeatureScoreY(it, sourceBitmap) } ?: 0f
        val elapsedMs = SystemClock.elapsedRealtime() - startedAt
        val fallbackTriggered = metrics?.topAlignmentRejected ?: false
        val bottomLossRatio = metrics?.bottomLossRatio ?: 0f
        logger.d(
            TAG,
            "合成指标 task=${taskId ?: "-"} template=${template.id} mode=$composeMode " +
                "calibrated=${template.calibrationEnabled} profileChanged=$calibrationProfileChanged " +
                "top=${sourceTopFeature?.type ?: "NONE"} align=$allowTopFeatureAlignment " +
                "dy=${metrics?.appliedDy?.toInt() ?: 0} bottom=${"%.3f".format(bottomLossRatio)} " +
                "overscan=${(calibratedMetrics?.overscanPx ?: metrics?.overscanPx ?: -1f).toInt()} " +
                "fallback=$fallbackTriggered time=${elapsedMs}ms",
        )
        metrics?.let { value ->
            if (value.bottomLossRatio > value.maxBottomLossRatio ||
                kotlin.math.abs(value.scaleDelta) > 0.001f ||
                kotlin.math.abs(value.appliedDy) > value.bottomReservePx
            ) {
                logger.d(
                    TAG,
                    "合成后验警告 taskId=${taskId ?: "none"} template=${template.id} " +
                        "bottomLossRatio=${value.bottomLossRatio} maxBottomLossRatio=${value.maxBottomLossRatio} " +
                        "baseScale=${value.baseScale} finalScale=${value.finalScale} scaleDelta=${value.scaleDelta} " +
                        "appliedDy=${value.appliedDy} bottomReservePx=${value.bottomReservePx} fallbackTriggered=${value.topAlignmentRejected}",
                )
            }
        }
        if (template.debugDrawGuides) {
            logger.d(
                TAG,
                "compose debug template=${template.id} version=${template.templateVersion} purity=${template.purity?.overallScore ?: -1f} " +
                    "taskId=${taskId ?: "none"} sourceSize=${sourceBitmap.width}x${sourceBitmap.height} visibleBounds=${geometry.visibleBounds.toShortLog()} " +
                    "calibrated=${template.calibrationEnabled} profileChanged=$calibrationProfileChanged " +
                    "sourceTop=${sourceTopFeature?.type}:${sourceTopFeature?.centerX},${sourceTopFeature?.centerY} " +
                    "sourceTopScore=${sourceTopFeature?.confidence ?: 0f} sourceTopScoreX=$sourceTopScoreX sourceTopScoreY=$sourceTopScoreY " +
                    "detectedTop=${detectedSourceTopFeature?.type}:${detectedSourceTopFeature?.centerX},${detectedSourceTopFeature?.centerY} " +
                    "templateTop=${geometry.templateTopFeature?.type}:${geometry.templateTopFeature?.centerX},${geometry.templateTopFeature?.centerY} " +
                    "baseScale=${metrics?.baseScale ?: -1f} finalScale=${metrics?.finalScale ?: -1f} scaleDelta=${metrics?.scaleDelta ?: -1f} " +
                    "calibratedScale=${calibratedMetrics?.scale ?: -1f} calibratedDraw=${calibratedMetrics?.drawRect?.toShortLog()} " +
                    "recommendedOverscanPx=${metrics?.recommendedOverscanPx ?: -1f} appliedOverscanPx=${metrics?.overscanPx ?: -1f} " +
                    "featureDx=${metrics?.featureDx ?: 0f} featureDy=${metrics?.featureDy ?: 0f} wantDx=${metrics?.wantDx ?: 0f} wantDy=${metrics?.wantDy ?: 0f} " +
                    "appliedDx=${metrics?.appliedDx ?: 0f} appliedDy=${metrics?.appliedDy ?: 0f} bottomReservePx=${metrics?.bottomReservePx ?: 0f} bottomLossRatio=${metrics?.bottomLossRatio ?: 0f} " +
                    "yClamp=${metrics?.yClampTriggered ?: false} topAlignRejected=${metrics?.topAlignmentRejected ?: false} coverageCorrection=${metrics?.coverageCorrectionTriggered ?: false} " +
                    "statusBandVeto=${metrics?.statusBarBandVetoTriggered ?: false} vetoReason=${metrics?.statusBarBandVetoReason ?: "none"} " +
                    "renderMode=$renderMode composeMode=$composeMode elapsedMs=$elapsedMs",
            )
        }

        val contentLayer = Bitmap.createBitmap(outputWidth, outputHeight, Bitmap.Config.ARGB_8888)
        val contentCanvas = Canvas(contentLayer)
        try {
            // STEP A: contentLayer 离屏绘制 screenshot
            contentCanvas.drawBitmap(sourceBitmap, null, contentDrawRect, createBitmapPaint())

            // STEP B: 动态顶部渲染模式。
            // 模板孔/岛由 frame.png 负责最终外观。这里不再用 topSuppressionPath
            // 对内容层做大范围 DST_OUT，避免顶部出现透明白块/白胶囊。
            if (!template.calibrationEnabled && composeMode != ComposeMode.MINIMAL) {
                geometry.cutouts.forEach { cutout ->
                    if (shouldApplyTemplateCutout(renderMode, cutout, geometry)) {
                        applyCutoutMask(contentCanvas, cutout, geometry, template)
                    }
                }
            }

            // STEP C: 主屏幕 DST_IN 裁剪
            if (screenMaskBitmap != null) {
                val paint = if (hasMeaningfulAlpha(screenMaskBitmap)) {
                    createAlphaMaskBitmapPaint()
                } else {
                    createLuminanceMaskBitmapPaint()
                }
                // screen_mask.png 是模板完整画布坐标，必须映射到完整输出画布。
                // 不能再缩放进 screenRect，否则 mask 会二次压缩并造成出框/错位。
                contentCanvas.drawBitmap(
                    screenMaskBitmap,
                    null,
                    RectF(0f, 0f, outputWidth.toFloat(), outputHeight.toFloat()),
                    paint,
                )
            } else {
                contentCanvas.drawPath(geometry.screenPath, createDstInPaint())
            }

            // STEP D: contentClipRect 二级硬裁剪。screenMask 负责真实屏幕形状，
            // contentClipRect 负责再吃掉右/底/孔边可能残留的 1px 采样污染。
            contentCanvas.drawRect(geometry.contentClipRect, createDstInPaint())

            // STEP E: 主画布贴回 contentLayer。
            canvas.drawBitmap(contentLayer, 0f, 0f, null)
        } finally {
            if (!contentLayer.isRecycled) contentLayer.recycle()
        }

        // STEP F: 模板孔下方画小黑底承接抗锯齿，最终外观由 frame.png 覆盖。
        if (renderMode == TopRenderMode.SHOW_TEMPLATE_HOLE) {
            drawTemplateTopFeatureUnderlay(canvas, geometry)
        }

        // STEP G: 最后叠加 frame overlay
        canvas.drawBitmap(frameBitmap, null, RectF(0f, 0f, outputWidth.toFloat(), outputHeight.toFloat()), createBitmapPaint())

        if (template.debugDrawGuides) {
            drawDebugGuides(
                canvas = canvas,
                geometry = geometry,
                drawRect = contentDrawRect,
                sourceFeature = sourceTopFeature,
                sourceStatusBarBand = sourceAnalysis.sourceStatusBarBand,
                sourceBitmap = sourceBitmap,
            )
        }

        return outputBitmap
    }

    private fun resolveTopRenderMode(
        sourceTopFeature: TopFeatureAnchor?,
        templateTopFeature: TopFeatureAnchor?,
    ): TopRenderMode {
        // 当前模板顶部孔/岛仍在 frame.png 里，无法真正隐藏；因此模板有顶部结构时必须由模板决定最终外观。
        if (templateTopFeature != null) {
            return TopRenderMode.SHOW_TEMPLATE_HOLE
        }
        return if (sourceTopFeature?.type == TopFeatureType.ISLAND) {
            TopRenderMode.SHOW_SOURCE_ISLAND
        } else {
            TopRenderMode.SHOW_TEMPLATE_HOLE
        }
    }

    private fun buildTopFeaturePrior(
        feature: TopFeatureAnchor,
        visibleBounds: RectF,
    ): ScreenshotAnalyzer.TopFeaturePrior {
        val width = visibleBounds.width().coerceAtLeast(1f)
        val height = visibleBounds.height().coerceAtLeast(1f)
        return ScreenshotAnalyzer.TopFeaturePrior(
            type = feature.type,
            centerXRatio = ((feature.centerX - visibleBounds.left) / width).coerceIn(0f, 1f),
            centerYRatio = ((feature.centerY - visibleBounds.top) / height).coerceIn(0f, 1f),
        )
    }

    private fun isReliableSourceTopFeature(
        feature: TopFeatureAnchor,
        sourceBitmap: Bitmap,
    ): Boolean {
        if (sourceBitmap.width <= 0 || sourceBitmap.height <= 0) return false
        if (feature.confidence < SOURCE_TOP_FEATURE_MIN_CONFIDENCE) return false

        val centerXRatio = feature.centerX / sourceBitmap.width.toFloat()
        if (centerXRatio !in SOURCE_TOP_FEATURE_MIN_CENTER_X_RATIO..SOURCE_TOP_FEATURE_MAX_CENTER_X_RATIO) {
            return false
        }

        val centerYRatio = feature.centerY / sourceBitmap.height.toFloat()
        if (centerYRatio !in SOURCE_TOP_FEATURE_MIN_CENTER_Y_RATIO..SOURCE_TOP_FEATURE_MAX_CENTER_Y_RATIO) {
            return false
        }

        val widthRatio = feature.width / sourceBitmap.width.toFloat()
        val heightRatio = feature.height / sourceBitmap.height.toFloat()
        if (widthRatio < SOURCE_TOP_FEATURE_MIN_WIDTH_RATIO || heightRatio < SOURCE_TOP_FEATURE_MIN_HEIGHT_RATIO) {
            return false
        }

        return true
    }

    private fun isReliableSourceTopFeatureForVerticalAlignment(
        feature: TopFeatureAnchor,
        sourceBitmap: Bitmap,
    ): Boolean {
        if (sourceBitmap.width <= 0 || sourceBitmap.height <= 0) return false
        if (feature.confidence < SOURCE_TOP_FEATURE_MIN_CONFIDENCE) return false

        val centerXRatio = feature.centerX / sourceBitmap.width.toFloat()
        if (centerXRatio !in SOURCE_TOP_FEATURE_VERTICAL_MIN_CENTER_X_RATIO..SOURCE_TOP_FEATURE_VERTICAL_MAX_CENTER_X_RATIO) {
            return false
        }

        val centerYRatio = feature.centerY / sourceBitmap.height.toFloat()
        if (centerYRatio !in SOURCE_TOP_FEATURE_MIN_CENTER_Y_RATIO..SOURCE_TOP_FEATURE_MAX_CENTER_Y_RATIO) {
            return false
        }

        val widthRatio = feature.width / sourceBitmap.width.toFloat()
        val heightRatio = feature.height / sourceBitmap.height.toFloat()
        if (widthRatio < SOURCE_TOP_FEATURE_MIN_WIDTH_RATIO || heightRatio < SOURCE_TOP_FEATURE_MIN_HEIGHT_RATIO) {
            return false
        }

        return true
    }

    private fun topFeatureScoreX(feature: TopFeatureAnchor, sourceBitmap: Bitmap): Float {
        if (sourceBitmap.width <= 0) return 0f
        val centerXRatio = feature.centerX / sourceBitmap.width.toFloat()
        return (1f - kotlin.math.abs(centerXRatio - 0.5f) / 0.5f).coerceIn(0f, 1f)
    }

    private fun topFeatureScoreY(feature: TopFeatureAnchor, sourceBitmap: Bitmap): Float {
        if (sourceBitmap.height <= 0) return 0f
        val centerYRatio = feature.centerY / sourceBitmap.height.toFloat()
        return when {
            centerYRatio in SOURCE_TOP_FEATURE_MIN_CENTER_Y_RATIO..SOURCE_TOP_FEATURE_MAX_CENTER_Y_RATIO -> 1f
            centerYRatio < SOURCE_TOP_FEATURE_MIN_CENTER_Y_RATIO ->
                (centerYRatio / SOURCE_TOP_FEATURE_MIN_CENTER_Y_RATIO).coerceIn(0f, 1f)
            else -> (1f - ((centerYRatio - SOURCE_TOP_FEATURE_MAX_CENTER_Y_RATIO) / SOURCE_TOP_FEATURE_MAX_CENTER_Y_RATIO))
                .coerceIn(0f, 1f)
        }
    }

    private fun shouldApplyTemplateCutout(
        renderMode: TopRenderMode,
        cutout: CutoutRegion,
        geometry: ResolvedGeometry,
    ): Boolean {
        val rect = cutout.rect ?: return false
        val outputRect = RectF(
            rect.left * geometry.scaleX,
            rect.top * geometry.scaleY,
            rect.right * geometry.scaleX,
            rect.bottom * geometry.scaleY,
        )
        val topBand = geometry.topSuppressionRect ?: geometry.safeTopBand
        if (!RectF.intersects(outputRect, topBand)) return true
        val feature = geometry.templateTopFeature ?: return renderMode != TopRenderMode.SHOW_SOURCE_ISLAND
        val nearFeatureCenter =
            kotlin.math.abs(outputRect.centerX() - feature.centerX) <= feature.width &&
                kotlin.math.abs(outputRect.centerY() - feature.centerY) <= feature.height
        val isSmallFeatureCutout =
            outputRect.width() <= feature.width * 1.8f &&
                outputRect.height() <= feature.height * 1.8f
        return nearFeatureCenter && isSmallFeatureCutout
    }

    private fun drawDebugGuides(
        canvas: Canvas,
        geometry: ResolvedGeometry,
        drawRect: RectF,
        sourceFeature: TopFeatureAnchor?,
        sourceStatusBarBand: ScreenRect?,
        sourceBitmap: Bitmap,
    ) {
        val screenPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(220, 52, 199, 89); style = Paint.Style.STROKE; strokeWidth = 3f }
        val drawRectPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(220, 0, 122, 255); style = Paint.Style.STROKE; strokeWidth = 3f }
        val suppressPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(230, 255, 59, 48); style = Paint.Style.STROKE; strokeWidth = 4f }
        val sourcePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(230, 255, 59, 48); style = Paint.Style.STROKE; strokeWidth = 3f }
        val sourceBandPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(220, 255, 149, 0); style = Paint.Style.STROKE; strokeWidth = 2f }
        val templatePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(230, 255, 204, 0); style = Paint.Style.STROKE; strokeWidth = 3f }
        val clipPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(230, 90, 200, 250); style = Paint.Style.STROKE; strokeWidth = 3f }
        val safePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(230, 175, 82, 222); style = Paint.Style.STROKE; strokeWidth = 2f }
        val bottomReservePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(180, 255, 45, 85); style = Paint.Style.STROKE; strokeWidth = 3f }
        val textPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply { color = Color.argb(230, 255, 149, 0); textSize = 22f }
        
        canvas.drawRect(geometry.screenRect, screenPaint)
        canvas.drawRect(geometry.contentClipRect, clipPaint)
        canvas.drawRect(geometry.safeTopBand, safePaint)
        coverRectSolver.lastMetrics?.let { metrics ->
            canvas.drawRect(
                RectF(
                    geometry.visibleBounds.left,
                    geometry.visibleBounds.bottom - metrics.bottomReservePx,
                    geometry.visibleBounds.right,
                    geometry.visibleBounds.bottom,
                ),
                bottomReservePaint,
            )
        }
        canvas.drawPath(geometry.screenPath, screenPaint)
        canvas.drawRect(drawRect, drawRectPaint)
        sourceFeature?.let { feature ->
            val scaleX = drawRect.width() / sourceBitmap.width.toFloat()
            val scaleY = drawRect.height() / sourceBitmap.height.toFloat()
            val cx = drawRect.left + feature.centerX * scaleX
            val cy = drawRect.top + feature.centerY * scaleY
            val halfW = feature.width * scaleX / 2f
            val halfH = feature.height * scaleY / 2f
            canvas.drawOval(RectF(cx - halfW, cy - halfH, cx + halfW, cy + halfH), sourcePaint)
        }
        sourceStatusBarBand?.let { band ->
            val scaleX = drawRect.width() / sourceBitmap.width.toFloat()
            val scaleY = drawRect.height() / sourceBitmap.height.toFloat()
            canvas.drawRect(
                RectF(
                    drawRect.left + band.left * scaleX,
                    drawRect.top + band.top * scaleY,
                    drawRect.left + band.right * scaleX,
                    drawRect.top + band.bottom * scaleY,
                ),
                sourceBandPaint,
            )
        }
        geometry.templateTopFeature?.let { feature ->
            val halfW = feature.width / 2f
            val halfH = feature.height / 2f
            canvas.drawOval(
                RectF(feature.centerX - halfW, feature.centerY - halfH, feature.centerX + halfW, feature.centerY + halfH),
                templatePaint,
            )
        }
        geometry.topSuppressionPath?.let { canvas.drawPath(it, suppressPaint) }
        coverRectSolver.lastMetrics?.let { metrics ->
            canvas.drawText(
                "mode=${metrics.composeMode} dx=${metrics.appliedDx.toInt()} dy=${metrics.appliedDy.toInt()} bottom=${metrics.bottomLossRatio} fallback=${metrics.topAlignmentRejected}",
                24f,
                42f,
                textPaint,
            )
        }
    }

    private fun drawTemplateTopFeatureUnderlay(canvas: Canvas, geometry: ResolvedGeometry) {
        val feature = geometry.templateTopFeature ?: return
        // 只给模板孔边缘补一个“几乎不可见”的黑底承接抗锯齿，目标是刚好吃掉白边，
        // 而不是形成可见黑块。这里宁可略微保守，也不把 underlay 做大。
        val bleed = (0.32f * geometry.scaleToOutput).coerceAtLeast(0.22f)
        val rect = RectF(
            feature.centerX - feature.width / 2f - bleed,
            feature.centerY - feature.height / 2f - bleed,
            feature.centerX + feature.width / 2f + bleed,
            feature.centerY + feature.height / 2f + bleed,
        )
        val underlayPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            color = Color.argb(245, 0, 0, 0)
            style = Paint.Style.FILL
        }
        if (rect.width() / rect.height().coerceAtLeast(1f) < 1.5f) {
            val radius = maxOf(rect.width(), rect.height()) / 2f
            canvas.drawCircle(rect.centerX(), rect.centerY(), radius, underlayPaint)
        } else {
            canvas.drawRoundRect(rect, rect.height() / 2f, rect.height() / 2f, underlayPaint)
        }
    }

    private fun applyCutoutMask(
        contentCanvas: Canvas,
        cutout: CutoutRegion,
        geometry: ResolvedGeometry,
        template: ShellTemplate,
    ) {
        val paint = createDstOutPaint()
        val bleed = (template.cutoutBleedPx + cutout.featherPx).coerceAtLeast(0f) * geometry.scaleToOutput
        cutout.rect?.let { rect ->
            val outputRect = RectF(
                rect.left * geometry.scaleX,
                rect.top * geometry.scaleY,
                rect.right * geometry.scaleX,
                rect.bottom * geometry.scaleY,
            ).apply { inset(-bleed, -bleed) }
            contentCanvas.drawRoundRect(outputRect, outputRect.height() / 2f, outputRect.height() / 2f, paint)
        }
        cutout.pathData?.takeIf { it.isNotBlank() }?.let { pathData ->
            runCatching {
                val path = PathParser.createPathFromPathData(pathData)
                path.transform(Matrix().apply { setScale(geometry.scaleX, geometry.scaleY) })
                contentCanvas.drawPath(path, paint)
            }
        }
    }

    private fun parseColor(raw: String): Int = runCatching { Color.parseColor(raw) }.getOrDefault(Color.TRANSPARENT)
    private fun createBitmapPaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG).apply { isFilterBitmap = true; isDither = true }
    private fun createDstInPaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply { color = Color.WHITE; style = Paint.Style.FILL; blendMode = BlendMode.DST_IN }
    private fun createDstOutPaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply { color = Color.WHITE; style = Paint.Style.FILL; blendMode = BlendMode.DST_OUT }
    private fun createAlphaMaskBitmapPaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG).apply { blendMode = BlendMode.DST_IN }
    private fun createLuminanceMaskBitmapPaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG).apply { blendMode = BlendMode.DST_IN; colorFilter = ColorMatrixColorFilter(ColorMatrix(floatArrayOf(0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0f, 0.299f, 0.587f, 0.114f, 0f, 0f))) }

    private fun hasMeaningfulAlpha(bitmap: Bitmap): Boolean {
        if (!bitmap.hasAlpha()) return false
        val stepX = (bitmap.width / 32).coerceAtLeast(1)
        val stepY = (bitmap.height / 32).coerceAtLeast(1)
        var transparentCount = 0
        var opaqueCount = 0
        for (y in 0 until bitmap.height step stepY) {
            for (x in 0 until bitmap.width step stepX) {
                val alpha = Color.alpha(bitmap.getPixel(x, y))
                if (alpha <= 16) transparentCount += 1
                if (alpha >= 239) opaqueCount += 1
                if (transparentCount > 2 && opaqueCount > 2) return true
            }
        }
        return false
    }

    private fun RectF.toShortLog(): String {
        return "${left.toInt()},${top.toInt()},${right.toInt()},${bottom.toInt()}"
    }

    private companion object {
        const val TAG = "ShellComposer"
        const val SOURCE_TOP_FEATURE_MIN_CONFIDENCE = 0.62f
        const val SOURCE_TOP_FEATURE_MIN_CENTER_X_RATIO = 0.46f
        const val SOURCE_TOP_FEATURE_MAX_CENTER_X_RATIO = 0.54f
        const val SOURCE_TOP_FEATURE_VERTICAL_MIN_CENTER_X_RATIO = 0.36f
        const val SOURCE_TOP_FEATURE_VERTICAL_MAX_CENTER_X_RATIO = 0.64f
        const val SOURCE_TOP_FEATURE_MIN_CENTER_Y_RATIO = 0.018f
        const val SOURCE_TOP_FEATURE_MAX_CENTER_Y_RATIO = 0.060f
        const val SOURCE_TOP_FEATURE_MIN_WIDTH_RATIO = 0.015f
        const val SOURCE_TOP_FEATURE_MIN_HEIGHT_RATIO = 0.006f
        val DEFAULT_COMPOSE_MODE = ComposeMode.SAFE
    }

    private enum class TopRenderMode {
        SHOW_SOURCE_ISLAND,
        SHOW_TEMPLATE_HOLE,
    }
}
