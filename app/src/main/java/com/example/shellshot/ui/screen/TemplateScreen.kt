package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.zIndex
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.template.TemplateImportDraft
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

private val TemplateBlue = Color(0xFF007AFF)
private val TemplateRed = Color(0xFFFF3B30)

@OptIn(ExperimentalFoundationApi::class)
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
    onDetailToggle: (Boolean) -> Unit = {},
) {
    val darkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val activeTemplate = uiState.selectedTemplate
    val otherTemplates = uiState.templates.filter { it.id != activeTemplate?.id }
    var detailTemplate by remember { mutableStateOf<ShellTemplate?>(null) }
    var detailVisible by remember { mutableStateOf(false) }
    var pendingDeleteTemplate by remember { mutableStateOf<ShellTemplate?>(null) }
    var pageVisible by remember { mutableStateOf(false) }
    var deletingTemplateId by remember { mutableStateOf<String?>(null) }
    var detailSourceY by remember { mutableStateOf<Dp?>(null) }
    val templateCenterYById = remember { mutableStateMapOf<String, Dp>() }
    val density = LocalDensity.current
    val scope = rememberCoroutineScope()

    fun openDetail(template: ShellTemplate) {
        detailSourceY = templateCenterYById[template.id]
        detailTemplate = template
        detailVisible = false
    }

    fun closeDetail() {
        detailVisible = false
    }

    LaunchedEffect(Unit) {
        pageVisible = true
    }
    LaunchedEffect(detailTemplate?.id) {
        if (detailTemplate != null) {
            delay(16)
            detailVisible = true
        }
    }
    LaunchedEffect(detailVisible) {
        onDetailToggle(detailVisible)
        if (!detailVisible) {
            delay(340)
            if (!detailVisible) {
                detailTemplate = null
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (darkTheme) Color.Black else Color(0xFFF2F2F7)),
    ) {
        AnimatedVisibility(
            visible = pageVisible,
            enter = slideInVertically(
                initialOffsetY = { 40 },
                animationSpec = spring(stiffness = 300f, dampingRatio = 0.8f),
            ) + fadeIn(),
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 20.dp),
            ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 64.dp, bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "模板",
                            fontSize = 34.sp,
                            lineHeight = 38.sp,
                            fontWeight = FontWeight.Black,
                            color = if (darkTheme) Color.White else Color.Black,
                        )
                        IconButton(
                            onClick = onUploadTemplateImage,
                            modifier = Modifier
                                .size(44.dp)
                                .shadow(
                                    elevation = 4.dp,
                                    shape = CircleShape,
                                    ambientColor = Color.Black.copy(alpha = 0.10f),
                                    spotColor = Color.Black.copy(alpha = 0.10f),
                                )
                                .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White, CircleShape)
                                .border(
                                    width = 1.dp,
                                    color = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color(0xFFE5E5EA),
                                    shape = CircleShape,
                                ),
                        ) {
                            AppIcon(
                                icon = AppIconId.Add,
                                contentDescription = "新增模板",
                                tint = if (darkTheme) Color.White else Color.Black,
                                modifier = Modifier.size(22.dp),
                            )
                        }
                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 120.dp),
                        contentPadding = PaddingValues(bottom = 120.dp),
                    ) {
                        item(key = "current-section") {
                            SectionLabel("当前使用", darkTheme)
                        }
                        if (activeTemplate != null) {
                            item(key = activeTemplate.id) {
                                val isDeleting = deletingTemplateId == activeTemplate.id
                                val deleteOffsetY by animateDpAsState(
                                    targetValue = if (isDeleting) 320.dp else 0.dp,
                                    animationSpec = tween(360),
                                    label = "active-template-delete-y-${activeTemplate.id}",
                                )
                                val deleteAlpha by animateFloatAsState(
                                    targetValue = if (isDeleting) 0f else 1f,
                                    animationSpec = tween(260),
                                    label = "active-template-delete-alpha-${activeTemplate.id}",
                                )
                                val deleteScale by animateFloatAsState(
                                    targetValue = if (isDeleting) 0.92f else 1f,
                                    animationSpec = tween(320),
                                    label = "active-template-delete-scale-${activeTemplate.id}",
                                )
                                val sourceAlpha by animateFloatAsState(
                                    targetValue = if (detailVisible && detailTemplate?.id == activeTemplate.id) 0f else 1f,
                                    animationSpec = tween(160),
                                    label = "active-template-source-alpha-${activeTemplate.id}",
                                )

                                TemplateCard(
                                    template = activeTemplate,
                                    isActive = true,
                            darkTheme = darkTheme,
                            modifier = Modifier
                                .animateItem()
                                .offset(y = deleteOffsetY)
                                .alpha(deleteAlpha * sourceAlpha)
                                .scale(deleteScale),
                            onClick = { openDetail(activeTemplate) },
                            onCenterYChanged = { centerY -> templateCenterYById[activeTemplate.id] = centerY },
                        )
                            }
                        } else {
                            item(key = "empty-current") {
                                EmptyTemplateCard(darkTheme = darkTheme)
                            }
                        }

                        item(key = "backup-space") {
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                        item(key = "backup-section") {
                            SectionLabel("备选模板", darkTheme)
                        }

                        if (otherTemplates.isEmpty()) {
                            item(key = "empty-backup") {
                                EmptyTemplateCard(
                                    darkTheme = darkTheme,
                                    text = if (uiState.templates.isEmpty()) "当前还没有可用模板。" else "没有其他备选模板。",
                                )
                            }
                        } else {
                            itemsIndexed(otherTemplates, key = { _, item -> item.id }) { index, template ->
                                val isDeleting = deletingTemplateId == template.id
                                val stackOffsetY by animateDpAsState(
                                    targetValue = if (index > 0) (-18 * index).dp else 0.dp,
                                    animationSpec = spring(stiffness = 360f, dampingRatio = 0.86f),
                                    label = "template-stack-offset-${template.id}",
                                )
                                val deleteOffsetY by animateDpAsState(
                                    targetValue = if (isDeleting) 320.dp else 0.dp,
                                    animationSpec = tween(360),
                                    label = "template-delete-y-${template.id}",
                                )
                                val deleteAlpha by animateFloatAsState(
                                    targetValue = if (isDeleting) 0f else 1f,
                                    animationSpec = tween(260),
                                    label = "template-delete-alpha-${template.id}",
                                )
                                val deleteScale by animateFloatAsState(
                                    targetValue = if (isDeleting) 0.92f else 1f,
                                    animationSpec = tween(320),
                                    label = "template-delete-scale-${template.id}",
                                )
                                val sourceAlpha by animateFloatAsState(
                                    targetValue = if (detailVisible && detailTemplate?.id == template.id) 0f else 1f,
                                    animationSpec = tween(160),
                                    label = "template-source-alpha-${template.id}",
                                )

                                TemplateCard(
                                    template = template,
                                    isActive = false,
                                    darkTheme = darkTheme,
                                    modifier = Modifier
                                        .animateItem()
                                        .zIndex(if (detailTemplate?.id == template.id && detailVisible) 100f else index.toFloat())
                                        .offset(y = stackOffsetY + deleteOffsetY)
                                        .alpha(deleteAlpha * sourceAlpha)
                                        .scale(deleteScale),
                                    onClick = { openDetail(template) },
                                    onCenterYChanged = { centerY -> templateCenterYById[template.id] = centerY },
                                    stackIndex = index,
                                    stackCount = otherTemplates.size,
                                )
                            }
                        }
                    }
                }
            }

        detailTemplate?.let { template ->
            TemplateDetailOverlay(
                template = template,
                visible = detailVisible,
                darkTheme = darkTheme,
                activeTemplateId = activeTemplate?.id,
                importInProgress = uiState.templateImportInProgress,
                sourceCenterY = detailSourceY,
                onClose = { closeDetail() },
                onDelete = {
                    pendingDeleteTemplate = template
                    closeDetail()
                },
                onApply = {
                    onSelectTemplate(template.id)
                    closeDetail()
                },
                modifier = Modifier.zIndex(200f),
            )
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
        AlertDialogCard(
            title = alert.title,
            message = alert.message,
            confirmText = "我知道了",
            onConfirm = onDismissImportAlert,
            onDismiss = onDismissImportAlert,
        )
    }

    pendingDeleteTemplate?.let { template ->
        DeleteTemplateConfirmDialog(
            template = template,
            darkTheme = darkTheme,
            onConfirm = {
                pendingDeleteTemplate = null
                scope.launch {
                    deletingTemplateId = template.id
                    delay(380)
                    onDeleteTemplate(template.id)
                    deletingTemplateId = null
                }
            },
            onDismiss = { pendingDeleteTemplate = null },
        )
    }
}

