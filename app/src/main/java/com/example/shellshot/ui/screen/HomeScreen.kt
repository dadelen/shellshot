package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.data.ProcessingStatus
import com.example.shellshot.service.AutoShellMode
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.utils.TimeUtils
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    darkTheme: Boolean,
    onToggleDarkTheme: () -> Unit,
    onToggleMonitoring: (Boolean) -> Unit,
    onSelectTemplate: (String) -> Unit,
    onOpenTemplates: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenLogs: () -> Unit,
) {
    val pageDark = darkTheme
    var isTransitioning by remember { mutableStateOf(false) }
    var previousDark by remember { mutableStateOf(darkTheme) }
    var targetDark by remember { mutableStateOf(darkTheme) }

    val scale by animateFloatAsState(
        targetValue = if (isTransitioning) 1.02f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow,
        ),
        label = "scale",
    )

    val contentAlpha by animateFloatAsState(
        targetValue = if (isTransitioning) 0f else 1f,
        animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        label = "contentAlpha",
    )

    LaunchedEffect(darkTheme) {
        if (previousDark != darkTheme) {
            isTransitioning = true
            delay(200)
            previousDark = darkTheme
            isTransitioning = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (pageDark) Color.Black else Color(0xFFF2F2F7))
            .scale(scale),
    ) {
        HomeContent(
            uiState = uiState,
            isDark = pageDark,
            onToggleTheme = {
                onToggleDarkTheme()
            },
            onToggleMonitoring = onToggleMonitoring,
            onOpenTemplates = onOpenTemplates,
            onOpenSettings = onOpenSettings,
            onOpenLogs = onOpenLogs,
            onSelectTemplate = onSelectTemplate,
            modifier = Modifier.alpha(contentAlpha),
        )
    }
}

