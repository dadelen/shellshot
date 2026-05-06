package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.shellshot.ui.theme.MotionConstants

fun iosPageFlyInEnter(direction: Int = 1): EnterTransition =
    fadeIn(animationSpec = tween(MotionConstants.PageMs, easing = MotionConstants.iosEaseOut)) +
        slideInHorizontally(
            animationSpec = tween(MotionConstants.PageMs, easing = MotionConstants.iosEaseOut),
            initialOffsetX = { direction * (it / 5) },
        ) +
        scaleIn(
            initialScale = 0.988f,
            animationSpec = tween(MotionConstants.PageMs, easing = MotionConstants.iosEaseOut),
        )

fun iosPageFlyOutExit(direction: Int = 1): ExitTransition =
    fadeOut(animationSpec = tween(MotionConstants.PageExitMs, easing = MotionConstants.iosEaseInOut)) +
        slideOutHorizontally(
            animationSpec = tween(MotionConstants.PageExitMs, easing = MotionConstants.iosEaseInOut),
            targetOffsetX = { -direction * (it / 10) },
        ) +
        scaleOut(
            targetScale = 0.996f,
            animationSpec = tween(MotionConstants.PageExitMs, easing = MotionConstants.iosEaseInOut),
        )

fun iosSheetFlyInEnter(): EnterTransition =
    fadeIn(animationSpec = tween(MotionConstants.PageMs, easing = MotionConstants.iosEaseOut)) +
        slideInVertically(
            animationSpec = tween(MotionConstants.PageMs, easing = MotionConstants.iosEaseOut),
            initialOffsetY = { it / 5 },
        ) +
        scaleIn(
            initialScale = 0.988f,
            animationSpec = tween(MotionConstants.PageMs, easing = MotionConstants.iosEaseOut),
        )

fun iosSheetFlyOutExit(): ExitTransition =
    fadeOut(animationSpec = tween(MotionConstants.QuickMs, easing = MotionConstants.iosEaseInOut)) +
        slideOutVertically(
            animationSpec = tween(MotionConstants.SettleMs, easing = MotionConstants.iosEaseInOut),
            targetOffsetY = { it / 6 },
        ) +
        scaleOut(
            targetScale = 0.992f,
            animationSpec = tween(MotionConstants.SettleMs, easing = MotionConstants.iosEaseInOut),
        )

@Composable
fun IOSFlyInContainer(
    modifier: Modifier = Modifier,
    enter: EnterTransition = iosPageFlyInEnter(),
    exit: ExitTransition = iosPageFlyOutExit(),
    content: @Composable () -> Unit,
) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = enter,
        exit = exit,
    ) {
        content()
    }
}