@Composable
private fun SectionLabel(text: String, darkTheme: Boolean) {
    Text(
        text = text,
        fontSize = 13.sp,
        fontWeight = FontWeight.Bold,
        color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray,
        modifier = Modifier.padding(start = 4.dp, bottom = 12.dp),
    )
}

@Composable
private fun TemplateDetailOverlay(
    template: ShellTemplate,
    visible: Boolean,
    darkTheme: Boolean,
    activeTemplateId: String?,
    importInProgress: Boolean,
    sourceCenterY: Dp?,
    onClose: () -> Unit,
    onDelete: () -> Unit,
    onApply: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val transition = updateTransition(targetState = visible, label = "template-detail-overlay")
    val cardAlpha by transition.animateFloat(
        transitionSpec = { tween(durationMillis = 220) },
        label = "template-detail-card-alpha",
    ) { shown ->
        if (shown) 1f else 0f
    }
    val cardScale by transition.animateFloat(
        transitionSpec = { spring(stiffness = 420f, dampingRatio = 0.82f) },
        label = "template-detail-card-scale",
    ) { shown ->
        if (shown) 1f else 0.58f
    }
    val contentAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 180, delayMillis = if (visible) 120 else 0),
        label = "template-detail-content-alpha",
    )
    val contentOffsetY by animateDpAsState(
        targetValue = if (visible) 0.dp else 18.dp,
        animationSpec = tween(durationMillis = 220, delayMillis = if (visible) 120 else 0),
        label = "template-detail-content-y",
    )

    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val sourceOffsetY = (sourceCenterY ?: (maxHeight / 2)) - (maxHeight / 2)
        val cardOffsetY by transition.animateDp(
            transitionSpec = { spring(stiffness = 420f, dampingRatio = 0.86f) },
            label = "template-detail-card-y",
        ) { shown ->
            if (shown) 0.dp else sourceOffsetY
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClose,
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth(0.80f)
                .offset(y = cardOffsetY)
                .graphicsLayer {
                    alpha = cardAlpha
                    scaleX = cardScale
                    scaleY = cardScale
                }
                .shadow(40.dp, RoundedCornerShape(48.dp), spotColor = Color.Black.copy(alpha = 0.18f))
                .clip(RoundedCornerShape(48.dp))
                .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White)
                .border(
                    width = 1.dp,
                    color = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.9f),
                    shape = RoundedCornerShape(48.dp),
                )
                .padding(24.dp)
                .clickable(enabled = false) {},
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    TemplateCircleButton(
                        icon = AppIconId.Delete,
                        tint = if (template.canDelete) TemplateRed else Color.Gray,
                        background = TemplateRed.copy(alpha = if (template.canDelete) 0.10f else 0.04f),
                        enabled = template.canDelete && !importInProgress,
                        contentDescription = "删除模板",
                        onClick = onDelete,
                    )
                    TemplateCircleButton(
                        icon = AppIconId.Close,
                        tint = if (darkTheme) Color.White else Color.Gray,
                        background = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFF2F2F7),
                        contentDescription = "关闭",
                        onClick = onClose,
                    )
                }

                Column(
                    modifier = Modifier
                        .offset(y = contentOffsetY)
                        .graphicsLayer { alpha = contentAlpha },
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    TemplatePreviewThumbnail(
                        previewPath = template.previewAsset,
                        contentDescription = template.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                            .scale(if (visible) 1f else 0.96f),
                        cornerRadius = 24.dp,
                        imagePadding = 0.dp,
                        selected = template.id == activeTemplateId,
                    )

                    Spacer(modifier = Modifier.height(18.dp))
                    Text(
                        text = template.name,
                        fontSize = 21.sp,
                        lineHeight = 25.sp,
                        fontWeight = FontWeight.Black,
                        color = if (darkTheme) Color.White else Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(22.dp))

                    DetailRow(
                        label = "分辨率",
                        value = templateResolutionLabel(template),
                        darkTheme = darkTheme,
                    )
                    DetailRow(
                        label = "模板类型",
                        value = if (template.isBuiltIn) "内置模板" else "用户自定义",
                        darkTheme = darkTheme,
                    )

                    Spacer(modifier = Modifier.height(26.dp))

                    Button(
                        onClick = onApply,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(52.dp),
                        enabled = template.id != activeTemplateId,
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = TemplateBlue,
                            disabledContainerColor = Color(0xFFE5E5EA),
                            disabledContentColor = Color.Gray,
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 8.dp),
                    ) {
                        Text(
                            text = if (template.id == activeTemplateId) "当前使用中" else "应用模板",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Black,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TemplateCircleButton(
    icon: AppIconId,
    tint: Color,
    background: Color,
    contentDescription: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) 0.92f else 1f,
        animationSpec = spring(stiffness = 520f, dampingRatio = 0.72f),
        label = "template-circle-button-scale",
    )

    Box(
        modifier = Modifier
            .size(36.dp)
            .scale(scale)
            .clip(RoundedCornerShape(999.dp))
            .background(background)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = icon,
            contentDescription = contentDescription,
            tint = tint,
            modifier = Modifier.size(18.dp),
        )
    }
}

