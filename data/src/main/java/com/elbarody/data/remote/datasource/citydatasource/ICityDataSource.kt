package com.elbarody.data.remote.datasource.citydatasource

import com.elbarody.data.remote.model.CitesResponse
import com.elbarody.domain.helper.Response

interface ICityDataSource {
    suspend fun getCities(): Response<CitesResponse>
}