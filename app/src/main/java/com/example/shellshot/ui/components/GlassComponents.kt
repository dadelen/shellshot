package com.example.shellshot.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.example.shellshot.ui.theme.shellShotTokens
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

data class GlassBottomBarItem(
    val label: String,
    val icon: ImageVector,
)

/**
 * Shared glass tuning for the bottom bar.
 *
 * Compose in this project does not expose a true live backdrop blur for the content behind
 * this surface, so `fauxBackdropBlur` softens internal tint layers as a graceful fallback.
 * The material is built from diffusion tint + translucent fill + specular highlight + bright edge.
 */
@Immutable
data class LiquidGlassBottomBarSpec(
    // Blur radius used by the fallback diffusion blobs when live backdrop blur is unavailable.
    val fauxBackdropBlur: Dp = 24.dp,
    // Main translucent fill alpha for the floating glass shell.
    val fillAlpha: Float = 0.82f,
    // Diagonal highlight angle for the surface sheen, in degrees.
    val highlightAngleDegrees: Float = 14f,
    // Thin edge alpha for the bright glass outline.
    val strokeAlpha: Float = 0.88f,
    // Capsule corner radius. Higher values feel closer to iOS glass pills.
    val cornerRadius: Dp = 34.dp,
    // Very light shadow strength to keep the bar floating, not card-heavy.
    val shadowElevation: Dp = 12.dp,
    // Extra glow alpha inside the selected capsule.
    val indicatorGlowAlpha: Float = 0.3f,
    // Inner horizontal inset for the selected capsule.
    val indicatorInset: Dp = 6.dp,
    // Selected capsule corner radius.
    val indicatorCornerRadius: Dp = 28.dp,
    // Bar height.
    val barHeight: Dp = 82.dp,
    val activeContentColor: Color = Color(0xFF1E2537),
    val inactiveContentColor: Color = Color(0xFF637087),
    val activeTint: Color = Color(0xFFCFEFFF),
    val warmTint: Color = Color(0xFFF7EDE2),
)

object LiquidGlassBottomBarDefaults {
    val Standard = LiquidGlassBottomBarSpec()

    val AppleEnhanced = LiquidGlassBottomBarSpec(
        fauxBackdropBlur = 34.dp,
        fillAlpha = 0.74f,
        highlightAngleDegrees = 10f,
        strokeAlpha = 0.96f,
        cornerRadius = 38.dp,
        shadowElevation = 8.dp,
        indicatorGlowAlpha = 0.46f,
        indicatorInset = 8.dp,
        indicatorCornerRadius = 32.dp,
        barHeight = 88.dp,
        activeContentColor = Color(0xFF182033),
        inactiveContentColor = Color(0xFF5C667E),
        activeTint = Color(0xFFCBEFFF),
        warmTint = Color(0xFFF7EFE8),
    )
}

/**
 * Shared glass tuning for every toggle.
 *
 * Compose cannot sample and blur the live content behind the switch here, so the high-fidelity
 * renderer uses blurred internal glow layers as the fallback frosted-glass treatment.
 */
enum class LegacyLiquidGlassSwitchQuality {
    HighFidelity,
    HighPerformance,
}

@Immutable
data class LegacyLiquidGlassSwitchSpec(
    // Blur radius for the glass shell in the high-fidelity renderer.
    val shellBlur: Dp = 14.dp,
    // Blur radius for the inner green liquid glow in the high-fidelity renderer.
    val liquidBlur: Dp = 10.dp,
    // Transparency of the frosted white shell.
    val shellAlpha: Float = 0.94f,
    // Diagonal highlight angle for the specular glass reflection, in degrees.
    val highlightAngleDegrees: Float = 14f,
    // Opacity of the outer glass stroke / bright edge.
    val strokeAlpha: Float = 0.92f,
    // Huge radius keeps the switch in a capsule shape.
    val cornerRadius: Dp = 999.dp,
    // Shadow strength. Kept low to stay subtle and iOS-like.
    val shadowElevation: Dp = 4.dp,
    // Default switch width. Can be overridden with the size parameter.
    val width: Dp = 84.dp,
    // Default switch height. Can be overridden with the size parameter.
    val height: Dp = 50.dp,
    // Thumb diameter reference value.
    val thumbDiameter: Dp = 36.dp,
    // Green ring width around the thumb.
    val thumbStrokeWidth: Dp = 2.dp,
    // Pressed scale feedback.
    val pressedScale: Float = 0.984f,
    // Main toggle animation duration. Keep inside the 180ms~280ms target range.
    val animationDurationMillis: Int = 240,
    // Disabled-state alpha.
    val disabledAlpha: Float = 0.46f,
    val activeTint: Color = Color(0xFF43D45A),
    val activeTintEdge: Color = Color(0xFF59DD72),
    val inactiveTint: Color = Color(0xFFE7ECF2),
)

