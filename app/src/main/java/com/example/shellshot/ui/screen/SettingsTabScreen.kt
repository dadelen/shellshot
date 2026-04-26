package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.shellshot.data.ThemeOverride
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.components.IconPlate
import com.example.shellshot.ui.components.LiquidGlassSwitch
import com.example.shellshot.ui.components.StaggeredReveal
import com.example.shellshot.ui.components.noRippleClick
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import dev.chrisbanes.haze.HazeState

@Composable
fun SettingsTabScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    isDark: Boolean,
    hazeState: HazeState,
    onRequestNotifications: () -> Unit,
    onRequestMediaAccess: () -> Unit,
    onOpenManageAllFilesSettings: () -> Unit,
    onOpenBatteryOptimizationSettings: () -> Unit,
    onRefreshScreenshotDirectories: () -> Unit,
    onToggleAutoDelete: (Boolean) -> Unit,
    onToggleMediaStoreFallback: (Boolean) -> Unit,
    onToggleDebugMode: (Boolean) -> Unit,
    onSetThemeOverride: (ThemeOverride) -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    var showThemePicker by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        StaggeredReveal(0) {
            Text(
                text = "设置",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = colors.textPrimary,
            )
        }

        StaggeredReveal(1) {
            GlassSurfaceCard(
                modifier = Modifier.fillMaxWidth(),
                isDark = isDark,
                cornerRadius = 40,
                padding = 28,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                        IconPlate(
                            icon = AppIconId.Edit,
                            tint = ShellColors.AccentAmber,
                            background = ShellColors.AccentAmber.copy(alpha = if (isDark) 0.2f else 0.12f),
                        )
                        Text("截图目录", fontSize = 20.sp, fontWeight = FontWeight.Bold, color = colors.textPrimary)
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colors.subtleFill, RoundedCornerShape(24.dp))
                            .padding(horizontal = 18.dp, vertical = 24.dp),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = uiState.screenshotDirectoryRelativePath,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            color = colors.textPrimary,
                        )
                    }
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
                            IconPlate(
                                icon = AppIconId.Sparkles,
                                tint = ShellColors.AccentAmber,
                                background = ShellColors.AccentAmber.copy(alpha = if (isDark) 0.2f else 0.12f),
                            )
                            Text("自动推荐", fontSize = 17.sp, fontWeight = FontWeight.Bold, color = colors.textPrimary)
                        }
                        Text(
                            text = if (uiState.detectingScreenshotDirectories) "刷新中" else "刷新",
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Medium,
                            color = ShellColors.AccentBlue,
                            modifier = Modifier.noRippleClick(onRefreshScreenshotDirectories),
                        )
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(colors.subtleFill, RoundedCornerShape(24.dp))
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        if (uiState.recommendedScreenshotDirectories.isEmpty()) {
                            Text("暂无推荐目录", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colors.textPrimary)
                        } else {
                            uiState.recommendedScreenshotDirectories.take(3).forEach { recommendation ->
                                Text(recommendation.relativePath, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = colors.textPrimary)
                                Text(recommendation.reason, fontSize = 13.sp, color = colors.textSecondary)
                            }
                        }
                    }
                }
            }
        }

        StaggeredReveal(2) {
            SettingsGroupCard(isDark = isDark) {
                SettingsRow(
                    icon = AppIconId.Notification,
                    tint = ShellColors.AccentBlue,
                    background = ShellColors.AccentBlue.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "通知权限",
                    value = if (uiState.permissionSnapshot.notificationsGranted) "已开启" else "去开启",
                    onClick = onRequestNotifications,
                )
                SettingsDivider(isDark)
                SettingsRow(
                    icon = AppIconId.Settings,
                    tint = ShellColors.AccentBlue,
                    background = ShellColors.AccentBlue.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "主题策略",
                    value = when (uiState.themeOverride) {
                        ThemeOverride.System -> "跟随系统"
                        ThemeOverride.Light -> "浅色"
                        ThemeOverride.Dark -> "深色"
                    },
                    onClick = { showThemePicker = true },
                )
                SettingsDivider(isDark)
                SettingsRow(
                    icon = AppIconId.Folder,
                    tint = ShellColors.AccentAmber,
                    background = ShellColors.AccentAmber.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "所有文件访问",
                    value = if (uiState.permissionSnapshot.allFilesGranted) "已开启" else "去开启",
                    onClick = onOpenManageAllFilesSettings,
                )
                SettingsDivider(isDark)
                SettingsRow(
                    icon = AppIconId.Gallery,
                    tint = ShellColors.AccentGreen,
                    background = ShellColors.AccentGreen.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "图片访问",
                    value = uiState.mediaAccessLabel,
                    onClick = onRequestMediaAccess,
                )
                SettingsDivider(isDark)
                SettingsRow(
                    icon = AppIconId.Battery,
                    tint = ShellColors.AccentPurple,
                    background = ShellColors.AccentPurple.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "电池优化",
                    value = "配置",
                    onClick = onOpenBatteryOptimizationSettings,
                )
            }
        }

        StaggeredReveal(3) {
            SettingsGroupCard(isDark = isDark) {
                SettingsToggleRow(
                    icon = AppIconId.ImageOff,
                    tint = ShellColors.AccentPurple,
                    background = ShellColors.AccentPurple.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "媒体兜底",
                    checked = uiState.settings.mediaStoreFallbackEnabled,
                    onToggle = onToggleMediaStoreFallback,
                )
                SettingsDivider(isDark)
                SettingsToggleRow(
                    icon = AppIconId.Delete,
                    tint = ShellColors.AccentRed,
                    background = ShellColors.AccentRed.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "处理后删除原图",
                    checked = uiState.settings.autoDeleteOriginal,
                    onToggle = onToggleAutoDelete,
                )
                SettingsDivider(isDark)
                SettingsToggleRow(
                    icon = AppIconId.Bug,
                    tint = ShellColors.AccentGreen,
                    background = ShellColors.AccentGreen.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "调试模式",
                    checked = uiState.settings.debugModeEnabled,
                    onToggle = onToggleDebugMode,
                )
            }
        }
    }

    if (showThemePicker) {
        ThemeOverrideDialog(
            isDark = isDark,
            selected = uiState.themeOverride,
            onSelect = {
                onSetThemeOverride(it)
                showThemePicker = false
            },
            onDismiss = { showThemePicker = false },
        )
    }
}

