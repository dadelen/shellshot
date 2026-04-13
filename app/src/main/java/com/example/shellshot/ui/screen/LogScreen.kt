package com.example.shellshot.ui.screen

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.withFrameNanos
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.data.LogEntry
import com.example.shellshot.data.LogLevel
import com.example.shellshot.ui.components.ZipBadge
import com.example.shellshot.ui.components.ZipCountPill
import com.example.shellshot.ui.components.ZipGlassCard
import com.example.shellshot.ui.components.ZipSectionDivider
import com.example.shellshot.ui.components.ZipStaggeredReveal
import com.example.shellshot.utils.TimeUtils

@Composable
fun LogScreen(
    modifier: Modifier = Modifier,
    logs: List<LogEntry>,
) {
    val darkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val titleColor = if (darkTheme) Color.White else Color(0xFF111827)
    val emptyColor = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280)
    val errorCount = logs.count { it.level == LogLevel.Error }

    val initialAnimatedLogOrder = remember {
        logs.mapIndexed { index, entry -> entry.stableKey() to index }.toMap()
    }
    val revealedLogKeys = remember {
        mutableStateMapOf<String, Boolean>().apply {
            initialAnimatedLogOrder.keys.forEach { key -> put(key, true) }
        }
    }
    val currentKeys = logs.map { it.stableKey() }

    LaunchedEffect(currentKeys) {
        val currentKeySet = currentKeys.toSet()
        revealedLogKeys.keys
            .toList()
            .filterNot(currentKeySet::contains)
            .forEach(revealedLogKeys::remove)
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        contentPadding = PaddingValues(top = 56.dp, bottom = 92.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
    ) {
        item {
            ZipStaggeredReveal(index = 0) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom,
                ) {
                    Text(
                        text = "运行日志",
                        fontSize = 30.sp,
                        lineHeight = 34.sp,
                        fontWeight = FontWeight.Bold,
                        color = titleColor,
                    )
                    ZipCountPill(
                        text = "共 ${logs.size} 条",
                        dotColor = if (errorCount > 0) Color(0xFFEF4444) else Color(0xFF3B82F6),
                        darkTheme = darkTheme,
                    )
                }
            }
        }

        if (logs.isEmpty()) {
            item {
                ZipStaggeredReveal(index = 1) {
                    ZipGlassCard(
                        darkTheme = darkTheme,
                        modifier = Modifier.fillMaxWidth(),
                        cornerRadius = 32.dp,
                    ) {
                        Text(
                            text = "当前还没有新的运行记录。",
                            fontSize = 14.sp,
                            lineHeight = 20.sp,
                            color = emptyColor,
                        )
                    }
                }
            }
        } else {
            items(logs, key = { it.stableKey() }) { log ->
                val logKey = log.stableKey()
                val initialIndex = initialAnimatedLogOrder[logKey]
                when {
                    initialIndex != null -> {
                        ZipStaggeredReveal(index = initialIndex + 1) {
                            ZipLogCard(log = log, darkTheme = darkTheme)
                        }
                    }

                    else -> {
                        ZipLogAppendReveal(
                            logKey = logKey,
                            shouldAnimate = revealedLogKeys[logKey] != true,
                            onAnimationHandled = { revealedLogKeys[logKey] = true },
                        ) {
                            ZipLogCard(log = log, darkTheme = darkTheme)
                        }
                    }
                }
            }
        }
    }
}

private fun LogEntry.stableKey(): String = "$timestampMillis-$tag-$message"

private data class LogPresentation(
    val module: String,
    val title: String,
    val detail: String?,
    val badgeLabel: String,
    val accentColor: Color,
    val badgeBackground: Color,
    val badgeText: Color,
)

private fun LogEntry.toPresentation(): LogPresentation {
    val lines = message
        .lineSequence()
        .map { it.trim() }
        .filter { it.isNotEmpty() }
        .toList()

    val title = normalizeLogText(lines.firstOrNull().orEmpty())
        .ifBlank { normalizeLogText(message) }
        .ifBlank { defaultTitle(level) }

    val detail = lines
        .drop(1)
        .map(::normalizeLogText)
        .firstOrNull { it.isNotBlank() }
        ?.take(72)

    val module = normalizeModuleName(tag)

    return when (level) {
        LogLevel.Debug -> LogPresentation(
            module = module,
            title = title,
            detail = detail,
            badgeLabel = "信息",
            accentColor = Color(0xFF3B82F6),
            badgeBackground = Color(0xFFEFF5FF),
            badgeText = Color(0xFF3B82F6),
        )

        LogLevel.Error -> LogPresentation(
            module = module,
            title = title,
            detail = detail,
            badgeLabel = "错误",
            accentColor = Color(0xFFEF4444),
            badgeBackground = Color(0xFFFFECEB),
            badgeText = Color(0xFFDC2626),
        )
    }
}

