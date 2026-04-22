package com.example.shellshot.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.AppTab
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect

data class DockItem(
    val tab: AppTab,
    val label: String,
    val icon: AppIconId,
    val visible: Boolean = true,
)

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
        Row(
            modifier = Modifier
                .height(68.dp)
                .hazeEffect(hazeState)
                .background(MaterialTheme.shellShotTokens.colors.glass, CircleShape)
                .border(1.dp, MaterialTheme.shellShotTokens.colors.glassStroke, CircleShape)
                .padding(horizontal = 6.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            visibleItems.forEach { item ->
                DockEntry(
                    item = item,
                    selected = item.tab == activeTab,
                    isDark = isDark,
                    onClick = { onTabSelected(item.tab) },
                )
            }
        }
    }
}

@Composable
private fun DockEntry(
    item: DockItem,
    selected: Boolean,
    isDark: Boolean,
    onClick: () -> Unit,
) {
    val width by animateDpAsState(
        targetValue = if (selected) 72.dp else 56.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "dock-width-${item.tab}",
    )
    val iconOffset by animateDpAsState(
        targetValue = if (selected) (-6).dp else 0.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "dock-icon-offset-${item.tab}",
    )
    val sliderAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = spring(stiffness = 300f, dampingRatio = 0.8f),
        label = "dock-slider-alpha-${item.tab}",
    )
    Box(
        modifier = Modifier
            .width(width)
            .height(56.dp)
            .noRippleClick(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(72.dp)
                .graphicsLayer { alpha = sliderAlpha }
                .background(Color.White.copy(alpha = if (isDark) 0.18f else 0.58f), CircleShape)
                .border(1.dp, Color.White.copy(alpha = if (isDark) 0.2f else 0.65f), CircleShape),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
                    .width(40.dp)
                    .height(1.5.dp)
                    .background(Color.White.copy(alpha = 0.6f), CircleShape),
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            AppIcon(
                icon = item.icon,
                contentDescription = item.label,
                tint = if (selected) ShellColors.AccentBlue else MaterialTheme.shellShotTokens.colors.textMuted,
                modifier = Modifier
                    .size(22.dp)
                    .offset(y = iconOffset),
            )
            AnimatedVisibility(
                visible = selected,
                enter = fadeIn(),
                exit = fadeOut(),
            ) {
                Text(
                    text = item.label,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                    color = ShellColors.AccentBlue,
                    modifier = Modifier.offset(y = 4.dp),
                )
            }
        }
    }
}
