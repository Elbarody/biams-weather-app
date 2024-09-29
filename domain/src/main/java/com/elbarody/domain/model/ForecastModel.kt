package com.elbarody.domain.model

data class ForecastModel(
    val cityWeatherData: CityWeatherData,
    val forecastDailyList: List<ForecastDailyItem>
)

data class CityWeatherData(
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