@Composable
private fun TemplateCard(
    template: ShellTemplate,
    isActive: Boolean,
    darkTheme: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onCenterYChanged: (Dp) -> Unit = {},
    stackIndex: Int? = null,
    stackCount: Int = 0,
) {
    val shape = RoundedCornerShape(40.dp)
    val density = LocalDensity.current
    val stacked = stackIndex != null
    val stackDepth = stackIndex?.coerceAtMost(5) ?: 0
    val stackInset = if (stacked) (stackDepth * 2).dp else 0.dp
    val stackScale = if (stacked) 1f - (stackDepth * 0.003f) else 1f
    val stackSurface = when {
        isActive -> if (darkTheme) Color(0xFF1C1C1E) else Color.White
        darkTheme -> Color(0xFF1C1C1E).copy(alpha = 1f - stackDepth * 0.035f)
        else -> Color.White
    }
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val pressScale by animateFloatAsState(
        targetValue = if (pressed) 0.985f else 1f,
        animationSpec = spring(stiffness = 520f, dampingRatio = 0.74f),
        label = "template-card-press-scale-${template.id}",
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = stackInset)
            .scale(pressScale * stackScale)
            .height(if (stacked) 86.dp else 104.dp)
            .shadow(
                elevation = if (stacked) (22 - stackDepth).dp else 12.dp,
                shape = shape,
                spotColor = Color.Black.copy(alpha = if (darkTheme) 0.34f else 0.13f),
            )
            .clip(shape)
            .background(stackSurface)
            .border(
                1.dp,
                if (isActive) {
                    TemplateBlue.copy(alpha = 0.22f)
                } else if (darkTheme) {
                    Color.White.copy(alpha = 0.08f + stackDepth * 0.015f)
                } else {
                    Color(0xFFE5E7EB).copy(alpha = 0.78f + stackDepth * 0.035f)
                },
                shape,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .onGloballyPositioned { coordinates ->
                val centerY = coordinates.boundsInRoot().center.y
                onCenterYChanged(with(density) { centerY.toDp() })
            },
    ) {
        if (stacked) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(18.dp)
                    .align(Alignment.TopCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black.copy(alpha = if (darkTheme) 0.16f else 0.055f),
                                Color.Black.copy(alpha = if (darkTheme) 0.05f else 0.018f),
                                Color.Transparent,
                            ),
                        ),
                    ),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .align(Alignment.TopCenter)
                    .background(if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.White.copy(alpha = 0.9f)),
            )
            Box(
                modifier = Modifier
                    .width((44 + stackDepth * 8).dp)
                    .height(2.dp)
                    .align(Alignment.TopCenter)
                    .clip(RoundedCornerShape(999.dp))
                    .background(if (darkTheme) Color.White.copy(alpha = 0.06f) else Color.Black.copy(alpha = 0.035f)),
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = if (stacked) 24.dp else 20.dp, vertical = if (stacked) 16.dp else 20.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .size(if (stacked) 48.dp else 64.dp),
                contentAlignment = Alignment.Center,
            ) {
                TemplatePreviewThumbnail(
                    previewPath = template.previewAsset,
                    contentDescription = template.name,
                    modifier = Modifier.fillMaxSize(),
                    cornerRadius = if (stacked) 12.dp else 14.dp,
                    imagePadding = 0.dp,
                    selected = isActive,
                )
            }

            Spacer(modifier = Modifier.width(if (stacked) 18.dp else 16.dp))

            if (stacked) {
                Text(
                    text = template.name,
                    fontSize = 17.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (darkTheme) Color.White else Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = templateResolutionLabel(template),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF9CA3AF),
                    maxLines = 1,
                )
            } else {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = template.name,
                        fontSize = 17.sp,
                        lineHeight = 22.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (darkTheme) Color.White else Color.Black,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(bottom = 8.dp),
                    )
                    Text(
                        text = templateResolutionLabel(template),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = TemplateBlue,
                        maxLines = 1,
                    )
                }
            }
        }
    }
}

