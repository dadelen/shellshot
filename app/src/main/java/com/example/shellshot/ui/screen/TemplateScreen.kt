package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material3.Slider
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
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.boundsInRoot
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.input.pointer.pointerInput
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
import com.example.shellshot.ui.components.bentoCard
import com.example.shellshot.ui.theme.AppTypography
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.R
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TemplateScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onSelectTemplate: (String) -> Unit,
    onUploadTemplateImage: () -> Unit,
    onDeleteTemplate: (String) -> Unit,
    onUpdateImportName: (String) -> Unit,
    onUpdateCalibration: (centerX: Float, centerY: Float, width: Float, cornerRadius: Float) -> Unit,
    onResetCalibration: () -> Unit,
    onToggleCalibrationGuides: (Boolean) -> Unit,
    onConfirmImport: () -> Unit,
    onCancelImport: () -> Unit,
    onDismissImportAlert: () -> Unit,
    onCancelPage: () -> Unit,
    onDetailToggle: (Boolean) -> Unit = {},
) {
    val isDark = androidx.compose.material3.MaterialTheme.colorScheme.background.red < 0.5f

    val activeTemplate = uiState.selectedTemplate
    val otherTemplates = uiState.templates.filter { it.id != activeTemplate?.id }
    var detailTemplate by remember { mutableStateOf<ShellTemplate?>(null) }
    var detailVisible by remember { mutableStateOf(false) }
    var pendingDeleteTemplate by remember { mutableStateOf<ShellTemplate?>(null) }
    var pageVisible by remember { mutableStateOf(false) }
    var deletingTemplateId by remember { mutableStateOf<String?>(null) }
    var detailSourceY by remember { mutableStateOf<Dp?>(null) }
    val templateCenterYById = remember { mutableStateMapOf<String, Dp>() }
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
            delay(360)
            if (!detailVisible) {
                detailTemplate = null
            }
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ShellColors.background(isDark)),
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
                    .padding(horizontal = 24.dp),
            ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 64.dp, bottom = 24.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = stringResource(R.string.templates_title),
                            style = AppTypography.displayLarge,
                            color = ShellColors.textPrimary(isDark),
                        )
                        IconButton(
                            onClick = onUploadTemplateImage,
                            modifier = Modifier
                                .size(44.dp)
                                .bentoCard(isDark, cornerRadius = 22.dp)
                        ) {
                            AppIcon(
                                icon = AppIconId.Add,
                                contentDescription = "Add Template",
                                tint = ShellColors.textPrimary(isDark),
                                modifier = Modifier.size(20.dp),
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
                            SectionLabel(stringResource(R.string.templates_active_section), isDark)
                        }
                        if (activeTemplate != null) {
                            item(key = activeTemplate.id) {
                                val isDeleting = deletingTemplateId == activeTemplate.id
                                val deleteOffsetY by animateDpAsState(targetValue = if (isDeleting) 320.dp else 0.dp, animationSpec = tween(360), label = "delete-y")
                                val deleteAlpha by animateFloatAsState(targetValue = if (isDeleting) 0f else 1f, animationSpec = tween(260), label = "delete-alpha")
                                val deleteScale by animateFloatAsState(targetValue = if (isDeleting) 0.92f else 1f, animationSpec = tween(320), label = "delete-scale")
                                val sourceAlpha by animateFloatAsState(targetValue = if (detailVisible && detailTemplate?.id == activeTemplate.id) 0f else 1f, animationSpec = tween(160), label = "source-alpha")

                                TemplateCard(
                                    template = activeTemplate,
                                    isActive = true,
                                    darkTheme = isDark,
                                    modifier = Modifier
                                        .animateItem()
                                        .offset(y = deleteOffsetY)
                                        .alpha(deleteAlpha * sourceAlpha)
                                        .scale(deleteScale),
                                    onClick = { openDetail(activeTemplate) },
                                    onCenterYChanged = { centerY -> templateCenterYById[activeTemplate.id] = centerY },
                                    needsRecalibration = activeTemplate.needsRecalibration(uiState),
                                )
                            }
                        } else {
                            item(key = "empty-current") {
                                EmptyTemplateCard(darkTheme = isDark, text = stringResource(R.string.templates_no_templates))
                            }
                        }

                        item(key = "backup-space") {
                            Spacer(modifier = Modifier.height(32.dp))
                        }
                        item(key = "backup-section") {
                            SectionLabel(stringResource(R.string.templates_available_section), isDark)
                        }

                        if (otherTemplates.isEmpty()) {
                            item(key = "empty-backup") {
                                EmptyTemplateCard(
                                    darkTheme = isDark,
                                    text = if (uiState.templates.isEmpty()) stringResource(R.string.templates_no_templates) else stringResource(R.string.templates_currently_active),
                                )
                            }
                        } else {
                            itemsIndexed(otherTemplates, key = { _, item -> item.id }) { index, template ->
                                val isDeleting = deletingTemplateId == template.id
                                val stackOffsetY by animateDpAsState(
                                    targetValue = if (index > 0) (-24 * index).dp else 0.dp,
                                    animationSpec = spring(stiffness = 400f, dampingRatio = 0.82f),
                                    label = "stack-y"
                                )
                                val deleteOffsetY by animateDpAsState(targetValue = if (isDeleting) 320.dp else 0.dp, animationSpec = tween(360), label = "delete-y")
                                val deleteAlpha by animateFloatAsState(targetValue = if (isDeleting) 0f else 1f, animationSpec = tween(260), label = "delete-alpha")
                                val deleteScale by animateFloatAsState(targetValue = if (isDeleting) 0.92f else 1f, animationSpec = tween(320), label = "delete-scale")
                                val sourceAlpha by animateFloatAsState(targetValue = if (detailVisible && detailTemplate?.id == template.id) 0f else 1f, animationSpec = tween(160), label = "source-alpha")

                                TemplateCard(
                                    template = template,
                                    isActive = false,
                                    darkTheme = isDark,
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
                                    needsRecalibration = template.needsRecalibration(uiState),
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
                darkTheme = isDark,
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
            isDark = isDark,
            onUpdateName = onUpdateImportName,
            onUpdateCalibration = onUpdateCalibration,
            onResetCalibration = onResetCalibration,
            onToggleGuides = onToggleCalibrationGuides,
            onConfirm = onConfirmImport,
            onDismiss = onCancelImport,
        )
    }

    uiState.templateImportAlert?.let { alert ->
        AlertDialogCard(
            title = alert.title,
            message = alert.message,
            isDark = isDark,
            confirmText = "Got it",
            onConfirm = onDismissImportAlert,
            onDismiss = onDismissImportAlert,
        )
    }

    pendingDeleteTemplate?.let { template ->
        DeleteTemplateConfirmDialog(
            template = template,
            darkTheme = isDark,
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
        style = AppTypography.labelMedium,
        color = ShellColors.textTertiary(darkTheme),
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
    BoxWithConstraints(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        val density = LocalDensity.current
        val screenHeight = maxHeight
        val screenCenterY = screenHeight / 2

        val startOffsetY = remember(sourceCenterY) {
            if (sourceCenterY != null) sourceCenterY - screenCenterY else 300.dp
        }

        val sharedSpring = spring<Float>(stiffness = 350f, dampingRatio = 0.85f)
        val sharedSpringDp = spring<Dp>(stiffness = 350f, dampingRatio = 0.85f)
        val transition = updateTransition(targetState = visible, label = "detail-transition")

        val overlayAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 240) },
            label = "overlay-alpha",
        ) { shown -> if (shown) 1f else 0f }

        val cardOffsetY by transition.animateDp(
            transitionSpec = { if (targetState) sharedSpringDp else tween(durationMillis = 220) },
            label = "card-y",
        ) { shown -> if (shown) 40.dp else startOffsetY }

        val cardScale by transition.animateFloat(
            transitionSpec = { if (targetState) sharedSpring else tween(durationMillis = 220) },
            label = "card-scale",
        ) { shown -> if (shown) 1f else 0.82f }

        val cardAlpha by transition.animateFloat(
            transitionSpec = { if (targetState) tween(150) else tween(150) },
            label = "card-alpha",
        ) { shown -> if (shown) 1f else 0f }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = overlayAlpha * 0.6f))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClose,
                ),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp)
                .offset(y = cardOffsetY)
                .graphicsLayer {
                    scaleX = cardScale
                    scaleY = cardScale
                    alpha = cardAlpha
                }
                .bentoCard(darkTheme, cornerRadius = 48.dp, isElevated = true)
                .padding(32.dp),
        ) {
            Column {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    TemplateCircleButton(
                        icon = AppIconId.Delete,
                        tint = if (template.canDelete) ShellColors.criticalRed else ShellColors.textTertiary(darkTheme),
                        background = ShellColors.badgeBg(darkTheme),
                        enabled = template.canDelete && !importInProgress,
                        contentDescription = "Delete Template",
                        onClick = onDelete,
                    )
                    TemplateCircleButton(
                        icon = AppIconId.Close,
                        tint = ShellColors.textSecondary(darkTheme),
                        background = ShellColors.badgeBg(darkTheme),
                        contentDescription = "Close",
                        onClick = onClose,
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Box(
                        modifier = Modifier
                            .size(80.dp)
                            .bentoCard(darkTheme, cornerRadius = 28.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        TemplatePreviewThumbnail(
                            previewPath = template.previewAsset,
                            contentDescription = template.name,
                            modifier = Modifier.fillMaxSize(),
                            cornerRadius = 28.dp,
                            imagePadding = 4.dp,
                            selected = template.id == activeTemplateId,
                        )
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    Text(
                        text = template.name,
                        style = AppTypography.titleLarge,
                        color = ShellColors.textPrimary(darkTheme),
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = templateResolutionLabel(template),
                        style = AppTypography.bodyMedium,
                        color = ShellColors.textSecondary(darkTheme),
                    )

                    Spacer(modifier = Modifier.height(28.dp))

                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        DetailRow(
                            label = stringResource(R.string.templates_resolution),
                            value = templateResolutionLabel(template),
                            darkTheme = darkTheme,
                        )
                        DetailRow(
                            label = "Type",
                            value = if (template.isBuiltIn) "Built-in" else "Custom",
                            darkTheme = darkTheme,
                        )
                    }

                    Spacer(modifier = Modifier.height(28.dp))

                        Button(
                            onClick = onApply,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(56.dp),
                            enabled = template.id != activeTemplateId,
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = ShellColors.actionBg(darkTheme),
                                contentColor = ShellColors.actionText(darkTheme),
                                disabledContainerColor = ShellColors.badgeBg(darkTheme),
                                disabledContentColor = ShellColors.textTertiary(darkTheme),
                            ),
                            elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                        ) {
                            Text(
                                text = if (template.id == activeTemplateId) stringResource(R.string.templates_currently_active) else stringResource(R.string.templates_apply),
                                style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
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
        label = "btn-scale",
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
    needsRecalibration: Boolean = false,
) {
    val density = LocalDensity.current
    val stacked = stackIndex != null
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val pressScale by animateFloatAsState(
        targetValue = if (pressed) 0.985f else 1f,
        animationSpec = spring(stiffness = 520f, dampingRatio = 0.74f),
        label = "press-scale",
    )
    Box(
        modifier = modifier
            .fillMaxWidth()
            .scale(pressScale)
            .height(if (stacked) 86.dp else 248.dp)
            .bentoCard(darkTheme, cornerRadius = 28.dp, isElevated = !stacked)
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
        if (!stacked) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = template.name,
                            style = AppTypography.titleLarge,
                            color = ShellColors.textPrimary(darkTheme),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = templateResolutionLabel(template),
                            style = AppTypography.bodyMedium,
                            color = ShellColors.textSecondary(darkTheme),
                            maxLines = 1,
                        )
                        if (needsRecalibration) {
                            Text(
                                text = "需要重新标定",
                                style = AppTypography.bodySmall,
                                color = ShellColors.criticalRed,
                                maxLines = 1,
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(ShellColors.badgeBg(darkTheme)),
                        contentAlignment = Alignment.Center,
                    ) {
                        AppIcon(
                            icon = AppIconId.Template,
                            contentDescription = null,
                            tint = ShellColors.textPrimary(darkTheme),
                            modifier = Modifier.size(22.dp),
                        )
                    }
                }

                Spacer(modifier = Modifier.height(18.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                        .clip(RoundedCornerShape(20.dp))
                        .background(ShellColors.badgeBg(darkTheme))
                        .padding(12.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    TemplatePreviewThumbnail(
                        previewPath = template.previewAsset,
                        contentDescription = template.name,
                        modifier = Modifier.fillMaxSize(),
                        cornerRadius = 16.dp,
                        imagePadding = 0.dp,
                        selected = true,
                    )
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.size(48.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    TemplatePreviewThumbnail(
                        previewPath = template.previewAsset,
                        contentDescription = template.name,
                        modifier = Modifier.fillMaxSize(),
                        cornerRadius = 10.dp,
                        imagePadding = 0.dp,
                        selected = isActive,
                    )
                }

                Spacer(modifier = Modifier.width(18.dp))

                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = template.name,
                        style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        color = ShellColors.textPrimary(darkTheme),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = templateResolutionLabel(template),
                        style = AppTypography.bodyMedium,
                        color = ShellColors.textSecondary(darkTheme),
                        maxLines = 1,
                    )
                    if (needsRecalibration) {
                        Text(
                            text = "需要重新标定",
                            style = AppTypography.bodySmall,
                            color = ShellColors.criticalRed,
                            maxLines = 1,
                        )
                    }
                }
            }
        }
    }
}

private fun ShellTemplate.needsRecalibration(uiState: MainUiState): Boolean {
    val saved = calibration?.captureProfile ?: return !isBuiltIn
    val current = uiState.currentDeviceCaptureProfile ?: return false
    return saved.hasMeaningfulChangeFrom(current)
}

@Composable
private fun DetailRow(label: String, value: String, darkTheme: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(16.dp))
            .background(ShellColors.badgeBg(darkTheme))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(label, style = AppTypography.bodyMedium, color = ShellColors.textSecondary(darkTheme))
        Text(value, style = AppTypography.bodyLarge, color = ShellColors.textPrimary(darkTheme))
    }
}

