package com.example.shellshot.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.GlassSurfaceCard
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
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val selectedTemplate = uiState.selectedTemplate
    val lastResult = uiState.lastProcessingResult
    val latestOutputPath = uiState.latestOutputPreviewPath

    Column(
        modifier = modifier
            .fillMaxSize()
            .windowInsetsPadding(WindowInsets.statusBars)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
    ) {
        // 标题区域
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

        // 渐变分隔线
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp)
                .height(1.dp)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            colors.textPrimary.copy(alpha = 0.06f),
                            colors.textPrimary.copy(alpha = 0.02f),
                            Color.Transparent,
                        )
                    )
                )
        )

        // 监听服务卡片 - 增加视觉权重
        StaggeredReveal(index = 1) {
            val monitorEnabled = uiState.settings.monitoringEnabled && uiState.hasCoreStartPermissions
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .shadow(
                        elevation = 12.dp,
                        shape = RoundedCornerShape(34.dp),
                        ambientColor = colors.accentBlue.copy(alpha = 0.08f),
                        spotColor = colors.accentBlue.copy(alpha = 0.08f),
                    )
            ) {
                GlassSurfaceCard(
                    isDark = isDark,
                    modifier = Modifier.fillMaxWidth(),
                    cornerRadius = 32,
                    padding = 22,
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Text(
                                text = "监听服务",
                                modifier = Modifier.weight(1f),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Black,
                                letterSpacing = (-0.4).sp,
                                color = colors.textPrimary,
                            )
                            Box(
                                modifier = Modifier.padding(start = 16.dp),
                                contentAlignment = Alignment.Center,
                            ) {
                                com.example.shellshot.ui.components.LiquidGlassSwitch(
                                    isOn = monitorEnabled,
                                    onToggle = onToggleMonitoring,
                                )
                            }
                        }
                    }
                }
            }
        }

        // 最近作品区域 - 降低视觉权重
        StaggeredReveal(index = 2) {
            SectionHeaderRow(
                title = "最近作品",
                value = lastResult?.processedAtMillis?.let { TimeUtils.formatTimestamp(it).substringAfter(' ') } ?: "",
                isDark = isDark,
                modifier = Modifier.padding(horizontal = 4.dp, vertical = 4.dp),
            )
        }

        // 作品预览卡片 - 增加视觉焦点
        StaggeredReveal(index = 3) {
            GlassSurfaceCard(
                isDark = isDark,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1f),
                    cornerRadius = 32,
	                padding = 32,
	            ) {
	                Box(
	                    modifier = Modifier.fillMaxSize(),
	                ) {
	                    when {
	                        latestOutputPath != null -> OutputPreview(
	                            path = latestOutputPath!!,
	                            isDark = isDark,
	                        )

	                        selectedTemplate != null -> TemplatePreviewThumbnail(
	                            previewPath = selectedTemplate.displayPreviewAsset,
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
	                    // 渐变遮罩引导视线
	                    Box(
	                        modifier = Modifier
	                            .fillMaxSize()
	                            .background(
	                                Brush.verticalGradient(
	                                    colors = listOf(
	                                        Color.Transparent,
	                                        colors.surface.copy(alpha = 0.22f),
	                                    ),
	                                    startY = 0.65f,
	                                )
	                            )
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
    val image = remember(path) {
        mutableStateOf<ImageBitmap?>(null)
    }
    LaunchedEffect(path) {
        image.value = withContext(Dispatchers.IO) {
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
    val currentImage = image.value
    if (currentImage != null) {
        Image(
            bitmap = currentImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Fit,
        )
    } else {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(ShellColors.surfaceAlt(isDark)),
        )
    }
}
