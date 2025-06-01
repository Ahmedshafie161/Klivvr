package com.klivvr.core.designSystem

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

object AbsColor {
    val LightGray = Color(0xFFF5F5F5)
    val LightBorder = Color(0xFFE0E0E0)
    val FlagBackground = Color(0xFFF3F4F6)
}

val LocalAbsColor = compositionLocalOf { AbsColor }
