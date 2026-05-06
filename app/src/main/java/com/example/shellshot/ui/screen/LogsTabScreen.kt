package com.example.shellshot.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.data.LogEntry
import com.example.shellshot.data.LogLevel
import com.example.shellshot.data.ProcessingResult
import com.example.shellshot.data.ProcessingStatus
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.designsystem.ioslike.IOSFlyInContainer
import com.example.shellshot.ui.theme.shellShotTokens
import com.example.shellshot.utils.TimeUtils
import dev.chrisbanes.haze.HazeState
import java.io.File

@Composable
fun LogsTabScreen(
    modifier: Modifier = Modifier,
    logs: List<LogEntry>,
    processingHistory: List<ProcessingResult>,
    isDark: Boolean,
    hazeState: HazeState,
) {
    val colors = MaterialTheme.shellShotTokens.colors
	    val history = remember(processingHistory) {
	        processingHistory
	            .filterNot(::isTransientCandidateResult)
	            .sortedByDescending { it.processedAtMillis }
	            .take(30)
	    }
    val problemLogs = remember(logs) {
        logs
            .filter(::isProblemLogForTab)
            .sortedByDescending { it.timestampMillis }
            .take(30)
    }
    val successCount = history.count { it.status == ProcessingStatus.Success }
    val failedCount = history.count { it.status == ProcessingStatus.Failed }

    IOSFlyInContainer(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .windowInsetsPadding(WindowInsets.statusBars)
                .padding(horizontal = 24.dp, vertical = 16.dp),
        ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "日志",
                fontSize = 30.sp,
                fontWeight = FontWeight.Black,
                color = colors.textPrimary,
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SummaryChip(
                label = "成功",
                value = successCount.toString(),
                color = Color(0xFF10B981),
                modifier = Modifier.weight(1f),
            )
            SummaryChip(
                label = "失败",
                value = failedCount.toString(),
                color = Color(0xFFEF4444),
                modifier = Modifier.weight(1f),
            )
            SummaryChip(
                label = "问题",
                value = problemLogs.size.toString(),
                color = Color(0xFFF59E0B),
                modifier = Modifier.weight(1f),
            )
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(bottom = 144.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            item(key = "history-section") {
                SectionTitle(
                    title = "截图处理",
                )
            }

            if (history.isEmpty()) {
                item(key = "history-empty") {
                    EmptyCard(
                        isDark = isDark,
                        title = "暂无处理记录",
                    )
                }
            } else {
                itemsIndexed(
                    items = history,
                    key = { _, result -> "${result.processedAtMillis}-${result.sourcePath}-${result.status}" },
                ) { _, result ->
                    Box(modifier = Modifier.animateItem()) {
                        ProcessingHistoryCard(result = result, isDark = isDark)
                    }
                }
            }

            item(key = "problem-section") {
                SectionTitle(
                    title = "问题日志",
                    modifier = Modifier.padding(top = 12.dp),
                )
            }

            if (problemLogs.isEmpty()) {
                item(key = "problem-empty") {
                    EmptyCard(
                        isDark = isDark,
                        title = "暂无问题日志",
                    )
                }
            } else {
                itemsIndexed(
                    items = problemLogs,
                    key = { _, log -> "${log.timestampMillis}-${log.tag}-${log.message}" },
                ) { _, log ->
                    Box(modifier = Modifier.animateItem()) {
                        ProblemLogCard(log = log, isDark = isDark)
                    }
                }
            }
        }
        }
    }
}

@Composable
private fun SummaryChip(
    label: String,
    value: String,
    color: Color,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Row(
        modifier = modifier
            .height(44.dp)
            .background(colors.subtleFill, RoundedCornerShape(18.dp))
            .padding(horizontal = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(8.dp)
                .background(color, CircleShape),
        )
        Text(
            text = label,
            fontSize = 12.sp,
            fontWeight = FontWeight.SemiBold,
            color = colors.textSecondary,
            maxLines = 1,
        )
        Text(
            text = value,
            fontSize = 15.sp,
            fontWeight = FontWeight.Black,
            color = colors.textPrimary,
            maxLines = 1,
        )
    }
}

@Composable
private fun SectionTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 2.dp, vertical = 4.dp),
    ) {
        Text(
            text = title,
            fontSize = 13.sp,
            fontWeight = FontWeight.Black,
            color = colors.textPrimary,
        )
    }
}

