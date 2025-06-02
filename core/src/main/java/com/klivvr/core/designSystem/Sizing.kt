package com.klivvr.core.designSystem

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AbsSizing(
    val zero: Dp = 0.dp,
    val xxSmall: Dp = 2.dp,
    val xSmall: Dp = 4.dp,
    val xSmall_2: Dp = 10.dp,
    val xLSmall: Dp = 12.dp,
    val small: Dp = 40.dp,
    val small_S: Dp = 50.dp,
    val small_L: Dp = 60.dp,
    val medium: Dp = 80.dp,
    val cardWidth: Dp = 300.dp,
    val cardHeight: Dp = 130.dp,
    val small_xL: Dp = 10.dp,
    val small_xxL: Dp = 16.dp,
)

val LocalAbsSizing = staticCompositionLocalOf { AbsSizing() }
