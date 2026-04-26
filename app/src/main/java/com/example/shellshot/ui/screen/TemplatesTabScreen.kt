package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.BoxScope
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.zIndex

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
import com.example.shellshot.ui.components.rememberShellShotHaptics
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
import kotlin.math.sign
import kotlin.math.roundToInt
import kotlinx.coroutines.delay

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
                animationSpec = spring(stiffness = 250f, dampingRatio = 0.65f),
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

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .windowInsetsPadding(WindowInsets.navigationBars)
                .padding(top = 8.dp),
        ) {
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
                        .offset(y = (-24).dp)
                        .pointerInput(templates.map { it.id }) {
                            detectHorizontalDragGestures(
                                onDragStart = {
                                    dragAccumulator = 0f
                                    dragVelocityHint = 0f
                                    dragStartIndex = animatable.value.roundToInt()
                                },
                                onHorizontalDrag = { change, dragAmount ->
                                    if (templates.isNotEmpty()) {
                                        change.consume()
                                        val dragAmountDp = with(density) { dragAmount.toDp().value }
                                        dragAccumulator += dragAmountDp
                                        dragVelocityHint = dragAmountDp
                                        // Keep the prototype trajectory, with only a slight extra damping.
                                        val base = dragStartIndex.toFloat()
                                        val elasticDeviation = (-(dragAccumulator) / 125f) * 0.22f
                                        carouselScope.launch {
                                            animatable.snapTo(base + elasticDeviation)
                                        }
                                    }
                                },
                                onDragEnd = {
                                    carouselScope.launch {
                                        val projectedDrag = dragAccumulator + (dragVelocityHint * 8f)
                                        val threshold = 40f
                                        val offset = if (projectedDrag < -threshold) 1
                                                     else if (projectedDrag > threshold) -1
                                                     else 0
                                        val target = dragStartIndex + offset
                                        animatable.animateTo(
                                            targetValue = target.toFloat(),
                                            animationSpec = spring(
                                                stiffness = 250f,
                                                dampingRatio = 0.65f,
                                            ),
                                        )
                                        if (offset != 0) {
                                            delay(70)
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
                            .width(388.dp)
                            .height(592.dp)
                            .background(
                                brush = Brush.radialGradient(
                                    colors = listOf(
                                        ShellColors.AccentBlue.copy(alpha = if (isDark) 0.16f else 0.24f),
                                        Color.White.copy(alpha = if (isDark) 0.04f else 0.10f),
                                        Color.Transparent,
                                    )
                                )
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
                            modifier = Modifier
                                .align(Alignment.Center)
                                .zIndex(50f - abs(relativeOffset)),
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
                    // Exit animation overlay for deleted templates
                    exitingTemplates.forEach { (id, template) ->
                        androidx.compose.runtime.key("exit-$id") {
                            ExitingTemplateGhost(
                                template = template,
                                isDark = isDark,
                                modifier = Modifier.align(Alignment.Center).zIndex(100f),
                                onFinished = {
                                    exitingTemplates = exitingTemplates - id
                                },
                            )
                        }
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

        retainedOverviewDetailTemplate?.let { template ->
            OverviewDetailDialog(
                template = template,
                visible = overviewDetailVisible,
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
                onConfirm = {
                    exitingTemplates = exitingTemplates + (template.id to template)
                    onDismissDeleteTemplate()
                    carouselScope.launch {
                        kotlinx.coroutines.delay(340)
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
            enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
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
            .padding(horizontal = 24.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            "模板",
            fontSize = 30.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = (-0.6).sp,
            color = colors.textPrimary,
        )
        Row(
            modifier = Modifier
                .background(colors.glass.copy(alpha = if (isDark) 0.94f else 0.98f), CircleShape)
                .border(1.dp, colors.glassStroke.copy(alpha = if (isDark) 0.9f else 0.7f), CircleShape)
                .padding(horizontal = 4.dp, vertical = 4.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            CountCapsule(text = "$count", isDark = isDark)
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(colors.subtleFill, CircleShape)
                    .noRippleClick(onOpenOverview),
                contentAlignment = Alignment.Center,
            ) {
                AppIcon(AppIconId.Stats, "模板总览", tint = colors.textMuted)
            }
            Box(
                modifier = Modifier
                    .size(36.dp)
                    .background(ShellColors.AccentBlue, CircleShape)
                    .noRippleClick(onAdd),
                contentAlignment = Alignment.Center,
            ) {
                AppIcon(AppIconId.Add, "新增模板", tint = Color.White)
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
    val colors = MaterialTheme.shellShotTokens.colors
    val absOffset = abs(relativeOffset)
    val isCenter = absOffset < 0.35f
    val showActionRow = absOffset < 0.6f
    val alpha = when {
        absOffset >= 3f -> 0f
        else -> (1f - absOffset * 0.4f).coerceAtLeast(0f)
    }
    val scale = 1f - absOffset * 0.12f
    val rotation = relativeOffset * 12f
    Box(
        modifier = modifier
            .width(260.dp)
            .height(480.dp)
            .graphicsLayer {
                translationX = (relativeOffset * 140.dp.toPx())
                translationY = (abs(relativeOffset) * 35.dp.toPx())
                rotationZ = rotation
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
                transformOrigin = TransformOrigin(0.5f, 1f)
                shadowElevation = if (isCenter) 40.dp.toPx() else 8.dp.toPx()
                shape = RoundedCornerShape(40.dp)
                clip = false
            }
            .noRippleClick(enabled = !isCenter, onClick = onClick),
    ) {
        if (isCenter) {
            CardAura(isDark = isDark)
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(40.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(
                                colors.surface.copy(alpha = if (isCenter) 1f else 0.92f),
                                colors.surfaceAlt.copy(alpha = if (isCenter) 0.98f else 0.88f),
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = if (isCenter) 1f else 0.95f),
                                Color(0xFFF7F7FA).copy(alpha = if (isCenter) 0.98f else 0.90f),
                            )
                        },
                    ),
                    shape = RoundedCornerShape(40.dp),
                )
                .border(
                    width = if (isCenter) 6.dp else 4.dp,
                    color = if (isDark) {
                        Color.White.copy(alpha = if (isCenter) 0.08f else 0.04f)
                    } else {
                        Color(0xFFF7F7F9).copy(alpha = if (isCenter) 1f else 0.76f)
                    },
                    shape = RoundedCornerShape(40.dp),
                )
        )
        Box(
            modifier = Modifier
                .fillMaxWidth(if (isCenter) 0.82f else 0.72f)
                .height(if (isCenter) 56.dp else 42.dp)
                .align(Alignment.TopCenter)
                .offset(y = 12.dp)
                .blur(if (isCenter) 18.dp else 10.dp)
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            Color.White.copy(alpha = if (isCenter) 0.42f else 0.16f),
                            Color.Transparent,
                        )
                    ),
                    shape = CircleShape,
                ),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(22.dp))
            Box(
                modifier = Modifier
                    .width(136.dp)
                    .height(228.dp),
                contentAlignment = Alignment.Center,
            ) {
                TemplatePreviewThumbnail(
                    previewPath = template.frameAsset,
                    contentDescription = template.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp, vertical = 6.dp),
                    cornerRadius = 24.dp,
                    selected = selected,
                )
                Box(
                    modifier = Modifier
                        .matchParentSizeCompat()
                        .padding(horizontal = 10.dp, vertical = 12.dp)
                        .blur(if (isCenter) 12.dp else 7.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = if (isCenter) 0.16f else 0.08f),
                                    Color.Transparent,
                                ),
                            ),
                            shape = RoundedCornerShape(28.dp),
                        )
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Column(
                modifier = Modifier,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(
                    template.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = colors.textPrimary.copy(alpha = if (isCenter) 1f else 0.84f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(
                    text = "${template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width} × ${template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height}",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.textSecondary.copy(alpha = if (isCenter) 0.88f else 0.58f),
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.animation.AnimatedVisibility(
                visible = showActionRow,
                enter = fadeIn(tween(260, easing = FastOutSlowInEasing)) + slideInVertically(tween(260, easing = FastOutSlowInEasing), initialOffsetY = { 10 }),
                exit = fadeOut(tween(170, easing = FastOutSlowInEasing)) + slideOutVertically(tween(170, easing = FastOutSlowInEasing), targetOffsetY = { 10 }),
            ) {
                Row(
                    modifier = Modifier
                        .zIndex(5f)
                        .fillMaxWidth()
                        .padding(bottom = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (selected) {
                        Box(
                            modifier = Modifier
                                .background(
                                    color = ShellColors.AccentBlue.copy(alpha = if (isDark) 0.22f else 0.12f),
                                    shape = CircleShape,
                                )
                                .border(
                                    width = 1.dp,
                                    color = ShellColors.AccentBlue.copy(alpha = if (isDark) 0.26f else 0.16f),
                                    shape = CircleShape,
                                )
                                .padding(horizontal = 24.dp, vertical = 10.dp),
                        ) {
                            Text("已应用", fontSize = 12.sp, fontWeight = FontWeight.Bold, color = ShellColors.AccentBlue)
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
                    Box(
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(40.dp)
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
private fun ExitingTemplateGhost(
    template: ShellTemplate,
    isDark: Boolean,
    modifier: Modifier = Modifier,
    onFinished: () -> Unit,
) {
    var exiting by remember(template.id) { mutableStateOf(false) }
    val alpha by animateFloatAsState(
        targetValue = if (exiting) 0f else 1f,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "template-exit-alpha",
    )
    val scale by animateFloatAsState(
        targetValue = if (exiting) 0.6f else 1f,
        animationSpec = spring(dampingRatio = 0.85f, stiffness = 300f),
        label = "template-exit-scale",
    )
    val blurDp by animateFloatAsState(
        targetValue = if (exiting) 22f else 0f,
        animationSpec = tween(500, easing = FastOutSlowInEasing),
        label = "template-exit-blur",
    )
    val ghostAlpha by animateFloatAsState(
        targetValue = if (exiting) 0.18f else 0f,
        animationSpec = tween(420, easing = FastOutSlowInEasing),
        label = "template-exit-ghost",
    )

    LaunchedEffect(template.id) {
        exiting = true
        kotlinx.coroutines.delay(500)
        onFinished()
    }

    Box(
        modifier = modifier
            .graphicsLayer {
                this.alpha = alpha
                scaleX = scale
                scaleY = scale
                translationY = if (exiting) 120.dp.toPx() else 0f
            }
            .blur(blurDp.dp)
    ) {
        TemplateCarouselCard(
            template = template,
            selected = false,
            relativeOffset = 0f,
            isDark = isDark,
            onClick = {},
            onApply = {},
            onDelete = {},
        )
        Box(
            modifier = Modifier
                .matchParentSizeCompat()
                .background(Color.White.copy(alpha = ghostAlpha), RoundedCornerShape(40.dp))
        )
    }
}

@Composable
private fun Modifier.matchParentSizeCompat(): Modifier = this.fillMaxSize()

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

@Composable
private fun BoxScope.CardAura(
    isDark: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .graphicsLayer { alpha = if (isDark) 0.8f else 1f }
            .blur(42.dp)
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        Color.Black.copy(alpha = if (isDark) 0.32f else 0.18f),
                        Color(0xFF0A84FF).copy(alpha = if (isDark) 0.18f else 0.14f),
                        Color.Transparent,
                    ),
                ),
                shape = RoundedCornerShape(58.dp),
            )
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .blur(24.dp)
            .background(
                color = Color.Black.copy(alpha = if (isDark) 0.26f else 0.12f),
                shape = RoundedCornerShape(48.dp),
            )
    )
}

@Composable
private fun BoxScope.PreviewGlossOverlay(
    isDark: Boolean,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(32.dp))
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = if (isDark) 0.14f else 0.28f),
                        Color.White.copy(alpha = if (isDark) 0.06f else 0.12f),
                        Color.Transparent,
                        Color(0xFFB8E4FF).copy(alpha = if (isDark) 0.08f else 0.18f),
                    ),
                ),
            )
    )
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
            .background(
                if (isDark) Color(0xFF050507) else Color(0xFFF2F2F7),
            ),
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
                    .windowInsetsPadding(WindowInsets.statusBars)
                    .background(
                        Brush.verticalGradient(
                            colors = if (isDark) {
                                listOf(
                                    Color.Black.copy(alpha = 0.88f),
                                    Color.Black.copy(alpha = 0.78f),
                                    Color.Transparent,
                                )
                            } else {
                                listOf(
                                    Color(0xFFF2F2F7),
                                    Color(0xFFF2F2F7),
                                    Color.Transparent,
                                )
                            }
                        )
                    )
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
    visible: Boolean,
    isDark: Boolean,
    animatedVisibilityScope: androidx.compose.animation.AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onApply: () -> Unit,
    onDelete: () -> Unit,
    onDismiss: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val sharedModifier = with(sharedTransitionScope) {
        Modifier.sharedBounds(
            sharedContentState = rememberSharedContentState(key = "overview-card-${template.id}"),
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
    val contentLift by animateFloatAsState(
        targetValue = if (visible) 0f else 14f,
        animationSpec = spring(stiffness = 310f, dampingRatio = 0.85f),
        label = "overview-detail-lift",
    )
    val contentScale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.986f,
        animationSpec = spring(stiffness = 300f, dampingRatio = 0.85f),
        label = "overview-detail-scale",
    )
    val contentAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = if (visible) 260 else 220, easing = FastOutSlowInEasing),
        label = "overview-detail-alpha",
    )
    val scrimAlpha by animateFloatAsState(
        targetValue = if (visible) 0.48f else 0f,
        animationSpec = tween(durationMillis = if (visible) 300 else 220, easing = FastOutSlowInEasing),
        label = "overview-detail-scrim-alpha",
    )
    val chromeAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(durationMillis = 260, delayMillis = if (visible) 90 else 0, easing = FastOutSlowInEasing),
        label = "overview-detail-chrome-alpha",
    )
    val contentOffset by animateFloatAsState(
        targetValue = if (visible) 0f else 8f,
        animationSpec = tween(durationMillis = 280, delayMillis = if (visible) 110 else 0, easing = FastOutSlowInEasing),
        label = "overview-detail-content-offset",
    )
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = scrimAlpha))
                .noRippleClick(onDismiss),
        )
        GlassSurfaceCard(
            modifier = sharedModifier
                .width(360.dp)
                .graphicsLayer {
                    translationY = contentLift.dp.toPx()
                    scaleX = contentScale
                    scaleY = contentScale
                    alpha = contentAlpha
                },
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
                            .graphicsLayer { alpha = chromeAlpha }
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
                        .graphicsLayer { alpha = chromeAlpha }
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
                verticalArrangement = Arrangement.spacedBy(14.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
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
                Text(
                    template.name,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Black,
                    color = colors.textPrimary,
                    modifier = Modifier.graphicsLayer {
                        alpha = chromeAlpha
                        translationY = contentOffset.dp.toPx()
                    },
                )
                Box(
                    modifier = Modifier.graphicsLayer {
                        alpha = chromeAlpha
                        translationY = contentOffset.dp.toPx()
                    }
                ) {
                    InfoRow("分辨率", "${template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width} × ${template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height}", isDark)
                }
                Box(
                    modifier = Modifier.graphicsLayer {
                        alpha = chromeAlpha
                        translationY = contentOffset.dp.toPx()
                    }
                ) {
                    InfoRow("模板类型", if (template.canDelete) "用户自定义" else "内置模板", isDark)
                }
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .graphicsLayer {
                            alpha = chromeAlpha
                            translationY = contentOffset.dp.toPx()
                        }
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
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(950, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "spinner",
    )
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDark) Color(0xD60A0A0D) else Color(0xDDF2F1EE))
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        event.changes.forEach { it.consume() }
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .width(252.dp)
                .background(
                    color = if (isDark) {
                        Color(0xFF17181C)
                    } else {
                        Color(0xFFFCFCFD)
                    },
                    shape = RoundedCornerShape(30.dp),
                )
                .border(
                    width = 1.dp,
                    color = if (isDark) {
                        Color.White.copy(alpha = 0.08f)
                    } else {
                        Color.White.copy(alpha = 0.78f)
                    },
                    shape = RoundedCornerShape(30.dp),
                )
                .padding(horizontal = 28.dp, vertical = 26.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Canvas(
                modifier = Modifier
                    .size(48.dp)
                    .graphicsLayer { rotationZ = rotation },
            ) {
                drawArc(
                    color = if (isDark) {
                        Color.White.copy(alpha = 0.12f)
                    } else {
                        Color(0xFFCCD1DA)
                    },
                    startAngle = 0f,
                    sweepAngle = 360f,
                    useCenter = false,
                    style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round),
                )
                drawArc(
                    color = ShellColors.AccentBlue,
                    startAngle = -90f,
                    sweepAngle = 94f,
                    useCenter = false,
                    style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round),
                )
            }
            Text(
                "正在加载图片",
                modifier = Modifier.padding(top = 18.dp),
                color = colors.textPrimary,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
            )
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
    val leftColors = remember {
        listOf(
            ShellColors.AccentBlue.toArgb(),
            ShellColors.AccentPurple.toArgb(),
            ShellColors.AccentPink.toArgb(),
        )
    }
    val rightColors = remember {
        listOf(
            ShellColors.AccentBlue.toArgb(),
            ShellColors.AccentCyan.toArgb(),
            ShellColors.AccentGreen.toArgb(),
        )
    }
    val parties = remember(token) {
        listOf(
            Party(
                emitter = Emitter(duration = 3000, TimeUnit.MILLISECONDS).perSecond(80),
                angle = 60,
                spread = 55,
                speed = 35f,
                maxSpeed = 50f,
                damping = 0.9f,
                timeToLive = 3000L,
                fadeOutEnabled = true,
                position = Position.Relative(0.0, 1.0),
                colors = leftColors,
            ),
            Party(
                emitter = Emitter(duration = 3000, TimeUnit.MILLISECONDS).perSecond(80),
                angle = 120,
                spread = 55,
                speed = 35f,
                maxSpeed = 50f,
                damping = 0.9f,
                timeToLive = 3000L,
                fadeOutEnabled = true,
                position = Position.Relative(1.0, 1.0),
                colors = rightColors,
            ),
        )
    }
    LaunchedEffect(token) {
        kotlinx.coroutines.delay(4000)
        onConsumed(token)
    }
    KonfettiView(
        modifier = Modifier.fillMaxSize(),
        parties = parties,
    )
}
