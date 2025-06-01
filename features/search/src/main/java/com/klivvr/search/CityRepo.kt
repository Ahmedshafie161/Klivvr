package com.klivvr.search

import com.klivvr.search.model.CityUiModel
import com.klivvr.search.model.Coord
import javax.inject.Inject

class CityRepository @Inject constructor(
) {
    suspend fun getCities(): List<CityUiModel> {
        val cities = listOf(
            CityUiModel("Zurich", "Switzerland", 1000L, Coord(47.3769, 8.5417)),
            CityUiModel("Amsterdam", "Netherlands", 1001L, Coord(52.3676, 4.9041)),
            CityUiModel("Berlin", "Germany", 1002L, Coord(52.5200, 13.4050)),
            CityUiModel("New York", "USA", 1003L, Coord(40.7128, -74.0060)),
            CityUiModel("San Francisco", "USA", 1004L, Coord(37.7749, -122.4194)),
            CityUiModel("Tokyo", "Japan", 1005L, Coord(35.6762, 139.6503)),
            CityUiModel("Sydney", "Australia", 1006L, Coord(-33.8688, 151.2093)),
            CityUiModel("Cape Town", "South Africa", 1007L, Coord(-33.9249, 18.4241)),
            CityUiModel("Rio de Janeiro", "Brazil", 1008L, Coord(-22.9068, -43.1729)),
            CityUiModel("Moscow", "Russia", 1009L, Coord(55.7558, 37.6173)),
            CityUiModel("Paris", "France", 1010L, Coord(48.8566, 2.3522)),
            CityUiModel("London", "United Kingdom", 1011L, Coord(51.5074, -0.1278)),
            CityUiModel("Rome", "Italy", 1012L, Coord(41.9028, 12.4964)),
            CityUiModel("Seoul", "South Korea", 1013L, Coord(37.5665, 126.9780)),
            CityUiModel("Bangkok", "Thailand", 1014L, Coord(13.7563, 100.5018)),
            CityUiModel("Dubai", "UAE", 1015L, Coord(25.2048, 55.2708)),
            CityUiModel("Istanbul", "Turkey", 1016L, Coord(41.0082, 28.9784)),
            CityUiModel("Toronto", "Canada", 1017L, Coord(43.651070, -79.347015)),
            CityUiModel("Mexico City", "Mexico", 1018L, Coord(19.4326, -99.1332)),
            CityUiModel("Buenos Aires", "Argentina", 1019L, Coord(-34.6037, -58.3816)),
            CityUiModel("Lagos", "Nigeria", 1020L, Coord(6.5244, 3.3792)),
            CityUiModel("Kuala Lumpur", "Malaysia", 1021L, Coord(3.1390, 101.6869)),
            CityUiModel("Barcelona", "Spain", 1022L, Coord(41.3851, 2.1734)),
            CityUiModel("Chicago", "USA", 1023L, Coord(41.8781, -87.6298)),
            CityUiModel("Athens", "Greece", 1024L, Coord(37.9838, 23.7275)),
            CityUiModel("Vienna", "Austria", 1025L, Coord(48.2082, 16.3738)),
            CityUiModel("Amsterdam", "USA", 1026L, Coord(42.9387, -74.1904)),
            CityUiModel("Melbourne", "Australia", 1027L, Coord(-37.8136, 144.9631))
        )
        return cities.map { city ->
            CityUiModel(
                id = city.id,
                name = city.name,
                countryCode = city.countryCode,
                coordinates = city.coordinates,
//                flagResId = getFlagResource(city.flagResId)
            )
        }.sortedWith(compareBy({ it.name.lowercase() }, { it.countryCode }))
    }

}