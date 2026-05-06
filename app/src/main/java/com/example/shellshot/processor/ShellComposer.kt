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
import kotlin.math.ceil
import kotlin.math.floor
import kotlin.math.hypot

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

        val geometry = geometryResolver.resolve(template, outputWidth, outputHeight)
        val sourceAnalysis = analyzeSource(sourceBitmap, geometry)
        val renderMode = resolveTopRenderMode(sourceAnalysis.topFeature, geometry.templateTopFeature)
        val calibrationCorners = geometry.calibrationCorners
        val srcW = sourceBitmap.width.toFloat()
        val srcH = sourceBitmap.height.toFloat()
        val edgeBleedPx = resolveEdgeBleedPx(template, geometry)
        val contentDrawRect = resolveContentDrawRect(
            sourceBitmap = sourceBitmap,
            geometry = geometry,
            template = template,
            sourceAnalysis = sourceAnalysis,
            edgeBleedPx = edgeBleedPx,
        )

        val contentLayerBounds = RectF(0f, 0f, outputWidth.toFloat(), outputHeight.toFloat())
        val contentLayerCheckpoint = canvas.saveLayer(contentLayerBounds, null)
        try {
            if (calibrationCorners.size == 4) {
                drawBitmapIntoQuad(
                    canvas = canvas,
                    bitmap = sourceBitmap,
                    drawRect = contentDrawRect,
                    targetRect = geometry.visibleBounds,
                    quadCorners = calibrationCorners,
                    bleedPx = edgeBleedPx,
                )
            } else {
                canvas.drawBitmap(sourceBitmap, null, contentDrawRect, createBitmapPaint())
            }

            if (screenMaskBitmap != null) {
                val paint = if (hasMeaningfulAlpha(screenMaskBitmap)) {
                    createAlphaMaskBitmapPaint()
                } else {
                    createLuminanceMaskBitmapPaint()
                }
                canvas.drawBitmap(
                    screenMaskBitmap,
                    null,
                    contentLayerBounds,
                    paint,
                )
            }

            canvas.drawPath(geometry.screenPath, createDstInPaint())
            val appliedCutouts = template.cutouts
                .filter { cutout -> shouldApplyTemplateCutout(renderMode, cutout, geometry) }
            appliedCutouts
                .forEach { cutout ->
                    applyCutoutMask(canvas, cutout, geometry, template)
                }
            if (renderMode == TopRenderMode.SHOW_TEMPLATE_HOLE && appliedCutouts.none { isTopFeatureCutout(it, geometry) }) {
                geometry.topSuppressionPath?.let { path ->
                    canvas.drawPath(path, createDstOutPaint())
                }
            }
        } finally {
            canvas.restoreToCount(contentLayerCheckpoint)
        }

        if (renderMode == TopRenderMode.SHOW_TEMPLATE_HOLE) {
            drawTemplateCutoutUnderlays(canvas, template, geometry)
            drawTemplateTopFeatureUnderlay(canvas, geometry)
        }
        drawFrameBitmap(canvas, frameBitmap, template, contentLayerBounds)
        if (template.debugDrawGuides) {
            drawDebugGuides(
                canvas = canvas,
                geometry = geometry,
                drawRect = contentDrawRect,
                sourceFeature = sourceAnalysis.topFeature,
                sourceStatusBarBand = sourceAnalysis.sourceStatusBarBand,
                sourceBitmap = sourceBitmap,
            )
        }

        val totalElapsedMs = SystemClock.elapsedRealtime() - startedAt
        logger.d(
            TAG,
            "合成完成 task=${taskId ?: "-"} template=${template.id} time=${totalElapsedMs}ms",
        )
        return outputBitmap
    }

    private fun drawBitmapIntoQuad(
        canvas: Canvas,
        bitmap: Bitmap,
        drawRect: RectF,
        targetRect: RectF,
        quadCorners: List<com.example.shellshot.template.CalibrationCorner>,
        bleedPx: Float,
    ) {
        val ordered = quadCorners.associateBy { it.id }
        val topLeft = ordered[com.example.shellshot.template.CalibrationCornerId.TOP_LEFT]
        val topRight = ordered[com.example.shellshot.template.CalibrationCornerId.TOP_RIGHT]
        val bottomRight = ordered[com.example.shellshot.template.CalibrationCornerId.BOTTOM_RIGHT]
        val bottomLeft = ordered[com.example.shellshot.template.CalibrationCornerId.BOTTOM_LEFT]
        if (topLeft == null || topRight == null || bottomRight == null || bottomLeft == null) {
            canvas.drawBitmap(bitmap, null, drawRect, createBitmapPaint())
            return
        }

        val matrix = Matrix()
        val src = floatArrayOf(
            0f, 0f,
            bitmap.width.toFloat(), 0f,
            bitmap.width.toFloat(), bitmap.height.toFloat(),
            0f, bitmap.height.toFloat(),
        )
        val dst = buildDestinationQuadForDrawRect(
            drawRect = drawRect,
            targetRect = targetRect,
            topLeft = topLeft,
            topRight = topRight,
            bottomRight = bottomRight,
            bottomLeft = bottomLeft,
        ).expandFromCenter(bleedPx)
        if (!matrix.setPolyToPoly(src, 0, dst, 0, 4)) {
            canvas.drawBitmap(bitmap, null, drawRect, createBitmapPaint())
            return
        }
        val clipPath = Path().apply {
            appendQuadPath(this, dst)
        }
        canvas.save()
        canvas.clipPath(clipPath)
        canvas.drawBitmap(bitmap, matrix, createBitmapPaint())
        canvas.restore()
    }

    private fun buildDestinationQuadForDrawRect(
        drawRect: RectF,
        targetRect: RectF,
        topLeft: com.example.shellshot.template.CalibrationCorner,
        topRight: com.example.shellshot.template.CalibrationCorner,
        bottomRight: com.example.shellshot.template.CalibrationCorner,
        bottomLeft: com.example.shellshot.template.CalibrationCorner,
    ): FloatArray {
        val targetWidth = targetRect.width().coerceAtLeast(1f)
        val targetHeight = targetRect.height().coerceAtLeast(1f)
        val leftU = (drawRect.left - targetRect.left) / targetWidth
        val rightU = (drawRect.right - targetRect.left) / targetWidth
        val topV = (drawRect.top - targetRect.top) / targetHeight
        val bottomV = (drawRect.bottom - targetRect.top) / targetHeight
        val topLeftPoint = interpolateQuadPoint(leftU, topV, topLeft, topRight, bottomRight, bottomLeft)
        val topRightPoint = interpolateQuadPoint(rightU, topV, topLeft, topRight, bottomRight, bottomLeft)
        val bottomRightPoint = interpolateQuadPoint(rightU, bottomV, topLeft, topRight, bottomRight, bottomLeft)
        val bottomLeftPoint = interpolateQuadPoint(leftU, bottomV, topLeft, topRight, bottomRight, bottomLeft)
        return floatArrayOf(
            topLeftPoint.x, topLeftPoint.y,
            topRightPoint.x, topRightPoint.y,
            bottomRightPoint.x, bottomRightPoint.y,
            bottomLeftPoint.x, bottomLeftPoint.y,
        )
    }

    private fun interpolateQuadPoint(
        u: Float,
        v: Float,
        topLeft: com.example.shellshot.template.CalibrationCorner,
        topRight: com.example.shellshot.template.CalibrationCorner,
        bottomRight: com.example.shellshot.template.CalibrationCorner,
        bottomLeft: com.example.shellshot.template.CalibrationCorner,
    ): QuadPoint {
        val topWeight = 1f - v
        val bottomWeight = v
        val leftWeight = 1f - u
        val rightWeight = u
        return QuadPoint(
            x = topLeft.x * leftWeight * topWeight +
                topRight.x * rightWeight * topWeight +
                bottomRight.x * rightWeight * bottomWeight +
                bottomLeft.x * leftWeight * bottomWeight,
            y = topLeft.y * leftWeight * topWeight +
                topRight.y * rightWeight * topWeight +
                bottomRight.y * rightWeight * bottomWeight +
                bottomLeft.y * leftWeight * bottomWeight,
        )
    }

    private fun appendQuadPath(path: Path, quad: FloatArray) {
        if (quad.size < 8) return
        path.moveTo(quad[0], quad[1])
        path.lineTo(quad[2], quad[3])
        path.lineTo(quad[4], quad[5])
        path.lineTo(quad[6], quad[7])
        path.close()
    }

    private data class QuadPoint(
        val x: Float,
        val y: Float,
    )

    private fun FloatArray.expandFromCenter(bleedPx: Float): FloatArray {
        if (size < 8 || bleedPx <= 0f) return this
        val centerX = (this[0] + this[2] + this[4] + this[6]) / 4f
        val centerY = (this[1] + this[3] + this[5] + this[7]) / 4f
        return FloatArray(size) { index ->
            if (index % 2 == 0) {
                val dx = this[index] - centerX
                val dy = this[index + 1] - centerY
                val length = hypot(dx, dy).coerceAtLeast(0.001f)
                this[index] + dx / length * bleedPx
            } else {
                val dx = this[index - 1] - centerX
                val dy = this[index] - centerY
                val length = hypot(dx, dy).coerceAtLeast(0.001f)
                this[index] + dy / length * bleedPx
            }
        }
    }

    private fun analyzeSource(
        sourceBitmap: Bitmap,
        geometry: ResolvedGeometry,
    ): ScreenshotAnalysis {
        return screenshotAnalyzer.analyze(
            bitmap = sourceBitmap,
            templatePrior = geometry.templateTopFeature?.let { buildTopFeaturePrior(it, geometry.visibleBounds) },
        )
    }

    private fun resolveContentDrawRect(
        sourceBitmap: Bitmap,
        geometry: ResolvedGeometry,
        template: ShellTemplate,
        sourceAnalysis: ScreenshotAnalysis,
        edgeBleedPx: Float,
    ): RectF {
        val sourceTopFeature = sourceAnalysis.topFeature
            ?.takeIf { isReliableSourceTopFeatureForVerticalAlignment(it, sourceBitmap) }

        val drawRect = if (geometry.calibrationCorners.size == 4) {
            calibratedComposeResolver.solve(
                srcW = sourceBitmap.width.toFloat(),
                srcH = sourceBitmap.height.toFloat(),
                geometry = geometry,
                overscan = edgeBleedPx,
                composeMode = DEFAULT_COMPOSE_MODE,
            )
        } else {
            coverRectSolver.solve(
                srcW = sourceBitmap.width.toFloat(),
                srcH = sourceBitmap.height.toFloat(),
                geometry = geometry,
                sourceAnchor = sourceTopFeature,
                targetAnchor = geometry.templateTopFeature,
                sourceStatusBarBand = sourceAnalysis.sourceStatusBarBand,
                overscan = edgeBleedPx,
                scaleMode = template.scaleMode,
                contentOffsetX = template.contentOffsetX * geometry.scaleX,
                contentOffsetY = template.contentOffsetY * geometry.scaleY,
                contentScaleAdjust = template.contentScaleAdjust,
                alignTopFeature = true,
                alignTopFeatureX = true,
                composeMode = DEFAULT_COMPOSE_MODE,
            )
        }

        return RectF(
            floor(drawRect.left - edgeBleedPx),
            floor(drawRect.top - edgeBleedPx),
            ceil(drawRect.right + edgeBleedPx),
            ceil(drawRect.bottom + edgeBleedPx),
        )
    }

    private fun resolveEdgeBleedPx(template: ShellTemplate, geometry: ResolvedGeometry): Float {
        val configuredOverscan = template.contentOverscanPx.coerceAtLeast(0f) * geometry.scaleToOutput
        val maskBleed = template.maskBleedPx.coerceAtLeast(0f) * geometry.scaleToOutput + WHITE_EDGE_MASK_EXTRA_PX
        return maxOf(WHITE_EDGE_MIN_BLEED_PX, configuredOverscan, maskBleed)
            .coerceAtMost(WHITE_EDGE_MAX_BLEED_PX * geometry.scaleToOutput.coerceAtLeast(1f))
    }

    private fun drawFrameBitmap(
        canvas: Canvas,
        frameBitmap: Bitmap,
        template: ShellTemplate,
        targetBounds: RectF,
    ) {
        if (!template.alphaTighten || !frameBitmap.hasAlpha()) {
            canvas.drawBitmap(frameBitmap, null, targetBounds, createBitmapPaint())
            return
        }

        val tightened = createAlphaTightenedFrameBitmap(frameBitmap, template)
        try {
            canvas.drawBitmap(tightened, null, targetBounds, createBitmapPaint())
        } finally {
            if (tightened !== frameBitmap && !tightened.isRecycled) {
                tightened.recycle()
            }
        }
    }

    private fun createAlphaTightenedFrameBitmap(
        frameBitmap: Bitmap,
        template: ShellTemplate,
    ): Bitmap {
        val width = frameBitmap.width
        val height = frameBitmap.height
        val pixels = IntArray(width * height)
        frameBitmap.getPixels(pixels, 0, width, 0, 0, width, height)
        val originalPixels = pixels.copyOf()

        val lowThreshold = template.alphaLowThreshold.coerceIn(0, 255)
        val highThreshold = template.alphaHighThreshold.coerceIn(lowThreshold, 255)
        var changed = false
        for (index in pixels.indices) {
            val color = pixels[index]
            val alpha = Color.alpha(color)
            val sanitized = when {
                alpha <= FRAME_ALPHA_TRANSPARENT_FLOOR -> Color.TRANSPARENT
                alpha <= lowThreshold && isBrightNeutralFringe(color) -> Color.TRANSPARENT
                alpha <= highThreshold &&
                    isBrightNeutralFringe(color) &&
                    hasTransparentNeighbor(originalPixels, width, height, index, lowThreshold) -> Color.TRANSPARENT
                alpha >= highThreshold && alpha < 255 -> Color.argb(
                    255,
                    Color.red(color),
                    Color.green(color),
                    Color.blue(color),
                )
                else -> color
            }
            if (sanitized != color) {
                pixels[index] = sanitized
                changed = true
            }
        }
        if (!changed) return frameBitmap
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }

    private fun isBrightNeutralFringe(color: Int): Boolean {
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val maxChannel = maxOf(red, green, blue)
        val minChannel = minOf(red, green, blue)
        val luminance = red * 0.299f + green * 0.587f + blue * 0.114f
        return luminance >= 210f && maxChannel - minChannel <= 48
    }

    private fun hasTransparentNeighbor(
        pixels: IntArray,
        width: Int,
        height: Int,
        index: Int,
        alphaThreshold: Int,
    ): Boolean {
        val x = index % width
        val y = index / width
        for (dy in -FRAME_FRINGE_NEIGHBOR_RADIUS..FRAME_FRINGE_NEIGHBOR_RADIUS) {
            val ny = y + dy
            if (ny !in 0 until height) continue
            for (dx in -FRAME_FRINGE_NEIGHBOR_RADIUS..FRAME_FRINGE_NEIGHBOR_RADIUS) {
                if (dx == 0 && dy == 0) continue
                val nx = x + dx
                if (nx !in 0 until width) continue
                if (Color.alpha(pixels[ny * width + nx]) <= alphaThreshold) {
                    return true
                }
            }
        }
        return false
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
        val rect = cutout.rect ?: return cutout.pathData?.isNotBlank() == true && renderMode == TopRenderMode.SHOW_TEMPLATE_HOLE
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
        return renderMode == TopRenderMode.SHOW_TEMPLATE_HOLE && nearFeatureCenter && isSmallFeatureCutout
    }

    private fun isTopFeatureCutout(
        cutout: CutoutRegion,
        geometry: ResolvedGeometry,
    ): Boolean {
        val rect = cutout.rect ?: return cutout.pathData?.isNotBlank() == true && geometry.topSuppressionRect != null
        val outputRect = RectF(
            rect.left * geometry.scaleX,
            rect.top * geometry.scaleY,
            rect.right * geometry.scaleX,
            rect.bottom * geometry.scaleY,
        )
        val topBand = geometry.topSuppressionRect ?: geometry.safeTopBand
        return RectF.intersects(outputRect, topBand)
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

    private fun drawTemplateCutoutUnderlays(
        canvas: Canvas,
        template: ShellTemplate,
        geometry: ResolvedGeometry,
    ) {
        val paint = createCutoutUnderlayPaint()
        template.cutouts
            .filter { cutout -> isTopFeatureCutout(cutout, geometry) }
            .forEach { cutout ->
                val bleed = (template.cutoutBleedPx + cutout.featherPx + CUTOUT_UNDERLAY_EXTRA_BLEED_PX)
                    .coerceAtLeast(0f) * geometry.scaleToOutput
                cutout.rect?.let { rect ->
                    val outputRect = RectF(
                        rect.left * geometry.scaleX,
                        rect.top * geometry.scaleY,
                        rect.right * geometry.scaleX,
                        rect.bottom * geometry.scaleY,
                    ).apply { inset(-bleed, -bleed) }
                    canvas.drawRoundRect(outputRect, outputRect.height() / 2f, outputRect.height() / 2f, paint)
                }
                cutout.pathData?.takeIf { it.isNotBlank() }?.let { pathData ->
                    runCatching {
                        val path = PathParser.createPathFromPathData(pathData)
                        path.transform(Matrix().apply { setScale(geometry.scaleX, geometry.scaleY) })
                        canvas.drawPath(path, paint)
                    }
                }
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
    private fun createCutoutUnderlayPaint(): Paint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply { color = Color.argb(255, 0, 0, 0); style = Paint.Style.FILL }
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
        const val WHITE_EDGE_MIN_BLEED_PX = 3.0f
        const val WHITE_EDGE_MAX_BLEED_PX = 9.0f
        const val WHITE_EDGE_MASK_EXTRA_PX = 1.5f
        const val FRAME_ALPHA_TRANSPARENT_FLOOR = 4
        const val FRAME_FRINGE_NEIGHBOR_RADIUS = 2
        const val CUTOUT_UNDERLAY_EXTRA_BLEED_PX = 1.2f
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
