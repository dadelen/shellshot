package com.example.shellshot.ui.components

import android.content.res.Configuration
import android.graphics.BlurMaskFilter
import android.graphics.Paint
import android.graphics.RectF
import android.graphics.RenderEffect
import android.graphics.Shader
import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.Article
import androidx.compose.material.icons.rounded.DashboardCustomize
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.asComposeRenderEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.theme.ShellShotTheme
import kotlinx.coroutines.delay

private val DockHeight = 68.dp
private val DockBottomPadding = 32.dp
private val DockHorizontalPadding = 16.dp
private val DockContainerPadding = 6.dp
private val DockGap = 4.dp
private val DockInactiveWidth = 56.dp
private val DockActiveWidth = 72.dp
private val DockShape = RoundedCornerShape(999.dp)
private val DockSliderShape = RoundedCornerShape(999.dp)
private val DockLabelReservedHeight = 14.dp

@Composable
fun BottomDock(
    items: List<NavItem>,
    selectedItemId: String,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
    darkTheme: Boolean = false,
) {
    val visibleItems = remember(items) { items.filter { it.visible } }
    if (visibleItems.isEmpty()) return

    val haptic = LocalHapticFeedback.current
    val density = LocalDensity.current
    val itemOffsetsPx = remember { mutableStateMapOf<String, Float>() }
    val itemWidthsPx = remember { mutableStateMapOf<String, Float>() }
    var previousSelectedId by remember { mutableStateOf(selectedItemId) }
    var sliderVisible by remember { mutableStateOf(false) }

    LaunchedEffect(selectedItemId) {
        if (previousSelectedId != selectedItemId) {
            previousSelectedId = selectedItemId
            sliderVisible = true
            delay(450)
            sliderVisible = false
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(bottom = DockBottomPadding),
        contentAlignment = Alignment.BottomCenter,
    ) {
        BoxWithConstraints(
            modifier = Modifier.padding(horizontal = DockHorizontalPadding),
            contentAlignment = Alignment.Center,
        ) {
            val dockWidth = DockActiveWidth + DockInactiveWidth * (visibleItems.size - 1) + DockGap * (visibleItems.size - 1) + DockContainerPadding * 2
            val selectedIndex = visibleItems.indexOfFirst { it.id == selectedItemId }.coerceAtLeast(0)
            val fallbackSliderOffsetPx = with(density) { ((DockInactiveWidth + DockGap) * selectedIndex).toPx() }
            val fallbackSliderWidthPx = with(density) { DockActiveWidth.toPx() }
            val sliderOffsetTargetPx = itemOffsetsPx[selectedItemId] ?: fallbackSliderOffsetPx
            val sliderWidthTargetPx = itemWidthsPx[selectedItemId] ?: fallbackSliderWidthPx

            val sliderOffsetPx by animateFloatAsState(
                targetValue = sliderOffsetTargetPx,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
                label = "zip-dock-shared-slider-x",
            )
            val sliderWidthPx by animateFloatAsState(
                targetValue = sliderWidthTargetPx,
                animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
                label = "zip-dock-shared-slider-width",
            )

            Box(
                modifier = Modifier
                    .width(dockWidth)
                    .height(DockHeight),
                contentAlignment = Alignment.Center,
            ) {
                ZipReferenceBase(
                    modifier = Modifier.fillMaxSize(),
                    darkTheme = darkTheme,
                )

                AnimatedVisibility(
                    visible = sliderVisible,
                    enter = fadeIn(),
                    exit = fadeOut(),
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(DockContainerPadding),
                        contentAlignment = Alignment.CenterStart,
                    ) {
                        ZipReferenceSlider(
                            modifier = Modifier
                                .width(with(density) { sliderWidthPx.toDp() })
                                .height(56.dp)
                                .graphicsLayer {
                                    translationX = sliderOffsetPx
                                },
                            darkTheme = darkTheme,
                        )
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(DockContainerPadding),
                    horizontalArrangement = Arrangement.spacedBy(DockGap),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    visibleItems.forEach { item ->
                        val selected = item.id == selectedItemId
                        DockButton(
                            id = item.id,
                            icon = item.icon,
                            label = item.label,
                            selected = selected,
                            darkTheme = darkTheme,
                            onPlaced = { itemOffsetsPx[item.id] = it },
                            onWidthChanged = { itemWidthsPx[item.id] = it },
                            onClick = {
                                if (!selected) {
                                    haptic.performHapticFeedback(HapticFeedbackType.LongPress)
                                    onItemSelected(item.id)
                                }
                            },
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ZipReferenceBase(
    modifier: Modifier,
    darkTheme: Boolean,
) {
    Box(
        modifier = modifier
            .background(
                color = if (darkTheme) Color.Black.copy(alpha = 0.40f) else Color.White.copy(alpha = 0.90f),
                shape = DockShape,
            )
            .border(
                width = 1.dp,
                color = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.White.copy(alpha = 0.40f),
                shape = DockShape,
            )
            .graphicsLayer {
                clip = true
                shape = DockShape
            }
            .drawWithCache {
                val cornerRadius = size.height / 2f
                val shadowRect = RectF(0f, 8.dp.toPx(), size.width, size.height + 8.dp.toPx())
                val shadowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = if (darkTheme) {
                        Color.Black.copy(alpha = 0.30f).toArgb()
                    } else {
                        Color.Black.copy(alpha = 0.10f).toArgb()
                    }
                    maskFilter = BlurMaskFilter(32.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
                }
                onDrawWithContent {
                    drawContext.canvas.nativeCanvas.drawRoundRect(
                        shadowRect,
                        cornerRadius,
                        cornerRadius,
                        shadowPaint,
                    )
                    drawContent()
                }
            },
    )
}

@Composable
private fun DockButton(
    id: String,
    icon: ImageVector,
    label: String,
    selected: Boolean,
    darkTheme: Boolean,
    onPlaced: (Float) -> Unit,
    onWidthChanged: (Float) -> Unit,
    onClick: () -> Unit,
) {
    val itemWidth by animateDpAsState(
        targetValue = if (selected) DockActiveWidth else DockInactiveWidth,
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing),
        label = "zip-dock-width",
    )
    val iconOffsetY by animateFloatAsState(
        targetValue = if (selected) -6f else 0f,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "zip-dock-icon-y",
    )
    val iconTint by animateColorAsState(
        targetValue = if (selected) Color(0xFF007AFF) else if (darkTheme) Color(0xFF8E8E93) else Color(0xFF737373),
        animationSpec = tween(durationMillis = 220, easing = FastOutSlowInEasing),
        label = "zip-dock-icon-color",
    )
    val labelAlpha by animateFloatAsState(
        targetValue = if (selected) 1f else 0f,
        animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        label = "zip-dock-label-alpha",
    )
    val labelOffsetY by animateFloatAsState(
        targetValue = if (selected) 0f else 4f,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "zip-dock-label-y",
    )
    val labelScale by animateFloatAsState(
        targetValue = if (selected) 1f else 0.8f,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "zip-dock-label-scale",
    )
    Box(
        modifier = Modifier
            .width(itemWidth)
            .height(56.dp)
            .onGloballyPositioned { onPlaced(it.positionInParent().x) }
            .onSizeChanged { onWidthChanged(it.width.toFloat()) }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(bottom = DockLabelReservedHeight),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = iconTint,
                modifier = Modifier
                    .graphicsLayer { translationY = iconOffsetY }
                    .size(22.dp),
            )
        }

        Text(
            text = label,
            color = Color(0xFF007AFF).copy(alpha = labelAlpha),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 4.dp)
                .graphicsLayer {
                    translationY = labelOffsetY
                    scaleX = labelScale
                    scaleY = labelScale
                },
        )
    }
}

@Composable
private fun ZipReferenceSlider(
    modifier: Modifier,
    darkTheme: Boolean,
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                clip = true
                shape = DockSliderShape
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = if (darkTheme) {
                        listOf(
                            Color.White.copy(alpha = 0.22f),
                            Color.White.copy(alpha = 0.08f),
                            Color.White.copy(alpha = 0.02f),
                        )
                    } else {
                        listOf(
                            Color.White.copy(alpha = 0.82f),
                            Color.White.copy(alpha = 0.34f),
                            Color.White.copy(alpha = 0.08f),
                        )
                    }
                ),
                shape = DockSliderShape,
            )
            .border(
                width = 0.5.dp,
                color = if (darkTheme) Color.White.copy(alpha = 0.20f) else Color.White.copy(alpha = 0.80f),
                shape = DockSliderShape,
            )
            .drawWithCache {
                val topGlare = Brush.verticalGradient(
                    colors = if (darkTheme) {
                        listOf(Color.White.copy(alpha = 0.30f), Color.Transparent)
                    } else {
                        listOf(Color.White.copy(alpha = 0.90f), Color.Transparent)
                    },
                    endY = size.height * 0.35f,
                )
                val bottomGlow = Brush.verticalGradient(
                    colors = if (darkTheme) {
                        listOf(Color.Transparent, Color.White.copy(alpha = 0.10f))
                    } else {
                        listOf(Color.Transparent, Color.White.copy(alpha = 0.40f))
                    },
                    startY = size.height * 0.8f,
                    endY = size.height,
                )
                val softShadow = Paint(Paint.ANTI_ALIAS_FLAG).apply {
                    color = if (darkTheme) {
                        Color.Black.copy(alpha = 0.30f).toArgb()
                    } else {
                        Color.Black.copy(alpha = 0.12f).toArgb()
                    }
                    maskFilter = BlurMaskFilter(16.dp.toPx(), BlurMaskFilter.Blur.NORMAL)
                }
                val shadowRect = RectF(0f, 4.dp.toPx(), size.width, size.height + 4.dp.toPx())
                val cornerRadius = size.height / 2f
                onDrawWithContent {
                    drawContext.canvas.nativeCanvas.drawRoundRect(
                        shadowRect,
                        cornerRadius,
                        cornerRadius,
                        softShadow,
                    )
                    drawContent()
                    drawRoundRect(topGlare, blendMode = BlendMode.Screen)
                    drawRoundRect(
                        bottomGlow,
                        topLeft = Offset(size.width * 0.16f, size.height * 0.8f),
                        size = Size(size.width * 0.68f, size.height * 0.2f),
                        blendMode = BlendMode.Screen,
                    )
                    drawLine(
                        color = if (darkTheme) Color.White.copy(alpha = 0.45f) else Color.White.copy(alpha = 1f),
                        start = Offset(size.width * 0.18f, 1.5.dp.toPx()),
                        end = Offset(size.width * 0.82f, 1.5.dp.toPx()),
                        strokeWidth = 1.1.dp.toPx(),
                        cap = StrokeCap.Round,
                    )
                    drawRoundRect(
                        color = if (darkTheme) Color.Black.copy(alpha = 0.20f) else Color.Black.copy(alpha = 0.05f),
                        style = Stroke(width = 0.5.dp.toPx()),
                    )
                }
            },
    )
}

@Preview(showBackground = true, backgroundColor = 0xFFF2F2F7)
@Composable
private fun BottomDockPreview() {
    ShellShotTheme {
        BottomDock(
            items = listOf(
                NavItem(id = "home", label = "首页", icon = Icons.Rounded.Home),
                NavItem(id = "templates", label = "模板", icon = Icons.Rounded.DashboardCustomize),
                NavItem(id = "settings", label = "设置", icon = Icons.Rounded.Settings),
                NavItem(id = "logs", label = "日志", icon = Icons.AutoMirrored.Rounded.Article),
            ),
            selectedItemId = "home",
            onItemSelected = {},
            darkTheme = false,
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF101114, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun BottomDockDarkPreview() {
    ShellShotTheme(darkTheme = true) {
        BottomDock(
            items = listOf(
                NavItem(id = "home", label = "首页", icon = Icons.Rounded.Home),
                NavItem(id = "templates", label = "模板", icon = Icons.Rounded.DashboardCustomize),
                NavItem(id = "settings", label = "设置", icon = Icons.Rounded.Settings),
                NavItem(id = "logs", label = "日志", icon = Icons.AutoMirrored.Rounded.Article),
            ),
            selectedItemId = "home",
            onItemSelected = {},
            darkTheme = true,
        )
    }
}