object LegacyLiquidGlassSwitchDefaults {
    val HighPerformance = LegacyLiquidGlassSwitchSpec(
        shellBlur = 0.dp,
        liquidBlur = 0.dp,
        shellAlpha = 0.96f,
        highlightAngleDegrees = 14f,
        strokeAlpha = 0.88f,
        cornerRadius = 999.dp,
        shadowElevation = 3.dp,
        width = 82.dp,
        height = 48.dp,
        thumbDiameter = 34.dp,
        thumbStrokeWidth = 1.5.dp,
        pressedScale = 0.988f,
        animationDurationMillis = 220,
        disabledAlpha = 0.5f,
        activeTint = Color(0xFF42D25A),
        activeTintEdge = Color(0xFF57DB70),
        inactiveTint = Color(0xFFE6EBF1),
    )

    val HighFidelity = LegacyLiquidGlassSwitchSpec(
        shellBlur = 14.dp,
        liquidBlur = 10.dp,
        shellAlpha = 0.93f,
        highlightAngleDegrees = 12f,
        strokeAlpha = 0.94f,
        cornerRadius = 999.dp,
        shadowElevation = 4.dp,
        width = 84.dp,
        height = 50.dp,
        thumbDiameter = 36.dp,
        thumbStrokeWidth = 2.dp,
        pressedScale = 0.984f,
        animationDurationMillis = 240,
        disabledAlpha = 0.46f,
        activeTint = Color(0xFF43D45A),
        activeTintEdge = Color(0xFF59DD72),
        inactiveTint = Color(0xFFE7ECF2),
    )

    val Standard = HighPerformance
    val AppleEnhanced = HighFidelity
}

@Composable
fun LiquidGlassBackdrop(
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
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
        GlassOrb(
            modifier = Modifier.size(320.dp).align(Alignment.TopStart).offset(x = (-96).dp, y = (-62).dp),
            colors = listOf(colors.accentBlue.copy(alpha = 0.04f), Color.Transparent),
        )
        GlassOrb(
            modifier = Modifier.size(340.dp).align(Alignment.TopEnd).offset(x = 62.dp, y = (-74).dp),
            colors = listOf(colors.accentBlue.copy(alpha = 0.06f), Color.Transparent),
        )
        GlassOrb(
            modifier = Modifier.size(300.dp).align(Alignment.CenterStart).offset(x = (-126).dp, y = 64.dp),
            colors = listOf(Color.White.copy(alpha = 0.2f), Color.Transparent),
        )
        GlassOrb(
            modifier = Modifier.size(320.dp).align(Alignment.CenterEnd).offset(x = 118.dp, y = 82.dp),
            colors = listOf(colors.accentBlue.copy(alpha = 0.03f), Color.Transparent),
        )
        GlassOrb(
            modifier = Modifier.size(360.dp).align(Alignment.BottomCenter).offset(y = 120.dp),
            colors = listOf(colors.accentBlue.copy(alpha = 0.025f), Color.Transparent),
        )
        GlassOrb(
            modifier = Modifier.size(240.dp).align(Alignment.Center).offset(y = (-24).dp),
            colors = listOf(Color.White.copy(alpha = 0.12f), Color.Transparent),
        )
        Box(
            modifier = Modifier.fillMaxSize().background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.1f),
                        Color.White.copy(alpha = 0.04f),
                        colors.backgroundSecondary.copy(alpha = 0.12f),
                    ),
                ),
            ),
        )
    }
}

@Composable
fun GlassPanel(
    modifier: Modifier = Modifier,
    shape: Shape = RoundedCornerShape(32.dp),
    contentPadding: PaddingValues = PaddingValues(24.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = modifier
            .shadow(
                elevation = 8.dp,
                shape = shape,
                clip = false,
                ambientColor = Color.White.copy(alpha = 0.08f),
                spotColor = Color(0x141C1C1E),
            )
            .clip(shape)
            .background(colors.cardBackgroundStrong.copy(alpha = 0.96f))
            .border(
                width = 1.dp,
                color = colors.cardStroke,
                shape = shape,
            ),
    ) {
        Box(
            modifier = Modifier.matchParentSize().background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = 0.16f),
                        Color.Transparent,
                        colors.accentBlue.copy(alpha = 0.02f),
                    ),
                ),
            ),
        )
        Column(
            modifier = Modifier.fillMaxWidth().padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content,
        )
    }
}

