package com.klivvr.search.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import com.klivvr.core.designSystem.CustomTheme
import com.klivvr.search.model.CityUiModel
import com.klivvr.search.model.countryFlags

@Composable
fun CityListItem(modifier: Modifier = Modifier, city: CityUiModel, onClick: () -> Unit) {
    Card(
        modifier = modifier, onClick = onClick, colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier.padding(CustomTheme.sizing.small_xL),
            verticalAlignment = Alignment.CenterVertically
        ) {
            countryFlags[city.countryCode]?.let {
                Box(
                    modifier = Modifier
                        .size(CustomTheme.sizing.medium)
                        .clip(CircleShape)
                        .background(CustomTheme.colors.LightGray_1),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painterResource(it),
                        null,
                        modifier = Modifier.size(CustomTheme.sizing.small)
                    )
                }
            }
            Column(
                modifier = Modifier.padding(CustomTheme.spacing.spacerM)
            ) {
                Text(
                    text = "${city.name}, ${city.countryCode}",
                    style = CustomTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = CustomTheme.spacing.padding)
                )
                Text(
                    text = "${city.coordinates.longitude} , ${city.coordinates.latitude}",
                    style = CustomTheme.typography.labelMedium,
                    color = CustomTheme.colors.Gray
                )
            }
        }
    }
}
