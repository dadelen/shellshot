package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp

@Composable
fun IOSGroupCard(
    modifier: Modifier = Modifier,
    isDark: Boolean,
    padding: PaddingValues = PaddingValues(IOSSpacing.Default.cardPadding),
    content: @Composable BoxScope.() -> Unit,
) {
    val colors = IOSColors.scheme(isDark)
    val shape = IOSShapes.Default.large
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(shape)
            .background(colors.card, shape)
            .border(0.5.dp, colors.separator, shape)
            .padding(padding),
        content = content,
    )
}
