package com.example.shellshot.ui.screen

import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.data.LogEntry
import com.example.shellshot.data.LogLevel
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.ZipStaggeredReveal
import com.example.shellshot.utils.TimeUtils
import kotlinx.coroutines.delay

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun LogScreen(
    modifier: Modifier = Modifier,
    logs: List<LogEntry>,
) {
    val darkTheme = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val listState = rememberLazyListState()
    val sourceLogs = remember(logs) {
        logs
            .asSequence()
            .filterNot(::isUiNoiseLog)
            .sortedByDescending { it.timestampMillis }
            .take(DISPLAY_LOG_LIMIT)
            .toList()
    }
    var displayedLogs by remember { mutableStateOf<List<LogEntry>>(emptyList()) }
    var animatedNewKeys by remember { mutableStateOf<Set<String>>(emptySet()) }
    val sourceSignature = remember(sourceLogs) {
        sourceLogs.joinToString(separator = "|") { it.stableKey() }
    }

    LaunchedEffect(sourceSignature) {
        if (displayedLogs.isEmpty()) {
            displayedLogs = sourceLogs
            animatedNewKeys = emptySet()
            return@LaunchedEffect
        }

        val sourceKeys = sourceLogs.map { it.stableKey() }.toSet()
        displayedLogs = displayedLogs.filter { it.stableKey() in sourceKeys }

        val displayedKeys = displayedLogs.map { it.stableKey() }.toSet()
        val missingLogs = sourceLogs.filter { it.stableKey() !in displayedKeys }

        if (missingLogs.isEmpty()) {
            displayedLogs = sourceLogs
            animatedNewKeys = emptySet()
            return@LaunchedEffect
        }

        missingLogs.asReversed().forEach { newLog ->
            val newKey = newLog.stableKey()
            if (displayedLogs.any { it.stableKey() == newKey }) {
                return@forEach
            }

            animatedNewKeys = setOf(newKey)
            displayedLogs = (listOf(newLog) + displayedLogs.filter { it.stableKey() in sourceKeys })
                .distinctBy { it.stableKey() }
                .sortedByDescending { it.timestampMillis }
                .take(DISPLAY_LOG_LIMIT)
            delay(LOG_INSERT_INTERVAL_MS)
        }

        displayedLogs = sourceLogs
        animatedNewKeys = emptySet()
    }

    LazyColumn(
        state = listState,
        modifier = modifier
            .fillMaxSize()
            .background(if (darkTheme) Color(0xFF050507) else Color(0xFFF5F6F8))
            .padding(horizontal = 18.dp),
        contentPadding = PaddingValues(top = 64.dp, bottom = 132.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        item {
            ZipStaggeredReveal(index = 0) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 8.dp, end = 8.dp, bottom = 24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    AppIcon(
                        icon = AppIconId.Terminal,
                        contentDescription = null,
                        tint = if (darkTheme) Color.White else Color.Black,
                        modifier = Modifier.size(28.dp),
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                    Text(
                        text = "系统日志",
                        fontSize = 32.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (darkTheme) Color.White else Color.Black,
                        modifier = Modifier.weight(1f),
                    )
                    Text(
                        text = "共 ${sourceLogs.size} 条",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray,
                    )
                }
            }
        }

        if (displayedLogs.isEmpty()) {
            item {
                ZipStaggeredReveal(index = 1) {
                    EmptyLogCard(darkTheme = darkTheme)
                }
            }
        } else {
            items(displayedLogs, key = { it.stableKey() }) { log ->
                val shouldAnimate = log.stableKey() in animatedNewKeys
                val itemModifier = Modifier.animateItem(placementSpec = logInsertSpring())
                if (shouldAnimate) {
                    AnimatedLogItem(
                        logKey = log.stableKey(),
                        modifier = itemModifier,
                    ) {
                        LogCard(log = log, darkTheme = darkTheme)
                    }
                } else {
                    Box(modifier = itemModifier.fillMaxWidth()) {
                        LogCard(log = log, darkTheme = darkTheme)
                    }
                }
            }
        }
    }
}

