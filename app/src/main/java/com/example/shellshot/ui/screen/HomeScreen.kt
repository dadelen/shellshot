package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DarkMode
import androidx.compose.material.icons.rounded.Description
import androidx.compose.material.icons.rounded.LightMode
import androidx.compose.material.icons.rounded.PhoneIphone
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.data.ProcessingStatus
import com.example.shellshot.service.AutoShellMode
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.LiquidGlassSwitch
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.components.ZipBadge
import com.example.shellshot.ui.components.ZipGlassCard
import com.example.shellshot.ui.components.ZipGlassIconButton
import com.example.shellshot.ui.components.ZipIconPlate
import com.example.shellshot.ui.components.ZipPulsingDot
import com.example.shellshot.ui.components.ZipSectionDivider
import com.example.shellshot.ui.components.ZipStaggeredReveal

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    darkTheme: Boolean,
    onToggleDarkTheme: () -> Unit,
    onToggleMonitoring: (Boolean) -> Unit,
    onSelectTemplate: (String) -> Unit,
) {
    val selectedTemplate = uiState.selectedTemplate
    val titleColor = if (darkTheme) Color.White else Color(0xFF111827)
    val secondaryTextColor = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280)
    val insetColor = if (darkTheme) Color.White.copy(alpha = 0.06f) else Color.White.copy(alpha = 0.4f)
    val insetBorder = if (darkTheme) Color.White.copy(alpha = 0.06f) else Color.White.copy(alpha = 0.6f)
    val monitoringEnabled = uiState.settings.monitoringEnabled
    val statusTitle = if (monitoringEnabled) "自动监听已开启" else "自动监听已关闭"
    val statusDot = when {
        uiState.mode == AutoShellMode.ACTIVE && uiState.monitoringActive -> Color(0xFF34C759)
        monitoringEnabled -> Color(0xFFF59E0B)
        darkTheme -> Color(0xFF636366)
        else -> Color(0xFFD1D1D6)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(top = 56.dp, bottom = 140.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        item {
            ZipStaggeredReveal(index = 0) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "ShellShot",
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = titleColor,
                    )
                    ZipGlassIconButton(
                        icon = if (darkTheme) Icons.Rounded.LightMode else Icons.Rounded.DarkMode,
                        darkTheme = darkTheme,
                        onClick = onToggleDarkTheme,
                    )
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 1) {
                ZipGlassCard(
                    darkTheme = darkTheme,
                    cornerRadius = 40.dp,
                    modifier = Modifier.fillMaxWidth(),
                    contentPadding = PaddingValues(28.dp),
                ) {
                    Box {
                        Box(
                            modifier = Modifier
                                .size(140.dp)
                                .align(Alignment.TopEnd)
                                .padding(end = 4.dp)
                                .background(
                                    Brush.radialGradient(
                                        colors = listOf(
                                            Color(0xFF3B82F6).copy(alpha = if (darkTheme) 0.22f else 0.12f),
                                            Color.Transparent,
                                        ),
                                    ),
                                    RoundedCornerShape(999.dp),
                                ),
                        )

                        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                ) {
                                    ZipPulsingDot(
                                        color = statusDot,
                                        size = 10.dp,
                                    )
                                    Text(
                                        text = statusTitle,
                                        fontSize = 20.sp,
                                        lineHeight = 24.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = titleColor,
                                    )
                                }
                                LiquidGlassSwitch(
                                    checked = monitoringEnabled,
                                    onCheckedChange = onToggleMonitoring,
                                )
                            }

                            Text(
                                text = homeStatusSubtitle(uiState),
                                fontSize = 14.sp,
                                lineHeight = 20.sp,
                                color = secondaryTextColor,
                            )
                        }
                    }
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 2) {
                ZipGlassCard(
                    darkTheme = darkTheme,
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 40.dp,
                    contentPadding = PaddingValues(28.dp),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            ZipIconPlate(
                                icon = Icons.Rounded.Description,
                                tint = Color(0xFF3B82F6),
                                backgroundColor = if (darkTheme) {
                                    Color(0xFF3B82F6).copy(alpha = 0.16f)
                                } else {
                                    Color(0xFFEFF6FF)
                                },
                            )
                            Text(
                                text = "当前目录",
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = titleColor,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(insetColor, RoundedCornerShape(24.dp))
                                .border(1.dp, insetBorder, RoundedCornerShape(24.dp))
                                .padding(horizontal = 20.dp, vertical = 18.dp),
                        ) {
                            Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.Top,
                                ) {
                                    Text(
                                        text = uiState.screenshotDirectoryRelativePath,
                                        fontSize = 20.sp,
                                        lineHeight = 26.sp,
                                        fontWeight = FontWeight.SemiBold,
                                        color = titleColor,
                                        maxLines = 2,
                                        overflow = TextOverflow.Ellipsis,
                                        modifier = Modifier.weight(1f),
                                    )
                                }

                            }
                        }
                    }
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 3) {
                ZipGlassCard(
                    darkTheme = darkTheme,
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 40.dp,
                    contentPadding = PaddingValues(28.dp),
                ) {
                    Column(verticalArrangement = Arrangement.spacedBy(18.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(16.dp),
                            verticalAlignment = Alignment.Top,
                        ) {
                            Column(
                                modifier = Modifier.weight(1f),
                                verticalArrangement = Arrangement.spacedBy(10.dp),
                            ) {
                                Text(
                                    text = selectedTemplate?.name ?: "暂无模板",
                                    fontSize = 20.sp,
                                    lineHeight = 25.sp,
                                    fontWeight = FontWeight.SemiBold,
                                    color = titleColor,
                                    maxLines = 2,
                                    overflow = TextOverflow.Ellipsis,
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                                    ZipBadge(
                                        text = if (selectedTemplate?.isBuiltIn == false) "用户定制" else "内置模板",
                                        backgroundColor = Color(0xFF007AFF).copy(alpha = if (darkTheme) 0.16f else 0.1f),
                                        textColor = Color(0xFF007AFF),
                                    )
                                    selectedTemplate?.let {
                                        ZipBadge(
                                            text = "${it.outputWidth.takeIf { width -> width > 0 } ?: it.screenRect.width} × ${it.outputHeight.takeIf { height -> height > 0 } ?: it.screenRect.height}",
                                            backgroundColor = if (darkTheme) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.5f),
                                            textColor = secondaryTextColor,
                                        )
                                    }
                                }
                            }
                            ZipIconPlate(
                                icon = Icons.Rounded.PhoneIphone,
                                tint = Color(0xFF007AFF),
                                backgroundColor = Color(0xFF007AFF).copy(alpha = if (darkTheme) 0.16f else 0.1f),
                                size = 50.dp,
                                cornerRadius = 12.dp,
                                iconSize = 25.dp,
                            )
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(insetColor, RoundedCornerShape(24.dp))
                                .border(1.dp, insetBorder, RoundedCornerShape(24.dp))
                                .padding(horizontal = 22.dp, vertical = 22.dp),
                            contentAlignment = Alignment.Center,
                        ) {
                            if (selectedTemplate != null) {
                                Box(
                                    modifier = Modifier
                                        .size(width = 132.dp, height = 244.dp)
                                        .clickable { onSelectTemplate(selectedTemplate.id) },
                                ) {
                                    TemplatePreviewThumbnail(
                                        previewPath = selectedTemplate.previewAsset,
                                        contentDescription = selectedTemplate.name,
                                        modifier = Modifier.fillMaxSize(),
                                        cornerRadius = 24.dp,
                                        imagePadding = 8.dp,
                                        selected = true,
                                    )
                                }
                            } else {
                                Box(
                                    modifier = Modifier
                                        .aspectRatio(1f / 2f)
                                        .height(168.dp)
                                        .background(
                                            if (darkTheme) Color.White.copy(alpha = 0.06f) else Color.Black.copy(alpha = 0.04f),
                                            RoundedCornerShape(24.dp),
                                        ),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Icon(
                                        imageVector = Icons.Rounded.PhoneIphone,
                                        contentDescription = null,
                                        tint = secondaryTextColor,
                                        modifier = Modifier.size(30.dp),
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        item {
            ZipStaggeredReveal(index = 4) {
                ZipGlassCard(
                    darkTheme = darkTheme,
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 40.dp,
                    contentPadding = PaddingValues(28.dp),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "最近处理",
                            fontSize = 12.sp,
                            lineHeight = 16.sp,
                            fontWeight = FontWeight.Medium,
                            color = secondaryTextColor,
                        )
                        ZipBadge(
                            text = processingStatusLabel(uiState),
                            backgroundColor = processingStatusBackground(uiState, darkTheme),
                            textColor = processingStatusColor(uiState),
                        )
                    }

                    if (uiState.lastProcessingResult != null) {
                        val result = uiState.lastProcessingResult
                        HomeDetailRow(
                            darkTheme = darkTheme,
                            label = "触发来源",
                            value = result.triggerSource ?: "unknown",
                        )
                        ZipSectionDivider(
                            darkTheme = darkTheme,
                            startIndent = 0.dp,
                        )
                        HomeDetailRow(
                            darkTheme = darkTheme,
                            label = "原图处理",
                            value = if (result.deletedOriginal) "已删除原图" else "保留原图",
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(insetColor, RoundedCornerShape(24.dp))
                                .border(1.dp, insetBorder, RoundedCornerShape(24.dp))
                                .padding(horizontal = 16.dp, vertical = 15.dp),
                        ) {
                            Text(
                                text = result.sourcePath.substringAfterLast('/'),
                                fontSize = 12.sp,
                                lineHeight = 16.sp,
                                color = if (darkTheme) Color(0xFFD4D4D8) else Color(0xFF52525B),
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis,
                            )
                        }
                    } else {
                        Text(
                            text = "还没有最近一次处理记录。",
                            fontSize = 14.sp,
                            color = secondaryTextColor,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeDetailRow(
    darkTheme: Boolean,
    label: String,
    value: String,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            fontSize = 15.sp,
            color = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280),
        )
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Medium,
            color = if (darkTheme) Color.White else Color(0xFF111827),
        )
    }
}

private fun processingStatusLabel(uiState: MainUiState): String {
    val status = uiState.lastProcessingResult?.status ?: return "暂无记录"
    return when (status) {
        ProcessingStatus.Success -> "处理成功"
        ProcessingStatus.Failed -> "处理失败"
        ProcessingStatus.Skipped -> "已跳过"
    }
}

private fun processingStatusColor(uiState: MainUiState): Color {
    val status = uiState.lastProcessingResult?.status ?: return Color(0xFF6B7280)
    return when (status) {
        ProcessingStatus.Success -> Color(0xFF10B981)
        ProcessingStatus.Failed -> Color(0xFFEF4444)
        ProcessingStatus.Skipped -> Color(0xFFF59E0B)
    }
}

private fun processingStatusBackground(uiState: MainUiState, darkTheme: Boolean): Color {
    val color = processingStatusColor(uiState)
    return color.copy(alpha = if (darkTheme) 0.18f else 0.12f)
}

private fun homeStatusSubtitle(uiState: MainUiState): String = when {
    !uiState.hasCoreStartPermissions -> "核心权限还没有准备好。打开监听时会先引导你完成授权。"
    uiState.mode == AutoShellMode.ACTIVE && uiState.monitoringActive ->
        "AutoShellService 正在稳定运行，当前会按照设置里的截图目录自动监听。"
    uiState.mode == AutoShellMode.SUSPEND_GAME ->
        "检测到游戏在前台，当前已自动暂停处理，离开游戏后会自动恢复。"
    uiState.mode == AutoShellMode.SUSPEND_SCREEN_OFF ->
        "当前处于息屏状态，监听暂时挂起，解锁后会自动恢复补扫。"
    uiState.settings.monitoringEnabled ->
        "自动监听已启用，服务会在满足条件时恢复到活动状态。"
    else ->
        "准备就绪，打开上面的开关后就会开始监听当前截图目录。"
}
