package com.klivvr.city.data

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName
import com.klivvr.city.domain.CityDomainModel
import com.klivvr.city.domain.CoordDomainModel

@Keep
internal class CityDataModel(
    @SerializedName("name") val name: String,
    @SerializedName("country") val countryCode: String,
    @SerializedName("_id") val id: Long,
    @SerializedName("coord") val coordinates: CoordDataModel,
)

internal data class CoordDataModel(
    @SerializedName("lon") val longitude: Double, @SerializedName("lat") val latitude: Double
)

internal fun CityDataModel.toDomain(): CityDomainModel = CityDomainModel(
    name = this.name,
    countryCode = this.countryCode,
    id = this.id,
    coordinates = this.coordinates.toDomain()
)

internal fun CoordDataModel.toDomain(): CoordDomainModel = CoordDomainModel(
    longitude = this.longitude, latitude = this.latitude
)
