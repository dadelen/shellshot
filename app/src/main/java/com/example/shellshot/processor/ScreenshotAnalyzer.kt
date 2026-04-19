package com.example.shellshot.processor

import android.graphics.Bitmap
import android.graphics.Color
import com.example.shellshot.template.ScreenRect
import com.example.shellshot.template.ScreenshotAnalysis
import com.example.shellshot.template.TopFeatureAnchor
import com.example.shellshot.template.TopFeatureType

/**
 * Source Space 截图分析器。
 *
 * 职责：仅分析原始截图（Source Space），检测顶部特征（灵动岛 / 挖孔），
 * 输出 [ScreenshotAnalysis]。输出坐标均在截图原始像素空间内，
 * 不参与模板几何计算（禁止与 Template Logical Space 混算）。
 *
 * 使用场景：在 [ShellComposer] 进行模板拼接之前，由外部调用或由
 * [ShellComposer] 内部在第一步调用，将结果以 Source Space 坐标传入
 * [ShellComposer.calculateAlignedCoverRect]，由对齐算法负责坐标映射。
 */
class ScreenshotAnalyzer {

    /**
     * 分析截图，检测顶部特征。
     *
     * @param bitmap 原始截图 Bitmap（任意分辨率）
     * @return [ScreenshotAnalysis]，坐标在截图原始像素空间内
     */
    fun analyze(
        bitmap: Bitmap,
        templatePrior: TopFeaturePrior? = null,
    ): ScreenshotAnalysis {
        val topFeature = detectTopFeature(bitmap, templatePrior)
        return ScreenshotAnalysis(
            width = bitmap.width,
            height = bitmap.height,
            topFeature = topFeature,
            sourceStatusBarBand = detectSourceStatusBarBand(bitmap, topFeature),
        )
    }

    private fun detectSourceStatusBarBand(bitmap: Bitmap, topFeature: TopFeatureAnchor?): ScreenRect {
        val scanHeight = (bitmap.height * TOP_FEATURE_SCAN_HEIGHT_RATIO)
            .toInt()
            .coerceIn(1, bitmap.height)
        val rowScores = IntArray(scanHeight)
        val featureLeft = topFeature?.let { (it.centerX - it.width * 0.75f).toInt() } ?: -1
        val featureRight = topFeature?.let { (it.centerX + it.width * 0.75f).toInt() } ?: -1
        for (y in 0 until scanHeight) {
            var score = 0
            for (x in 0 until bitmap.width) {
                if (x in featureLeft..featureRight) continue
                if (isStatusBarPixel(bitmap.getPixel(x, y))) score += 1
            }
            rowScores[y] = score
        }
        val peakY = rowScores.indices.maxByOrNull { rowScores[it] } ?: 0
        val peak = rowScores[peakY]
        if (peak <= 0) {
            val fallbackHeight = (bitmap.height * 0.035f).toInt().coerceIn(1, scanHeight)
            return ScreenRect(left = 0, top = 0, right = bitmap.width, bottom = fallbackHeight)
        }
        val threshold = maxOf(2, (peak * 0.42f).toInt())
        var top = peakY
        while (top > 0 && rowScores[top - 1] >= threshold) top -= 1
        var bottom = peakY + 1
        while (bottom < scanHeight && rowScores[bottom] >= threshold) bottom += 1
        val minHeight = (bitmap.height * 0.012f).toInt().coerceAtLeast(4)
        if (bottom - top < minHeight) {
            val pad = (minHeight - (bottom - top) + 1) / 2
            top = (top - pad).coerceAtLeast(0)
            bottom = (bottom + pad).coerceAtMost(scanHeight)
        }
        return ScreenRect(
            left = 0,
            top = top,
            right = bitmap.width,
            bottom = bottom,
        )
    }

