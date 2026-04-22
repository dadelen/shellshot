package com.example.shellshot.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeEffect

@Composable
fun GlassSurfaceCard(
    modifier: Modifier = Modifier,
    hazeState: HazeState? = null,
    isDark: Boolean,
    cornerRadius: Int = 40,
    padding: Int = 24,
    content: @Composable BoxScope.() -> Unit,
) {
    val shape = RoundedCornerShape(cornerRadius.dp)
    Box(
        modifier = modifier
            .clip(shape)
            .then(
                if (hazeState != null) {
                    Modifier.hazeEffect(hazeState)
                } else {
                    Modifier
                }
            )
            .background(
                color = MaterialTheme.shellShotTokens.colors.surface.copy(alpha = if (hazeState != null) 0.82f else 1f),
                shape = shape,
            )
            .border(1.dp, MaterialTheme.shellShotTokens.colors.glassStroke, shape)
            .padding(padding.dp),
        content = content,
    )
}

@Composable
fun CountCapsule(
    text: String,
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Box(
        modifier = modifier
            .clip(CircleShape)
            .background(colors.subtleFill)
            .border(1.dp, colors.separator, CircleShape)
            .padding(horizontal = 14.dp, vertical = 7.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            fontSize = 13.sp,
            fontWeight = FontWeight.Black,
            color = if (isDark) colors.textMuted else colors.textMuted,
        )
    }
}

@Composable
fun IconPlate(
    icon: AppIconId,
    tint: Color,
    background: Color,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(36.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(background)
            .border(1.dp, tint.copy(alpha = 0.15f), RoundedCornerShape(12.dp)),
        contentAlignment = Alignment.Center,
    ) {
        AppIcon(
            icon = icon,
            contentDescription = null,
            tint = tint,
            modifier = Modifier.size(18.dp),
        )
    }
}

@Composable
fun SectionHeaderRow(
    title: String,
    value: String,
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = title,
            fontSize = 12.sp,
            fontWeight = FontWeight.Black,
            letterSpacing = 2.sp,
            color = colors.textSecondary,
        )
        Text(
            text = value,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            color = colors.textSecondary,
        )
    }
}

@Composable
fun PhoneMockFrame(
    modifier: Modifier = Modifier,
    imageContent: @Composable BoxScope.() -> Unit,
) {
    Box(
        modifier = modifier
            .size(width = 110.dp, height = 220.dp)
            .background(Color(0xFF121212), RoundedCornerShape(35.dp))
            .border(6.dp, Color(0xFF121212), RoundedCornerShape(35.dp))
            .clip(RoundedCornerShape(29.dp)),
        contentAlignment = Alignment.TopCenter,
    ) {
        imageContent()
        Box(
            modifier = Modifier
                .padding(top = 8.dp)
                .size(width = 32.dp, height = 12.dp)
                .background(Color(0xFF262626), CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Box(
                modifier = Modifier
                    .size(4.dp)
                    .background(Color(0xFF404040), CircleShape),
            )
        }
    }
}

@Composable
fun StaggeredReveal(
    index: Int,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var visible by remember(index) { mutableStateOf(false) }
    LaunchedEffect(index) {
        visible = false
        kotlinx.coroutines.delay(index * 80L)
        visible = true
    }
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = fadeIn(animationSpec = tween(360, easing = FastOutSlowInEasing)) +
            slideInVertically(
                initialOffsetY = { it / 5 },
                animationSpec = tween(420, easing = FastOutSlowInEasing),
            ),
        exit = fadeOut(animationSpec = tween(180)) +
            slideOutVertically(targetOffsetY = { -it / 6 }),
    ) {
        content()
    }
}

@Composable
fun LiquidGlassSwitch(
    isOn: Boolean,
    onToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
) {
    var isAnimating by remember { mutableStateOf(false) }
    LaunchedEffect(isOn) {
        isAnimating = true
        kotlinx.coroutines.delay(150)
        isAnimating = false
    }
    val xOffset by animateDpAsState(
        targetValue = when {
            isOn && isAnimating -> 0.dp
            isOn -> 24.dp
            isAnimating -> 4.dp
            else -> 0.dp
        },
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "switch-x",
    )
    val beadWidth by animateDpAsState(
        targetValue = if (isAnimating) 36.dp else 26.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "switch-width",
    )
    val beadHeight by animateDpAsState(
        targetValue = if (isAnimating) 22.dp else 26.dp,
        animationSpec = spring(stiffness = 500f, dampingRatio = 0.85f),
        label = "switch-height",
    )
    val restAlpha by animateFloatAsState(
        targetValue = if (isAnimating) 0f else 1f,
        animationSpec = tween(150),
        label = "switch-rest-alpha",
    )
    val glassAlpha by animateFloatAsState(
        targetValue = if (isAnimating) 1f else 0f,
        animationSpec = tween(150),
        label = "switch-glass-alpha",
    )

    Box(
        modifier = modifier
            .size(width = 56.dp, height = 32.dp)
            .clip(CircleShape)
            .background(
                color = if (isOn) ShellColors.AccentGreen else Color(0xFFE5E7EB),
                shape = CircleShape,
            )
            .border(
                width = if (isOn) 0.dp else 1.dp,
                color = Color.White.copy(alpha = 0.35f),
                shape = CircleShape,
            )
            .padding(3.dp)
            .noRippleClick {
                onToggle(!isOn)
            },
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.CenterStart)
                .offset(x = xOffset)
                .size(width = beadWidth, height = beadHeight)
                .clip(CircleShape)
                .background(Color.White)
                .border(0.5.dp, Color.White.copy(alpha = 0.8f), CircleShape),
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White.copy(alpha = restAlpha), CircleShape),
            )
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.White.copy(alpha = 0.9f * glassAlpha),
                                Color.White.copy(alpha = 0.1f * glassAlpha),
                            ),
                        ),
                    ),
            )
        }
    }
}