@Composable
private fun EmptyTemplateCard(darkTheme: Boolean, text: String = "No templates available.") {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .bentoCard(darkTheme, cornerRadius = 24.dp)
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(AppIconId.Template, contentDescription = null, tint = ShellColors.textTertiary(darkTheme), modifier = Modifier.size(22.dp))
            Text(text, style = AppTypography.bodyMedium, color = ShellColors.textTertiary(darkTheme), modifier = Modifier.padding(start = 12.dp))
        }
    }
}

@Composable
private fun TemplateImportDialog(
    draft: TemplateImportDraft,
    inProgress: Boolean,
    isDark: Boolean,
    onUpdateName: (String) -> Unit,
    onUpdateCalibration: (centerX: Float, centerY: Float, width: Float, cornerRadius: Float) -> Unit,
    onResetCalibration: () -> Unit,
    onToggleGuides: (Boolean) -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = { if (!inProgress) onDismiss() },
        properties = DialogProperties(usePlatformDefaultWidth = false),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = if (isDark) 0.58f else 0.38f))
                .padding(horizontal = 18.dp, vertical = 20.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.94f)
                    .widthIn(max = 460.dp)
                    .bentoCard(isDark, cornerRadius = 32.dp, isElevated = true)
                    .background(if (isDark) Color(0xFF1C1C1E) else Color(0xFFF8F9FB))
                    .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                ) {
                    Text("标定截图显示区域", style = AppTypography.titleLarge, color = ShellColors.textPrimary(isDark))
                    Text(
                        "拖动半透明区域，让它覆盖未来真实截图应该显示的位置。比例已锁定为当前设备捕获基准。",
                        style = AppTypography.bodyMedium,
                        color = ShellColors.textSecondary(isDark),
                    )
                    CalibrationPreview(
                        draft = draft,
                        isDark = isDark,
                        enabled = !inProgress,
                        onUpdateCalibration = onUpdateCalibration,
                    )
                    CalibrationControls(
                        draft = draft,
                        isDark = isDark,
                        enabled = !inProgress,
                        onUpdateCalibration = onUpdateCalibration,
                        onResetCalibration = onResetCalibration,
                        onToggleGuides = onToggleGuides,
                    )
                    OutlinedTextField(
                        value = draft.templateName,
                        onValueChange = onUpdateName,
                        label = { Text(stringResource(R.string.templates_import_name_label)) },
                        singleLine = true,
                        modifier = Modifier.fillMaxWidth(),
                        enabled = !inProgress,
                        shape = RoundedCornerShape(18.dp),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = ShellColors.actionBg(isDark),
                            unfocusedBorderColor = ShellColors.border(isDark),
                        ),
                    )
                    Text(
                        "基准 ${draft.captureProfile.captureWidth} × ${draft.captureProfile.captureHeight} · DPI ${draft.captureProfile.densityDpi}",
                        style = AppTypography.bodySmall,
                        color = ShellColors.textTertiary(isDark),
                    )
                }

                Spacer(modifier = Modifier.height(14.dp))

                DialogPrimaryButton(
                    text = if (inProgress) stringResource(R.string.templates_import_generating) else "保存标定并导入",
                    isDark = isDark,
                    enabled = draft.templateName.isNotBlank() && !inProgress,
                    onClick = onConfirm,
                )
                Spacer(modifier = Modifier.height(10.dp))
                DialogSecondaryButton(text = stringResource(R.string.templates_cancel), isDark = isDark, enabled = !inProgress, onClick = onDismiss)
            }
        }
    }
}

