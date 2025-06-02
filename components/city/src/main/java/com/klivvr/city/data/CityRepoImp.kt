package com.klivvr.city.data

import com.klivvr.city.data.dataSource.CityIDataSource
import com.klivvr.city.domain.CityIRepo
import javax.inject.Inject

internal class CityRepoImp @Inject constructor(private val dataSource: CityIDataSource) : CityIRepo {
    override suspend fun getCities() = dataSource.getCities().map { it.toDomain() }
}