@Composable
private fun DetailRow(label: String, value: String, darkTheme: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .background(if (darkTheme) Color.White.copy(alpha = 0.06f) else Color(0xFFF9F9FB), RoundedCornerShape(12.dp))
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(value, color = if (darkTheme) Color.White else Color.Black, fontWeight = FontWeight.Black, fontSize = 14.sp)
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
        onDismissRequest = { if (!inProgress) onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        DialogSurface(darkTheme = false, cornerRadius = 28.dp) {
            Text("新增模板", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
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
                    focusedBorderColor = TemplateBlue.copy(alpha = 0.5f),
                    unfocusedBorderColor = Color(0xFFE8ECF3),
                ),
            )
            DialogPrimaryButton(
                text = if (inProgress) "生成中..." else "生成模板",
                enabled = draft.templateName.isNotBlank() && !inProgress,
                onClick = onConfirm,
            )
            DialogSecondaryButton(text = "取消", enabled = !inProgress, onClick = onDismiss)
        }
    }
}

@Composable
private fun AlertDialogCard(
    title: String,
    message: String,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        DialogSurface(darkTheme = false, cornerRadius = 28.dp) {
            Text(title, fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.Black)
            Text(message, fontSize = 14.sp, lineHeight = 20.sp, color = Color.Gray)
            DialogPrimaryButton(text = confirmText, onClick = onConfirm)
        }
    }
}

