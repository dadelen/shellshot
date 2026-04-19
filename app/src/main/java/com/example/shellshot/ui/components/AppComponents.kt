package com.example.shellshot.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import android.content.res.Configuration
import com.example.shellshot.ui.theme.ShellShotTheme
import com.example.shellshot.ui.theme.ShellShotColorTokens
import com.example.shellshot.ui.theme.ShellShotShadowStyle
import com.example.shellshot.ui.theme.shellShotTokens
import kotlin.math.abs

enum class StatusTone {
    Neutral,
    Info,
    Success,
    Warning,
    Error,
}

@Immutable
data class LiquidGlassBottomBarStyle(
    val barHeight: Dp = 60.dp,
    val cornerRadius: Dp = 28.dp,
    val horizontalInset: Dp = 12.dp,
    val verticalInset: Dp = 10.dp,
    val borderWidth: Dp = 1.dp,
    val borderAlpha: Float = 0.74f,
    val containerAlpha: Float = 0.82f,
    val highlightAlpha: Float = 0.38f,
    val fauxBlurRadius: Dp = 12.dp,
    val floatingShadow: Dp = 6.dp,
    val indicatorCornerRadius: Dp = 18.dp,
    val indicatorShadow: Dp = 2.dp,
    val indicatorStretchFactor: Float = 0.08f,
    val maxStretch: Dp = 14.dp,
    val motionDurationMillis: Int = 280,
    val unselectedScale: Float = 0.94f,
    val selectedScale: Float = 1f,
    val unselectedContentAlpha: Float = 0.72f,
    val selectedContentAlpha: Float = 1f,
)

object LiquidGlassBottomBarStyleDefaults {
    val Balanced = LiquidGlassBottomBarStyle()

    val Enhanced = LiquidGlassBottomBarStyle(
        barHeight = 62.dp,
        cornerRadius = 30.dp,
        horizontalInset = 12.dp,
        verticalInset = 10.dp,
        borderAlpha = 0.78f,
        containerAlpha = 0.84f,
        highlightAlpha = 0.42f,
        fauxBlurRadius = 14.dp,
        floatingShadow = 7.dp,
        indicatorCornerRadius = 19.dp,
        indicatorShadow = 2.dp,
        indicatorStretchFactor = 0.1f,
        maxStretch = 16.dp,
        motionDurationMillis = 300,
    )
}

