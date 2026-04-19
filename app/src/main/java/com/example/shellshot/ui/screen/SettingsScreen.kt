package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.media.ScreenshotDirectoryRecommendation
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.BentoSwitch
import com.example.shellshot.ui.components.ZipStaggeredReveal
import com.example.shellshot.ui.components.bentoCard
import com.example.shellshot.ui.theme.AppTypography
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.R
import androidx.compose.ui.res.stringResource
import com.kyant.backdrop.Backdrop

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    liquidBackdrop: Backdrop,
    onRequestNotifications: () -> Unit,
    onRequestMediaAccess: () -> Unit,
    onOpenNotificationSettings: () -> Unit,
    onOpenManageAllFilesSettings: () -> Unit,
    onOpenBatteryOptimizationSettings: () -> Unit,
    onToggleDebugMode: (Boolean) -> Unit,
    onRefreshScreenshotDirectories: () -> Unit,
    onUpdateScreenshotDirectory: (String) -> Unit,
    onToggleAutoDelete: (Boolean) -> Unit,
    onToggleMediaStoreFallback: (Boolean) -> Unit,
) {
    // Removed uiState.settings.darkTheme because it doesn't exist. By default using semantic inference:
    val isDark = androidx.compose.material3.MaterialTheme.colorScheme.background.red < 0.5f

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(ShellColors.background(isDark)),
        contentPadding = PaddingValues(top = 80.dp, bottom = 142.dp),
    ) {
        item {
            ZipStaggeredReveal(index = 0) {
                Text(
                    text = stringResource(R.string.settings_title),
                    style = AppTypography.displayLarge,
                    color = ShellColors.textPrimary(isDark),
                    modifier = Modifier.padding(start = 24.dp, end = 24.dp, bottom = 24.dp),
                )
            }
        }

        item {
            ZipStaggeredReveal(index = 1) {
                SettingsGroup(darkTheme = isDark) {
                    SettingItem(
                        darkTheme = isDark,
                        icon = AppIconId.Notification,
                        title = stringResource(R.string.settings_notifications),
                        value = if (uiState.permissionSnapshot.notificationsGranted) stringResource(R.string.settings_granted) else stringResource(R.string.settings_required),
                        showDivider = true,
                        onClick = if (uiState.permissionSnapshot.notificationsGranted) onOpenNotificationSettings else onRequestNotifications,
                    )
                    SettingItem(
                        darkTheme = isDark,
                        icon = AppIconId.Folder,
                        title = stringResource(R.string.settings_storage_access),
                        value = if (uiState.permissionSnapshot.allFilesGranted) stringResource(R.string.settings_granted) else stringResource(R.string.settings_required),
                        showDivider = true,
                        onClick = onOpenManageAllFilesSettings,
                    )
                    SettingItem(
                        darkTheme = isDark,
                        icon = AppIconId.Gallery,
                        title = stringResource(R.string.settings_media_access),
                        value = uiState.mediaAccessLabel,
                        showDivider = true,
                        onClick = onRequestMediaAccess,
                    )
                    SettingItem(
                        darkTheme = isDark,
                        icon = AppIconId.Battery,
                        title = stringResource(R.string.settings_battery_opt),
                        value = stringResource(R.string.settings_configure),
                        showDivider = false,
                        onClick = onOpenBatteryOptimizationSettings,
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(24.dp))
        }

        item {
            ZipStaggeredReveal(index = 2) {
                SettingsGroup(darkTheme = isDark) {
                    SettingToggle(
                        darkTheme = isDark,
                        icon = AppIconId.ImageOff,
                        title = stringResource(R.string.settings_media_store_fallback),
                        checked = uiState.settings.mediaStoreFallbackEnabled,
                        onCheckedChange = onToggleMediaStoreFallback,
                        showDivider = true,
                    )
                    SettingToggle(
                        darkTheme = isDark,
                        icon = AppIconId.Delete,
                        title = stringResource(R.string.settings_auto_delete),
                        checked = uiState.settings.autoDeleteOriginal,
                        onCheckedChange = onToggleAutoDelete,
                        showDivider = true,
                    )
                    SettingToggle(
                        darkTheme = isDark,
                        icon = AppIconId.Bug,
                        title = stringResource(R.string.settings_debug_mode),
                        checked = uiState.settings.debugModeEnabled,
                        onCheckedChange = onToggleDebugMode,
                        showDivider = false,
                    )
                }
            }
        }

        item {
            Spacer(modifier = Modifier.height(32.dp))
            SectionHeader(text = stringResource(R.string.settings_monitoring_dir), darkTheme = isDark)
        }

        item {
            ZipStaggeredReveal(index = 3) {
                ScreenshotDirectoryCard(
                    darkTheme = isDark,
                    currentRelativePath = uiState.screenshotDirectoryRelativePath,
                    recommendations = uiState.recommendedScreenshotDirectories,
                    detecting = uiState.detectingScreenshotDirectories,
                    onRefresh = onRefreshScreenshotDirectories,
                    onSelectRecommendation = onUpdateScreenshotDirectory,
                )
            }
        }
    }
}

@Composable
private fun SectionHeader(text: String, darkTheme: Boolean) {
    Text(
        text = text.uppercase(),
        style = AppTypography.labelMedium,
        color = ShellColors.textTertiary(darkTheme),
        modifier = Modifier.padding(start = 32.dp, end = 32.dp, bottom = 12.dp),
    )
}

@Composable
private fun SettingsGroup(
    darkTheme: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp)
            .bentoCard(darkTheme, cornerRadius = 24.dp)
            .padding(contentPadding),
        content = content,
    )
}

