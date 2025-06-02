package com.klivvr.core.designSystem

import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color

object AbsColor {
    val Gray = Color(0xFF797979)
    val Dark_Gray = Color(0xFF4f4f4f)
    val LightGray_1 = Color(0xFFececef)
    val LightGray_2 = Color(0xFFf5f4f6)
    val LightGray_3 = Color(0xFFededed)
    val LightGray_4 = Color(0xFF525252)
    val LightGray_5 = Color(0xFFbdbdbd)
    val LightGray_6 = Color(0xFFbbbbbb)
}

val LocalAbsColor = compositionLocalOf { AbsColor }