@Composable
fun AppBackdrop(
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val darkBackdrop = colors.backgroundPrimary.luminance() < 0.1f
    val topLightAlpha = if (darkBackdrop) 0.10f else 0.14f
    val blueGlowAlpha = if (darkBackdrop) 0.025f else 0.06f
    val pinkGlowAlpha = if (darkBackdrop) 0.0f else 0.10f
    val violetGlowAlpha = if (darkBackdrop) 0.04f else 0.13f
    val bottomBlueAlpha = if (darkBackdrop) 0.0f else 0.055f
    val centerWhiteAlpha = if (darkBackdrop) 0.03f else 0.07f

    Box(
        modifier = modifier.background(
            brush = Brush.verticalGradient(
                colors = listOf(
                    colors.backgroundPrimary,
                    colors.backgroundPrimary,
                    colors.backgroundSecondary,
                ),
            ),
        ),
    ) {
        BackgroundWash(
            modifier = Modifier
                .fillMaxWidth()
                .height(240.dp)
                .align(Alignment.TopCenter),
            color = Color.White.copy(alpha = topLightAlpha),
        )
        BackgroundWash(
            modifier = Modifier
                .size(320.dp)
                .align(Alignment.TopEnd)
                .offset(x = 88.dp, y = (-64).dp),
            color = colors.accentBlue.copy(alpha = blueGlowAlpha),
        )
        BackgroundWash(
            modifier = Modifier
                .size(280.dp)
                .align(Alignment.BottomStart)
                .offset(x = (-92).dp, y = 84.dp),
            color = colors.accentBlue.copy(alpha = if (darkBackdrop) 0f else 0.032f),
        )
        BackgroundWash(
            modifier = Modifier
                .size(260.dp)
                .align(Alignment.TopStart)
                .offset(x = (-72).dp, y = 80.dp),
            color = Color(0xFFFBCFE8).copy(alpha = pinkGlowAlpha),
        )
        BackgroundWash(
            modifier = Modifier
                .size(300.dp)
                .align(Alignment.CenterStart)
                .offset(x = (-118).dp, y = 36.dp),
            color = Color(0xFFC7D2FE).copy(alpha = violetGlowAlpha),
        )
        BackgroundWash(
            modifier = Modifier
                .size(340.dp)
                .align(Alignment.CenterEnd)
                .offset(x = 116.dp, y = 76.dp),
            color = colors.accentBlue.copy(alpha = if (darkBackdrop) 0.015f else 0.04f),
        )
        BackgroundWash(
            modifier = Modifier
                .size(360.dp)
                .align(Alignment.BottomCenter)
                .offset(y = 120.dp),
            color = Color(0xFFE0F2FE).copy(alpha = bottomBlueAlpha),
        )
        BackgroundWash(
            modifier = Modifier
                .size(240.dp)
                .align(Alignment.Center)
                .offset(y = (-28).dp),
            color = Color.White.copy(alpha = centerWhiteAlpha),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(220.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = if (darkBackdrop) 0.06f else 0.22f),
                            Color.Transparent,
                        ),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = if (darkBackdrop) 0.02f else 0.04f),
                            Color.Transparent,
                            colors.backgroundSecondary.copy(alpha = if (darkBackdrop) 0.04f else 0.24f),
                        ),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .drawWithCache {
                    val primarySweep = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = if (darkBackdrop) 0.04f else 0.18f),
                            Color.Transparent,
                        ),
                        start = Offset(x = size.width * 0.18f, y = 0f),
                        end = Offset(x = size.width * 0.58f, y = size.height),
                    )
                    val secondarySweep = Brush.linearGradient(
                        colors = listOf(
                            Color.Transparent,
                            colors.accentBlue.copy(alpha = if (darkBackdrop) 0.015f else 0.05f),
                            Color.Transparent,
                        ),
                        start = Offset(x = size.width * 0.48f, y = 0f),
                        end = Offset(x = size.width * 0.84f, y = size.height),
                    )
                    onDrawBehind {
                        drawRect(primarySweep, blendMode = BlendMode.Screen)
                        drawRect(secondarySweep, blendMode = BlendMode.Screen)
                    }
                },
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .align(Alignment.BottomStart)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            colors.backgroundSecondary.copy(alpha = if (darkBackdrop) 0.02f else 0.26f),
                        ),
                    ),
                ),
        )
    }
}

@Composable
fun AppPageSection(
    modifier: Modifier = Modifier,
    maxContentWidth: Dp = 1120.dp,
    content: @Composable BoxScope.() -> Unit,
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        val innerModifier = if (maxWidth > maxContentWidth) {
            Modifier.width(maxContentWidth)
        } else {
            Modifier.fillMaxWidth()
        }
        Box(
            modifier = innerModifier,
            content = content,
        )
    }
}

@Composable
fun AppPageHeader(
    eyebrow: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val spacing = MaterialTheme.shellShotTokens.spacing

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(spacing.sm),
    ) {
        if (eyebrow.isNotBlank()) {
            Text(
                text = eyebrow,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Medium,
                color = colors.textSecondary,
            )
        }
        Text(
            text = title,
            style = MaterialTheme.typography.displaySmall,
            color = colors.textPrimary,
        )
        if (description.isNotBlank()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyLarge,
                color = colors.textSecondary,
            )
        }
    }
}

@Composable
fun AppSectionLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.Medium,
        color = colors.textTertiary,
    )
}

@Composable
fun AppSurfaceCard(
    modifier: Modifier = Modifier,
    prominent: Boolean = false,
    contentPadding: PaddingValues = PaddingValues(20.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    val tokens = MaterialTheme.shellShotTokens
    val colors = tokens.colors
    val shape = RoundedCornerShape(if (prominent) tokens.radii.pageCard else tokens.radii.secondaryCard)
    val shadow = if (prominent) tokens.shadows.card else tokens.shadows.soft

    Box(
        modifier = modifier
            .appShadow(shadow, shape)
            .clip(shape)
            .background(
                if (prominent) {
                    colors.cardBackgroundStrong.copy(alpha = 0.94f)
                } else {
                    colors.cardBackground.copy(alpha = 0.92f)
                },
            )
            .border(
                width = 1.dp,
                color = colors.cardStroke,
                shape = shape,
            ),
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .drawWithCache {
                    val topGlow = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.18f),
                            Color.Transparent,
                        ),
                        endY = size.height * 0.42f,
                    )
                    val sideTint = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.08f),
                            Color.Transparent,
                            colors.accentBlue.copy(alpha = 0.02f),
                        ),
                    )
                    onDrawBehind {
                        drawRoundRect(brush = topGlow, blendMode = BlendMode.Screen)
                        drawRoundRect(brush = sideTint, blendMode = BlendMode.Screen)
                    }
                },
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(tokens.spacing.lg),
            content = content,
        )
    }
}

