package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.components.noRippleClick
import com.example.shellshot.ui.theme.MotionConstants
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import dev.chrisbanes.haze.HazeState

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TemplateOverviewSheet(
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
                if (isDark) Color(0xFF050507) else Color(0xFFF5F2EC),
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
                                    Color(0xFFF5F2EC),
                                    Color(0xFFF5F2EC),
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
                            overlapIndex = if (index > 0) index else -1,
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
fun OverviewCard(
    template: ShellTemplate,
    isDark: Boolean,
    active: Boolean,
    overlapIndex: Int = -1,
    animatedVisibilityScope: androidx.compose.animation.AnimatedVisibilityScope,
    sharedTransitionScope: SharedTransitionScope,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val previewSharedModifier = with(sharedTransitionScope) {
        Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = "preview-${template.id}"),
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
    val titleSharedModifier = with(sharedTransitionScope) {
        Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = "title-${template.id}"),
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
    val resolutionSharedModifier = with(sharedTransitionScope) {
        Modifier.sharedElement(
            sharedContentState = rememberSharedContentState(key = "resolution-${template.id}"),
            animatedVisibilityScope = animatedVisibilityScope,
        )
    }
    val isStacked = overlapIndex >= 0
    val stackAlpha = if (isStacked) (1f - overlapIndex * 0.2f).coerceIn(0.3f, 1f) else 1f
    val stackOffset = if (isStacked) (-16 * overlapIndex).dp else 0.dp
    val stackShadow = if (isStacked) (4 + overlapIndex * 2).dp else 4.dp
    val stackBorderAlpha = if (isStacked) (0.1f + overlapIndex * 0.05f).coerceIn(0.1f, 0.3f) else 0.1f
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = stackOffset)
            .graphicsLayer {
                alpha = stackAlpha
                shadowElevation = stackShadow.toPx()
                shape = RoundedCornerShape(if (active) 32.dp else 28.dp)
                clip = false
            }
            .background(colors.surface, RoundedCornerShape(if (active) 32.dp else 28.dp))
            .border(
                width = 1.5.dp,
                color = when {
                    active -> ShellColors.AccentBlue.copy(alpha = 0.2f)
                    isStacked -> colors.separator.copy(alpha = stackBorderAlpha)
                    else -> colors.separator.copy(alpha = 0.1f)
                },
                shape = RoundedCornerShape(if (active) 32.dp else 28.dp),
            )
            .padding(horizontal = 20.dp, vertical = if (active) 24.dp else 18.dp)
            .noRippleClick(onClick),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = previewSharedModifier
                .size(if (active) 56.dp else 52.dp)
                .background(
                    if (active) ShellColors.AccentBlue.copy(alpha = 0.12f) else colors.subtleFill,
                    RoundedCornerShape(16.dp),
                ),
            contentAlignment = Alignment.Center,
        ) {
            TemplatePreviewThumbnail(
                previewPath = template.displayPreviewAsset,
                contentDescription = template.name,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(6.dp),
                cornerRadius = 12.dp,
            )
        }
        Text(
            text = template.name,
            modifier = titleSharedModifier
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
            modifier = resolutionSharedModifier,
            fontSize = if (active) 14.sp else 13.sp,
            fontWeight = FontWeight.Bold,
            color = if (active) ShellColors.AccentBlue else colors.textSecondary,
        )
    }
}

private fun Modifier.offset(y: androidx.compose.ui.unit.Dp): Modifier = this.then(
    Modifier.padding(top = y)
)

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun OverviewDetailDialog(
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
        animationSpec = MotionConstants.sheetSpring,
        label = "overview-detail-lift",
    )
    val contentScale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.986f,
        animationSpec = MotionConstants.sheetSpring,
        label = "overview-detail-scale",
    )
    val contentAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = if (visible) MotionConstants.SettleMs else 220,
            easing = MotionConstants.iosEaseInOut,
        ),
        label = "overview-detail-alpha",
    )
    val scrimAlpha by animateFloatAsState(
        targetValue = if (visible) 0.48f else 0f,
        animationSpec = tween(
            durationMillis = if (visible) MotionConstants.OverlayMs else 220,
            easing = MotionConstants.iosEaseInOut,
        ),
        label = "overview-detail-scrim-alpha",
    )
    val chromeAlpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(
            durationMillis = MotionConstants.SettleMs,
            delayMillis = if (visible) 80 else 0,
            easing = MotionConstants.iosEaseOut,
        ),
        label = "overview-detail-chrome-alpha",
    )
    val contentOffset by animateFloatAsState(
        targetValue = if (visible) 0f else 8f,
        animationSpec = tween(
            durationMillis = MotionConstants.SettleMs,
            delayMillis = if (visible) 90 else 0,
            easing = MotionConstants.iosEaseOut,
        ),
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
                        previewPath = template.displayPreviewAsset,
                        contentDescription = template.name,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(12.dp),
                        cornerRadius = 20.dp,
                    )
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
