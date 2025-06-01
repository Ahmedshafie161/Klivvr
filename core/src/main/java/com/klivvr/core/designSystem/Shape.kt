package com.klivvr.core.designSystem

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.dp

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(5.dp),
    large = RoundedCornerShape(10.dp),
)

val cardShape = RoundedCornerShape(16.dp)
val avatarShape = RoundedCornerShape(50)


val LocalAbsShape = compositionLocalOf { Shapes }
