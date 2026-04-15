package com.example.shellshot.ui.components

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.theme.ShellShotTheme
import com.kyant.backdrop.Backdrop
import com.kyant.backdrop.drawBackdrop
import com.kyant.backdrop.effects.blur
import com.kyant.backdrop.effects.lens
import com.kyant.backdrop.effects.vibrancy
import com.kyant.backdrop.highlight.Highlight
import com.kyant.backdrop.shadow.InnerShadow
import com.kyant.backdrop.shadow.Shadow
import com.kyant.shapes.Capsule

private val DockEase = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)

@Composable
fun BottomDock(
    items: List<NavItem>,
    selectedItemId: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
    liquidBackdrop: Backdrop? = null,
    hidden: Boolean = false,
) {
    val visibleItems = items.filter { it.visible }
    if (visibleItems.isEmpty()) return

    val haptic = LocalHapticFeedback.current
    val shape = RoundedCornerShape(32.dp)
    val tabsCount = visibleItems.size
    val dockWidth = (tabsCount * 68 + 8).dp
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
            .width(dockWidth)
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
            .then(
                if (liquidBackdrop != null) {
                    Modifier.drawBackdrop(
                        backdrop = liquidBackdrop,
                        shape = { Capsule() },
                        effects = {
                            vibrancy()
                            blur(8.dp.toPx())
                            lens(18.dp.toPx(), 22.dp.toPx())
                        },
                        highlight = { Highlight.Default.copy(alpha = if (darkTheme) 0.32f else 0.50f) },
                        shadow = { Shadow(radius = 14.dp, alpha = if (darkTheme) 0.28f else 0.14f) },
                        innerShadow = { InnerShadow(radius = 6.dp, alpha = if (darkTheme) 0.20f else 0.10f) },
                        onDrawSurface = {
                            drawRect(if (darkTheme) Color(0xFF121212).copy(alpha = 0.40f) else Color(0xFFFAFAFA).copy(alpha = 0.40f))
                        },
                    )
                } else {
                    Modifier.background(if (darkTheme) Color.Black.copy(alpha = 0.56f) else Color.White.copy(alpha = 0.86f))
                },
            )
            .border(
                width = 1.dp,
                color = if (darkTheme) Color.White.copy(alpha = 0.12f) else Color.White.copy(alpha = 0.50f),
                shape = shape,
            )
            .height(64.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            visibleItems.forEach { item ->
                DockTab(
                    item = item,
                    selected = item.id == selectedItemId,
                    darkTheme = darkTheme,
                    onClick = {
                        haptic.performHapticFeedback(HapticFeedbackType.KeyboardTap)
                        if (item.id != selectedItemId) {
                            onItemSelected(item.id)
                        }
                    },
                )
            }
        }
    }
}

@Composable
private fun RowScope.DockTab(
    item: NavItem,
    selected: Boolean,
    darkTheme: Boolean,
    onClick: () -> Unit,
) {
    val iconOffsetY by animateDpAsState(
        targetValue = if (selected) (-6).dp else 0.dp,
        animationSpec = spring(stiffness = 330f, dampingRatio = 0.84f),
        label = "dock-icon-y",
    )
    val textOffsetY by animateDpAsState(
        targetValue = if (selected) 10.dp else 15.dp,
        animationSpec = spring(stiffness = 330f, dampingRatio = 0.84f),
        label = "dock-text-y",
    )
    val labelAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(durationMillis = 260, easing = DockEase),
        label = "dock-item-label-alpha",
    )
    val accentColor = if (darkTheme) Color.White else Color(0xFF007AFF)
    val idleColor = if (darkTheme) Color(0xFF8E8E93) else Color.Gray

    Column(
        modifier = Modifier
            .clip(Capsule())
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                role = Role.Tab,
                onClick = onClick,
            )
            .fillMaxHeight()
            .weight(1f),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
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
