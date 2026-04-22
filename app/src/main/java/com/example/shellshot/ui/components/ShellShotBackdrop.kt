package com.example.shellshot.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.example.shellshot.R
import com.example.shellshot.ui.theme.ShellColors

@Composable
fun ShellShotBackdrop(
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    val noise = ImageBitmap.imageResource(id = R.drawable.noise_texture)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ShellColors.background(isDark)),
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        ShellColors.AccentBlue.copy(alpha = if (isDark) 0.14f else 0.18f),
                        Color.Transparent,
                    ),
                    radius = size.minDimension * 0.7f,
                    center = Offset(size.width * 0.88f, size.height * 0.16f),
                ),
                radius = size.minDimension * 0.46f,
                center = Offset(size.width * 0.88f, size.height * 0.16f),
            )
            drawCircle(
                brush = Brush.radialGradient(
                    colors = listOf(
                        ShellColors.AccentPurple.copy(alpha = if (isDark) 0.12f else 0.10f),
                        Color.Transparent,
                    ),
                    radius = size.minDimension * 0.8f,
                    center = Offset(size.width * 0.14f, size.height * 0.82f),
                ),
                radius = size.minDimension * 0.42f,
                center = Offset(size.width * 0.14f, size.height * 0.82f),
            )

            val gridPath = Path().apply {
                addOval(
                    Rect(
                        left = size.width * 0.1f,
                        top = -size.height * 0.35f,
                        right = size.width * 0.9f,
                        bottom = size.height * 0.62f,
                    ),
                )
            }
            clipPath(gridPath) {
                val step = 32.dp.toPx()
                var x = 0f
                while (x <= size.width) {
                    drawLine(
                        color = ShellColors.GridLine,
                        start = Offset(x, 0f),
                        end = Offset(x, size.height),
                        strokeWidth = 1f,
                    )
                    x += step
                }
                var y = 0f
                while (y <= size.height) {
                    drawLine(
                        color = ShellColors.GridLine,
                        start = Offset(0f, y),
                        end = Offset(size.width, y),
                        strokeWidth = 1f,
                    )
                    y += step
                }
            }
            drawImage(
                image = noise,
                dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                alpha = if (isDark) 0.06f else 0.04f,
                blendMode = BlendMode.Overlay,
            )
        }
    }
}
