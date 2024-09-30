package com.elbarody.data.remote.datasource.citydatasource

import com.elbarody.data.remote.api.CitiesApi
import com.elbarody.data.remote.helper.apiCall
import com.elbarody.data.remote.model.CitesResponse
import com.elbarody.domain.helper.Response
import javax.inject.Inject

class CityDataSource @Inject constructor(
    private val api: CitiesApi
) : ICityDataSource {

    override suspend fun getCities(): Response<CitesResponse> = apiCall {
        api.getCities()
    }
}