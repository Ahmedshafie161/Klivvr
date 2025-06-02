package com.klivvr.city.domain

interface CityIRepo {
    suspend fun getCities(): List<CityDomainModel>
}