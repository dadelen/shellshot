package com.example.shellshot.ui.theme

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Dp

object MotionConstants {
    const val QuickMs = 150
    const val SettleMs = 260
    const val PageMs = 360
    const val PageExitMs = 280
    const val OverlayMs = 300

    val iosEaseOut: Easing = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)
    val iosEaseInOut: Easing = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)

    val iosSpring = spring<Float>(
        dampingRatio = 0.84f,
        stiffness = Spring.StiffnessMediumLow,
    )

    val iosSpringDp = spring<Dp>(
        dampingRatio = 0.84f,
        stiffness = Spring.StiffnessMediumLow,
    )

    val iosSpringSize = spring<IntSize>(
        dampingRatio = 0.86f,
        stiffness = Spring.StiffnessMediumLow,
    )

    val iosSpringOffset = spring<IntOffset>(
        dampingRatio = 0.86f,
        stiffness = Spring.StiffnessMediumLow,
    )

    val iosSpringRect = spring<Rect>(
        dampingRatio = 0.86f,
        stiffness = Spring.StiffnessMediumLow,
    )

    val pressSpring = spring<Float>(
        dampingRatio = 0.72f,
        stiffness = Spring.StiffnessMedium,
    )

    val dockSpring = spring<Float>(
        dampingRatio = 0.78f,
        stiffness = Spring.StiffnessMedium,
    )

    val dockSpringDp = spring<Dp>(
        dampingRatio = 0.78f,
        stiffness = Spring.StiffnessMedium,
    )

    val sheetSpring = spring<Float>(
        dampingRatio = 0.9f,
        stiffness = Spring.StiffnessLow,
    )

    val cardExpandSpring = spring<Float>(
        dampingRatio = 0.8f,
        stiffness = Spring.StiffnessMedium
    )

    val sheetSpringDp = spring<Dp>(
        dampingRatio = 0.9f,
        stiffness = Spring.StiffnessLow,
    )

    fun quickTween() = tween<Float>(durationMillis = QuickMs, easing = iosEaseOut)

    fun settleTween() = tween<Float>(durationMillis = SettleMs, easing = iosEaseOut)

    fun overlayTween() = tween<Float>(durationMillis = OverlayMs, easing = iosEaseInOut)
}
