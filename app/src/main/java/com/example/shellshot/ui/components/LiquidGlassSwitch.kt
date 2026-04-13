package com.example.shellshot.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun LiquidGlassSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    width: Dp = 52.dp,
    height: Dp = 32.dp,
) {
    var isAnimating by remember { mutableStateOf(false) }
    val darkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val hapticFeedback = LocalHapticFeedback.current

    LaunchedEffect(isAnimating) {
        if (isAnimating) {
            delay(150)
            isAnimating = false
        }
    }

    val springSpec = spring<Dp>(stiffness = 500f, dampingRatio = 0.6f)

    val thumbOffset by animateDpAsState(
        targetValue = if (checked) {
            if (isAnimating) 0.dp else 20.dp
        } else {
            if (isAnimating) 8.dp else 0.dp
        },
        animationSpec = springSpec,
        label = "liquid-switch-offset",
    )
    val thumbWidth by animateDpAsState(
        targetValue = if (isAnimating) 40.dp else 28.dp,
        animationSpec = springSpec,
        label = "liquid-switch-width",
    )
    val thumbHeight by animateDpAsState(
        targetValue = if (isAnimating) 22.dp else 28.dp,
        animationSpec = springSpec,
        label = "liquid-switch-height",
    )
    val backgroundColor by animateColorAsState(
        targetValue = if (checked) {
            Color(0xFF34C759)
        } else if (darkTheme) {
            Color(0xFF3F3F46)
        } else {
            Color(0xFFE5E5EA)
        },
        label = "liquid-switch-color",
    )
    val trackBorderColor by animateColorAsState(
        targetValue = if (checked) {
            Color.Transparent
        } else if (darkTheme) {
            Color.White.copy(alpha = 0.16f)
        } else {
            Color(0xFFD1D5DB).copy(alpha = 0.48f)
        },
        label = "liquid-switch-track-border",
    )
    val solidThumbAlpha by animateFloatAsState(
        targetValue = if (isAnimating) 0f else 1f,
        label = "liquid-switch-solid-alpha",
    )
    val glassThumbAlpha by animateFloatAsState(
        targetValue = if (isAnimating) 1f else 0f,
        label = "liquid-switch-glass-alpha",
    )

    Box(
        modifier = modifier
            .alpha(if (enabled) 1f else 0.5f)
            .size(width = width, height = height)
            .clip(RoundedCornerShape(16.dp))
            .background(backgroundColor)
            .border(1.dp, trackBorderColor, RoundedCornerShape(16.dp))
            .clickable(
                enabled = enabled,
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
            ) {
                hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
                isAnimating = true
                onCheckedChange(!checked)
            }
            .padding(2.dp),
        contentAlignment = Alignment.CenterStart,
    ) {
        Box(
            modifier = Modifier
                .offset(x = thumbOffset)
                .size(width = thumbWidth, height = thumbHeight)
                .shadow(2.dp, RoundedCornerShape(percent = 50))
                .clip(RoundedCornerShape(percent = 50)),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(solidThumbAlpha)
                    .background(Color.White, RoundedCornerShape(percent = 50)),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .alpha(glassThumbAlpha)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.82f),
                                Color.White.copy(alpha = 0.34f),
                                Color.White.copy(alpha = 0.08f),
                            ),
                        ),
                        RoundedCornerShape(percent = 50),
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 2.dp)
                        .background(
                            Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.28f),
                                    Color.Transparent,
                                ),
                            ),
                            RoundedCornerShape(percent = 50),
                        ),
                )
            }
        }
    }
}

@Composable
fun StatefulLiquidGlassSwitch(
    initialChecked: Boolean,
    onChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    width: Dp = 52.dp,
    height: Dp = 32.dp,
) {
    var checked by rememberSaveable { mutableStateOf(initialChecked) }

    LiquidGlassSwitch(
        checked = checked,
        onCheckedChange = {
            checked = it
            onChange(it)
        },
        modifier = modifier,
        enabled = enabled,
        width = width,
        height = height,
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F2F7)
@Composable
private fun LiquidGlassSwitchPreview() {
    Box(modifier = Modifier.padding(24.dp)) {
        LiquidGlassSwitch(
            checked = true,
            onCheckedChange = {},
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F2F7)
@Composable
private fun LiquidGlassSwitchDemoPreview() {
    Box(modifier = Modifier.padding(24.dp)) {
        Text(" ")
        StatefulLiquidGlassSwitch(
            initialChecked = false,
            onChange = {},
        )
    }
}
