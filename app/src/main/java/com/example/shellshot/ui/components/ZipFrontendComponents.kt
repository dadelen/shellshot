package com.example.shellshot.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun ZipGlassCard(
    darkTheme: Boolean,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 40.dp,
    contentPadding: PaddingValues = PaddingValues(24.dp),
    content: @Composable ColumnScope.() -> Unit,
) {
    val shape = RoundedCornerShape(cornerRadius)
    val containerColor = if (darkTheme) Color(0xFF1C1C1E) else Color.White
    val borderColor = if (darkTheme) Color.White.copy(alpha = 0.05f) else Color.White
    val ambientShadow = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.Black.copy(alpha = 0.05f)
    val topGlow = if (darkTheme) Color.White.copy(alpha = 0.05f) else Color.White.copy(alpha = 0.16f)
    val bottomGlow = if (darkTheme) Color.White.copy(alpha = 0.02f) else Color.Black.copy(alpha = 0.03f)
    val accentGlow = if (darkTheme) Color(0xFF3B82F6).copy(alpha = 0.06f) else Color(0xFF3B82F6).copy(alpha = 0.05f)

    Box(
        modifier = modifier
            .shadow(
                elevation = 10.dp,
                shape = shape,
                clip = false,
                ambientColor = ambientShadow,
                spotColor = ambientShadow,
            )
            .clip(shape)
            .background(containerColor, shape)
            .border(1.dp, borderColor, shape),
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            topGlow,
                            Color.Transparent,
                            bottomGlow,
                        ),
                    ),
                ),
        )
        Box(
            modifier = Modifier
                .size(136.dp)
                .align(Alignment.TopEnd)
                .padding(top = 8.dp, end = 8.dp)
                .background(
                    Brush.radialGradient(
                        colors = listOf(accentGlow, Color.Transparent),
                    ),
                    CircleShape,
                ),
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(24.dp)
                .padding(horizontal = 14.dp, vertical = 4.dp)
                .align(Alignment.TopCenter)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.White.copy(alpha = if (darkTheme) 0.14f else 0.55f),
                            Color.Transparent,
                        ),
                    ),
                    RoundedCornerShape(999.dp),
                ),
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(contentPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            content = content,
        )
    }
}

@Composable
fun ZipBadge(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.Medium,
        color = textColor,
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(999.dp))
            .border(1.dp, textColor.copy(alpha = 0.14f), RoundedCornerShape(999.dp))
            .padding(horizontal = 10.dp, vertical = 5.dp),
    )
}

@Composable
fun ZipCountPill(
    text: String,
    dotColor: Color,
    darkTheme: Boolean,
    modifier: Modifier = Modifier,
) {
    val shape = RoundedCornerShape(999.dp)
    Row(
        modifier = modifier
            .shadow(
                elevation = 2.dp,
                shape = shape,
                clip = false,
                ambientColor = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.Black.copy(alpha = 0.05f),
                spotColor = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.Black.copy(alpha = 0.05f),
            )
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White, shape)
            .border(
                1.dp,
                if (darkTheme) Color.White.copy(alpha = 0.05f) else Color.White,
                shape,
            )
            .padding(horizontal = 12.dp, vertical = 7.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        ZipPulsingDot(color = dotColor, size = 6.dp)
        Text(
            text = text,
            fontSize = 13.sp,
            lineHeight = 16.sp,
            fontWeight = FontWeight.Medium,
            color = if (darkTheme) Color(0xFFD4D4D8) else Color(0xFF6B7280),
        )
    }
}

@Composable
fun ZipIconPlate(
    icon: AppIconId,
    tint: Color,
    backgroundColor: Color,
    modifier: Modifier = Modifier,
    size: Dp = 34.dp,
    cornerRadius: Dp = 12.dp,
    iconSize: Dp = 18.dp,
) {
    val plateShape = RoundedCornerShape(cornerRadius)
    Box(
        modifier = modifier
            .shadow(
                elevation = 1.5.dp,
                shape = plateShape,
                clip = false,
                ambientColor = Color.Black.copy(alpha = 0.06f),
                spotColor = Color.Black.copy(alpha = 0.08f),
            )
            .size(size)
            .background(backgroundColor, plateShape)
            .border(1.dp, tint.copy(alpha = 0.12f), plateShape),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(iconSize),
        )
    }
}

@Composable
fun ZipGlassIconButton(
    icon: AppIconId,
    darkTheme: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    size: Dp = 40.dp,
) {
    val shape = CircleShape
    Box(
        modifier = modifier
            .shadow(
                elevation = 3.dp,
                shape = shape,
                clip = false,
                ambientColor = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.Black.copy(alpha = 0.05f),
                spotColor = if (darkTheme) Color.White.copy(alpha = 0.10f) else Color.Black.copy(alpha = 0.05f),
            )
            .size(size)
            .clip(shape)
            .background(if (darkTheme) Color(0xFF1C1C1E) else Color.White)
            .border(
                1.dp,
                if (darkTheme) Color.White.copy(alpha = 0.05f) else Color.White,
                shape,
            )
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = icon,
            contentDescription = null,
            tint = if (darkTheme) Color(0xFFD4D4D8) else Color(0xFF52525B),
            modifier = Modifier.size(20.dp),
        )
    }
}

@Composable
fun ZipPulsingDot(
    color: Color,
    modifier: Modifier = Modifier,
    size: Dp = 8.dp,
) {
    val transition = rememberInfiniteTransition(label = "zip-pulse-dot")
    val pulseScale by transition.animateFloat(
        initialValue = 1f,
        targetValue = 1.85f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "zip-pulse-scale",
    )
    val pulseAlpha by transition.animateFloat(
        initialValue = 0.55f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 1400, easing = LinearEasing),
            repeatMode = RepeatMode.Restart,
        ),
        label = "zip-pulse-alpha",
    )

    Box(modifier = modifier.size(size * 2.2f), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .size(size * pulseScale)
                .background(color.copy(alpha = pulseAlpha), CircleShape),
        )
        Box(
            modifier = Modifier
                .size(size)
                .background(color, CircleShape),
        )
    }
}

@Composable
fun ZipSectionDivider(
    darkTheme: Boolean,
    modifier: Modifier = Modifier,
    startIndent: Dp = 60.dp,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = startIndent)
            .height(1.dp)
            .background(if (darkTheme) Color.White.copy(alpha = 0.1f) else Color.White.copy(alpha = 0.4f)),
    )
}

@Composable
fun ZipStaggeredReveal(
    index: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var visible by remember(index) { mutableStateOf(false) }

    LaunchedEffect(index) {
        delay(index * 80L)
        visible = true
    }

    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn(
            animationSpec = tween(durationMillis = 360, easing = FastOutSlowInEasing),
        ) + slideInVertically(
            initialOffsetY = { it / 5 },
            animationSpec = tween(durationMillis = 420, easing = FastOutSlowInEasing),
        ) + scaleIn(
            initialScale = 0.95f,
            animationSpec = tween(durationMillis = 420, easing = FastOutSlowInEasing),
        ),
        exit = fadeOut(
            animationSpec = tween(durationMillis = 180, easing = FastOutSlowInEasing),
        ) + slideOutVertically(
            targetOffsetY = { -it / 6 },
            animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        ) + scaleOut(
            targetScale = 0.98f,
            animationSpec = tween(durationMillis = 200, easing = FastOutSlowInEasing),
        ),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            content()
        }
    }
}
