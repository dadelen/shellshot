package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.DeleteOutline
import androidx.compose.material.icons.outlined.Description
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material.icons.outlined.Folder
import androidx.compose.material.icons.outlined.HideImage
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.NotificationsNone
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.media.ScreenshotDirectoryRecommendation
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.LiquidGlassSwitch
import com.example.shellshot.ui.components.ZipGlassCard
import com.example.shellshot.ui.components.ZipIconPlate
import com.example.shellshot.ui.components.ZipSectionDivider
import com.example.shellshot.ui.components.ZipStaggeredReveal

@Composable
fun SettingsScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    onRequestNotifications: () -> Unit,
    onRequestMediaAccess: () -> Unit,
    onOpenNotificationSettings: () -> Unit,
    onOpenManageAllFilesSettings: () -> Unit,
    onToggleDebugMode: (Boolean) -> Unit,
    onRefreshScreenshotDirectories: () -> Unit,
    onUpdateScreenshotDirectory: (String) -> Unit,
    onToggleAutoDelete: (Boolean) -> Unit,
    onToggleMediaStoreFallback: (Boolean) -> Unit,
) {
    val darkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val titleColor = if (darkTheme) Color.White else Color(0xFF111827)
    val currentDirectoryPath = uiState.screenshotDirectoryRelativePath

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(top = 56.dp, bottom = 140.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            ZipStaggeredReveal(index = 0) {
                Text(
                    text = "设置",
                    fontSize = 30.sp,
                    lineHeight = 34.sp,
                    fontWeight = FontWeight.Bold,
                    color = titleColor,
                )
            }
        }

        item {
            ZipStaggeredReveal(index = 1) {
                ZipGlassCard(
                    darkTheme = darkTheme,
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 40.dp,
                    contentPadding = PaddingValues(0.dp),
                ) {
                    SettingActionRow(
                        darkTheme = darkTheme,
                        icon = Icons.Outlined.NotificationsNone,
                        iconColor = Color(0xFF3B82F6),
                        iconBackground = if (darkTheme) Color(0xFF3B82F6).copy(alpha = 0.16f) else Color(0xFFEFF6FF),
                        label = "通知权限",
                        value = if (uiState.permissionSnapshot.notificationsGranted) "已开启" else "去开启",
                        onClick = if (uiState.permissionSnapshot.notificationsGranted) {
                            onOpenNotificationSettings
                        } else {
                            onRequestNotifications
                        },
                    )
                    ZipSectionDivider(darkTheme = darkTheme)
                    SettingActionRow(
                        darkTheme = darkTheme,
                        icon = Icons.Outlined.Folder,
                        iconColor = Color(0xFFF59E0B),
                        iconBackground = if (darkTheme) Color(0xFFF59E0B).copy(alpha = 0.16f) else Color(0xFFFFFBEB),
                        label = "所有文件访问",
                        value = if (uiState.permissionSnapshot.allFilesGranted) "已开启" else "去开启",
                        onClick = onOpenManageAllFilesSettings,
                    )
                    ZipSectionDivider(darkTheme = darkTheme)
                    SettingActionRow(
                        darkTheme = darkTheme,
                        icon = Icons.Outlined.Image,
                        iconColor = Color(0xFF10B981),
                        iconBackground = if (darkTheme) Color(0xFF10B981).copy(alpha = 0.16f) else Color(0xFFECFDF5),
                        label = "图片访问",
                        value = uiState.mediaAccessLabel,
                        onClick = onRequestMediaAccess,
                    )
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 2) {
                ZipGlassCard(
                    darkTheme = darkTheme,
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 40.dp,
                    contentPadding = PaddingValues(0.dp),
                ) {
                    SettingToggleRow(
                        darkTheme = darkTheme,
                        icon = Icons.Outlined.Description,
                        iconColor = Color(0xFF10B981),
                        iconBackground = if (darkTheme) Color(0xFF10B981).copy(alpha = 0.16f) else Color(0xFFECFDF5),
                        label = "调试模式",
                        checked = uiState.settings.debugModeEnabled,
                        onCheckedChange = onToggleDebugMode,
                    )
                    ZipSectionDivider(darkTheme = darkTheme)
                    SettingToggleRow(
                        darkTheme = darkTheme,
                        icon = Icons.Outlined.HideImage,
                        iconColor = Color(0xFFA855F7),
                        iconBackground = if (darkTheme) Color(0xFFA855F7).copy(alpha = 0.16f) else Color(0xFFFAF5FF),
                        label = "媒体兜底",
                        checked = uiState.settings.mediaStoreFallbackEnabled,
                        onCheckedChange = onToggleMediaStoreFallback,
                    )
                    ZipSectionDivider(darkTheme = darkTheme)
                    SettingToggleRow(
                        darkTheme = darkTheme,
                        icon = Icons.Outlined.DeleteOutline,
                        iconColor = Color(0xFFEF4444),
                        iconBackground = if (darkTheme) Color(0xFFEF4444).copy(alpha = 0.16f) else Color(0xFFFEF2F2),
                        label = "处理后删除原图",
                        checked = uiState.settings.autoDeleteOriginal,
                        onCheckedChange = onToggleAutoDelete,
                    )
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 3) {
                ZipGlassCard(
                    darkTheme = darkTheme,
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 40.dp,
                    contentPadding = PaddingValues(24.dp),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(20.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ZipIconPlate(
                                icon = Icons.Outlined.Edit,
                                tint = Color(0xFFF59E0B),
                                backgroundColor = if (darkTheme) {
                                    Color(0xFFF59E0B).copy(alpha = 0.16f)
                                } else {
                                    Color(0xFFFFFBEB)
                                },
                            )
                            Text(
                                text = "截图目录",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = titleColor,
                            )
                        }

                        DirectoryCurrentPanel(
                            darkTheme = darkTheme,
                            titleColor = titleColor,
                            relativePath = currentDirectoryPath,
                        )

                        DirectoryRecommendationSection(
                            darkTheme = darkTheme,
                            title = "自动推荐",
                            recommendations = uiState.recommendedScreenshotDirectories,
                            currentRelativePath = currentDirectoryPath,
                            detecting = uiState.detectingScreenshotDirectories,
                            actionLabel = if (uiState.detectingScreenshotDirectories) null else "刷新",
                            onAction = if (uiState.detectingScreenshotDirectories) null else onRefreshScreenshotDirectories,
                            onSelectRecommendation = onUpdateScreenshotDirectory,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun SettingActionRow(
    darkTheme: Boolean,
    icon: ImageVector,
    iconColor: Color,
    iconBackground: Color,
    label: String,
    value: String,
    onClick: () -> Unit,
) {
    val titleColor = if (darkTheme) Color.White else Color(0xFF111827)
    val valueColor = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(horizontal = 24.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ZipIconPlate(
                icon = icon,
                tint = iconColor,
                backgroundColor = iconBackground,
            )
            Text(
                text = label,
                fontSize = 17.sp,
                color = titleColor,
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = value,
                fontSize = 15.sp,
                color = valueColor,
            )
            Icon(
                imageVector = Icons.Outlined.ChevronRight,
                contentDescription = null,
                tint = if (darkTheme) Color(0xFF71717A) else Color(0xFF9CA3AF),
                modifier = Modifier.size(20.dp),
            )
        }
    }
}

@Composable
private fun SettingToggleRow(
    darkTheme: Boolean,
    icon: ImageVector,
    iconColor: Color,
    iconBackground: Color,
    label: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 18.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ZipIconPlate(
                icon = icon,
                tint = iconColor,
                backgroundColor = iconBackground,
            )
            Text(
                text = label,
                fontSize = 17.sp,
                color = if (darkTheme) Color.White else Color(0xFF111827),
            )
        }
        LiquidGlassSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
        )
    }
}

@Composable
private fun DirectoryCurrentPanel(
    darkTheme: Boolean,
    titleColor: Color,
    relativePath: String,
) {
    val background = if (darkTheme) Color.White.copy(alpha = 0.06f) else Color(0xFFF8F8FA)
    val borderColor = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFECECF1)

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(background, RoundedCornerShape(24.dp))
            .border(1.dp, borderColor, RoundedCornerShape(24.dp))
            .padding(horizontal = 20.dp, vertical = 18.dp),
    ) {
        Text(
            text = relativePath,
            fontSize = 17.sp,
            lineHeight = 22.sp,
            fontWeight = FontWeight.SemiBold,
            color = titleColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
private fun DirectoryRecommendationSection(
    darkTheme: Boolean,
    title: String,
    recommendations: List<ScreenshotDirectoryRecommendation>,
    currentRelativePath: String,
    detecting: Boolean,
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    onSelectRecommendation: (String) -> Unit,
) {
    val titleColor = if (darkTheme) Color.White else Color(0xFF111827)
    val secondaryTextColor = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280)
    val fieldBackground = if (darkTheme) Color.White.copy(alpha = 0.06f) else Color(0xFFF8F8FA)
    val fieldBorder = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFECECF1)
    val actionBlue = if (darkTheme) Color(0xFF93C5FD) else Color(0xFF2563EB)

    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = titleColor,
            )
            if (!actionLabel.isNullOrBlank() && onAction != null) {
                Text(
                    text = actionLabel,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium,
                    color = actionBlue,
                    modifier = Modifier.clickable(onClick = onAction),
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
            .background(fieldBackground, RoundedCornerShape(24.dp))
            .border(1.dp, fieldBorder, RoundedCornerShape(24.dp))
                .padding(horizontal = 18.dp, vertical = 16.dp),
        ) {
            when {
                detecting -> {
                    DirectoryRecommendationHint(
                        title = "正在检测推荐目录",
                        supporting = "检测完成后会在这里展示推荐结果。",
                        titleColor = titleColor,
                        supportingColor = secondaryTextColor,
                    )
                }

                recommendations.isEmpty() -> {
                    DirectoryRecommendationHint(
                        title = "暂无推荐目录",
                        supporting = "先截几张图后再刷新。",
                        titleColor = titleColor,
                        supportingColor = secondaryTextColor,
                    )
                }

                else -> {
                    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                        recommendations.forEachIndexed { index, recommendation ->
                            val isCurrent = recommendation.relativePath.equals(currentRelativePath, ignoreCase = true)
                            if (index > 0) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            if (darkTheme) Color.White.copy(alpha = 0.06f) else Color(0xFFE8EAF0),
                                            RoundedCornerShape(999.dp),
                                        )
                                        .padding(vertical = 0.5.dp),
                                )
                            }
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clickable { onSelectRecommendation(recommendation.relativePath) }
                                    .padding(vertical = 2.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    Text(
                                        text = recommendation.relativePath,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = if (isCurrent) actionBlue else titleColor,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f),
                                    )
                                    if (isCurrent) {
                                        Text(
                                            text = "当前",
                                            fontSize = 12.sp,
                                            fontWeight = FontWeight.Medium,
                                            color = actionBlue,
                                            modifier = Modifier.padding(start = 10.dp),
                                        )
                                    }
                                }
                                Text(
                                    text = recommendation.reason,
                                    fontSize = 12.sp,
                                    lineHeight = 17.sp,
                                    color = secondaryTextColor,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DirectoryRecommendationHint(
    title: String,
    supporting: String,
    titleColor: Color,
    supportingColor: Color,
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = titleColor,
        )
        Text(
            text = supporting,
            fontSize = 12.sp,
            lineHeight = 17.sp,
            color = supportingColor,
        )
    }
}