    private fun isStatusBarPixel(color: Int): Boolean {
        if (Color.alpha(color) < 180) return false
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val luminance = red * 0.299f + green * 0.587f + blue * 0.114f
        val maxChannel = maxOf(red, green, blue)
        val minChannel = minOf(red, green, blue)
        val spread = maxChannel - minChannel
        val brightGlyph = luminance > 150f && spread < 120
        val darkGlyph = luminance < 80f && spread < 90
        val coloredIndicator = luminance > 80f && spread > 45 && maxChannel > 135
        return brightGlyph || darkGlyph || coloredIndicator
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 内部检测逻辑（Source Space，坐标均为截图原始像素）
    // ─────────────────────────────────────────────────────────────────────────

    private fun detectTopFeature(
        sourceBitmap: Bitmap,
        templatePrior: TopFeaturePrior?,
    ): TopFeatureAnchor? {
        if (sourceBitmap.width <= 0 || sourceBitmap.height <= 0) return null

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
            if (visited[index] || !isTopFeaturePixel(pixels[index])) {
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
                        if (isTopFeaturePixel(pixels[next])) {
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

        val candidates = buildTopFeatureCandidates(
            components = components,
            sourceWidth = scanWidth,
            sourceHeight = sourceBitmap.height,
            templatePrior = templatePrior,
        )
        return candidates
            .filter { it.score >= TOP_FEATURE_ACCEPT_SCORE }
            .maxWithOrNull(
                compareBy<ScoredTopFeatureCandidate> { it.score }
                    .thenBy { it.priority }
            )
            ?.anchor
    }

    private fun buildTopFeatureCandidates(
        components: List<TopFeatureComponent>,
        sourceWidth: Int,
        sourceHeight: Int,
        templatePrior: TopFeaturePrior?,
    ): List<ScoredTopFeatureCandidate> {
        if (components.isEmpty()) return emptyList()
        val candidates = mutableListOf<ScoredTopFeatureCandidate>()

        detectTopBandFeature(components)?.let { anchor ->
            candidates += scoreCandidate(
                kind = CandidateKind.ISLAND,
                anchor = anchor,
                sourceWidth = sourceWidth,
                sourceHeight = sourceHeight,
                templatePrior = templatePrior,
                connectivityScore = anchor.confidence,
                priority = 40,
            )
        }

        components
            .mapNotNull { it.toIslandAnchor() }
            .forEach { anchor ->
                candidates += scoreCandidate(
                    kind = CandidateKind.ISLAND,
                    anchor = anchor,
                    sourceWidth = sourceWidth,
                    sourceHeight = sourceHeight,
                    templatePrior = templatePrior,
                    connectivityScore = anchor.confidence,
                    priority = 30,
                )
            }

        detectClusteredTopFeature(components)?.let { anchor ->
            candidates += scoreCandidate(
                kind = if (anchor.type == TopFeatureType.ISLAND) CandidateKind.ISLAND else CandidateKind.PUNCH_HOLE_CENTER,
                anchor = anchor,
                sourceWidth = sourceWidth,
                sourceHeight = sourceHeight,
                templatePrior = templatePrior,
                connectivityScore = anchor.confidence,
                priority = 25,
            )
        }

        components
            .mapNotNull { it.toPunchHoleAnchor() }
            .forEach { anchor ->
                val centerRatio = anchor.centerX / sourceWidth.toFloat()
                candidates += scoreCandidate(
                    kind = if (centerRatio in 0.38f..0.62f) CandidateKind.PUNCH_HOLE_CENTER else CandidateKind.PUNCH_HOLE_LEFT,
                    anchor = anchor,
                    sourceWidth = sourceWidth,
                    sourceHeight = sourceHeight,
                    templatePrior = templatePrior,
                    connectivityScore = anchor.confidence,
                    priority = if (centerRatio in 0.38f..0.62f) 20 else 5,
                )
            }

        return candidates
    }

    private fun scoreCandidate(
        kind: CandidateKind,
        anchor: TopFeatureAnchor,
        sourceWidth: Int,
        sourceHeight: Int,
        templatePrior: TopFeaturePrior?,
        connectivityScore: Float,
        priority: Int,
    ): ScoredTopFeatureCandidate {
        val centerXRatio = anchor.centerX / sourceWidth.toFloat()
        val centerYRatio = anchor.centerY / sourceHeight.toFloat()
        val aspect = anchor.width / anchor.height.coerceAtLeast(1f)
        val symmetryScore = (1f - kotlin.math.abs(centerXRatio - 0.5f) / 0.5f).coerceIn(0f, 1f)
        val topDistanceScore = when {
            centerYRatio in 0.018f..0.060f -> 1f
            centerYRatio < 0.018f -> (centerYRatio / 0.018f).coerceIn(0f, 1f) * 0.65f
            else -> (1f - ((centerYRatio - 0.060f) / 0.060f)).coerceIn(0f, 1f)
        }
        val aspectScore = when (kind) {
            CandidateKind.ISLAND -> {
                val ideal = 3.6f
                (1f - kotlin.math.abs(aspect - ideal) / ideal).coerceIn(0f, 1f)
            }
            CandidateKind.PUNCH_HOLE_CENTER,
            CandidateKind.PUNCH_HOLE_LEFT -> {
                (1f - kotlin.math.abs(aspect - 1f) / 1.2f).coerceIn(0f, 1f)
            }
        }
        val statusDifferenceScore = when {
            kind == CandidateKind.PUNCH_HOLE_LEFT -> 0.15f
            anchor.width < sourceWidth * 0.018f -> 0.35f
            centerXRatio !in 0.34f..0.66f -> 0.35f
            else -> 1f
        }
        val templatePriorScore = templatePrior?.let { prior ->
            val xScore = (1f - kotlin.math.abs(centerXRatio - prior.centerXRatio) / 0.28f).coerceIn(0f, 1f)
            val yScore = (1f - kotlin.math.abs(centerYRatio - prior.centerYRatio) / 0.08f).coerceIn(0f, 1f)
            val typeScore = when {
                prior.type == TopFeatureType.NONE -> 0.65f
                prior.type == anchor.type -> 1f
                prior.type == TopFeatureType.PUNCH_HOLE && anchor.type == TopFeatureType.ISLAND -> 0.85f
                else -> 0.70f
            }
            (xScore * 0.60f + yScore * 0.15f + typeScore * 0.25f).coerceIn(0f, 1f)
        } ?: 0.72f

        val score = (
            connectivityScore.coerceIn(0f, 1f) * 0.24f +
                aspectScore * 0.18f +
                symmetryScore * 0.18f +
                topDistanceScore * 0.16f +
                statusDifferenceScore * 0.10f +
                templatePriorScore * 0.14f
            ).coerceIn(0f, 1f)

        return ScoredTopFeatureCandidate(
            anchor = anchor.copy(confidence = score),
            kind = kind,
            score = score,
            priority = priority,
        )
    }

    /**
     * 判断像素是否为顶部特征像素（深色中性 or 带隐私色调）。
     * 专为检测灵动岛 / 挖孔设计，排除浅色/透明区域。
     */
    private fun isTopFeaturePixel(color: Int): Boolean {
        if (Color.alpha(color) < 180) return false
        val red = Color.red(color)
        val green = Color.green(color)
        val blue = Color.blue(color)
        val luminance = red * 0.299f + green * 0.587f + blue * 0.114f
        val maxChannel = maxOf(red, green, blue)
        val minChannel = minOf(red, green, blue)
        val channelSpread = maxChannel - minChannel
        // 深色中性区域（灵动岛 / 挖孔背景）
        val darkNeutral = luminance < TOP_FEATURE_DARK_LUMINANCE &&
            channelSpread < TOP_FEATURE_MAX_CHANNEL_SPREAD
        // 带隐私色调的深色区域（部分 ROM 在岛/孔区域会着色）
        val privacyTint = luminance < TOP_FEATURE_PRIVACY_LUMINANCE &&
            channelSpread > TOP_FEATURE_PRIVACY_MIN_SPREAD &&
            (blue > red + 24 || red > green + 24)
        return darkNeutral || privacyTint
    }

    /**
     * 尝试将多个紧凑小组件聚合为一个岛/孔特征。
     * 适用于灵动岛被状态栏图标分割成多个连通域的情况。
     */
    private fun detectClusteredTopFeature(
        components: List<TopFeatureComponent>,
    ): TopFeatureAnchor? {
        val candidates = components.filter { it.isCompactTopCenterCandidate() }
        if (candidates.isEmpty()) return null

        val left = candidates.minOf { it.left }.toFloat()
        val top = candidates.minOf { it.top }.toFloat()
        val right = candidates.maxOf { it.right }.toFloat()
        val bottom = candidates.maxOf { it.bottom }.toFloat()
        val width = right - left
        val height = bottom - top
        val sourceWidth = candidates.first().sourceWidth
        val scanHeight = candidates.first().sourceScanHeight
        if (width < TOP_FEATURE_MIN_SIZE_PX || height < TOP_FEATURE_MIN_SIZE_PX) return null
        if (width > sourceWidth * 0.42f || height > scanHeight * 0.42f) return null

        val centerX = (left + right) / 2f
        val centerY = (top + bottom) / 2f
        val aspect = width / height.coerceAtLeast(1f)
        val centerScore = (1f - (kotlin.math.abs(centerX - sourceWidth / 2f) / (sourceWidth / 2f)))
            .coerceIn(0f, 1f)
        if (centerScore < TOP_FEATURE_MIN_CENTER_SCORE) return null

        val type = if (aspect >= 1.8f || candidates.size > 1) {
            TopFeatureType.ISLAND
        } else {
            TopFeatureType.PUNCH_HOLE
        }
        return TopFeatureAnchor(
            type = type,
            centerX = centerX,
            centerY = centerY,
            width = width,
            height = height,
            confidence = (0.35f + centerScore * 0.45f + candidates.size.coerceAtMost(3) * 0.05f)
                .coerceIn(0f, 1f),
        )
    }

    private fun detectTopBandFeature(
        components: List<TopFeatureComponent>,
    ): TopFeatureAnchor? {
        val candidates = components.filter { it.isTopBandCandidate() }
        if (candidates.isEmpty()) return null

        val sourceWidth = candidates.first().sourceWidth
        val scanHeight = candidates.first().sourceScanHeight
        val clusteredCandidates = candidates
            .sortedBy { kotlin.math.abs(it.centerX - sourceWidth / 2f) }
            .take(4)
        val left = candidates.minOf { it.left }.toFloat()
        val top = candidates.minOf { it.top }.toFloat()
        val right = candidates.maxOf { it.right }.toFloat()
        val bottom = candidates.maxOf { it.bottom }.toFloat()
        val clusteredLeft = clusteredCandidates.minOf { it.left }.toFloat()
        val clusteredTop = clusteredCandidates.minOf { it.top }.toFloat()
        val clusteredRight = clusteredCandidates.maxOf { it.right }.toFloat()
        val clusteredBottom = clusteredCandidates.maxOf { it.bottom }.toFloat()
        val width = right - left
        val height = bottom - top
        val clusteredWidth = clusteredRight - clusteredLeft
        val clusteredHeight = clusteredBottom - clusteredTop
        val centerX = (clusteredLeft + clusteredRight) / 2f
        val centerY = (clusteredTop + clusteredBottom) / 2f
        val clusteredAspect = clusteredWidth / clusteredHeight.coerceAtLeast(1f)
        val componentCount = clusteredCandidates.size
        val centerScore = (1f - (kotlin.math.abs(centerX - sourceWidth / 2f) / (sourceWidth / 2f)))
            .coerceIn(0f, 1f)

        if (centerScore < TOP_FEATURE_MIN_CENTER_SCORE) return null
        if (centerY > scanHeight * 0.48f) return null
        if (clusteredWidth < sourceWidth * 0.07f) return null
        if (clusteredWidth > sourceWidth * 0.34f) return null
        if (clusteredHeight > scanHeight * 0.36f) return null
        if (componentCount < 2 && clusteredAspect < 2.1f) return null
        // 如果原始 union 远大于中心簇，说明把右侧状态栏图标也吞进来了，必须丢弃。
        if (width > clusteredWidth * 1.8f) return null

        return TopFeatureAnchor(
            type = TopFeatureType.ISLAND,
            centerX = centerX,
            centerY = centerY,
            width = clusteredWidth,
            height = clusteredHeight.coerceAtLeast(TOP_FEATURE_MIN_SIZE_PX.toFloat()),
            confidence = (0.45f + centerScore * 0.35f + clusteredCandidates.size.coerceAtMost(4) * 0.04f)
                .coerceIn(0f, 1f),
        )
    }

    // ─────────────────────────────────────────────────────────────────────────
    // 内部数据类：TopFeatureComponent（Source Space 连通域）
    // ─────────────────────────────────────────────────────────────────────────

    private data class TopFeatureComponent(
        val left: Int,
        val top: Int,
        val right: Int,
        val bottom: Int,
        val pixelCount: Int,
        val sourceWidth: Int,
        val sourceScanHeight: Int,
    ) {
        val width: Float get() = (right - left).toFloat()
        val height: Float get() = (bottom - top).toFloat()
        val centerX: Float get() = (left + right) / 2f
        val centerY: Float get() = (top + bottom) / 2f
        private val aspect: Float get() = width / height.coerceAtLeast(1f)
        private val fillRatio: Float
            get() = pixelCount / (width * height).coerceAtLeast(1f)
        private val centerScore: Float
            get() = (1f - (kotlin.math.abs(centerX - sourceWidth / 2f) / (sourceWidth / 2f)))
                .coerceIn(0f, 1f)
        private val topScore: Float
            get() = (1f - (centerY / sourceScanHeight.toFloat())).coerceIn(0f, 1f)

        fun isCompactTopCenterCandidate(): Boolean {
            if (bottom >= sourceScanHeight - 2) return false
            if (width < TOP_FEATURE_MIN_SIZE_PX || height < TOP_FEATURE_MIN_SIZE_PX) return false
            if (width > sourceWidth * 0.20f || height > sourceScanHeight * 0.42f) return false
            if (centerY > sourceScanHeight * 0.58f) return false
            if (!isNearHorizontalCenter(centerX, sourceWidth)) return false
            if (fillRatio < 0.18f) return false
            return true
        }

        fun isTopBandCandidate(): Boolean {
            if (bottom >= sourceScanHeight - 2) return false
            if (width < TOP_FEATURE_MIN_SIZE_PX || height < TOP_FEATURE_MIN_SIZE_PX) return false
            if (width > sourceWidth * 0.36f || height > sourceScanHeight * 0.42f) return false
            if (centerY > sourceScanHeight * 0.52f) return false
            if (!isNearHorizontalCenter(centerX, sourceWidth)) return false
            if (fillRatio < 0.12f) return false
            return true
        }

        fun toIslandAnchor(): TopFeatureAnchor? {
            val minWidth = sourceWidth * 0.08f
            val maxWidth = sourceWidth * 0.48f
            val maxHeight = sourceScanHeight * 0.42f
            if (bottom >= sourceScanHeight - 2) return null
            if (width !in minWidth..maxWidth) return null
            if (height !in TOP_FEATURE_MIN_SIZE_PX.toFloat()..maxHeight) return null
            if (aspect < 2.2f || aspect > 9f) return null
            if (fillRatio < 0.35f) return null
            if (centerScore < TOP_FEATURE_MIN_CENTER_SCORE) return null

            val confidence = (0.45f + centerScore * 0.35f + topScore * 0.10f +
                fillRatio.coerceAtMost(1f) * 0.10f).coerceIn(0f, 1f)
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
            if (bottom >= sourceScanHeight - 2) return null
            if (width !in minSize..maxSize || height !in minSize..maxSize) return null
            if (aspect !in 0.55f..1.85f) return null
            if (fillRatio < 0.30f) return null
            if (centerScore < TOP_FEATURE_MIN_CENTER_SCORE) return null

            val confidence = (0.40f + centerScore * 0.35f + topScore * 0.10f +
                fillRatio.coerceAtMost(1f) * 0.15f).coerceIn(0f, 1f)
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

    data class TopFeaturePrior(
        val type: TopFeatureType,
        val centerXRatio: Float,
        val centerYRatio: Float,
    )

    private enum class CandidateKind {
        ISLAND,
        PUNCH_HOLE_CENTER,
        PUNCH_HOLE_LEFT,
    }

    private data class ScoredTopFeatureCandidate(
        val anchor: TopFeatureAnchor,
        val kind: CandidateKind,
        val score: Float,
        val priority: Int,
    )

    companion object {
        private const val TOP_FEATURE_SCAN_HEIGHT_RATIO = 0.16f
        private const val TOP_FEATURE_MIN_SIZE_PX = 8
        private const val TOP_FEATURE_ACCEPT_SCORE = 0.62f
        private const val TOP_FEATURE_CENTER_MIN_RATIO = 0.38f
        private const val TOP_FEATURE_CENTER_MAX_RATIO = 0.62f
        private const val TOP_FEATURE_MIN_CENTER_SCORE = 0.76f
        private const val TOP_FEATURE_DARK_LUMINANCE = 105f
        private const val TOP_FEATURE_MAX_CHANNEL_SPREAD = 80
        private const val TOP_FEATURE_PRIVACY_LUMINANCE = 190f
        private const val TOP_FEATURE_PRIVACY_MIN_SPREAD = 50

        private fun isNearHorizontalCenter(centerX: Float, sourceWidth: Int): Boolean {
            if (sourceWidth <= 0) return false
            val ratio = centerX / sourceWidth.toFloat()
            return ratio in TOP_FEATURE_CENTER_MIN_RATIO..TOP_FEATURE_CENTER_MAX_RATIO
        }
    }
}
