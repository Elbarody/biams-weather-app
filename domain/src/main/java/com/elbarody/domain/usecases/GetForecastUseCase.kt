package com.elbarody.domain.usecases

import com.elbarody.domain.repository.IForecastRepository
import javax.inject.Inject

class GetForecastUseCase @Inject constructor(private val repository: IForecastRepository) {

    suspend operator fun invoke(lat: Double, lon: Double) = repository.getForecast(lat, lon)
}