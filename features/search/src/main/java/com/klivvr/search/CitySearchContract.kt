package com.klivvr.search

import com.klivvr.search.model.CityUiModel
import kotlinx.collections.immutable.ImmutableMap

sealed interface CitySearchState {
    data object Loading : CitySearchState

    data class Data(
        val cities: ImmutableMap<Char, List<CityUiModel>>,
        val filteredCities: ImmutableMap<Char, List<CityUiModel>>,
        val searchQuery: String,
        val cityCounter: Int,
        val selectedCity: CityUiModel? = null,
    ) : CitySearchState

    data object Empty : CitySearchState
}
