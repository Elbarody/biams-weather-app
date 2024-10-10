package com.elbarody.data.remote.model


import com.google.gson.annotations.SerializedName

data class CityWeather(
    @SerializedName("coord") val coord: Coord? = null,
    @SerializedName("country") val countryCode: String,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val cityName: String,
    @SerializedName("sunrise") val sunrise: Int,
    @SerializedName("sunset") val sunset: Int,
    //@SerializedName("timezone") val timezone: Int
)

data class Coord(
    @SerializedName("lat") val lat: Double,
    @SerializedName("lon") val lon: Double
)