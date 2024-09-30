package com.elbarody.data.mapper

import com.elbarody.data.remote.model.CitesResponse
import com.elbarody.data.remote.model.CityItem
import com.elbarody.domain.model.CitiesListDataModel
import com.elbarody.domain.model.CityDataModel

fun CitesResponse.toCityListData(): CitiesListDataModel {
    return CitiesListDataModel(
        citiesList = citiesList.map { it.toCitItemModel() }
    )
}

private fun CityItem.toCitItemModel(): CityDataModel {
    return CityDataModel(
        cityName = cityNameEn,
        id = id,
        lat = lat,
        lon = lon
    )
}
