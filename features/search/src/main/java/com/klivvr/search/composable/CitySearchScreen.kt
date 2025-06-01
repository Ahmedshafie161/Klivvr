package com.klivvr.search.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.klivvr.core.commonUi.LoadingScreen
import com.klivvr.search.CitySearchState
import com.klivvr.search.CitySearchViewModel
import com.klivvr.search.model.CityUiModel

@Composable
fun CitySearchScreen(
    viewModel: CitySearchViewModel = hiltViewModel(),
    onMapRequested: (CityUiModel) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val focusRequester = remember { FocusRequester() }


    Box(modifier = Modifier.fillMaxSize()) {
        when (val uiState = state.value) {
            is CitySearchState.Loading -> LoadingScreen()
            is CitySearchState.Empty -> EmptyScreen()
            is CitySearchState.Data -> {
                CityList(
                    cities = uiState.filteredCities,
                    onCitySelected = { city ->
                        viewModel.onCitySelected(city)
                        onMapRequested(city)
                    }
                )

                SearchBar(
                    query = uiState.searchQuery,
                    onQueryChange = viewModel::filterCities,
                    focusRequester = focusRequester,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopCenter)
                )
            }

        }
    }
}