@Composable
fun GlassInsetBlock(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 14.dp, vertical = 12.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(22.dp))
            .background(colors.cardBackground.copy(alpha = 0.94f))
            .border(1.dp, colors.cardStroke, RoundedCornerShape(22.dp))
            .padding(contentPadding),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        content = content,
    )
}

@Composable
fun ScreenHeader(
    eyebrow: String,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SectionLabel(text = eyebrow)
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.72f),
        )
    }
}

@Composable
fun GlassActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    emphasized: Boolean = false,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val shape = RoundedCornerShape(22.dp)
    val backgroundBrush = if (emphasized) {
        Brush.verticalGradient(
            colors = listOf(
                colors.accentBlueSoft,
                colors.cardBackgroundStrong,
            ),
        )
    } else {
        Brush.verticalGradient(
            colors = listOf(
                colors.cardBackgroundStrong.copy(alpha = 0.94f),
                colors.cardBackground.copy(alpha = 0.9f),
            ),
        )
    }

    Box(
        modifier = modifier
            .clip(shape)
            .background(backgroundBrush)
            .border(
                width = 1.dp,
                color = if (emphasized) colors.accentBlue.copy(alpha = 0.12f) else colors.cardStroke,
                shape = shape,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
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
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun GlassChipButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    emphasized: Boolean = false,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val shape = RoundedCornerShape(18.dp)
    Box(
        modifier = modifier
            .widthIn(min = 86.dp)
            .clip(shape)
            .background(
                brush = Brush.verticalGradient(
                    colors = if (emphasized) {
                        listOf(colors.accentBlueSoft, colors.cardBackgroundStrong)
                    } else {
                        listOf(colors.cardBackgroundStrong.copy(alpha = 0.94f), colors.cardBackground.copy(alpha = 0.9f))
                    },
                ),
            )
            .border(
                width = 1.dp,
                color = if (emphasized) colors.accentBlue.copy(alpha = 0.12f) else colors.cardStroke,
                shape = shape,
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            )
            .padding(horizontal = 14.dp, vertical = 10.dp),
    ) {
        Text(
            text = text,
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun StatusPill(
    text: String,
    accent: Color,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(999.dp))
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        colors.cardBackgroundStrong.copy(alpha = 0.92f),
                        accent.copy(alpha = 0.08f),
                    ),
                ),
            )
            .border(1.dp, colors.cardStroke, RoundedCornerShape(999.dp))
            .padding(horizontal = 12.dp, vertical = 8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(modifier = Modifier.size(8.dp).background(color = accent, shape = CircleShape))
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.SemiBold,
            color = MaterialTheme.colorScheme.onSurface,
        )
    }
}

@Composable
fun DetailRow(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
    valueColor: Color = MaterialTheme.colorScheme.onSurface,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.Top,
    ) {
        Text(
            text = label,
            modifier = Modifier.width(84.dp),
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.66f),
        )
        Text(
            text = value,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyMedium,
            color = valueColor,
            textAlign = TextAlign.Start,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Composable
fun GlassDivider(
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(modifier = modifier.fillMaxWidth().height(1.dp).background(colors.dividerColor))
}

@Composable
fun SectionLabel(
    text: String,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Text(
        text = text,
        modifier = modifier,
        style = MaterialTheme.typography.labelLarge,
        fontWeight = FontWeight.SemiBold,
        color = colors.textSecondary,
    )
}

@Composable
fun LegacyLiquidGlassSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    quality: LegacyLiquidGlassSwitchQuality = LegacyLiquidGlassSwitchQuality.HighFidelity,
    spec: LegacyLiquidGlassSwitchSpec = if (quality == LegacyLiquidGlassSwitchQuality.HighFidelity) {
        LegacyLiquidGlassSwitchDefaults.HighFidelity
    } else {
        LegacyLiquidGlassSwitchDefaults.HighPerformance
    },
    size: DpSize = DpSize(spec.width, spec.height),
    activeTrackColor: Color = spec.activeTint,
    inactiveTrackColor: Color = spec.inactiveTint,
) {
    val tunedSpec = spec.copy(
        activeTint = activeTrackColor,
        inactiveTint = inactiveTrackColor,
    )
    when (quality) {
        LegacyLiquidGlassSwitchQuality.HighFidelity -> LegacyHighFidelityLiquidGlassSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            size = size,
            spec = tunedSpec,
        )

        LegacyLiquidGlassSwitchQuality.HighPerformance -> LegacyHighPerformanceLiquidGlassSwitch(
            checked = checked,
            onCheckedChange = onCheckedChange,
            modifier = modifier,
            enabled = enabled,
            size = size,
            spec = tunedSpec,
        )
    }
}

