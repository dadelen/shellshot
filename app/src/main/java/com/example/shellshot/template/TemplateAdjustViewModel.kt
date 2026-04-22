package com.example.shellshot.template

class TemplateAdjustViewModel {
    fun updateParam(
        draft: TemplateImportDraft,
        field: TemplateAdjustField,
        delta: Int,
    ): TemplateImportDraft {
        val params = draft.adjustmentParams
        val next = when (field) {
            TemplateAdjustField.OFFSET_X -> params.copy(offsetX = params.offsetX + delta)
            TemplateAdjustField.OFFSET_Y -> params.copy(offsetY = params.offsetY + delta)
            TemplateAdjustField.SCALE_PER_MILLE -> params.copy(scale = (params.scale + delta / 1000f).coerceIn(0.94f, 1.06f))
            TemplateAdjustField.TOP_FEATURE_OFFSET_Y -> params.copy(topFeatureOffsetY = params.topFeatureOffsetY + delta)
            TemplateAdjustField.CLIP_INSET_RIGHT -> params.copy(clipInsetRight = (params.clipInsetRight + delta).coerceIn(0, 16))
            TemplateAdjustField.CLIP_INSET_BOTTOM -> params.copy(clipInsetBottom = (params.clipInsetBottom + delta).coerceIn(0, 16))
        }
        return draft.copy(adjustmentParams = next.snapped)
    }

    fun setParam(
        draft: TemplateImportDraft,
        field: TemplateAdjustField,
        value: String,
    ): TemplateImportDraft {
        val parsedInt = value.toIntOrNull()
        val parsedFloat = value.toFloatOrNull()
        val params = draft.adjustmentParams
        val next = when (field) {
            TemplateAdjustField.OFFSET_X -> params.copy(offsetX = parsedInt ?: params.offsetX)
            TemplateAdjustField.OFFSET_Y -> params.copy(offsetY = parsedInt ?: params.offsetY)
            TemplateAdjustField.SCALE_PER_MILLE -> params.copy(scale = (parsedFloat ?: params.scale).coerceIn(0.94f, 1.06f))
            TemplateAdjustField.TOP_FEATURE_OFFSET_Y -> params.copy(topFeatureOffsetY = parsedInt ?: params.topFeatureOffsetY)
            TemplateAdjustField.CLIP_INSET_RIGHT -> params.copy(clipInsetRight = (parsedInt ?: params.clipInsetRight).coerceIn(0, 16))
            TemplateAdjustField.CLIP_INSET_BOTTOM -> params.copy(clipInsetBottom = (parsedInt ?: params.clipInsetBottom).coerceIn(0, 16))
        }
        return draft.copy(adjustmentParams = next.snapped)
    }

    fun setMode(draft: TemplateImportDraft, mode: TemplateAdjustCheckMode): TemplateImportDraft =
        draft.copy(checkMode = mode)

    fun setLoupe(draft: TemplateImportDraft, target: TemplateAdjustLoupeTarget, zoom: Int): TemplateImportDraft =
        draft.copy(loupeTarget = target, loupeZoom = zoom.coerceIn(2, 8))

    fun toggleLayer(draft: TemplateImportDraft, layer: TemplateAdjustLayer): TemplateImportDraft {
        val next = if (layer in draft.visibleLayers) draft.visibleLayers - layer else draft.visibleLayers + layer
        return draft.copy(visibleLayers = next)
    }

    fun reset(draft: TemplateImportDraft): TemplateImportDraft =
        draft.copy(adjustmentParams = TemplateAdjustmentParams())
}

enum class TemplateAdjustField {
    OFFSET_X,
    OFFSET_Y,
    SCALE_PER_MILLE,
    TOP_FEATURE_OFFSET_Y,
    CLIP_INSET_RIGHT,
    CLIP_INSET_BOTTOM,
}
