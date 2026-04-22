package com.example.shellshot.ui.theme

import androidx.compose.animation.core.spring
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.Dp

object MotionConstants {
    val iosSpring = spring<Float>(
        dampingRatio = 0.8f,
        stiffness = 400f,
    )

    val iosSpringDp = spring<Dp>(
        dampingRatio = 0.8f,
        stiffness = 400f,
    )

    val iosSpringSize = spring<IntSize>(
        dampingRatio = 0.8f,
        stiffness = 400f,
    )

    val iosSpringOffset = spring<IntOffset>(
        dampingRatio = 0.8f,
        stiffness = 400f,
    )

    val iosSpringRect = spring<Rect>(
        dampingRatio = 0.8f,
        stiffness = 400f,
    )
}
