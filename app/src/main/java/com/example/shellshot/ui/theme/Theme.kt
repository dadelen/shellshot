package com.example.shellshot.ui.theme

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.animation.core.tween
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

private val LightColors = lightColorScheme(
    primary = ShellColors.AccentBlue,
    onPrimary = Color.White,
    secondary = ShellColors.AccentBlue,
    onSecondary = Color.White,
    tertiary = ShellColors.AccentPurple,
    background = ShellColors.BackgroundLight,
    surface = ShellColors.SurfaceLight,
    surfaceVariant = ShellColors.SurfaceAltLight,
    primaryContainer = ShellColors.subtleFill(false),
    secondaryContainer = ShellColors.subtleFill(false),
    error = ShellColors.AccentRed,
    onBackground = ShellColors.TextPrimaryLight,
    onSurface = ShellColors.TextPrimaryLight,
    onSurfaceVariant = ShellColors.TextMutedLight,
)

private val DarkColors = darkColorScheme(
    primary = ShellColors.AccentBlue,
    onPrimary = Color.White,
    secondary = ShellColors.AccentBlue,
    onSecondary = Color.White,
    tertiary = ShellColors.AccentPurple,
    background = ShellColors.BackgroundDark,
    surface = ShellColors.SurfaceDark,
    surfaceVariant = ShellColors.SurfaceAltDark,
    primaryContainer = ShellColors.subtleFill(true),
    secondaryContainer = ShellColors.subtleFill(true),
    error = ShellColors.AccentRed,
    onBackground = ShellColors.TextPrimaryDark,
    onSurface = ShellColors.TextPrimaryDark,
    onSurfaceVariant = ShellColors.TextMutedDark,
)

private val AppShapes = Shapes(
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(24.dp),
)

@Composable
fun ShellShotTheme(
    darkTheme: Boolean,
    content: @Composable () -> Unit,
) {
    val tokens = if (darkTheme) DarkShellShotTokens else LightShellShotTokens
    val targetColors = if (darkTheme) DarkColors else LightColors
    val colorScheme = targetColors.animate()

    CompositionLocalProvider(LocalShellShotTokens provides tokens) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            shapes = AppShapes,
            content = content,
        )
    }
}

@Composable
private fun ColorScheme.animate(): ColorScheme {
    val primary by animateColorAsState(primary, tween(600), label = "theme-primary")
    val onPrimary by animateColorAsState(onPrimary, tween(600), label = "theme-on-primary")
    val secondary by animateColorAsState(secondary, tween(600), label = "theme-secondary")
    val background by animateColorAsState(background, tween(600), label = "theme-background")
    val surface by animateColorAsState(surface, tween(600), label = "theme-surface")
    val surfaceVariant by animateColorAsState(surfaceVariant, tween(600), label = "theme-surface-variant")
    val onBackground by animateColorAsState(onBackground, tween(600), label = "theme-on-background")
    val onSurface by animateColorAsState(onSurface, tween(600), label = "theme-on-surface")
    val onSurfaceVariant by animateColorAsState(onSurfaceVariant, tween(600), label = "theme-on-surface-variant")
    val primaryContainer by animateColorAsState(primaryContainer, tween(600), label = "theme-primary-container")
    val secondaryContainer by animateColorAsState(secondaryContainer, tween(600), label = "theme-secondary-container")
    val error by animateColorAsState(error, tween(600), label = "theme-error")

    return copy(
        primary = primary,
        onPrimary = onPrimary,
        secondary = secondary,
        background = background,
        surface = surface,
        surfaceVariant = surfaceVariant,
        onBackground = onBackground,
        onSurface = onSurface,
        onSurfaceVariant = onSurfaceVariant,
        primaryContainer = primaryContainer,
        secondaryContainer = secondaryContainer,
        error = error,
    )
}
