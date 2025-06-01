package com.klivvr.search.model

data class CityUiModel(
    val name: String,
    val countryCode: String,
    val id: Long,
    val coordinates: Coord,
    val flagResId: Int? = null
)

data class Coord(val longitude: Double, val latitude: Double)