@Composable
fun AppInsetPanel(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val shape = RoundedCornerShape(20.dp)

    Box(
        modifier = modifier
            .clip(shape)
            .background(Color.White.copy(alpha = 0.58f))
            .border(
                width = 1.dp,
                color = colors.cardStroke.copy(alpha = 0.72f),
                shape = shape,
            ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            content = content,
        )
    }
}

@Composable
fun AppMetaChip(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(Color.White.copy(alpha = 0.7f))
            .border(
                width = 1.dp,
                color = colors.cardStroke.copy(alpha = 0.78f),
                shape = RoundedCornerShape(999.dp),
            )
            .padding(horizontal = 8.dp, vertical = 4.dp),
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Medium,
            color = colors.textTertiary,
        )
    }
}

@Composable
fun AppDarkButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed && enabled) 0.99f else 1f,
        animationSpec = spring(dampingRatio = 0.85f, stiffness = 700f),
        label = "app-dark-button-scale",
    )
    val shape = RoundedCornerShape(MaterialTheme.shellShotTokens.radii.button)

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = if (enabled) 1f else 0.42f
            }
            .shadow(
                elevation = 6.dp,
                shape = shape,
                clip = false,
                ambientColor = Color(0x05000000),
                spotColor = Color(0x221C1C1E),
            )
            .clip(shape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF303136),
                        Color(0xFF1F2024),
                    ),
                ),
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = 0.08f),
                shape = shape,
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 18.dp, vertical = 15.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color.White,
        )
    }
}

@Composable
fun AppPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val tokens = MaterialTheme.shellShotTokens
    val colors = tokens.colors
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed && enabled) 0.988f else 1f,
        animationSpec = spring(dampingRatio = 0.78f, stiffness = 620f),
        label = "app-primary-button-scale",
    )
    val shape = RoundedCornerShape(tokens.radii.button)

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = if (enabled) 1f else 0.48f
            }
            .clip(shape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.96f),
                        colors.accentBlueSoft,
                    ),
                ),
            )
            .border(
                width = 1.dp,
                color = colors.accentBlue.copy(alpha = 0.14f),
                shape = shape,
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 18.dp, vertical = 15.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = colors.textPrimary,
        )
    }
}

@Composable
fun AppSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val tokens = MaterialTheme.shellShotTokens
    val colors = tokens.colors
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val scale by animateFloatAsState(
        targetValue = if (pressed && enabled) 0.99f else 1f,
        animationSpec = spring(dampingRatio = 0.8f, stiffness = 640f),
        label = "app-secondary-button-scale",
    )
    val shape = RoundedCornerShape(tokens.radii.button)

    Box(
        modifier = modifier
            .graphicsLayer {
                scaleX = scale
                scaleY = scale
                alpha = if (enabled) 1f else 0.48f
            }
            .clip(shape)
            .background(Color.White.copy(alpha = 0.86f))
            .border(
                width = 1.dp,
                color = colors.cardStroke,
                shape = shape,
            )
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 16.dp, vertical = 14.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.titleSmall,
            fontWeight = FontWeight.Medium,
            textAlign = TextAlign.Center,
            color = colors.textPrimary,
        )
    }
}

