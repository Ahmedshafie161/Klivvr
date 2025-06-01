package com.klivvr.core.designSystem

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


data class CustomTypography(
    val headlineLarge: TextStyle = TextStyle(
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
    ),
    val headlineMedium: TextStyle = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.SemiBold,
    ),

    val labelMedium: TextStyle = TextStyle(
        fontSize = 16.sp, fontWeight = FontWeight.Medium
    ),

    val labelGeo: TextStyle = TextStyle(
        fontSize = 14.sp, fontWeight = FontWeight.Normal, color = Color.Gray
    )
)

val LocalAbsTypography = staticCompositionLocalOf { CustomTypography() }

