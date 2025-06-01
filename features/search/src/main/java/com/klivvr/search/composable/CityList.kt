package com.klivvr.search.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.klivvr.core.designSystem.CustomTheme
import com.klivvr.search.model.CityUiModel
import kotlin.collections.component1
import kotlin.collections.component2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CityList(
    cities: List<CityUiModel>, onCitySelected: (CityUiModel) -> Unit
) {
    val groupedCities = remember(cities) {
        cities.groupBy { it.name.first().uppercaseChar() }
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize(), contentPadding = PaddingValues(top = 80.dp)
    ) {
        groupedCities.forEach { (initial, cityGroup) ->
            stickyHeader {
                GroupHeader(initial = initial)
            }

            items(cityGroup) { city ->
                CityListItem(city = city, onClick = { onCitySelected(city) })
            }
        }
    }
}
@Composable
fun CityListItem(city: CityUiModel, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp), onClick = onClick
    ) {
        Row(
            modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            city.flagResId?.let {
                Image(
                    painter = painterResource(it),
                    contentDescription = "Flag of ${city.countryCode}",
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "${city.name}, ${city.countryCode}",
                    style = CustomTheme.typography.labelMedium
                )
                Text(
                    text = "Lat: ${city.coordinates.latitude}, Lon: ${city.coordinates.longitude}",
                    style = CustomTheme.typography.labelMedium,
                    color = CustomTheme.colors.LightGray.copy(alpha = 0.7f)
                )
            }
        }
    }
}