@Composable
fun AppStatusPill(
    text: String,
    tone: StatusTone,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val accent = tone.iconTint(colors)
    val background = when (tone) {
        StatusTone.Success -> colors.accentGreenSoft
        StatusTone.Warning -> colors.accentAmberSoft
        StatusTone.Error -> colors.accentRedSoft
        StatusTone.Info -> colors.accentBlueSoft
        StatusTone.Neutral -> Color.White.copy(alpha = 0.72f)
    }

    Row(
        modifier = modifier
            .clip(RoundedCornerShape(MaterialTheme.shellShotTokens.radii.pill))
            .background(background)
            .border(
                width = 1.dp,
                color = accent.copy(alpha = if (tone == StatusTone.Neutral) 0.08f else 0.14f),
                shape = RoundedCornerShape(MaterialTheme.shellShotTokens.radii.pill),
            )
            .padding(horizontal = 10.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(7.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(6.dp)
                .background(accent, CircleShape),
        )
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Medium,
            color = if (tone == StatusTone.Neutral) colors.textSecondary else accent,
        )
    }
}

@Composable
fun AppDivider(
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(colors.dividerColor),
    )
}

@Composable
fun AppIconPlate(
    icon: AppIconId,
    tint: Color,
    modifier: Modifier = Modifier,
) {
    val tokens = MaterialTheme.shellShotTokens
    val colors = tokens.colors

    Box(
        modifier = modifier
            .size(46.dp)
            .shadow(
                elevation = 3.dp,
                shape = RoundedCornerShape(tokens.radii.iconPlate),
                clip = false,
                ambientColor = Color.White.copy(alpha = 0.08f),
                spotColor = Color(0x081C1C1E),
            )
            .clip(RoundedCornerShape(tokens.radii.iconPlate))
            .background(Color.White.copy(alpha = 0.9f))
            .border(
                width = 1.dp,
                color = colors.cardStroke,
                shape = RoundedCornerShape(tokens.radii.iconPlate),
            ),
    ) {
        AppIcon(
            icon = icon,
            contentDescription = null,
            modifier = Modifier.align(Alignment.Center),
            tint = tint,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TemplateCard(
    name: String,
    previewPath: String,
    summary: String,
    selected: Boolean,
    actionText: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    onDelete: (() -> Unit)? = null,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val shape = RoundedCornerShape(22.dp)
    val actionShape = RoundedCornerShape(999.dp)
    val metaParts = remember(summary) {
        summary.split("·")
            .map { it.trim() }
            .filter { it.isNotBlank() }
    }
    val dismissState = rememberSwipeToDismissBoxState(
        confirmValueChange = { value ->
            if (value == SwipeToDismissBoxValue.EndToStart) {
                onDelete?.invoke()
                false
            } else {
                false
            }
        },
    )

    val cardContent: @Composable () -> Unit = {
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 2.dp,
                    shape = shape,
                    clip = false,
                    ambientColor = Color.White.copy(alpha = 0.08f),
                    spotColor = Color(0x091C1C1E),
                )
                .clip(shape)
                .background(Color.White)
                .border(
                    width = 1.dp,
                    color = if (selected) colors.accentBlue.copy(alpha = 0.45f) else colors.cardStroke,
                    shape = shape,
                )
                .padding(horizontal = 16.dp, vertical = 16.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(14.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TemplatePreviewThumbnail(
                    previewPath = previewPath,
                    contentDescription = name,
                    selected = selected,
                    modifier = Modifier.size(width = 58.dp, height = 86.dp),
                    cornerRadius = 16.dp,
                    imagePadding = 6.dp,
                )
                Column(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(6.dp),
                ) {
                    Text(
                        text = name,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.textPrimary,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        metaParts.take(2).forEach { item ->
                            AppMetaChip(text = item)
                        }
                    }
                    if (metaParts.size > 2) {
                        Text(
                            text = metaParts.drop(2).joinToString(" · "),
                            style = MaterialTheme.typography.bodySmall,
                            color = colors.textSecondary,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                        )
                    }
                }

                Box(
                    modifier = Modifier.widthIn(min = 76.dp),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    if (selected) {
                        Box(
                            modifier = Modifier
                                .clip(actionShape)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(
                                            Color(0xFF2E2F34),
                                            Color(0xFF1F2024),
                                        ),
                                    ),
                                )
                                .border(
                                    width = 1.dp,
                                    color = Color.White.copy(alpha = 0.08f),
                                    shape = actionShape,
                                )
                                .padding(horizontal = 12.dp, vertical = 7.dp),
                        ) {
                            Text(
                                text = "使用中",
                                fontSize = 11.sp,
                                fontWeight = FontWeight.SemiBold,
                                color = Color.White,
                            )
                        }
                    } else {
                        OutlinedButton(
                            onClick = onClick,
                            shape = actionShape,
                            contentPadding = PaddingValues(horizontal = 13.dp, vertical = 7.dp),
                            colors = ButtonDefaults.outlinedButtonColors(
                                containerColor = Color.White.copy(alpha = 0.9f),
                                contentColor = colors.textPrimary,
                            ),
                            border = BorderStroke(1.dp, colors.cardStroke.copy(alpha = 0.9f)),
                        ) {
                            Text(
                                text = actionText,
                                fontSize = 11.sp,
                                fontWeight = FontWeight.Medium,
                            )
                        }
                    }
                }
            }
        }
    }

    if (onDelete != null) {
        SwipeToDismissBox(
            state = dismissState,
            modifier = modifier,
            enableDismissFromStartToEnd = false,
            enableDismissFromEndToStart = true,
            backgroundContent = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(shape)
                        .background(colors.accentRed.copy(alpha = 0.88f))
                        .border(
                            width = 1.dp,
                            color = colors.accentRed.copy(alpha = 0.22f),
                            shape = shape,
                        )
                        .padding(end = 22.dp),
                    contentAlignment = Alignment.CenterEnd,
                ) {
                    AppIcon(
                        icon = AppIconId.Delete,
                        contentDescription = "删除模板",
                        tint = Color.White,
                    )
                }
            },
        ) {
            cardContent()
        }
    } else {
        Box(modifier = modifier) {
            cardContent()
        }
    }
}

