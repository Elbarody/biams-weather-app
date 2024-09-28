package com.elbarody.data.remote.datasource

import com.elbarody.data.remote.api.ForecastApi
import com.elbarody.data.remote.helper.apiCall
import com.elbarody.data.remote.model.ForecastResponse
import com.elbarody.domin.helper.Response
import javax.inject.Inject

class ForecastDataSource @Inject constructor(
    private val api: ForecastApi
) : IForecastDataSource {
    override suspend fun getForecast(lat: String, lon: String): Response<ForecastResponse> = apiCall {
        api.getForecast(lat = lat,lon = lon)
    }
}