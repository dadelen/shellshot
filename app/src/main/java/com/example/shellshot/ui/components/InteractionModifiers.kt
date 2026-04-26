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
import androidx.compose.ui.composed
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalView
import androidx.core.view.HapticFeedbackConstantsCompat
import androidx.core.view.ViewCompat

fun Modifier.noRippleClick(
    onClick: () -> Unit,
): Modifier = noRippleClick(enabled = true, onClick = onClick)

fun Modifier.noRippleClick(
    enabled: Boolean = true,
    pressedScale: Float = 0.97f,
    pressedAlpha: Float = 0.94f,
    onClick: () -> Unit,
): Modifier = composed {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed) pressedScale else 1f,
        animationSpec = spring(stiffness = 600f, dampingRatio = 0.7f),
        label = "press-scale",
    )
    val alpha by animateFloatAsState(
        targetValue = if (pressed) pressedAlpha else 1f,
        animationSpec = spring(stiffness = 600f, dampingRatio = 0.7f),
        label = "press-alpha",
    )
    
    this
        .graphicsLayer {
            scaleX = scale
            scaleY = scale
        }
        .alpha(alpha)
        .clickable(
            enabled = enabled,
            indication = null,
            interactionSource = interactionSource,
            onClick = onClick,
        )
}

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

class ShellShotHaptics internal constructor(
    private val perform: (Int) -> Unit,
) {
    fun dockTick() = perform(HapticFeedbackConstantsCompat.CLOCK_TICK)

    fun toggle(enabled: Boolean) = perform(
        if (enabled) {
            HapticFeedbackConstantsCompat.TOGGLE_ON
        } else {
            HapticFeedbackConstantsCompat.TOGGLE_OFF
        }
    )

    fun selection() = perform(HapticFeedbackConstantsCompat.CLOCK_TICK)
}

@Composable
fun rememberShellShotHaptics(): ShellShotHaptics {
    val view = LocalView.current
    return remember(view) {
        ShellShotHaptics { constant ->
            ViewCompat.performHapticFeedback(view, constant)
        }
    }
}
