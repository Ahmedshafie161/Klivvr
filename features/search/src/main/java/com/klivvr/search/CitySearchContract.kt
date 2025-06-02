package com.klivvr.search

import com.klivvr.search.model.CityUiModel

sealed interface CitySearchState {
    data object Loading : CitySearchState

    data class Data(
        val cities: Map<Char, List<CityUiModel>>,
        val filteredCities: Map<Char, List<CityUiModel>>,
        val searchQuery: String,
        val cityCounter: Int,
        val selectedCity: CityUiModel? = null,
    ) : CitySearchState

    data object Empty : CitySearchState
}
