package com.impactyn.core.designSystem

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AbsSizing(
    val small: Dp = 48.dp,
    val medium: Dp = 64.dp,
    val cardWidth: Dp = 300.dp,
    val cardHeight: Dp = 130.dp,
    val small_xL: Dp = 10.dp,
    val small_xxL: Dp = 16.dp,
)

val LocalAbsSizing = staticCompositionLocalOf { AbsSizing() }
