package com.elbarody.domain.usecases.cityusecase

import com.elbarody.domain.repository.cityrepo.ICityRepository
import com.elbarody.domain.repository.forecastrepo.IForecastRepository
import javax.inject.Inject

class GetCityUseCase @Inject constructor(private val repository: ICityRepository) {

    suspend operator fun invoke() = repository.getCities()
}