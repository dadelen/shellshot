package com.example.shellshot.processor

import android.graphics.BlendMode
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Matrix
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import androidx.core.graphics.alpha
import androidx.core.graphics.PathParser
import com.example.shellshot.template.CutoutRegion
import com.example.shellshot.template.ScaleMode
import com.example.shellshot.template.ScreenRect
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
) {
    // Support direct bitmap-driven composition so callers that already own frame/mask bitmaps
    // can skip asset loading and reuse the same fixed-template pipeline.
    suspend fun compose(
        sourceBitmap: Bitmap,
        frameBitmap: Bitmap,
        template: ShellTemplate,
        screenMaskBitmap: Bitmap? = null,
    ): Bitmap = withContext(Dispatchers.Default) {
        composeInternal(
            sourceBitmap = sourceBitmap,
            frameBitmap = frameBitmap,
            template = template,
            screenMaskBitmap = screenMaskBitmap,
        )
    }

    // Main app flow keeps bitmap loading outside the composer and delegates here after the
    // screenshot has already been decoded upstream.
    suspend fun compose(
        sourceBitmap: Bitmap,
        template: ShellTemplate,
    ): Bitmap {
        val frameBitmap = templateRepository.loadFrameBitmap(template)
        val screenMaskBitmap = templateRepository.loadScreenMaskBitmap(template)

        try {
            return compose(
                sourceBitmap = sourceBitmap,
                frameBitmap = frameBitmap,
                template = template,
                screenMaskBitmap = screenMaskBitmap,
            )
        } finally {
            if (!frameBitmap.isRecycled) {
                frameBitmap.recycle()
            }
            if (screenMaskBitmap != null && !screenMaskBitmap.isRecycled) {
                screenMaskBitmap.recycle()
            }
        }
    }

    private fun composeInternal(
        sourceBitmap: Bitmap,
        frameBitmap: Bitmap,
        template: ShellTemplate,
        screenMaskBitmap: Bitmap?,
    ): Bitmap {
        val outputSize = resolveOutputSize(template, frameBitmap)
        val outputBitmap = Bitmap.createBitmap(
            outputSize.width,
            outputSize.height,
            Bitmap.Config.ARGB_8888,
        )
        val canvas = Canvas(outputBitmap)

        // Draw any configured background first so transparent frame edges still export cleanly.
        canvas.drawColor(parseColor(template.backgroundColor))

        val geometry = resolveGeometryInOutputSpace(
            template = template,
            outputSize = outputSize,
        )
        val sourceTopFeature = detectSourceTopFeature(sourceBitmap)
        val templateTopFeature = template.templateTopFeature?.scaledToOutput(
            template = template,
            outputSize = outputSize,
        )
        val effectiveTopFeature = when (sourceTopFeature?.type) {
            TopFeatureType.ISLAND -> sourceTopFeature
            TopFeatureType.PUNCH_HOLE -> sourceTopFeature
            else -> templateTopFeature
        }
        val contentDrawRect = calculateAlignedCoverRect(
            sourceBitmap = sourceBitmap,
            template = template,
            geometry = geometry,
            sourceTopFeature = sourceTopFeature,
            templateTopFeature = templateTopFeature,
        ).alignToPixel()

        val contentLayer = Bitmap.createBitmap(
            outputSize.width,
            outputSize.height,
            Bitmap.Config.ARGB_8888,
        )
        val contentCanvas = Canvas(contentLayer)
        val bitmapPaint = createBitmapPaint()

        try {
            contentCanvas.drawBitmap(sourceBitmap, null, contentDrawRect, bitmapPaint)
            applyScreenMask(
                contentCanvas = contentCanvas,
                outputSize = outputSize,
                geometry = geometry,
                screenMaskBitmap = screenMaskBitmap,
                template = template,
            )
            val sourceHasTopFeature = sourceTopFeature?.type == TopFeatureType.ISLAND ||
                sourceTopFeature?.type == TopFeatureType.PUNCH_HOLE
            geometry.cutouts.forEach { cutout ->
                // If the screenshot already contains a top island/hole, keep that semantic
                // content and only suppress template cutouts in the top feature band.
                if (!sourceHasTopFeature || !cutout.isTopFeatureCutout(geometry.screenRect)) {
                    applyCutoutMask(
                        contentCanvas = contentCanvas,
                        cutout = cutout,
                    )
                }
            }
            canvas.drawBitmap(contentLayer, 0f, 0f, null)
        } finally {
            if (!contentLayer.isRecycled) {
                contentLayer.recycle()
            }
        }

        drawFrameOverlay(
            canvas = canvas,
            frameBitmap = frameBitmap,
            outputSize = outputSize,
            templateTopFeature = templateTopFeature,
            sourceTopFeature = sourceTopFeature,
            contentDrawRect = contentDrawRect,
            sourceBitmap = sourceBitmap,
        )
        if (template.debugDrawGuides) {
            drawDebugGuides(
                canvas = canvas,
                geometry = geometry,
                contentDrawRect = contentDrawRect,
                sourceTopFeature = sourceTopFeature,
                templateTopFeature = templateTopFeature,
                effectiveTopFeature = effectiveTopFeature,
                sourceBitmap = sourceBitmap,
            )
        }
        return outputBitmap
    }

    private fun resolveGeometryInOutputSpace(
        template: ShellTemplate,
        outputSize: OutputSize,
    ): CompositionGeometry {
        val screenRect = template.screenRect
            .scaledToOutput(
                outputWidth = outputSize.width,
                outputHeight = outputSize.height,
                template = template,
            )
            .inset(template.screenInsetPx * geometryScale(template, outputSize))
            .coerceWithin(
                outputWidth = outputSize.width,
                outputHeight = outputSize.height,
            )
            .toRectF()
            .outset(template.maskBleedPx * geometryScale(template, outputSize))
            .alignToPixel()

        val screenPath = buildScreenShapePath(
            template = template,
            outputSize = outputSize,
            screenRect = screenRect,
        )

        val cutouts = template.cutouts.mapNotNull { cutout ->
            resolveCutoutInOutputSpace(
                cutout = cutout,
                template = template,
                outputSize = outputSize,
            )
        }

        return CompositionGeometry(
            screenRect = screenRect,
            screenPath = screenPath,
            cutouts = cutouts,
            scale = geometryScale(template, outputSize),
        )
    }

    private fun buildScreenShapePath(
        template: ShellTemplate,
        outputSize: OutputSize,
        screenRect: RectF,
    ): Path {
        val rawPath = template.screenPath
            ?.takeIf { it.isNotBlank() }
            ?: template.screenMaskPath?.takeIf { it.isNotBlank() }

        val path = rawPath?.let {
            parseTemplatePath(
                rawPath = it,
                template = template,
                outputSize = outputSize,
            )
        }

        return path ?: buildRoundedRectPath(
            screenRect = screenRect,
            cornerRadius = template.cornerRadius * geometryScale(template, outputSize) + template.maskBleedPx * geometryScale(template, outputSize),
        )
    }

    private fun resolveCutoutInOutputSpace(
        cutout: CutoutRegion,
        template: ShellTemplate,
        outputSize: OutputSize,
    ): ResolvedCutout? {
        val scale = geometryScale(template, outputSize)
        val bleed = (template.cutoutBleedPx + cutout.featherPx).coerceAtLeast(0f) * scale
        val rect = cutout.rect?.scaledToOutput(
            outputWidth = outputSize.width,
            outputHeight = outputSize.height,
            template = template,
            )?.toRectF()?.outset(bleed)?.alignToPixel()
        val path = cutout.pathData?.takeIf { it.isNotBlank() }?.let {
            parseTemplatePath(
                rawPath = it,
                template = template,
                outputSize = outputSize,
            )
        }

        return when {
            path != null -> ResolvedCutout(path = path, rect = null, bleedPx = bleed)
            rect != null -> ResolvedCutout(path = null, rect = rect, bleedPx = bleed)
            else -> null
        }
    }

    private fun calculateAlignedCoverRect(
        sourceBitmap: Bitmap,
        template: ShellTemplate,
        geometry: CompositionGeometry,
        sourceTopFeature: TopFeatureAnchor?,
        templateTopFeature: TopFeatureAnchor?,
    ): RectF {
        val overscan = (template.contentOverscanPx * geometry.scale).coerceAtLeast(0f)
        val baseTarget = geometry.screenRect.outset(overscan)
        val targetWidth = baseTarget.width()
        val targetHeight = baseTarget.height()
        val requestedScale = when (template.scaleMode) {
            ScaleMode.FIT_CENTER -> minOf(targetWidth / sourceBitmap.width, targetHeight / sourceBitmap.height)
            ScaleMode.CENTER_CROP -> maxOf(targetWidth / sourceBitmap.width, targetHeight / sourceBitmap.height)
        } * (1f + template.contentScaleAdjust)
        val coverScale = maxOf(
            targetWidth / sourceBitmap.width,
            targetHeight / sourceBitmap.height,
        )
        val scale = maxOf(requestedScale, coverScale)

        val scaledWidth = sourceBitmap.width * scale
        val scaledHeight = sourceBitmap.height * scale
        val offsetX = template.contentOffsetX * geometry.scale
        val offsetY = template.contentOffsetY * geometry.scale

        val rect = RectF(
            baseTarget.centerX() - scaledWidth / 2f,
            baseTarget.centerY() - scaledHeight / 2f,
            baseTarget.centerX() + scaledWidth / 2f,
            baseTarget.centerY() + scaledHeight / 2f,
        )

        val sourceFeature = sourceTopFeature?.takeIf {
            it.type == TopFeatureType.ISLAND || it.type == TopFeatureType.PUNCH_HOLE
        }
        if (sourceFeature != null && templateTopFeature != null) {
            val mappedSourceCenterX = rect.left + sourceFeature.centerX * scale
            val mappedSourceCenterY = rect.top + sourceFeature.centerY * scale
            rect.offset(
                templateTopFeature.centerX - mappedSourceCenterX,
                templateTopFeature.centerY - mappedSourceCenterY,
            )
        }

        rect.offset(offsetX, offsetY)
        return rect.clampToCover(baseTarget)
    }

    private fun detectSourceTopFeature(sourceBitmap: Bitmap): TopFeatureAnchor? {
        if (sourceBitmap.width <= 0 || sourceBitmap.height <= 0) {
            return null
        }

        val scanWidth = sourceBitmap.width
        val scanHeight = (sourceBitmap.height * TOP_FEATURE_SCAN_HEIGHT_RATIO)
            .toInt()
            .coerceIn(1, sourceBitmap.height)
        val pixels = IntArray(scanWidth * scanHeight)
        sourceBitmap.getPixels(pixels, 0, scanWidth, 0, 0, scanWidth, scanHeight)

        val visited = BooleanArray(pixels.size)
        val components = mutableListOf<TopFeatureComponent>()
        val queue = IntArray(pixels.size)

        for (index in pixels.indices) {
            if (visited[index] || !isTopFeatureDarkPixel(pixels[index])) {
                visited[index] = true
                continue
            }

            var head = 0
            var tail = 0
            queue[tail++] = index
            visited[index] = true
            var minX = scanWidth
            var minY = scanHeight
            var maxX = -1
            var maxY = -1
            var count = 0

            while (head < tail) {
                val current = queue[head++]
                val x = current % scanWidth
                val y = current / scanWidth
                count += 1
                if (x < minX) minX = x
                if (y < minY) minY = y
                if (x > maxX) maxX = x
                if (y > maxY) maxY = y

                for (dy in -1..1) {
                    for (dx in -1..1) {
                        if (dx == 0 && dy == 0) continue
                        val nx = x + dx
                        val ny = y + dy
                        if (nx !in 0 until scanWidth || ny !in 0 until scanHeight) continue
                        val next = ny * scanWidth + nx
                        if (visited[next]) continue
                        visited[next] = true
                        if (isTopFeatureDarkPixel(pixels[next])) {
                            queue[tail++] = next
                        }
                    }
                }
            }

            val width = maxX - minX + 1
            val height = maxY - minY + 1
            if (width >= TOP_FEATURE_MIN_SIZE_PX && height >= TOP_FEATURE_MIN_SIZE_PX) {
                components += TopFeatureComponent(
                    left = minX,
                    top = minY,
                    right = maxX + 1,
                    bottom = maxY + 1,
                    pixelCount = count,
                    sourceWidth = scanWidth,
                    sourceScanHeight = scanHeight,
                )
            }
        }

        val island = components
            .mapNotNull { it.toIslandAnchor() }
            .maxByOrNull { it.confidence }
        if (island != null) {
            return island
        }

        return components
            .mapNotNull { it.toPunchHoleAnchor() }
            .maxByOrNull { it.confidence }
    }

    private fun isTopFeatureDarkPixel(color: Int): Boolean {
        if (Color.alpha(color) < 180) {
            return false
        }
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val luminance = red * 0.299f + green * 0.587f + blue * 0.114f
        val maxChannel = maxOf(red, green, blue)
        val minChannel = minOf(red, green, blue)
        return luminance < TOP_FEATURE_DARK_LUMINANCE && (maxChannel - minChannel) < TOP_FEATURE_MAX_CHANNEL_SPREAD
    }

    private fun applyScreenMask(
        contentCanvas: Canvas,
        outputSize: OutputSize,
        geometry: CompositionGeometry,
        screenMaskBitmap: Bitmap?,
        template: ShellTemplate,
    ) {
        val layerBounds = RectF(0f, 0f, outputSize.width.toFloat(), outputSize.height.toFloat())
        val maskPaint = createDstInPaint()
        val maskBitmap = screenMaskBitmap?.let {
            if (template.alphaTighten) {
                tightenMaskAlpha(
                    source = it,
                    lowThreshold = template.alphaLowThreshold,
                    highThreshold = template.alphaHighThreshold,
                )
            } else {
                it
            }
        }

        try {
            if (maskBitmap != null) {
                val paint = if (hasMeaningfulAlpha(maskBitmap)) createAlphaMaskBitmapPaint() else createLuminanceMaskBitmapPaint()
                val bleed = (template.maskBleedPx * geometryScale(template, outputSize)).coerceAtLeast(0f)
                contentCanvas.drawBitmap(maskBitmap, null, layerBounds.outset(bleed), paint)
            } else {
                contentCanvas.drawPath(geometry.screenPath, maskPaint)
            }
        } finally {
            if (maskBitmap != null && maskBitmap !== screenMaskBitmap && !maskBitmap.isRecycled) {
                maskBitmap.recycle()
            }
        }
    }

    private fun applyCutoutMask(
        contentCanvas: Canvas,
        cutout: ResolvedCutout,
    ) {
        val paint = createDstOutPaint()
        cutout.path?.let {
            contentCanvas.drawPath(it, paint)
            if (cutout.bleedPx > 0f) {
                contentCanvas.drawPath(
                    it,
                    Paint(Paint.ANTI_ALIAS_FLAG).apply {
                        color = Color.WHITE
                        style = Paint.Style.STROKE
                        strokeWidth = cutout.bleedPx * 2f
                        strokeJoin = Paint.Join.ROUND
                        strokeCap = Paint.Cap.ROUND
                        blendMode = BlendMode.DST_OUT
                    },
                )
            }
        }
        cutout.rect?.let { contentCanvas.drawRoundRect(it, it.height() / 2f, it.height() / 2f, paint) }
    }

    private fun tightenMaskAlpha(
        source: Bitmap,
        lowThreshold: Int,
        highThreshold: Int,
    ): Bitmap {
        val low = lowThreshold.coerceIn(0, 255)
        val high = highThreshold.coerceIn(low + 1, 255)
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        source.getPixels(pixels, 0, width, 0, 0, width, height)

        for (index in pixels.indices) {
            val color = pixels[index]
            val alpha = if (source.hasAlpha()) {
                Color.alpha(color)
            } else {
                ((Color.red(color) * 0.299f) + (Color.green(color) * 0.587f) + (Color.blue(color) * 0.114f)).toInt()
            }
            val tightened = when {
                alpha <= low -> 0
                alpha >= high -> 255
                else -> (((alpha - low) / (high - low).toFloat()) * 255f).toInt().coerceIn(0, 255)
            }
            pixels[index] = Color.argb(tightened, 255, 255, 255)
        }

        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }

    private fun parseTemplatePath(
        rawPath: String,
        template: ShellTemplate,
        outputSize: OutputSize,
    ): Path? {
        val parsedPath = runCatching { PathParser.createPathFromPathData(rawPath) }
            .onFailure { throwable ->
                logger.e(TAG, "Failed to parse template path for template ${template.id}", throwable)
            }
            .getOrNull()
            ?: return null

        val transform = Matrix().apply {
            setScale(
                outputSize.width / logicalWidth(template, outputSize).toFloat(),
                outputSize.height / logicalHeight(template, outputSize).toFloat(),
            )
        }
        return Path(parsedPath).apply { transform(transform) }
    }

    private fun drawDebugGuides(
        canvas: Canvas,
        geometry: CompositionGeometry,
        contentDrawRect: RectF,
        sourceTopFeature: TopFeatureAnchor?,
        templateTopFeature: TopFeatureAnchor?,
        effectiveTopFeature: TopFeatureAnchor?,
        sourceBitmap: Bitmap,
    ) {
        val screenPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(220, 0, 122, 255)
            style = Paint.Style.STROKE
            strokeWidth = 3f
        }
        val contentPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(220, 52, 199, 89)
            style = Paint.Style.STROKE
            strokeWidth = 3f
        }
        val cutoutPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(230, 255, 59, 48)
            style = Paint.Style.STROKE
            strokeWidth = 3f
        }
        val sourceFeaturePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(230, 175, 82, 222)
            style = Paint.Style.STROKE
            strokeWidth = 4f
        }
        val templateFeaturePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(230, 255, 204, 0)
            style = Paint.Style.STROKE
            strokeWidth = 4f
        }
        val effectiveFeaturePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = Color.argb(240, 90, 200, 250)
            style = Paint.Style.STROKE
            strokeWidth = 2f
        }

        canvas.drawRect(geometry.screenRect, screenPaint)
        canvas.drawPath(geometry.screenPath, screenPaint)
        canvas.drawRect(contentDrawRect, contentPaint)
        sourceTopFeature
            ?.mapFromSourceToOutput(contentDrawRect, sourceBitmap)
            ?.let { drawTopFeatureGuide(canvas, it, sourceFeaturePaint) }
        templateTopFeature?.let { drawTopFeatureGuide(canvas, it, templateFeaturePaint) }
        effectiveTopFeature
            ?.let { feature ->
                if (feature === sourceTopFeature) {
                    feature.mapFromSourceToOutput(contentDrawRect, sourceBitmap)
                } else {
                    feature
                }
            }
            ?.let { drawTopFeatureGuide(canvas, it, effectiveFeaturePaint) }
        geometry.cutouts.forEach { cutout ->
            cutout.path?.let { canvas.drawPath(it, cutoutPaint) }
            cutout.rect?.let { canvas.drawRect(it, cutoutPaint) }
        }
    }

    private fun drawTopFeatureGuide(
        canvas: Canvas,
        feature: TopFeatureAnchor,
        paint: Paint,
    ) {
        val rect = feature.toRectF()
        when (feature.type) {
            TopFeatureType.ISLAND -> canvas.drawRoundRect(rect, rect.height() / 2f, rect.height() / 2f, paint)
            TopFeatureType.PUNCH_HOLE -> canvas.drawOval(rect, paint)
            TopFeatureType.NONE -> canvas.drawRect(rect, paint)
        }
        canvas.drawCircle(feature.centerX, feature.centerY, 5f, paint)
    }

    private fun TopFeatureAnchor.scaledToOutput(
        template: ShellTemplate,
        outputSize: OutputSize,
    ): TopFeatureAnchor {
        val scaleX = outputSize.width / logicalWidth(template, outputSize).toFloat()
        val scaleY = outputSize.height / logicalHeight(template, outputSize).toFloat()
        return TopFeatureAnchor(
            type = type,
            centerX = centerX * scaleX,
            centerY = centerY * scaleY,
            width = width * scaleX,
            height = height * scaleY,
            confidence = confidence,
        )
    }

    private fun ResolvedCutout.isTopFeatureCutout(screenRect: RectF): Boolean {
        val bounds = RectF()
        when {
            rect != null -> bounds.set(rect)
            path != null -> path.computeBounds(bounds, true)
            else -> return false
        }
        val topBandBottom = screenRect.top + screenRect.height() * TOP_FEATURE_CUTOUT_BAND_RATIO
        return bounds.centerY() <= topBandBottom && bounds.centerX() in screenRect.left..screenRect.right
    }

    private fun drawScreenshotCover(
        canvas: Canvas,
        sourceBitmap: Bitmap,
        template: ShellTemplate,
        outputSize: OutputSize,
        screenMaskBitmap: Bitmap?,
    ) {
        val effectiveScreenRect = resolveEffectiveScreenRect(
            template = template,
            outputSize = outputSize,
            screenMaskBitmap = screenMaskBitmap,
        )
        val screenRect = effectiveScreenRect.toRectF()
        val coverRect = CompositionLayoutCalculator.calculateCoverRect(
            sourceWidth = sourceBitmap.width,
            sourceHeight = sourceBitmap.height,
            targetRect = effectiveScreenRect,
            scaleMode = template.scaleMode,
        ).expanded(resolveMaskBleedPx(template, outputSize))

        val bitmapPaint = createBitmapPaint()
        val pathMask = buildScreenMaskPath(
            template = template,
            outputSize = outputSize,
        ) ?: buildRoundedRectPath(
            screenRect = screenRect,
            cornerRadius = template.cornerRadius,
        )

        when {
            // Bitmap mask is the highest-priority option because it can capture arbitrary
            // irregular screen silhouettes while staying aligned to the template coordinate space.
            screenMaskBitmap != null -> applyMaskBitmap(
                canvas = canvas,
                sourceBitmap = sourceBitmap,
                coverRect = coverRect,
                maskBitmap = screenMaskBitmap,
                bitmapPaint = bitmapPaint,
                outputSize = outputSize,
            )

            // Path mask is the next best option and still lets templates express precise cutouts
            // without falling back to runtime image analysis.
            else -> applyPathMask(
                canvas = canvas,
                sourceBitmap = sourceBitmap,
                coverRect = coverRect,
                maskPath = pathMask,
                bitmapPaint = bitmapPaint,
                outputSize = outputSize,
            )
        }
    }

    private fun applyMaskBitmap(
        canvas: Canvas,
        sourceBitmap: Bitmap,
        coverRect: RectF,
        maskBitmap: Bitmap,
        bitmapPaint: Paint,
        outputSize: OutputSize,
    ) {
        val layerBounds = RectF(0f, 0f, outputSize.width.toFloat(), outputSize.height.toFloat())
        val saveCount = canvas.saveLayer(layerBounds, null)
        val maskPaint = if (hasMeaningfulAlpha(maskBitmap)) {
            createAlphaMaskBitmapPaint()
        } else {
            createLuminanceMaskBitmapPaint()
        }

        // Draw the screenshot cover first, then keep only the mask-visible region.
        // Prefer the real alpha channel when available, then fall back to luminance-based masks
        // for older hard-edged template assets.
        canvas.drawBitmap(sourceBitmap, null, coverRect, bitmapPaint)
        canvas.drawBitmap(
            maskBitmap,
            null,
            layerBounds,
            maskPaint,
        )

        canvas.restoreToCount(saveCount)
    }

    private fun applyPathMask(
        canvas: Canvas,
        sourceBitmap: Bitmap,
        coverRect: RectF,
        maskPath: Path,
        bitmapPaint: Paint,
        outputSize: OutputSize,
    ) {
        val layerBounds = RectF(0f, 0f, outputSize.width.toFloat(), outputSize.height.toFloat())
        val saveCount = canvas.saveLayer(layerBounds, null)
        val maskBitmap = renderPathMaskBitmap(
            outputSize = outputSize,
            maskPath = maskPath,
        )

        try {
            canvas.drawBitmap(sourceBitmap, null, coverRect, bitmapPaint)
            canvas.drawBitmap(
                maskBitmap,
                null,
                layerBounds,
                createAlphaMaskBitmapPaint(),
            )
            canvas.restoreToCount(saveCount)
        } finally {
            if (!maskBitmap.isRecycled) {
                maskBitmap.recycle()
            }
        }
    }

    private fun buildRoundedRectPath(
        screenRect: RectF,
        cornerRadius: Float,
    ): Path = Path().apply {
        addRoundRect(
            screenRect,
            cornerRadius,
            cornerRadius,
            Path.Direction.CW,
        )
    }

    private fun buildScreenMaskPath(
        template: ShellTemplate,
        outputSize: OutputSize,
    ): Path? {
        val rawPath = template.screenMaskPath?.takeIf { it.isNotBlank() } ?: return null
        val parsedPath = runCatching { PathParser.createPathFromPathData(rawPath) }
            .onFailure { throwable ->
                logger.e(TAG, "Failed to parse screen mask path for template ${template.id}", throwable)
            }
            .getOrNull()
            ?: return null

        val designWidth = template.outputWidth.takeIf { it > 0 } ?: outputSize.width
        val designHeight = template.outputHeight.takeIf { it > 0 } ?: outputSize.height

        if (designWidth == outputSize.width && designHeight == outputSize.height) {
            return parsedPath
        }

        val scaledPath = Path(parsedPath)
        val transform = Matrix().apply {
            setScale(
                outputSize.width / designWidth.toFloat(),
                outputSize.height / designHeight.toFloat(),
            )
        }
        scaledPath.transform(transform)
        return scaledPath
    }

    private fun drawFrameOverlay(
        canvas: Canvas,
        frameBitmap: Bitmap,
        outputSize: OutputSize,
        templateTopFeature: TopFeatureAnchor? = null,
        sourceTopFeature: TopFeatureAnchor? = null,
        contentDrawRect: RectF? = null,
        sourceBitmap: Bitmap? = null,
    ) {
        val sourceHasTopFeature = sourceTopFeature?.type == TopFeatureType.ISLAND ||
            sourceTopFeature?.type == TopFeatureType.PUNCH_HOLE
        if (sourceHasTopFeature && templateTopFeature != null && contentDrawRect != null && sourceBitmap != null) {
            val frameLayer = Bitmap.createBitmap(
                outputSize.width,
                outputSize.height,
                Bitmap.Config.ARGB_8888,
            )
            val frameCanvas = Canvas(frameLayer)
            try {
                frameCanvas.drawBitmap(
                    frameBitmap,
                    null,
                    RectF(0f, 0f, outputSize.width.toFloat(), outputSize.height.toFloat()),
                    createBitmapPaint(),
                )
                val sourceFeatureInOutput = sourceTopFeature.mapFromSourceToOutput(
                    contentDrawRect = contentDrawRect,
                    sourceBitmap = sourceBitmap,
                )
                clearTemplateTopFeatureOverlay(
                    canvas = frameCanvas,
                    templateTopFeature = templateTopFeature,
                    sourceTopFeatureInOutput = sourceFeatureInOutput,
                )
                canvas.drawBitmap(frameLayer, 0f, 0f, null)
            } finally {
                if (!frameLayer.isRecycled) {
                    frameLayer.recycle()
                }
            }
            return
        }

        // Frame is always drawn last so bezel, cutout, and hardware details sit above the screenshot layer.
        canvas.drawBitmap(
            frameBitmap,
            null,
            RectF(0f, 0f, outputSize.width.toFloat(), outputSize.height.toFloat()),
            createBitmapPaint(),
        )
    }

    private fun clearTemplateTopFeatureOverlay(
        canvas: Canvas,
        templateTopFeature: TopFeatureAnchor,
        sourceTopFeatureInOutput: TopFeatureAnchor,
    ) {
        val clearPaint = Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            blendMode = BlendMode.CLEAR
            style = Paint.Style.FILL
        }
        val clearRect = templateTopFeature
            .unionWith(sourceTopFeatureInOutput)
            .outset(TOP_FEATURE_FRAME_CLEAR_BLEED_PX)
            .alignToPixel()
        val radius = when (sourceTopFeatureInOutput.type) {
            TopFeatureType.ISLAND -> clearRect.height() / 2f
            TopFeatureType.PUNCH_HOLE -> maxOf(clearRect.width(), clearRect.height()) / 2f
            TopFeatureType.NONE -> clearRect.height() / 2f
        }
        canvas.drawRoundRect(clearRect, radius, radius, clearPaint)
    }

    private fun resolveOutputSize(
        template: ShellTemplate,
        frameBitmap: Bitmap,
    ): OutputSize {
        val width = template.outputWidth.takeIf { it > 0 } ?: frameBitmap.width
        val height = template.outputHeight.takeIf { it > 0 } ?: frameBitmap.height
        return OutputSize(width = width, height = height)
    }

    private fun logicalWidth(template: ShellTemplate, outputSize: OutputSize): Int {
        return template.logicalWidth.takeIf { it > 0 }
            ?: template.outputWidth.takeIf { it > 0 }
            ?: outputSize.width
    }

    private fun logicalHeight(template: ShellTemplate, outputSize: OutputSize): Int {
        return template.logicalHeight.takeIf { it > 0 }
            ?: template.outputHeight.takeIf { it > 0 }
            ?: outputSize.height
    }

    private fun geometryScale(template: ShellTemplate, outputSize: OutputSize): Float {
        val scaleX = outputSize.width / logicalWidth(template, outputSize).toFloat()
        val scaleY = outputSize.height / logicalHeight(template, outputSize).toFloat()
        return (scaleX + scaleY) / 2f
    }

    private fun resolveEffectiveScreenRect(
        template: ShellTemplate,
        outputSize: OutputSize,
        screenMaskBitmap: Bitmap?,
    ): ScreenRect {
        val explicitRect = template.screenRect
            .scaledTo(
                outputWidth = outputSize.width,
                outputHeight = outputSize.height,
                template = template,
            )
            .inset(template.screenInsetPx)
            .coerceWithin(
                outputWidth = outputSize.width,
                outputHeight = outputSize.height,
            )
        if (explicitRect.isValid()) {
            return explicitRect
        }

        val maskBounds = screenMaskBitmap?.let {
            detectBitmapMaskBounds(
                maskBitmap = it,
                outputSize = outputSize,
            )?.inset(template.screenInsetPx)?.coerceWithin(
                outputWidth = outputSize.width,
                outputHeight = outputSize.height,
            )
        }
        return maskBounds ?: ScreenRect(
            left = 0,
            top = 0,
            right = outputSize.width,
            bottom = outputSize.height,
        )
    }

    private fun detectBitmapMaskBounds(
        maskBitmap: Bitmap,
        outputSize: OutputSize,
    ): ScreenRect? {
        val width = maskBitmap.width
        val height = maskBitmap.height
        if (width <= 0 || height <= 0) {
            return null
        }

        val pixels = IntArray(width * height)
        maskBitmap.getPixels(pixels, 0, width, 0, 0, width, height)

        var minX = width
        var minY = height
        var maxX = -1
        var maxY = -1

        for (index in pixels.indices) {
            val color = pixels[index]
            val visible = color.alpha > 32 || (
                Color.red(color) >= 128 &&
                    Color.green(color) >= 128 &&
                    Color.blue(color) >= 128
                )
            if (!visible) {
                continue
            }

            val x = index % width
            val y = index / width
            if (x < minX) minX = x
            if (y < minY) minY = y
            if (x > maxX) maxX = x
            if (y > maxY) maxY = y
        }

        if (maxX < minX || maxY < minY) {
            return null
        }

        val scaleX = outputSize.width / width.toFloat()
        val scaleY = outputSize.height / height.toFloat()

        return ScreenRect(
            left = (minX * scaleX).toInt(),
            top = (minY * scaleY).toInt(),
            right = kotlin.math.ceil((maxX + 1) * scaleX).toInt(),
            bottom = kotlin.math.ceil((maxY + 1) * scaleY).toInt(),
        )
    }

    private fun createBitmapPaint(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG).apply {
            isFilterBitmap = true
            isDither = true
        }
    }

    private fun createAlphaMaskBitmapPaint(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG).apply {
            blendMode = BlendMode.DST_IN
            isFilterBitmap = true
            isDither = true
        }
    }

    private fun createLuminanceMaskBitmapPaint(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG or Paint.FILTER_BITMAP_FLAG or Paint.DITHER_FLAG).apply {
            blendMode = BlendMode.DST_IN
            colorFilter = ColorMatrixColorFilter(LUMINANCE_TO_ALPHA_MATRIX)
            isFilterBitmap = true
            isDither = true
        }
    }

    private fun createSolidMaskPaint(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.FILL
            isDither = true
        }
    }

    private fun createDstInPaint(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.FILL
            blendMode = BlendMode.DST_IN
            isDither = true
        }
    }

    private fun createDstOutPaint(): Paint {
        return Paint(Paint.ANTI_ALIAS_FLAG or Paint.DITHER_FLAG).apply {
            color = Color.WHITE
            style = Paint.Style.FILL
            blendMode = BlendMode.DST_OUT
            isDither = true
        }
    }

    private fun renderPathMaskBitmap(
        outputSize: OutputSize,
        maskPath: Path,
    ): Bitmap {
        val maskBitmap = Bitmap.createBitmap(
            outputSize.width,
            outputSize.height,
            Bitmap.Config.ARGB_8888,
        )
        val maskCanvas = Canvas(maskBitmap)
        maskCanvas.drawColor(Color.TRANSPARENT)
        maskCanvas.drawPath(maskPath, createSolidMaskPaint())
        return maskBitmap
    }

    private fun hasMeaningfulAlpha(bitmap: Bitmap): Boolean {
        if (!bitmap.hasAlpha()) {
            return false
        }

        val width = bitmap.width
        val height = bitmap.height
        if (width <= 0 || height <= 0) {
            return false
        }

        val sampleStepX = maxOf(1, width / MASK_ALPHA_SAMPLE_GRID)
        val sampleStepY = maxOf(1, height / MASK_ALPHA_SAMPLE_GRID)

        var translucentCount = 0
        var sampleCount = 0
        var y = 0
        while (y < height) {
            var x = 0
            while (x < width) {
                val alpha = bitmap.getPixel(x, y).alpha
                if (alpha in 8..247) {
                    translucentCount += 1
                    if (translucentCount >= MIN_MEANINGFUL_ALPHA_SAMPLES) {
                        return true
                    }
                }
                sampleCount += 1
                x += sampleStepX
            }
            y += sampleStepY
        }

        return sampleCount > 0 && translucentCount > 0
    }

    private fun resolveMaskBleedPx(
        template: ShellTemplate,
        outputSize: OutputSize,
    ): Float {
        val baseBleed = (minOf(outputSize.width, outputSize.height) * DEFAULT_MASK_BLEED_RATIO)
            .coerceIn(MIN_MASK_BLEED_PX, MAX_MASK_BLEED_PX)
        return (baseBleed + template.maskBleedPx).coerceAtLeast(0f)
    }

    private fun parseColor(raw: String): Int {
        return runCatching { Color.parseColor(raw) }.getOrDefault(Color.TRANSPARENT)
    }

    private data class OutputSize(
        val width: Int,
        val height: Int,
    )

    private data class CompositionGeometry(
        val screenRect: RectF,
        val screenPath: Path,
        val cutouts: List<ResolvedCutout>,
        val scale: Float,
    )

    private data class ResolvedCutout(
        val path: Path?,
        val rect: RectF?,
        val bleedPx: Float,
    )

    private data class TopFeatureComponent(
        val left: Int,
        val top: Int,
        val right: Int,
        val bottom: Int,
        val pixelCount: Int,
        val sourceWidth: Int,
        val sourceScanHeight: Int,
    ) {
        private val width: Float get() = (right - left).toFloat()
        private val height: Float get() = (bottom - top).toFloat()
        private val centerX: Float get() = (left + right) / 2f
        private val centerY: Float get() = (top + bottom) / 2f
        private val aspect: Float get() = width / height.coerceAtLeast(1f)
        private val fillRatio: Float get() = pixelCount / (width * height).coerceAtLeast(1f)
        private val centerScore: Float
            get() = (1f - (kotlin.math.abs(centerX - sourceWidth / 2f) / (sourceWidth / 2f))).coerceIn(0f, 1f)
        private val topScore: Float
            get() = (1f - (centerY / sourceScanHeight.toFloat())).coerceIn(0f, 1f)

        fun toIslandAnchor(): TopFeatureAnchor? {
            val minWidth = sourceWidth * 0.08f
            val maxWidth = sourceWidth * 0.48f
            val maxHeight = sourceScanHeight * 0.70f
            if (width !in minWidth..maxWidth) return null
            if (height !in TOP_FEATURE_MIN_SIZE_PX.toFloat()..maxHeight) return null
            if (aspect < 2.2f || aspect > 9f) return null
            if (fillRatio < 0.35f) return null
            if (centerScore < 0.45f) return null

            val confidence = (0.45f + centerScore * 0.35f + topScore * 0.10f + fillRatio.coerceAtMost(1f) * 0.10f)
                .coerceIn(0f, 1f)
            return TopFeatureAnchor(
                type = TopFeatureType.ISLAND,
                centerX = centerX,
                centerY = centerY,
                width = width,
                height = height,
                confidence = confidence,
            )
        }

        fun toPunchHoleAnchor(): TopFeatureAnchor? {
            val minSize = TOP_FEATURE_MIN_SIZE_PX.toFloat()
            val maxSize = minOf(sourceWidth * 0.12f, sourceScanHeight * 0.75f)
            if (width !in minSize..maxSize || height !in minSize..maxSize) return null
            if (aspect !in 0.55f..1.85f) return null
            if (fillRatio < 0.30f) return null
            if (centerScore < 0.40f) return null

            val confidence = (0.40f + centerScore * 0.35f + topScore * 0.10f + fillRatio.coerceAtMost(1f) * 0.15f)
                .coerceIn(0f, 1f)
            return TopFeatureAnchor(
                type = TopFeatureType.PUNCH_HOLE,
                centerX = centerX,
                centerY = centerY,
                width = maxOf(width, height),
                height = maxOf(width, height),
                confidence = confidence,
            )
        }
    }

    private companion object {
        const val TAG = "ShellComposer"
        const val MASK_ALPHA_SAMPLE_GRID = 24
        const val MIN_MEANINGFUL_ALPHA_SAMPLES = 3
        const val TOP_FEATURE_SCAN_HEIGHT_RATIO = 0.16f
        const val TOP_FEATURE_CUTOUT_BAND_RATIO = 0.18f
        const val TOP_FEATURE_MIN_SIZE_PX = 8
        const val TOP_FEATURE_DARK_LUMINANCE = 72f
        const val TOP_FEATURE_MAX_CHANNEL_SPREAD = 42
        const val TOP_FEATURE_FRAME_CLEAR_BLEED_PX = 3f
        const val DEFAULT_MASK_BLEED_RATIO = 0.0012f
        const val MIN_MASK_BLEED_PX = 1f
        const val MAX_MASK_BLEED_PX = 2f
        val LUMINANCE_TO_ALPHA_MATRIX = ColorMatrix(
            floatArrayOf(
                0f, 0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f, 0f,
                0f, 0f, 0f, 0f, 0f,
                0.299f, 0.587f, 0.114f, 0f, 0f,
            ),
        )
    }
}

