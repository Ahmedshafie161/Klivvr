package com.klivvr.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.klivvr.core.designSystem.CustomTheme

@Composable
fun GroupHeader(modifier: Modifier = Modifier, initial: String) {
    Box(
        modifier
            .wrapContentSize()
            .defaultMinSize(CustomTheme.sizing.small_L, CustomTheme.sizing.small_L)
            .clip(CircleShape)
            .background(Color.White)
            .border(CustomTheme.sizing.xxSmall, CustomTheme.colors.LightGray_5, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = initial,
            modifier = Modifier,
            style = CustomTheme.typography.headlineMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}