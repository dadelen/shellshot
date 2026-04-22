package com.example.shellshot.ui.theme

import androidx.compose.ui.graphics.Color

object ShellColors {
    val BackgroundLight = Color(0xFFEBEDF0)
    val BackgroundDark = Color(0xFF000000)
    val SurfaceLight = Color(0xFFFFFFFF)
    val SurfaceDark = Color(0xFF1C1C1E)
    val SurfaceAltLight = Color(0xFFF2F2F7)
    val SurfaceAltDark = Color(0xFF141416)
    val TextPrimaryLight = Color(0xFF171717)
    val TextPrimaryDark = Color(0xFFFFFFFF)
    val TextSecondaryLight = Color(0xFFA3A3A3)
    val TextSecondaryDark = Color(0xFF737373)
    val TextMutedLight = Color(0xFF6B7280)
    val TextMutedDark = Color(0xFFA1A1AA)
    val AccentBlue = Color(0xFF007AFF)
    val AccentGreen = Color(0xFF34C759)
    val AccentPurple = Color(0xFF8B5CF6)
    val AccentAmber = Color(0xFFF59E0B)
    val AccentRed = Color(0xFFEF4444)
    val AccentPink = Color(0xFFFF2D55)
    val AccentCyan = Color(0xFF5AC8FA)
    val GlassLight = Color(0x99FFFFFF)
    val GlassDark = Color(0x33101012)
    val GlassStrokeLight = Color(0xB3FFFFFF)
    val GlassStrokeDark = Color(0x33FFFFFF)
    val GridLine = Color(0x14808080)

    fun background(isDark: Boolean) = if (isDark) BackgroundDark else BackgroundLight
    fun surface(isDark: Boolean) = if (isDark) SurfaceDark else SurfaceLight
    fun surfaceAlt(isDark: Boolean) = if (isDark) SurfaceAltDark else SurfaceAltLight
    fun surfaceHigh(isDark: Boolean) = surfaceAlt(isDark)
    fun textPrimary(isDark: Boolean) = if (isDark) TextPrimaryDark else TextPrimaryLight
    fun textSecondary(isDark: Boolean) = if (isDark) TextSecondaryDark else TextSecondaryLight
    fun textMuted(isDark: Boolean) = if (isDark) TextMutedDark else TextMutedLight
    fun textTertiary(isDark: Boolean) = textMuted(isDark)
    fun glass(isDark: Boolean) = if (isDark) GlassDark else GlassLight
    fun glassSurface(isDark: Boolean) = glass(isDark)
    fun glassStroke(isDark: Boolean) = if (isDark) GlassStrokeDark else GlassStrokeLight
    fun loadingOverlay(isDark: Boolean) = if (isDark) Color(0xCC000000) else Color(0xCCFFFFFF)
    fun dockBackground(isDark: Boolean) = if (isDark) Color(0x33101012) else Color(0x1AFFFFFF)
    fun subtleFill(isDark: Boolean) = if (isDark) Color(0x26FFFFFF) else Color(0xFFF5F5F5)
    fun badgeBg(isDark: Boolean) = subtleFill(isDark)
    fun separator(isDark: Boolean) = if (isDark) Color(0x14FFFFFF) else Color(0x14000000)
}
