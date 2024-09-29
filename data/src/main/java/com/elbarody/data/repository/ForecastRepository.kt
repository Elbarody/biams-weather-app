package com.elbarody.data.repository

import com.elbarody.data.mapper.mapResponse
import com.elbarody.data.mapper.toForecastModel
import com.elbarody.data.remote.datasource.ForecastDataSource
import com.elbarody.domain.repository.IForecastRepository
import javax.inject.Inject

class ForecastRepository @Inject constructor(
    private val dataSource: ForecastDataSource
) : IForecastRepository {
    override suspend fun getForecast(lat: Double, lon: Double) =
        dataSource.getForecast(lat, lon).mapResponse { forecastResponse ->
            forecastResponse.toForecastModel()
        }

}