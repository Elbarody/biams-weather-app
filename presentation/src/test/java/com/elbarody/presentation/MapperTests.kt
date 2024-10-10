package com.elbarody.presentation

import com.elbarody.data.mapper.toCityListData
import com.elbarody.data.mapper.toForecastModel
import com.elbarody.data.remote.model.CitesResponse
import com.elbarody.data.remote.model.CityItem
import com.elbarody.data.remote.model.CityWeather
import com.elbarody.data.remote.model.ForecastResponse
import com.elbarody.data.remote.model.MainTempData
import com.elbarody.data.remote.model.WeatherCondition
import com.elbarody.data.remote.model.WeatherDetailsItem
import com.elbarody.domain.model.CitiesListDataModel
import com.elbarody.domain.model.ForecastModel
import com.elbarody.domain.model.CityDataModel
import com.elbarody.domain.model.CityWeatherData
import com.elbarody.domain.model.ForecastDailyItem
import com.elbarody.domain.model.ForecastHourItem
import org.junit.Assert.assertEquals
import org.junit.Test
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class MapperTests {

    @Test
    fun `CitesResponse toCityListData maps correctly`() {
        val cityItem = CityItem(cityNameEn = "Cairo", id = 1, lat = 30.033333, lon = 31.233334, cityNameAr = "القاهرة")
        val citesResponse = CitesResponse(citiesList = listOf(cityItem))

        val expected = CitiesListDataModel(citiesList = listOf(CityDataModel(cityName = "Cairo", id = 1, lat = 30.033333, lon = 31.233334)))
        val actual = citesResponse.toCityListData()

        assertEquals(expected, actual)
    }

    @Test
    fun `ForecastResponse toForecastModel maps correctly`() {
        val weatherDetailsItem = WeatherDetailsItem(
            dateTime = "2024-10-10 12:00:00",
            mainTempData = MainTempData(tempMin = 20.0 + 273.15, tempMax = 30.0 + 273.15, temp = 25.0 + 273.15), // Ensure values are in Kelvin
            weatherCondition = listOf(
                WeatherCondition(
                    mainConditionDescription = "Clear",
                    icon = "clear",
                    id = 1,
                    mainCondition = "Clear"
                )
            )
        )

        val forecastResponse = ForecastResponse(
            cityWeather = CityWeather(
                cityName = "Cairo",
                countryCode = "EG",
                sunrise = 1696942800, // 06:00 UTC in seconds
                sunset = 1696986000, // 18:00 UTC in seconds
                id = 1
            ),
            weatherDetailsList = listOf(weatherDetailsItem),
            cod = "200",
            message = 0,
            cnt = 1
        )

        // Convert Unix timestamps to local time for Cairo
        val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("Africa/Cairo")
        }
        val expectedSunrise = dateFormat.format(Date(1696942800L * 1000))
        val expectedSunset = dateFormat.format(Date(1696986000L * 1000))

        val expected = ForecastModel(
            cityWeatherData = CityWeatherData(
                cityName = "Cairo",
                countryName = "Egypt",
                sunrise = expectedSunrise,
                sunset = expectedSunset
            ),
            forecastDailyList = listOf(ForecastDailyItem(
                date = "2024-10-10",
                tempMin = "20°C",
                tempMax = "30°C",
                forecastHourList = listOf(ForecastHourItem(
                    time = "12:00", // From dateTime in weatherDetailsItem
                    temp = "25°C", // This should match the temperature in mainTempData
                    condition = "Clear", // Weather condition from WeatherCondition
                    icon = "https://openweathermap.org/img/w/clear.png" // Ensure this matches the actual URL
                ))
            ))
        )

        val actual = forecastResponse.toForecastModel()

        assertEquals(expected, actual)
    }




}