object CompositionLayoutCalculator {
    fun calculateCoverRect(
        sourceWidth: Int,
        sourceHeight: Int,
        targetRect: ScreenRect,
        scaleMode: ScaleMode,
    ): RectF {
        val targetWidth = targetRect.width.toFloat()
        val targetHeight = targetRect.height.toFloat()

        // CENTER_CROP is the template "cover" mode: keep aspect ratio, fill the target, crop overflow.
        val scale = when (scaleMode) {
            ScaleMode.FIT_CENTER -> minOf(targetWidth / sourceWidth, targetHeight / sourceHeight)
            ScaleMode.CENTER_CROP -> maxOf(targetWidth / sourceWidth, targetHeight / sourceHeight)
        }

        val scaledWidth = sourceWidth * scale
        val scaledHeight = sourceHeight * scale
        val left = targetRect.left + ((targetWidth - scaledWidth) / 2f)
        val top = targetRect.top + ((targetHeight - scaledHeight) / 2f)

        return RectF(
            left,
            top,
            left + scaledWidth,
            top + scaledHeight,
        )
    }

    fun calculateDestinationRect(
        sourceWidth: Int,
        sourceHeight: Int,
        targetRect: ScreenRect,
        scaleMode: ScaleMode,
    ): RectF {
        return calculateCoverRect(
            sourceWidth = sourceWidth,
            sourceHeight = sourceHeight,
            targetRect = targetRect,
            scaleMode = scaleMode,
        )
    }
}

