package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.LiquidGlassSwitch
import com.example.shellshot.ui.designsystem.ioslike.IOSColors
import com.example.shellshot.ui.designsystem.ioslike.IOSGroupCard
import com.example.shellshot.ui.designsystem.ioslike.IOSPage
import com.example.shellshot.ui.designsystem.ioslike.IOSSettingRow
import com.example.shellshot.ui.designsystem.ioslike.IOSSpacing
import com.example.shellshot.ui.designsystem.ioslike.IOSTypography
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
    val colors = IOSColors.scheme(isDark)
    IOSPage(
        modifier = modifier,
        title = "设置",
        isDark = isDark,
    ) {
        IOSSection(title = "截图目录", isDark = isDark) {
            Column(
                modifier = Modifier.padding(IOSSpacing.Default.cardPadding),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                Text(
                    text = uiState.screenshotDirectoryRelativePath,
                    style = IOSTypography.Default.cardTitle,
                    color = colors.primaryText,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            IOSDivider(isDark = isDark)
            IOSSettingRow(
                icon = AppIconId.Refresh,
                title = if (uiState.detectingScreenshotDirectories) "正在刷新推荐" else "自动推荐目录",
                isDark = isDark,
                tint = IOSColors.Orange,
                onClick = onRefreshScreenshotDirectories,
                trailing = {
                    AppIcon(
                        icon = AppIconId.ChevronRight,
                        contentDescription = null,
                        tint = colors.tertiaryText,
                        modifier = Modifier.size(18.dp),
                    )
                },
            )
        }

        IOSSection(title = "权限", isDark = isDark) {
            IOSSettingRow(
                icon = AppIconId.Notification,
                title = "通知权限",
                isDark = isDark,
                tint = IOSColors.Blue,
                onClick = onRequestNotifications,
                trailing = { IOSChevron(isDark) },
            )
            IOSDivider(isDark = isDark)
            IOSSettingRow(
                icon = AppIconId.Folder,
                title = "全部文件访问",
                isDark = isDark,
                tint = IOSColors.Orange,
                onClick = onOpenManageAllFilesSettings,
                trailing = { IOSChevron(isDark) },
            )
            IOSDivider(isDark = isDark)
            IOSSettingRow(
                icon = AppIconId.Gallery,
                title = "图片访问",
                isDark = isDark,
                tint = IOSColors.Green,
                onClick = onRequestMediaAccess,
                trailing = { IOSChevron(isDark) },
            )
            IOSDivider(isDark = isDark)
            IOSSettingRow(
                icon = AppIconId.Battery,
                title = "电池优化",
                isDark = isDark,
                tint = IOSColors.Orange,
                onClick = onOpenBatteryOptimizationSettings,
                trailing = { IOSChevron(isDark) },
            )
        }

        IOSSection(title = "输出", isDark = isDark) {
            IOSSettingRow(
                icon = AppIconId.ImageOff,
                title = "MediaStore 兜底",
                isDark = isDark,
                tint = IOSColors.Blue,
                trailing = {
                    LiquidGlassSwitch(
                        isOn = uiState.settings.mediaStoreFallbackEnabled,
                        onToggle = onToggleMediaStoreFallback,
                    )
                },
            )
            IOSDivider(isDark = isDark)
            IOSSettingRow(
                icon = AppIconId.Delete,
                title = "删除原图",
                isDark = isDark,
                tint = IOSColors.Red,
                trailing = {
                    LiquidGlassSwitch(
                        isOn = uiState.settings.autoDeleteOriginal,
                        onToggle = onToggleAutoDelete,
                    )
                },
            )
        }

        IOSSection(title = "性能", isDark = isDark) {
            IOSSettingRow(
                icon = AppIconId.Bug,
                title = "调试模式",
                isDark = isDark,
                tint = IOSColors.Green,
                trailing = {
                    LiquidGlassSwitch(
                        isOn = uiState.settings.debugModeEnabled,
                        onToggle = onToggleDebugMode,
                    )
                },
            )
        }

        Spacer(modifier = Modifier.height(112.dp))
    }
}

@Composable
private fun IOSSection(
    title: String,
    isDark: Boolean,
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = IOSColors.scheme(isDark)
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = title,
            style = IOSTypography.Default.secondary,
            color = colors.secondaryText,
            modifier = Modifier.padding(horizontal = 4.dp),
        )
        IOSGroupCard(
            isDark = isDark,
            padding = PaddingValues(0.dp),
        ) {
            Column(content = content)
        }
    }
}

@Composable
private fun IOSDivider(isDark: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 62.dp)
            .height(0.5.dp)
            .background(IOSColors.scheme(isDark).separator),
    )
}

@Composable
private fun IOSChevron(isDark: Boolean) {
    AppIcon(
        icon = AppIconId.ChevronRight,
        contentDescription = null,
        tint = IOSColors.scheme(isDark).tertiaryText,
        modifier = Modifier.size(18.dp),
    )
}
