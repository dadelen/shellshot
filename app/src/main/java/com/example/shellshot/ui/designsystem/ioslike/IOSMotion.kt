package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween

object IOSMotion {
    const val QuickMs = 180
    const val SettleMs = 240
    const val EmphasizedMs = 260

    val EaseOut = CubicBezierEasing(0.16f, 1f, 0.3f, 1f)
    val EaseInOut = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)

    val PressSpring = spring<Float>(
        dampingRatio = 0.70f,
        stiffness = 420f,
    )

    fun quickTween() = tween<Float>(durationMillis = QuickMs, easing = EaseOut)

    fun settleTween() = tween<Float>(durationMillis = SettleMs, easing = EaseOut)

    fun visibilitySpring() = spring<Float>(
        dampingRatio = 0.76f,
        stiffness = Spring.StiffnessMedium,
    )
}