@Composable
fun LegacyStatefulLiquidGlassSwitch(
    initialChecked: Boolean,
    onChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    quality: LegacyLiquidGlassSwitchQuality = LegacyLiquidGlassSwitchQuality.HighFidelity,
    spec: LegacyLiquidGlassSwitchSpec = if (quality == LegacyLiquidGlassSwitchQuality.HighFidelity) {
        LegacyLiquidGlassSwitchDefaults.HighFidelity
    } else {
        LegacyLiquidGlassSwitchDefaults.HighPerformance
    },
    size: DpSize = DpSize(spec.width, spec.height),
    activeTrackColor: Color = spec.activeTint,
    inactiveTrackColor: Color = spec.inactiveTint,
) {
    var checked by rememberSaveable(initialChecked) { mutableStateOf(initialChecked) }

    LegacyLiquidGlassSwitch(
        checked = checked,
        onCheckedChange = {
            checked = it
            onChange(it)
        },
        modifier = modifier,
        enabled = enabled,
        quality = quality,
        spec = spec,
        size = size,
        activeTrackColor = activeTrackColor,
        inactiveTrackColor = inactiveTrackColor,
    )
}

@Composable
private fun LegacyHighFidelityLiquidGlassSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    size: DpSize,
    spec: LegacyLiquidGlassSwitchSpec,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val progress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(
            durationMillis = spec.animationDurationMillis,
            easing = LegacyLiquidGlassSwitchMotionEasing,
        ),
        label = "liquid-glass-switch-progress",
    )
    val pressScale by animateFloatAsState(
        targetValue = if (pressed && enabled) spec.pressedScale else 1f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 620f),
        label = "liquid-glass-switch-press-scale",
    )
    val shellTopColor by animateColorAsState(
        targetValue = if (checked) Color.White.copy(alpha = spec.shellAlpha) else Color.White.copy(alpha = spec.shellAlpha * 0.98f),
        animationSpec = tween(spec.animationDurationMillis, easing = LegacyLiquidGlassSwitchMotionEasing),
        label = "liquid-glass-switch-shell-top",
    )
    val shellBottomColor by animateColorAsState(
        targetValue = if (checked) spec.inactiveTint.copy(alpha = 0.8f) else spec.inactiveTint.copy(alpha = 0.92f),
        animationSpec = tween(spec.animationDurationMillis, easing = LegacyLiquidGlassSwitchMotionEasing),
        label = "liquid-glass-switch-shell-bottom",
    )
    val liquidStartColor by animateColorAsState(
        targetValue = spec.activeTint,
        animationSpec = tween(spec.animationDurationMillis, easing = LegacyLiquidGlassSwitchMotionEasing),
        label = "liquid-glass-switch-liquid-start",
    )
    val liquidEndColor by animateColorAsState(
        targetValue = spec.activeTintEdge,
        animationSpec = tween(spec.animationDurationMillis, easing = LegacyLiquidGlassSwitchMotionEasing),
        label = "liquid-glass-switch-liquid-end",
    )
    val thumbBorderColor by animateColorAsState(
        targetValue = if (checked) spec.activeTint else Color.White.copy(alpha = 0.6f),
        animationSpec = tween(spec.animationDurationMillis, easing = LegacyLiquidGlassSwitchMotionEasing),
        label = "liquid-glass-switch-thumb-border",
    )
    val shellShape = RoundedCornerShape(spec.cornerRadius)
    val thumbShape = RoundedCornerShape(999.dp)
    val stretch = (4f * progress * (1f - progress)).coerceIn(0f, 1f)

    BoxWithConstraints(
        modifier = modifier
            .width(size.width)
            .height(size.height)
            .graphicsLayer {
                scaleX = pressScale
                scaleY = pressScale
                alpha = if (enabled) 1f else spec.disabledAlpha
            }
            .shadow(
                elevation = spec.shadowElevation,
                shape = shellShape,
                clip = false,
                ambientColor = Color.White.copy(alpha = 0.14f),
                spotColor = Color(0x18000000),
            )
            .clip(shellShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(shellTopColor, shellBottomColor),
                ),
            )
            .border(
                width = 1.dp,
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = spec.strokeAlpha),
                        Color.White.copy(alpha = 0.14f),
                    ),
                ),
                shape = shellShape,
            )
            .clickable(
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null,
                onClick = { onCheckedChange(!checked) },
            ),
    ) {
        val shellInset = maxHeight * 0.08f
        val liquidHeight = maxHeight - shellInset * 2
        val thumbHeight = maxHeight - shellInset * 2 - 2.dp
        val thumbBaseWidth = thumbHeight * 0.9f
        val thumbWidth = thumbBaseWidth + thumbHeight * 0.1f * stretch
        val thumbOffset = lerp(shellInset + 2.dp, maxWidth - thumbWidth - shellInset - 2.dp, progress)
        val thumbTop = (maxHeight - thumbHeight) / 2
        val liquidTargetWidth = thumbOffset + thumbWidth - shellInset + maxWidth * 0.03f
        val liquidWidth = liquidTargetWidth * progress
        val sheenOffset = lerp((-16).dp, liquidWidth - 14.dp, progress)

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = shellInset - 2.dp, y = shellInset)
                .width(liquidWidth + 4.dp)
                .height(liquidHeight)
                .blur(spec.liquidBlur)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            spec.activeTintEdge.copy(alpha = 0.08f + progress * 0.12f),
                            Color.Transparent,
                        ),
                    ),
                    shape = thumbShape,
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = shellInset, y = shellInset)
                .width(liquidWidth)
                .height(liquidHeight)
                .clip(thumbShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(liquidStartColor, liquidEndColor),
                    ),
                )
                .border(
                    width = 1.dp,
                    color = Color.White.copy(alpha = 0.14f + progress * 0.08f),
                    shape = thumbShape,
                ),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 8.dp, y = 4.dp)
                    .fillMaxWidth(0.46f)
                    .height(5.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.42f),
                                Color.White.copy(alpha = 0.08f),
                            ),
                        ),
                    ),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = sheenOffset)
                    .width(18.dp)
                    .fillMaxHeight()
                    .blur(8.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.16f + stretch * 0.12f),
                                Color.Transparent,
                            ),
                        ),
                    ),
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = thumbOffset - 6.dp, y = thumbTop - 2.dp)
                .width(thumbWidth + 12.dp)
                .height(thumbHeight + 10.dp)
                .blur(spec.shellBlur)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.22f),
                            Color.Transparent,
                        ),
                    ),
                    shape = CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = thumbOffset, y = thumbTop)
                .width(thumbWidth)
                .height(thumbHeight)
                .shadow(
                    elevation = spec.shadowElevation + 1.dp,
                    shape = thumbShape,
                    clip = false,
                    ambientColor = Color.White.copy(alpha = 0.18f),
                    spotColor = Color(0x16000000),
                )
                .clip(thumbShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Color(0xFFF8FAFC)),
                    ),
                )
                .border(
                    width = spec.thumbStrokeWidth,
                    color = thumbBorderColor,
                    shape = thumbShape,
                ),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 5.dp, y = 4.dp)
                    .fillMaxWidth(0.44f)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.9f)),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = lerp((-8).dp, thumbWidth - 10.dp, progress))
                    .width(8.dp)
                    .fillMaxHeight(0.72f)
                    .blur(5.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.16f + stretch * 0.1f),
                                Color.Transparent,
                            ),
                        ),
                    ),
            )
            GlassSpecularLayer(
                modifier = Modifier.matchParentSize(),
                shape = thumbShape,
                highlightAngleDegrees = spec.highlightAngleDegrees - 8f,
                highlightAlpha = 0.12f,
                breathe = 1f,
            )
        }
        GlassSpecularLayer(
            modifier = Modifier.matchParentSize(),
            shape = shellShape,
            highlightAngleDegrees = spec.highlightAngleDegrees,
            highlightAlpha = 0.14f,
            breathe = 1f,
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = shellInset)
                .fillMaxWidth(0.82f)
                .height(5.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.48f),
                            Color.White.copy(alpha = 0.1f),
                            Color.Transparent,
                        ),
                    ),
                ),
        )
    }
}