@Composable
fun SettingActionRow(
    icon: AppIconId,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
    statusText: String? = null,
    statusTone: StatusTone = StatusTone.Info,
    onClick: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppIconPlate(icon = icon, tint = statusTone.iconTint(colors))
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(if (description.isBlank()) 0.dp else 4.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.textPrimary,
            )
            if (description.isNotBlank()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (!statusText.isNullOrBlank()) {
            Text(
                text = statusText,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Medium,
                color = statusTone.iconTint(colors),
            )
        }
        AppIcon(
            icon = AppIconId.ChevronRight,
            contentDescription = null,
            tint = colors.textTertiary,
        )
    }
}

@Composable
fun SettingToggleRow(
    icon: AppIconId,
    title: String,
    description: String,
    checked: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onCheckedChange: (Boolean) -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AppIconPlate(
            icon = icon,
            tint = if (checked) colors.accentGreen else colors.textTertiary,
        )
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(if (description.isBlank()) 0.dp else 4.dp),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                color = colors.textPrimary,
            )
            if (description.isNotBlank()) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = colors.textSecondary,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        BentoSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            enabled = enabled,
            darkTheme = androidx.compose.material3.MaterialTheme.colorScheme.background.red < 0.5f
        )
    }
}

@Composable
fun LogStreamItem(
    module: String,
    timestamp: String,
    summary: String,
    tone: StatusTone,
    modifier: Modifier = Modifier,
    details: String? = null,
    expanded: Boolean,
    onToggleExpanded: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val accent = tone.iconTint(colors)

    AppSurfaceCard(
        modifier = modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onToggleExpanded,
            ),
        contentPadding = PaddingValues(horizontal = 18.dp, vertical = 18.dp),
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(14.dp)) {
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
                            .size(8.dp)
                            .background(accent, CircleShape),
                    )
                    Text(
                        text = module,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.SemiBold,
                        color = colors.textPrimary,
                    )
                }
                AppStatusPill(text = tone.label(), tone = tone)
            }
            AppInsetPanel(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(horizontal = 16.dp, vertical = 14.dp),
            ) {
                Text(
                    text = timestamp,
                    style = MaterialTheme.typography.labelLarge,
                    color = colors.textTertiary,
                )
                Text(
                    text = summary,
                    style = MaterialTheme.typography.titleSmall,
                    fontWeight = FontWeight.SemiBold,
                    color = colors.textPrimary,
                    maxLines = if (expanded) Int.MAX_VALUE else 2,
                    overflow = TextOverflow.Ellipsis,
                )
                AnimatedVisibility(
                    visible = !details.isNullOrBlank(),
                    enter = fadeIn(tween(160)) + expandVertically(tween(180)),
                    exit = fadeOut(tween(140)) + shrinkVertically(tween(140)),
                ) {
                    Text(
                        text = if (expanded) details.orEmpty() else details.orEmpty().lineSequence().firstOrNull().orEmpty(),
                        style = MaterialTheme.typography.bodySmall,
                        color = colors.textSecondary,
                        maxLines = if (expanded) Int.MAX_VALUE else 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
            }
        }
    }
}