@Composable
private fun CalibrationPreview(
    draft: TemplateImportDraft,
    isDark: Boolean,
    enabled: Boolean,
    onUpdateCalibration: (centerX: Float, centerY: Float, width: Float, cornerRadius: Float) -> Unit,
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxWidth()
            .height(320.dp)
            .clip(RoundedCornerShape(24.dp))
            .background(ShellColors.badgeBg(isDark)),
        contentAlignment = Alignment.Center,
    ) {
        val density = LocalDensity.current
        val canvasWidth = with(density) { maxWidth.toPx() }
        val canvasHeight = with(density) { maxHeight.toPx() }
        val scale = minOf(
            canvasWidth / draft.outputWidth.toFloat().coerceAtLeast(1f),
            canvasHeight / draft.outputHeight.toFloat().coerceAtLeast(1f),
        )
        val imageWidthPx = draft.outputWidth * scale
        val imageHeightPx = draft.outputHeight * scale
        val imageWidthDp = with(density) { imageWidthPx.toDp() }
        val imageHeightDp = with(density) { imageHeightPx.toDp() }
        val overlayLeftPx = (draft.overlayCenterX - draft.overlayWidth / 2f) * scale
        val overlayTopPx = (draft.overlayCenterY - draft.overlayHeight / 2f) * scale
        val overlayWidthPx = draft.overlayWidth * scale
        val overlayHeightPx = draft.overlayHeight * scale

        Box(
            modifier = Modifier
                .size(width = imageWidthDp, height = imageHeightDp),
        ) {
            TemplatePreviewThumbnail(
                previewPath = draft.sourceImagePath,
                contentDescription = draft.templateName,
                modifier = Modifier.fillMaxSize(),
                cornerRadius = 18.dp,
                imagePadding = 0.dp,
            )
            Canvas(modifier = Modifier.fillMaxSize()) {
                if (draft.showGuides) {
                    val guidePaintColor = Color.White.copy(alpha = 0.32f)
                    drawLine(guidePaintColor, start = androidx.compose.ui.geometry.Offset(size.width / 2f, 0f), end = androidx.compose.ui.geometry.Offset(size.width / 2f, size.height), strokeWidth = 1f)
                    drawLine(guidePaintColor, start = androidx.compose.ui.geometry.Offset(0f, size.height / 2f), end = androidx.compose.ui.geometry.Offset(size.width, size.height / 2f), strokeWidth = 1f)
                }
                val rect = androidx.compose.ui.geometry.Rect(
                    left = overlayLeftPx,
                    top = overlayTopPx,
                    right = overlayLeftPx + overlayWidthPx,
                    bottom = overlayTopPx + overlayHeightPx,
                )
                drawRoundRect(
                    color = Color(0xFF34C759).copy(alpha = 0.24f),
                    topLeft = rect.topLeft,
                    size = rect.size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(draft.overlayCornerRadius * scale, draft.overlayCornerRadius * scale),
                )
                drawRoundRect(
                    color = Color(0xFF34C759),
                    topLeft = rect.topLeft,
                    size = rect.size,
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(draft.overlayCornerRadius * scale, draft.overlayCornerRadius * scale),
                    style = Stroke(width = 3f),
                )
                val handleColor = Color.White
                listOf(rect.topLeft, rect.topRight, rect.bottomLeft, rect.bottomRight).forEach { point ->
                    drawCircle(handleColor, radius = 7f, center = point)
                    drawCircle(Color(0xFF34C759), radius = 7f, center = point, style = Stroke(width = 2f))
                }
                drawCircle(
                    color = Color(0xFFFFCC00),
                    radius = 5f,
                    center = androidx.compose.ui.geometry.Offset(rect.center.x, rect.center.y),
                )
            }
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .pointerInput(enabled, draft.overlayCenterX, draft.overlayCenterY, draft.overlayWidth, draft.overlayCornerRadius) {
                        if (!enabled) return@pointerInput
                        detectDragGestures { change, dragAmount ->
                            change.consume()
                            onUpdateCalibration(
                                draft.overlayCenterX + dragAmount.x / scale,
                                draft.overlayCenterY + dragAmount.y / scale,
                                draft.overlayWidth,
                                draft.overlayCornerRadius,
                            )
                        }
                    },
            )
        }
    }
}

