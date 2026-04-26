package com.example.shellshot.ui.screen

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import com.example.shellshot.template.CalibratedTemplateGeometry
import com.example.shellshot.template.CalibrationCornerId
import com.example.shellshot.template.CalibrationMagnifierState
import com.example.shellshot.template.CalibrationSnapEngine
import com.example.shellshot.template.TemplateCalibrationEngine
import com.example.shellshot.template.TemplateImportDraft
import com.example.shellshot.ui.components.AppIcon
import com.example.shellshot.ui.components.AppIconId
import com.example.shellshot.ui.components.PremiumLoadingAnimation
import com.example.shellshot.ui.components.noRippleClick
import com.example.shellshot.ui.theme.ShellColors
import kotlin.math.hypot
import kotlin.math.max
import kotlin.math.roundToInt

private val CalibrationSaveButtonColor = Color(0xFF0A84FF)
private const val TemplateNameMaxLength = 9

@Composable
fun TemplateCalibrationPage(
    draft: TemplateImportDraft,
    inProgress: Boolean,
    isDark: Boolean,
    onUpdateName: (String) -> Unit,
    onStartCornerDrag: (CalibrationCornerId, Float, Float) -> Unit,
    onUpdateCorner: (CalibrationCornerId, Float, Float) -> Unit,
    onFinishCornerDrag: () -> Unit,
    onUpdateCornerRadius: (Float) -> Unit,
    onReset: () -> Unit,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    val density = LocalDensity.current
    val sourceBitmap: Bitmap? = remember(draft.sourceImagePath) {
        runCatching { BitmapFactory.decodeFile(draft.sourceImagePath) }.getOrNull()
    }
    val sourceImage = remember(sourceBitmap) { sourceBitmap?.asImageBitmap() }
    val geometry = remember(draft) { TemplateCalibrationEngine.buildGeometry(draft, forSave = false) }
    var magnifierState by remember { mutableStateOf(CalibrationMagnifierState()) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(ShellColors.background(isDark))
            .windowInsetsPadding(WindowInsets.statusBars)
            .windowInsetsPadding(WindowInsets.navigationBars)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                IconButton(onClick = onDismiss) {
                    AppIcon(AppIconId.Close, null, tint = ShellColors.textPrimary(isDark))
                }
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "标定屏幕区域",
                        style = MaterialTheme.typography.titleLarge,
                        color = ShellColors.textPrimary(isDark),
                    )
                }
                Spacer(modifier = Modifier.width(48.dp))
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(ShellColors.surfaceHigh(isDark), RoundedCornerShape(28.dp))
                    .padding(12.dp),
            ) {
                TemplateCalibrationPreview(
                    draft = draft,
                    geometry = geometry,
                    isDark = isDark,
                    sourceBitmap = sourceBitmap,
                    sourceImage = sourceImage,
                    magnifierState = magnifierState,
                    onMagnifierStateChange = { magnifierState = it },
                    onStartCornerDrag = onStartCornerDrag,
                    onUpdateCorner = onUpdateCorner,
                    onFinishCornerDrag = onFinishCornerDrag,
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(ShellColors.surfaceHigh(isDark), RoundedCornerShape(24.dp))
                    .padding(horizontal = 18.dp, vertical = 16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                FloatingLabelTextField(
                    value = draft.templateName,
                    onValueChange = onUpdateName,
                    isDark = isDark,
                    label = "模板名称",
                    maxLength = TemplateNameMaxLength,
                )
                Text(
                    text = "圆角 ${draft.cornerRadiusPx.roundToInt()} px",
                    color = ShellColors.textPrimary(isDark),
                    style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.SemiBold),
                )
                FrostedSlider(
                    value = draft.cornerRadiusPx,
                    onValueChange = onUpdateCornerRadius,
                    valueRange = 0f..max(1f, minOf(geometry.visibleBounds.width, geometry.visibleBounds.height) / 2f),
                    isDark = isDark,
                )
                Text(
                    text = draft.detectionSummary,
                    color = ShellColors.textTertiary(isDark),
                    style = MaterialTheme.typography.bodySmall,
                    modifier = Modifier.basicMarquee(),
                )
                CalibrationLegend(
                    isDark = isDark,
                    modifier = Modifier.fillMaxWidth(),
                )
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    FrostedActionButton(
                        text = "重置",
                        isDark = isDark,
                        primary = false,
                        modifier = Modifier.weight(1f),
                        onClick = onReset,
                    )
                    FrostedActionButton(
                        text = "保存导入",
                        isDark = isDark,
                        primary = true,
                        modifier = Modifier.weight(1f),
                        enabled = !inProgress && draft.templateName.isNotBlank(),
                        onClick = {
                            if (!inProgress && draft.templateName.isNotBlank()) {
                                onConfirm()
                            }
                        },
                    )
                }
            }
        }

        if (inProgress) {
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
                Column(
                    modifier = Modifier
                        .widthIn(max = 280.dp)
                        .background(
                            color = if (isDark) Color(0xFF17181C) else Color(0xFFFCFCFD),
                            shape = RoundedCornerShape(30.dp),
                        )
                        .border(
                            1.dp,
                            if (isDark) Color.White.copy(alpha = 0.08f) else Color.White.copy(alpha = 0.78f),
                            RoundedCornerShape(30.dp),
                        )
                        .padding(horizontal = 28.dp, vertical = 28.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                ) {
                    PremiumLoadingAnimation(
                        isDark = isDark,
                        message = "正在保存模板",
                    )
                }
            }
        }
    }
}

