package com.klivvr.city.data.dataSource

import com.klivvr.city.data.CityDataModel

interface CityIDataSource {
    suspend fun getCities(): List<CityDataModel>
}