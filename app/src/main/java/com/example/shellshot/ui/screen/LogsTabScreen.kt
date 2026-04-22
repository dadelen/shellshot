package com.example.shellshot.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.data.LogEntry
import com.example.shellshot.data.LogLevel
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.CountCapsule
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.theme.shellShotTokens
import com.example.shellshot.utils.TimeUtils
import dev.chrisbanes.haze.HazeState

@Composable
fun LogsTabScreen(
    modifier: Modifier = Modifier,
    logs: List<LogEntry>,
    isDark: Boolean,
    hazeState: HazeState,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val visibleLogs = remember(logs) {
        logs
            .filterNot(::isUiNoiseLogForTab)
            .sortedByDescending { it.timestampMillis }
            .take(60)
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .padding(horizontal = 24.dp, vertical = 16.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 28.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text("运行日志", fontSize = 30.sp, fontWeight = FontWeight.Bold, color = colors.textPrimary)
            CountCapsule(text = "共 ${visibleLogs.size} 条", isDark = isDark)
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 144.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(
                items = visibleLogs,
                key = { "${it.timestampMillis}-${it.tag}-${it.message}" },
            ) { log ->
                AnimatedVisibility(
                    visible = true,
                    enter = fadeIn() + slideInVertically(initialOffsetY = { it / 5 }),
                    exit = fadeOut() + slideOutVertically(targetOffsetY = { -it / 6 }),
                ) {
                    LogCard(
                        log = log,
                        isDark = isDark,
                        modifier = Modifier
                            .animateItem()
                            .blur(0.dp),
                    )
                }
            }
        }
    }
}

@Composable
private fun LogCard(
    log: LogEntry,
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val badgeColor = when {
        log.message.contains("失败", ignoreCase = true) || log.level == LogLevel.Error -> Color(0xFFFEF3C7)
        log.message.contains("成功", ignoreCase = true) -> Color(0xFFECFDF5)
        else -> Color(0xFFEFF6FF)
    }
    val badgeText = when {
        log.message.contains("失败", ignoreCase = true) || log.level == LogLevel.Error -> Color(0xFFD97706)
        log.message.contains("成功", ignoreCase = true) -> Color(0xFF059669)
        else -> Color(0xFF2563EB)
    }
    val dotColor = when {
        log.message.contains("失败", ignoreCase = true) || log.level == LogLevel.Error -> Color(0xFFF59E0B)
        log.message.contains("成功", ignoreCase = true) -> Color(0xFF10B981)
        else -> Color(0xFF3B82F6)
    }
    GlassSurfaceCard(
        modifier = modifier.fillMaxWidth(),
        isDark = isDark,
        cornerRadius = 32,
        padding = 24,
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .background(dotColor, CircleShape),
                    )
                    Text(
                        text = log.tag.substringAfterLast('.').uppercase(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.textSecondary,
                        letterSpacing = 1.5.sp,
                    )
                }
                Box(
                    modifier = Modifier
                        .background(badgeColor, RoundedCornerShape(8.dp))
                        .padding(horizontal = 10.dp, vertical = 3.dp),
                ) {
                    Text(logTypeLabelForCard(log), fontSize = 10.sp, fontWeight = FontWeight.Bold, color = badgeText)
                }
            }
            Text(
                text = compactLogTitleForTab(log),
                modifier = Modifier.padding(top = 16.dp),
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.textPrimary,
                lineHeight = 22.sp,
            )
            compactLogDetailForTab(log)?.let { detail ->
                Text(
                    text = detail,
                    modifier = Modifier.padding(top = 10.dp),
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    color = colors.textSecondary,
                )
            }
            Row(
                modifier = Modifier
                    .padding(top = 18.dp)
                    .background(colors.subtleFill, RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 7.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                AppIcon(icon = AppIconId.Terminal, contentDescription = null, tint = colors.textSecondary, modifier = Modifier.size(14.dp))
                Text(
                    text = TimeUtils.formatTimestamp(log.timestampMillis).substringAfterLast(' '),
                    modifier = Modifier.padding(start = 6.dp),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textSecondary,
                    fontFamily = FontFamily.Monospace,
                )
            }
        }
    }
}

private fun isUiNoiseLogForTab(log: LogEntry): Boolean {
    if (log.level == LogLevel.Error) return false
    val normalizedTag = log.tag.substringAfterLast('.')
    return normalizedTag.equals("ScreenState", ignoreCase = true) ||
        listOf(
            "cleanup:onDestroy",
            "Idle polling cancelled",
            "Screen state listener stopped",
            "屏幕状态监听已停止",
            "服务已销毁 explicitStop=true reason=onDestroy",
            "Skip self restart",
        ).any { fragment -> log.message.contains(fragment, ignoreCase = true) }
}

private fun logTypeLabelForCard(log: LogEntry): String = when {
    log.message.contains("失败", ignoreCase = true) -> "警告"
    log.message.contains("成功", ignoreCase = true) -> "成功"
    else -> "信息"
}

private fun compactLogTitleForTab(log: LogEntry): String {
    val message = log.message
    return message
        .substringBefore(" reason=", message)
        .substringBefore(" path=", message)
        .substringBefore(" output=", message)
        .substringBefore(" taskPath=", message)
        .substringBefore(" candidatePath=", message)
        .substringBefore(" explicitStop=", message)
        .substringBefore(" thread=", message)
        .trim()
}

private fun compactLogDetailForTab(log: LogEntry): String? {
    val lines = log.message.lineSequence().map { it.trim() }.filter { it.isNotBlank() }.toList()
    if (lines.size <= 1) return null
    return lines.drop(1).joinToString(" ")
}
