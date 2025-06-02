package com.klivvr.search

import app.cash.turbine.test
import com.klivvr.city.domain.CityDomainModel
import com.klivvr.city.domain.CoordDomainModel
import com.klivvr.city.domain.GetCitiesUseCase
import com.klivvr.core.util.CloseableCoroutineScope
import com.klivvr.search.model.toCityUiModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.mock

@OptIn(ExperimentalCoroutinesApi::class)
class CitySearchViewModelTest {

    private val testDispatcher: TestDispatcher = UnconfinedTestDispatcher()
    private val getCitiesUseCase: GetCitiesUseCase = mock()
    private lateinit var citySearchViewModel: CitySearchViewModel

    private val testCities = listOf(
        CityDomainModel("Cairo", "EG", 1, CoordDomainModel(30.0444, 31.2357)),
        CityDomainModel("Alexandria", "EG", 2, CoordDomainModel(31.2001, 29.9187)),
        CityDomainModel("Giza", "EG", 3, CoordDomainModel(30.0131, 31.2089))
    )

    @Before
    fun setUp() = runTest(testDispatcher) {
        `when`(getCitiesUseCase()).thenReturn(testCities)
        citySearchViewModel = CitySearchViewModel(
            getCitiesUseCase = getCitiesUseCase,
            customScope = CloseableCoroutineScope(this.coroutineContext),
            dispatcher = testDispatcher
        )
    }

    @Test
    fun `filterCities filters by query`() = runTest(testDispatcher) {
        val sortedTestCities = testCities.sortedBy { it.name.lowercase() }

        `when`(getCitiesUseCase()).thenReturn(sortedTestCities)

        citySearchViewModel = CitySearchViewModel(
            getCitiesUseCase = getCitiesUseCase,
            customScope = CloseableCoroutineScope(this.coroutineContext),
            dispatcher = testDispatcher
        )

        val testQuery = "Cairo"

        citySearchViewModel.uiState.test {
            val initialData = awaitItem() as CitySearchState.Data
            println("Received first state: $initialData")

            citySearchViewModel.filterCities(testQuery)
            testScheduler.advanceUntilIdle()

            val filteredState = awaitItem() as CitySearchState.Data
            println("Received filtered state: $filteredState")

            assertEquals(testQuery, filteredState.searchQuery)
            assertEquals(1, filteredState.cityCounter)
            assertTrue(filteredState.filteredCities.values.flatten().any {
                it.name.contains(testQuery, ignoreCase = true)
            })
        }
    }
    @Test
    fun `onCitySelected updates selectedCity`() = runTest(testDispatcher) {
        citySearchViewModel.uiState.test {
            skipItems(1) // Skip Loading + initial Data
            val testCity = testCities[0].toCityUiModel()
            citySearchViewModel.onCitySelected(testCity)
            val state = awaitItem() as CitySearchState.Data
            assertEquals(testCity, state.selectedCity)
        }
    }

    @Test
    fun `empty city list results in Empty state`() = runTest(testDispatcher) {
        `when`(getCitiesUseCase()).thenReturn(emptyList())

        val viewModel = CitySearchViewModel(
            getCitiesUseCase = getCitiesUseCase,
            customScope = CloseableCoroutineScope(this.coroutineContext),
            dispatcher = testDispatcher
        )

        viewModel.uiState.test {
            val state = awaitItem()
            assert(state is CitySearchState.Empty)
        }
    }
}
