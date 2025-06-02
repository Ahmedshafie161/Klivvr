package com.klivvr.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
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
    Column(
        modifier = modifier
            .wrapContentWidth()
            .fillMaxHeight()
            .padding(start = CustomTheme.spacing.spacerM),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            Modifier
                .wrapContentSize()
                .defaultMinSize(CustomTheme.sizing.small_L, CustomTheme.sizing.small_L)
                .clip(CircleShape)
                .background(Color.Gray), contentAlignment = Alignment.Center
        ) {
            Text(
                text = initial,
                modifier = Modifier,
                style = CustomTheme.typography.labelMedium,
                color = CustomTheme.colors.LightGray_1,
                textAlign = TextAlign.Center
            )
        }
        Box(
            modifier = Modifier
                .width(CustomTheme.sizing.xSmall)
                .height(CustomTheme.sizing.small_S)
                .background(Color.Black)
        )
    }
}