@Composable
private fun HomeContent(
    uiState: MainUiState,
    isDark: Boolean,
    onToggleTheme: () -> Unit,
    onToggleMonitoring: (Boolean) -> Unit,
    onOpenTemplates: () -> Unit,
    onOpenSettings: () -> Unit,
    onOpenLogs: () -> Unit,
    onSelectTemplate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    var showContent by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        delay(50)
        showContent = true
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(if (isDark) Color.Black else Color(0xFFF2F2F7))
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
    ) {
        StaggeredItem(visible = showContent, index = 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, bottom = 24.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "ShellShot",
                    fontSize = 34.sp,
                    fontWeight = FontWeight.Black,
                    color = if (isDark) Color.White else Color.Black,
                )
                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier
                        .size(44.dp)
                        .background(
                            if (isDark) Color(0xFF1C1C1E) else Color.White,
                            CircleShape
                        )
                        .shadow(
                            elevation = 4.dp,
                            shape = CircleShape,
                            ambientColor = Color.Black.copy(alpha = 0.1f),
                            spotColor = Color.Black.copy(alpha = 0.1f),
                        )
                        .border(
                            width = 1.dp,
                            color = if (isDark) Color.White.copy(alpha = 0.1f) else Color(0xFFE5E5EA),
                            shape = CircleShape,
                        ),
                ) {
                    AppIcon(
                        icon = if (isDark) AppIconId.Sun else AppIconId.Moon,
                        contentDescription = "切换主题",
                        tint = if (isDark) Color.White else Color.Black,
                        modifier = Modifier.size(23.dp),
                    )
                }
            }
        }

        StaggeredItem(visible = showContent, index = 1) {
            StatusCard(
                uiState = uiState,
                isDark = isDark,
                onToggleMonitoring = onToggleMonitoring,
                onOpenSettings = onOpenSettings,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        StaggeredItem(visible = showContent, index = 2) {
            CurrentTemplateCard(
                uiState = uiState,
                isDark = isDark,
                onOpenTemplates = onOpenTemplates,
                onSelectTemplate = onSelectTemplate,
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        StaggeredItem(visible = showContent, index = 3) {
            RecentLogCard(uiState = uiState, isDark = isDark)
        }

        Spacer(modifier = Modifier.height(120.dp))
    }
}

@Composable
private fun StaggeredItem(
    visible: Boolean,
    index: Int,
    content: @Composable () -> Unit,
) {
    var itemVisible by remember { mutableStateOf(false) }
    LaunchedEffect(visible) {
        if (visible) {
            delay(index * 100L)
            itemVisible = true
        }
    }

    AnimatedVisibility(
        visible = itemVisible,
        enter = slideInVertically(initialOffsetY = { 30 }) + fadeIn() + scaleIn(initialScale = 0.98f),
        exit = fadeOut() + scaleOut(targetScale = 1.02f),
        modifier = Modifier.fillMaxWidth(),
    ) {
        content()
    }
}

@Composable
private fun StatusCard(
    uiState: MainUiState,
    isDark: Boolean,
    onToggleMonitoring: (Boolean) -> Unit,
    onOpenSettings: () -> Unit,
) {
    val monitoringEnabled = uiState.settings.monitoringEnabled
    val dotColor = when {
        uiState.mode == AutoShellMode.ACTIVE && uiState.monitoringActive -> Color(0xFF34C759)
        monitoringEnabled -> Color(0xFFFF9500)
        isDark -> Color(0xFF8E8E93)
        else -> Color.Gray
    }
    val title = when {
        uiState.mode == AutoShellMode.ACTIVE && uiState.monitoringActive -> "监听服务已启动"
        monitoringEnabled -> "监听服务准备中"
        else -> "监听服务未启动"
    }
    val infiniteTransition = rememberInfiniteTransition(label = "home-status-dot")
    val pingScale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 2.5f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Restart),
        label = "home-status-dot-scale",
    )
    val pingAlpha by infiniteTransition.animateFloat(
        initialValue = 0.5f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(tween(1500), RepeatMode.Restart),
        label = "home-status-dot-alpha",
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(20.dp, RoundedCornerShape(40.dp), spotColor = Color.Black.copy(alpha = if (isDark) 0.18f else 0.10f))
            .clip(RoundedCornerShape(40.dp))
            .background(if (isDark) Color(0xFF1C1C1E) else Color.White)
            .border(1.dp, if (isDark) Color.White.copy(alpha = 0.05f) else Color.White, RoundedCornerShape(40.dp))
            .padding(28.dp),
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 40.dp, y = (-40).dp)
                .size(160.dp)
                .blur(60.dp)
                .background(Color(0xFF3B82F6).copy(alpha = if (isDark) 0.10f else 0.05f), CircleShape),
        )

        Column {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(contentAlignment = Alignment.Center) {
                    Box(
                        modifier = Modifier
                            .size(12.dp)
                            .scale(pingScale)
                            .background(dotColor.copy(alpha = pingAlpha), CircleShape),
                    )
                    Box(modifier = Modifier.size(8.dp).background(dotColor, CircleShape))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Text(
                    text = title,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (isDark) Color.White else Color.Black,
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = { onToggleMonitoring(!monitoringEnabled) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (monitoringEnabled) Color(0xFFFF3B30) else if (isDark) Color.White else Color.Black,
                ),
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(60.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp, pressedElevation = 0.dp),
            ) {
                AppIcon(
                    icon = if (monitoringEnabled) AppIconId.Close else AppIconId.Play,
                    contentDescription = null,
                    tint = if (isDark || monitoringEnabled) Color.Black else Color.White,
                    modifier = Modifier.size(20.dp),
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (monitoringEnabled) "停止监听服务" else "启动监听服务",
                    color = if (isDark || monitoringEnabled) Color.Black else Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Black,
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            SmallActionButton(
                title = "权限设置",
                icon = AppIconId.Settings,
                isDark = isDark,
                modifier = Modifier.fillMaxWidth(),
                onClick = onOpenSettings,
            )
        }
    }
}

@Composable
private fun SmallActionButton(
    title: String,
    icon: AppIconId,
    isDark: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .height(60.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(if (isDark) Color.White.copy(alpha = 0.05f) else Color(0xFFF9F9FB))
            .clickable(onClick = onClick)
            .padding(12.dp),
        contentAlignment = Alignment.Center,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            AppIcon(
                icon = icon,
                contentDescription = null,
                tint = if (isDark) Color.LightGray else Color.DarkGray,
                modifier = Modifier.size(20.dp).alpha(0.7f),
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = if (isDark) Color.LightGray else Color.DarkGray,
            )
        }
    }
}

