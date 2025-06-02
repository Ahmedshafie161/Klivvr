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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
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
                    focusManager.clearFocus() // clears focus when tapping anywhere
                })
            }) {
        when (val uiState = state.value) {
            is CitySearchState.Loading -> LoadingScreen()
            is CitySearchState.Empty -> EmptyScreen()
            is CitySearchState.Data -> {
                Text(
                    modifier = Modifier
                        .wrapContentSize()
                        .padding(start = CustomTheme.spacing.spacerM),
                    textAlign = TextAlign.Start,
                    text = "City Search",
                    style = CustomTheme.typography.headlineLarge
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = CustomTheme.spacing.spacerM),
                    textAlign = TextAlign.Center,
                    text = "${uiState.cityCounter} cities",
                    style = CustomTheme.typography.labelMedium,
                    color = CustomTheme.colors.LightGray_4
                )

                CityList(modifier = Modifier
                    .fillMaxSize()
                    .weight(1f),
                    groupedCities = uiState.filteredCities,
                    onCitySelected = { city ->
                        viewModel.onCitySelected(city)
                        onMapRequested(city)
                    })
                SearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = viewModel::filterCities,
                    focusRequester = focusRequester,
                    modifier = Modifier
                        .fillMaxWidth()
                        .imePadding()
                )
            }
        }
    }
}