@Composable
private fun LegacyHighPerformanceLiquidGlassSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier,
    enabled: Boolean,
    size: DpSize,
    spec: LegacyLiquidGlassSwitchSpec,
) {
    val interactionSource = remember { MutableInteractionSource() }
    val pressed by interactionSource.collectIsPressedAsState()
    val progress by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = tween(
            durationMillis = spec.animationDurationMillis,
            easing = LegacyLiquidGlassSwitchMotionEasing,
        ),
        label = "liquid-glass-switch-performance-progress",
    )
    val pressScale by animateFloatAsState(
        targetValue = if (pressed && enabled) spec.pressedScale else 1f,
        animationSpec = spring(dampingRatio = 0.7f, stiffness = 620f),
        label = "liquid-glass-switch-performance-scale",
    )
    val shellBottomColor by animateColorAsState(
        targetValue = if (checked) spec.inactiveTint.copy(alpha = 0.84f) else spec.inactiveTint.copy(alpha = 0.92f),
        animationSpec = tween(spec.animationDurationMillis, easing = LegacyLiquidGlassSwitchMotionEasing),
        label = "liquid-glass-switch-performance-shell",
    )
    val thumbBorderColor by animateColorAsState(
        targetValue = if (checked) spec.activeTint else Color.White.copy(alpha = 0.58f),
        animationSpec = tween(spec.animationDurationMillis, easing = LegacyLiquidGlassSwitchMotionEasing),
        label = "liquid-glass-switch-performance-thumb-border",
    )
    val shellShape = RoundedCornerShape(spec.cornerRadius)
    val thumbShape = RoundedCornerShape(999.dp)
    val stretch = (4f * progress * (1f - progress)).coerceIn(0f, 1f)

    BoxWithConstraints(
        modifier = modifier
            .width(size.width)
            .height(size.height)
            .graphicsLayer {
                scaleX = pressScale
                scaleY = pressScale
                alpha = if (enabled) 1f else spec.disabledAlpha
            }
            .shadow(
                elevation = spec.shadowElevation,
                shape = shellShape,
                clip = false,
                ambientColor = Color.White.copy(alpha = 0.12f),
                spotColor = Color(0x14000000),
            )
            .clip(shellShape)
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = spec.shellAlpha),
                        shellBottomColor,
                    ),
                ),
            )
            .border(
                width = 1.dp,
                color = Color.White.copy(alpha = spec.strokeAlpha),
                shape = shellShape,
            )
            .clickable(
                enabled = enabled,
                role = Role.Switch,
                interactionSource = interactionSource,
                indication = null,
                onClick = { onCheckedChange(!checked) },
            ),
    ) {
        val shellInset = maxHeight * 0.08f
        val liquidHeight = maxHeight - shellInset * 2
        val thumbHeight = maxHeight - shellInset * 2 - 2.dp
        val thumbWidth = thumbHeight * (0.9f + 0.08f * stretch)
        val thumbOffset = lerp(shellInset + 2.dp, maxWidth - thumbWidth - shellInset - 2.dp, progress)
        val thumbTop = (maxHeight - thumbHeight) / 2
        val liquidWidth = (thumbOffset + thumbWidth - shellInset + maxWidth * 0.02f) * progress

        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = shellInset, y = shellInset)
                .width(liquidWidth)
                .height(liquidHeight)
                .clip(thumbShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(spec.activeTint, spec.activeTintEdge),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = thumbOffset, y = thumbTop)
                .width(thumbWidth)
                .height(thumbHeight)
                .shadow(
                    elevation = spec.shadowElevation,
                    shape = thumbShape,
                    clip = false,
                    ambientColor = Color.White.copy(alpha = 0.12f),
                    spotColor = Color(0x14000000),
                )
                .clip(thumbShape)
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.White, Color(0xFFF7FAFC)),
                    ),
                )
                .border(
                    width = spec.thumbStrokeWidth,
                    color = thumbBorderColor,
                    shape = thumbShape,
                ),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .offset(x = 5.dp, y = 4.dp)
                    .fillMaxWidth(0.42f)
                    .height(4.dp)
                    .clip(CircleShape)
                    .background(Color.White.copy(alpha = 0.84f)),
            )
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = shellInset)
                .fillMaxWidth(0.78f)
                .height(4.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.42f),
                            Color.White.copy(alpha = 0.08f),
                            Color.Transparent,
                        ),
                    ),
                ),
        )
    }
}

