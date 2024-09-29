package com.elbarody.presentation

import com.elbarody.base.mvi.UiEvent
import com.elbarody.base.mvi.UiState
import com.elbarody.domain.model.ForecastModel

class ForecastContract {

    sealed class Event : UiEvent {
        data class onCityClicked(val cityName: String,val lat: Double,val lon: Double) : Event()

    }

    data class State(
        val forecastState: ForecastUiState = ForecastUiState.Idle,
        val cityName: String = "",
    ) : UiState


    sealed class ForecastUiState {
        data object Idle : ForecastUiState()
        data class Loading(val isLoading: Boolean) : ForecastUiState()
        data class DisplayForecast(val forecast: ForecastModel) : ForecastUiState()
        data class DisplayError(val errorMessage: String? = null) : ForecastUiState()
    }
}