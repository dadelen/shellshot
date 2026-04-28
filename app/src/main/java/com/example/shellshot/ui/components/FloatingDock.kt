package com.example.shellshot.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.AppTab
import com.example.shellshot.ui.theme.ShellColors
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class DockItem(
    val tab: AppTab,
    val label: String,
    val icon: AppIconId,
    val visible: Boolean = true,
)

private val DockShellHeight = 62.dp
private val DockItemWidth = 56.dp
private val DockPillWidth = 68.dp
private val DockSpacing = 4.dp
private val DockHorizontalPadding = 6.dp
private val DockVerticalPadding = 4.dp
private val DockShape = RoundedCornerShape(percent = 50)
private val DockPillShape = RoundedCornerShape(percent = 50)

@Composable
fun FloatingDock(
    items: List<DockItem>,
    activeTab: AppTab,
    isDark: Boolean,
    hazeState: HazeState,
    detailMode: Boolean,
    modifier: Modifier = Modifier,
    onTabSelected: (AppTab) -> Unit,
) {
    val visibleItems = items.filter { it.visible }
    if (visibleItems.isEmpty()) return

    val haptics = rememberShellShotHaptics()
    val scope = rememberCoroutineScope()
    val activeIndex = visibleItems.indexOfFirst { it.tab == activeTab }.coerceAtLeast(0)
    var stretchPill by remember { mutableStateOf(false) }

    val shellWidth = remember(visibleItems.size) {
        val itemWidths = visibleItems.size * DockItemWidth.value
        val itemSpacing = (visibleItems.size - 1).coerceAtLeast(0) * DockSpacing.value
        itemWidths + itemSpacing + (DockHorizontalPadding.value * 2f)
    }.dp
    val activeOffset by animateDpAsState(
        targetValue = (DockHorizontalPadding + (DockItemWidth + DockSpacing) * activeIndex) -
            ((DockPillWidth - DockItemWidth) / 2),
        animationSpec = spring(stiffness = 340f, dampingRatio = 0.82f),
        label = "dock-active-offset",
    )
    val pillScaleX by animateFloatAsState(
        targetValue = if (stretchPill) 1.08f else 1f,
        animationSpec = spring(stiffness = 320f, dampingRatio = 0.78f),
        label = "dock-pill-scale-x",
    )
    val pillScaleY by animateFloatAsState(
        targetValue = if (stretchPill) 0.96f else 1f,
        animationSpec = spring(stiffness = 320f, dampingRatio = 0.82f),
        label = "dock-pill-scale-y",
    )
    val dockOffsetY by animateDpAsState(
        targetValue = if (detailMode) 150.dp else 0.dp,
        animationSpec = spring(stiffness = 350f, dampingRatio = 0.85f),
        label = "dock-y",
    )
    val dockScale by animateFloatAsState(
        targetValue = if (detailMode) 0.82f else 1f,
        animationSpec = spring(stiffness = 350f, dampingRatio = 0.85f),
        label = "dock-scale",
    )
    val dockAlpha by animateFloatAsState(
        targetValue = if (detailMode) 0f else 1f,
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "dock-alpha",
    )

    LaunchedEffect(activeTab) {
        stretchPill = true
        delay(170)
        stretchPill = false
    }

    Box(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(bottom = 30.dp)
            .graphicsLayer {
                translationY = dockOffsetY.toPx()
                scaleX = dockScale
                scaleY = dockScale
                alpha = dockAlpha
            },
        contentAlignment = Alignment.BottomCenter,
    ) {
        DockShell(
            isDark = isDark,
            hazeState = hazeState,
            width = shellWidth,
            modifier = Modifier,
        ) {
            DockSelectionPill(
                isDark = isDark,
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = activeOffset)
                    .graphicsLayer {
                        scaleX = pillScaleX
                        scaleY = pillScaleY
                    },
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = DockHorizontalPadding, vertical = DockVerticalPadding),
                horizontalArrangement = Arrangement.spacedBy(DockSpacing),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                visibleItems.forEach { item ->
                    DockItemButton(
                        item = item,
                        selected = item.tab == activeTab,
                        isDark = isDark,
                        onClick = {
                            if (item.tab != activeTab) {
                                haptics.dockTick()
                                scope.launch {
                                    stretchPill = true
                                    delay(170)
                                    stretchPill = false
                                }
                                onTabSelected(item.tab)
                            }
                        },
                    )
                }
            }
        }
    }
}

