package com.elbarody.data.remote.model

import com.google.gson.annotations.SerializedName

data class CitesResponse(
    @SerializedName("cities")
    val citiesList: List<CityItem>
)

data class CityItem(
    @SerializedName("cityNameAr")
    val cityNameAr: String,
    @SerializedName("cityNameEn")
    val cityNameEn: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)
