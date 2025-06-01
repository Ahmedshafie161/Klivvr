package com.klivvr.city

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CityDataModel(
    @SerializedName("name") val name: String,
    @SerializedName("country") val countryCode: String,
    @SerializedName("_id") val id: Long,
    @SerializedName("coord") val coordinates: CoordDataModel,
)

data class CoordDataModel(
    @SerializedName("lon") val longitude: Double, @SerializedName("lat") val latitude: Double
)