@Composable
fun LiquidGlassBottomBar(
    items: List<GlassBottomBarItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    style: LiquidGlassBottomBarStyle = LiquidGlassBottomBarStyleDefaults.Balanced,
) {
    val tokens = MaterialTheme.shellShotTokens
    val colors = tokens.colors
    val shape = RoundedCornerShape(style.cornerRadius)
    val floatingShadow = tokens.shadows.floating.copy(elevation = style.floatingShadow)

    BoxWithConstraints(
        modifier = modifier
            .height(style.barHeight)
            .appShadow(floatingShadow, shape)
            .clip(shape)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = style.containerAlpha * 0.48f),
                        MaterialTheme.colorScheme.surface.copy(alpha = style.containerAlpha * 0.72f),
                        MaterialTheme.colorScheme.surfaceVariant.copy(alpha = style.containerAlpha),
                    ),
                ),
            )
            .border(style.borderWidth, colors.cardStroke.copy(alpha = style.borderAlpha), shape),
    ) {
        val itemWidth = maxWidth / items.size.coerceAtLeast(1)
        val baseIndicatorWidth = itemWidth - style.horizontalInset * 2
        var previousSelectedIndex by remember { mutableIntStateOf(selectedIndex) }
        val stretchProgress = remember { Animatable(0f) }
        val indicatorOffset by animateDpAsState(
            targetValue = itemWidth * selectedIndex + style.horizontalInset,
            animationSpec = spring(dampingRatio = 0.9f, stiffness = 680f),
            label = "liquid-glass-bottom-bar-offset",
        )
        val selectionFraction by animateFloatAsState(
            targetValue = if (items.lastIndex <= 0) 0f else selectedIndex.toFloat() / items.lastIndex.toFloat(),
            animationSpec = tween(style.motionDurationMillis, easing = PremiumMotionEasing),
            label = "liquid-glass-bottom-bar-fraction",
        )

        LaunchedEffect(selectedIndex, itemWidth) {
            if (previousSelectedIndex == selectedIndex && stretchProgress.value == 0f) {
                return@LaunchedEffect
            }
            val hopDistance = abs(selectedIndex - previousSelectedIndex).coerceAtLeast(1)
            stretchProgress.snapTo(hopDistance.toFloat())
            stretchProgress.animateTo(
                targetValue = 0f,
                animationSpec = tween(style.motionDurationMillis, easing = PremiumMotionEasing),
            )
            previousSelectedIndex = selectedIndex
        }

        val indicatorWidth = (
            baseIndicatorWidth +
                ((itemWidth * (style.indicatorStretchFactor * stretchProgress.value)) + 4.dp)
                    .coerceAtMost(style.maxStretch)
            )

        Box(
            modifier = Modifier
                .matchParentSize()
                .clip(shape)
                .drawWithCache {
                    val topHighlight = Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = style.highlightAlpha),
                            Color.White.copy(alpha = style.highlightAlpha * 0.14f),
                            Color.Transparent,
                        ),
                        endY = size.height * 0.42f,
                    )
                    val bottomShade = Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.03f),
                        ),
                        startY = size.height * 0.7f,
                        endY = size.height,
                    )
                    val innerEdge = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.1f),
                            Color.Transparent,
                            Color.White.copy(alpha = 0.05f),
                        ),
                    )
                    onDrawBehind {
                        drawRoundRect(brush = topHighlight, blendMode = BlendMode.Screen)
                        drawRoundRect(brush = bottomShade)
                        drawRoundRect(brush = innerEdge, blendMode = BlendMode.Screen)
                    }
                },
        )

        Box(
            modifier = Modifier
                .size(width = maxWidth * 0.34f, height = style.barHeight * 0.42f)
                .align(Alignment.CenterStart)
                .offset(x = (-6).dp, y = (-2).dp)
                .blur(style.fauxBlurRadius)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.12f),
                            Color.Transparent,
                        ),
                    ),
                    CircleShape,
                ),
        )

        Box(
            modifier = Modifier
                .size(width = maxWidth * 0.28f, height = style.barHeight * 0.38f)
                .align(Alignment.CenterEnd)
                .offset(x = 6.dp, y = 1.dp)
                .blur(style.fauxBlurRadius)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            colors.accentBlue.copy(alpha = 0.05f),
                            Color.Transparent,
                        ),
                    ),
                    CircleShape,
                ),
        )

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 4.dp)
                .fillMaxWidth(0.72f)
                .height(6.dp)
                .clip(CircleShape)
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.34f),
                            Color.White.copy(alpha = 0.08f),
                            Color.Transparent,
                        ),
                    ),
                ),
        )

        Box(
            modifier = Modifier
                .padding(vertical = style.verticalInset)
                .offset(x = indicatorOffset)
                .width(indicatorWidth)
                .fillMaxHeight()
                .shadow(
                    elevation = style.indicatorShadow,
                    shape = RoundedCornerShape(style.indicatorCornerRadius),
                    clip = false,
                    ambientColor = Color.White.copy(alpha = 0.08f),
                    spotColor = Color(0x141C1C1E),
                )
                .clip(RoundedCornerShape(style.indicatorCornerRadius))
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.98f),
                            Color(0xFFF7F8FA),
                            Color(0xFFF2F3F6),
                        ),
                    ),
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.92f),
                    shape = RoundedCornerShape(style.indicatorCornerRadius),
                ),
        ) {
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .drawWithCache {
                        val diagonalGloss = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.24f),
                                Color.White.copy(alpha = 0.08f),
                                Color.Transparent,
                            ),
                            start = Offset(x = size.width * 0.18f, y = 0f),
                            end = Offset(x = size.width * 0.78f, y = size.height),
                        )
                        val lowerShadow = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.03f),
                            ),
                            startY = size.height * 0.56f,
                            endY = size.height,
                        )
                        onDrawBehind {
                            drawRoundRect(brush = diagonalGloss, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = lowerShadow)
                        }
                    },
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .drawWithCache {
                        val surfaceGlow = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.28f),
                                Color.Transparent,
                            ),
                            endY = size.height * 0.36f,
                        )
                        val softGlassBand = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.12f),
                                Color.Transparent,
                            ),
                        )
                        onDrawBehind {
                            drawRoundRect(brush = surfaceGlow, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = softGlassBand, blendMode = BlendMode.Screen)
                        }
                    },
            )
            Box(
                modifier = Modifier
                    .size(width = indicatorWidth * 0.34f, height = style.barHeight * 0.22f)
                    .align(Alignment.TopCenter)
                    .offset(y = 4.dp)
                    .blur(8.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.22f),
                                Color.Transparent,
                            ),
                        ),
                        CircleShape,
                    ),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.62f)
                    .height(5.dp)
                    .align(Alignment.TopCenter)
                    .offset(y = 3.dp)
                    .blur(6.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.26f),
                                Color.Transparent,
                            ),
                        ),
                        CircleShape,
                    ),
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.68f)
                    .height(6.dp)
                    .align(Alignment.BottomCenter)
                    .offset(y = (-2).dp)
                    .blur(7.dp)
                    .background(
                        Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.07f),
                                Color.Transparent,
                            ),
                        ),
                        CircleShape,
                    ),
            )
        }

        Row(modifier = Modifier.fillMaxSize()) {
            items.forEachIndexed { index, item ->
                val selected = index == selectedIndex
                val tint by animateColorAsState(
                    targetValue = if (selected) colors.textPrimary else colors.textTertiary,
                    animationSpec = tween(style.motionDurationMillis, easing = PremiumMotionEasing),
                    label = "liquid-glass-bottom-bar-tint-$index",
                )
                val contentAlpha by animateFloatAsState(
                    targetValue = if (selected) style.selectedContentAlpha else style.unselectedContentAlpha,
                    animationSpec = tween(style.motionDurationMillis, easing = PremiumMotionEasing),
                    label = "liquid-glass-bottom-bar-alpha-$index",
                )
                val iconScale by animateFloatAsState(
                    targetValue = if (selected) style.selectedScale else style.unselectedScale,
                    animationSpec = spring(dampingRatio = 0.82f, stiffness = 620f),
                    label = "liquid-glass-bottom-bar-scale-$index",
                )
                val labelOffset by animateDpAsState(
                    targetValue = if (selected) 0.dp else 0.5.dp,
                    animationSpec = tween(style.motionDurationMillis, easing = PremiumMotionEasing),
                    label = "liquid-glass-bottom-bar-label-offset-$index",
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clickable(
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null,
                            onClick = { onSelect(index) },
                        )
                        .padding(horizontal = 8.dp, vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    val iconSize by animateDpAsState(
                        targetValue = if (selected) 18.dp else 17.dp,
                        animationSpec = tween(style.motionDurationMillis, easing = PremiumMotionEasing),
                        label = "liquid-glass-bottom-bar-icon-size-$index",
                    )
                    AppIcon(
                        icon = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier
                            .size(iconSize)
                            .graphicsLayer {
                                scaleX = iconScale
                                scaleY = iconScale
                                alpha = contentAlpha
                            },
                        tint = tint,
                    )
                    Text(
                        text = item.label,
                        modifier = Modifier
                            .offset(y = labelOffset)
                            .graphicsLayer { alpha = contentAlpha },
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                        color = tint,
                    )
                }
            }
        }
    }
}

