package com.example.shellshot.ui.designsystem.ioslike

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.dp

@Immutable
data class IOSShapeScale(
    val small: RoundedCornerShape = RoundedCornerShape(10.dp),
    val medium: RoundedCornerShape = RoundedCornerShape(16.dp),
    val large: RoundedCornerShape = RoundedCornerShape(22.dp),
    val glass: RoundedCornerShape = RoundedCornerShape(30.dp),
    val pill: RoundedCornerShape = RoundedCornerShape(999.dp),
)

object IOSShapes {
    val Default = IOSShapeScale()
}
