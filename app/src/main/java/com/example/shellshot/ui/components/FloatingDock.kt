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
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.AppTab
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

private val DockShellHeight = 64.dp
private val DockItemWidth = 56.dp
private val DockActiveWidth = 72.dp
private val DockSpacing = 4.dp
private val DockSliderCenterOffset = (DockActiveWidth - DockItemWidth) / 2

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
    var sliderVisible by remember { mutableStateOf(false) }
    var previousTab by remember { mutableStateOf(activeTab) }
    var sliderStretch by remember { mutableStateOf(false) }
    val activeOffset by animateDpAsState(
        targetValue = ((DockItemWidth + DockSpacing) * activeIndex) - DockSliderCenterOffset,
        animationSpec = spring(stiffness = 235f, dampingRatio = 0.90f),
        label = "dock-active-offset",
    )
    val sliderScaleX by animateFloatAsState(
        targetValue = if (sliderStretch) 1.16f else 1f,
        animationSpec = spring(stiffness = 210f, dampingRatio = 0.72f),
        label = "dock-slider-scale-x",
    )
    val sliderScaleY by animateFloatAsState(
        targetValue = if (sliderStretch) 0.92f else 1f,
        animationSpec = spring(stiffness = 235f, dampingRatio = 0.76f),
        label = "dock-slider-scale-y",
    )
    LaunchedEffect(activeTab) {
        if (previousTab != activeTab) {
            sliderVisible = true
            sliderStretch = true
            previousTab = activeTab
            delay(300)
            sliderStretch = false
            delay(520)
            sliderVisible = false
        }
    }
    val dockOffsetY by animateDpAsState(
        targetValue = if (detailMode) 150.dp else 0.dp,
        animationSpec = spring(stiffness = 350f, dampingRatio = 0.85f),
        label = "dock-y",
    )
    val dockScale by animateFloatAsState(
        targetValue = if (detailMode) 0.8f else 1f,
        animationSpec = spring(stiffness = 350f, dampingRatio = 0.85f),
        label = "dock-scale",
    )
    val dockAlpha by animateFloatAsState(
        targetValue = if (detailMode) 0f else 1f,
        animationSpec = spring(stiffness = 350f, dampingRatio = 0.85f),
        label = "dock-alpha",
    )
    val shellWidth = remember(visibleItems.size) {
        (visibleItems.size * 56) + ((visibleItems.size - 1).coerceAtLeast(0) * 4) + 12
    }.dp

    Box(
        modifier = modifier
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(bottom = 32.dp)
            .graphicsLayer {
                translationY = dockOffsetY.toPx()
                scaleX = dockScale
                scaleY = dockScale
                alpha = dockAlpha
            },
        contentAlignment = Alignment.BottomCenter,
    ) {
        Box(
            modifier = Modifier
                .width(shellWidth + 10.dp)
                .height(DockShellHeight + 8.dp)
                .blur(12.dp)
                .background(
                    color = if (isDark) Color.Black.copy(alpha = 0.22f) else Color(0xFFC9D0DB).copy(alpha = 0.12f),
                    shape = CircleShape,
                ),
        )

        Box(
            modifier = Modifier
                .width(shellWidth)
                .height(DockShellHeight)
                .hazeEffect(hazeState)
                .clip(CircleShape)
                .background(
                    brush = Brush.linearGradient(
                        colors = if (isDark) {
                            listOf(
                                Color(0xD2272B33),
                                Color(0xC61B1F27),
                            )
                        } else {
                            listOf(
                                Color(0xF0F8F7F4),
                                Color(0xE8F0EEEA),
                            )
                        },
                    ),
                    shape = CircleShape,
                )
                .border(
                    width = 0.9.dp,
                    color = if (isDark) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.52f),
                    shape = CircleShape,
                )
                .blur(0.3.dp)
                .padding(horizontal = 6.dp, vertical = 4.dp),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = if (isDark) Color.Black.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.28f),
                        shape = CircleShape,
                    ),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        brush = Brush.radialGradient(
                            colors = if (isDark) {
                                listOf(
                                    Color.White.copy(alpha = 0.035f),
                                    Color.Transparent,
                                )
                            } else {
                                listOf(
                                    Color.White.copy(alpha = 0.14f),
                                    Color.Transparent,
                                )
                            },
                        ),
                    ),
            )
            AnimatedVisibility(
                visible = sliderVisible,
                enter = fadeIn(animationSpec = tween(120, easing = FastOutSlowInEasing)) +
                    scaleIn(
                        initialScale = 0.94f,
                        animationSpec = tween(260, easing = FastOutSlowInEasing),
                    ),
                exit = fadeOut(animationSpec = tween(300, easing = FastOutSlowInEasing)) +
                    scaleOut(
                        targetScale = 0.985f,
                        animationSpec = tween(340, easing = FastOutSlowInEasing),
                    ),
                modifier = Modifier
                    .offset(x = activeOffset)
                    .graphicsLayer {
                        scaleX = sliderScaleX
                        scaleY = sliderScaleY
                    }
                    .align(Alignment.CenterStart),
            ) {
                DockSlider(isDark = isDark)
            }

            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.spacedBy(DockSpacing),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                visibleItems.forEach { item ->
                    DockButton(
                        item = item,
                        selected = item.tab == activeTab,
                        isDark = isDark,
                        onClick = {
                            if (item.tab != activeTab) {
                                haptics.dockTick()
                                sliderStretch = true
                                scope.launch {
                                    delay(300)
                                    sliderStretch = false
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
private fun DockSlider(
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .width(DockActiveWidth)
            .height(56.dp)
            .blur(0.5.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = if (isDark) {
                        listOf(
                            Color.White.copy(alpha = 0.16f),
                            Color.White.copy(alpha = 0.05f),
                        )
                    } else {
                        listOf(
                            Color.White.copy(alpha = 0.48f),
                            Color.White.copy(alpha = 0.18f),
                        )
                    },
                ),
                shape = CircleShape,
            )
            .border(
                width = 0.6.dp,
                color = if (isDark) Color.White.copy(alpha = 0.16f) else Color.White.copy(alpha = 0.38f),
                shape = CircleShape,
            ),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(1.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color.White.copy(alpha = 0.06f),
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.08f),
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.18f),
                                Color.Transparent,
                                Color(0xFFDCE4F3).copy(alpha = 0.12f),
                            )
                        },
                    ),
                    shape = CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            if (isDark) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.24f),
                            Color.Transparent,
                        ),
                    ),
                    shape = CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color.White.copy(alpha = 0.16f),
                                Color.White.copy(alpha = 0.06f),
                                Color.Transparent,
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.52f),
                                Color.White.copy(alpha = 0.20f),
                                Color.Transparent,
                            )
                        },
                    ),
                    shape = CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp, vertical = 6.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color.White.copy(alpha = 0.12f),
                                Color.Transparent,
                                Color.White.copy(alpha = 0.06f),
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.34f),
                                Color.Transparent,
                                Color.White.copy(alpha = 0.12f),
                            )
                        },
                        tileMode = TileMode.Clamp,
                    ),
                    shape = CircleShape,
                )
                .blur(5.dp),
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 3.dp)
                .width(46.dp)
                .height(18.dp)
                .background(
                    brush = Brush.verticalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color.White.copy(alpha = 0.22f),
                                Color.Transparent,
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.68f),
                                Color.Transparent,
                            )
                        },
                    ),
                    shape = CircleShape,
                )
                .blur(6.dp),
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 12.dp, top = 7.dp)
                .width(22.dp)
                .height(8.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = if (isDark) {
                            listOf(
                                Color.White.copy(alpha = 0.18f),
                                Color.Transparent,
                            )
                        } else {
                            listOf(
                                Color.White.copy(alpha = 0.72f),
                                Color.Transparent,
                            )
                        },
                    ),
                    shape = CircleShape,
                )
                .blur(3.dp),
        )
    }
}

