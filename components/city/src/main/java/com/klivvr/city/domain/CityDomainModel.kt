package com.klivvr.city.domain

import androidx.annotation.Keep

@Keep
data class CityDomainModel(
    val name: String,
    val countryCode: String,
    val id: Long,
    val coordinates: CoordDomainModel,
)

@Keep
data class CoordDomainModel(
    val longitude: Double, val latitude: Double
)