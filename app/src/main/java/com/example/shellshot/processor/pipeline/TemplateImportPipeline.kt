package com.example.shellshot.processor.pipeline

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.RectF
import com.example.shellshot.template.ScreenGeometryDerived
import com.example.shellshot.template.ScreenRect
import com.example.shellshot.template.StatusBarSafeZones
import com.example.shellshot.template.TopFeatureType
import java.util.ArrayDeque
import kotlin.math.abs
import kotlin.math.hypot
import kotlin.math.roundToInt

class TemplateImportPipeline {
    private val purifier = TemplatePurifier()
    private val analyzer = TemplateAnalyzer()
    private val fitter = FeatureFitter()
    private val validator = TemplateValidator()

    /**
     * 执行完整的纯化与检测流水线
     */
    fun processTemplate(
        templateId: String,
        templateName: String,
        originalFrame: Bitmap,
        providedMask: Bitmap? = null,
    ): ImportedTemplate {
        val argbFrame = originalFrame.copy(Bitmap.Config.ARGB_8888, false)

        // 1. 先得到 screen_mask；frame 净化必须依赖 mask 限定区域，禁止全图修边。
        val rawMask = providedMask ?: analyzer.detectScreenMaskViaFloodFill(argbFrame)
        val tightenedMask = purifier.applyAlphaTighten(rawMask, low = DEFAULT_ALPHA_LOW, high = DEFAULT_ALPHA_HIGH)
        val smoothMask = purifier.applyMorphologicalSmoothing(tightenedMask)
        
        // 2. 提取 screen rect，并拟合顶部结构。
        val screenRect = analyzer.extractScreenRectFromMask(smoothMask)
        val fittedFeature = fitter.detectAndFitTopFeature(smoothMask, screenRect)
        val derivedGeometry = deriveScreenGeometry(screenRect, fittedFeature, smoothMask.width, smoothMask.height)
        
        // 3. 区域化净化 frame：只处理屏幕开口/top suppression 邻域，保留设备外阴影/外发光。
        val cleanFrame = purifier.cleanFrameAroundScreenOpening(
            source = argbFrame,
            mask = smoothMask,
            screenRect = screenRect,
            topSuppressionRect = derivedGeometry.topSuppressionRect,
        )
        val topLayers = purifier.splitTopLayers(
            source = cleanFrame,
            topHoleOverlayRect = derivedGeometry.topHoleOverlayRect,
        )
        
        // 4. 校验与评分。
        val purityReport = validator.generatePurityReport(
            originalFrame = originalFrame,
            cleanFrame = cleanFrame,
            mask = smoothMask,
            rect = screenRect,
            topFeature = fittedFeature,
        )
        if (!purityReport.isAccepted) {
            error("模板未通过净化校验流水线，已被拒绝: ${purityReport.warnings}")
        }
        
        return ImportedTemplate(
            id = templateId,
            name = templateName,
            frameBitmap = cleanFrame,
            frameBaseBitmap = topLayers.frameBase,
            topHoleOverlayBitmap = topLayers.topHoleOverlay,
            maskBitmap = smoothMask,
            screenRect = derivedGeometry.visibleBounds,
            geometryDerived = derivedGeometry,
            topFeature = fittedFeature,
            purityReport = purityReport,
        )
    }