@Composable
private fun DockButton(
    item: DockItem,
    selected: Boolean,
    isDark: Boolean,
    onClick: () -> Unit,
) {
    val iconOffset by animateDpAsState(
        targetValue = if (selected) (-6).dp else 0.dp,
        animationSpec = spring(stiffness = 480f, dampingRatio = 0.88f),
        label = "dock-icon-offset-${item.tab}",
    )
    val labelOffset by animateDpAsState(
        targetValue = if (selected) 10.dp else 14.dp,
        animationSpec = spring(stiffness = 480f, dampingRatio = 0.88f),
        label = "dock-label-offset-${item.tab}",
    )
    val labelScale by animateFloatAsState(
        targetValue = if (selected) 1f else 0.82f,
        animationSpec = spring(stiffness = 480f, dampingRatio = 0.88f),
        label = "dock-label-scale-${item.tab}",
    )

    Box(
        modifier = Modifier
            .width(DockItemWidth)
            .fillMaxHeight()
            .noRippleClick(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            AppIcon(
                icon = item.icon,
                contentDescription = item.label,
                tint = if (selected) Color(0xFF007AFF) else if (isDark) Color(0xFF9CA3AF) else Color(0xFF6B7280),
                modifier = Modifier
                    .size(22.dp)
                    .offset(y = iconOffset),
            )

            AnimatedVisibility(
                visible = selected,
                enter = fadeIn(tween(180, easing = FastOutSlowInEasing)),
                exit = fadeOut(tween(120)),
                modifier = Modifier.align(Alignment.Center),
            ) {
                Text(
                    text = item.label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF007AFF),
                    modifier = Modifier.graphicsLayer {
                        translationY = labelOffset.toPx()
                        scaleX = labelScale
                        scaleY = labelScale
                    },
                )
            }
        }
    }
}