@Composable
private fun ProcessingHistoryCard(
    result: ProcessingResult,
    isDark: Boolean,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val statusColor = when (result.status) {
        ProcessingStatus.Success -> Color(0xFF10B981)
        ProcessingStatus.Failed -> Color(0xFFEF4444)
        ProcessingStatus.Skipped -> Color(0xFFF59E0B)
    }

    GlassSurfaceCard(
        modifier = Modifier.fillMaxWidth(),
        isDark = isDark,
        cornerRadius = 24,
        padding = 16,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .background(statusColor, CircleShape),
                    )
                    Text(
                        text = processingStatusLabel(result.status),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Black,
                        color = colors.textPrimary,
                    )
                }
                Text(
                    text = TimeUtils.formatTimestamp(result.processedAtMillis).substringAfterLast(' '),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.textMuted,
                    fontFamily = FontFamily.Monospace,
                )
            }

            Text(
                text = File(result.sourcePath).name.ifBlank { "未知截图" },
                fontSize = 13.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            val detail = processingDetail(result)
            if (detail.isNotBlank()) {
                Text(
                    text = detail,
                    fontSize = 12.sp,
                    lineHeight = 17.sp,
                    color = colors.textMuted,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}

@Composable
private fun ProblemLogCard(
    log: LogEntry,
    isDark: Boolean,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    GlassSurfaceCard(
        modifier = Modifier.fillMaxWidth(),
        isDark = isDark,
        cornerRadius = 24,
        padding = 16,
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Box(
                        modifier = Modifier
                            .size(9.dp)
                            .background(problemColor(log), CircleShape),
                    )
                    Text(
                        text = log.tag.substringAfterLast('.').uppercase(),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Black,
                        color = colors.textSecondary,
                        letterSpacing = 1.sp,
                        maxLines = 1,
                    )
                }
                Text(
                    text = TimeUtils.formatTimestamp(log.timestampMillis).substringAfterLast(' '),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = colors.textMuted,
                    fontFamily = FontFamily.Monospace,
                )
            }
            Text(
                text = compactProblemTitle(log),
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = colors.textPrimary,
                lineHeight = 19.sp,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
        }
    }
}

@Composable
private fun EmptyCard(
    isDark: Boolean,
    title: String,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    GlassSurfaceCard(
        modifier = Modifier.fillMaxWidth(),
        isDark = isDark,
        cornerRadius = 24,
        padding = 18,
    ) {
        Text(
            text = title,
            fontSize = 15.sp,
            fontWeight = FontWeight.Black,
            color = colors.textPrimary,
        )
    }
}

private fun isProblemLogForTab(log: LogEntry): Boolean {
    if (log.level == LogLevel.Error) return true
    val message = log.message
    return listOf(
        "失败",
        "异常",
        "错误",
        "警告",
        "failed",
        "error",
        "exception",
        "warning",
    ).any { fragment -> message.contains(fragment, ignoreCase = true) }
}

private fun problemColor(log: LogEntry): Color = when {
    log.level == LogLevel.Error -> Color(0xFFEF4444)
    log.message.contains("警告", ignoreCase = true) || log.message.contains("warning", ignoreCase = true) -> Color(0xFFF59E0B)
    else -> Color(0xFFEF4444)
}

private fun processingStatusLabel(status: ProcessingStatus): String = when (status) {
    ProcessingStatus.Success -> "生成成功"
    ProcessingStatus.Failed -> "处理失败"
    ProcessingStatus.Skipped -> "已跳过"
}

private fun processingDetail(result: ProcessingResult): String {
    val detail = result.detailMessage.orEmpty().ifBlank {
        result.outputPath?.let { "已保存到 ${File(it).name}" }.orEmpty()
    }
    val deleteNote = result.deleteMessage?.takeIf { it.isNotBlank() }?.let { "原图：$it" }
    return listOf(detail, deleteNote.orEmpty()).filter { it.isNotBlank() }.joinToString(" · ")
}

private fun isTransientCandidateResult(result: ProcessingResult): Boolean {
    val detail = result.detailMessage.orEmpty()
    return result.status == ProcessingStatus.Skipped &&
        listOf(
            "候选截图尚未准备好",
            "候选图暂未准备好",
            "候选截图暂未准备好",
        ).any { fragment -> detail.contains(fragment, ignoreCase = true) }
}

private fun compactProblemTitle(log: LogEntry): String {
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
