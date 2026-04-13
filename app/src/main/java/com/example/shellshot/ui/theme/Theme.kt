package com.example.shellshot.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = AccentBlue,
    onPrimary = TextPrimary,
    secondary = AccentGreen,
    onSecondary = TextPrimary,
    tertiary = AccentAmber,
    background = BackgroundPrimary,
    surface = CardBackgroundStrong,
    surfaceVariant = CardBackground,
    primaryContainer = AccentBlueSoft,
    secondaryContainer = AccentGreenSoft,
    error = AccentRed,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
    onSurfaceVariant = TextSecondary,
)

private val DarkColors = darkColorScheme(
    primary = DarkAccentBlue,
    onPrimary = DarkTextPrimary,
    secondary = DarkAccentGreen,
    onSecondary = DarkTextPrimary,
    tertiary = DarkAccentAmber,
    background = DarkBackgroundPrimary,
    surface = DarkCardBackgroundStrong,
    surfaceVariant = DarkCardBackground,
    primaryContainer = DarkAccentBlue.copy(alpha = 0.14f),
    secondaryContainer = DarkAccentGreen.copy(alpha = 0.14f),
    error = DarkAccentRed,
    onBackground = DarkTextPrimary,
    onSurface = DarkTextPrimary,
    onSurfaceVariant = DarkTextSecondary,
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(20.dp),
    medium = RoundedCornerShape(22.dp),
    large = RoundedCornerShape(28.dp),
)

@Composable
fun ShellShotTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val tokens = if (darkTheme) DarkShellShotTokens else LightShellShotTokens

    CompositionLocalProvider(LocalShellShotTokens provides tokens) {
        MaterialTheme(
            colorScheme = if (darkTheme) DarkColors else LightColors,
            typography = AppTypography,
            shapes = AppShapes,
            content = content,
        )
    }
}
