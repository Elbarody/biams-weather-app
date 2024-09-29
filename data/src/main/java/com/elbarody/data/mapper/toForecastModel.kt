package com.elbarody.data.mapper

import com.elbarody.data.remote.model.*
import com.elbarody.domain.model.ForecastDailyItem
import com.elbarody.domain.model.ForecastHourItem
import com.elbarody.domain.model.ForecastModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.round

fun ForecastResponse.toForecastModel(): ForecastModel {
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

    return ForecastModel(
        countryName = Locale("", city.countryCode).displayCountry,
        cityName = city.cityName,
        sunrise = city.sunrise.toFormattedTime(dateFormat),
        sunset = city.sunset.toFormattedTime(dateFormat),
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
        time = detail.dateTime.substringAfter(" "),
        temp = detail.mainTempData.temp.toFormattedCelsius(),
        condition = detail.weatherCondition.firstOrNull()?.mainConditionDescription.orEmpty(),
        icon = detail.weatherCondition.firstOrNull()?.icon.orEmpty()
    )
}

private fun List<WeatherDetailsItem>.groupByDate() = groupBy { it.dateTime.substringBefore(" ") }

private fun Int.toFormattedTime(format: SimpleDateFormat) = format.format(Date(this.toLong() * 1000))

private fun Double.toCelsius() = this - 273.15

private fun Double.toFormattedCelsius() = "${roundToNearestInt(this.toCelsius())}°C"

fun roundToNearestInt(value: Double): Int {
    return round(value).toInt()
}
