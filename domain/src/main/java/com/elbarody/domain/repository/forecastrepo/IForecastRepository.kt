package com.elbarody.domain.repository.forecastrepo

import com.elbarody.domain.helper.Response
import com.elbarody.domain.model.ForecastModel

interface IForecastRepository {

    suspend fun getForecast(lat: Double, lon: Double): Response<ForecastModel>
}