    private fun deriveScreenGeometry(
        visibleBounds: ScreenRect,
        topFeature: FittedFeature?,
        canvasWidth: Int,
        canvasHeight: Int,
    ): ScreenGeometryDerived {
        val leftInset = 1
        val topInset = 1
        val rightInset = 2
        val bottomInset = 2
        val topHoleOverlayRect = topFeature?.bounds?.let { bounds ->
            val overlayWidth = when (topFeature.type) {
                TopFeatureType.PUNCH_HOLE -> bounds.width() * 1.75f
                TopFeatureType.ISLAND -> bounds.width() * 1.22f
                TopFeatureType.NONE -> bounds.width()
            }
            val overlayHeight = when (topFeature.type) {
                TopFeatureType.PUNCH_HOLE -> bounds.height() * 1.75f
                TopFeatureType.ISLAND -> bounds.height() * 1.30f
                TopFeatureType.NONE -> bounds.height()
            }
            val cx = bounds.centerX()
            val cy = bounds.centerY()
            ScreenRect(
                left = (cx - overlayWidth / 2f).roundToInt(),
                top = (cy - overlayHeight / 2f).roundToInt(),
                right = (cx + overlayWidth / 2f).roundToInt(),
                bottom = (cy + overlayHeight / 2f).roundToInt(),
            ).normalizedWithin(canvasWidth, canvasHeight)
        }
        val topFeatureAvoidRect = topHoleOverlayRect?.let { rect ->
            ScreenRect(
                left = rect.left - maxOf(8, visibleBounds.width / 80),
                top = rect.top - 4,
                right = rect.right + maxOf(8, visibleBounds.width / 80),
                bottom = rect.bottom + 4,
            ).normalizedWithin(canvasWidth, canvasHeight)
        }
        val topSuppressionRect = topFeatureAvoidRect
        val protectedBottom = topFeatureAvoidRect?.bottom
            ?: (visibleBounds.top + maxOf(20, (visibleBounds.height * 0.045f).roundToInt()))
        val safeTopBand = ScreenRect(
            left = visibleBounds.left,
            top = visibleBounds.top,
            right = visibleBounds.right,
            bottom = maxOf(protectedBottom + 4, visibleBounds.top + 1).coerceAtMost(visibleBounds.bottom),
        ).normalizedWithin(canvasWidth, canvasHeight)
        val contentClipRect = ScreenRect(
            left = visibleBounds.left + leftInset,
            top = visibleBounds.top + topInset,
            right = visibleBounds.right - rightInset,
            bottom = visibleBounds.bottom - bottomInset,
        ).normalizedWithin(canvasWidth, canvasHeight)
        return ScreenGeometryDerived(
            visibleBounds = visibleBounds.normalizedWithin(canvasWidth, canvasHeight),
            safeTopBand = safeTopBand,
            contentClipRect = contentClipRect,
            topSuppressionRect = topSuppressionRect,
            topHoleOverlayRect = topHoleOverlayRect,
            topFeatureAvoidRect = topFeatureAvoidRect,
            statusBarSafeZones = deriveStatusBarSafeZones(visibleBounds, topFeatureAvoidRect, canvasWidth, canvasHeight),
        )
    }

    private fun deriveStatusBarSafeZones(
        visibleBounds: ScreenRect,
        avoidRect: ScreenRect?,
        canvasWidth: Int,
        canvasHeight: Int,
    ): StatusBarSafeZones? {
        val avoid = avoidRect ?: return null
        val margin = maxOf(6, visibleBounds.width / 70)
        val bandBottom = maxOf(avoid.bottom + margin, visibleBounds.top + visibleBounds.height / 12)
            .coerceAtMost(visibleBounds.bottom)
        val left = ScreenRect(
            left = visibleBounds.left,
            top = visibleBounds.top,
            right = (avoid.left - margin).coerceAtLeast(visibleBounds.left + 1),
            bottom = bandBottom,
        ).normalizedWithin(canvasWidth, canvasHeight)
        val right = ScreenRect(
            left = (avoid.right + margin).coerceAtMost(visibleBounds.right - 1),
            top = visibleBounds.top,
            right = visibleBounds.right,
            bottom = bandBottom,
        ).normalizedWithin(canvasWidth, canvasHeight)
        return StatusBarSafeZones(
            left = left,
            right = right,
            avoid = avoid.normalizedWithin(canvasWidth, canvasHeight),
        )
    }

    private fun ScreenRect.normalizedWithin(width: Int, height: Int): ScreenRect {
        val left = left.coerceAtLeast(0).coerceAtMost(width - 2)
        val top = top.coerceAtLeast(0).coerceAtMost(height - 2)
        val right = right.coerceAtLeast(left + 1).coerceAtMost(width)
        val bottom = bottom.coerceAtLeast(top + 1).coerceAtMost(height)
        return ScreenRect(left = left, top = top, right = right, bottom = bottom)
    }

    private companion object {
        const val DEFAULT_ALPHA_LOW = 32
        const val DEFAULT_ALPHA_HIGH = 208
    }
}

class TemplatePurifier {
    data class TopLayers(
        val frameBase: Bitmap,
        val topHoleOverlay: Bitmap?,
    )