private fun ScreenRect.toRectF(): RectF {
    return RectF(
        left.toFloat(),
        top.toFloat(),
        right.toFloat(),
        bottom.toFloat(),
    )
}

private fun ScreenRect.scaledTo(
    outputWidth: Int,
    outputHeight: Int,
    template: ShellTemplate,
): ScreenRect {
    val designWidth = template.outputWidth.takeIf { it > 0 } ?: outputWidth
    val designHeight = template.outputHeight.takeIf { it > 0 } ?: outputHeight
    if (designWidth == outputWidth && designHeight == outputHeight) {
        return this
    }

    val scaleX = outputWidth / designWidth.toFloat()
    val scaleY = outputHeight / designHeight.toFloat()
    return ScreenRect(
        left = kotlin.math.round(left * scaleX).toInt(),
        top = kotlin.math.round(top * scaleY).toInt(),
        right = kotlin.math.round(right * scaleX).toInt(),
        bottom = kotlin.math.round(bottom * scaleY).toInt(),
    )
}

private fun ScreenRect.scaledToOutput(
    outputWidth: Int,
    outputHeight: Int,
    template: ShellTemplate,
): ScreenRect {
    val designWidth = template.logicalWidthFor(OutputSizeCompat(outputWidth, outputHeight))
    val designHeight = template.logicalHeightFor(OutputSizeCompat(outputWidth, outputHeight))
    if (designWidth == outputWidth && designHeight == outputHeight) {
        return this
    }

    val scaleX = outputWidth / designWidth.toFloat()
    val scaleY = outputHeight / designHeight.toFloat()
    return ScreenRect(
        left = kotlin.math.round(left * scaleX).toInt(),
        top = kotlin.math.round(top * scaleY).toInt(),
        right = kotlin.math.round(right * scaleX).toInt(),
        bottom = kotlin.math.round(bottom * scaleY).toInt(),
    )
}

