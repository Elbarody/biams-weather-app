package com.elbarody.data.remote.datasource

import com.elbarody.data.remote.model.ForecastResponse
import com.elbarody.domin.helper.Response

interface IForecastDataSource {
    suspend fun getForecast(lat: Double, lon: Double): Response<ForecastResponse>
}