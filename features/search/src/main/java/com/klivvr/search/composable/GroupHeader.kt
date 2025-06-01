package com.klivvr.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.klivvr.core.designSystem.CustomTheme

@Composable
fun GroupHeader(initial: Char) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(CustomTheme.colors.LightGray)
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = initial.toString(),
            style = CustomTheme.typography.labelMedium,
            color = CustomTheme.colors.LightGray
        )
    }
}
