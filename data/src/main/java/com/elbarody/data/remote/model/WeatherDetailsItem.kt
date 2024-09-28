package com.elbarody.data.remote.model


import com.google.gson.annotations.SerializedName

data class WeatherDetailsItem(
    @SerializedName("dt_txt") val dateTime: String,
    @SerializedName("main") val mainTempData: MainTempData,
    @SerializedName("weather") val weatherCondition: List<WeatherCondition>,
)

data class MainTempData(
    @SerializedName("feels_like") val feelsLike: Double,
    @SerializedName("temp") val temp: Double,
    @SerializedName("temp_max") val tempMax: Double,
    @SerializedName("temp_min") val tempMin: Double
)

data class WeatherCondition(
    @SerializedName("description") val mainConditionDescription: String,
    @SerializedName("icon") val icon: String,
    @SerializedName("id") val id: Int,
    @SerializedName("main") val mainCondition: String
)