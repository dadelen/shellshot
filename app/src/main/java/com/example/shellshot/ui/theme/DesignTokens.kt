package com.example.shellshot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShellShotColors(
    val background: Color,
    val surface: Color,
    val surfaceAlt: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textMuted: Color,
    val accentBlue: Color,
    val accentGreen: Color,
    val accentAmber: Color,
    val accentPurple: Color,
    val accentRed: Color,
    val accentPink: Color,
    val accentCyan: Color,
    val glass: Color,
    val glassStroke: Color,
    val separator: Color,
    val subtleFill: Color,
)

@Immutable
data class ShellShotSpacing(
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
    val xxxl: Dp = 32.dp,
    val pageHorizontal: Dp = 24.dp,
)

@Immutable
data class ShellShotRadius(
    val largeCard: Dp = 40.dp,
    val mediumCard: Dp = 32.dp,
    val pill: Dp = 999.dp,
    val button: Dp = 16.dp,
    val dock: Dp = 34.dp,
)

@Immutable
data class ShellShotElevation(
    val subtle: Dp = 4.dp,
    val card: Dp = 14.dp,
    val floating: Dp = 24.dp,
)

@Immutable
data class MotionSpecTokens(
    val pageDurationMs: Int = 400,
    val quickDurationMs: Int = 150,
)

@Immutable
data class ShellShotTokens(
    val colors: ShellShotColors,
    val spacing: ShellShotSpacing = ShellShotSpacing(),
    val radius: ShellShotRadius = ShellShotRadius(),
    val elevation: ShellShotElevation = ShellShotElevation(),
    val motion: MotionSpecTokens = MotionSpecTokens(),
)

val LightShellShotTokens = ShellShotTokens(
    colors = ShellShotColors(
        background = ShellColors.BackgroundLight,
        surface = ShellColors.SurfaceLight,
        surfaceAlt = ShellColors.SurfaceAltLight,
        textPrimary = ShellColors.TextPrimaryLight,
        textSecondary = ShellColors.TextSecondaryLight,
        textMuted = ShellColors.TextMutedLight,
        accentBlue = ShellColors.AccentBlue,
        accentGreen = ShellColors.AccentGreen,
        accentAmber = ShellColors.AccentAmber,
        accentPurple = ShellColors.AccentPurple,
        accentRed = ShellColors.AccentRed,
        accentPink = ShellColors.AccentPink,
        accentCyan = ShellColors.AccentCyan,
        glass = ShellColors.GlassLight,
        glassStroke = ShellColors.GlassStrokeLight,
        separator = ShellColors.separator(false),
        subtleFill = ShellColors.subtleFill(false),
    ),
)

val DarkShellShotTokens = ShellShotTokens(
    colors = ShellShotColors(
        background = ShellColors.BackgroundDark,
        surface = ShellColors.SurfaceDark,
        surfaceAlt = ShellColors.SurfaceAltDark,
        textPrimary = ShellColors.TextPrimaryDark,
        textSecondary = ShellColors.TextSecondaryDark,
        textMuted = ShellColors.TextMutedDark,
        accentBlue = ShellColors.AccentBlue,
        accentGreen = ShellColors.AccentGreen,
        accentAmber = ShellColors.AccentAmber,
        accentPurple = ShellColors.AccentPurple,
        accentRed = ShellColors.AccentRed,
        accentPink = ShellColors.AccentPink,
        accentCyan = ShellColors.AccentCyan,
        glass = ShellColors.GlassDark,
        glassStroke = ShellColors.GlassStrokeDark,
        separator = ShellColors.separator(true),
        subtleFill = ShellColors.subtleFill(true),
    ),
)

val LocalShellShotTokens = staticCompositionLocalOf { LightShellShotTokens }

val MaterialTheme.shellShotTokens: ShellShotTokens
    @Composable
    get() = LocalShellShotTokens.current
