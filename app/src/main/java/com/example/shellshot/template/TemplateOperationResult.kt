package com.example.shellshot.template

data class TemplateImportResult(
    val success: Boolean,
    val templateId: String? = null,
    val message: String,
)

data class TemplateDeleteResult(
    val success: Boolean,
    val deletedTemplateId: String? = null,
    val message: String,
)
