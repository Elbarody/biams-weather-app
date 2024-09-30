package com.elbarody.domain.model

data class CitiesListDataModel(
    val citiesList: List<CityDataModel>
)

data class CityDataModel(
    val cityName: String,
    val id: Int,
    val lat: Double,
    val lon: Double
)