@Composable
private fun CalibrationControls(
    draft: TemplateImportDraft,
    isDark: Boolean,
    enabled: Boolean,
    onUpdateCalibration: (centerX: Float, centerY: Float, width: Float, cornerRadius: Float) -> Unit,
    onResetCalibration: () -> Unit,
    onToggleGuides: (Boolean) -> Unit,
) {
    Column(modifier = Modifier.fillMaxWidth(), verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("缩放", style = AppTypography.bodyMedium, color = ShellColors.textSecondary(isDark))
        Slider(
            value = draft.overlayWidth,
            onValueChange = { width ->
                onUpdateCalibration(draft.overlayCenterX, draft.overlayCenterY, width, draft.overlayCornerRadius)
            },
            valueRange = 24f..draft.outputWidth.toFloat(),
            enabled = enabled,
        )
        Text("圆角", style = AppTypography.bodyMedium, color = ShellColors.textSecondary(isDark))
        Slider(
            value = draft.overlayCornerRadius,
            onValueChange = { radius ->
                onUpdateCalibration(draft.overlayCenterX, draft.overlayCenterY, draft.overlayWidth, radius)
            },
            valueRange = 0f..(minOf(draft.overlayWidth, draft.overlayHeight) / 2f).coerceAtLeast(1f),
            enabled = enabled,
        )
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedButton(
                onClick = onResetCalibration,
                enabled = enabled,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text("重置")
            }
            OutlinedButton(
                onClick = { onToggleGuides(!draft.showGuides) },
                enabled = enabled,
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
            ) {
                Text(if (draft.showGuides) "隐藏辅助线" else "显示辅助线")
            }
        }
    }
}

