package com.example.shellshot.ui.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.IntSize
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
            drawRect(
                brush = Brush.verticalGradient(
                    colors = if (isDark) {
                        listOf(
                            Color.White.copy(alpha = 0.022f),
                            Color.Transparent,
                            Color.Black.copy(alpha = 0.18f),
                        )
	                    } else {
	                        listOf(
	                            Color.White.copy(alpha = 0.78f),
	                            Color(0xFFFAF8F3).copy(alpha = 0.34f),
	                            Color(0xFFF2EEE5).copy(alpha = 0.16f),
	                        )
	                    },
	                ),
	                size = size,
	            )

	            if (!isDark) {
	                drawRect(
	                    brush = Brush.verticalGradient(
	                        colors = listOf(
	                            Color.Transparent,
	                            Color(0xFF9B8A6F).copy(alpha = 0.035f),
	                        ),
	                        startY = size.height * 0.42f,
	                        endY = size.height,
	                    ),
	                    size = size,
	                )
	            }

	            drawImage(
                image = noise,
                dstSize = IntSize(size.width.toInt(), size.height.toInt()),
                alpha = if (isDark) 0.05f else 0.03f,
                blendMode = BlendMode.Overlay,
            )
        }
    }
}