@Composable
private fun DeleteTemplateConfirmDialog(
    template: ShellTemplate,
    darkTheme: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        DialogSurface(darkTheme = darkTheme, cornerRadius = 40.dp) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(18.dp))
                    .background(TemplateRed.copy(alpha = 0.10f)),
                contentAlignment = Alignment.Center,
            ) {
                AppIcon(
                    icon = AppIconId.Delete,
                    contentDescription = null,
                    tint = TemplateRed,
                    modifier = Modifier.size(25.dp),
                )
            }
            Text(
                text = "确认移除模板",
                fontSize = 22.sp,
                lineHeight = 26.sp,
                fontWeight = FontWeight.Black,
                color = if (darkTheme) Color.White else Color.Black,
            )
            Text(
                text = template.name,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Bold,
                color = if (darkTheme) Color.White.copy(alpha = 0.78f) else Color(0xFF3A3A3C),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "该操作将从本机移除此模板配置及其预览资源，完成后无法恢复。",
                fontSize = 13.sp,
                lineHeight = 19.sp,
                color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray,
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(1.dp, if (darkTheme) Color.White.copy(alpha = 0.10f) else Color(0xFFE5E5EA)),
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (darkTheme) Color.White.copy(alpha = 0.06f) else Color(0xFFF2F2F7),
                        contentColor = if (darkTheme) Color.White else Color.Black,
                    ),
                ) {
                    Text("取消", fontWeight = FontWeight.Bold)
                }
                Button(
                    onClick = onConfirm,
                    modifier = Modifier
                        .weight(1f)
                        .height(52.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = TemplateRed),
                    elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                ) {
                    Text("删除", fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

@Composable
private fun DialogSurface(
    darkTheme: Boolean,
    cornerRadius: Dp,
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 22.dp, vertical = 28.dp)
            .widthIn(max = 420.dp)
            .clip(RoundedCornerShape(cornerRadius))
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White.copy(alpha = 0.96f))
            .border(
                1.dp,
                if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.White.copy(alpha = 0.92f),
                RoundedCornerShape(cornerRadius),
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = content,
    )
}

@Composable
private fun DialogPrimaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(containerColor = TemplateBlue),
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun DialogSecondaryButton(
    text: String,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, Color(0xFFE8ECF3)),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
    ) {
        Text(text, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun EmptyTemplateCard(
    darkTheme: Boolean,
    text: String = "当前还没有可用模板。",
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(40.dp))
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White)
            .border(
                1.dp,
                if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFE5E5EA),
                RoundedCornerShape(40.dp),
            )
            .padding(22.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(AppIconId.Template, contentDescription = null, tint = Color.Gray, modifier = Modifier.size(22.dp))
            Text(text, color = Color.Gray, modifier = Modifier.padding(start = 10.dp))
        }
    }
}

private fun templateResolutionLabel(template: ShellTemplate): String {
    val width = template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width
    val height = template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height
    return "$width × $height"
}
