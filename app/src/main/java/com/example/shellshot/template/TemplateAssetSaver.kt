package com.example.shellshot.template

import android.graphics.Bitmap
import com.example.shellshot.processor.pipeline.ImportedTemplate
import java.io.File
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class TemplateAssetSaver(
    private val json: Json,
    private val previewRenderer: TemplatePreviewRenderer = TemplatePreviewRenderer(),
) {
    fun save(
        targetDirectory: File,
        configFileName: String,
        templateId: String,
        draft: TemplateImportDraft,
        importedTemplate: ImportedTemplate,
        geometry: CalibratedTemplateGeometry,
    ): TemplateImportResult {
        targetDirectory.mkdirs()
        val frameFile = File(targetDirectory, "frame.png")
        val frameBaseFile = File(targetDirectory, "frameBase.png")
        val topHoleOverlayFile = File(targetDirectory, "topHoleOverlay.png")
        val previewFile = File(targetDirectory, "preview.png")
        val maskFile = File(targetDirectory, "screen_mask.png")
        val configFile = File(targetDirectory, configFileName)

        writeBitmap(frameFile, importedTemplate.frameBitmap, "无法保存模板外框")
        writeBitmap(frameBaseFile, importedTemplate.frameBaseBitmap, "无法保存模板基础外框")
        val topHoleOverlayAsset = importedTemplate.topHoleOverlayBitmap?.let { overlay ->
            writeBitmap(topHoleOverlayFile, overlay, "无法保存模板顶部孔位层")
            topHoleOverlayFile.absolutePath
        }

        val baseMask = checkNotNull(importedTemplate.maskBitmap) {
            "模板预处理未生成 screenMask"
        }
        val adjustedMask = previewRenderer.renderAdjustedMask(
            baseMask = baseMask,
            baseVisibleBounds = draft.baseVisibleBounds,
            geometry = geometry,
        )
        val preview = previewRenderer.renderPreview(importedTemplate.frameBitmap, geometry)
        try {
            writeBitmap(maskFile, adjustedMask, "无法生成屏幕遮罩")
            writeBitmap(previewFile, preview, "无法保存模板预览")
        } finally {
            adjustedMask.recycle()
            preview.recycle()
        }

        val config = TemplateConfig(
            id = templateId,
            name = draft.templateName.ifBlank { "我的模板" },
            templateVersion = ShellTemplate.CURRENT_TEMPLATE_VERSION,
            importPipelineVersion = TemplateAdjustmentApplier.IMPORT_PIPELINE_VERSION,
            manualAdjustVersion = TemplateAdjustmentApplier.MANUAL_ADJUST_VERSION,
            frameAsset = frameFile.absolutePath,
            frameBaseAsset = frameBaseFile.absolutePath,
            topHoleOverlayAsset = topHoleOverlayAsset,
            previewAsset = previewFile.absolutePath,
            logicalWidth = draft.outputWidth,
            logicalHeight = draft.outputHeight,
            outputWidth = draft.outputWidth,
            outputHeight = draft.outputHeight,
            screenRect = geometry.screenRect,
            cornerRadius = draft.cornerRadiusPx,
            screenMaskBitmap = maskFile.absolutePath,
            calibration = TemplateCalibration(
                enabled = true,
                captureProfile = draft.captureProfile,
                captureWidth = draft.captureProfile.captureWidth,
                captureHeight = draft.captureProfile.captureHeight,
                captureAspectRatio = draft.captureProfile.captureAspectRatio,
                overlayCenterX = geometry.screenRect.left + geometry.screenRect.width / 2f,
                overlayCenterY = geometry.screenRect.top + geometry.screenRect.height / 2f,
                overlayWidth = geometry.screenRect.width.toFloat(),
                overlayHeight = geometry.screenRect.height.toFloat(),
                overlayCornerRadius = draft.cornerRadiusPx,
                calibrationCorners = draft.corners.normalizedFor(draft.outputWidth, draft.outputHeight),
                visibleBounds = geometry.visibleBounds,
                screenBounds = geometry.screenRect,
                contentClipRect = geometry.contentClipRect,
                updatedAt = System.currentTimeMillis(),
                physicalModeWidth = draft.captureProfile.physicalModeWidth,
                physicalModeHeight = draft.captureProfile.physicalModeHeight,
                densityDpi = draft.captureProfile.densityDpi,
            ),
            visibleBounds = geometry.visibleBounds,
            safeTopBand = geometry.safeTopBand,
            contentClipRect = geometry.contentClipRect,
            topSuppressionRect = geometry.topSuppressionRect,
            topHoleOverlayRect = geometry.topHoleOverlayRect,
            topFeatureAvoidRect = geometry.topFeatureAvoidRect,
            statusBarSafeZones = importedTemplate.geometryDerived.statusBarSafeZones,
            topLayerMode = if (topHoleOverlayAsset != null) TemplateTopLayerMode.SEPARATED else TemplateTopLayerMode.INLINE,
            templateTopFeature = geometry.templateTopFeature,
            purity = TemplatePuritySummary(
                overallScore = importedTemplate.purityReport.overallScore,
                warnings = importedTemplate.purityReport.warnings,
            ),
            screenInsetPx = 0f,
            maskBleedPx = 1.25f,
            cutoutBleedPx = 1.2f,
            contentOverscanPx = 3.5f,
            alphaTighten = true,
            alphaLowThreshold = 32,
            alphaHighThreshold = 208,
            backgroundColor = "#00000000",
            scaleMode = ScaleMode.CENTER_CROP.name,
        )
        configFile.writeText(json.encodeToString(config))

        return TemplateImportResult(
            success = true,
            templateId = templateId,
            message = "模板已完成工业级微调并保存",
        )
    }

    private fun writeBitmap(file: File, bitmap: Bitmap, failureMessage: String) {
        file.outputStream().use { output ->
            check(bitmap.compress(Bitmap.CompressFormat.PNG, 100, output)) {
                failureMessage
            }
        }
    }
}
