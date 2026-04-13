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
import com.example.shellshot.template.ScaleMode
import com.example.shellshot.template.ScreenRect
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.TemplateRepository
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

        drawScreenshotCover(
            canvas = canvas,
            sourceBitmap = sourceBitmap,
            template = template,
            outputSize = outputSize,
            screenMaskBitmap = screenMaskBitmap,
        )
        drawFrameOverlay(
            canvas = canvas,
            frameBitmap = frameBitmap,
            outputSize = outputSize,
        )
        return outputBitmap
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
    ) {
        // Frame is always drawn last so bezel, cutout, and hardware details sit above the screenshot layer.
        canvas.drawBitmap(
            frameBitmap,
            null,
            RectF(0f, 0f, outputSize.width.toFloat(), outputSize.height.toFloat()),
            createBitmapPaint(),
        )
    }

    private fun resolveOutputSize(
        template: ShellTemplate,
        frameBitmap: Bitmap,
    ): OutputSize {
        val width = template.outputWidth.takeIf { it > 0 } ?: frameBitmap.width
        val height = template.outputHeight.takeIf { it > 0 } ?: frameBitmap.height
        return OutputSize(width = width, height = height)
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

    private companion object {
        const val TAG = "ShellComposer"
        const val MASK_ALPHA_SAMPLE_GRID = 24
        const val MIN_MEANINGFUL_ALPHA_SAMPLES = 3
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
