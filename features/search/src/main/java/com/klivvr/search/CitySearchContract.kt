package com.klivvr.search

import com.klivvr.search.model.CityUiModel

sealed interface CitySearchState {
    data object Loading : CitySearchState

    data class Data(
        val cities: List<CityUiModel>,
        val filteredCities: List<CityUiModel>,
        val searchQuery: String,
        val selectedCity: CityUiModel? = null
    ) : CitySearchState

    data object Empty : CitySearchState
}
