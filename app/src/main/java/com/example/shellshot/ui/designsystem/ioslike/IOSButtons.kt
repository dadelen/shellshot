package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp

@Composable
fun IOSPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    leading: @Composable (() -> Unit)? = null,
) {
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 52.dp)
            .liquidPressEffect(interactionSource = interactionSource, enabled = enabled)
            .background(
                color = IOSColors.Blue.copy(alpha = if (enabled) 1f else 0.42f),
                shape = IOSShapes.Default.pill,
            )
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(horizontal = 22.dp, vertical = 14.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leading?.invoke()
        Text(
            text = text,
            style = IOSTypography.Default.button,
            color = Color.White,
        )
    }
}

@Composable
fun IOSSecondaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    enabled: Boolean = true,
    leading: @Composable (() -> Unit)? = null,
) {
    val colors = IOSColors.scheme(isDark)
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .liquidPressEffect(interactionSource = interactionSource, enabled = enabled)
            .background(colors.cardElevated.copy(alpha = if (enabled) 0.92f else 0.48f), IOSShapes.Default.pill)
            .border(0.5.dp, colors.separator, IOSShapes.Default.pill)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(horizontal = 18.dp, vertical = 12.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leading?.invoke()
        Text(
            text = text,
            style = IOSTypography.Default.button,
            color = colors.primaryText,
        )
    }
}

@Composable
fun IOSPillIconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    enabled: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colors = IOSColors.scheme(isDark)
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .defaultMinSize(minWidth = 44.dp, minHeight = 44.dp)
            .liquidPressEffect(interactionSource = interactionSource, enabled = enabled)
            .background(colors.card.copy(alpha = if (enabled) 0.86f else 0.42f), IOSShapes.Default.pill)
            .border(0.5.dp, colors.separator, IOSShapes.Default.pill)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
            .padding(12.dp),
        contentAlignment = Alignment.Center,
    ) {
        content()
    }
}
