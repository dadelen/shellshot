package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.shellshot.template.CalibrationCornerId
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.components.StaggeredReveal
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.components.noRippleClick
import com.example.shellshot.ui.components.rememberShellShotHaptics
import com.example.shellshot.ui.designsystem.ioslike.IOSColors
import com.example.shellshot.ui.designsystem.ioslike.IOSFlyInContainer
import com.example.shellshot.ui.designsystem.ioslike.IOSLargeTitleTopBar
import com.example.shellshot.ui.designsystem.ioslike.IOSPillIconButton
import com.example.shellshot.ui.designsystem.ioslike.iosSheetFlyInEnter
import com.example.shellshot.ui.designsystem.ioslike.iosSheetFlyOutExit
import com.example.shellshot.ui.theme.MotionConstants
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import dev.chrisbanes.haze.HazeState
import kotlin.math.abs
import kotlin.math.floor
import kotlin.math.roundToInt
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

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
    val density = LocalDensity.current
    val haptics = rememberShellShotHaptics()
    var dragAccumulator by remember { mutableStateOf(0f) }
    var dragStartIndex by remember { mutableStateOf(0) }
    var dragVelocityHint by remember { mutableStateOf(0f) }
    var exitingTemplates by remember { mutableStateOf(emptyMap<String, ShellTemplate>()) }
    var retainedOverviewDetailTemplate by remember { mutableStateOf<ShellTemplate?>(null) }
    var overviewDetailVisible by remember { mutableStateOf(false) }

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
                animationSpec = spring(stiffness = 340f, dampingRatio = 0.78f),
            )
        }
        onSetCarouselAnchor(null)
    }

    LaunchedEffect(uiState.overviewDetailTemplate?.id, templates.map { it.id }) {
        val incoming = uiState.overviewDetailTemplate
        if (incoming != null) {
            retainedOverviewDetailTemplate = incoming
            overviewDetailVisible = true
        } else if (retainedOverviewDetailTemplate != null) {
            overviewDetailVisible = false
            kotlinx.coroutines.delay(260)
            if (!overviewDetailVisible) {
                retainedOverviewDetailTemplate = null
            }
        }
    }

    LaunchedEffect(uiState.templateConfettiToken) {
        if (uiState.templateConfettiToken != 0L) {
            haptics.selection()
        }
    }

    LaunchedEffect(templates.map { it.id }) {
        val visibleTemplateIds = templates.map { it.id }.toSet()
        exitingTemplates = exitingTemplates.filterKeys { id -> id in visibleTemplateIds }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(IOSColors.scheme(isDark).groupedBackground),
    ) {
        IOSFlyInContainer(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(top = 8.dp),
            ) {
                StaggeredReveal(index = 0, modifier = Modifier.zIndex(20f)) {
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
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .zIndex(0f)
                            .offset(y = (-36).dp)
                            .pointerInput(templates.map { it.id }) {
                                detectHorizontalDragGestures(
                                    onDragStart = {
                                        dragAccumulator = 0f
                                        dragVelocityHint = 0f
                                        dragStartIndex = animatable.value.roundToInt()
                                        carouselScope.launch {
                                            animatable.stop()
                                        }
                                    },
                                    onHorizontalDrag = { change, dragAmount ->
                                        if (templates.isNotEmpty()) {
                                            change.consume()
                                            val dragAmountDp = with(density) { dragAmount.toDp().value }
                                            dragAccumulator += dragAmountDp
                                            dragVelocityHint = dragAmountDp
                                            val base = dragStartIndex.toFloat()
                                            val dragProgress = (-(dragAccumulator) / 132f).coerceIn(-0.48f, 0.48f)
                                            carouselScope.launch {
                                                animatable.snapTo(base + dragProgress)
                                            }
                                        }
                                    },
                                    onDragEnd = {
                                        carouselScope.launch {
                                            val projectedDrag = dragAccumulator + (dragVelocityHint * 8f)
                                            val threshold = 48f
                                            val offset = if (projectedDrag < -threshold) 1
                                                else if (projectedDrag > threshold) -1
                                                else 0
                                            val target = dragStartIndex + offset
                                            animatable.animateTo(
                                                targetValue = target.toFloat(),
                                                animationSpec = spring(
                                                    stiffness = 340f,
                                                    dampingRatio = 0.78f,
                                                ),
                                            )
                                            if (offset != 0) {
                                                delay(55)
                                                haptics.selection()
                                            }
                                        }
                                    },
                                )
                            },
                        contentAlignment = Alignment.Center,
                    ) {
                        Box(
                            modifier = Modifier
                                .align(Alignment.Center)
                                .width(408.dp)
                                .height(616.dp)
                                .background(
                                    brush = Brush.radialGradient(
                                        colors = listOf(
                                            ShellColors.AccentBlue.copy(alpha = if (isDark) 0.18f else 0.22f),
                                            Color.White.copy(alpha = if (isDark) 0.06f else 0.12f),
                                            Color.Transparent,
                                        )
                                    )
                                ),
                        )
                        val baseIndex = animatable.value.roundToInt()
                        val carouselOffsets = when (templates.size) {
                            1 -> listOf(0)
                            2 -> listOf(0, 1)
                            else -> (-2..2).toList()
                        }
                        carouselOffsets.forEach { offset ->
                            val virtualIndex = baseIndex + offset
                            val template = templates[dataIndexFor(virtualIndex)]
                            val relativeOffset = when (templates.size) {
                                1 -> 0f
                                else -> virtualIndex - animatable.value
                            }
                            val isExiting = exitingTemplates.containsKey(template.id)
                            androidx.compose.runtime.key("carousel-${template.id}-$virtualIndex") {
                                TemplateCarouselCard(
                                    template = template,
                                    selected = template.id == selectedTemplateId,
                                    relativeOffset = relativeOffset,
                                    isDark = isDark,
                                    visible = !isExiting,
                                    deleting = isExiting,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .zIndex(if (isExiting) 100f else 50f - abs(relativeOffset)),
                                    onClick = {
                                        val stepDistance = abs(relativeOffset.roundToInt())
                                        if (stepDistance == 1) {
                                            onSetCarouselAnchor(template.id)
                                            carouselScope.launch {
                                                delay(70)
                                                haptics.selection()
                                            }
                                        }
                                    },
                                    onApply = { onSelectTemplate(template.id) },
                                    onDelete = { onRequestDeleteTemplate(template.id) },
                                )
                            }
                        }
                    }
                }
            }
        }

        AnimatedVisibility(
            visible = uiState.templateOverviewVisible,
            enter = iosSheetFlyInEnter(),
            exit = iosSheetFlyOutExit(),
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

        retainedOverviewDetailTemplate?.let { template ->
            AnimatedVisibility(
                visible = overviewDetailVisible,
                enter = iosSheetFlyInEnter(),
                exit = iosSheetFlyOutExit(),
            ) {
                OverviewDetailDialog(
                    template = template,
                    visible = overviewDetailVisible,
                    isDark = isDark,
                    animatedVisibilityScope = this@AnimatedVisibility,
                    sharedTransitionScope = sharedTransitionScope,
	                    onApply = {
	                        onSetCarouselAnchor(template.id)
	                        onSelectTemplate(template.id)
	                        onCloseOverviewDetail()
	                        carouselScope.launch {
	                            delay(180)
	                            onCloseOverview()
	                        }
	                    },
                    onDelete = { onRequestDeleteTemplate(template.id) },
                    onDismiss = onCloseOverviewDetail,
                )
            }
        }

        uiState.pendingDeleteTemplate?.let { template ->
            DeleteTemplateDialog(
                templateName = template.name,
                isDark = isDark,
	                onConfirm = {
	                    val deleteIndex = templates.indexOfFirst { it.id == template.id }
		                    val nextIndex = when {
		                        templates.size <= 1 || deleteIndex < 0 -> null
		                        deleteIndex < templates.lastIndex -> deleteIndex + 1
		                        else -> deleteIndex - 1
		                    }
			                    val nextTemplateId = nextIndex?.let { templates.getOrNull(it)?.id }
			                    exitingTemplates = exitingTemplates + (template.id to template)
			                    onDismissDeleteTemplate()
			                    if (nextTemplateId != null) {
			                        onSetCarouselAnchor(nextTemplateId)
			                    }
			                    carouselScope.launch {
			                        delay(320L)
			                        onConfirmDeleteTemplate(template.id)
			                    }
			                },
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
            enter = iosSheetFlyInEnter(),
            exit = iosSheetFlyOutExit(),
        ) {
            Box(modifier = Modifier.fillMaxSize()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .pointerInput(Unit) {
                            awaitPointerEventScope {
                                while (true) {
                                    val event = awaitPointerEvent()
                                    event.changes.forEach { it.consume() }
                                }
                            }
                        }
                )
                uiState.templateImportDraft?.let { draft ->
                    TemplateCalibrationPage(
                        draft = draft,
                        inProgress = uiState.templateImportInProgress,
                        isDark = isDark,
                        onUpdateName = onUpdateImportName,
                        onUpdateCornerRadius = onUpdateCornerRadius,
                        onReset = onResetCalibration,
                        onConfirm = onConfirmImport,
                        onDismiss = onCancelImport,
                        sampleScreenshot = uiState.calibrationSampleScreenshot,
                    )
                }
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
    val colors = IOSColors.scheme(isDark)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top,
    ) {
        IOSLargeTitleTopBar(
            title = "模板",
            isDark = isDark,
            modifier = Modifier.weight(1f),
        )
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            IOSPillIconButton(
                isDark = isDark,
                onClick = onOpenOverview,
            ) {
                AppIcon(
                    AppIconId.Stats,
                    "模板总览",
                    tint = colors.secondaryText,
                    modifier = Modifier.size(19.dp),
                )
            }
            IOSPillIconButton(
                isDark = isDark,
                onClick = onAdd,
            ) {
                AppIcon(
                    AppIconId.Add,
                    "新增模板",
                    tint = IOSColors.Blue,
                    modifier = Modifier.size(20.dp),
                )
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
    visible: Boolean = true,
    deleting: Boolean = false,
    lightweight: Boolean = false,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onApply: () -> Unit,
    onDelete: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val absOffset = abs(relativeOffset)
    val isCenter = absOffset < 0.35f
    val stackOffset = relativeOffset.coerceIn(-2.2f, 2.2f)
    val deleteProgress by animateFloatAsState(
        targetValue = if (deleting) 1f else 0f,
        animationSpec = tween(280, easing = MotionConstants.iosEaseInOut),
        label = "template-delete-${template.id}",
    )
    val showActionRow = !lightweight && !deleting && absOffset < 0.6f
    val alpha = when {
        absOffset >= 2.6f -> 0f
        else -> (1f - absOffset * 0.24f).coerceIn(0f, 1f)
    }
    val scale = 1f - absOffset * 0.065f
    val deleteScale = 1f - 0.075f * deleteProgress
    val cornerRadius = if (isCenter) 40.dp else 36.dp
    Box(
        modifier = modifier
            .width(286.dp)
            .height(518.dp)
            .graphicsLayer {
                translationX = (stackOffset * 112.dp.toPx())
                translationY = ((absOffset * 24.dp.toPx()) + (stackOffset * stackOffset * 5.dp.toPx())) +
                    (18.dp.toPx() * deleteProgress)
                rotationZ = (stackOffset * 4.8f) * (1f - deleteProgress)
                rotationY = (stackOffset * -6f).coerceIn(-10f, 10f)
                cameraDistance = 72.dp.toPx()
                scaleX = scale * deleteScale
                scaleY = scale * deleteScale
                this.alpha = alpha * (1f - deleteProgress)
                transformOrigin = TransformOrigin(0.5f, if (deleting) 0.5f else 1f)
                shadowElevation = (if (lightweight) 0f else if (isCenter) 26.dp.toPx() else 8.dp.toPx()) *
                    (1f - deleteProgress)
                shape = RoundedCornerShape(cornerRadius)
                clip = false
            }
            .noRippleClick(enabled = visible && !deleting && !isCenter, onClick = onClick),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(cornerRadius))
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color(0xFF222225).copy(alpha = if (isCenter) 0.98f else 0.92f),
                                colors.surfaceAlt.copy(alpha = if (isCenter) 0.96f else 0.88f),
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = if (isCenter) 1f else 0.97f),
                                Color(0xFFF4F5F8).copy(alpha = if (isCenter) 0.98f else 0.92f),
                            )
                        },
                    ),
                    shape = RoundedCornerShape(cornerRadius),
                )
                .border(
                    width = if (isCenter) 1.2.dp else 0.9.dp,
                    color = if (isDark) {
                        Color.White.copy(alpha = if (isCenter) 0.12f else 0.06f)
                    } else {
                        Color.White.copy(alpha = if (isCenter) 0.92f else 0.66f)
                    },
                    shape = RoundedCornerShape(cornerRadius),
                )
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 22.dp, vertical = 24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(if (isCenter) 12.dp else 18.dp))
            Box(
                modifier = Modifier
                    .width(if (isCenter) 212.dp else 196.dp)
                    .height(if (isCenter) 392.dp else 362.dp),
                contentAlignment = Alignment.Center,
            ) {
                TemplatePreviewThumbnail(
                    previewPath = template.displayPreviewAsset,
                    contentDescription = template.name,
                    modifier = Modifier.fillMaxSize(),
                    cornerRadius = 28.dp,
                    selected = selected,
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.animation.AnimatedVisibility(
                visible = showActionRow,
                enter = fadeIn(tween(MotionConstants.SettleMs, easing = MotionConstants.iosEaseOut)) +
                    slideInVertically(
                        animationSpec = tween(MotionConstants.SettleMs, easing = MotionConstants.iosEaseOut),
                        initialOffsetY = { 10 },
                    ),
                exit = fadeOut(tween(MotionConstants.QuickMs, easing = MotionConstants.iosEaseInOut)) +
                    slideOutVertically(
                        animationSpec = tween(MotionConstants.QuickMs, easing = MotionConstants.iosEaseInOut),
                        targetOffsetY = { 10 },
                    ),
            ) {
                Row(
                    modifier = Modifier
                        .zIndex(5f),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .width(124.dp)
                            .height(42.dp)
                            .background(
                                color = if (selected) {
                                    ShellColors.AccentBlue.copy(alpha = if (isDark) 0.18f else 0.12f)
                                } else {
                                    ShellColors.AccentBlue
                                },
                                shape = CircleShape,
                            )
                            .border(
                                width = 1.dp,
                                color = if (selected) {
                                    ShellColors.AccentBlue.copy(alpha = if (isDark) 0.34f else 0.24f)
                                } else {
                                    Color.White.copy(alpha = if (isDark) 0.12f else 0.34f)
                                },
                                shape = CircleShape,
                            )
                            .noRippleClick(enabled = !selected, onClick = onApply),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = if (selected) "已应用" else "应用",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Black,
                            color = if (selected) ShellColors.AccentBlue else Color.White,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .size(42.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = if (template.canDelete) {
                                        listOf(
                                            ShellColors.AccentRed.copy(alpha = 0.18f),
                                            ShellColors.AccentRed.copy(alpha = 0.10f),
                                        )
                                    } else {
                                        listOf(colors.subtleFill, colors.subtleFill)
                                    }
                                ),
                                shape = CircleShape
                            )
                            .graphicsLayer { this.alpha = if (template.canDelete) 1f else 0.38f }
                            .noRippleClick(enabled = template.canDelete, onClick = onDelete),
                        contentAlignment = Alignment.Center,
                    ) {
                        AppIcon(
                            icon = AppIconId.Delete,
                            contentDescription = "删除模板",
                            tint = if (template.canDelete) ShellColors.AccentRed else colors.textMuted,
                        )
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
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 32.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(14.dp),
        ) {
            Box(
                modifier = Modifier
                    .size(104.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(
                        brush = Brush.verticalGradient(
                            colors = if (isDark) {
                                listOf(Color.White.copy(alpha = 0.10f), Color.White.copy(alpha = 0.04f))
                            } else {
                                listOf(Color.White.copy(alpha = 0.94f), Color(0xFFF2F3F7).copy(alpha = 0.90f))
                            },
                        ),
                    )
                    .border(
                        width = 1.dp,
                        color = if (isDark) Color.White.copy(alpha = 0.10f) else Color.White.copy(alpha = 0.86f),
                        shape = RoundedCornerShape(28.dp),
                    ),
                contentAlignment = Alignment.Center,
            ) {
                AppIcon(
                    icon = AppIconId.Template,
                    contentDescription = null,
                    tint = colors.textSecondary.copy(alpha = if (isDark) 0.72f else 0.58f),
                    modifier = Modifier.size(44.dp),
                )
            }
            Text(
                text = "还没有模板",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary,
            )
            Text(
                text = "点右上角绿色按钮添加",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = colors.textSecondary,
            )
        }
    }
}
