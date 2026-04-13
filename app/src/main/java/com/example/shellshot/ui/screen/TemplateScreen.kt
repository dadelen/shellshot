package com.example.shellshot.ui.screen

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.TemplateImportDraft
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.components.ZipCountPill
import com.example.shellshot.ui.components.ZipGlassCard
import com.example.shellshot.ui.components.ZipStaggeredReveal

private val TemplateBlue = Color(0xFF007AFF)
private val TemplateRed = Color(0xFFFF4D4F)

@Suppress("UnusedParameter")
@Composable
fun TemplateScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onSelectTemplate: (String) -> Unit,
    onUploadTemplateImage: () -> Unit,
    onDeleteTemplate: (String) -> Unit,
    onUpdateImportName: (String) -> Unit,
    onConfirmImport: () -> Unit,
    onCancelImport: () -> Unit,
    onDismissImportAlert: () -> Unit,
    onCancelPage: () -> Unit,
) {
    val darkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5f
    var pendingDeleteTemplate by remember { mutableStateOf<ShellTemplate?>(null) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
        contentPadding = PaddingValues(top = 44.dp, bottom = 132.dp),
        verticalArrangement = Arrangement.spacedBy(18.dp),
    ) {
        item {
            ZipStaggeredReveal(index = 0) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "手机模板库",
                        fontSize = 28.sp,
                        lineHeight = 32.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (darkTheme) Color.White else Color(0xFF15171A),
                    )
                    ZipCountPill(
                        text = "共 ${uiState.templates.size} 条",
                        dotColor = TemplateBlue,
                        darkTheme = darkTheme,
                    )
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 1) {
                AddTemplateCard(
                    darkTheme = darkTheme,
                    inProgress = uiState.templateImportInProgress,
                    onClick = onUploadTemplateImage,
                )
            }
        }

        if (uiState.templates.isEmpty()) {
            item {
                ZipStaggeredReveal(index = 2) {
                    ZipGlassCard(
                        darkTheme = darkTheme,
                        modifier = Modifier.fillMaxWidth(),
                        cornerRadius = 40.dp,
                        contentPadding = PaddingValues(horizontal = 20.dp, vertical = 18.dp),
                    ) {
                        Text(
                            text = "当前还没有可用模板。",
                            fontSize = 14.sp,
                            color = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280),
                        )
                    }
                }
            }
        } else {
            itemsIndexed(uiState.templates, key = { _, item -> item.id }) { index, template ->
                ZipStaggeredReveal(index = index + 2) {
                    TemplateRow(
                        template = template,
                        darkTheme = darkTheme,
                        isActive = template.id == uiState.selectedTemplate?.id,
                        onUse = { onSelectTemplate(template.id) },
                        onDelete = if (template.canDelete && !uiState.templateImportInProgress) {
                            { pendingDeleteTemplate = template }
                        } else {
                            null
                        },
                    )
                }
            }
        }
    }

    uiState.templateImportDraft?.let { draft ->
        TemplateImportDialog(
            draft = draft,
            inProgress = uiState.templateImportInProgress,
            onUpdateName = onUpdateImportName,
            onConfirm = onConfirmImport,
            onDismiss = onCancelImport,
        )
    }

    uiState.templateImportAlert?.let { alert ->
        TemplateAlertDialog(
            title = alert.title,
            message = alert.message,
            onDismiss = onDismissImportAlert,
        )
    }

    pendingDeleteTemplate?.let { template ->
        DeleteTemplateConfirmDialog(
            template = template,
            onConfirm = {
                onDeleteTemplate(template.id)
                pendingDeleteTemplate = null
            },
            onDismiss = { pendingDeleteTemplate = null },
        )
    }
}

