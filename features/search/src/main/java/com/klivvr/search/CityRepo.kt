package com.klivvr.search

import com.klivvr.search.model.CityUiModel
import com.klivvr.search.model.Coord
import javax.inject.Inject

class CityRepository @Inject constructor(
) {
    suspend fun getCities(): List<CityUiModel> {
        val cities = listOf(
            CityUiModel("Zurich", "CH", 1000L, Coord(47.3769, 8.5417)),           // Switzerland
            CityUiModel("Amsterdam", "NL", 1001L, Coord(52.3676, 4.9041)),        // Netherlands
            CityUiModel("Berlin", "DE", 1002L, Coord(52.5200, 13.4050)),          // Germany
            CityUiModel("New York", "US", 1003L, Coord(40.7128, -74.0060)),       // USA
            CityUiModel("San Francisco", "US", 1004L, Coord(37.7749, -122.4194)), // USA
            CityUiModel("Tokyo", "JP", 1005L, Coord(35.6762, 139.6503)),          // Japan
            CityUiModel("Sydney", "AU", 1006L, Coord(-33.8688, 151.2093)),        // Australia
            CityUiModel("Cape Town", "ZA", 1007L, Coord(-33.9249, 18.4241)),      // South Africa
            CityUiModel("Rio de Janeiro", "BR", 1008L, Coord(-22.9068, -43.1729)),// Brazil
            CityUiModel("Moscow", "RU", 1009L, Coord(55.7558, 37.6173)),          // Russia
            CityUiModel("Paris", "FR", 1010L, Coord(48.8566, 2.3522)),            // France
            CityUiModel("London", "GB", 1011L, Coord(51.5074, -0.1278)),          // United Kingdom
            CityUiModel("Rome", "IT", 1012L, Coord(41.9028, 12.4964)),            // Italy
            CityUiModel("Seoul", "KR", 1013L, Coord(37.5665, 126.9780)),          // South Korea
            CityUiModel("Bangkok", "TH", 1014L, Coord(13.7563, 100.5018)),        // Thailand
            CityUiModel("Dubai", "AE", 1015L, Coord(25.2048, 55.2708)),           // UAE
            CityUiModel("Istanbul", "TR", 1016L, Coord(41.0082, 28.9784)),        // Turkey
            CityUiModel("Toronto", "CA", 1017L, Coord(43.651070, -79.347015)),    // Canada
            CityUiModel("Mexico City", "MX", 1018L, Coord(19.4326, -99.1332)),    // Mexico
            CityUiModel("Buenos Aires", "AR", 1019L, Coord(-34.6037, -58.3816)),  // Argentina
            CityUiModel("Lagos", "NG", 1020L, Coord(6.5244, 3.3792)),             // Nigeria
            CityUiModel("Kuala Lumpur", "MY", 1021L, Coord(3.1390, 101.6869)),    // Malaysia
            CityUiModel("Barcelona", "ES", 1022L, Coord(41.3851, 2.1734)),        // Spain
            CityUiModel("Chicago", "US", 1023L, Coord(41.8781, -87.6298)),        // USA
            CityUiModel("Athens", "GR", 1024L, Coord(37.9838, 23.7275)),          // Greece
            CityUiModel("Vienna", "AT", 1025L, Coord(48.2082, 16.3738)),          // Austria
            CityUiModel("Amsterdam", "US", 1026L, Coord(42.9387, -74.1904)),      // USA (Amsterdam, NY)
            CityUiModel("Melbourne", "AU", 1027L, Coord(-37.8136, 144.9631))      // Australia
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