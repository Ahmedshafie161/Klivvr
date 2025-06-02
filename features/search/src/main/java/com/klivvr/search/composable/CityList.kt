package com.klivvr.search.composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import com.klivvr.core.designSystem.CustomTheme
import com.klivvr.search.model.CityUiModel
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

    // Define line properties
    val lineColor = CustomTheme.colors.LightGray_6
    val lineThickness = CustomTheme.sizing.xxSmall
    val circleRadius = CustomTheme.sizing.xLSmall
    val lineStartPadding = CustomTheme.sizing.small_L / 2
    val endY = CustomTheme.sizing.xSmall_2
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .drawBehind {
                // Draw vertical line spanning full height of the LazyColumn container
                val strokeWidth = lineThickness.toPx()
                val x = lineStartPadding.toPx()
                val yStart = 0f
                val yEnd = size.height - endY.toPx()

                drawLine(
                    color = lineColor,
                    start = Offset(x, yStart),
                    end = Offset(x, yEnd),
                    strokeWidth = strokeWidth
                )
                drawCircle(
                    color = lineColor,
                    radius = circleRadius.toPx(),
                    center = Offset(x, size.height - endY.toPx() - circleRadius.toPx())
                )
            }, contentPadding = PaddingValues(
            top = CustomTheme.spacing.spacerM, bottom = CustomTheme.spacing.spacerM
        ), state = columnState
    ) {
        groupedCities.forEach { (initial, cities) ->
            stickyHeader {
                GroupHeader(
                    modifier = Modifier.padding(start = CustomTheme.sizing.zero),
                    initial = initial.toString()
                )
            }
            items(cities, key = { it.id }) { city ->
                CityListItem(modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = lineStartPadding + CustomTheme.spacing.spacerL,
                        top = CustomTheme.spacing.spacer,
                        bottom = CustomTheme.spacing.spacer
                    ), city = city, onClick = { onCitySelected(city) })
            }
        }
    }
}