@Composable
private fun FloatingLabelTextField(
    value: String,
    onValueChange: (String) -> Unit,
    isDark: Boolean,
    label: String,
    maxLength: Int,
    modifier: Modifier = Modifier,
) {
    val countColor = if (value.length >= maxLength) {
        CalibrationSaveButtonColor
    } else {
        ShellColors.textTertiary(isDark)
    }
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 10.dp),
    ) {
        BasicTextField(
            value = value,
            onValueChange = { next ->
                onValueChange(next.take(maxLength))
            },
            singleLine = true,
            textStyle = TextStyle(
                color = ShellColors.textPrimary(isDark),
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(ShellColors.glassSurface(isDark), RoundedCornerShape(18.dp))
                .border(1.dp, ShellColors.separator(isDark), RoundedCornerShape(18.dp))
                .padding(horizontal = 18.dp, vertical = 20.dp),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.CenterStart,
                ) {
                    if (value.isBlank()) {
                        Text(
                            text = "输入模板名称",
                            color = ShellColors.textTertiary(isDark),
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Medium,
                        )
                    }
                    innerTextField()
                }
            },
        )
        Row(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .offset(y = (-10).dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Box(
                modifier = Modifier
                    .background(ShellColors.surfaceHigh(isDark), RoundedCornerShape(999.dp))
                    .border(1.dp, ShellColors.separator(isDark), RoundedCornerShape(999.dp))
                    .padding(horizontal = 10.dp, vertical = 3.dp),
            ) {
                Text(
                    text = label,
                    color = ShellColors.textTertiary(isDark),
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
            Box(
                modifier = Modifier
                    .background(
                        ShellColors.surfaceHigh(isDark).copy(alpha = if (isDark) 0.96f else 0.9f),
                        RoundedCornerShape(999.dp),
                    )
                    .border(1.dp, ShellColors.separator(isDark), RoundedCornerShape(999.dp))
                    .padding(horizontal = 10.dp, vertical = 3.dp),
            ) {
                Text(
                    text = "${value.length}/$maxLength",
                    color = countColor,
                    fontSize = 11.sp,
                    fontWeight = FontWeight.Bold,
                )
            }
        }
        Text(
            text = "最多 $maxLength 个字",
            color = ShellColors.textTertiary(isDark),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(top = 76.dp, end = 4.dp),
        )
    }
}

@Composable
private fun FrostedSlider(
    value: Float,
    onValueChange: (Float) -> Unit,
    valueRange: ClosedFloatingPointRange<Float>,
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    BoxWithConstraints(
        modifier = modifier
            .fillMaxWidth()
            .height(38.dp),
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .pointerInput(valueRange.start, valueRange.endInclusive) {
                    detectDragGestures(
                        onDragStart = { offset ->
                            val fraction = (offset.x / size.width).coerceIn(0f, 1f)
                            onValueChange(valueRange.start + (valueRange.endInclusive - valueRange.start) * fraction)
                        },
                    ) { change, _ ->
                        val fraction = (change.position.x / size.width).coerceIn(0f, 1f)
                        onValueChange(valueRange.start + (valueRange.endInclusive - valueRange.start) * fraction)
                    }
                }
        ) {
            val fraction = ((value - valueRange.start) / (valueRange.endInclusive - valueRange.start).coerceAtLeast(0.0001f)).coerceIn(0f, 1f)
            val trackHeight = 12.dp.toPx()
            val centerY = size.height / 2f
            drawRoundRect(
                color = if (isDark) Color.White.copy(alpha = 0.12f) else Color.Black.copy(alpha = 0.08f),
                topLeft = Offset(0f, centerY - trackHeight / 2f),
                size = Size(size.width, trackHeight),
                cornerRadius = CornerRadius(trackHeight / 2f, trackHeight / 2f),
            )
            drawRoundRect(
                color = CalibrationSaveButtonColor.copy(alpha = 0.95f),
                topLeft = Offset(0f, centerY - trackHeight / 2f),
                size = Size(size.width * fraction, trackHeight),
                cornerRadius = CornerRadius(trackHeight / 2f, trackHeight / 2f),
            )
            drawCircle(
                color = CalibrationSaveButtonColor.copy(alpha = 0.22f),
                radius = 18.dp.toPx(),
                center = Offset(size.width * fraction, centerY),
            )
            drawCircle(
                color = Color.White,
                radius = 12.dp.toPx(),
                center = Offset(size.width * fraction, centerY),
            )
        }
    }
}