@Composable
private fun SettingItem(
    darkTheme: Boolean,
    icon: AppIconId,
    title: String,
    value: String,
    showDivider: Boolean,
    onClick: () -> Unit,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    
    val backgroundColor = if (pressed) ShellColors.badgeBg(darkTheme) else Color.Transparent

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(backgroundColor)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                    onClick = onClick
                )
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconPlate(icon, darkTheme)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = AppTypography.bodyLarge,
                color = ShellColors.textPrimary(darkTheme),
                modifier = Modifier.weight(1f),
            )
            Text(
                text = value,
                style = AppTypography.bodyMedium,
                color = ShellColors.textSecondary(darkTheme),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.width(8.dp))
            AppIcon(
                icon = AppIconId.ChevronRight,
                contentDescription = null,
                tint = ShellColors.textTertiary(darkTheme),
                modifier = Modifier.size(16.dp),
            )
        }
        if (showDivider) GroupDivider(darkTheme)
    }
}

@Composable
private fun SettingToggle(
    darkTheme: Boolean,
    icon: AppIconId,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    showDivider: Boolean,
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp, vertical = 12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconPlate(icon, darkTheme)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                style = AppTypography.bodyLarge,
                color = ShellColors.textPrimary(darkTheme),
                modifier = Modifier.weight(1f),
            )
            BentoSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                darkTheme = darkTheme,
                backdrop = null // Fallback if liquidSwitch uses it
            )
        }
        if (showDivider) GroupDivider(darkTheme)
    }
}

@Composable
private fun ScreenshotDirectoryCard(
    darkTheme: Boolean,
    currentRelativePath: String,
    recommendations: List<ScreenshotDirectoryRecommendation>,
    detecting: Boolean,
    onRefresh: () -> Unit,
    onSelectRecommendation: (String) -> Unit,
) {
    SettingsGroup(darkTheme = darkTheme) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 20.dp, bottom = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = stringResource(R.string.settings_active_target),
                    style = AppTypography.bodyLarge,
                    color = ShellColors.textPrimary(darkTheme),
                )
                Text(
                    text = if (detecting) stringResource(R.string.settings_scanning) else stringResource(R.string.settings_rescan),
                    style = AppTypography.labelMedium,
                    color = ShellColors.textSecondary(darkTheme),
                    modifier = Modifier.clickable(enabled = !detecting, onClick = onRefresh),
                )
            }
            
            GroupDivider(darkTheme = darkTheme)
            
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, end = 20.dp, top = 16.dp, bottom = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconPlate(icon = AppIconId.Folder, isDark = darkTheme)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = currentRelativePath,
                    style = AppTypography.bodyMedium,
                    color = ShellColors.textSecondary(darkTheme),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            if (recommendations.isNotEmpty()) {
                recommendations.forEachIndexed { index, recommendation ->
                    GroupDivider(darkTheme = darkTheme)
                    val current = recommendation.relativePath.equals(currentRelativePath, ignoreCase = true)
                    
                    val interactionSource = remember { MutableInteractionSource() }
                    val pressed by interactionSource.collectIsPressedAsState()
                    val backgroundColor = if (pressed) ShellColors.badgeBg(darkTheme) else Color.Transparent

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(backgroundColor)
                            .clickable(interactionSource = interactionSource, indication = null) {
                                onSelectRecommendation(recommendation.relativePath)
                            }
                            .padding(20.dp),
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = recommendation.relativePath,
                                style = AppTypography.bodyLarge,
                                color = if (current) ShellColors.textPrimary(darkTheme) else ShellColors.textSecondary(darkTheme),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f),
                            )
                            if (current) {
                                AppIcon(AppIconId.Sparkles, contentDescription = null, tint = ShellColors.textPrimary(darkTheme), modifier = Modifier.size(16.dp))
                            }
                        }
                        Text(
                            text = recommendation.reason,
                            style = AppTypography.bodyMedium,
                            color = ShellColors.textTertiary(darkTheme),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            } else if (!detecting) {
                 GroupDivider(darkTheme = darkTheme)
                 Text(
                    text = stringResource(R.string.settings_insufficient_media),
                    style = AppTypography.bodyMedium,
                    color = ShellColors.textTertiary(darkTheme),
                    modifier = Modifier.padding(20.dp)
                 )
            }
        }
    }
}

@Composable
private fun IconPlate(icon: AppIconId, isDark: Boolean) {
    // Premium monochrome plate
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(ShellColors.badgeBg(isDark)),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = icon,
            contentDescription = null,
            tint = ShellColors.textPrimary(isDark),
            modifier = Modifier.size(18.dp).align(Alignment.Center),
        )
    }
}

@Composable
private fun GroupDivider(darkTheme: Boolean) {
    HorizontalDivider(
        color = ShellColors.border(darkTheme),
        thickness = 0.5.dp,
        modifier = Modifier.padding(start = 68.dp),
    )
}
