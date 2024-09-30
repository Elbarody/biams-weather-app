package com.elbarody.data.remote.api

import com.elbarody.data.remote.model.CitesResponse
import retrofit2.http.GET

interface CitiesApi {
    @GET("uploads/cities.json")
    suspend fun getCities(): CitesResponse

}