@Composable
private fun FrostedActionButton(
    text: String,
    isDark: Boolean,
    primary: Boolean,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    val background = when {
        primary && enabled -> CalibrationSaveButtonColor
        primary -> CalibrationSaveButtonColor.copy(alpha = 0.52f)
        else -> ShellColors.glassSurface(isDark)
    }
    val foreground = when {
        primary && enabled -> Color.White
        primary -> Color.White.copy(alpha = 0.72f)
        else -> ShellColors.textPrimary(isDark)
    }
    Box(
        modifier = modifier
            .background(background, RoundedCornerShape(18.dp))
            .border(
                width = if (primary) 0.dp else 1.dp,
                color = ShellColors.separator(isDark),
                shape = RoundedCornerShape(18.dp),
            )
            .noRippleClick(enabled = enabled, onClick = onClick)
            .padding(vertical = 16.dp),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = text,
            color = foreground,
            fontWeight = FontWeight.Black,
            fontSize = 15.sp,
        )
    }
}

@Composable
private fun TemplateCalibrationPreview(
    draft: TemplateImportDraft,
    geometry: CalibratedTemplateGeometry,
    isDark: Boolean,
    sourceBitmap: Bitmap?,
    sourceImage: androidx.compose.ui.graphics.ImageBitmap?,
    magnifierState: CalibrationMagnifierState,
    onMagnifierStateChange: (CalibrationMagnifierState) -> Unit,
    onStartCornerDrag: (CalibrationCornerId, Float, Float) -> Unit,
    onUpdateCorner: (CalibrationCornerId, Float, Float) -> Unit,
    onFinishCornerDrag: () -> Unit,
) {
    val density = LocalDensity.current
    BoxWithConstraints(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        val imageScale = minOf(
            constraints.maxWidth / draft.outputWidth.toFloat(),
            constraints.maxHeight / draft.outputHeight.toFloat(),
        ).coerceAtLeast(0.01f)
        val displayWidth = draft.outputWidth * imageScale
        val displayHeight = draft.outputHeight * imageScale
        val contentLeft = (constraints.maxWidth - displayWidth) / 2f
        val contentTop = (constraints.maxHeight - displayHeight) / 2f
        val hitRadiusPx = with(density) { 32.dp.toPx() }

        fun toScreen(point: Offset): Offset = Offset(
            x = contentLeft + point.x * imageScale,
            y = contentTop + point.y * imageScale,
        )

        fun toImage(point: Offset): Offset = Offset(
            x = ((point.x - contentLeft) / imageScale).coerceIn(0f, draft.outputWidth.toFloat()),
            y = ((point.y - contentTop) / imageScale).coerceIn(0f, draft.outputHeight.toFloat()),
        )

        fun nearestCorner(point: Offset): CalibrationCornerId? {
            val candidates = draft.normalizedCorners.mapNotNull { corner ->
                val screenPoint = toScreen(Offset(corner.x, corner.y))
                val distance = hypot((screenPoint.x - point.x).toDouble(), (screenPoint.y - point.y).toDouble()).toFloat()
                if (distance <= hitRadiusPx) corner.id to distance else null
            }
            return candidates.minByOrNull { it.second }?.first
        }

        Box(
            modifier = Modifier
                .widthIn(max = with(density) { displayWidth.toDp() })
                .height(with(density) { displayHeight.toDp() })
                .fillMaxWidth()
        ) {
            if (sourceImage != null) {
                Image(
                    painter = BitmapPainter(sourceImage),
                    contentDescription = "模板预览",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillBounds,
                )
            } else {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(ShellColors.glassSurface(isDark), RoundedCornerShape(24.dp))
                )
            }

            Canvas(
                modifier = Modifier
                    .fillMaxSize()
                    .pointerInput(draft.normalizedCorners, sourceBitmap, draft.snapEnabled) {
                        var draggingCornerId: CalibrationCornerId? = null
                        detectDragGestures(
                            onDragStart = { offset ->
                                val selected = nearestCorner(offset)
                                draggingCornerId = selected
                                if (selected != null) {
                                    val imagePoint = toImage(offset)
                                    onStartCornerDrag(selected, imagePoint.x, imagePoint.y)
                                    onMagnifierStateChange(
                                        CalibrationMagnifierState(
                                            activeCornerId = selected,
                                            fingerX = offset.x,
                                            fingerY = offset.y,
                                            focusX = imagePoint.x,
                                            focusY = imagePoint.y,
                                            visible = true,
                                        )
                                    )
                                }
                            },
                            onDragEnd = {
                                draggingCornerId = null
                                onFinishCornerDrag()
                                onMagnifierStateChange(CalibrationMagnifierState())
                            },
                            onDragCancel = {
                                draggingCornerId = null
                                onFinishCornerDrag()
                                onMagnifierStateChange(CalibrationMagnifierState())
                            },
                        ) { change, _ ->
                            val cornerId = draggingCornerId ?: return@detectDragGestures
                            val rawImagePoint = toImage(change.position)
                            val snapped = if (draft.snapEnabled && sourceBitmap != null) {
                                CalibrationSnapEngine.snap(sourceBitmap, rawImagePoint.x, rawImagePoint.y)
                            } else {
                                null
                            }
                            val finalX = snapped?.x ?: rawImagePoint.x
                            val finalY = snapped?.y ?: rawImagePoint.y
                            onUpdateCorner(cornerId, finalX, finalY)
                            onMagnifierStateChange(
                                CalibrationMagnifierState(
                                    activeCornerId = cornerId,
                                    fingerX = change.position.x,
                                    fingerY = change.position.y,
                                    focusX = finalX,
                                    focusY = finalY,
                                    visible = true,
                                )
                            )
                            change.consume()
                        }
                    }
            ) {
                val corners = draft.normalizedCorners.associateBy { it.id }
                val topLeft = corners[CalibrationCornerId.TOP_LEFT] ?: return@Canvas
                val topRight = corners[CalibrationCornerId.TOP_RIGHT] ?: return@Canvas
                val bottomRight = corners[CalibrationCornerId.BOTTOM_RIGHT] ?: return@Canvas
                val bottomLeft = corners[CalibrationCornerId.BOTTOM_LEFT] ?: return@Canvas

                val polygon = Path().apply {
                    moveTo(topLeft.x * imageScale, topLeft.y * imageScale)
                    lineTo(topRight.x * imageScale, topRight.y * imageScale)
                    lineTo(bottomRight.x * imageScale, bottomRight.y * imageScale)
                    lineTo(bottomLeft.x * imageScale, bottomLeft.y * imageScale)
                    close()
                }

                if (draft.showGuides) {
                    drawRect(
                        color = Color.White.copy(alpha = 0.12f),
                        size = size,
                        style = Stroke(width = 1f),
                    )
                    val thirdW = size.width / 3f
                    val thirdH = size.height / 3f
                    repeat(2) { index ->
                        drawLine(
                            color = Color.White.copy(alpha = 0.18f),
                            start = Offset(thirdW * (index + 1), 0f),
                            end = Offset(thirdW * (index + 1), size.height),
                            strokeWidth = 1f,
                        )
                        drawLine(
                            color = Color.White.copy(alpha = 0.18f),
                            start = Offset(0f, thirdH * (index + 1)),
                            end = Offset(size.width, thirdH * (index + 1)),
                            strokeWidth = 1f,
                        )
                    }
                }

                val orangeColor = if (isDark) Color(0xFFFF9F0A) else Color(0xFFFF9500)
                val blueColor = if (isDark) Color(0xFF32ADE6) else Color(0xFF0A84FF)
                
                drawPath(
                    path = polygon,
                    color = orangeColor.copy(alpha = 0.4f),
                )
                drawPath(
                    path = polygon,
                    color = orangeColor,
                    style = Stroke(width = 2.5f),
                )

                val roundRadius = draft.cornerRadiusPx * imageScale
                drawRoundRect(
                    color = blueColor.copy(alpha = 0.15f),
                    topLeft = Offset(geometry.visibleBounds.left * imageScale, geometry.visibleBounds.top * imageScale),
                    size = Size(geometry.visibleBounds.width * imageScale, geometry.visibleBounds.height * imageScale),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(roundRadius, roundRadius),
                    style = Fill,
                )
                drawRoundRect(
                    color = blueColor,
                    topLeft = Offset(geometry.visibleBounds.left * imageScale, geometry.visibleBounds.top * imageScale),
                    size = Size(geometry.visibleBounds.width * imageScale, geometry.visibleBounds.height * imageScale),
                    cornerRadius = androidx.compose.ui.geometry.CornerRadius(roundRadius, roundRadius),
                    style = Stroke(width = 2f),
                )
            }
        }

        if (magnifierState.visible && sourceImage != null) {
            val popupY = with(density) { 60.dp.toPx() }
            val topMarginPx = with(density) { 12.dp.toPx() }
            val popupHalfPx = with(density) { 72.dp.toPx() }
            val belowOffsetPx = with(density) { 24.dp.toPx() }
            val desiredAbove = magnifierState.fingerY - popupY
            val popupOffset = if (desiredAbove < topMarginPx) {
                IntOffset(
                    x = (magnifierState.fingerX - popupHalfPx).roundToInt(),
                    y = (magnifierState.fingerY + belowOffsetPx).roundToInt(),
                )
            } else {
                IntOffset(
                    x = (magnifierState.fingerX - popupHalfPx).roundToInt(),
                    y = (desiredAbove - popupHalfPx).roundToInt(),
                )
            }
            Popup(offset = popupOffset) {
                CalibrationMagnifier(
                    sourceImage = sourceImage,
                    draft = draft,
                    focusX = magnifierState.focusX,
                    focusY = magnifierState.focusY,
                )
            }
        }
    }
}

