package com.example.shellshot.ui.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.noRippleClick(
    onClick: () -> Unit,
): Modifier = noRippleClick(enabled = true, onClick = onClick)

fun Modifier.noRippleClick(
    enabled: Boolean = true,
    onClick: () -> Unit,
): Modifier = clickable(
    enabled = enabled,
    indication = null,
    interactionSource = MutableInteractionSource(),
    onClick = onClick,
)

@Composable
fun Modifier.pressScaleEffect(
    pressedScale: Float = 0.97f,
    pressedAlpha: Float = 0.94f,
): Modifier {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) pressedScale else 1f,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "press-scale",
    )
    val alpha by animateFloatAsState(
        targetValue = if (pressed) pressedAlpha else 1f,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "press-alpha",
    )
    return this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .alpha(alpha)
        .clickable(
            interactionSource = interactionSource,
            indication = null,
            onClick = {},
        )
}
