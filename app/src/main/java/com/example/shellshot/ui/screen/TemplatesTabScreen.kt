package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.shellshot.template.CalibrationCornerId
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.CountCapsule
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.components.IconPlate
import com.example.shellshot.ui.components.StaggeredReveal
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.components.noRippleClick
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import dev.chrisbanes.haze.HazeState
import kotlinx.coroutines.launch
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.roundToInt

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TemplatesTabScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    isDark: Boolean,
    hazeState: HazeState,
    animatedVisibilityScope: androidx.compose.animation.AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onUploadTemplateImage: () -> Unit,
    onSelectTemplate: (String) -> Unit,
    onRequestDeleteTemplate: (String) -> Unit,
    onDismissDeleteTemplate: () -> Unit,
    onConfirmDeleteTemplate: (String) -> Unit,
    onOpenOverview: () -> Unit,
    onCloseOverview: () -> Unit,
    onOpenOverviewDetail: (String) -> Unit,
    onCloseOverviewDetail: () -> Unit,
    onSetCarouselAnchor: (String?) -> Unit,
    onUpdateImportName: (String) -> Unit,
    onStartCornerDrag: (CalibrationCornerId, Float, Float) -> Unit,
    onUpdateCorner: (CalibrationCornerId, Float, Float) -> Unit,
    onFinishCornerDrag: () -> Unit,
    onUpdateCornerRadius: (Float) -> Unit,
    onResetCalibration: () -> Unit,
    onConfirmImport: () -> Unit,
    onCancelImport: () -> Unit,
    onDismissImportAlert: () -> Unit,
    onAcknowledgeConfetti: (Long) -> Unit,
) {
    val templates = uiState.templates
    val selectedTemplateId = uiState.selectedTemplate?.id
    val animatable = remember { Animatable(0f) }
    val colors = MaterialTheme.shellShotTokens.colors
    val carouselScope = rememberCoroutineScope()

    fun dataIndexFor(virtualIndex: Int): Int {
        val size = templates.size
        if (size == 0) return 0
        return ((virtualIndex % size) + size) % size
    }

    fun nearestVirtualIndex(targetIndex: Int, current: Float, size: Int): Float {
        val currentWrap = floor(current / size).toInt()
        return (currentWrap - 2..currentWrap + 2)
            .map { wrap -> targetIndex + wrap * size }
            .minByOrNull { abs(it - current) }
            ?.toFloat()
            ?: targetIndex.toFloat()
    }

    LaunchedEffect(templates.map { it.id }, selectedTemplateId) {
        val targetIndex = templates.indexOfFirst { it.id == selectedTemplateId }.takeIf { it >= 0 } ?: 0
        if (templates.isNotEmpty() && animatable.value == 0f) {
            animatable.snapTo(targetIndex.toFloat())
        }
    }

    LaunchedEffect(uiState.templateCarouselAnchorId, templates.map { it.id }) {
        val anchorId = uiState.templateCarouselAnchorId ?: return@LaunchedEffect
        val targetIndex = templates.indexOfFirst { it.id == anchorId }
        if (targetIndex >= 0) {
            animatable.animateTo(
                targetValue = nearestVirtualIndex(targetIndex, animatable.value, templates.size),
                animationSpec = spring(stiffness = 300f, dampingRatio = 0.8f),
            )
        }
        onSetCarouselAnchor(null)
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(top = 14.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            StaggeredReveal(index = 0) {
                TemplatesHeader(
                    count = templates.size,
                    isDark = isDark,
                    onAdd = onUploadTemplateImage,
                    onOpenOverview = onOpenOverview,
                )
            }
            if (templates.isEmpty()) {
                EmptyTemplatesStage(
                    isDark = isDark,
                    modifier = Modifier.weight(1f),
                    onAdd = onUploadTemplateImage,
                )
            } else {
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .pointerInput(templates.map { it.id }) {
                            detectHorizontalDragGestures(
                                onHorizontalDrag = { change, dragAmount ->
                                    change.consume()
                                    if (templates.isNotEmpty() && abs(animatable.value - animatable.value.roundToInt()) < 0.55f) {
                                        carouselScope.launch {
                                            animatable.snapTo(animatable.value - dragAmount / 180f)
                                        }
                                    }
                                },
                                onDragEnd = {
                                    carouselScope.launch {
                                        animatable.animateTo(
                                            targetValue = animatable.value.roundToInt().toFloat(),
                                            animationSpec = spring(stiffness = 300f, dampingRatio = 0.8f),
                                        )
                                    }
                                },
                            )
                        },
                    contentAlignment = Alignment.Center,
                ) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.Center)
                            .width(240.dp)
                            .height(440.dp)
                            .blur(80.dp)
                            .background(
                                ShellColors.AccentBlue.copy(alpha = if (isDark) 0.08f else 0.12f),
                                RoundedCornerShape(56.dp),
                            ),
                    )
                    val baseIndex = animatable.value.roundToInt()
                    (-2..2).forEach { offset ->
                        val virtualIndex = baseIndex + offset
                        val template = templates[dataIndexFor(virtualIndex)]
                        val relativeOffset = virtualIndex - animatable.value
                        TemplateCarouselCard(
                            template = template,
                            selected = template.id == selectedTemplateId,
                            relativeOffset = relativeOffset,
                            isDark = isDark,
                            modifier = Modifier.align(Alignment.Center),
                            onClick = {
                                if (abs(relativeOffset) < 0.1f) return@TemplateCarouselCard
                                if (abs(relativeOffset) < 1.1f) {
                                    onSetCarouselAnchor(template.id)
                                }
                            },
                            onApply = { onSelectTemplate(template.id) },
                            onDelete = { onRequestDeleteTemplate(template.id) },
                        )
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = uiState.templateOverviewVisible,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
        ) {
            TemplateOverviewSheet(
                templates = templates,
                selectedTemplateId = selectedTemplateId,
                isDark = isDark,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                onClose = onCloseOverview,
                onOpenDetail = onOpenOverviewDetail,
            )
        }

        uiState.overviewDetailTemplate?.let { template ->
            OverviewDetailDialog(
                template = template,
                isDark = isDark,
                animatedVisibilityScope = animatedVisibilityScope,
                sharedTransitionScope = sharedTransitionScope,
                onApply = {
                    onSelectTemplate(template.id)
                    onCloseOverviewDetail()
                    onCloseOverview()
                },
                onDelete = { onRequestDeleteTemplate(template.id) },
                onDismiss = onCloseOverviewDetail,
            )
        }

        uiState.pendingDeleteTemplate?.let { template ->
            DeleteTemplateDialog(
                templateName = template.name,
                isDark = isDark,
                onConfirm = { onConfirmDeleteTemplate(template.id) },
                onDismiss = onDismissDeleteTemplate,
            )
        }

        if (uiState.templateImportPreparing) {
            TemplateLoadingOverlay(isDark = isDark)
        }

        uiState.templateImportAlert?.let { alert ->
            SimpleMessageDialog(
                title = alert.title,
                message = alert.message,
                isDark = isDark,
                onDismiss = onDismissImportAlert,
            )
        }

        AnimatedVisibility(
            visible = uiState.templateImportDraft != null,
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
        ) {
            uiState.templateImportDraft?.let { draft ->
                TemplateCalibrationPage(
                    draft = draft,
                    inProgress = uiState.templateImportInProgress,
                    isDark = isDark,
                    onUpdateName = onUpdateImportName,
                    onStartCornerDrag = onStartCornerDrag,
                    onUpdateCorner = onUpdateCorner,
                    onFinishCornerDrag = onFinishCornerDrag,
                    onUpdateCornerRadius = onUpdateCornerRadius,
                    onReset = onResetCalibration,
                    onConfirm = onConfirmImport,
                    onDismiss = onCancelImport,
                )
            }
        }

        if (uiState.templateConfettiToken != 0L) {
            TemplateConfetti(
                token = uiState.templateConfettiToken,
                onConsumed = onAcknowledgeConfetti,
            )
        }
    }
}