@Composable
private fun AnimatedLogItem(
    logKey: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var appeared by remember(logKey) { mutableStateOf(false) }

    LaunchedEffect(logKey) {
        appeared = true
    }

    val transition = updateTransition(targetState = appeared, label = "log-insert-$logKey")
    val offsetY by transition.animateDp(
        transitionSpec = { logInsertSpring() },
        label = "log-insert-y",
    ) { visible -> if (visible) 0.dp else 20.dp }
    val scale by transition.animateFloat(
        transitionSpec = { logInsertSpring() },
        label = "log-insert-scale",
    ) { visible -> if (visible) 1f else 0.985f }
    val alpha by transition.animateFloat(
        transitionSpec = { spring(stiffness = 520f, dampingRatio = 0.9f) },
        label = "log-insert-alpha",
    ) { visible -> if (visible) 1f else 0f }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer {
                translationY = offsetY.toPx()
                scaleX = scale
                scaleY = scale
                this.alpha = alpha
            }
    ) {
        content()
    }
}

private const val LOG_INSERT_STIFFNESS = 520f
private const val LOG_INSERT_DAMPING_RATIO = 0.90f
private const val DISPLAY_LOG_LIMIT = 60
private const val LOG_INSERT_INTERVAL_MS = 850L

private fun <T> logInsertSpring() = spring<T>(
    stiffness = LOG_INSERT_STIFFNESS,
    dampingRatio = LOG_INSERT_DAMPING_RATIO,
)

@Composable
private fun LogCard(
    log: LogEntry,
    darkTheme: Boolean,
) {
    val normalizedForType = normalizeLogText(log.message)
    val typeLabel = logTypeLabel(normalizedForType, log.level)
    val color = logTypeColor(typeLabel)
    val messageLines = log.message.lineSequence().map { it.trim() }.filter { it.isNotBlank() }.toList()
    val normalizedMessage = normalizeLogText(messageLines.firstOrNull().orEmpty())
    val title = compactLogTitle(normalizedMessage).ifBlank { typeLabel }
    val detail = compactLogDetail(normalizedMessage, messageLines.drop(1), log.level)
    val shape = RoundedCornerShape(24.dp)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(top = 18.dp),
        ) {
            Box(modifier = Modifier.size(9.dp).background(color, CircleShape))
            Box(
                modifier = Modifier
                    .width(1.dp)
                    .padding(top = 4.dp)
                    .size(width = 1.dp, height = 86.dp)
                    .background(if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFDDE1E8)),
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .shadow(6.dp, shape, spotColor = Color.Black.copy(alpha = if (darkTheme) 0.26f else 0.05f))
                .clip(shape)
                .background(if (darkTheme) Color(0xFF17181C) else Color.White)
                .border(
                    1.dp,
                    if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFE5E7EB).copy(alpha = 0.62f),
                    shape,
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = normalizeModuleName(log.tag),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = if (darkTheme) Color.White else Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.weight(1f),
                )
                Text(
                    text = TimeUtils.formatTimestamp(log.timestampMillis).substringAfterLast(' '),
                    fontSize = 12.sp,
                    color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray,
                )
            }
            Text(
                text = title,
                fontSize = 15.sp,
                lineHeight = 22.sp,
                color = if (darkTheme) Color(0xFFD4D4D8) else Color(0xFF333333),
                fontWeight = FontWeight.Medium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            if (!detail.isNullOrBlank()) {
                Text(
                    text = normalizeLogText(detail),
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily.Monospace,
                )
            }
            Text(
                text = typeLabel,
                fontSize = 11.sp,
                fontWeight = FontWeight.Bold,
                color = color,
                modifier = Modifier
                    .background(color.copy(alpha = 0.10f), RoundedCornerShape(8.dp))
                    .border(1.dp, color.copy(alpha = 0.20f), RoundedCornerShape(8.dp))
                    .padding(horizontal = 12.dp, vertical = 4.dp),
            )
        }
    }
}

private fun logTypeLabel(message: String, level: LogLevel): String = when {
    message.contains("删除", ignoreCase = true) && message.contains("失败", ignoreCase = true) -> "删除失败"
    message.contains("合成失败", ignoreCase = true) || message.contains("ComposeFailed", ignoreCase = true) -> "合成失败"
    message.contains("保存失败", ignoreCase = true) || message.contains("SaveFailed", ignoreCase = true) -> "保存失败"
    message.contains("跳过", ignoreCase = true) || message.contains("Skipped", ignoreCase = true) -> "跳过"
    message.contains("成功", ignoreCase = true) || message.contains("Success", ignoreCase = true) -> "成功"
    level == LogLevel.Error -> "错误"
    else -> "信息"
}

