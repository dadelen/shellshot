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
data class TemplateConfig(
    val id: String,
    val name: String,
    val frameAsset: String,
    val previewAsset: String = "",
    val outputWidth: Int = 0,
    val outputHeight: Int = 0,
    val screenRect: ScreenRect,
    val cornerRadius: Float = 0f,
    val screenMaskBitmap: String? = null,
    val screenMaskPath: String? = null,
    val screenInsetPx: Float = 0f,
    val maskBleedPx: Float = 0f,
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
    val outputWidth: Int,
    val outputHeight: Int,
    val screenRect: ScreenRect,
    val cornerRadius: Float,
    val screenMaskBitmap: String?,
    val screenMaskPath: String?,
    val screenInsetPx: Float,
    val maskBleedPx: Float,
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
                outputWidth = config.outputWidth,
                outputHeight = config.outputHeight,
                screenRect = config.screenRect,
                cornerRadius = config.cornerRadius,
                screenMaskBitmap = config.screenMaskBitmap,
                screenMaskPath = config.screenMaskPath,
                screenInsetPx = config.screenInsetPx,
                maskBleedPx = config.maskBleedPx,
                backgroundColor = config.backgroundColor,
                scaleMode = ScaleMode.fromRaw(config.scaleMode),
                isBuiltIn = isBuiltIn,
                storageDirectoryPath = storageDirectoryPath,
            )
        }
    }
}
