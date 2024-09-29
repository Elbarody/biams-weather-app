package com.elbarody.presentation

import androidx.lifecycle.viewModelScope
import com.elbarody.base.mvi.BaseViewModel
import com.elbarody.domain.helper.Response
import com.elbarody.domain.usecases.GetForecastUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ForecastViewModel @Inject constructor(
    private val forecastUseCase: GetForecastUseCase
) : BaseViewModel<ForecastContract.Event, ForecastContract.State>() {


    override fun createInitialState(): ForecastContract.State {
        return ForecastContract.State(
            ForecastContract.ForecastUiState.Idle, cityName = ""
        )
    }

    override fun handleEvent(event: ForecastContract.Event) {
        when (event) {
            is ForecastContract.Event.onCityClicked -> {
                setState {
                    copy(
                        cityName = event.cityName,
                    )
                }
                getForecast(event.lat, event.lon)
            }
        }
    }

    private fun getForecast(lat: Double, lon: Double) {
        viewModelScope.launch {
            kotlin.runCatching {
                setState { copy(forecastState = ForecastContract.ForecastUiState.Loading(true)) }
                forecastUseCase.invoke(30.0444, 31.2357)
            }.onSuccess { response ->
                if (response is Response.Success) {
                    val forecastPresentation = response.data
                    setState {
                        copy(
                            forecastState = ForecastContract.ForecastUiState.DisplayForecast(
                                forecastPresentation
                            ),
                        )
                    }
                } else if (response is Response.Error) {
                    setErrorState(response.errorMessage)
                }
            }.onFailure {
                setErrorState(it.message ?: "Error occurred, please try again")
            }
        }
    }

    private fun setErrorState(errorMessage: String) {
        setState { copy(forecastState = ForecastContract.ForecastUiState.DisplayError(errorMessage)) }
    }
}
