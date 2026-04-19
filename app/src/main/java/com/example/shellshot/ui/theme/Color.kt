package com.example.shellshot.ui.theme

import androidx.compose.ui.graphics.Color

object ShellColors {
    // Backgrounds
    fun background(isDark: Boolean) = if (isDark) Color.Black else Color(0xFFE8EAEE)
    fun baseCard(isDark: Boolean) = if (isDark) Color(0xCC141415) else Color(0xCCFFFFFF)
    fun elevatedCard(isDark: Boolean) = if (isDark) Color(0xD91C1C1E) else Color(0xD9F7F8FA)
    
    // Status & Badges (Monochrome logic instead of colors)
    fun badgeBg(isDark: Boolean) = if (isDark) Color.White.copy(alpha = 0.1f) else Color.Black.copy(alpha = 0.06f)
    fun badgeText(isDark: Boolean) = if (isDark) Color(0xFFE5E5EA) else Color(0xFF3A3A3C)
    
    // Strokes and separators
    fun border(isDark: Boolean) = if (isDark) Color.White.copy(alpha = 0.08f) else Color.Black.copy(alpha = 0.06f)
    fun subtleBorder(isDark: Boolean) = if (isDark) Color.White.copy(alpha = 0.03f) else Color.Black.copy(alpha = 0.03f)
    
    // Text primary/secondary
    fun textPrimary(isDark: Boolean) = if (isDark) Color.White else Color.Black
    fun textSecondary(isDark: Boolean) = if (isDark) Color(0xFFA1A1AA) else Color(0xFF8E8E93)
    fun textTertiary(isDark: Boolean) = if (isDark) Color(0xFF636366) else Color(0xFFAEAEC0)
    
    // Button Action (Invert theme for focus)
    fun actionBg(isDark: Boolean) = if (isDark) Color.White else Color.Black
    fun actionText(isDark: Boolean) = if (isDark) Color.Black else Color.White
    
    // Toggles (Glass Switch active states)
    fun switchActive(isDark: Boolean) = if (isDark) Color.White else Color.Black

    // Strict Accents (Use EXTREMELY sparingly)
    val criticalOrange = Color(0xFFFF9F0A)
    val criticalRed = Color(0xFFFF453A)
    val successGreen = Color(0xFF32D74B)
}
