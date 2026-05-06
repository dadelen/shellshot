package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.runtime.Immutable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class IOSTypeScale(
    val largeTitle: TextStyle = TextStyle(
        fontSize = 34.sp,
        lineHeight = 38.sp,
        fontWeight = FontWeight.Bold,
    ),
    val screenTitle: TextStyle = TextStyle(
        fontSize = 28.sp,
        lineHeight = 34.sp,
        fontWeight = FontWeight.Bold,
    ),
    val cardTitle: TextStyle = TextStyle(
        fontSize = 21.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    val body: TextStyle = TextStyle(
        fontSize = 17.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.Normal,
    ),
    val secondary: TextStyle = TextStyle(
        fontSize = 14.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
    ),
    val button: TextStyle = TextStyle(
        fontSize = 17.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
    ),
)

object IOSTypography {
    val Default = IOSTypeScale()
}
