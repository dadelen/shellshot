package com.example.shellshot.template

import kotlinx.serialization.Serializable

@Serializable
data class ScreenRect(
    val left: Int,
    val top: Int,
    val right: Int,
    val bottom: Int,
) {
    val width: Int get() = right - left
    val height: Int get() = bottom - top
}

@Serializable
data class CutoutRegion(
    val pathData: String? = null,
    val rect: ScreenRect? = null,
    val featherPx: Float = 0f,
)

@Serializable
enum class TopFeatureType {
    NONE,
    ISLAND,
    PUNCH_HOLE,
}

@Serializable
data class TopFeatureAnchor(
    val type: TopFeatureType,
    val centerX: Float,
    val centerY: Float,
    val width: Float,
    val height: Float,
    val confidence: Float = 1f,
)

/**
 * Source Space 分析结果。
 * 所有坐标均在原始截图像素空间内，不参与模板几何计算。
 * 由 [com.example.shellshot.processor.ScreenshotAnalyzer] 生成。
 */
data class ScreenshotAnalysis(
    val width: Int,
    val height: Int,
    val topFeature: TopFeatureAnchor?,
    val sourceStatusBarBand: ScreenRect? = null,
)

@Serializable
data class ScreenGeometryDerived(
    val visibleBounds: ScreenRect,
    val safeTopBand: ScreenRect,
    val contentClipRect: ScreenRect,
    val topSuppressionRect: ScreenRect? = null,
    val topHoleOverlayRect: ScreenRect? = null,
    val topFeatureAvoidRect: ScreenRect? = null,
    val statusBarSafeZones: StatusBarSafeZones? = null,
)

@Serializable
data class DeviceCaptureProfile(
    val captureWidth: Int,
    val captureHeight: Int,
    val captureAspectRatio: Float,
    val densityDpi: Int,
    val physicalModeWidth: Int? = null,
    val physicalModeHeight: Int? = null,
    val rotationAtCalibration: Int,
    val updatedAt: Long,
) {
    fun hasMeaningfulChangeFrom(other: DeviceCaptureProfile): Boolean {
        return captureWidth != other.captureWidth ||
            captureHeight != other.captureHeight ||
            physicalModeWidth != other.physicalModeWidth ||
            physicalModeHeight != other.physicalModeHeight ||
            rotationAtCalibration != other.rotationAtCalibration ||
            kotlin.math.abs(densityDpi - other.densityDpi) >= 24
    }
}

@Serializable
data class TemplateCalibration(
    val enabled: Boolean = false,
    val captureProfile: DeviceCaptureProfile? = null,
    val captureWidth: Int = captureProfile?.captureWidth ?: 0,
    val captureHeight: Int = captureProfile?.captureHeight ?: 0,
    val captureAspectRatio: Float = captureProfile?.captureAspectRatio ?: 0f,
    val overlayCenterX: Float = 0f,
    val overlayCenterY: Float = 0f,
    val overlayWidth: Float = 0f,
    val overlayHeight: Float = 0f,
    val overlayCornerRadius: Float = 0f,
    val visibleBounds: ScreenRect? = null,
    val screenBounds: ScreenRect? = null,
    val contentClipRect: ScreenRect? = null,
    val updatedAt: Long = 0L,
    val physicalModeWidth: Int? = captureProfile?.physicalModeWidth,
    val physicalModeHeight: Int? = captureProfile?.physicalModeHeight,
    val densityDpi: Int = captureProfile?.densityDpi ?: 0,
) {
    val isUsable: Boolean
        get() = enabled &&
            captureAspectRatio > 0f &&
            visibleBounds != null &&
            screenBounds != null &&
            overlayWidth > 0f &&
            overlayHeight > 0f
}

@Serializable
data class TemplatePuritySummary(
    val overallScore: Float,
    val warnings: List<String> = emptyList(),
)

@Serializable
data class StatusBarSafeZones(
    val left: ScreenRect,
    val right: ScreenRect,
    val avoid: ScreenRect,
)

@Serializable
enum class TemplateTopLayerMode {
    INLINE,
    SEPARATED,
}

