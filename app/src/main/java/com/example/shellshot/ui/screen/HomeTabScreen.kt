package com.example.shellshot.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.ui.MainUiState
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.LiquidGlassSwitch
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.designsystem.ioslike.IOSColors
import com.example.shellshot.ui.designsystem.ioslike.IOSGroupCard
import com.example.shellshot.ui.designsystem.ioslike.IOSPage
import com.example.shellshot.ui.designsystem.ioslike.IOSPillIconButton
import com.example.shellshot.ui.designsystem.ioslike.IOSSpacing
import com.example.shellshot.ui.designsystem.ioslike.IOSTypography
import com.example.shellshot.ui.designsystem.ioslike.LiteLiquidGlassPanel
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun HomeTabScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    isDark: Boolean,
    onToggleThemeQuick: () -> Unit,
    onToggleMonitoring: (Boolean) -> Unit,
) {
    val colors = IOSColors.scheme(isDark)
    val monitorEnabled = uiState.settings.monitoringEnabled && uiState.hasCoreStartPermissions

    IOSPage(
        modifier = modifier,
        title = "Auto Shell",
        isDark = isDark,
        contentPadding = PaddingValues(horizontal = IOSSpacing.Default.pageHorizontal, vertical = 18.dp),
        topBarTrailing = {
            IOSPillIconButton(
                isDark = isDark,
                onClick = onToggleThemeQuick,
            ) {
                AppIcon(
                    icon = if (isDark) AppIconId.Sun else AppIconId.Moon,
                    contentDescription = "切换主题",
                    tint = colors.secondaryText,
                    modifier = Modifier.size(20.dp),
                )
            }
        },
    ) {
        IOSGroupCard(
            isDark = isDark,
            padding = PaddingValues(horizontal = 18.dp, vertical = 14.dp),
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterEnd,
            ) {
                Text(
                    text = if (monitorEnabled) "自动监听" else "已关闭",
                    style = IOSTypography.Default.cardTitle,
                    color = colors.primaryText,
                    modifier = Modifier.align(Alignment.CenterStart),
                )
                LiquidGlassSwitch(
                    isOn = monitorEnabled,
                    onToggle = onToggleMonitoring,
                )
            }
        }

        ScreenshotPreviewPanel(
            path = uiState.latestOutputPreviewPath,
            template = uiState.selectedTemplate,
            isDark = isDark,
        )

        Spacer(modifier = Modifier.height(112.dp))
    }
}

@Composable
private fun ScreenshotPreviewPanel(
    path: String?,
    template: ShellTemplate?,
    isDark: Boolean,
) {
    val colors = IOSColors.scheme(isDark)
    LiteLiquidGlassPanel(
        modifier = Modifier.fillMaxWidth(),
        isDark = isDark,
        padding = PaddingValues(10.dp),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(0.78f)
                .clip(RoundedCornerShape(28.dp))
                .background(if (isDark) Color.Black else colors.groupedBackground),
        ) {
            if (path != null) {
                OutputPreview(
                    path = path,
                    isDark = isDark,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp),
                )
            } else if (template != null) {
                TemplatePreviewThumbnail(
                    previewPath = template.displayPreviewAsset,
                    contentDescription = template.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(18.dp),
                    cornerRadius = 28.dp,
                )
            } else {
                EmptyPreviewState(
                    modifier = Modifier.fillMaxSize(),
                    isDark = isDark,
                )
            }
        }
    }
}

@Composable
private fun EmptyPreviewState(
    modifier: Modifier = Modifier,
    isDark: Boolean,
) {
    val colors = IOSColors.scheme(isDark)
    Column(
        modifier = modifier.padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        AppIcon(
            icon = AppIconId.Gallery,
            contentDescription = null,
            tint = colors.tertiaryText,
            modifier = Modifier.size(42.dp),
        )
    }
}

@Composable
private fun OutputPreview(
    path: String,
    isDark: Boolean,
    modifier: Modifier = Modifier,
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
    val currentImage by image
    if (currentImage != null) {
        Image(
            bitmap = currentImage!!,
            contentDescription = "最近生成的带壳截图",
            modifier = modifier,
            contentScale = ContentScale.Fit,
        )
    } else {
        EmptyPreviewState(
            modifier = modifier,
            isDark = isDark,
        )
    }
}
