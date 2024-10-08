package com.elbarody.data.remote

object Constants {
    const val API_KEY = "your_api_key"

    const val WEATHER_BASE_URL = "https://api.openweathermap.org/"
    const val CITIES_BASE_URL = "https://dev-orcas.s3.eu-west-1.amazonaws.com/"

    private const val ICON_BASE_URL = "https://openweathermap.org/img/w/"

    fun getIconUrl(icon: String): String {
        return "$ICON_BASE_URL$icon.png"
    }

}