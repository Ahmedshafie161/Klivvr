package com.impactyn.core.designSystem

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
data class AbsSpacing(
    val spacerM: Dp = 16.dp,
    val spacer: Dp = 12.dp,
    val cardPadding: Dp = 16.dp,
    val padding: Dp = 16.dp,
    val rowSpacing: Dp = 12.dp,
)

val LocalAbsSpacing = staticCompositionLocalOf { AbsSpacing() }
