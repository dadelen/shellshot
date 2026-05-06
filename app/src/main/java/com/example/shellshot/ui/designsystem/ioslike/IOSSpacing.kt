package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class IOSSpacingScale(
    val pageHorizontal: Dp = 20.dp,
    val cardPadding: Dp = 16.dp,
    val groupGap: Dp = 16.dp,
    val rowVertical: Dp = 14.dp,
    val bottomBarHorizontal: Dp = 18.dp,
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
)

object IOSSpacing {
    val Default = IOSSpacingScale()
}