private fun ScreenRect.inset(delta: Float): ScreenRect {
    if (delta == 0f) {
        return this
    }

    val rounded = kotlin.math.round(delta).toInt()
    return ScreenRect(
        left = left + rounded,
        top = top + rounded,
        right = right - rounded,
        bottom = bottom - rounded,
    )
}

private fun ScreenRect.coerceWithin(outputWidth: Int, outputHeight: Int): ScreenRect {
    val safeLeft = left.coerceIn(0, outputWidth)
    val safeTop = top.coerceIn(0, outputHeight)
    val safeRight = right.coerceIn(safeLeft, outputWidth)
    val safeBottom = bottom.coerceIn(safeTop, outputHeight)
    return ScreenRect(
        left = safeLeft,
        top = safeTop,
        right = safeRight,
        bottom = safeBottom,
    )
}

private fun ScreenRect.isValid(): Boolean {
    return width > 0 && height > 0
}

private fun RectF.expanded(delta: Float): RectF {
    if (delta <= 0f) {
        return this
    }
    return RectF(
        left - delta,
        top - delta,
        right + delta,
        bottom + delta,
    )
}

private fun RectF.outset(delta: Float): RectF {
    if (delta == 0f) {
        return RectF(this)
    }
    return RectF(left - delta, top - delta, right + delta, bottom + delta)
}

