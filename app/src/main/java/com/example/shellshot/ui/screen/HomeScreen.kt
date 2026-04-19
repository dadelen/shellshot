package com.example.shellshot.ui.screen

import android.graphics.BitmapFactory
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
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
import com.example.shellshot.R
import androidx.compose.ui.res.stringResource
import com.kyant.backdrop.Backdrop
import java.io.File
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.AppTypography
import com.example.shellshot.ui.components.bentoCard
import com.example.shellshot.ui.components.ghostPill
import com.example.shellshot.ui.components.BentoSwitch

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    uiState: MainUiState,
    darkTheme: Boolean,
    liquidBackdrop: Backdrop,
    onToggleDarkTheme: () -> Unit,
    onToggleMonitoring: (Boolean) -> Unit,
    onSelectTemplate: (String) -> Unit,
    onOpenTemplates: () -> Unit,
    onOpenSettings: () -> Unit,
) {
    val pageDark = darkTheme
    
    // 我们去除了刺眼的主题过渡 Alpha 和 Scale，直接由 colorscheme 接管渐变。
    // 但是内容本身的级联入场依然保留。
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ShellColors.background(pageDark)),
    ) {
        HomeContent(
            uiState = uiState,
            isDark = pageDark,
            liquidBackdrop = liquidBackdrop,
            onToggleTheme = onToggleDarkTheme,
            onToggleMonitoring = onToggleMonitoring,
            onOpenTemplates = onOpenTemplates,
            onOpenSettings = onOpenSettings,
            onSelectTemplate = onSelectTemplate,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
private fun HomeContent(
    uiState: MainUiState,
    isDark: Boolean,
    liquidBackdrop: Backdrop,
    onToggleTheme: () -> Unit,
    onToggleMonitoring: (Boolean) -> Unit,
    onOpenTemplates: () -> Unit,
    onOpenSettings: () -> Unit,
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
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 24.dp),
    ) {
        StaggeredItem(visible = showContent, index = 0) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 64.dp, bottom = 32.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.home_title),
                    style = AppTypography.displayLarge,
                    color = ShellColors.textPrimary(isDark)
                )
                IconButton(
                    onClick = onToggleTheme,
                    modifier = Modifier.size(44.dp).bentoCard(isDark, cornerRadius = 22.dp)
                ) {
                    AppIcon(
                        icon = if (isDark) AppIconId.Sun else AppIconId.Moon,
                        contentDescription = stringResource(R.string.home_toggle_theme),
                        tint = ShellColors.textPrimary(isDark),
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
        }

        StaggeredItem(visible = showContent, index = 1) {
            HeroPreviewCard(
                uiState = uiState,
                isDark = isDark,
                liquidBackdrop = liquidBackdrop,
                onOpenTemplates = onOpenTemplates,
                onSelectTemplate = onSelectTemplate,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        StaggeredItem(visible = showContent, index = 2) {
            StatusCard(
                uiState = uiState,
                isDark = isDark,
                liquidBackdrop = liquidBackdrop,
                onToggleMonitoring = onToggleMonitoring,
                onOpenSettings = onOpenSettings,
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        StaggeredItem(visible = showContent, index = 3) {
            RecentLogCard(uiState = uiState, isDark = isDark, liquidBackdrop = liquidBackdrop)
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
            delay(index * 70L)
            itemVisible = true
        }
    }

    AnimatedVisibility(
        visible = itemVisible,
        enter = slideInVertically(initialOffsetY = { 24 }) + fadeIn(tween(300, easing = FastOutSlowInEasing)) + scaleIn(initialScale = 0.98f),
        exit = fadeOut(tween(140)) + scaleOut(targetScale = 0.98f),
        modifier = Modifier.fillMaxWidth(),
    ) {
        content()
    }
}

@Composable
private fun HeroPreviewCard(
    uiState: MainUiState,
    isDark: Boolean,
    liquidBackdrop: Backdrop,
    onOpenTemplates: () -> Unit,
    onSelectTemplate: (String) -> Unit,
) {
    val template = uiState.selectedTemplate
    val outputPath = uiState.lastProcessingResult?.outputPath
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val pressScale by animateFloatAsState(targetValue = if (pressed) 0.97f else 1f, spring(stiffness = 500f, dampingRatio = 0.7f), label = "hero")

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .graphicsLayer { scaleX = pressScale; scaleY = pressScale }
            .bentoCard(isDark, cornerRadius = 24.dp, backdrop = liquidBackdrop)
            .clickable(interactionSource = interactionSource, indication = null) {
                if (template != null) onSelectTemplate(template.id) else onOpenTemplates()
            }
            .padding(24.dp),
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(R.string.home_last_export),
                        style = AppTypography.labelMedium,
                        color = ShellColors.textTertiary(isDark),
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = template?.name ?: stringResource(R.string.home_no_template),
                        style = AppTypography.titleLarge,
                        color = ShellColors.textPrimary(isDark),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                
                // 去除五花八门的牌子，使用极简的次重指示箭头
                AppIcon(
                    icon = AppIconId.ChevronRight,
                    contentDescription = null,
                    tint = ShellColors.textSecondary(isDark),
                    modifier = Modifier.size(20.dp).alpha(0.5f)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(320.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(if (isDark) Color(0xFF0A0A0B) else Color(0xFFF1F2F4)),
                contentAlignment = Alignment.Center,
            ) {
                when {
                    outputPath != null -> OutputImagePreview(path = outputPath, modifier = Modifier.fillMaxSize())
                    template != null -> TemplatePreviewThumbnail(
                        previewPath = template.previewAsset,
                        contentDescription = template.name,
                        modifier = Modifier.fillMaxWidth().height(300.dp),
                        cornerRadius = 20.dp,
                        imagePadding = 0.dp,
                        selected = false,
                    )
                    else -> Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AppIcon(
                            icon = AppIconId.Template,
                            contentDescription = stringResource(R.string.home_no_template),
                            tint = ShellColors.textTertiary(isDark),
                            modifier = Modifier.size(32.dp)
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(stringResource(R.string.home_ready_to_frame), style = AppTypography.bodyMedium, color = ShellColors.textTertiary(isDark))
                    }
                }
            }
        }
    }
}

@Composable
private fun OutputImagePreview(path: String, modifier: Modifier = Modifier) {
    val image by produceState<ImageBitmap?>(initialValue = null, path) {
        value = withContext(Dispatchers.IO) {
            runCatching {
                val file = File(path)
                if (file.exists()) BitmapFactory.decodeFile(file.absolutePath)?.asImageBitmap() else null
            }.getOrNull()
        }
    }
    if (image != null) {
        Image(bitmap = image!!, contentDescription = null, modifier = modifier, contentScale = ContentScale.Fit)
    }
}

@Composable
private fun StatusCard(
    uiState: MainUiState,
    isDark: Boolean,
    liquidBackdrop: Backdrop,
    onToggleMonitoring: (Boolean) -> Unit,
    onOpenSettings: () -> Unit,
) {
    val monitoringEnabled = uiState.settings.monitoringEnabled
    val hasPerms = uiState.hasCoreStartPermissions
    
    val title = if (monitoringEnabled) stringResource(R.string.home_observer_active) else stringResource(R.string.home_observer_inactive)
    val subtitle = when {
        !hasPerms -> stringResource(R.string.home_missing_permissions)
        monitoringEnabled -> stringResource(R.string.home_waiting_screenshots)
        else -> stringResource(R.string.home_standby)
    }

    Box(modifier = Modifier.fillMaxWidth().bentoCard(isDark, cornerRadius = 24.dp, backdrop = liquidBackdrop).padding(24.dp)) {
        Column {
            // ListTile 替代臃肿的大按钮，把 Switch 嵌在右侧！
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(text = title, style = AppTypography.titleMedium, color = ShellColors.textPrimary(isDark))
                    Spacer(modifier = Modifier.height(2.dp))
                    Text(text = subtitle, style = AppTypography.bodyMedium, color = ShellColors.textSecondary(isDark))
                }
                
                BentoSwitch(
                    checked = monitoringEnabled,
                    onCheckedChange = { onToggleMonitoring(it) },
                    modifier = Modifier.padding(start = 16.dp),
                    darkTheme = isDark,
                    backdrop = liquidBackdrop
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Sub-actions 
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                // Secondary Settings Trigger
                Row(
                    modifier = Modifier.clickable { onOpenSettings() }.alpha(0.6f),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AppIcon(
                        icon = AppIconId.Settings,
                        contentDescription = "Settings",
                        tint = ShellColors.textPrimary(isDark),
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(stringResource(R.string.home_configuration), style = AppTypography.bodyMedium, color = ShellColors.textPrimary(isDark))
                }
                
                if (!hasPerms) {
                    Text("! Requires Auth", style = AppTypography.labelMedium, color = ShellColors.criticalOrange)
                }
            }
        }
    }
}

@Composable
private fun RecentLogCard(uiState: MainUiState, isDark: Boolean, liquidBackdrop: Backdrop) {
    val result = uiState.lastProcessingResult
    
    Box(modifier = Modifier.fillMaxWidth().bentoCard(isDark, cornerRadius = 24.dp, backdrop = liquidBackdrop).padding(24.dp)) {
        Column {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(text = stringResource(R.string.home_activity_log), style = AppTypography.labelMedium, color = ShellColors.textTertiary(isDark))
                if (result?.status == ProcessingStatus.Failed) {
                    Text("Failed", style = AppTypography.labelMedium, color = ShellColors.criticalRed)
                }
            }
            
            Spacer(modifier = Modifier.height(16.dp))
            
            if (result == null) {
                Text(stringResource(R.string.home_no_activity), style = AppTypography.bodyMedium, color = ShellColors.textSecondary(isDark))
            } else {
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Source", style = AppTypography.bodyMedium, color = ShellColors.textSecondary(isDark))
                    Text("file_observer", style = AppTypography.bodyMedium, color = ShellColors.textPrimary(isDark))
                }
                Spacer(modifier = Modifier.height(8.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Text("Time", style = AppTypography.bodyMedium, color = ShellColors.textSecondary(isDark))
                    Text(TimeUtils.formatTimestamp(result.processedAtMillis), style = AppTypography.bodyMedium, color = ShellColors.textPrimary(isDark))
                }
                Spacer(modifier = Modifier.height(16.dp))
                
                // Muted file path container
                Box(modifier = Modifier.fillMaxWidth().ghostPill(isDark)) {
                    Text(
                        text = result.sourcePath.substringAfterLast('/'),
                        style = AppTypography.labelMedium,
                        color = ShellColors.textSecondary(isDark),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}
