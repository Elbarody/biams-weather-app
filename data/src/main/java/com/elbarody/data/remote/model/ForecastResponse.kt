package com.elbarody.data.remote.model


import com.google.gson.annotations.SerializedName

data class ForecastResponse(
    @SerializedName("message") val message: Int,
    @SerializedName("cnt") val cnt: Int,
    @SerializedName("cod") val cod: String,
    @SerializedName("city") val city: City,
    @SerializedName("list") val weatherDetailsList: List<WeatherDetailsItem>
)