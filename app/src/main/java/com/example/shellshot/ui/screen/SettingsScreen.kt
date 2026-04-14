package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.media.ScreenshotDirectoryRecommendation
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.LiquidGlassSwitch
import com.example.shellshot.ui.components.ZipStaggeredReveal

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
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
    val darkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val titleColor = if (darkTheme) Color.White else Color.Black

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(top = 64.dp, bottom = 142.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            ZipStaggeredReveal(index = 0) {
                Text(
                    text = "设置",
                    fontSize = 32.sp,
                    lineHeight = 36.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor,
                )
            }
        }

        item {
            ZipStaggeredReveal(index = 1) {
                SettingsGroup(darkTheme = darkTheme) {
                    SettingItem(
                        darkTheme = darkTheme,
                        icon = AppIconId.Notification,
                        title = "通知权限",
                        value = if (uiState.permissionSnapshot.notificationsGranted) "已开启" else "去开启",
                        showDivider = true,
                        onClick = if (uiState.permissionSnapshot.notificationsGranted) onOpenNotificationSettings else onRequestNotifications,
                    )
                    SettingItem(
                        darkTheme = darkTheme,
                        icon = AppIconId.Folder,
                        title = "所有文件访问",
                        value = if (uiState.permissionSnapshot.allFilesGranted) "已开启" else "去开启",
                        showDivider = true,
                        onClick = onOpenManageAllFilesSettings,
                    )
                    SettingItem(
                        darkTheme = darkTheme,
                        icon = AppIconId.Gallery,
                        title = "图片访问",
                        value = uiState.mediaAccessLabel,
                        showDivider = true,
                        onClick = onRequestMediaAccess,
                    )
                    SettingItem(
                        darkTheme = darkTheme,
                        icon = AppIconId.Battery,
                        title = "电量优化",
                        value = "去设置",
                        showDivider = false,
                        onClick = onOpenBatteryOptimizationSettings,
                    )
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 2) {
                SettingsGroup(darkTheme = darkTheme) {
                    SettingToggle(
                        darkTheme = darkTheme,
                        icon = AppIconId.ImageOff,
                        title = "媒体兜底",
                        checked = uiState.settings.mediaStoreFallbackEnabled,
                        onCheckedChange = onToggleMediaStoreFallback,
                        showDivider = true,
                    )
                    SettingToggle(
                        darkTheme = darkTheme,
                        icon = AppIconId.Delete,
                        title = "处理后删除原图",
                        checked = uiState.settings.autoDeleteOriginal,
                        onCheckedChange = onToggleAutoDelete,
                        showDivider = true,
                    )
                    SettingToggle(
                        darkTheme = darkTheme,
                        icon = AppIconId.Bug,
                        title = "调试模式",
                        checked = uiState.settings.debugModeEnabled,
                        onCheckedChange = onToggleDebugMode,
                        showDivider = false,
                    )
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 3) {
                ScreenshotDirectoryCard(
                    darkTheme = darkTheme,
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
private fun SettingsGroup(
    darkTheme: Boolean,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(40.dp))
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White)
            .border(
                1.dp,
                if (darkTheme) Color.White.copy(alpha = 0.08f) else Color.White,
                RoundedCornerShape(40.dp),
            )
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
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable(onClick = onClick)
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconPlate(icon, darkTheme)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = title,
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = if (darkTheme) Color.White else Color.Black,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = value,
                fontSize = 15.sp,
                color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            AppIcon(
                icon = AppIconId.ChevronRight,
                contentDescription = null,
                tint = if (darkTheme) Color(0xFF636366) else Color.LightGray,
                modifier = Modifier.padding(start = 8.dp),
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
                fontSize = 17.sp,
                fontWeight = FontWeight.Medium,
                color = if (darkTheme) Color.White else Color.Black,
                modifier = Modifier.weight(1f),
            )
            LiquidGlassSwitch(
                checked = checked,
                onCheckedChange = onCheckedChange,
                width = 56.dp,
                height = 32.dp,
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
    val titleColor = if (darkTheme) Color.White else Color.Black
    val secondaryColor = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray
    val amber = Color(0xFFF59E0B)

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(
                elevation = 20.dp,
                shape = RoundedCornerShape(40.dp),
                spotColor = Color.Black.copy(alpha = if (darkTheme) 0.5f else 0.05f),
            )
            .clip(RoundedCornerShape(40.dp))
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White)
            .border(
                1.dp,
                if (darkTheme) Color.White.copy(alpha = 0.05f) else Color.White,
                RoundedCornerShape(40.dp),
            )
            .padding(28.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp),
        ) {
            AmberIconPlate(icon = AppIconId.Edit, darkTheme = darkTheme, amber = amber)
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "截图目录",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = titleColor,
            )
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(72.dp)
                .clip(RoundedCornerShape(24.dp))
                .background(if (darkTheme) Color.Black.copy(alpha = 0.4f) else Color(0xFFF9F9FB))
                .border(1.dp, if (darkTheme) Color.White.copy(alpha = 0.05f) else Color.Transparent, RoundedCornerShape(24.dp))
                .padding(horizontal = 18.dp),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = currentRelativePath,
                fontSize = 15.sp,
                lineHeight = 20.sp,
                fontWeight = FontWeight.Medium,
                color = titleColor,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                AmberIconPlate(icon = AppIconId.Sparkles, darkTheme = darkTheme, amber = amber)
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = "自动推荐",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor,
                )
            }
            Text(
                text = if (detecting) "检测中" else "刷新",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF007AFF),
                modifier = Modifier.clickable(enabled = !detecting, onClick = onRefresh),
            )
        }

        when {
            detecting -> DirectoryRecommendationBox(
                darkTheme = darkTheme,
                title = "正在检测推荐目录",
                supporting = "检测完成后会在这里展示推荐结果。",
            )
            recommendations.isEmpty() -> DirectoryRecommendationBox(
                darkTheme = darkTheme,
                title = "暂无推荐目录",
                supporting = "先截几张图后再刷新。",
            )
            else -> Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(40.dp))
                    .background(if (darkTheme) Color.White.copy(alpha = 0.02f) else Color(0xFFF9F9FB).copy(alpha = 0.5f))
                    .border(1.dp, if (darkTheme) Color.White.copy(alpha = 0.05f) else Color(0xFFF2F2F7), RoundedCornerShape(40.dp))
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                recommendations.forEachIndexed { index, recommendation ->
                    val current = recommendation.relativePath.equals(currentRelativePath, ignoreCase = true)
                    if (index > 0) {
                        HorizontalDivider(color = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFF2F2F7))
                    }
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onSelectRecommendation(recommendation.relativePath) },
                        verticalArrangement = Arrangement.spacedBy(4.dp),
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = recommendation.relativePath,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (current) Color(0xFF007AFF) else titleColor,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis,
                                modifier = Modifier.weight(1f),
                            )
                            if (current) {
                                Text("当前", fontSize = 12.sp, color = Color(0xFF007AFF), fontWeight = FontWeight.Bold)
                            }
                        }
                        Text(
                            text = recommendation.reason,
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            color = secondaryColor,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun DirectoryRecommendationBox(
    darkTheme: Boolean,
    title: String,
    supporting: String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(40.dp))
            .background(if (darkTheme) Color.White.copy(alpha = 0.02f) else Color(0xFFF9F9FB).copy(alpha = 0.5f))
            .border(1.dp, if (darkTheme) Color.White.copy(alpha = 0.05f) else Color(0xFFF2F2F7), RoundedCornerShape(40.dp))
            .padding(24.dp),
    ) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (darkTheme) Color.White else Color.Black,
        )
        Spacer(modifier = Modifier.height(6.dp))
        Text(
            text = supporting,
            fontSize = 14.sp,
            color = Color.Gray,
        )
    }
}

@Composable
private fun AmberIconPlate(icon: AppIconId, darkTheme: Boolean, amber: Color) {
    Box(
        modifier = Modifier
            .size(36.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(if (darkTheme) amber.copy(alpha = 0.10f) else Color(0xFFFFFBEB))
            .border(0.5.dp, amber.copy(alpha = 0.20f), RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = icon,
            contentDescription = null,
            tint = amber,
            modifier = Modifier.size(18.dp),
        )
    }
}

@Composable
private fun IconPlate(icon: AppIconId, darkTheme: Boolean) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .background(if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFF2F2F7), RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = icon,
            contentDescription = null,
            tint = if (darkTheme) Color.White else Color.Black,
            modifier = Modifier.size(20.dp),
        )
    }
}

@Composable
private fun GroupDivider(darkTheme: Boolean) {
    HorizontalDivider(
        color = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFF2F2F7),
        thickness = 1.dp,
        modifier = Modifier.padding(start = 68.dp),
    )
}
