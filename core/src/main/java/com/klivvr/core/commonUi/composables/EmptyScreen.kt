package com.klivvr.core.commonUi.composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.klivvr.core.R
import com.klivvr.core.designSystem.CustomTheme

@Composable
fun EmptyScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .padding(CustomTheme.sizing.small),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(R.string.no_cities_found),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}