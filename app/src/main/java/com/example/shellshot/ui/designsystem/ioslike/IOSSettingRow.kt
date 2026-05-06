package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId

@Composable
fun IOSSettingRow(
    icon: AppIconId,
    title: String,
    modifier: Modifier = Modifier,
    isDark: Boolean,
    tint: Color = IOSColors.Blue,
    iconBackground: Color = tint.copy(alpha = if (isDark) 0.18f else 0.12f),
    enabled: Boolean = true,
    onClick: (() -> Unit)? = null,
    trailing: @Composable (() -> Unit)? = null,
) {
    val colors = IOSColors.scheme(isDark)
    val interactionSource = remember { MutableInteractionSource() }
    val clickModifier = if (onClick != null) {
        Modifier
            .liquidPressEffect(interactionSource = interactionSource, enabled = enabled, pressedScale = 0.985f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                enabled = enabled,
                role = Role.Button,
                onClick = onClick,
            )
    } else {
        Modifier
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = 56.dp)
            .then(clickModifier)
            .semantics {
                contentDescription = title
            }
            .padding(horizontal = IOSSpacing.Default.cardPadding, vertical = IOSSpacing.Default.rowVertical),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(34.dp)
                .background(iconBackground, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center,
        ) {
            AppIcon(
                icon = icon,
                contentDescription = null,
                tint = tint,
                modifier = Modifier.size(18.dp),
            )
        }
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(2.dp),
        ) {
            Text(
                text = title,
                style = IOSTypography.Default.body,
                color = colors.primaryText,
            )
        }
        trailing?.invoke()
    }
}