private fun defaultTitle(level: LogLevel): String = when (level) {
    LogLevel.Debug -> "状态已更新"
    LogLevel.Error -> "处理出现异常"
}

private fun normalizeModuleName(source: String): String = when (source.substringAfterLast('.')) {
    "AutoShellForegroundService" -> "AUTOSHELLSERVICE"
    "ScreenshotProcessor" -> "SCREENSHOTPROCESSOR"
    "TemplateRepository" -> "TEMPLATEREPOSITORY"
    "MainViewModel" -> "MAINVIEWMODEL"
    "QueuedScreenshotWorker" -> "QUEUEDWORKER"
    "ScreenState" -> "SCREENSTATE"
    else -> source.substringAfterLast('.').uppercase()
}

private fun normalizeLogText(source: String): String {
    return source
        .replace("_", " ")
        .replace("Skip self restart", "跳过自恢复")
        .replace("Screenshot pipeline cancelled", "截图处理已取消")
        .replace("Service destroyed", "服务已销毁")
        .replace("Idle polling cancelled", "空闲轮询已取消")
        .replace("FileNotFoundException", "文件未找到")
        .replace("IOException", "读写异常")
        .replace("IllegalStateException", "状态异常")
        .replace("NullPointerException", "空指针异常")
        .replace(Regex("\\s+"), " ")
        .trim()
}

@Composable
private fun ZipLogAppendReveal(
    logKey: String,
    shouldAnimate: Boolean,
    modifier: Modifier = Modifier,
    onAnimationHandled: () -> Unit,
    content: @Composable () -> Unit,
) {
    var entered by remember(logKey) { mutableStateOf(!shouldAnimate) }
    var animationHandled by remember(logKey) { mutableStateOf(false) }

    LaunchedEffect(logKey, shouldAnimate) {
        if (shouldAnimate && !animationHandled) {
            withFrameNanos { }
            entered = true
            animationHandled = true
            onAnimationHandled()
        } else if (!entered) {
            entered = true
        }
    }

    val translationY by animateFloatAsState(
        targetValue = if (entered) 0f else 14f,
        animationSpec = spring(stiffness = 420f, dampingRatio = 0.8f),
        label = "log-append-translation",
    )
    val scale by animateFloatAsState(
        targetValue = if (entered) 1f else 0.96f,
        animationSpec = spring(stiffness = 420f, dampingRatio = 0.82f),
        label = "log-append-scale",
    )
    val alpha by animateFloatAsState(
        targetValue = if (entered) 1f else 0f,
        animationSpec = tween(durationMillis = 260, easing = LinearOutSlowInEasing),
        label = "log-append-alpha",
    )

    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                this.translationY = translationY
                this.scaleX = scale
                this.scaleY = scale
                this.alpha = alpha
            },
    ) {
        content()
    }
}

@Composable
private fun ZipLogCard(
    log: LogEntry,
    darkTheme: Boolean,
) {
    val presentation = remember(log) { log.toPresentation() }

    ZipGlassCard(
        darkTheme = darkTheme,
        modifier = Modifier.fillMaxWidth(),
        cornerRadius = 32.dp,
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 22.dp),
    ) {
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
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .background(presentation.accentColor, RoundedCornerShape(999.dp)),
                    )
                    Text(
                        text = presentation.module,
                        fontSize = 14.sp,
                        letterSpacing = 0.8.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = if (darkTheme) Color(0xFFD4D4D8) else Color(0xFF9CA3AF),
                    )
                }
                ZipBadge(
                    text = presentation.badgeLabel,
                    backgroundColor = presentation.badgeBackground,
                    textColor = presentation.badgeText,
                )
            }

            ZipSectionDivider(
                darkTheme = darkTheme,
                startIndent = 0.dp,
            )

            Text(
                text = presentation.title,
                fontSize = 16.sp,
                lineHeight = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = if (darkTheme) Color(0xFFF4F4F5) else Color(0xFF111827),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )

            if (!presentation.detail.isNullOrBlank()) {
                Text(
                    text = presentation.detail,
                    fontSize = 13.sp,
                    lineHeight = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }

            ZipSectionDivider(
                darkTheme = darkTheme,
                startIndent = 0.dp,
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier
                    .background(
                        if (darkTheme) Color.White.copy(alpha = 0.06f) else Color.White.copy(alpha = 0.72f),
                        RoundedCornerShape(999.dp),
                    )
                    .border(
                        1.dp,
                        if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFE5E7EB),
                        RoundedCornerShape(999.dp),
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
            ) {
                Icon(
                    imageVector = Icons.Outlined.Schedule,
                    contentDescription = null,
                    tint = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF9CA3AF),
                    modifier = Modifier.size(14.dp),
                )
                Text(
                    text = TimeUtils.formatTimestamp(log.timestampMillis).substringAfterLast(' '),
                    fontSize = 12.sp,
                    color = if (darkTheme) Color(0xFFA1A1AA) else Color(0xFF6B7280),
                )
            }
        }
    }
}