@Composable
private fun AddTemplateCard(
    darkTheme: Boolean,
    inProgress: Boolean,
    onClick: () -> Unit,
) {
    val shape = RoundedCornerShape(40.dp)
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(shape)
            .drawBehind {
                drawRoundRect(
                    color = if (darkTheme) Color.White.copy(alpha = 0.16f) else Color(0xFFD8DEE8),
                    cornerRadius = CornerRadius(40.dp.toPx(), 40.dp.toPx()),
                    style = Stroke(
                        width = 1.5.dp.toPx(),
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(14.dp.toPx(), 10.dp.toPx()),
                        ),
                    ),
                )
            }
            .clickable(enabled = !inProgress, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 20.dp),
    ) {
        Row(
            modifier = Modifier.align(Alignment.Center),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(
                        if (darkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.72f),
                        RoundedCornerShape(12.dp),
                    )
                    .border(
                        1.dp,
                        if (darkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.9f),
                        RoundedCornerShape(12.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    imageVector = Icons.Rounded.Add,
                    contentDescription = null,
                    tint = if (darkTheme) Color(0xFFD4D4D8) else Color(0xFF8A8F98),
                    modifier = Modifier.size(18.dp),
                )
            }
            Text(
                text = if (inProgress) "正在准备模板..." else "新增模板",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (darkTheme) Color(0xFFD4D4D8) else Color(0xFF5E6672),
            )
        }
    }
}

