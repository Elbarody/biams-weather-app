package com.elbarody.presentation

import androidx.lifecycle.viewModelScope
import com.elbarody.base.mvi.BaseViewModel
import com.elbarody.domain.helper.Response
import com.elbarody.domain.model.CitiesListDataModel
import com.elbarody.domain.model.ForecastModel
import com.elbarody.domain.usecases.cityusecase.GetCityUseCase
import com.elbarody.domain.usecases.forecastusecase.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastUseCase: GetForecastUseCase,
    private val cityUseCase: GetCityUseCase
) : BaseViewModel<ForecastContract.Event, ForecastContract.State>() {

    override fun createInitialState() = ForecastContract.State(
        forecastState = ForecastContract.ForecastUiState.Idle,
    )

    override fun handleEvent(event: ForecastContract.Event) {
        when (event) {
            is ForecastContract.Event.OnCityClicked -> {
                getForecast(event.lat, event.lon)
            }
            ForecastContract.Event.OnStart -> getCities()
        }
    }


    private fun getForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            executeRequest(
                request = { forecastUseCase.invoke(lat, lon) },
                onSuccess = { response -> handleForecastResponse(response) }
            )
        }
    }

    private fun getCities() {
        viewModelScope.launch {
            executeRequest(
                request = { cityUseCase.invoke() },
                onSuccess = { response -> handleCitiesResponse(response) }
            )
        }
    }

    private suspend fun <T> executeRequest(
        request: suspend () -> Response<T>,
        onSuccess: (Response<T>) -> Unit
    ) {
        setState { copy(forecastState = ForecastContract.ForecastUiState.Loading(true)) }

        kotlin.runCatching {
            request()
        }.onSuccess { response ->
            onSuccess(response)
        }.onFailure { exception ->
            setErrorState(exception.message ?: "Error occurred, please try again")
        }
    }

    private fun handleForecastResponse(response: Response<ForecastModel>) {
        when (response) {
            is Response.Success -> {
                setState { copy(forecastState = ForecastContract.ForecastUiState.DisplayForecast(response.data)) }
            }
            is Response.Error -> {
                setErrorState(response.errorMessage)
            }
        }
    }

    private fun handleCitiesResponse(response: Response<CitiesListDataModel>) {
        when (response) {
            is Response.Success -> setState { copy(forecastState = ForecastContract.ForecastUiState.DisplayCities(response.data)) }
            is Response.Error -> setErrorState(response.errorMessage)
        }
    }

    private fun setErrorState(errorMessage: String) {
        setState { copy(forecastState = ForecastContract.ForecastUiState.DisplayError(errorMessage)) }
    }
}