    fun splitTopLayers(source: Bitmap, topHoleOverlayRect: ScreenRect?): TopLayers {
        val frameBase = source.copy(Bitmap.Config.ARGB_8888, true)
        val rect = topHoleOverlayRect ?: return TopLayers(frameBase = frameBase, topHoleOverlay = null)
        val width = source.width
        val height = source.height
        val overlay = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val sourcePixels = IntArray(width * height)
        val basePixels = IntArray(width * height)
        val overlayPixels = IntArray(width * height) { Color.TRANSPARENT }
        source.getPixels(sourcePixels, 0, width, 0, 0, width, height)
        frameBase.getPixels(basePixels, 0, width, 0, 0, width, height)
        for (y in rect.top until rect.bottom) {
            for (x in rect.left until rect.right) {
                val offset = y * width + x
                overlayPixels[offset] = sourcePixels[offset]
                basePixels[offset] = Color.TRANSPARENT
            }
        }
        frameBase.setPixels(basePixels, 0, width, 0, 0, width, height)
        overlay.setPixels(overlayPixels, 0, width, 0, 0, width, height)
        return TopLayers(frameBase = frameBase, topHoleOverlay = overlay)
    }

    fun cleanFrameAroundScreenOpening(
        source: Bitmap,
        mask: Bitmap,
        screenRect: ScreenRect,
        topSuppressionRect: ScreenRect?,
    ): Bitmap {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        val maskPixels = IntArray(mask.width * mask.height)
        source.getPixels(pixels, 0, width, 0, 0, width, height)
        mask.getPixels(maskPixels, 0, mask.width, 0, 0, mask.width, mask.height)

        val cleanBand = expandedRect(screenRect, 8, width, height)
        val topBand = topSuppressionRect?.let { expandedRect(it, 10, width, height) }
        for (y in cleanBand.top until cleanBand.bottom) {
            for (x in cleanBand.left until cleanBand.right) {
                val offset = y * width + x
                if (!isOpeningRelatedPixel(maskPixels, width, height, x, y) && topBand?.contains(x, y) != true) continue
                val alpha = Color.alpha(pixels[offset])
                if (alpha <= CLEAR_ALPHA_THRESHOLD) {
                    pixels[offset] = Color.TRANSPARENT
                } else if (alpha < FRINGE_ALPHA_THRESHOLD && isLikelyWhiteFringe(pixels[offset])) {
                    val ratio = alpha / 255f
                    pixels[offset] = Color.argb(
                        alpha,
                        unmatteWhite(Color.red(pixels[offset]), ratio),
                        unmatteWhite(Color.green(pixels[offset]), ratio),
                        unmatteWhite(Color.blue(pixels[offset]), ratio),
                    )
                }
            }
        }
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }

