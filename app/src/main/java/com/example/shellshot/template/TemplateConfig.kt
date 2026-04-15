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

@Serializable
data class TemplateConfig(
    val id: String,
    val name: String,
    val frameAsset: String,
    val previewAsset: String = "",
    val logicalWidth: Int = 0,
    val logicalHeight: Int = 0,
    val outputWidth: Int = 0,
    val outputHeight: Int = 0,
    val screenRect: ScreenRect,
    val screenPath: String? = null,
    val cornerRadius: Float = 0f,
    val screenMaskBitmap: String? = null,
    val screenMaskPath: String? = null,
    val cutouts: List<CutoutRegion> = emptyList(),
    val templateTopFeature: TopFeatureAnchor? = null,
    val screenInsetPx: Float = 0f,
    val maskBleedPx: Float = 1.0f,
    val cutoutBleedPx: Float = 1.2f,
    val contentOverscanPx: Float = 1.5f,
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
    val frameAsset: String,
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

    companion object {
        fun fromConfig(
            config: TemplateConfig,
            isBuiltIn: Boolean = true,
            storageDirectoryPath: String? = null,
        ): ShellTemplate {
            return ShellTemplate(
                id = config.id,
                name = config.name,
                frameAsset = config.frameAsset,
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