@Composable
fun PremiumBottomBar(
    items: List<GlassBottomBarItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LiquidGlassBottomBar(
        items = items,
        selectedIndex = selectedIndex,
        onSelect = onSelect,
        modifier = modifier,
        style = LiquidGlassBottomBarStyleDefaults.Balanced,
    )
}

@Preview(name = "Liquid Glass Bottom Bar", showBackground = true, backgroundColor = 0xFFF7F7F5)
@Preview(
    name = "Liquid Glass Bottom Bar Dark",
    showBackground = true,
    backgroundColor = 0xFF17181B,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun LiquidGlassBottomBarPreview() {
    ShellShotTheme {
        var selectedIndex by remember { mutableIntStateOf(0) }
        val previewItems = listOf(
            GlassBottomBarItem("首页", AppIconId.Home),
            GlassBottomBarItem("模板", AppIconId.Template),
            GlassBottomBarItem("设置", AppIconId.Settings),
            GlassBottomBarItem("日志", AppIconId.Terminal),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp)
                .background(MaterialTheme.shellShotTokens.colors.backgroundPrimary),
        ) {
            LiquidGlassBottomBar(
                items = previewItems,
                selectedIndex = selectedIndex,
                onSelect = { selectedIndex = it },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 18.dp),
                style = LiquidGlassBottomBarStyleDefaults.Enhanced,
            )
        }
    }
}

