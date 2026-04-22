package com.example.shellshot.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.data.ProcessingStatus
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.components.PhoneMockFrame
import com.example.shellshot.ui.components.SectionHeaderRow
import com.example.shellshot.ui.components.StaggeredReveal
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.components.noRippleClick
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import com.example.shellshot.utils.TimeUtils
import dev.chrisbanes.haze.HazeState
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeTabScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    isDark: Boolean,
    hazeState: HazeState,
    onToggleThemeQuick: () -> Unit,
    onToggleMonitoring: (Boolean) -> Unit,
    onOpenTemplates: () -> Unit,
    onSelectTemplateAndOpen: (String) -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val selectedTemplate = uiState.selectedTemplate
    val lastResult = uiState.lastProcessingResult

    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp),
    ) {
        StaggeredReveal(index = 0) {
            Box(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = "ShellShot",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Black,
                    letterSpacing = (-1).sp,
                    color = colors.textPrimary,
                )
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .background(colors.surface, androidx.compose.foundation.shape.CircleShape)
                        .noRippleClick(onToggleThemeQuick)
                        .padding(10.dp),
                    contentAlignment = Alignment.Center,
                ) {
                    AppIcon(
                        icon = if (isDark) AppIconId.Sun else AppIconId.Moon,
                        contentDescription = "切换主题",
                        tint = colors.textMuted,
                        modifier = Modifier.height(20.dp),
                    )
                }
            }
        }

        StaggeredReveal(index = 1) {
            GlassSurfaceCard(
                isDark = isDark,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = "监听服务",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = colors.textPrimary,
                    )
                    Text(
                        text = when {
                            !uiState.hasCoreStartPermissions -> "需要先完成权限授权"
                            uiState.settings.monitoringEnabled -> uiState.phaseLabel
                            else -> "点击右侧开关开始监听截图"
                        },
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium,
                        color = colors.textMuted,
                    )
                    Box(modifier = Modifier.align(Alignment.End)) {
                        com.example.shellshot.ui.components.LiquidGlassSwitch(
                            isOn = uiState.settings.monitoringEnabled && uiState.hasCoreStartPermissions,
                            onToggle = onToggleMonitoring,
                        )
                    }
                }
            }
        }

        StaggeredReveal(index = 2) {
            SectionHeaderRow(
                title = "最近作品",
                value = lastResult?.processedAtMillis?.let { TimeUtils.formatTimestamp(it).substringAfter(' ') } ?: "等待生成",
                isDark = isDark,
                modifier = Modifier.padding(horizontal = 8.dp),
            )
        }

        StaggeredReveal(index = 3) {
            GlassSurfaceCard(
                isDark = isDark,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .noRippleClick {
                        selectedTemplate?.id?.let(onSelectTemplateAndOpen) ?: onOpenTemplates()
                    },
                cornerRadius = 48,
                padding = 32,
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    PhoneMockFrame {
                        when {
                            lastResult?.outputPath != null -> OutputPreview(
                                path = lastResult.outputPath,
                                isDark = isDark,
                            )
                            
                            selectedTemplate != null -> TemplatePreviewThumbnail(
                                previewPath = selectedTemplate.previewAsset,
                                contentDescription = selectedTemplate.name,
                                modifier = Modifier.fillMaxSize(),
                                cornerRadius = 29.dp,
                            )
                            else -> Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(ShellColors.surfaceAlt(isDark)),
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Text(
                        text = selectedTemplate?.name ?: "还没有模板",
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Black,
                        color = colors.textPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = when {
                            lastResult?.status == ProcessingStatus.Success -> "最新导出已完成"
                            selectedTemplate != null -> "已准备好开始套壳"
                            else -> "去模板页选择或导入模板"
                        },
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        color = colors.textSecondary,
                    )
                }
            }
        }
    }
}

@Composable
private fun OutputPreview(
    path: String,
    isDark: Boolean,
) {
    val image by produceState<ImageBitmap?>(initialValue = null, path) {
        value = withContext(Dispatchers.IO) {
            runCatching {
                val file = File(path)
                if (file.exists()) {
                    BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap()
                } else {
                    null
                }
            }.getOrNull()
        }
    }
    if (image != null) {
        Image(
            bitmap = image!!,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop,
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ShellColors.surfaceAlt(isDark)),
        )
    }
}
