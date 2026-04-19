package com.example.shellshot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class ShellShotColorTokens(
    val backgroundPrimary: Color,
    val backgroundSecondary: Color,
    val cardBackground: Color,
    val cardBackgroundStrong: Color,
    val cardStroke: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val textTertiary: Color,
    val accentBlue: Color,
    val accentBlueSoft: Color,
    val accentGreen: Color,
    val accentGreenSoft: Color,
    val accentAmber: Color,
    val accentAmberSoft: Color,
    val accentRed: Color,
    val accentRedSoft: Color,
    val dividerColor: Color,
    val navBarBackground: Color,
    val selectedItemBackground: Color,
)

@Immutable
data class ShellShotRadiusTokens(
    val pageCard: Dp = 40.dp,
    val secondaryCard: Dp = 24.dp,
    val button: Dp = 20.dp,
    val pill: Dp = 16.dp,
    val iconPlate: Dp = 12.dp,
    val navBar: Dp = 30.dp,
    val navBarSelected: Dp = 22.dp,
)

@Immutable
data class ShellShotShadowStyle(
    val elevation: Dp,
    val ambientColor: Color,
    val spotColor: Color,
)

@Immutable
data class ShellShotShadowTokens(
    val soft: ShellShotShadowStyle,
    val card: ShellShotShadowStyle,
    val floating: ShellShotShadowStyle,
)

@Immutable
data class ShellShotSpacingTokens(
    val xs: Dp = 4.dp,
    val sm: Dp = 8.dp,
    val md: Dp = 12.dp,
    val lg: Dp = 16.dp,
    val xl: Dp = 20.dp,
    val xxl: Dp = 24.dp,
    val xxxl: Dp = 32.dp,
    val hero: Dp = 40.dp,
)

@Immutable
data class ShellShotTokens(
    val colors: ShellShotColorTokens,
    val radii: ShellShotRadiusTokens = ShellShotRadiusTokens(),
    val shadows: ShellShotShadowTokens,
    val spacing: ShellShotSpacingTokens = ShellShotSpacingTokens(),
)

val LightShellShotTokens = ShellShotTokens(
    colors = ShellShotColorTokens(
        backgroundPrimary = ShellColors.background(false),
        backgroundSecondary = Color(0xFFDDE1E7),
        cardBackground = ShellColors.baseCard(false),
        cardBackgroundStrong = ShellColors.elevatedCard(false),
        cardStroke = ShellColors.border(false),
        textPrimary = ShellColors.textPrimary(false),
        textSecondary = ShellColors.textSecondary(false),
        textTertiary = ShellColors.textTertiary(false),
        accentBlue = ShellColors.actionBg(false),
        accentBlueSoft = ShellColors.badgeBg(false),
        accentGreen = ShellColors.successGreen,
        accentGreenSoft = ShellColors.successGreen.copy(alpha = 0.16f),
        accentAmber = ShellColors.criticalOrange,
        accentAmberSoft = ShellColors.criticalOrange.copy(alpha = 0.18f),
        accentRed = ShellColors.criticalRed,
        accentRedSoft = ShellColors.criticalRed.copy(alpha = 0.18f),
        dividerColor = ShellColors.border(false),
        navBarBackground = ShellColors.baseCard(false),
        selectedItemBackground = ShellColors.badgeBg(false),
    ),
    shadows = ShellShotShadowTokens(
        soft = ShellShotShadowStyle(
            elevation = 2.dp,
            ambientColor = Color.Black.copy(alpha = 0.05f),
            spotColor = Color.Black.copy(alpha = 0.05f),
        ),
        card = ShellShotShadowStyle(
            elevation = 5.dp,
            ambientColor = Color.Black.copy(alpha = 0.05f),
            spotColor = Color.Black.copy(alpha = 0.05f),
        ),
        floating = ShellShotShadowStyle(
            elevation = 10.dp,
            ambientColor = Color.Black.copy(alpha = 0.08f),
            spotColor = Color.Black.copy(alpha = 0.08f),
        ),
    ),
)

val DarkShellShotTokens = ShellShotTokens(
    colors = ShellShotColorTokens(
        backgroundPrimary = ShellColors.background(true),
        backgroundSecondary = ShellColors.background(true),
        cardBackground = ShellColors.baseCard(true),
        cardBackgroundStrong = ShellColors.elevatedCard(true),
        cardStroke = ShellColors.border(true),
        textPrimary = ShellColors.textPrimary(true),
        textSecondary = ShellColors.textSecondary(true),
        textTertiary = ShellColors.textTertiary(true),
        accentBlue = ShellColors.actionBg(true),
        accentBlueSoft = ShellColors.badgeBg(true),
        accentGreen = ShellColors.successGreen,
        accentGreenSoft = ShellColors.successGreen.copy(alpha = 0.16f),
        accentAmber = ShellColors.criticalOrange,
        accentAmberSoft = ShellColors.criticalOrange.copy(alpha = 0.18f),
        accentRed = ShellColors.criticalRed,
        accentRedSoft = ShellColors.criticalRed.copy(alpha = 0.18f),
        dividerColor = ShellColors.border(true),
        navBarBackground = ShellColors.baseCard(true),
        selectedItemBackground = ShellColors.badgeBg(true),
    ),
    shadows = ShellShotShadowTokens(
        soft = ShellShotShadowStyle(
            elevation = 2.dp,
            ambientColor = Color.Black.copy(alpha = 0.2f),
            spotColor = Color.Black.copy(alpha = 0.4f),
        ),
        card = ShellShotShadowStyle(
            elevation = 5.dp,
            ambientColor = Color.Black.copy(alpha = 0.3f),
            spotColor = Color.Black.copy(alpha = 0.5f),
        ),
        floating = ShellShotShadowStyle(
            elevation = 10.dp,
            ambientColor = Color.Black.copy(alpha = 0.4f),
            spotColor = Color.Black.copy(alpha = 0.6f),
        ),
    ),
)

val LocalShellShotTokens = staticCompositionLocalOf { LightShellShotTokens }

val MaterialTheme.shellShotTokens: ShellShotTokens
    @Composable
    get() = LocalShellShotTokens.current
