package com.example.shellshot.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Immutable
data class TemplateModuleColorTokens(
    val pageBackground: Color,
    val container: Color,
    val overviewOverlay: Color,
    val primary: Color,
    val destructive: Color,
    val textPrimary: Color,
    val textSecondary: Color,
    val iconPlate: Color,
    val podGlass: Color,
    val glow: Color,
)

@Immutable
data class TemplateModuleRadiusTokens(
    val carouselCard: Dp,
    val overviewCard: Dp,
    val iconPlate: Dp,
    val pod: Dp,
)

@Immutable
data class TemplateModuleShadowTokens(
    val cardElevation: Dp,
    val podElevation: Dp,
    val overviewElevation: Dp,
)

@Immutable
data class TemplateModuleSpacingTokens(
    val cardPadding: Dp,
    val titleGap: Dp,
    val podBottomInset: Dp,
    val gridGap: Dp,
)

@Immutable
data class TemplateModuleTypographyTokens(
    val pageTitle: TextUnit,
    val cardTitle: TextUnit,
    val cardSubtitle: TextUnit,
    val buttonText: TextUnit,
    val overviewTitle: TextUnit,
    val overviewSubtitle: TextUnit,
)

@Immutable
data class TemplateModuleTokens(
    val colors: TemplateModuleColorTokens,
    val radii: TemplateModuleRadiusTokens,
    val shadows: TemplateModuleShadowTokens,
    val spacing: TemplateModuleSpacingTokens,
    val typography: TemplateModuleTypographyTokens,
)

private val lightTemplateModuleTokens = TemplateModuleTokens(
    colors = TemplateModuleColorTokens(
        pageBackground = Color(0xFFF2F2F7),
        container = Color(0xFFFFFFFF),
        overviewOverlay = Color(0xEFFFFFFF),
        primary = Color(0xFF007AFF),
        destructive = Color(0xFFFF3B30),
        textPrimary = Color(0xFF1D1D1F),
        textSecondary = Color(0xFF8E8E93),
        iconPlate = Color(0xFFF5F5F7),
        podGlass = Color(0xEFFFFFFF),
        glow = Color(0x33007AFF),
    ),
    radii = TemplateModuleRadiusTokens(
        carouselCard = 40.dp,
        overviewCard = 24.dp,
        iconPlate = 24.dp,
        pod = 999.dp,
    ),
    shadows = TemplateModuleShadowTokens(
        cardElevation = 18.dp,
        podElevation = 20.dp,
        overviewElevation = 4.dp,
    ),
    spacing = TemplateModuleSpacingTokens(
        cardPadding = 24.dp,
        titleGap = 30.dp,
        podBottomInset = 112.dp,
        gridGap = 12.dp,
    ),
    typography = TemplateModuleTypographyTokens(
        pageTitle = 30.sp,
        cardTitle = 22.sp,
        cardSubtitle = 13.sp,
        buttonText = 11.sp,
        overviewTitle = 15.sp,
        overviewSubtitle = 11.sp,
    ),
)

private val darkTemplateModuleTokens = TemplateModuleTokens(
    colors = TemplateModuleColorTokens(
        pageBackground = Color(0xFF000000),
        container = Color(0xFF1C1C1E),
        overviewOverlay = Color(0xE61A1A1D),
        primary = Color(0xFF007AFF),
        destructive = Color(0xFFFF3B30),
        textPrimary = Color(0xFFF5F5F7),
        textSecondary = Color(0xFF98989D),
        iconPlate = Color(0xFF2C2C2E),
        podGlass = Color(0xDE1C1C1E),
        glow = Color(0x33007AFF),
    ),
    radii = TemplateModuleRadiusTokens(
        carouselCard = 40.dp,
        overviewCard = 24.dp,
        iconPlate = 24.dp,
        pod = 999.dp,
    ),
    shadows = TemplateModuleShadowTokens(
        cardElevation = 18.dp,
        podElevation = 20.dp,
        overviewElevation = 4.dp,
    ),
    spacing = TemplateModuleSpacingTokens(
        cardPadding = 24.dp,
        titleGap = 30.dp,
        podBottomInset = 112.dp,
        gridGap = 12.dp,
    ),
    typography = TemplateModuleTypographyTokens(
        pageTitle = 30.sp,
        cardTitle = 22.sp,
        cardSubtitle = 13.sp,
        buttonText = 11.sp,
        overviewTitle = 15.sp,
        overviewSubtitle = 11.sp,
    ),
)

val MaterialTheme.templateModuleTokens: TemplateModuleTokens
    @Composable
    get() = if (colorScheme.background.red < 0.5f) darkTemplateModuleTokens else lightTemplateModuleTokens
