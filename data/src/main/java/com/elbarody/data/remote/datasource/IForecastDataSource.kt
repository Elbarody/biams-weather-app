package com.elbarody.data.remote.datasource

import com.elbarody.data.remote.model.ForecastResponse
import com.elbarody.domin.helper.Response

interface IForecastDataSource {
    suspend fun getForecast(lat: String, lon: String): Response<ForecastResponse>
}