@Composable
private fun TemplatesHeader(
    count: Int,
    isDark: Boolean,
    onAdd: () -> Unit,
    onOpenOverview: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text("模板", fontSize = 30.sp, fontWeight = FontWeight.Black, color = colors.textPrimary)
        Row(
            modifier = Modifier
                .background(colors.glass, CircleShape)
                .border(1.dp, colors.glassStroke, CircleShape)
                .padding(4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CountCapsule(text = "$count", isDark = isDark)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(ShellColors.AccentBlue, CircleShape)
                    .noRippleClick(onAdd),
                contentAlignment = Alignment.Center,
            ) {
                AppIcon(AppIconId.Add, "新增模板", tint = Color.White)
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(colors.subtleFill, CircleShape)
                    .noRippleClick(onOpenOverview),
                contentAlignment = Alignment.Center,
            ) {
                AppIcon(AppIconId.Stats, "模板总览", tint = colors.textMuted)
            }
        }
    }
}

@Composable
private fun TemplateCarouselCard(
    template: ShellTemplate,
    selected: Boolean,
    relativeOffset: Float,
    isDark: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onApply: () -> Unit,
    onDelete: () -> Unit,
) {
    val absOffset = abs(relativeOffset)
    val isCenter = absOffset < 0.1f
    val alpha = if (absOffset >= 3f) 0f else 1f - absOffset * 0.4f
    val scale = 1f - absOffset * 0.12f
    Box(
        modifier = modifier
            .width(260.dp)
            .height(480.dp)
            .graphicsLayer {
                translationX = (relativeOffset * 140.dp.toPx())
                translationY = (absOffset * 35.dp.toPx())
                rotationZ = relativeOffset * 12f
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
                transformOrigin = TransformOrigin(0.5f, 1f)
            }
            .background(MaterialTheme.shellShotTokens.colors.surface, RoundedCornerShape(40.dp))
            .border(
                width = 6.dp,
                color = if (isDark) Color.White.copy(alpha = 0.05f) else Color(0xFFF5F5F5),
                shape = RoundedCornerShape(40.dp),
            )
            .noRippleClick(onClick),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(48.dp))
            Box(
                modifier = Modifier
                    .width(124.dp)
                    .height(220.dp)
                    .background(MaterialTheme.shellShotTokens.colors.subtleFill, RoundedCornerShape(32.dp))
                    .border(1.dp, MaterialTheme.shellShotTokens.colors.separator, RoundedCornerShape(32.dp)),
                contentAlignment = Alignment.Center,
            ) {
                TemplatePreviewThumbnail(
                    previewPath = template.previewAsset,
                    contentDescription = template.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                    cornerRadius = 24.dp,
                    selected = selected,
                )
            }
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                template.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.shellShotTokens.colors.textPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Text(
                text = "${template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width} × ${template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = MaterialTheme.shellShotTokens.colors.textSecondary,
            )
            Spacer(modifier = Modifier.weight(1f))
            if (isCenter) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (selected) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.shellShotTokens.colors.subtleFill, CircleShape)
                                .padding(horizontal = 24.dp, vertical = 10.dp),
                        ) {
                            Text("已应用", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.shellShotTokens.colors.textSecondary)
                        }
                    } else {
                        Box(
                            modifier = Modifier
                                .background(ShellColors.AccentBlue, CircleShape)
                                .padding(horizontal = 24.dp, vertical = 10.dp)
                                .noRippleClick(onApply),
                        ) {
                            Text("应用模板", fontSize = 12.sp, fontWeight = FontWeight.Black, color = Color.White)
                        }
                    }
                    if (template.canDelete) {
                        Box(
                            modifier = Modifier
                                .padding(start = 8.dp)
                                .size(40.dp)
                                .noRippleClick(onDelete),
                            contentAlignment = Alignment.Center,
                        ) {
                            AppIcon(icon = AppIconId.Delete, contentDescription = "删除模板", tint = ShellColors.AccentRed)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun EmptyTemplatesStage(
    isDark: Boolean,
    modifier: Modifier = Modifier,
    onAdd: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {
            IconPlate(
                icon = AppIconId.Template,
                tint = ShellColors.AccentBlue,
                background = ShellColors.AccentBlue.copy(alpha = if (isDark) 0.18f else 0.12f),
                modifier = Modifier.size(110.dp),
            )
            Text("还没有模板", fontSize = 24.sp, fontWeight = FontWeight.Black, color = colors.textPrimary)
            Text("导入第一张手机壳图片，弧形轮播就会在这里展开。", fontSize = 14.sp, color = colors.textSecondary)
            Box(
                modifier = Modifier
                    .background(ShellColors.AccentBlue, CircleShape)
                    .padding(horizontal = 24.dp, vertical = 12.dp)
                    .noRippleClick(onAdd),
            ) {
                Text("导入模板", color = Color.White, fontWeight = FontWeight.Black)
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun TemplateOverviewSheet(
    templates: List<ShellTemplate>,
    selectedTemplateId: String?,
    isDark: Boolean,
    animatedVisibilityScope: androidx.compose.animation.AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onClose: () -> Unit,
    onOpenDetail: (String) -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val activeTemplate = templates.firstOrNull { it.id == selectedTemplateId } ?: templates.firstOrNull()
    val otherTemplates = templates.filterNot { it.id == activeTemplate?.id }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDark) Color.Black.copy(alpha = 0.96f) else Color(0xFFF2F2F7).copy(alpha = 0.96f)),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = 140.dp),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (isDark) Color.Black.copy(alpha = 0.9f) else Color(0xFFF2F2F7).copy(alpha = 0.9f))
                    .padding(horizontal = 24.dp, vertical = 18.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text("模板总览", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = colors.textPrimary)
                Box(
                    modifier = Modifier
                        .background(ShellColors.AccentBlue.copy(alpha = 0.12f), CircleShape)
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .noRippleClick(onClose),
                ) {
                    Text("完成", color = ShellColors.AccentBlue, fontWeight = FontWeight.Bold)
                }
            }
            Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 20.dp), verticalArrangement = Arrangement.spacedBy(28.dp)) {
                Text("当前使用", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = colors.textSecondary, modifier = Modifier.padding(start = 8.dp))
                activeTemplate?.let { template ->
                    OverviewCard(
                        template = template,
                        isDark = isDark,
                        active = true,
                        animatedVisibilityScope = animatedVisibilityScope,
                        sharedTransitionScope = sharedTransitionScope,
                        onClick = { onOpenDetail(template.id) },
                    )
                }
                Text("备选模板", fontSize = 13.sp, fontWeight = FontWeight.Bold, color = colors.textSecondary, modifier = Modifier.padding(start = 8.dp))
                Column {
                    otherTemplates.forEachIndexed { index, template ->
                        OverviewCard(
                            template = template,
                            isDark = isDark,
                            active = false,
                            overlap = index > 0,
                            animatedVisibilityScope = animatedVisibilityScope,
                            sharedTransitionScope = sharedTransitionScope,
                            onClick = { onOpenDetail(template.id) },
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun OverviewCard(
    template: ShellTemplate,
    isDark: Boolean,
    active: Boolean,
    overlap: Boolean = false,
    animatedVisibilityScope: androidx.compose.animation.AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val sharedModifier = with(sharedTransitionScope) {
        Modifier.sharedBounds(
            sharedContentState = rememberSharedContentState(key = "overview-card-${template.id}"),
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
    Row(
        modifier = sharedModifier
            .fillMaxWidth()
            .offset(y = if (overlap) (-24).dp else 0.dp)
            .background(colors.surface, RoundedCornerShape(if (active) 36.dp else 30.dp))
            .border(
                1.dp,
                if (active) ShellColors.AccentBlue.copy(alpha = 0.2f) else colors.separator,
                RoundedCornerShape(if (active) 36.dp else 30.dp),
            )
            .padding(horizontal = 20.dp, vertical = if (active) 24.dp else 18.dp)
            .noRippleClick(onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(if (active) 56.dp else 52.dp)
                .background(
                    if (active) ShellColors.AccentBlue.copy(alpha = 0.12f) else colors.subtleFill,
                    RoundedCornerShape(16.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            TemplatePreviewThumbnail(
                previewPath = template.previewAsset,
                contentDescription = template.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                cornerRadius = 12.dp,
            )
        }
        Text(
            text = template.name,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1f),
            fontSize = if (active) 18.sp else 17.sp,
            fontWeight = FontWeight.Bold,
            color = colors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = "${template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width} × ${template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height}",
            fontSize = if (active) 14.sp else 13.sp,
            fontWeight = FontWeight.Bold,
            color = if (active) ShellColors.AccentBlue else colors.textSecondary,
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun OverviewDetailDialog(
    template: ShellTemplate,
    isDark: Boolean,
    animatedVisibilityScope: androidx.compose.animation.AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onApply: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.6f))
                .noRippleClick(onDismiss),
        )
        GlassSurfaceCard(
            modifier = Modifier.width(360.dp),
            isDark = isDark,
            cornerRadius = 48,
            padding = 28,
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                if (template.canDelete) {
                    Box(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .size(40.dp)
                            .background(ShellColors.AccentRed.copy(alpha = 0.12f), CircleShape)
                            .noRippleClick(onDelete),
                        contentAlignment = Alignment.Center,
                    ) {
                        AppIcon(AppIconId.Delete, "删除模板", tint = ShellColors.AccentRed)
                    }
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .size(40.dp)
                        .background(colors.subtleFill, CircleShape)
                        .noRippleClick(onDismiss),
                    contentAlignment = Alignment.Center,
                ) {
                    AppIcon(AppIconId.Close, "关闭", tint = colors.textSecondary)
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(14.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(88.dp)
                        .background(colors.subtleFill, RoundedCornerShape(28.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    TemplatePreviewThumbnail(
                        previewPath = template.previewAsset,
                        contentDescription = template.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        cornerRadius = 20.dp,
                    )
                }
                Text(template.name, fontSize = 24.sp, fontWeight = FontWeight.Black, color = colors.textPrimary)
                Text(
                    "${template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width} × ${template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height}",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShellColors.AccentBlue,
                )
                InfoRow("分辨率", "${template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width} × ${template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height}", isDark)
                InfoRow("模板类型", if (template.canDelete) "用户自定义" else "内置模板", isDark)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(ShellColors.AccentBlue, RoundedCornerShape(20.dp))
                        .padding(vertical = 16.dp)
                        .noRippleClick(onApply),
                    contentAlignment = Alignment.Center,
                ) {
                    Text("应用", color = Color.White, fontSize = 18.sp, fontWeight = FontWeight.Black)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String, isDark: Boolean) {
    val colors = MaterialTheme.shellShotTokens.colors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(colors.subtleFill, RoundedCornerShape(18.dp))
            .padding(horizontal = 16.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, color = colors.textSecondary, fontWeight = FontWeight.Bold, fontSize = 14.sp)
        Text(value, color = colors.textPrimary, fontWeight = FontWeight.Black, fontSize = 14.sp)
    }
}

@Composable
private fun DeleteTemplateDialog(
    templateName: String,
    isDark: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        GlassSurfaceCard(
            modifier = Modifier.width(320.dp),
            isDark = isDark,
            cornerRadius = 32,
            padding = 24,
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(
                    modifier = Modifier
                        .size(64.dp)
                        .background(ShellColors.AccentRed.copy(alpha = 0.12f), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    AppIcon(AppIconId.Delete, null, tint = ShellColors.AccentRed, modifier = Modifier.size(30.dp))
                }
                Text("删除模板", modifier = Modifier.padding(top = 16.dp), fontSize = 22.sp, fontWeight = FontWeight.Bold, color = MaterialTheme.shellShotTokens.colors.textPrimary)
                Text("确认删除“$templateName”吗？此操作无法撤销。", modifier = Modifier.padding(top = 8.dp), fontSize = 15.sp, color = MaterialTheme.shellShotTokens.colors.textSecondary)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(MaterialTheme.shellShotTokens.colors.subtleFill, RoundedCornerShape(16.dp))
                            .padding(vertical = 14.dp)
                            .noRippleClick(onDismiss),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("取消", fontWeight = FontWeight.Bold, color = MaterialTheme.shellShotTokens.colors.textPrimary)
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .background(ShellColors.AccentRed, RoundedCornerShape(16.dp))
                            .padding(vertical = 14.dp)
                            .noRippleClick(onConfirm),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text("删除", fontWeight = FontWeight.Bold, color = Color.White)
                    }
                }
            }
        }
    }
}

@Composable
private fun TemplateLoadingOverlay(isDark: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ShellColors.loadingOverlay(isDark)),
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(64.dp)
                    .background(Color.Transparent, CircleShape)
                    .border(4.dp, Color.White.copy(alpha = 0.2f), CircleShape),
            )
            Text("正在加载图片...", modifier = Modifier.padding(top = 20.dp), color = MaterialTheme.shellShotTokens.colors.textPrimary, fontSize = 20.sp, fontWeight = FontWeight.Black)
            Text("系统正在智能解析屏幕比例", modifier = Modifier.padding(top = 8.dp), color = MaterialTheme.shellShotTokens.colors.textSecondary, fontSize = 14.sp)
        }
    }
}

@Composable
private fun SimpleMessageDialog(
    title: String,
    message: String,
    isDark: Boolean,
    onDismiss: () -> Unit,
) {
    Dialog(onDismissRequest = onDismiss) {
        GlassSurfaceCard(
            modifier = Modifier.width(320.dp),
            isDark = isDark,
            cornerRadius = 28,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                Text(title, fontSize = 20.sp, fontWeight = FontWeight.Black, color = MaterialTheme.shellShotTokens.colors.textPrimary)
                Text(message, fontSize = 15.sp, color = MaterialTheme.shellShotTokens.colors.textSecondary)
                Box(
                    modifier = Modifier
                        .align(Alignment.End)
                        .background(ShellColors.AccentBlue, RoundedCornerShape(14.dp))
                        .padding(horizontal = 18.dp, vertical = 10.dp)
                        .noRippleClick(onDismiss),
                ) {
                    Text("知道了", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
private fun TemplateConfetti(
    token: Long,
    onConsumed: (Long) -> Unit,
) {
    val leftParty = remember(token) {
        Party(
            emitter = Emitter(duration = 250, TimeUnit.MILLISECONDS).perSecond(120),
            angle = 60,
            spread = 55,
            speed = 18f,
            maxSpeed = 28f,
            position = Position.Relative(0.0, 1.0),
            colors = listOf(
                ShellColors.AccentBlue.toArgb(),
                ShellColors.AccentCyan.toArgb(),
                ShellColors.AccentGreen.toArgb(),
                ShellColors.AccentPurple.toArgb(),
                ShellColors.AccentPink.toArgb(),
            ),
        )
    }
    val rightParty = remember(token) {
        Party(
            emitter = Emitter(duration = 250, TimeUnit.MILLISECONDS).perSecond(120),
            angle = 120,
            spread = 55,
            speed = 18f,
            maxSpeed = 28f,
            position = Position.Relative(1.0, 1.0),
            colors = listOf(
                ShellColors.AccentBlue.toArgb(),
                ShellColors.AccentCyan.toArgb(),
                ShellColors.AccentGreen.toArgb(),
                ShellColors.AccentPurple.toArgb(),
                ShellColors.AccentPink.toArgb(),
            ),
        )
    }
    LaunchedEffect(token) {
        kotlinx.coroutines.delay(2800)
        onConsumed(token)
    }
    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = listOf(leftParty, rightParty),
    )
}
