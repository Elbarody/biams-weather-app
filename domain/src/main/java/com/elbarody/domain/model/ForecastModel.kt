package com.elbarody.domain.model

data class ForecastModel(
    val cityData: CityData,
    val forecastDailyList: List<ForecastDailyItem>
)

data class CityData(
    val countryName: String,
    val cityName: String,
    val sunrise: String,
    val sunset: String
)

data class ForecastDailyItem(
    val date: String,
    val tempMax: String,
    val tempMin: String,
    val forecastHourList: List<ForecastHourItem>
)

data class ForecastHourItem(
    val time: String,
    val temp: String,
    val condition: String,
    val icon: String
)
