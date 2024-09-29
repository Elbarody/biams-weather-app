package com.elbarody.data.mapper

import com.elbarody.data.mapper.WeatherFormatter.formatTimeWithoutSeconds
import com.elbarody.data.mapper.WeatherFormatter.toFormattedCelsius
import com.elbarody.data.mapper.WeatherFormatter.toFormattedTime
import com.elbarody.data.remote.model.*
import com.elbarody.domain.model.CityWeatherData
import com.elbarody.domain.model.ForecastDailyItem
import com.elbarody.domain.model.ForecastHourItem
import com.elbarody.domain.model.ForecastModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

private val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

// Responsible for formatting data
object WeatherFormatter {
    fun String.formatTimeWithoutSeconds(): String = this.substring(0, 5)

    fun Double.toFormattedCelsius(): String = "${round(this - 273.15).toInt()}°C"

    fun Int.toFormattedTime(): String = dateFormat.format(Date(this.toLong() * 1000))

}

fun ForecastResponse.toForecastModel(): ForecastModel {
    return ForecastModel(
        cityWeatherData = CityWeatherData(
            cityName = cityWeather.cityName,
            countryName = Locale("", cityWeather.countryCode).displayCountry,
            sunrise = cityWeather.sunrise.toFormattedTime(),
            sunset = cityWeather.sunset.toFormattedTime()
        ),
        forecastDailyList = weatherDetailsList.groupByDate().map { (date, weatherDetails) ->
            ForecastDailyItem(
                date = date,
                tempMin = weatherDetails.minOfTemp().toFormattedCelsius(),
                tempMax = weatherDetails.maxOfTemp().toFormattedCelsius(),
                forecastHourList = weatherDetails.toHourlyForecasts()
            )
        }
    )
}

// Helper functions and extensions
private fun List<WeatherDetailsItem>.minOfTemp() = minOf { it.mainTempData.tempMin }
private fun List<WeatherDetailsItem>.maxOfTemp() = maxOf { it.mainTempData.tempMax }

private fun List<WeatherDetailsItem>.toHourlyForecasts() = map { detail ->
    ForecastHourItem(
        time = detail.dateTime.substringAfter(" ").formatTimeWithoutSeconds(),
        temp = detail.mainTempData.temp.toFormattedCelsius(),
        condition = detail.weatherCondition.firstOrNull()?.mainConditionDescription.orEmpty(),
        icon = detail.weatherCondition.firstOrNull()?.iconWithUrl.orEmpty()
    )
}

private fun List<WeatherDetailsItem>.groupByDate() = groupBy { it.dateTime.substringBefore(" ") }