@Composable
private fun CurrentTemplateCard(
    uiState: MainUiState,
    isDark: Boolean,
    onOpenTemplates: () -> Unit,
    onSelectTemplate: (String) -> Unit,
) {
    val template = uiState.selectedTemplate
    val resolution = template?.let {
        "${it.outputWidth.takeIf { width -> width > 0 } ?: it.screenRect.width} × ${it.outputHeight.takeIf { height -> height > 0 } ?: it.screenRect.height}"
    } ?: "暂无模板"

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(15.dp, RoundedCornerShape(40.dp), spotColor = Color.Black.copy(alpha = if (isDark) 0.16f else 0.05f))
            .clip(RoundedCornerShape(40.dp))
            .background(if (isDark) Color(0xFF1C1C1E) else Color.White)
            .clickable {
                if (template != null) {
                    onSelectTemplate(template.id)
                } else {
                    onOpenTemplates()
                }
            }
            .padding(28.dp),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.Top,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = template?.name ?: "暂无模板",
                        fontSize = 24.sp,
                        lineHeight = 28.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isDark) Color.White else Color.Black,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                        Badge(if (template?.isBuiltIn == false) "用户定制" else "内置模板", Color(0xFF007AFF))
                        Badge(resolution, Color.Gray)
                    }
                }
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(Color(0xFF007AFF).copy(alpha = 0.10f), RoundedCornerShape(16.dp)),
                    contentAlignment = Alignment.Center,
                ) {
                    AppIcon(
                        icon = AppIconId.Template,
                        contentDescription = null,
                        tint = Color(0xFF007AFF),
                        modifier = Modifier.size(28.dp),
                    )
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            if (template != null) {
                TemplatePreviewThumbnail(
                    previewPath = template.previewAsset,
                    contentDescription = template.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    cornerRadius = 0.dp,
                    imagePadding = 0.dp,
                )
            }
        }
    }
}

@Composable
private fun RecentLogCard(
    uiState: MainUiState,
    isDark: Boolean,
) {
    val result = uiState.lastProcessingResult
    val status = result?.status
    val color = when (status) {
        ProcessingStatus.Success -> Color(0xFF10B981)
        ProcessingStatus.Failed -> Color(0xFFFF3B30)
        ProcessingStatus.Skipped -> Color(0xFFFF9500)
        null -> Color.Gray
    }
    val statusText = when (status) {
        ProcessingStatus.Success -> "SUCCESS"
        ProcessingStatus.Failed -> "FAILED"
        ProcessingStatus.Skipped -> "SKIPPED"
        null -> "EMPTY"
    }
    val infiniteTransition = rememberInfiniteTransition(label = "recent-log-pulse")
    val pulseAlpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0.4f,
        animationSpec = infiniteRepeatable(tween(1000), RepeatMode.Reverse),
        label = "recent-log-pulse-alpha",
    )

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .shadow(15.dp, RoundedCornerShape(40.dp), spotColor = Color.Black.copy(alpha = if (isDark) 0.16f else 0.05f))
            .clip(RoundedCornerShape(40.dp))
            .background(if (isDark) Color(0xFF1C1C1E) else Color.White)
            .padding(28.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = "最近处理记录",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Gray,
                    letterSpacing = 1.sp,
                )
                Row(
                    modifier = Modifier
                        .background(color.copy(alpha = 0.10f), CircleShape)
                        .border(1.dp, color.copy(alpha = 0.20f), CircleShape)
                        .padding(horizontal = 12.dp, vertical = 4.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(modifier = Modifier.size(8.dp).alpha(pulseAlpha).background(color, CircleShape))
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(statusText, color = color, fontSize = 10.sp, fontWeight = FontWeight.Black)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            LogDetailRow("触发来源", if (result == null) "暂无记录" else "file_observer", isDark)
            LogDetailRow("原图处理", if (uiState.settings.autoDeleteOriginal) "已删除原图" else "保留原图", isDark)
            result?.processedAtMillis?.let {
                LogDetailRow("处理时间", TimeUtils.formatTimestamp(it), isDark)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(if (isDark) Color.Black.copy(alpha = 0.4f) else Color(0xFFF9F9FB), RoundedCornerShape(16.dp))
                    .padding(12.dp),
            ) {
                Text(
                    text = result?.sourcePath?.substringAfterLast('/') ?: "等待下一次截图处理",
                    fontSize = 12.sp,
                    color = Color.Gray,
                    fontFamily = FontFamily.Monospace,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun LogDetailRow(label: String, value: String, isDark: Boolean) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(label, fontSize = 14.sp, color = Color.Gray, fontWeight = FontWeight.Medium)
        Text(value, fontSize = 14.sp, color = if (isDark) Color.White else Color.Black, fontWeight = FontWeight.Bold)
    }
}

@Composable
private fun Badge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .background(color.copy(alpha = 0.10f), RoundedCornerShape(8.dp))
            .border(1.dp, color.copy(alpha = 0.20f), RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Text(text, color = color, fontSize = 11.sp, fontWeight = FontWeight.Bold)
    }
}
