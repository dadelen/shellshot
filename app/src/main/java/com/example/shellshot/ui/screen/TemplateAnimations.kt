package com.example.shellshot.ui.screen

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.shellshot.template.ShellTemplate
import com.example.shellshot.ui.components.TemplatePreviewThumbnail
import com.example.shellshot.ui.designsystem.ioslike.IOSFlyInContainer
import com.example.shellshot.ui.designsystem.ioslike.iosSheetFlyInEnter
import com.example.shellshot.ui.designsystem.ioslike.iosSheetFlyOutExit
import com.example.shellshot.ui.theme.MotionConstants
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import kotlinx.coroutines.delay
import nl.dionsegijn.konfetti.compose.KonfettiView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import java.util.concurrent.TimeUnit
import kotlin.math.abs

@Composable
fun ExitingTemplateGhost(
    template: ShellTemplate,
    isDark: Boolean,
    modifier: Modifier = Modifier,
    onFinished: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    val progress = remember(template.id) { Animatable(0f) }

    LaunchedEffect(template.id) {
        progress.snapTo(0f)
        progress.animateTo(
            targetValue = 1f,
            animationSpec = tween(520, easing = MotionConstants.iosEaseInOut),
        )
        onFinished()
    }

    val tuck = (progress.value / 0.18f).coerceIn(0f, 1f)
    val exit = ((progress.value - 0.16f) / 0.84f).coerceIn(0f, 1f)
    val exitEased = MotionConstants.iosEaseInOut.transform(exit)
    val breathe = 1f - abs(tuck * 2f - 1f)
    val alpha = (1f - exitEased * 1.08f).coerceIn(0f, 1f)
    val scale = 1f - (0.018f * tuck) + (0.014f * breathe) - (0.18f * exitEased)
    val translateY = (8f * tuck) - (124f * exitEased)
    val translateX = 10f * exitEased
    val rotationZ = -2.8f * exitEased
    val rotationX = 7f * exitEased
    val shadowAlpha = (1f - exitEased).coerceIn(0f, 1f)
    val liftSheenAlpha = 0.10f * (1f - exitEased)
    val afterImageAlpha = 0.08f * (1f - exitEased)
    val cardShape = RoundedCornerShape(40.dp)

    Box(
        modifier = modifier
            .width(260.dp)
            .height(480.dp)
            .graphicsLayer {
                this.alpha = alpha
                scaleX = scale
                scaleY = scale
                translationY = translateY.dp.toPx()
                translationX = translateX.dp.toPx()
                this.rotationZ = rotationZ
                this.rotationX = rotationX
                cameraDistance = 18f * density
                shadowElevation = 34.dp.toPx() * shadowAlpha
                shape = cardShape
                clip = false
                transformOrigin = TransformOrigin(0.5f, 0.92f)
            }
            .background(
                brush = Brush.verticalGradient(
                    colors = if (isDark) {
                        listOf(
                            colors.surface,
                            colors.surfaceAlt,
                        )
                    } else {
                        listOf(
                            Color.White,
                            Color(0xFFF7F7FA),
                        )
                    },
                ),
                shape = cardShape,
            )
            .border(
                width = 4.dp,
                color = if (isDark) {
                    Color.White.copy(alpha = 0.06f)
                } else {
                    Color(0xFFF7F7F9).copy(alpha = 0.8f)
                },
                shape = cardShape,
            ),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 24.dp, vertical = 34.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(22.dp))
            Box(
                modifier = Modifier
                    .width(136.dp)
                    .height(228.dp),
                contentAlignment = Alignment.Center,
            ) {
                TemplatePreviewThumbnail(
                    previewPath = template.displayPreviewAsset,
                    contentDescription = template.name,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 4.dp, vertical = 6.dp),
                    cornerRadius = 24.dp,
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            Text(
                template.name,
                fontSize = 24.sp,
                fontWeight = FontWeight.Black,
                color = colors.textPrimary,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "${template.outputWidth.takeIf { it > 0 } ?: template.screenRect.width} × ${template.outputHeight.takeIf { it > 0 } ?: template.screenRect.height}",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                color = colors.textSecondary,
            )
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(
                            Color.White.copy(alpha = liftSheenAlpha),
                            Color.White.copy(alpha = liftSheenAlpha * 0.34f),
                            Color.Transparent,
                        ),
                    ),
                    cardShape,
                )
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .graphicsLayer {
                    this.alpha = afterImageAlpha
                    translationY = 12.dp.toPx()
                    scaleX = 0.985f
                    scaleY = 0.985f
                }
                .background(
                    if (isDark) Color.White.copy(alpha = 0.10f) else Color(0xFFD7E3F7).copy(alpha = 0.24f),
                    cardShape,
                )
        )
    }
}

