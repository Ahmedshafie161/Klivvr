package com.klivvr.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.klivvr.city.domain.GetCitiesUseCase
import com.klivvr.core.util.groupByFirstLetter
import com.klivvr.search.model.CityUiModel
import com.klivvr.search.model.toCityUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.toImmutableList
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<CitySearchState>(CitySearchState.Loading)
    val uiState: StateFlow<CitySearchState> = _uiState.asStateFlow()

    private lateinit var citySearcher: CitySearcher

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val cities = getCitiesUseCase.invoke().map { it.toCityUiModel() }

            if (cities.isEmpty()) {
                _uiState.value = CitySearchState.Empty
            } else {
                citySearcher = CitySearcher(cities)
                val grouped = cities.groupByFirstLetter { it.name }.toImmutableMap()
                _uiState.value = CitySearchState.Data(
                    cities = grouped,
                    filteredCities = grouped,
                    searchQuery = "",
                    selectedCity = null,
                    cityCounter = cities.size
                )
            }
        }
    }

    fun filterCities(query: String) {
        val currentState = _uiState.value
        if (currentState !is CitySearchState.Data) return

        if (query.isEmpty()) {
            _uiState.value = currentState.copy(searchQuery = query,
                filteredCities = currentState.cities,
                cityCounter = currentState.cities.values.sumOf { it.size })
        } else {
            viewModelScope.launch(Dispatchers.Default) {
                val result = citySearcher.search(query)
                _uiState.value = currentState.copy(
                    searchQuery = query,
                    filteredCities = result.groupByFirstLetter { it.name }.toImmutableMap(),
                    cityCounter = result.size
                )
            }
        }
    }

    fun onCitySelected(city: CityUiModel) {
        val currentState = _uiState.value
        if (currentState is CitySearchState.Data) {
            _uiState.value = currentState.copy(selectedCity = city)
        }
    }
}