@Composable
private fun CalibrationLegend(
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .background(
                color = ShellColors.badgeBg(isDark),
                shape = RoundedCornerShape(14.dp),
            )
            .padding(horizontal = 12.dp, vertical = 10.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LegendRow(
            color = if (isDark) Color(0xFFFF9F0A) else Color(0xFFFF9500),
            label = "拖拽范围",
            description = "当前区域",
            isDark = isDark,
            modifier = Modifier.weight(1f),
        )
        LegendRow(
            color = if (isDark) Color(0xFF32ADE6) else Color(0xFF0A84FF),
            label = "最终生效",
            description = "保存区域",
            isDark = isDark,
            modifier = Modifier.weight(1f),
        )
    }
}

@Composable
private fun LegendRow(
    color: Color,
    label: String,
    description: String,
    isDark: Boolean,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Canvas(modifier = Modifier.size(18.dp)) {
            drawRoundRect(
                color = color.copy(alpha = 0.22f),
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(5.dp.toPx(), 5.dp.toPx()),
            )
            drawRoundRect(
                color = color,
                cornerRadius = androidx.compose.ui.geometry.CornerRadius(5.dp.toPx(), 5.dp.toPx()),
                style = Stroke(width = 1.8.dp.toPx()),
            )
        }
        Column(verticalArrangement = Arrangement.spacedBy(1.dp)) {
            Text(
                text = label,
                style = MaterialTheme.typography.labelSmall,
                color = ShellColors.textPrimary(isDark),
            )
            Text(
                text = description,
                style = MaterialTheme.typography.labelSmall,
                color = ShellColors.textTertiary(isDark),
            )
        }
    }
}

