package com.example.shellshot.processor.pipeline

import android.graphics.Bitmap
import android.graphics.Path
import android.graphics.RectF
import com.example.shellshot.template.CutoutRegion
import com.example.shellshot.template.ScreenGeometryDerived
import com.example.shellshot.template.ScreenRect
import com.example.shellshot.template.StatusBarSafeZones
import com.example.shellshot.template.TopFeatureAnchor
import com.example.shellshot.template.TopFeatureType

// ─────────────────────────────────────────────────────────────────────────
// 1. Feature Geometry & Modeling
// ─────────────────────────────────────────────────────────────────────────

/**
 * 表示在顶部的受抑制区域（挖孔/灵动岛）的几何特征结构，使用精确矢量图描绘，彻底告别像素锯齿
 */
sealed class FeatureShape {
    abstract fun toPath(): Path

    data class Circle(val cx: Float, val cy: Float, val radius: Float) : FeatureShape() {
        override fun toPath() = Path().apply { addCircle(cx, cy, radius, Path.Direction.CW) }
    }

    data class Capsule(val rect: RectF, val cornerRadius: Float) : FeatureShape() {
        override fun toPath() = Path().apply { addRoundRect(rect, cornerRadius, cornerRadius, Path.Direction.CW) }
    }
}

/**
 * 经过预处理形态学和几何拟合后得到的特征
 */
data class FittedFeature(
    val type: TopFeatureType,
    val bounds: RectF,
    val shape: FeatureShape,
    val confidence: Float,
)

// ─────────────────────────────────────────────────────────────────────────
// 2. Import Pipeline Data Structures
// ─────────────────────────────────────────────────────────────────────────

/**
 * 模板净化体检报告，低于 0.6 会直接被抛出拒绝
 */
data class TemplatePurityReport(
    val overallScore: Float,
    val alphaCleanScore: Float,
    val edgeCleanScore: Float,
    val openingConfidenceScore: Float,
    val topFeatureConsistencyScore: Float,
    val geometryConsistencyScore: Float,
    val isAccepted: Boolean,
    val warnings: List<String>,
)

/**
 * 导入流水线输出的纯净模型
 */
data class ImportedTemplate(
    val id: String,
    val name: String,
    val frameBitmap: Bitmap,
    val frameBaseBitmap: Bitmap,
    val topHoleOverlayBitmap: Bitmap?,
    val maskBitmap: Bitmap?,
    val screenRect: ScreenRect,
    val geometryDerived: ScreenGeometryDerived,
    val topFeature: FittedFeature?,
    val purityReport: TemplatePurityReport,
)

// ─────────────────────────────────────────────────────────────────────────
// 3. Compose Pipeline Data Structures
// ─────────────────────────────────────────────────────────────────────────

/**
 * 最终输出在画布空间的几何模型
 */
data class ResolvedGeometry(
    val screenRect: RectF,
    val visibleBounds: RectF,
    val safeTopBand: RectF,
    val contentClipRect: RectF,
    val contentClipPath: Path,
    val screenPath: Path,
    val topSuppressionPath: Path?, // 基于 FittedFeature 映射出来的最终剔除区域
    val topSuppressionRect: RectF?,
    val topHoleOverlayRect: RectF?,
    val topFeatureAvoidRect: RectF?,
    val statusBarSafeZones: ResolvedStatusBarSafeZones?,
    val cutouts: List<CutoutRegion>,
    val templateTopFeature: TopFeatureAnchor?,
    val scaleX: Float,
    val scaleY: Float,
    val scaleToOutput: Float,
    val safeTopInset: Float, // 用于限制截图最大 Y 偏移
)

data class ResolvedStatusBarSafeZones(
    val left: RectF,
    val right: RectF,
    val avoid: RectF,
)

enum class ComposeMode {
    STANDARD,
    SAFE,
    MINIMAL,
}

data class CoverRectMetrics(
    val composeMode: ComposeMode,
    val overscan: Float,
    val scale: Float,
    val baseScale: Float,
    val finalScale: Float,
    val scaleDelta: Float,
    val recommendedOverscanPx: Float,
    val overscanPx: Float,
    val featureDx: Float,
    val featureDy: Float,
    val wantDx: Float,
    val wantDy: Float,
    val appliedDx: Float,
    val appliedDy: Float,
    val bottomReservePx: Float,
    val bottomLossRatio: Float,
    val maxBottomLossRatio: Float,
    val yClampTriggered: Boolean,
    val coverageCorrectionTriggered: Boolean,
    val topAlignmentRejected: Boolean = false,
    val statusBarSafeCheckTriggered: Boolean,
    val statusBarBandVetoTriggered: Boolean,
    val statusBarBandVetoReason: String?,
)

data class CalibratedComposeMetrics(
    val overscanPx: Float,
    val scale: Float,
    val drawRect: RectF,
)
