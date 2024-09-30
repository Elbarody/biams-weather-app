package com.elbarody.data.repository.cityrepo

import com.elbarody.data.mapper.mapResponse
import com.elbarody.data.mapper.toCityListData
import com.elbarody.data.remote.datasource.citydatasource.CityDataSource
import com.elbarody.domain.helper.Response
import com.elbarody.domain.model.CitiesListDataModel
import com.elbarody.domain.repository.cityrepo.ICityRepository
import javax.inject.Inject

class CityRepository @Inject constructor(
    private val dataSource: CityDataSource
) : ICityRepository {

    override suspend fun getCities(): Response<CitiesListDataModel> =
        dataSource.getCities().mapResponse { cityResponse ->
            cityResponse.toCityListData()
        }

}