@Composable
fun LegacyLiquidGlassBottomBar(
    items: List<GlassBottomBarItem>,
    selectedIndex: Int,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    spec: LiquidGlassBottomBarSpec = LiquidGlassBottomBarDefaults.AppleEnhanced,
) {
    val breatheTransition = rememberInfiniteTransition(label = "liquid-glass-bottom-bar-breathe")
    val breathe by breatheTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.08f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 4200, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
        ),
        label = "liquid-glass-bottom-bar-breathe-alpha",
    )
    val shimmer by breatheTransition.animateFloat(
        initialValue = -0.2f,
        targetValue = 1.18f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 2800, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "liquid-glass-bottom-bar-sheen",
    )
    val shape = RoundedCornerShape(spec.cornerRadius)
    val barPrismColor = Color(0xFFE6F8FF)

    BoxWithConstraints(
        modifier = modifier
            .height(spec.barHeight)
            .shadow(
                elevation = spec.shadowElevation,
                shape = shape,
                clip = false,
                ambientColor = Color.White.copy(alpha = 0.14f),
                spotColor = Color(0x335F9FFF).copy(alpha = 0.5f),
            )
            .clip(shape)
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(
                        Color.White.copy(alpha = spec.fillAlpha),
                        spec.warmTint.copy(alpha = spec.fillAlpha * 0.64f),
                        spec.activeTint.copy(alpha = spec.fillAlpha * 0.58f),
                    ),
                ),
            )
            .border(
                width = 1.dp,
                brush = Brush.linearGradient(
                    colors = listOf(
                        Color.White.copy(alpha = spec.strokeAlpha),
                        Color.White.copy(alpha = spec.strokeAlpha * 0.2f),
                    ),
                ),
                shape = shape,
            ),
    ) {
        val itemWidth = maxWidth / items.size
        val indicatorWidth = itemWidth - spec.indicatorInset * 2
        val indicatorOffset by animateDpAsState(
            targetValue = itemWidth * selectedIndex + spec.indicatorInset,
            animationSpec = spring(dampingRatio = 0.82f, stiffness = 520f),
            label = "bottom-bar-indicator-offset",
        )

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.58f)
                .align(Alignment.CenterStart)
                .offset(x = (-26).dp, y = (-4).dp)
                .blur(spec.fauxBackdropBlur)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.26f * breathe),
                            barPrismColor.copy(alpha = 0.16f * breathe),
                            Color.Transparent,
                        ),
                    ),
                    shape = CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.52f)
                .align(Alignment.CenterEnd)
                .offset(x = 20.dp, y = 4.dp)
                .blur(spec.fauxBackdropBlur)
                .background(
                    brush = Brush.radialGradient(
                        colors = listOf(
                            spec.warmTint.copy(alpha = 0.18f * breathe),
                            Color(0xFFF4F8FF).copy(alpha = 0.1f * breathe),
                            Color.Transparent,
                        ),
                    ),
                    shape = CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = maxWidth * shimmer - 88.dp)
                .width(104.dp)
                .fillMaxHeight(0.92f)
                .blur(spec.fauxBackdropBlur + 2.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Color.White.copy(alpha = 0.2f * breathe),
                            barPrismColor.copy(alpha = 0.08f * breathe),
                            Color.Transparent,
                        ),
                    ),
                    shape = CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 5.dp)
                .fillMaxWidth(0.9f)
                .height(13.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.68f),
                            Color.White.copy(alpha = 0.22f),
                            Color.Transparent,
                        ),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 6.dp)
                .fillMaxWidth(0.82f)
                .height(8.dp)
                .clip(CircleShape)
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.Transparent,
                            barPrismColor.copy(alpha = 0.12f * breathe),
                            Color.White.copy(alpha = 0.08f * breathe),
                            Color.Transparent,
                        ),
                    ),
                ),
        )
        GlassSpecularLayer(
            modifier = Modifier.matchParentSize(),
            shape = shape,
            highlightAngleDegrees = spec.highlightAngleDegrees,
            highlightAlpha = 0.34f,
            breathe = breathe,
        )
        Box(
            modifier = Modifier
                .matchParentSize()
                .padding(1.5.dp)
                .border(
                    width = 0.8.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.34f),
                            Color.White.copy(alpha = 0.08f),
                            barPrismColor.copy(alpha = 0.16f),
                        ),
                    ),
                    shape = shape,
                ),
        )

        Box(
            modifier = Modifier
                .padding(vertical = 7.dp)
                .offset(x = indicatorOffset)
                .width(indicatorWidth)
                .fillMaxHeight()
                .shadow(
                    elevation = spec.shadowElevation + 2.dp,
                    shape = RoundedCornerShape(spec.indicatorCornerRadius),
                    clip = false,
                    ambientColor = Color.White.copy(alpha = 0.18f),
                    spotColor = spec.activeTint.copy(alpha = spec.indicatorGlowAlpha),
                )
                .clip(RoundedCornerShape(spec.indicatorCornerRadius))
                .background(
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.98f),
                            spec.activeTint.copy(alpha = 0.82f),
                            spec.warmTint.copy(alpha = 0.72f),
                        ),
                    ),
                )
                .border(
                    width = 1.dp,
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color.White.copy(alpha = 0.98f),
                            Color.White.copy(alpha = 0.26f),
                        ),
                    ),
                    shape = RoundedCornerShape(spec.indicatorCornerRadius),
                ),
        ) {
            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(width = indicatorWidth * 0.58f, height = spec.barHeight - 28.dp)
                    .blur(22.dp)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                barPrismColor.copy(alpha = 0.22f * breathe),
                                Color.Transparent,
                            ),
                        ),
                        shape = CircleShape,
                    ),
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(0.62f)
                    .align(Alignment.CenterStart)
                    .offset(x = (-12).dp)
                    .blur(spec.fauxBackdropBlur)
                    .background(
                        brush = Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.24f * breathe),
                                spec.activeTint.copy(alpha = spec.indicatorGlowAlpha * breathe),
                                Color.Transparent,
                            ),
                        ),
                        shape = CircleShape,
                    ),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .offset(x = indicatorWidth * shimmer - 42.dp)
                    .width(64.dp)
                    .fillMaxHeight(0.88f)
                    .blur(18.dp)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.3f + 0.1f * breathe),
                                barPrismColor.copy(alpha = 0.14f),
                                Color.Transparent,
                            ),
                        ),
                        shape = CircleShape,
                    ),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 4.dp)
                    .fillMaxWidth(0.82f)
                    .height(12.dp)
                    .clip(CircleShape)
                    .background(
                        brush = Brush.horizontalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.74f),
                                Color.White.copy(alpha = 0.18f),
                                Color.Transparent,
                            ),
                        ),
                    ),
            )
            GlassSpecularLayer(
                modifier = Modifier.matchParentSize(),
                shape = RoundedCornerShape(spec.indicatorCornerRadius),
                highlightAngleDegrees = spec.highlightAngleDegrees - 6f,
                highlightAlpha = 0.38f,
                breathe = breathe,
            )
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .padding(1.5.dp)
                    .border(
                        width = 0.8.dp,
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.42f),
                                Color.White.copy(alpha = 0.1f),
                                barPrismColor.copy(alpha = 0.2f),
                            ),
                        ),
                        shape = RoundedCornerShape(spec.indicatorCornerRadius),
                    ),
            )
        }

        Row(modifier = Modifier.fillMaxSize()) {
            items.forEachIndexed { index, item ->
                val selected = index == selectedIndex
                val tint by animateColorAsState(
                    targetValue = if (selected) spec.activeContentColor else spec.inactiveContentColor,
                    label = "bottom-bar-item-tint-$index",
                )
                val itemLift by animateDpAsState(
                    targetValue = if (selected) (-1).dp else 0.dp,
                    animationSpec = spring(dampingRatio = 0.82f, stiffness = 520f),
                    label = "bottom-bar-item-lift-$index",
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
                        .padding(horizontal = 8.dp, vertical = 13.dp)
                        .offset(y = itemLift),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.label,
                        modifier = Modifier.size(if (selected) 21.dp else 19.dp),
                        tint = tint,
                    )
                    Text(
                        text = item.label,
                        style = MaterialTheme.typography.labelLarge,
                        fontWeight = if (selected) FontWeight.SemiBold else FontWeight.Medium,
                        color = tint,
                    )
                }
            }
        }
    }
}

