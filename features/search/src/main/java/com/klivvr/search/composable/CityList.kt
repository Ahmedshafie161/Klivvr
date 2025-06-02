package com.klivvr.search.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.klivvr.core.designSystem.CustomTheme
import com.klivvr.search.model.CityUiModel
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.ImmutableMap
import kotlin.collections.component1
import kotlin.collections.component2

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ColumnScope.CityList(
    modifier: Modifier = Modifier,
    groupedCities: ImmutableMap<Char, List<CityUiModel>>,
    onCitySelected: (CityUiModel) -> Unit
) {

    val columnState = rememberLazyListState()
    LaunchedEffect(groupedCities) {
        columnState.scrollToItem(0)
    }

    LazyColumn(
        modifier = modifier, contentPadding = PaddingValues(
            top = CustomTheme.spacing.spacerM, bottom = CustomTheme.spacing.spacerM
        ), state = columnState
    ) {
        groupedCities.forEach { (initial, cityGroup) ->
            stickyHeader {
                GroupHeader(initial = initial.toString())
            }
            items(cityGroup) { city ->
                CityListItem(modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = CustomTheme.spacing.spacerL,
                        vertical = CustomTheme.spacing.spacer
                    ), city = city, onClick = { onCitySelected(city) })
            }
        }
    }
}