    fun applyAlphaTighten(source: Bitmap, low: Int, high: Int): Bitmap {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        source.getPixels(pixels, 0, width, 0, 0, width, height)

        for (i in pixels.indices) {
            val color = pixels[i]
            val value = if (source.hasAlpha() && Color.alpha(color) < 255) Color.alpha(color) else Color.red(color)
            val tightened = when {
                value <= low -> 0
                value >= high -> 255
                else -> (((value - low) / (high - low).toFloat()) * 255f).toInt().coerceIn(0, 255)
            }
            pixels[i] = Color.argb(tightened, 255, 255, 255)
        }
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(pixels, 0, width, 0, 0, width, height)
        }
    }

    fun applyMorphologicalSmoothing(source: Bitmap): Bitmap {
        val opened = dilateAlpha(erodeAlpha(source, radius = 1), radius = 1)
        val closed = erodeAlpha(dilateAlpha(opened, radius = 1), radius = 1)
        val expanded = dilateAlpha(closed, radius = 1)
        val blurred = fastBoxBlur(expanded, radius = 1)
        return applyAlphaTighten(blurred, low = 72, high = 184)
    }

    private fun isOpeningRelatedPixel(maskPixels: IntArray, width: Int, height: Int, x: Int, y: Int): Boolean {
        val centerAlpha = Color.alpha(maskPixels[y * width + x])
        if (centerAlpha in 1..254) return true
        for (yy in (y - 2).coerceAtLeast(0)..(y + 2).coerceAtMost(height - 1)) {
            for (xx in (x - 2).coerceAtLeast(0)..(x + 2).coerceAtMost(width - 1)) {
                if (Color.alpha(maskPixels[yy * width + xx]) != centerAlpha) return true
            }
        }
        return false
    }

    private fun expandedRect(rect: ScreenRect, amount: Int, width: Int, height: Int): ScreenRect {
        return ScreenRect(
            left = (rect.left - amount).coerceAtLeast(0),
            top = (rect.top - amount).coerceAtLeast(0),
            right = (rect.right + amount).coerceAtMost(width),
            bottom = (rect.bottom + amount).coerceAtMost(height),
        )
    }

    private fun ScreenRect.contains(x: Int, y: Int): Boolean = x in left until right && y in top until bottom

    private fun isLikelyWhiteFringe(color: Int): Boolean {
        val luminance = Color.red(color) * 0.299f + Color.green(color) * 0.587f + Color.blue(color) * 0.114f
        return luminance > 160f
    }

    private fun erodeAlpha(source: Bitmap, radius: Int): Bitmap = morphAlpha(source, radius, takeMin = true)

    private fun dilateAlpha(source: Bitmap, radius: Int): Bitmap = morphAlpha(source, radius, takeMin = false)

    private fun morphAlpha(source: Bitmap, radius: Int, takeMin: Boolean): Bitmap {
        val width = source.width
        val height = source.height
        val pixels = IntArray(width * height)
        val out = IntArray(width * height)
        source.getPixels(pixels, 0, width, 0, 0, width, height)
        for (y in 0 until height) {
            for (x in 0 until width) {
                var value = if (takeMin) 255 else 0
                for (yy in (y - radius).coerceAtLeast(0)..(y + radius).coerceAtMost(height - 1)) {
                    for (xx in (x - radius).coerceAtLeast(0)..(x + radius).coerceAtMost(width - 1)) {
                        val alpha = Color.alpha(pixels[yy * width + xx])
                        value = if (takeMin) minOf(value, alpha) else maxOf(value, alpha)
                    }
                }
                out[y * width + x] = Color.argb(value, 255, 255, 255)
            }
        }
        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(out, 0, width, 0, 0, width, height)
        }
    }

    private fun fastBoxBlur(source: Bitmap, radius: Int): Bitmap {
        val width = source.width
        val height = source.height
        val output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        val inPixels = IntArray(width * height)
        val outPixels = IntArray(width * height)
        source.getPixels(inPixels, 0, width, 0, 0, width, height)

        val windowSize = radius * 2 + 1
        for (y in 0 until height) {
            var sumA = 0
            for (i in -radius..radius) sumA += Color.alpha(inPixels[y * width + i.coerceIn(0, width - 1)])
            for (x in 0 until width) {
                outPixels[y * width + x] = sumA / windowSize
                val subX = (x - radius).coerceAtLeast(0)
                val addX = (x + radius + 1).coerceAtMost(width - 1)
                sumA += Color.alpha(inPixels[y * width + addX]) - Color.alpha(inPixels[y * width + subX])
            }
        }

        val finalPixels = IntArray(width * height)
        for (x in 0 until width) {
            var sumA = 0
            for (i in -radius..radius) sumA += outPixels[i.coerceIn(0, height - 1) * width + x]
            for (y in 0 until height) {
                val a = sumA / windowSize
                finalPixels[y * width + x] = Color.argb(a, 255, 255, 255)
                val subY = (y - radius).coerceAtLeast(0)
                val addY = (y + radius + 1).coerceAtMost(height - 1)
                sumA += outPixels[addY * width + x] - outPixels[subY * width + x]
            }
        }

        output.setPixels(finalPixels, 0, width, 0, 0, width, height)
        return output
    }

    private fun unmatteWhite(channel: Int, alphaRatio: Float): Int {
        if (alphaRatio <= 0f) return 0
        return ((channel - 255f * (1f - alphaRatio)) / alphaRatio)
            .roundToInt()
            .coerceIn(0, 255)
    }

    private companion object {
        const val CLEAR_ALPHA_THRESHOLD = 16
        const val FRINGE_ALPHA_THRESHOLD = 96
    }
}

