package com.klivvr.city.data.dataSource

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.klivvr.city.data.CityDataModel
import com.klivvr.city.data.toDomain
import com.klivvr.city.domain.CityDomainModel
import com.klivvr.city.domain.CityIRepo
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import java.io.InputStreamReader

class CityLocalDataSource @Inject constructor(@ApplicationContext private val context: Context): CityIDataSource {
    override suspend fun getCities(): List<CityDataModel> {
        val inputStream = context.assets.open("cities.json")
        val reader = InputStreamReader(inputStream)
        val type = object : TypeToken<List<CityDataModel>>() {}.type
        val cities = Gson().fromJson<List<CityDataModel>>(reader, type)
        return cities.map { city ->
            CityDataModel(
                id = city.id,
                name = city.name,
                countryCode = city.countryCode,
                coordinates = city.coordinates,
            )
        }.sortedWith(compareBy({ it.name.lowercase() }, { it.countryCode }))
    }
}