private fun StatusTone.iconTint(colors: ShellShotColorTokens): Color {
    return when (this) {
        StatusTone.Success -> colors.accentGreen
        StatusTone.Warning -> colors.accentAmber
        StatusTone.Error -> colors.accentRed
        StatusTone.Info -> colors.accentBlue
        StatusTone.Neutral -> colors.textSecondary
    }
}

private fun StatusTone.label(): String {
    return when (this) {
        StatusTone.Success -> "成功"
        StatusTone.Warning -> "注意"
        StatusTone.Error -> "错误"
        StatusTone.Info -> "信息"
        StatusTone.Neutral -> "状态"
    }
}

private fun Modifier.appShadow(
    shadow: ShellShotShadowStyle,
    shape: Shape,
): Modifier {
    return this.shadow(
        elevation = shadow.elevation,
        shape = shape,
        clip = false,
        ambientColor = shadow.ambientColor,
        spotColor = shadow.spotColor,
    )
}

@Composable
private fun BackgroundWash(
    modifier: Modifier,
    color: Color,
) {
    val middleColor = remember(color) { color.copy(alpha = color.alpha * 0.48f) }
    Box(
        modifier = modifier
            .graphicsLayer()
            .background(
                brush = Brush.radialGradient(
                    colors = listOf(
                        color,
                        middleColor,
                        Color.Transparent,
                    ),
                ),
                shape = CircleShape,
            ),
    )
}

private val PremiumMotionEasing = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)


