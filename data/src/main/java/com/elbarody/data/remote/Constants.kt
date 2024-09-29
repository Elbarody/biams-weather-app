package com.elbarody.data.remote

object Constants {
    const val API_KEY = "ef63c18bccc22cb056e9a7c3aa6d024d"

    const val BASE_URL = "https://api.openweathermap.org/data/2.5/"

    private const val ICON_BASE_URL = "https://openweathermap.org/img/w/"

    fun getIconUrl(icon: String): String {
        return "$ICON_BASE_URL$icon.png"
    }

}