package com.klivvr.search.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.klivvr.core.R
import com.klivvr.core.commonUi.composables.EmptyScreen
import com.klivvr.core.commonUi.composables.LoadingScreen
import com.klivvr.core.designSystem.CustomTheme
import com.klivvr.search.CitySearchState
import com.klivvr.search.CitySearchViewModel
import com.klivvr.search.model.CityUiModel

@Composable
fun CitySearchScreen(
    viewModel: CitySearchViewModel = hiltViewModel(), onMapRequested: (CityUiModel) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    Column(
        Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.LightGray_2)
            .systemBarsPadding()
            .pointerInput(Unit) {
                detectTapGestures(onTap = {
                    focusManager.clearFocus()
                })
            }) {
        when (val uiState = state.value) {
            is CitySearchState.Loading -> LoadingScreen()
            is CitySearchState.Empty -> EmptyScreen(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            )

            is CitySearchState.Data -> {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = CustomTheme.spacing.spacerM),
                    textAlign = TextAlign.Start,
                    text = stringResource(id = R.string.city_search_title),
                    style = CustomTheme.typography.headlineLarge
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = CustomTheme.spacing.spacerM),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = R.string.city_count, uiState.cityCounter),
                    style = CustomTheme.typography.labelMedium,
                    color = CustomTheme.colors.LightGray_4
                )

                CityList(modifier = Modifier
                    .padding(horizontal = CustomTheme.spacing.spacerM)
                    .fillMaxSize()
                    .weight(1f),
                    groupedCities = uiState.filteredCities,
                    onCitySelected = { city ->
                        viewModel.onCitySelected(city)
                        onMapRequested(city)
                    })
            }
        }

        val uiState = state.value
        if (uiState !is CitySearchState.Loading) {
            val query = when (uiState) {
                is CitySearchState.Data -> uiState.searchQuery
                is CitySearchState.Empty -> uiState.query
                else -> ""
            }
            SearchBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .imePadding(),
                query = query,
                onQueryChange = viewModel::filterCities,
                focusRequester = focusRequester,
            )
        }
    }
}