@Composable
fun TemplateLoadingOverlay(isDark: Boolean) {
    val infiniteTransition = rememberInfiniteTransition(label = "loading")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(950),
            repeatMode = RepeatMode.Restart,
        ),
        label = "spinner",
    )
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(if (isDark) Color(0xD60A0A0D) else Color(0xDDF2F1EE))
            .pointerInput(Unit) {
                awaitPointerEventScope {
                    while (true) {
                        val event = awaitPointerEvent()
                        event.changes.forEach { it.consume() }
                    }
                }
            },
        contentAlignment = Alignment.Center,
    ) {
        IOSFlyInContainer(
            enter = iosSheetFlyInEnter(),
            exit = iosSheetFlyOutExit(),
        ) {
            Column(
                modifier = Modifier
                    .width(252.dp)
                    .background(
                        color = if (isDark) Color(0xFF17181C) else Color(0xFFFCFCFD),
                        shape = RoundedCornerShape(30.dp),
                    )
                    .border(
                        width = 1.dp,
                        color = if (isDark) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.78f),
                        shape = RoundedCornerShape(30.dp),
                    )
                    .padding(horizontal = 28.dp, vertical = 26.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Canvas(
                    modifier = Modifier
                        .size(48.dp)
                        .graphicsLayer { rotationZ = rotation },
                ) {
                    drawArc(
                        color = if (isDark) Color.White.copy(alpha = 0.12f) else Color(0xFFCCD1DA),
                        startAngle = 0f,
                        sweepAngle = 360f,
                        useCenter = false,
                        style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round),
                    )
                    drawArc(
                        color = ShellColors.AccentBlue,
                        startAngle = -90f,
                        sweepAngle = 94f,
                        useCenter = false,
                        style = Stroke(width = 4.dp.toPx(), cap = StrokeCap.Round),
                    )
                }
                Text(
                    "正在加载图片",
                    modifier = Modifier.padding(top = 18.dp),
                    color = colors.textPrimary,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
    }
}

@Composable
fun TemplateConfetti(
    token: Long,
    onConsumed: (Long) -> Unit,
) {
    val leftColors = remember {
        listOf(
            ShellColors.AccentBlue.toArgb(),
            ShellColors.AccentPurple.toArgb(),
            ShellColors.AccentPink.toArgb(),
        )
    }
    val rightColors = remember {
        listOf(
            ShellColors.AccentBlue.toArgb(),
            ShellColors.AccentCyan.toArgb(),
            ShellColors.AccentGreen.toArgb(),
            Color.White.toArgb(),
        )
    }
    val parties = remember(token) {
        listOf(
	            Party(
	                speed = 24f,
	                maxSpeed = 42f,
	                damping = 0.92f,
	                angle = 304,
	                spread = 38,
	                colors = leftColors,
	                position = Position.Relative(0.02, 0.82),
	                emitter = Emitter(duration = 520, TimeUnit.MILLISECONDS).perSecond(96),
	                timeToLive = 3600L,
	                fadeOutEnabled = true,
	            ),
	            Party(
	                speed = 24f,
	                maxSpeed = 42f,
	                damping = 0.92f,
	                angle = 236,
	                spread = 38,
	                colors = rightColors,
	                position = Position.Relative(0.98, 0.82),
	                emitter = Emitter(duration = 520, TimeUnit.MILLISECONDS).perSecond(96),
	                timeToLive = 3600L,
	                fadeOutEnabled = true,
	            ),
            Party(
                speed = 14f,
                maxSpeed = 24f,
                damping = 0.94f,
	                angle = 296,
	                spread = 26,
	                colors = leftColors,
	                position = Position.Relative(0.03, 0.835),
	                emitter = Emitter(duration = 900, TimeUnit.MILLISECONDS).perSecond(22),
	                timeToLive = 3000L,
	                fadeOutEnabled = true,
                delay = 120,
            ),
            Party(
                speed = 14f,
                maxSpeed = 24f,
                damping = 0.94f,
	                angle = 244,
	                spread = 26,
	                colors = rightColors,
	                position = Position.Relative(0.97, 0.835),
	                emitter = Emitter(duration = 900, TimeUnit.MILLISECONDS).perSecond(22),
                timeToLive = 3000L,
                fadeOutEnabled = true,
                delay = 120,
            ),
        )
    }
    LaunchedEffect(token) {
        delay(4300)
        onConsumed(token)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .zIndex(300f)
    ) {
        KonfettiView(
            modifier = Modifier.fillMaxSize(),
            parties = parties,
        )
    }
}