@Serializable
data class TemplateConfig(
    val id: String,
    val name: String,
    val templateVersion: Int = 1,
    val frameAsset: String,
    val frameBaseAsset: String? = null,
    val topHoleOverlayAsset: String? = null,
    val previewAsset: String = "",
    // 模板逻辑空间尺寸（推荐显式设为 1000 × 2000）。
    // 所有 screenRect、topFeature、cutouts、path 均在此逻辑空间内定义，
    // 禁止用 frame PNG 原始像素尺寸直接参与计算。
    val logicalWidth: Int = 0,
    val logicalHeight: Int = 0,
    val outputWidth: Int = 0,
    val outputHeight: Int = 0,
    val screenRect: ScreenRect,
    val screenPath: String? = null,
    val cornerRadius: Float = 0f,
    val screenMaskBitmap: String? = null,
    val screenMaskPath: String? = null,
    val calibration: TemplateCalibration? = null,
    val visibleBounds: ScreenRect? = null,
    val safeTopBand: ScreenRect? = null,
    val contentClipRect: ScreenRect? = null,
    val topSuppressionRect: ScreenRect? = null,
    val topHoleOverlayRect: ScreenRect? = null,
    val topFeatureAvoidRect: ScreenRect? = null,
    val statusBarSafeZones: StatusBarSafeZones? = null,
    val topLayerMode: TemplateTopLayerMode = TemplateTopLayerMode.INLINE,
    val purity: TemplatePuritySummary? = null,
    val cutouts: List<CutoutRegion> = emptyList(),
    val templateTopFeature: TopFeatureAnchor? = null,
    val screenInsetPx: Float = 0f,
    val maskBleedPx: Float = 1.0f,
    val cutoutBleedPx: Float = 1.2f,
    // 防白边 overscan，逻辑空间像素，推荐 2.0f ~ 4.0f。
    val contentOverscanPx: Float = 2.5f,
    val contentOffsetX: Float = 0f,
    val contentOffsetY: Float = 0f,
    val contentScaleAdjust: Float = 0f,
    val alphaTighten: Boolean = true,
    val alphaLowThreshold: Int = 24,
    val alphaHighThreshold: Int = 232,
    val debugDrawGuides: Boolean = false,
    val backgroundColor: String = "#00000000",
    val scaleMode: String = ScaleMode.CENTER_CROP.name,
)

@Serializable
data class TemplateListAsset(
    val templates: List<String>,
)

enum class ScaleMode {
    /** @deprecated 禁止在新模板中使用，仅保留用于向后兼容。新模板必须使用 CENTER_CROP。 */
    FIT_CENTER,
    CENTER_CROP;

    companion object {
        fun fromRaw(value: String): ScaleMode {
            return when {
                value.equals("cover", ignoreCase = true) -> CENTER_CROP
                value.equals("contain", ignoreCase = true) -> FIT_CENTER
                value.equals("fit", ignoreCase = true) -> FIT_CENTER
                else -> entries.firstOrNull { it.name.equals(value, ignoreCase = true) } ?: CENTER_CROP
            }
        }
    }
}

