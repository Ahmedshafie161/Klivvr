package com.klivvr.search

import androidx.lifecycle.ViewModel
import com.klivvr.city.domain.GetCitiesUseCase
import com.klivvr.core.IoDispatcher
import com.klivvr.core.util.CloseableCoroutineScope
import com.klivvr.core.util.groupByFirstLetter
import com.klivvr.search.model.CityUiModel
import com.klivvr.search.model.toCityUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.ImmutableMap
import kotlinx.collections.immutable.toImmutableMap
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CitySearchViewModel @Inject constructor(
    private val getCitiesUseCase: GetCitiesUseCase,
    private val customScope: CloseableCoroutineScope,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel(viewModelScope = customScope) {

    private val _uiState = MutableStateFlow<CitySearchState>(CitySearchState.Loading)
    val uiState: StateFlow<CitySearchState> = _uiState.asStateFlow()

    private lateinit var citySearcher: CitySearcher

    private var fullCitiesMap: ImmutableMap<Char, List<CityUiModel>>? = null

    init {
        customScope.launch(dispatcher) {
            val cities = getCitiesUseCase().map { it.toCityUiModel() }

            if (cities.isEmpty()) {
                _uiState.value = CitySearchState.Empty("")
            } else {
                citySearcher = CitySearcher(cities)
                val grouped = cities.groupByFirstLetter { it.name }.toImmutableMap()
                fullCitiesMap = grouped
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

        val cities = when (currentState) {
            is CitySearchState.Data -> currentState.cities
            is CitySearchState.Empty -> fullCitiesMap ?: return
            else -> return
        }

        if (query.isEmpty()) {
            _uiState.value = CitySearchState.Data(
                cities = cities,
                filteredCities = cities,
                searchQuery = query,
                cityCounter = cities.values.sumOf { it.size },
                selectedCity = null
            )
        } else {
            customScope.launch(dispatcher) {
                val result = citySearcher.search(query)

                if (result.isEmpty()) {
                    _uiState.value = CitySearchState.Empty(query)
                } else {
                    _uiState.value = CitySearchState.Data(
                        cities = cities,
                        filteredCities = result.groupByFirstLetter { it.name }.toImmutableMap(),
                        searchQuery = query,
                        cityCounter = result.size,
                        selectedCity = null
                    )
                }
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
