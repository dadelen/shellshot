package com.example.shellshot.ui.components

import android.content.res.Configuration
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.RenderEffect as AndroidRenderEffect
import android.graphics.Shader
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.CompositingStrategy
import androidx.compose.ui.graphics.RenderEffect
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.example.shellshot.ui.theme.ShellShotTheme
import com.example.shellshot.ui.theme.shellShotTokens
import kotlin.math.abs
import kotlin.math.roundToInt

@Immutable
data class NavItem(
    val id: String,
    val label: String,
    val icon: AppIconId,
    val visible: Boolean = true,
)

private val AppleLiquidNavBarShape = RoundedCornerShape(32.dp)
private val AppleLiquidNavBarPillShape = RoundedCornerShape(28.dp)

@Immutable
private data class AppleLiquidNavBarMetrics(
    val barHeight: androidx.compose.ui.unit.Dp = 84.dp,
    val pillHorizontalInset: androidx.compose.ui.unit.Dp = 6.dp,
    val pillVerticalInset: androidx.compose.ui.unit.Dp = 6.dp,
    val iconSize: androidx.compose.ui.unit.Dp = 24.dp,
)

private val DefaultAppleLiquidNavBarMetrics = AppleLiquidNavBarMetrics()

@Composable
fun AppleLiquidNavBar(
    items: List<NavItem>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    if (items.isEmpty()) {
        return
    }

    val metrics = DefaultAppleLiquidNavBarMetrics
    val colors = MaterialTheme.shellShotTokens.colors
    val density = LocalDensity.current
    val hapticFeedback = LocalHapticFeedback.current
    val currentIndex = selectedIndex.coerceIn(0, items.lastIndex)
    val blurEffect = rememberAppleLiquidChainEffect()
    var lastHapticIndex by remember { mutableIntStateOf(currentIndex) }

    LaunchedEffect(currentIndex) {
        if (lastHapticIndex != currentIndex) {
            hapticFeedback.performHapticFeedback(HapticFeedbackType.LongPress)
            lastHapticIndex = currentIndex
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .fillMaxWidth()
                .height(metrics.barHeight)
                .graphicsLayer {
                    compositingStrategy = CompositingStrategy.Offscreen
                }
                .shadow(
                    elevation = 14.dp,
                    shape = AppleLiquidNavBarShape,
                    clip = false,
                    ambientColor = Color.White.copy(alpha = 0.08f),
                    spotColor = Color(0x24000000),
                )
                .clip(AppleLiquidNavBarShape)
                .background(Color.White.copy(alpha = 0.25f))
                .border(
                    width = 0.5.dp,
                    color = Color.White.copy(alpha = 0.15f),
                    shape = AppleLiquidNavBarShape,
                ),
        ) {
            val itemWidth = maxWidth / items.size
            val pillWidth = itemWidth - metrics.pillHorizontalInset * 2
            val itemWidthPx = with(density) { itemWidth.toPx() }
            val pillTargetOffsetPx = with(density) {
                (itemWidth * currentIndex + metrics.pillHorizontalInset).toPx()
            }
            val pillOffsetPx by animateFloatAsState(
                targetValue = pillTargetOffsetPx,
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessLow,
                ),
                label = "apple-liquid-nav-pill-offset",
            )

            var previousFrameOffsetPx by remember { mutableFloatStateOf(pillOffsetPx) }
            var previousFrameTimeNanos by remember { mutableLongStateOf(0L) }
            var motionDeltaPx by remember { mutableFloatStateOf(0f) }
            var motionVelocityPxPerSecond by remember { mutableFloatStateOf(0f) }

            LaunchedEffect(Unit) {
                snapshotFlow { pillOffsetPx }.collect { latestOffset ->
                    val frameTimeNanos = System.nanoTime()
                    motionDeltaPx = latestOffset - previousFrameOffsetPx
                    if (previousFrameTimeNanos != 0L) {
                        val deltaSeconds =
                            (frameTimeNanos - previousFrameTimeNanos) / 1_000_000_000f
                        if (deltaSeconds > 0f) {
                            val instantaneousVelocity = motionDeltaPx / deltaSeconds
                            motionVelocityPxPerSecond =
                                motionVelocityPxPerSecond * 0.72f + instantaneousVelocity * 0.28f
                        }
                    }
                    previousFrameOffsetPx = latestOffset
                    previousFrameTimeNanos = frameTimeNanos
                }
            }

            val stretchFraction = if (itemWidthPx == 0f) {
                0f
            } else {
                (
                    abs(motionDeltaPx) / itemWidthPx * 3.35f +
                        abs(motionVelocityPxPerSecond) / itemWidthPx * 0.05f
                    ).coerceIn(0f, 1f)
            }
            val pillTransformOriginX by animateFloatAsState(
                targetValue = when {
                    motionVelocityPxPerSecond > 12f -> 0.18f
                    motionVelocityPxPerSecond < -12f -> 0.82f
                    else -> 0.5f
                },
                animationSpec = spring(dampingRatio = 0.9f, stiffness = 520f),
                label = "apple-liquid-nav-pill-origin",
            )
            val pillScaleX by animateFloatAsState(
                targetValue = 1f + stretchFraction * 0.2f,
                animationSpec = spring(dampingRatio = 0.8f, stiffness = 400f),
                label = "apple-liquid-nav-pill-scale-x",
            )
            val pillScaleY by animateFloatAsState(
                targetValue = 1f - stretchFraction * 0.1f,
                animationSpec = spring(dampingRatio = 0.84f, stiffness = 420f),
                label = "apple-liquid-nav-pill-scale-y",
            )
            val motionBias = if (itemWidthPx == 0f) {
                0f
            } else {
                (motionVelocityPxPerSecond / (itemWidthPx * 10f)).coerceIn(-1f, 1f)
            }
            val pillBiasTranslationPx by animateFloatAsState(
                targetValue = with(density) { 5.dp.toPx() } * motionBias,
                animationSpec = spring(dampingRatio = 0.88f, stiffness = 520f),
                label = "apple-liquid-nav-pill-bias-translation",
            )

            // Keep the blurred material isolated so content changes underneath do not invalidate it.
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .graphicsLayer {
                        clip = true
                        shape = AppleLiquidNavBarShape
                        compositingStrategy = CompositingStrategy.Offscreen
                        renderEffect = blurEffect
                    }
                    .drawWithCache {
                        val baseBackdrop = Brush.linearGradient(
                            colors = listOf(
                                colors.backgroundPrimary.copy(alpha = 0.94f),
                                colors.backgroundSecondary.copy(alpha = 0.9f),
                            ),
                            start = Offset.Zero,
                            end = Offset(x = size.width, y = size.height),
                        )
                        val coolSweep = Brush.linearGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.14f),
                                colors.accentBlue.copy(alpha = 0.2f),
                                Color.Transparent,
                            ),
                            start = Offset(x = size.width * 0.18f, y = 0f),
                            end = Offset(x = size.width * 0.82f, y = size.height),
                        )
                        val warmBloom = Brush.radialGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.38f),
                                Color.Transparent,
                            ),
                            center = Offset(x = size.width * 0.22f, y = size.height * 0.25f),
                            radius = size.minDimension * 0.62f,
                        )
                        val lowerBloom = Brush.radialGradient(
                            colors = listOf(
                                colors.accentBlue.copy(alpha = 0.22f),
                                Color.Transparent,
                            ),
                            center = Offset(x = size.width * 0.8f, y = size.height * 0.74f),
                            radius = size.minDimension * 0.56f,
                        )
                        val refractedBand = Brush.linearGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.White.copy(alpha = 0.18f),
                                colors.accentBlue.copy(alpha = 0.08f),
                                Color.Transparent,
                            ),
                            start = Offset(x = size.width * 0.08f, y = 0f),
                            end = Offset(x = size.width * 0.72f, y = size.height),
                        )
                        val rimLight = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.22f),
                                Color.Transparent,
                            ),
                            endY = size.height * 0.42f,
                        )
                        onDrawBehind {
                            drawRoundRect(brush = baseBackdrop)
                            drawRoundRect(brush = coolSweep, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = warmBloom, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = lowerBloom, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = refractedBand, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = rimLight, blendMode = BlendMode.Screen)
                        }
                    },
            )

            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .drawWithCache {
                        val shellLight = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.18f),
                                Color.Transparent,
                            ),
                            endY = size.height * 0.35f,
                        )
                        val shellEdge = Brush.horizontalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.08f),
                                Color.Transparent,
                                Color.White.copy(alpha = 0.07f),
                            ),
                        )
                        val shellDepth = Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black.copy(alpha = 0.06f),
                            ),
                            startY = size.height * 0.66f,
                            endY = size.height,
                        )
                        onDrawBehind {
                            drawRoundRect(brush = shellLight, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = shellEdge, blendMode = BlendMode.Screen)
                            drawRoundRect(brush = shellDepth)
                        }
                    },
            )

            Box(
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(vertical = metrics.pillVerticalInset)
                    .offset {
                        IntOffset(
                            x = pillOffsetPx.roundToInt(),
                            y = 0,
                        )
                    }
                    .fillMaxHeight()
                    .width(pillWidth)
                    .shadow(
                        elevation = 7.dp,
                        shape = AppleLiquidNavBarPillShape,
                        clip = false,
                        ambientColor = Color.White.copy(alpha = 0.06f),
                        spotColor = Color.Black.copy(alpha = 0.18f),
                    )
                    .graphicsLayer {
                        compositingStrategy = CompositingStrategy.Offscreen
                        scaleX = pillScaleX
                        scaleY = pillScaleY
                        translationX = pillBiasTranslationPx
                        transformOrigin = TransformOrigin(pillTransformOriginX, 0.5f)
                    }
                    .clip(AppleLiquidNavBarPillShape)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.48f),
                                Color.White.copy(alpha = 0.26f),
                                colors.backgroundPrimary.copy(alpha = 0.1f),
                            ),
                        ),
                    )
                    .border(
                        width = 0.5.dp,
                        color = Color.White.copy(alpha = 0.28f),
                        shape = AppleLiquidNavBarPillShape,
                    ),
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .drawWithCache {
                            val gloss = Brush.verticalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.34f),
                                    Color.Transparent,
                                ),
                                endY = size.height * 0.42f,
                            )
                            val depth = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.08f),
                                ),
                                startY = size.height * 0.58f,
                                endY = size.height,
                            )
                            val travelingGlassBand = Brush.linearGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.White.copy(alpha = 0.2f),
                                    Color.White.copy(alpha = 0.06f),
                                    Color.Transparent,
                                ),
                                start = Offset(
                                    x = size.width * (0.12f + motionBias * 0.18f),
                                    y = 0f,
                                ),
                                end = Offset(
                                    x = size.width * (0.8f + motionBias * 0.22f),
                                    y = size.height,
                                ),
                            )
                            val edgeBloom = Brush.horizontalGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.04f),
                                    Color.White.copy(alpha = 0.16f),
                                    Color.White.copy(alpha = 0.04f),
                                ),
                            )
                            val centerHotspot = Brush.radialGradient(
                                colors = listOf(
                                    Color.White.copy(alpha = 0.18f),
                                    Color.Transparent,
                                ),
                                center = Offset(
                                    x = size.width * (0.5f + motionBias * 0.18f),
                                    y = size.height * 0.38f,
                                ),
                                radius = size.minDimension * 0.88f,
                            )
                            val lowerInnerShadow = Brush.verticalGradient(
                                colors = listOf(
                                    Color.Transparent,
                                    Color.Black.copy(alpha = 0.05f),
                                    Color.Black.copy(alpha = 0.1f),
                                ),
                                startY = size.height * 0.48f,
                                endY = size.height,
                            )
                            onDrawBehind {
                                drawRoundRect(brush = gloss, blendMode = BlendMode.Screen)
                                drawRoundRect(brush = travelingGlassBand, blendMode = BlendMode.Screen)
                                drawRoundRect(brush = edgeBloom, blendMode = BlendMode.Screen)
                                drawRoundRect(brush = centerHotspot, blendMode = BlendMode.Screen)
                                drawRoundRect(brush = depth)
                                drawRoundRect(brush = lowerInnerShadow)
                            }
                        },
                )
            }

            Row(modifier = Modifier.fillMaxSize()) {
                items.forEachIndexed { index, item ->
                    val selected = index == currentIndex
                    val iconTint by animateColorAsState(
                        targetValue = if (selected) {
                            colors.textPrimary.copy(alpha = 0.98f)
                        } else {
                            colors.textSecondary.copy(alpha = 0.88f)
                        },
                        label = "apple-liquid-nav-icon-tint-$index",
                    )
                    val iconScale by animateFloatAsState(
                        targetValue = if (selected) 1.07f else 0.9f,
                        animationSpec = spring(dampingRatio = 0.8f, stiffness = 500f),
                        label = "apple-liquid-nav-icon-scale-$index",
                    )
                    val iconLiftPx = with(density) {
                        (if (selected) (-1.5).dp else 0.dp).toPx()
                    }
                    val iconTranslationY by animateFloatAsState(
                        targetValue = iconLiftPx,
                        animationSpec = spring(dampingRatio = 0.82f, stiffness = 480f),
                        label = "apple-liquid-nav-icon-lift-$index",
                    )
                    val iconAlpha by animateFloatAsState(
                        targetValue = if (selected) 1f else 0.82f,
                        animationSpec = spring(dampingRatio = 0.9f, stiffness = 520f),
                        label = "apple-liquid-nav-icon-alpha-$index",
                    )
                    val labelAlpha by animateFloatAsState(
                        targetValue = if (selected) 0.98f else 0.68f,
                        animationSpec = spring(dampingRatio = 0.9f, stiffness = 520f),
                        label = "apple-liquid-nav-label-alpha-$index",
                    )
                    val labelLiftPx by animateFloatAsState(
                        targetValue = with(density) { (if (selected) 0.dp else 1.dp).toPx() },
                        animationSpec = spring(dampingRatio = 0.9f, stiffness = 520f),
                        label = "apple-liquid-nav-label-lift-$index",
                    )

                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                role = Role.Tab,
                                onClick = { onItemSelected(index) },
                            ),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(vertical = 8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                        ) {
                            AppIcon(
                                icon = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier
                                    .size(metrics.iconSize)
                                    .graphicsLayer {
                                        scaleX = iconScale
                                        scaleY = iconScale
                                        alpha = iconAlpha
                                        translationY = iconTranslationY
                                    },
                                tint = iconTint,
                            )
                            Text(
                                text = item.label,
                                style = MaterialTheme.typography.labelSmall,
                                color = iconTint,
                                modifier = Modifier.graphicsLayer {
                                    alpha = labelAlpha
                                    translationY = labelLiftPx
                                },
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun rememberAppleLiquidChainEffect(): RenderEffect {
    return remember {
        val vibrancyMatrix = ColorMatrix().apply {
            setSaturation(1.4f)
            postConcat(
                ColorMatrix(
                    floatArrayOf(
                        1f, 0f, 0f, 0f, 8f,
                        0f, 1f, 0f, 0f, 8f,
                        0f, 0f, 1f, 0f, 10f,
                        0f, 0f, 0f, 1f, 0f,
                    ),
                ),
            )
        }
        val blur = AndroidRenderEffect.createBlurEffect(30f, 30f, Shader.TileMode.CLAMP)
        val vibrancy = AndroidRenderEffect.createColorFilterEffect(
            ColorMatrixColorFilter(vibrancyMatrix),
        )
        AndroidRenderEffect.createChainEffect(vibrancy, blur).asComposeRenderEffect()
    }
}

@Preview(name = "Apple Liquid Nav Bar", showBackground = true, backgroundColor = 0xFFF3F5F8)
@Preview(
    name = "Apple Liquid Nav Bar Dark",
    showBackground = true,
    backgroundColor = 0xFF131418,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
)
@Composable
private fun AppleLiquidNavBarPreview() {
    ShellShotTheme {
        var selectedIndex by remember { mutableIntStateOf(0) }
        val previewItems = listOf(
            NavItem(id = "home", label = "Home", icon = AppIconId.Home),
            NavItem(id = "templates", label = "Templates", icon = AppIconId.Template),
            NavItem(id = "settings", label = "Settings", icon = AppIconId.Settings),
            NavItem(id = "logs", label = "Logs", icon = AppIconId.Terminal),
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(180.dp),
        ) {
            AppBackdrop(modifier = Modifier.fillMaxSize())
            AppleLiquidNavBar(
                items = previewItems,
                selectedIndex = selectedIndex,
                onItemSelected = { selectedIndex = it },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(horizontal = 16.dp, vertical = 12.dp),
            )
        }
    }
}
