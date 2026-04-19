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
import androidx.compose.foundation.interaction.collectIsPressedAsState
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
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.example.shellshot.ui.theme.AppTypography
import com.example.shellshot.ui.theme.ShellColors
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
    val selectedIndex = visibleItems.indexOfFirst { it.id == selectedItemId }.coerceAtLeast(0)
    val sliderOffsetX by animateDpAsState(
        targetValue = (selectedIndex * 68).dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.72f),
        label = "dock-selected-slider-x",
    )
    val dockOffsetY by animateDpAsState(
        targetValue = if (hidden) 150.dp else 0.dp,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.8f),
        label = "dock-hidden-offset",
    )
    val dockScale by animateFloatAsState(
        targetValue = if (hidden) 0.8f else 1f,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.8f),
        label = "dock-hidden-scale",
    )
    val dockAlpha by animateFloatAsState(
        targetValue = if (hidden) 0f else 1f,
        animationSpec = tween(durationMillis = 200),
        label = "dock-hidden-alpha",
    )
    
    val backgroundColor = if (darkTheme) Color(0xFF141415).copy(alpha = 0.85f) else Color.White.copy(alpha = 0.88f)

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
            .bentoCard(
                isDark = darkTheme,
                cornerRadius = 32.dp,
                isElevated = true,
                backdrop = liquidBackdrop
            )
            .height(64.dp)
            .padding(4.dp),
        contentAlignment = Alignment.Center,
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = sliderOffsetX)
                .width(68.dp)
                .fillMaxHeight()
                .padding(6.dp)
                .clip(Capsule())
                .background(ShellColors.badgeBg(darkTheme))
        )
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
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    
    val pressScale by animateFloatAsState(
        targetValue = if (pressed) 0.88f else 1f,
        animationSpec = spring(stiffness = 600f, dampingRatio = 0.65f),
        label = "dock-tab-press-scale"
    )

    val iconOffsetY by animateDpAsState(
        targetValue = if (selected) (-6).dp else 0.dp,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.72f),
        label = "dock-icon-y",
    )
    val textOffsetY by animateDpAsState(
        targetValue = if (selected) 10.dp else 16.dp,
        animationSpec = spring(stiffness = 400f, dampingRatio = 0.72f),
        label = "dock-text-y",
    )
    val labelAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(durationMillis = 260, easing = DockEase),
        label = "dock-item-label-alpha",
    )
    val accentColor = ShellColors.textPrimary(darkTheme)
    val idleColor = ShellColors.textTertiary(darkTheme)

    Column(
        modifier = Modifier
            .clip(Capsule())
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                role = Role.Tab,
                onClick = onClick,
            )
            .fillMaxHeight()
            .weight(1f)
            .graphicsLayer { 
                scaleX = pressScale
                scaleY = pressScale 
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = Modifier
                .height(56.dp)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            DockAnimatedIcon(
                item = item,
                selected = selected,
                tint = if (selected) accentColor else idleColor,
                modifier = Modifier
                    .size(24.dp)
                    .offset(y = iconOffsetY),
            )

            Text(
                text = item.label,
                color = accentColor,
                style = AppTypography.labelMedium,
                modifier = Modifier
                    .offset(y = textOffsetY)
                    .alpha(labelAlpha),
            )
        }
    }
}

@Composable
private fun DockAnimatedIcon(
    item: NavItem,
    selected: Boolean,
    tint: Color,
    modifier: Modifier = Modifier,
) {
    val asset = when (item.id) {
        "home" -> "lottie/home.json"
        "templates" -> "lottie/template.json"
        "settings" -> "lottie/settings.json"
        "logs" -> "lottie/logs.json"
        else -> null
    }
    if (asset == null) {
        AppIcon(icon = item.icon, contentDescription = item.label, tint = tint, modifier = modifier)
        return
    }

    val composition by rememberLottieComposition(LottieCompositionSpec.Asset(asset))
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = selected,
        iterations = 1,
        restartOnPlay = true,
    )
    val dynamicProperties = rememberLottieDynamicProperties(
        rememberLottieDynamicProperty(
            property = LottieProperty.COLOR,
            value = tint.toArgb(),
            keyPath = arrayOf("**"),
        ),
        rememberLottieDynamicProperty(
            property = LottieProperty.STROKE_COLOR,
            value = tint.toArgb(),
            keyPath = arrayOf("**"),
        ),
    )

    if (composition != null) {
        LottieAnimation(
            composition = composition,
            progress = { if (selected) progress else 0f },
            modifier = modifier,
            dynamicProperties = dynamicProperties,
        )
    } else {
        AppIcon(icon = item.icon, contentDescription = item.label, tint = tint, modifier = modifier)
    }
}