private fun RectF.alignToPixel(): RectF {
    return RectF(
        kotlin.math.floor(left).toFloat(),
        kotlin.math.floor(top).toFloat(),
        kotlin.math.ceil(right).toFloat(),
        kotlin.math.ceil(bottom).toFloat(),
    )
}

private fun RectF.clampToCover(target: RectF): RectF {
    val result = RectF(this)
    var dx = 0f
    var dy = 0f

    if (result.left > target.left) {
        dx = target.left - result.left
    } else if (result.right < target.right) {
        dx = target.right - result.right
    }

    if (result.top > target.top) {
        dy = target.top - result.top
    } else if (result.bottom < target.bottom) {
        dy = target.bottom - result.bottom
    }

    result.offset(dx, dy)
    return result
}

private fun TopFeatureAnchor.mapFromSourceToOutput(
    contentDrawRect: RectF,
    sourceBitmap: Bitmap,
): TopFeatureAnchor {
    val scaleX = contentDrawRect.width() / sourceBitmap.width.toFloat()
    val scaleY = contentDrawRect.height() / sourceBitmap.height.toFloat()
    return TopFeatureAnchor(
        type = type,
        centerX = contentDrawRect.left + centerX * scaleX,
        centerY = contentDrawRect.top + centerY * scaleY,
        width = width * scaleX,
        height = height * scaleY,
        confidence = confidence,
    )
}

private fun TopFeatureAnchor.toRectF(): RectF {
    return RectF(
        centerX - width / 2f,
        centerY - height / 2f,
        centerX + width / 2f,
        centerY + height / 2f,
    )
}

private fun TopFeatureAnchor.unionWith(other: TopFeatureAnchor): RectF {
    val first = toRectF()
    val second = other.toRectF()
    first.union(second)
    return first
}

private data class OutputSizeCompat(
    val width: Int,
    val height: Int,
)

private fun ShellTemplate.logicalWidthFor(outputSize: OutputSizeCompat): Int {
    return logicalWidth.takeIf { it > 0 }
        ?: outputWidth.takeIf { it > 0 }
        ?: outputSize.width
}

private fun ShellTemplate.logicalHeightFor(outputSize: OutputSizeCompat): Int {
    return logicalHeight.takeIf { it > 0 }
        ?: outputHeight.takeIf { it > 0 }
        ?: outputSize.height
}