@Composable
private fun TemplateRow(
    template: ShellTemplate,
    darkTheme: Boolean,
    isActive: Boolean,
    onUse: () -> Unit,
    onDelete: (() -> Unit)?,
) {
    val outerShape = RoundedCornerShape(32.dp)
    val previewShape = RoundedCornerShape(12.dp)
    val activeRingFill = if (darkTheme) TemplateBlue.copy(alpha = 0.12f) else TemplateBlue.copy(alpha = 0.08f)
    val activeRingStroke = if (darkTheme) TemplateBlue.copy(alpha = 0.42f) else TemplateBlue.copy(alpha = 0.22f)
    val previewPlateBackground = if (darkTheme) Color.White.copy(alpha = 0.06f) else Color(0xFFF8F9FC)
    val previewPlateBorder = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFE9EDF5)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(outerShape)
            .background(if (isActive) activeRingFill else Color.Transparent, outerShape)
            .border(
                width = if (isActive) 1.5.dp else 0.dp,
                color = if (isActive) activeRingStroke else Color.Transparent,
                shape = outerShape,
            )
            .padding(if (isActive) 4.dp else 0.dp),
    ) {
        ZipGlassCard(
            darkTheme = darkTheme,
            modifier = Modifier.fillMaxWidth(),
            cornerRadius = 32.dp,
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 20.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(54.dp)
                        .background(previewPlateBackground, previewShape)
                        .border(1.dp, previewPlateBorder, previewShape),
                    contentAlignment = Alignment.Center,
                ) {
                    if (template.previewAsset.isNotBlank()) {
                        TemplatePreviewThumbnail(
                            previewPath = template.previewAsset,
                            contentDescription = template.name,
                            modifier = Modifier.size(width = 28.dp, height = 42.dp),
                            cornerRadius = 8.dp,
                            imagePadding = 1.dp,
                            selected = false,
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Rounded.PhoneIphone,
                            contentDescription = null,
                            tint = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF9CA3AF),
                            modifier = Modifier.size(22.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(4.dp),
                ) {
                    Text(
                        text = template.name,
                        fontSize = 16.sp,
                        lineHeight = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (darkTheme) Color.White else Color(0xFF15171A),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = templateTypeLabel(template),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF7B8492),
                        maxLines = 1,
                    )
                    Text(
                        text = templateResolutionLabel(template),
                        fontSize = 12.sp,
                        color = if (darkTheme) Color(0xFF8D939D) else Color(0xFF9AA2AE),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (!isActive && onDelete != null) {
                        Box(
                            modifier = Modifier
                                .size(40.dp)
                                .background(
                                    TemplateRed.copy(alpha = if (darkTheme) 0.16f else 0.1f),
                                    RoundedCornerShape(999.dp),
                                )
                                .border(
                                    1.dp,
                                    TemplateRed.copy(alpha = if (darkTheme) 0.22f else 0.12f),
                                    RoundedCornerShape(999.dp),
                                )
                                .clickable(onClick = onDelete),
                            contentAlignment = Alignment.Center,
                        ) {
                            Icon(
                                imageVector = Icons.Rounded.Delete,
                                contentDescription = "删除模板",
                                tint = TemplateRed,
                                modifier = Modifier.size(18.dp),
                            )
                        }
                    }

                    Box(
                        modifier = Modifier
                            .height(42.dp)
                            .background(
                                if (isActive) TemplateBlue else {
                                    if (darkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.84f)
                                },
                                RoundedCornerShape(999.dp),
                            )
                            .border(
                                1.dp,
                                if (isActive) Color.Transparent else {
                                    if (darkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.94f)
                                },
                                RoundedCornerShape(999.dp),
                            )
                            .clickable(onClick = onUse)
                            .padding(horizontal = 18.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = if (isActive) "当前使用" else "使用",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = if (isActive) Color.White else if (darkTheme) Color(0xFFE4E4E7) else Color(0xFF4F5865),
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TemplateImportDialog(
    draft: TemplateImportDraft,
    inProgress: Boolean,
    onUpdateName: (String) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = {
            if (!inProgress) {
                onDismiss()
            }
        },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        DialogCard {
            Text(
                text = "新增模板",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827),
            )

            TemplatePreviewThumbnail(
                previewPath = draft.sourceImagePath,
                contentDescription = draft.templateName,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(220.dp),
                cornerRadius = 24.dp,
                imagePadding = 10.dp,
            )

            OutlinedTextField(
                value = draft.templateName,
                onValueChange = onUpdateName,
                label = { Text("模板名称") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                enabled = !inProgress,
                shape = RoundedCornerShape(18.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.White.copy(alpha = 0.84f),
                    unfocusedContainerColor = Color.White.copy(alpha = 0.68f),
                    focusedBorderColor = TemplateBlue.copy(alpha = 0.5f),
                    unfocusedBorderColor = Color(0xFFE8ECF3),
                ),
            )

            DialogPrimaryButton(
                text = if (inProgress) "生成中..." else "生成模板",
                enabled = draft.templateName.isNotBlank() && !inProgress,
                onClick = onConfirm,
            )
            DialogSecondaryButton(
                text = "取消",
                enabled = !inProgress,
                onClick = onDismiss,
            )
        }
    }
}

@Composable
private fun TemplateAlertDialog(
    title: String,
    message: String,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        DialogCard {
            Text(
                text = title,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827),
            )
            Text(
                text = message,
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
            )
            DialogPrimaryButton(
                text = "我知道了",
                onClick = onDismiss,
            )
        }
    }
}

@Composable
private fun DeleteTemplateConfirmDialog(
    template: ShellTemplate,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        DialogCard {
            Text(
                text = "删除模板",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF111827),
            )
            Text(
                text = "确认删除这个模板吗？删除后无法恢复。",
                fontSize = 14.sp,
                color = Color(0xFF6B7280),
            )

            TemplatePreviewThumbnail(
                previewPath = template.previewAsset,
                contentDescription = template.name,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp),
                cornerRadius = 22.dp,
                imagePadding = 8.dp,
            )

            Text(
                text = template.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFF111827),
            )

            OutlinedButton(
                onClick = onConfirm,
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    containerColor = TemplateRed.copy(alpha = 0.08f),
                    contentColor = TemplateRed,
                ),
                border = BorderStroke(1.dp, TemplateRed.copy(alpha = 0.2f)),
            ) {
                Text(
                    text = "确认删除",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold,
                )
            }

            DialogSecondaryButton(
                text = "取消",
                onClick = onDismiss,
            )
        }
    }
}

@Composable
private fun DialogCard(
    content: @Composable () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 28.dp)
            .widthIn(max = 420.dp)
            .background(Color.White.copy(alpha = 0.95f), RoundedCornerShape(28.dp))
            .border(1.dp, Color.White.copy(alpha = 0.92f), RoundedCornerShape(28.dp))
            .padding(22.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = { content() },
    )
}

@Composable
private fun DialogPrimaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(if (enabled) TemplateBlue else TemplateBlue.copy(alpha = 0.45f))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color.White,
        )
    }
}

@Composable
private fun DialogSecondaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(Color(0xFFF7F8FB))
            .border(1.dp, Color(0xFFE8ECF3), RoundedCornerShape(18.dp))
            .clickable(enabled = enabled, onClick = onClick)
            .padding(horizontal = 18.dp, vertical = 14.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = if (enabled) Color(0xFF111827) else Color(0xFF9CA3AF),
        )
    }
}

private fun templateTypeLabel(template: ShellTemplate): String {
    return if (template.isBuiltIn) "内置模板" else "用户定制"
}

private fun templateResolutionLabel(template: ShellTemplate): String {
    val width = template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width
    val height = template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height
    return "${width} × ${height}"
}