@Composable
private fun AlertDialogCard(
    title: String,
    message: String,
    isDark: Boolean,
    confirmText: String,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss, properties = DialogProperties(usePlatformDefaultWidth = false)) {
        DialogSurface(darkTheme = isDark, cornerRadius = 28.dp) {
            Text(title, style = AppTypography.titleLarge, color = ShellColors.textPrimary(isDark))
            Text(message, style = AppTypography.bodyMedium, color = ShellColors.textSecondary(isDark))
            DialogPrimaryButton(text = confirmText, isDark = isDark, onClick = onConfirm)
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
        val transition = updateTransition(targetState = true, label = "delete-confirm")
        val overlayAlpha by transition.animateFloat(
            transitionSpec = { tween(durationMillis = 280) },
            label = "delete-overlay-alpha",
        ) { shown -> if (shown) 0.4f else 0f }
        val dialogScale by transition.animateFloat(
            transitionSpec = { spring(stiffness = 400f, dampingRatio = 0.85f) },
            label = "delete-dialog-scale",
        ) { shown -> if (shown) 1f else 0.95f }
        val dialogOffsetY by transition.animateDp(
            transitionSpec = { spring(stiffness = 400f, dampingRatio = 0.85f) },
            label = "delete-dialog-y",
        ) { shown -> if (shown) 0.dp else 20.dp }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = overlayAlpha))
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismiss,
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Column(
                modifier = Modifier
                    .widthIn(max = 320.dp)
                    .graphicsLayer {
                        scaleX = dialogScale
                        scaleY = dialogScale
                        translationY = dialogOffsetY.toPx()
                    }
                    .bentoCard(darkTheme, cornerRadius = 32.dp, isElevated = true)
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(ShellColors.badgeBg(darkTheme)),
                    contentAlignment = Alignment.Center,
                ) {
                    AppIcon(
                        icon = AppIconId.Delete,
                        contentDescription = null,
                        tint = ShellColors.criticalRed,
                        modifier = Modifier.size(32.dp),
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                Text(
                    text = "删除模板",
                    style = AppTypography.titleLarge,
                    color = ShellColors.textPrimary(darkTheme),
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "您确定删除吗？此操作不可逆",
                    style = AppTypography.bodyMedium,
                    color = ShellColors.textSecondary(darkTheme),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                )

                Spacer(modifier = Modifier.height(32.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Button(
                        onClick = onDismiss,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = ShellColors.badgeBg(darkTheme),
                            contentColor = ShellColors.textPrimary(darkTheme),
                        ),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    ) {
                        Text("Cancel", style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold))
                    }
                    Button(
                        onClick = onConfirm,
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp),
                        shape = RoundedCornerShape(14.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = ShellColors.criticalRed),
                        elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp),
                    ) {
                        Text("Delete", style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold), color = Color.White)
                    }
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
            .bentoCard(darkTheme, cornerRadius = cornerRadius, isElevated = true)
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color(0xFFF8F9FB))
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp),
        content = content,
    )
}

@Composable
private fun DialogPrimaryButton(
    text: String,
    isDark: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = ShellColors.actionBg(isDark),
            contentColor = ShellColors.actionText(isDark)
        ),
    ) {
        Text(text, style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold))
    }
}

@Composable
private fun DialogSecondaryButton(
    text: String,
    isDark: Boolean,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    OutlinedButton(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        enabled = enabled,
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(1.dp, ShellColors.border(isDark)),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = ShellColors.textPrimary(isDark)),
    ) {
        Text(text, style = AppTypography.bodyLarge.copy(fontWeight = FontWeight.Bold))
    }
}

private fun templateResolutionLabel(template: ShellTemplate): String {
    val width = template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width
    val height = template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height
    return "$width × $height"
}