@Composable
private fun GlassSpecularLayer(
    modifier: Modifier,
    shape: Shape,
    highlightAngleDegrees: Float,
    highlightAlpha: Float,
    breathe: Float,
) {
    Box(
        modifier = modifier
            .clip(shape)
            .drawWithCache {
                val (start, end) = gradientLineForAngle(
                    angleDegrees = highlightAngleDegrees,
                    width = size.width,
                    height = size.height,
                )
                val radius = size.height / 2f
                onDrawBehind {
                    drawRoundRect(
                        brush = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = highlightAlpha * (0.96f + 0.08f * breathe)),
                                Color.White.copy(alpha = highlightAlpha * 0.26f),
                                Color.Transparent,
                            ),
                            start = start,
                            end = end,
                        ),
                        cornerRadius = CornerRadius(radius, radius),
                        blendMode = BlendMode.Screen,
                    )
                    drawRoundRect(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = highlightAlpha * (0.66f + 0.08f * breathe)),
                                Color.Transparent,
                            ),
                            endY = size.height * 0.48f,
                        ),
                        size = Size(size.width, size.height * 0.48f),
                        cornerRadius = CornerRadius(radius, radius),
                        blendMode = BlendMode.Screen,
                    )
                }
            },
    )
}

private val LegacyLiquidGlassSwitchMotionEasing = CubicBezierEasing(0.22f, 1f, 0.36f, 1f)

private fun gradientLineForAngle(
    angleDegrees: Float,
    width: Float,
    height: Float,
): Pair<Offset, Offset> {
    val radians = Math.toRadians(angleDegrees.toDouble())
    val radius = hypot(width.toDouble(), height.toDouble()).toFloat() / 2f
    val dx = cos(radians).toFloat() * radius
    val dy = sin(radians).toFloat() * radius
    val centerX = width / 2f
    val centerY = height / 2f
    return Offset(centerX - dx, centerY - dy) to Offset(centerX + dx, centerY + dy)
}

@Composable
private fun GlassOrb(
    modifier: Modifier,
    colors: List<Color>,
) {
    Box(
        modifier = modifier.blur(96.dp).background(
            brush = Brush.radialGradient(colors = colors),
            shape = CircleShape,
        ),
    )
}
