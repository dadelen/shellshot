package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
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
                cornerRadius = 32,
                padding = 24,
            ) {
                Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
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
                            .background(colors.subtleFill, RoundedCornerShape(20.dp))
                            .padding(horizontal = 18.dp, vertical = 20.dp),
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
                            .background(colors.subtleFill, RoundedCornerShape(20.dp))
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        if (uiState.recommendedScreenshotDirectories.isEmpty()) {
                            Text("", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = colors.textPrimary)
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
                    icon = AppIconId.Folder,
                    tint = ShellColors.AccentAmber,
                    background = ShellColors.AccentAmber.copy(alpha = if (isDark) 0.18f else 0.12f),
                    label = "文件访问",
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
                    label = "删除原图",
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
        Spacer(modifier = Modifier.height(136.dp))
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
        cornerRadius = 32,
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
            .padding(horizontal = 24.dp, vertical = 16.dp),
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
            .padding(horizontal = 24.dp, vertical = 16.dp),
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
