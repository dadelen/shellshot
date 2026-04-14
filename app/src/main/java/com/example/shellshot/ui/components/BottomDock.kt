package com.example.shellshot.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.theme.ShellShotTheme

@Composable
fun BottomDock(
    items: List<NavItem>,
    selectedItemId: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    hidden: Boolean = false,
) {
    val visibleItems = items.filter { it.visible }
    if (visibleItems.isEmpty()) return

    val haptic = LocalHapticFeedback.current
    val shape = RoundedCornerShape(32.dp)
    val selectedIndex = visibleItems.indexOfFirst { it.id == selectedItemId }.coerceAtLeast(0)
    val inactiveWidth = 56.dp
    val activeWidth = 72.dp
    val targetXOffset = inactiveWidth * selectedIndex
    val sliderXOffset by animateDpAsState(
        targetValue = targetXOffset,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.75f),
        label = "dock-slider-x",
    )
    val sliderWidth by animateDpAsState(
        targetValue = activeWidth,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.75f),
        label = "dock-slider-width",
    )
    val dockOffsetY by animateDpAsState(
        targetValue = if (hidden) 150.dp else 0.dp,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.7f),
        label = "dock-hidden-offset",
    )
    val dockScale by animateFloatAsState(
        targetValue = if (hidden) 0.8f else 1f,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.7f),
        label = "dock-hidden-scale",
    )
    val dockAlpha by animateFloatAsState(
        targetValue = if (hidden) 0f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = "dock-hidden-alpha",
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
            }
            .shadow(
                elevation = 24.dp,
                shape = shape,
                clip = false,
                ambientColor = Color.Black.copy(alpha = if (darkTheme) 0.38f else 0.12f),
                spotColor = Color.Black.copy(alpha = if (darkTheme) 0.42f else 0.15f),
            )
            .clip(shape)
            .background(if (darkTheme) Color.Black.copy(alpha = 0.56f) else Color.White.copy(alpha = 0.86f))
            .border(
                width = 1.dp,
                color = if (darkTheme) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.50f),
                shape = shape,
            )
            .padding(6.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = sliderXOffset)
                .width(sliderWidth)
                .height(56.dp)
                .clip(RoundedCornerShape(28.dp))
                .background(
                    if (darkTheme) {
                        Brush.verticalGradient(
                            listOf(
                                Color.White.copy(alpha = 0.14f),
                                Color.White.copy(alpha = 0.06f),
                            ),
                        )
                    } else {
                        Brush.verticalGradient(
                            listOf(
                                Color.Black.copy(alpha = 0.05f),
                                Color.Transparent,
                            ),
                        )
                    },
                )
                .border(
                    width = 0.5.dp,
                    color = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.Black.copy(alpha = 0.05f),
                    shape = RoundedCornerShape(28.dp),
                ),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 2.dp)
                    .width(40.dp)
                    .height(1.5.dp)
                    .background(Color.White.copy(alpha = if (darkTheme) 0.34f else 0.80f), RoundedCornerShape(1.dp)),
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            visibleItems.forEach { item ->
                val selected = item.id == selectedItemId
                DockItem(
                    item = item,
                    selected = selected,
                    darkTheme = darkTheme,
                    onClick = {
                        if (!selected) {
                            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                            onItemSelected(item.id)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun DockItem(
    item: NavItem,
    selected: Boolean,
    darkTheme: Boolean,
    onClick: () -> Unit,
) {
    val itemWidth by animateDpAsState(
        targetValue = if (selected) 72.dp else 56.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.7f),
        label = "dock-item-width",
    )
    val iconOffsetY by animateDpAsState(
        targetValue = if (selected) (-6).dp else 0.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.7f),
        label = "dock-icon-y",
    )
    val textOffsetY by animateDpAsState(
        targetValue = if (selected) 10.dp else 15.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.7f),
        label = "dock-text-y",
    )
    val labelAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(durationMillis = 200),
        label = "dock-item-label-alpha",
    )
    val accentColor = if (darkTheme) Color.White else Color(0xFF007AFF)
    val idleColor = if (darkTheme) Color(0xFF8E8E93) else Color.Gray

    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(56.dp)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = item.icon,
            contentDescription = item.label,
            tint = if (selected) accentColor else idleColor,
            modifier = Modifier
                .size(24.dp)
                .offset(y = iconOffsetY),
        )

        Text(
            text = item.label,
            color = accentColor,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .offset(y = textOffsetY)
                .alpha(labelAlpha),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFEBEDF0)
@Composable
private fun BottomDockPreview() {
    ShellShotTheme {
        BottomDock(
            items = listOf(
                NavItem(id = "home", label = "首页", icon = AppIconId.Home),
                NavItem(id = "templates", label = "模板", icon = AppIconId.Template),
                NavItem(id = "settings", label = "设置", icon = AppIconId.Settings),
                NavItem(id = "logs", label = "日志", icon = AppIconId.Terminal),
            ),
            selectedItemId = "home",
            onItemSelected = {},
        )
    }
}
