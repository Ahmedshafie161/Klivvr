package com.klivvr.search.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.klivvr.core.designSystem.CustomTheme
import com.klivvr.search.model.CityUiModel
import com.klivvr.search.model.countryFlags
import kotlin.collections.component1
import kotlin.collections.component2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnScope.CityList(
    modifier: Modifier = Modifier, cities: List<CityUiModel>, onCitySelected: (CityUiModel) -> Unit
) {
    val groupedCities = remember(cities) {
        cities.groupBy { it.name.first().uppercaseChar() }
    }

    LazyColumn(
        modifier = modifier, contentPadding = PaddingValues(top = 16.dp, bottom = 16.dp)
    ) {
        groupedCities.forEach { (initial, cityGroup) ->
            stickyHeader {
                GroupHeader(initial = initial.toString())
            }
            items(cityGroup) { city ->
                CityListItem(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 10.dp),
                    city = city,
                    onClick = { onCitySelected(city) })
            }
        }
    }
}

@Composable
fun CityListItem(modifier: Modifier = Modifier, city: CityUiModel, onClick: () -> Unit) {
    Card(
        modifier = modifier, onClick = onClick, colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row (modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically){
            countryFlags[city.countryCode]?.let {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .clip(CircleShape)
                        .background(CustomTheme.colors.LightGray_1),
                    contentAlignment = Alignment.Center
                ) {
                    Image(painterResource(it), null, modifier = Modifier.size(40.dp))
                }
            }
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "${city.name}, ${city.countryCode}",
                    style = CustomTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold),
                    modifier = Modifier.padding(bottom = 4.dp)
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