class TemplateAnalyzer {
    fun detectScreenMaskViaFloodFill(frame: Bitmap): Bitmap {
        val width = frame.width
        val height = frame.height
        val pixels = IntArray(width * height)
        frame.getPixels(pixels, 0, width, 0, 0, width, height)

        val visited = BooleanArray(width * height)
        var best: FloodComponent? = null
        val centerX = width / 2f
        val centerY = height / 2f

        for (y in 0 until height) {
            for (x in 0 until width) {
                val offset = y * width + x
                if (visited[offset] || Color.alpha(pixels[offset]) > TRANSPARENT_SEED_ALPHA) {
                    visited[offset] = true
                    continue
                }
                val component = floodFill(x, y, pixels, visited, width, height)
                if (component.area < width * height * 0.02f) continue
                val currentBest = best
                if (currentBest == null || component.score(centerX, centerY) > currentBest.score(centerX, centerY)) {
                    best = component
                }
            }
        }

        val bestComponent = best
        if (bestComponent == null || bestComponent.area < width * height * MIN_SCREEN_AREA_RATIO) {
            throw IllegalArgumentException("未能检测到有效的透明屏幕区域")
        }

        return Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888).apply {
            setPixels(bestComponent.maskPixels, 0, width, 0, 0, width, height)
        }
    }

    private fun floodFill(
        startX: Int, startY: Int, pixels: IntArray, visited: BooleanArray, width: Int, height: Int
    ): FloodComponent {
        val queue = ArrayDeque<Pair<Int, Int>>()
        val maskPixels = IntArray(width * height) { Color.BLACK }
        queue.addLast(startX to startY)
        visited[startY * width + startX] = true
        var area = 0
        var minX = startX
        var minY = startY
        var maxX = startX
        var maxY = startY

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            val offset = y * width + x
            maskPixels[offset] = Color.WHITE
            area++
            if (x < minX) minX = x
            if (y < minY) minY = y
            if (x > maxX) maxX = x
            if (y > maxY) maxY = y

            if (x > 0 && !visited[offset - 1] && Color.alpha(pixels[offset - 1]) <= TRANSPARENT_SEED_ALPHA) { visited[offset - 1] = true; queue.addLast(x - 1 to y) }
            if (x < width - 1 && !visited[offset + 1] && Color.alpha(pixels[offset + 1]) <= TRANSPARENT_SEED_ALPHA) { visited[offset + 1] = true; queue.addLast(x + 1 to y) }
            if (y > 0 && !visited[offset - width] && Color.alpha(pixels[offset - width]) <= TRANSPARENT_SEED_ALPHA) { visited[offset - width] = true; queue.addLast(x to y - 1) }
            if (y < height - 1 && !visited[offset + width] && Color.alpha(pixels[offset + width]) <= TRANSPARENT_SEED_ALPHA) { visited[offset + width] = true; queue.addLast(x to y + 1) }
        }
        return FloodComponent(area, minX, minY, maxX, maxY, maskPixels)
    }

    fun extractScreenRectFromMask(mask: Bitmap): ScreenRect {
        val width = mask.width
        val height = mask.height
        val pixels = IntArray(width * height)
        mask.getPixels(pixels, 0, width, 0, 0, width, height)

        var minX = width; var minY = height; var maxX = 0; var maxY = 0
        for (y in 0 until height) {
            for (x in 0 until width) {
                if (Color.alpha(pixels[y * width + x]) >= 128) {
                    if (x < minX) minX = x
                    if (y < minY) minY = y
                    if (x > maxX) maxX = x
                    if (y > maxY) maxY = y
                }
            }
        }
        return ScreenRect(minX, minY, maxX + 1, maxY + 1)
    }

    private data class FloodComponent(
        val area: Int,
        val minX: Int,
        val minY: Int,
        val maxX: Int,
        val maxY: Int,
        val maskPixels: IntArray,
    ) {
        fun score(centerX: Float, centerY: Float): Float {
            val componentCenterX = (minX + maxX) / 2f
            val componentCenterY = (minY + maxY) / 2f
            val distance = hypot(componentCenterX - centerX, componentCenterY - centerY)
            return area - distance * 0.8f
        }
    }

    private companion object {
        const val TRANSPARENT_SEED_ALPHA = 12
        const val MIN_SCREEN_AREA_RATIO = 0.08f
    }
}

