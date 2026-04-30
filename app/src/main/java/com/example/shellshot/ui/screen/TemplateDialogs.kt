package com.example.shellshot.ui.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.GlassSurfaceCard
import com.example.shellshot.ui.components.noRippleClick
import com.example.shellshot.ui.theme.MotionConstants
import com.example.shellshot.ui.theme.ShellColors
import com.example.shellshot.ui.theme.shellShotTokens

@Composable
fun DeleteTemplateDialog(
    templateName: String,
    isDark: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(MotionConstants.SettleMs, easing = MotionConstants.iosEaseOut),
        label = "delete-dialog-alpha",
    )
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.96f,
        animationSpec = MotionConstants.sheetSpring,
        label = "delete-dialog-scale",
    )
    Dialog(onDismissRequest = onDismiss) {
        GlassSurfaceCard(
            modifier = Modifier
                .width(320.dp)
                .graphicsLayer {
                    this.alpha = alpha
                    scaleX = scale
                    scaleY = scale
                    translationY = ((1f - scale) * 240f).dp.toPx()
                },
            isDark = isDark,
            cornerRadius = 32,
            padding = 28,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Box(
                    modifier = Modifier
                        .size(56.dp)
                        .background(ShellColors.AccentRed.copy(alpha = 0.12f), CircleShape),
                    contentAlignment = Alignment.Center,
                ) {
                    AppIcon(AppIconId.Delete, null, tint = ShellColors.AccentRed, modifier = Modifier.size(28.dp))
                }
                Text(
                    "删除模板",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = colors.textPrimary,
                )
                Text(
                    "确定要删除「$templateName」吗？此操作无法撤销。",
                    fontSize = 15.sp,
                    color = colors.textSecondary,
                    lineHeight = 20.sp,
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .background(colors.subtleFill, RoundedCornerShape(14.dp))
                            .noRippleClick(onDismiss),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "取消",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = colors.textPrimary,
                        )
                    }
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(48.dp)
                            .background(ShellColors.AccentRed, RoundedCornerShape(14.dp))
                            .noRippleClick(onConfirm),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            "删除",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White,
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SimpleMessageDialog(
    title: String,
    message: String,
    isDark: Boolean,
    onDismiss: () -> Unit,
) {
    val colors = MaterialTheme.shellShotTokens.colors
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        visible = true
    }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(MotionConstants.SettleMs, easing = MotionConstants.iosEaseOut),
        label = "message-dialog-alpha",
    )
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.96f,
        animationSpec = MotionConstants.sheetSpring,
        label = "message-dialog-scale",
    )
    Dialog(onDismissRequest = onDismiss) {
        GlassSurfaceCard(
            modifier = Modifier
                .width(320.dp)
                .graphicsLayer {
                    this.alpha = alpha
                    scaleX = scale
                    scaleY = scale
                    translationY = ((1f - scale) * 240f).dp.toPx()
                },
            isDark = isDark,
            cornerRadius = 32,
            padding = 28,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    title,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Black,
                    color = colors.textPrimary,
                )
                Text(
                    message,
                    fontSize = 15.sp,
                    color = colors.textSecondary,
                    lineHeight = 20.sp,
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(ShellColors.AccentBlue, RoundedCornerShape(14.dp))
                        .noRippleClick(onDismiss),
                    contentAlignment = Alignment.Center,
                ) {
                    Text(
                        "确定",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White,
                    )
                }
            }
        }
    }
}
