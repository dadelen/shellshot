package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

@Immutable
data class IOSColorScheme(
    val groupedBackground: Color,
    val card: Color,
    val cardElevated: Color,
    val separator: Color,
    val primaryText: Color,
    val secondaryText: Color,
    val tertiaryText: Color,
    val glassFill: Color,
    val glassStroke: Color,
)

object IOSColors {
    val Blue = Color(0xFF007AFF)
    val Green = Color(0xFF34C759)
    val Red = Color(0xFFFF3B30)
    val Orange = Color(0xFFFF9500)

    val LightGroupedBackground = Color(0xFFF2F2F7)
    val LightCard = Color(0xFFFFFFFF)
    val DarkBackground = Color(0xFF000000)
    val DarkCard = Color(0xFF1C1C1E)
    val LightSeparator = Color(0x1F3C3C43)
    val DarkSeparator = Color(0x33FFFFFF)

    fun scheme(isDark: Boolean): IOSColorScheme = if (isDark) {
        IOSColorScheme(
            groupedBackground = DarkBackground,
            card = DarkCard,
            cardElevated = Color(0xFF2C2C2E),
            separator = DarkSeparator,
            primaryText = Color.White,
            secondaryText = Color(0xFFEBEBF5).copy(alpha = 0.72f),
            tertiaryText = Color(0xFFEBEBF5).copy(alpha = 0.48f),
            glassFill = Color.White.copy(alpha = 0.10f),
            glassStroke = Color.White.copy(alpha = 0.22f),
        )
    } else {
        IOSColorScheme(
            groupedBackground = LightGroupedBackground,
            card = LightCard,
            cardElevated = Color(0xFFFBFBFD),
            separator = LightSeparator,
            primaryText = Color(0xFF1C1C1E),
            secondaryText = Color(0x993C3C43),
            tertiaryText = Color(0x663C3C43),
            glassFill = Color.White.copy(alpha = 0.72f),
            glassStroke = Color.White.copy(alpha = 0.82f),
        )
    }
}