class FeatureFitter {
    fun detectAndFitTopFeature(mask: Bitmap, screenRect: ScreenRect): FittedFeature? {
        val width = mask.width
        val height = mask.height
        val pixels = IntArray(width * height)
        mask.getPixels(pixels, 0, width, 0, 0, width, height)

        val scanTop = screenRect.top
        val scanBottom = (screenRect.top + (screenRect.height * 0.14f).toInt()).coerceAtMost(screenRect.bottom - 1)
        val scanLeft = screenRect.left + (screenRect.width * 0.25f).toInt()
        val scanRight = screenRect.right - (screenRect.width * 0.25f).toInt()

        val visited = BooleanArray(width * height)
        var maxArea = 0
        var bestFeatureBounds: RectF? = null

        // 寻找 mask 中的禁绘连通域：屏幕开口内 alpha 低的位置就是模板自带岛/孔。
        for (y in scanTop until scanBottom) {
            for (x in scanLeft until scanRight) {
                val offset = y * width + x
                if (!visited[offset] && Color.alpha(pixels[offset]) < 128) {
                    val (area, cx1, cy1, cx2, cy2) = findNonWhiteComponent(x, y, pixels, visited, width, scanLeft, scanRight, scanTop, scanBottom)
                    if (area > maxArea && area > TOP_FEATURE_MIN_AREA) {
                        maxArea = area
                        bestFeatureBounds = RectF(cx1.toFloat(), cy1.toFloat(), cx2.toFloat(), cy2.toFloat())
                    }
                }
            }
        }

        val bounds = bestFeatureBounds ?: return null
        
        val aspect = bounds.width() / bounds.height()
        val type = if (aspect >= 2.5f) TopFeatureType.ISLAND else TopFeatureType.PUNCH_HOLE
        val fittedBounds = bounds.apply { inset(-1.5f, -1.5f) }
        val shape = if (aspect < 1.6f) {
            val radius = maxOf(fittedBounds.width(), fittedBounds.height()) / 2f
            FeatureShape.Circle(cx = fittedBounds.centerX(), cy = fittedBounds.centerY(), radius = radius)
        } else {
            FeatureShape.Capsule(rect = fittedBounds, cornerRadius = fittedBounds.height() / 2f)
        }

        return FittedFeature(
            type = type,
            bounds = fittedBounds,
            shape = shape,
            confidence = (maxArea / (bounds.width() * bounds.height()).coerceAtLeast(1f)).coerceIn(0f, 1f),
        )
    }

    private fun findNonWhiteComponent(
        startX: Int, startY: Int, pixels: IntArray, visited: BooleanArray, width: Int,
        minXLimit: Int, maxXLimit: Int, minYLimit: Int, maxYLimit: Int
    ): FeatureCmp {
        val queue = ArrayDeque<Pair<Int, Int>>()
        queue.addLast(startX to startY)
        visited[startY * width + startX] = true
        var minX = startX; var minY = startY; var maxX = startX; var maxY = startY
        var area = 0

        while (queue.isNotEmpty()) {
            val (x, y) = queue.removeFirst()
            area++
            if (x < minX) minX = x; if (y < minY) minY = y
            if (x > maxX) maxX = x; if (y > maxY) maxY = y

            for (dx in -1..1) {
                for (dy in -1..1) {
                    if (dx == 0 && dy == 0) continue
                    val nx = x + dx
                    val ny = y + dy
                    if (nx in minXLimit..maxXLimit && ny in minYLimit..maxYLimit) {
                        val nOffset = ny * width + nx
                        if (!visited[nOffset] && Color.alpha(pixels[nOffset]) < 128) {
                            visited[nOffset] = true
                            queue.addLast(nx to ny)
                        }
                    }
                }
            }
        }
        return FeatureCmp(area, minX, minY, maxX + 1, maxY + 1)
    }
    
    private data class FeatureCmp(val area: Int, val minX: Int, val minY: Int, val maxX: Int, val maxY: Int)

    private companion object {
        const val TOP_FEATURE_MIN_AREA = 36
    }
}