@Composable
private fun DockShell(
    isDark: Boolean,
    hazeState: HazeState,
    width: androidx.compose.ui.unit.Dp,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val shadowColor = if (isDark) {
        Color.Black.copy(alpha = 0.32f)
    } else {
        Color(0xFFB6BCC7).copy(alpha = 0.18f)
    }
    val shellBrush = Brush.verticalGradient(
        colors = if (isDark) {
            listOf(
                Color(0xD92A2E35),
                Color(0xCF1C2027),
            )
        } else {
            listOf(
                Color(0xEEF8F8F6),
                Color(0xE7EEEDE9),
            )
        },
    )
    val shellStroke = if (isDark) {
        Color.White.copy(alpha = 0.11f)
    } else {
        Color.White.copy(alpha = 0.68f)
    }
    val innerFog = if (isDark) {
        Color.White.copy(alpha = 0.035f)
    } else {
        Color.White.copy(alpha = 0.33f)
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .width(width + 8.dp)
                .height(DockShellHeight + 10.dp)
                .blur(14.dp)
                .background(shadowColor, DockShape),
        )
        Box(
            modifier = Modifier
                .width(width)
                .height(DockShellHeight)
                .hazeEffect(hazeState)
                .clip(DockShape)
                .background(shellBrush, DockShape)
                .border(width = 0.8.dp, color = shellStroke, shape = DockShape),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(1.dp)
                    .clip(DockShape)
                    .background(innerFog),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 1.dp, bottom = 20.dp, start = 12.dp, end = 12.dp)
                    .clip(DockShape)
                    .background(
                        Brush.verticalGradient(
                            colors = if (isDark) {
                                listOf(
                                    Color.White.copy(alpha = 0.06f),
                                    Color.Transparent,
                                )
                            } else {
                                listOf(
                                    Color.White.copy(alpha = 0.34f),
                                    Color.Transparent,
                                )
                            },
                        ),
                    ),
            )
            content()
        }
    }
}

@Composable
private fun DockSelectionPill(
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    val pillBrush = Brush.verticalGradient(
        colors = if (isDark) {
            listOf(
                Color.White.copy(alpha = 0.14f),
                Color.White.copy(alpha = 0.055f),
            )
        } else {
            listOf(
                Color.White.copy(alpha = 0.58f),
                Color.White.copy(alpha = 0.22f),
            )
        },
    )
    val pillStroke = if (isDark) {
        Color.White.copy(alpha = 0.12f)
    } else {
        Color.White.copy(alpha = 0.52f)
    }

    Box(
        modifier = modifier
            .width(DockPillWidth)
            .height(54.dp)
            .padding(vertical = 3.dp)
            .clip(DockPillShape)
            .background(pillBrush, DockPillShape)
            .border(width = 0.7.dp, color = pillStroke, shape = DockPillShape),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp)
                .clip(DockPillShape)
                .background(
                    Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color.White.copy(alpha = 0.06f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.05f),
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.36f),
                                Color.White.copy(alpha = 0.12f),
                                Color(0xFFDDE4EF).copy(alpha = 0.08f),
                            )
                        },
                    ),
                ),
        )
    }
}

@Composable
private fun DockItemButton(
    item: DockItem,
    selected: Boolean,
    isDark: Boolean,
    onClick: () -> Unit,
) {
    val iconTint by animateColorAsState(
        targetValue = if (selected) ShellColors.AccentBlue else if (isDark) Color(0xFF9AA1AC) else Color(0xFF6D7480),
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "dock-icon-tint-${item.tab}",
    )
    val iconOffset by animateDpAsState(
        targetValue = if (selected) (-5).dp else 0.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.86f),
        label = "dock-icon-offset-${item.tab}",
    )
    val labelOffset by animateDpAsState(
        targetValue = if (selected) 10.dp else 12.dp,
        animationSpec = spring(stiffness = 460f, dampingRatio = 0.88f),
        label = "dock-label-offset-${item.tab}",
    )
    val labelScale by animateFloatAsState(
        targetValue = if (selected) 1f else 0.94f,
        animationSpec = spring(stiffness = 460f, dampingRatio = 0.88f),
        label = "dock-label-scale-${item.tab}",
    )

    Box(
        modifier = Modifier
            .width(DockItemWidth)
            .fillMaxHeight()
            .noRippleClick(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AppIcon(
                icon = item.icon,
                contentDescription = item.label,
                tint = iconTint,
                modifier = Modifier
                    .size(22.dp)
                    .offset(y = iconOffset),
            )
        }

        AnimatedVisibility(
            visible = selected,
            enter = fadeIn(animationSpec = tween(160, easing = FastOutSlowInEasing)) +
                scaleIn(initialScale = 0.92f, animationSpec = tween(220, easing = FastOutSlowInEasing)),
            exit = fadeOut(animationSpec = tween(110)) +
                scaleOut(targetScale = 0.96f, animationSpec = tween(150)),
            modifier = Modifier.align(Alignment.Center),
        ) {
            Text(
                text = item.label,
                fontSize = 10.sp,
                lineHeight = 10.sp,
                fontWeight = FontWeight.SemiBold,
                color = ShellColors.AccentBlue,
                modifier = Modifier.graphicsLayer {
                    translationY = labelOffset.toPx()
                    scaleX = labelScale
                    scaleY = labelScale
                },
            )
        }
    }
}
