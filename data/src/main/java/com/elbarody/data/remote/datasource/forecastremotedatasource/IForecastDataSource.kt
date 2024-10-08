package com.elbarody.data.remote.datasource.forecastremotedatasource

import com.elbarody.data.remote.model.ForecastResponse
import com.elbarody.domain.helper.Response

interface IForecastDataSource {
    suspend fun getForecast(lat: Double, lon: Double): Response<ForecastResponse>
}