@Composable
private fun CalibrationMagnifier(
    sourceImage: androidx.compose.ui.graphics.ImageBitmap,
    draft: TemplateImportDraft,
    focusX: Float,
    focusY: Float,
) {
    val zoom = 2f
    val sampleRadius = 36f
    Box(
        modifier = Modifier
            .size(144.dp)
            .background(Color(0xF4141518), RoundedCornerShape(24.dp))
            .border(1.dp, Color.White.copy(alpha = 0.16f), RoundedCornerShape(24.dp))
            .padding(10.dp)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val srcLeft = (focusX - sampleRadius).coerceIn(0f, (draft.outputWidth - 2).toFloat())
            val srcTop = (focusY - sampleRadius).coerceIn(0f, (draft.outputHeight - 2).toFloat())
            val srcWidth = (sampleRadius * 2f / zoom).coerceAtLeast(2f)
            val srcHeight = (sampleRadius * 2f / zoom).coerceAtLeast(2f)
            clipRect {
                drawImage(
                    image = sourceImage,
                    srcOffset = androidx.compose.ui.unit.IntOffset(srcLeft.roundToInt(), srcTop.roundToInt()),
                    srcSize = androidx.compose.ui.unit.IntSize(srcWidth.roundToInt(), srcHeight.roundToInt()),
                    dstSize = androidx.compose.ui.unit.IntSize(size.width.roundToInt(), size.height.roundToInt()),
                )
            }
            drawLine(
                color = Color.White,
                start = Offset(size.width / 2f, 0f),
                end = Offset(size.width / 2f, size.height),
                strokeWidth = 1.5f,
            )
            drawLine(
                color = Color.White,
                start = Offset(0f, size.height / 2f),
                end = Offset(size.width, size.height / 2f),
                strokeWidth = 1.5f,
            )
            drawCircle(
                color = Color(0xFF34C759),
                radius = 4.dp.toPx(),
                center = Offset(size.width / 2f, size.height / 2f),
            )
        }
    }
}