private fun logTypeColor(label: String): Color = when (label) {
    "成功" -> Color(0xFF34C759)
    "跳过" -> Color(0xFFFFB340)
    "删除失败", "合成失败", "保存失败", "错误" -> Color(0xFFFF453A)
    else -> Color(0xFF007AFF)
}

@Composable
private fun EmptyLogCard(darkTheme: Boolean) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(40.dp))
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White)
            .border(
                1.dp,
                if (darkTheme) Color.White.copy(alpha = 0.08f) else Color(0xFFE5E5EA),
                RoundedCornerShape(40.dp),
            )
            .padding(20.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text("暂无运行日志", color = if (darkTheme) Color(0xFFA1A1AA) else Color.Gray)
    }
}

private fun LogEntry.stableKey(): String = "$timestampMillis-$tag-$message"

private fun isUiNoiseLog(log: LogEntry): Boolean {
    if (log.level == LogLevel.Error) {
        return false
    }
    val normalizedTag = log.tag.substringAfterLast('.')
    val message = log.message
    return normalizedTag.equals("ScreenState", ignoreCase = true) ||
        uiNoiseFragments.any { fragment -> message.contains(fragment, ignoreCase = true) }
}

private val uiNoiseFragments = listOf(
    "cleanup:onDestroy",
    "Idle polling cancelled",
    "Screen state listener stopped",
    "屏幕状态监听已停止",
    "服务已销毁 explicitStop=true reason=onDestroy",
    "Skip self restart",
)

private fun normalizeModuleName(source: String): String = when (source.substringAfterLast('.')) {
    "AutoShellForegroundService" -> "AutoShellService"
    "ScreenshotProcessor" -> "ScreenshotProcessor"
    "TemplateRepository" -> "TemplateRepository"
    "MainViewModel" -> "MainViewModel"
    "QueuedScreenshotWorker" -> "QueuedWorker"
    "ScreenState" -> "ScreenState"
    else -> source.substringAfterLast('.')
}

private fun compactLogTitle(source: String): String {
    if (source.startsWith("套壳成功", ignoreCase = true)) return "套壳成功"
    if (source.startsWith("任务终态失败", ignoreCase = true)) return "处理失败"
    if (source.startsWith("任务待重试", ignoreCase = true)) return "等待重试"
    if (source.startsWith("任务跳过", ignoreCase = true)) return "已跳过"
    if (source.startsWith("合成后验警告", ignoreCase = true)) return "合成检查提醒"

    return source
        .substringBefore(" reason=", source)
        .substringBefore(" path=", source)
        .substringBefore(" output=", source)
        .substringBefore(" taskPath=", source)
        .substringBefore(" candidatePath=", source)
        .substringBefore(" explicitStop=", source)
        .substringBefore(" thread=", source)
        .substringBefore(" at com.example", source)
        .replace("cleanup:onDestroy", "退出清理")
        .replace("cleanup:stop:user stop", "用户停止")
        .trim()
}

private fun compactLogDetail(
    normalizedFirstLine: String,
    extraLines: List<String>,
    level: LogLevel,
): String? {
    val inlineDetail = listOf("reason=", "basis=", "deleteOriginal=", "explicitStop=", "thread=")
        .firstNotNullOfOrNull { key ->
            normalizedFirstLine.substringAfter(key, missingDelimiterValue = "").takeIf { it.isNotBlank() }?.let {
                "$key$it"
            }
        }
    val multilineDetail = extraLines.joinToString(" ") { normalizeLogText(it) }.trim()
    return when {
        multilineDetail.isNotBlank() -> multilineDetail
        normalizedFirstLine.startsWith("套壳成功", ignoreCase = true) -> null
        level == LogLevel.Error && inlineDetail != null -> inlineDetail
        inlineDetail != null && normalizedFirstLine.startsWith("任务", ignoreCase = true) -> inlineDetail
        level == LogLevel.Error && normalizedFirstLine.length > 48 -> normalizedFirstLine
        else -> null
    }
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
