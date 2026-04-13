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
        backgroundPrimary = BackgroundPrimary,
        backgroundSecondary = BackgroundSecondary,
        cardBackground = CardBackground,
        cardBackgroundStrong = CardBackgroundStrong,
        cardStroke = CardStroke,
        textPrimary = TextPrimary,
        textSecondary = TextSecondary,
        textTertiary = TextTertiary,
        accentBlue = AccentBlue,
        accentBlueSoft = AccentBlueSoft,
        accentGreen = AccentGreen,
        accentGreenSoft = AccentGreenSoft,
        accentAmber = AccentAmber,
        accentAmberSoft = AccentAmberSoft,
        accentRed = AccentRed,
        accentRedSoft = AccentRedSoft,
        dividerColor = DividerColor,
        navBarBackground = NavBarBackground,
        selectedItemBackground = SelectedItemBackground,
    ),
    shadows = ShellShotShadowTokens(
        soft = ShellShotShadowStyle(
            elevation = 2.dp,
            ambientColor = ShadowAmbient,
            spotColor = ShadowSpot,
        ),
        card = ShellShotShadowStyle(
            elevation = 5.dp,
            ambientColor = ShadowAmbient,
            spotColor = ShadowSpot,
        ),
        floating = ShellShotShadowStyle(
            elevation = 10.dp,
            ambientColor = ShadowAmbient,
            spotColor = ShadowSpot,
        ),
    ),
)

val DarkShellShotTokens = ShellShotTokens(
    colors = ShellShotColorTokens(
        backgroundPrimary = DarkBackgroundPrimary,
        backgroundSecondary = DarkBackgroundSecondary,
        cardBackground = DarkCardBackground,
        cardBackgroundStrong = DarkCardBackgroundStrong,
        cardStroke = DarkCardStroke,
        textPrimary = DarkTextPrimary,
        textSecondary = DarkTextSecondary,
        textTertiary = DarkTextTertiary,
        accentBlue = DarkAccentBlue,
        accentBlueSoft = DarkAccentBlue.copy(alpha = 0.18f),
        accentGreen = DarkAccentGreen,
        accentGreenSoft = DarkAccentGreen.copy(alpha = 0.16f),
        accentAmber = DarkAccentAmber,
        accentAmberSoft = DarkAccentAmber.copy(alpha = 0.18f),
        accentRed = DarkAccentRed,
        accentRedSoft = DarkAccentRed.copy(alpha = 0.18f),
        dividerColor = DarkDividerColor,
        navBarBackground = DarkNavBarBackground,
        selectedItemBackground = DarkSelectedItemBackground,
    ),
    shadows = ShellShotShadowTokens(
        soft = ShellShotShadowStyle(
            elevation = 2.dp,
            ambientColor = DarkShadowAmbient,
            spotColor = DarkShadowSpot,
        ),
        card = ShellShotShadowStyle(
            elevation = 5.dp,
            ambientColor = DarkShadowAmbient,
            spotColor = DarkShadowSpot,
        ),
        floating = ShellShotShadowStyle(
            elevation = 10.dp,
            ambientColor = DarkShadowAmbient,
            spotColor = DarkShadowSpot,
        ),
    ),
)

val LocalShellShotTokens = staticCompositionLocalOf { LightShellShotTokens }

val MaterialTheme.shellShotTokens: ShellShotTokens
    @Composable
    get() = LocalShellShotTokens.current
