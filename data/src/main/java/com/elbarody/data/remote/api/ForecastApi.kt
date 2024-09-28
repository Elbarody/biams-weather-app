package com.elbarody.data.remote.api

import com.elbarody.data.remote.Constants
import com.elbarody.data.remote.model.ForecastResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ForecastApi {

    @GET("forecast")
    suspend fun getForecast(
        @Query("appid") clientId: String = Constants.API_KEY,
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): ForecastResponse
}