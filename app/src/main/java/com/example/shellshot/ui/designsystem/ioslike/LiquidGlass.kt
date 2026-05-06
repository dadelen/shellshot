package com.example.shellshot.ui.designsystem.ioslike

import android.os.Build
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect

@Composable
fun LiteLiquidGlassPanel(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    padding: PaddingValues = PaddingValues(IOSSpacing.Default.cardPadding),
    content: @Composable BoxScope.() -> Unit,
) {
    val colors = IOSColors.scheme(isDark)
    val shape = IOSShapes.Default.glass
    Box(
        modifier = modifier
            .shadow(
                elevation = 12.dp,
                shape = shape,
                ambientColor = Color.Black.copy(alpha = if (isDark) 0.24f else 0.10f),
                spotColor = Color.Black.copy(alpha = if (isDark) 0.22f else 0.08f),
            )
            .clip(shape)
            .background(colors.glassFill, shape)
            .drawWithCache {
                val highlight = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = if (isDark) 0.18f else 0.42f),
                        Color.White.copy(alpha = if (isDark) 0.06f else 0.18f),
                        Color.Transparent,
                    ),
                    start = Offset.Zero,
                    end = Offset(size.width, size.height),
                )
                onDrawWithContent {
                    drawContent()
                    drawRect(highlight)
                }
            }
            .border(0.8.dp, colors.glassStroke, shape)
            .padding(padding),
        content = content,
    )
}

@Composable
fun LiquidGlassBottomBar(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    hazeState: HazeState? = null,
    enableRealBlur: Boolean = false,
    content: @Composable RowScope.() -> Unit,
) {
    val canUseRealBlur = enableRealBlur && hazeState != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S
    Box(
        modifier = modifier
            .navigationBarsPadding()
            .padding(
                horizontal = IOSSpacing.Default.bottomBarHorizontal,
                vertical = 10.dp,
            ),
        contentAlignment = Alignment.Center,
    ) {
        LiteLiquidGlassPanel(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .then(if (canUseRealBlur) Modifier.hazeEffect(hazeState!!) else Modifier),
            isDark = isDark,
            padding = PaddingValues(horizontal = 12.dp, vertical = 8.dp),
        ) {
            Row(
                modifier = Modifier.matchParentSize(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically,
                content = content,
            )
        }
    }
}

@Composable
fun LiquidGlassStatusCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    icon: AppIconId? = null,
    tint: Color = IOSColors.Blue,
) {
    val colors = IOSColors.scheme(isDark)
    LiteLiquidGlassPanel(
        modifier = modifier,
        isDark = isDark,
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (icon != null) {
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .background(tint.copy(alpha = if (isDark) 0.20f else 0.12f), IOSShapes.Default.medium),
                    contentAlignment = Alignment.Center,
                ) {
                    AppIcon(
                        icon = icon,
                        contentDescription = null,
                        tint = tint,
                        modifier = Modifier.size(20.dp),
                    )
                }
            }
            Column(
                verticalArrangement = Arrangement.spacedBy(2.dp),
            ) {
                Text(title, style = IOSTypography.Default.secondary, color = colors.secondaryText)
                Text(value, style = IOSTypography.Default.cardTitle, color = colors.primaryText)
            }
        }
    }
}

@Composable
fun LiquidGlassActionButton(
    icon: AppIconId,
    label: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    enabled: Boolean = true,
    tint: Color = IOSColors.Blue,
) {
    val colors = IOSColors.scheme(isDark)
    val interactionSource = remember { MutableInteractionSource() }
    Column(
        modifier = modifier
            .defaultMinSize(minWidth = 56.dp, minHeight = 50.dp)
            .liquidPressEffect(interactionSource = interactionSource, enabled = enabled)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(2.dp),
    ) {
        AppIcon(
            icon = icon,
            contentDescription = label,
            tint = if (enabled) tint else colors.tertiaryText,
            modifier = Modifier.size(21.dp),
        )
        Text(
            text = label,
            style = IOSTypography.Default.secondary,
            color = if (enabled) colors.primaryText else colors.tertiaryText,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun TemplateGlassSelectionOverlay(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    label: String = "已应用",
) {
    LiteLiquidGlassPanel(
        modifier = modifier,
        isDark = isDark,
        padding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
    ) {
        Text(
            text = label,
            style = IOSTypography.Default.secondary,
            color = IOSColors.Blue,
        )
    }
}
