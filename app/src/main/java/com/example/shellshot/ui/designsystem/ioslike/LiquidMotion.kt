package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer

fun Modifier.liquidPressEffect(
    interactionSource: MutableInteractionSource,
    enabled: Boolean = true,
    pressedScale: Float = 0.98f,
    pressedAlpha: Float = 0.9f,
): Modifier = composed {
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (enabled && pressed) pressedScale else 1f,
        animationSpec = IOSMotion.PressSpring,
        label = "ios-liquid-press-scale",
    )
    val alpha by animateFloatAsState(
        targetValue = if (enabled && pressed) pressedAlpha else 1f,
        animationSpec = IOSMotion.PressSpring,
        label = "ios-liquid-press-alpha",
    )
    graphicsLayer {
        scaleX = scale
        scaleY = scale
        this.alpha = alpha
    }
}