data class ShellTemplate(
    val id: String,
    val name: String,
    val templateVersion: Int,
    val frameAsset: String,
    val frameBaseAsset: String?,
    val topHoleOverlayAsset: String?,
    val previewAsset: String,
    val logicalWidth: Int,
    val logicalHeight: Int,
    val outputWidth: Int,
    val outputHeight: Int,
    val screenRect: ScreenRect,
    val screenPath: String?,
    val cornerRadius: Float,
    val screenMaskBitmap: String?,
    val screenMaskPath: String?,
    val calibration: TemplateCalibration?,
    val visibleBounds: ScreenRect?,
    val safeTopBand: ScreenRect?,
    val contentClipRect: ScreenRect?,
    val topSuppressionRect: ScreenRect?,
    val topHoleOverlayRect: ScreenRect?,
    val topFeatureAvoidRect: ScreenRect?,
    val statusBarSafeZones: StatusBarSafeZones?,
    val topLayerMode: TemplateTopLayerMode,
    val purity: TemplatePuritySummary?,
    val cutouts: List<CutoutRegion>,
    val templateTopFeature: TopFeatureAnchor?,
    val screenInsetPx: Float,
    val maskBleedPx: Float,
    val cutoutBleedPx: Float,
    val contentOverscanPx: Float,
    val contentOffsetX: Float,
    val contentOffsetY: Float,
    val contentScaleAdjust: Float,
    val alphaTighten: Boolean,
    val alphaLowThreshold: Int,
    val alphaHighThreshold: Int,
    val debugDrawGuides: Boolean,
    val backgroundColor: String,
    val scaleMode: ScaleMode,
    val isBuiltIn: Boolean = true,
    val storageDirectoryPath: String? = null,
) {
    val hasExplicitOutputSize: Boolean
        get() = outputWidth > 0 && outputHeight > 0

    val canDelete: Boolean
        get() = !isBuiltIn && !storageDirectoryPath.isNullOrBlank()

    val calibrationEnabled: Boolean
        get() = calibration?.isUsable == true

    /**
     * Template Logical Space 有效宽度。
     * 优先使用显式声明的 [logicalWidth]（标准值 [DEFAULT_LOGICAL_WIDTH]=1000），
     * 其次 [outputWidth]，最后 fallback 到调用方给定值（通常为 outputSize.width）。
     */
    fun effectiveLogicalWidth(fallback: Int): Int =
        logicalWidth.takeIf { it > 0 }
            ?: outputWidth.takeIf { it > 0 }
            ?: fallback

    /**
     * Template Logical Space 有效高度。
     * 优先使用显式声明的 [logicalHeight]（标准值 [DEFAULT_LOGICAL_HEIGHT]=2000），
     * 其次 [outputHeight]，最后 fallback 到调用方给定值（通常为 outputSize.height）。
     */
    fun effectiveLogicalHeight(fallback: Int): Int =
        logicalHeight.takeIf { it > 0 }
            ?: outputHeight.takeIf { it > 0 }
            ?: fallback

    companion object {
        /** 模板逻辑空间标准宽度。所有 screenRect、topFeature、cutouts、path 坐标均基于此空间定义。*/
        const val DEFAULT_LOGICAL_WIDTH = 1000

        /** 模板逻辑空间标准高度。所有 screenRect、topFeature、cutouts、path 坐标均基于此空间定义。*/
        const val DEFAULT_LOGICAL_HEIGHT = 2000
        const val CURRENT_TEMPLATE_VERSION = 3

        fun fromConfig(
            config: TemplateConfig,
            isBuiltIn: Boolean = true,
            storageDirectoryPath: String? = null,
        ): ShellTemplate {
            return ShellTemplate(
                id = config.id,
                name = config.name,
                templateVersion = config.templateVersion,
                frameAsset = config.frameAsset,
                frameBaseAsset = config.frameBaseAsset,
                topHoleOverlayAsset = config.topHoleOverlayAsset,
                previewAsset = config.previewAsset.ifBlank { config.frameAsset },
                logicalWidth = config.logicalWidth.takeIf { it > 0 } ?: config.outputWidth,
                logicalHeight = config.logicalHeight.takeIf { it > 0 } ?: config.outputHeight,
                outputWidth = config.outputWidth,
                outputHeight = config.outputHeight,
                screenRect = config.screenRect,
                screenPath = config.screenPath,
                cornerRadius = config.cornerRadius,
                screenMaskBitmap = config.screenMaskBitmap,
                screenMaskPath = config.screenMaskPath ?: config.screenPath,
                calibration = config.calibration,
                visibleBounds = config.calibration?.visibleBounds ?: config.visibleBounds ?: config.screenRect,
                safeTopBand = config.safeTopBand,
                contentClipRect = config.calibration?.contentClipRect ?: config.contentClipRect,
                topSuppressionRect = config.topSuppressionRect,
                topHoleOverlayRect = config.topHoleOverlayRect,
                topFeatureAvoidRect = config.topFeatureAvoidRect,
                statusBarSafeZones = config.statusBarSafeZones,
                topLayerMode = config.topLayerMode,
                purity = config.purity,
                cutouts = config.cutouts,
                templateTopFeature = config.templateTopFeature,
                screenInsetPx = config.screenInsetPx,
                maskBleedPx = config.maskBleedPx,
                cutoutBleedPx = config.cutoutBleedPx,
                contentOverscanPx = config.contentOverscanPx,
                contentOffsetX = config.contentOffsetX,
                contentOffsetY = config.contentOffsetY,
                contentScaleAdjust = config.contentScaleAdjust,
                alphaTighten = config.alphaTighten,
                alphaLowThreshold = config.alphaLowThreshold,
                alphaHighThreshold = config.alphaHighThreshold,
                debugDrawGuides = config.debugDrawGuides,
                backgroundColor = config.backgroundColor,
                scaleMode = ScaleMode.fromRaw(config.scaleMode),
                isBuiltIn = isBuiltIn,
                storageDirectoryPath = storageDirectoryPath,
            )
        }
    }
}
