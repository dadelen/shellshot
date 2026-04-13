package com.example.shellshot.template

data class TemplateImportDraft(
    val sourceImagePath: String,
    val templateName: String,
    val validationWarning: String? = null,
)