@Composable
private fun SettingsGroupCard(
    isDark: Boolean,
    content: @Composable ColumnScope.() -> Unit,
) {
    GlassSurfaceCard(
        modifier = Modifier.fillMaxWidth(),
        isDark = isDark,
        cornerRadius = 40,
        padding = 8,
    ) {
        Column(content = content)
    }
}

@Composable
private fun SettingsRow(
    icon: AppIconId,
    tint: androidx.compose.ui.graphics.Color,
    background: androidx.compose.ui.graphics.Color,
    label: String,
    value: String,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .noRippleClick(onClick)
            .padding(horizontal = 24.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconPlate(icon = icon, tint = tint, background = background)
            Text(label, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = colors.textPrimary)
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp), verticalAlignment = Alignment.CenterVertically) {
            Text(value, fontSize = 15.sp, color = colors.textSecondary)
            com.example.shellshot.ui.components.AppIcon(
                icon = AppIconId.ChevronRight,
                contentDescription = null,
                tint = colors.textSecondary,
            )
        }
    }
}

@Composable
private fun SettingsToggleRow(
    icon: AppIconId,
    tint: androidx.compose.ui.graphics.Color,
    background: androidx.compose.ui.graphics.Color,
    label: String,
    checked: Boolean,
    onToggle: (Boolean) -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp), verticalAlignment = Alignment.CenterVertically) {
            IconPlate(icon = icon, tint = tint, background = background)
            Text(label, fontSize = 17.sp, fontWeight = FontWeight.Medium, color = colors.textPrimary)
        }
        LiquidGlassSwitch(isOn = checked, onToggle = onToggle)
    }
}

@Composable
private fun SettingsDivider(isDark: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 68.dp)
            .height(1.dp)
            .background(ShellColors.separator(isDark)),
    )
}

@Composable
private fun ThemeOverrideDialog(
    isDark: Boolean,
    selected: ThemeOverride,
    onSelect: (ThemeOverride) -> Unit,
    onDismiss: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Dialog(onDismissRequest = onDismiss) {
        GlassSurfaceCard(
            modifier = Modifier.fillMaxWidth(),
            isDark = isDark,
            cornerRadius = 32,
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                Text("主题策略", fontSize = 22.sp, fontWeight = FontWeight.Black, color = colors.textPrimary)
                ThemeOverride.entries.forEach { option ->
                    val title = when (option) {
                        ThemeOverride.System -> "跟随系统"
                        ThemeOverride.Light -> "浅色"
                        ThemeOverride.Dark -> "深色"
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                if (selected == option) ShellColors.AccentBlue.copy(alpha = 0.12f) else colors.subtleFill,
                                RoundedCornerShape(18.dp),
                            )
                            .padding(horizontal = 16.dp, vertical = 14.dp)
                            .noRippleClick { onSelect(option) },
                    ) {
                        Text(title, color = colors.textPrimary, fontWeight = FontWeight.Bold)
                    }
                }
            }
        }
    }
}