class TemplateValidator {
    fun generatePurityReport(
        originalFrame: Bitmap,
        cleanFrame: Bitmap,
        mask: Bitmap,
        rect: ScreenRect,
        topFeature: FittedFeature?,
    ): TemplatePurityReport {
        val areaRatio = (rect.width * rect.height).toFloat() / (mask.width * mask.height)
        val centerOffsetX = abs((rect.left + rect.right) / 2f - mask.width / 2f) / mask.width
        val alphaClean = calculateAlphaCleanScore(originalFrame, cleanFrame)
        val edgeClean = calculateMaskEdgeCleanScore(mask, rect)
        val openingConfidence = when {
            areaRatio in 0.42f..0.92f -> 1f
            areaRatio in 0.32f..0.96f -> 0.72f
            else -> 0.35f
        }
        val topFeatureConsistency = topFeature?.let { feature ->
            val centerScore = 1f - (abs(feature.bounds.centerX() - mask.width / 2f) / (mask.width / 2f)).coerceIn(0f, 1f)
            val yScore = if (feature.bounds.centerY() in rect.top.toFloat()..(rect.top + rect.height * 0.18f)) 1f else 0.45f
            ((centerScore * 0.65f) + (yScore * 0.35f)).coerceIn(0f, 1f)
        } ?: 0.85f
        val geometryConsistency = when {
            centerOffsetX <= 0.05f && areaRatio in 0.42f..0.92f -> 1f
            centerOffsetX <= 0.10f -> 0.75f
            else -> 0.45f
        }

        val overall = 0.30f * alphaClean +
            0.25f * edgeClean +
            0.20f * openingConfidence +
            0.15f * topFeatureConsistency +
            0.10f * geometryConsistency

        val warnings = buildList {
            if (alphaClean < 0.8f) add("透明边 RGB 污染较重，已尝试净化")
            if (edgeClean < 0.8f) add("screen mask 边缘毛刺较多")
            if (openingConfidence < 0.8f) add("屏幕区域比例异常")
            if (topFeatureConsistency < 0.8f) add("顶部挖孔/灵动岛位置可疑")
            if (geometryConsistency < 0.8f) add("屏幕区域几何中心偏移较大")
        }

        return TemplatePurityReport(
            overallScore = overall,
            alphaCleanScore = alphaClean,
            edgeCleanScore = edgeClean,
            openingConfidenceScore = openingConfidence,
            topFeatureConsistencyScore = topFeatureConsistency,
            geometryConsistencyScore = geometryConsistency,
            isAccepted = overall >= REJECT_SCORE,
            warnings = warnings,
        )
    }

    private fun calculateAlphaCleanScore(original: Bitmap, clean: Bitmap): Float {
        val width = original.width
        val height = original.height
        val source = IntArray(width * height)
        val cleaned = IntArray(width * height)
        original.getPixels(source, 0, width, 0, 0, width, height)
        clean.getPixels(cleaned, 0, width, 0, 0, width, height)
        var polluted = 0
        var transparent = 0
        for (i in source.indices) {
            val alpha = Color.alpha(source[i])
            if (alpha in 1 until FRINGE_ALPHA_THRESHOLD) {
                transparent += 1
                val luminance = Color.red(source[i]) * 0.299f + Color.green(source[i]) * 0.587f + Color.blue(source[i]) * 0.114f
                if (luminance > 150f) polluted += 1
            }
        }
        if (transparent == 0) return 1f
        val pollutionRatio = polluted / transparent.toFloat()
        return (1f - pollutionRatio).coerceIn(0.35f, 1f)
    }

    private fun calculateMaskEdgeCleanScore(mask: Bitmap, rect: ScreenRect): Float {
        val width = mask.width
        val height = mask.height
        val pixels = IntArray(width * height)
        mask.getPixels(pixels, 0, width, 0, 0, width, height)
        var edgeSamples = 0
        var midAlpha = 0
        for (y in rect.top.coerceAtLeast(1) until rect.bottom.coerceAtMost(height - 1)) {
            for (x in rect.left.coerceAtLeast(1) until rect.right.coerceAtMost(width - 1)) {
                val alpha = Color.alpha(pixels[y * width + x])
                if (alpha in 1..254) {
                    edgeSamples += 1
                    if (alpha in 48..207) midAlpha += 1
                }
            }
        }
        if (edgeSamples == 0) return 1f
        return (1f - (midAlpha / edgeSamples.toFloat()) * 0.45f).coerceIn(0.45f, 1f)
    }

    private companion object {
        const val FRINGE_ALPHA_THRESHOLD = 96
        const val REJECT_SCORE = 0.60f
    }
}
