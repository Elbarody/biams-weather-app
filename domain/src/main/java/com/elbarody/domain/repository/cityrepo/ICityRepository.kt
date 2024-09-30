package com.elbarody.domain.repository.cityrepo

import com.elbarody.domain.helper.Response
import com.elbarody.domain.model.CitiesListDataModel

interface ICityRepository {

    suspend fun getCities(): Response<CitiesListDataModel>
}