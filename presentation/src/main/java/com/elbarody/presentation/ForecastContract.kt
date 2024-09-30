package com.elbarody.presentation

import com.elbarody.base.mvi.UiEvent
import com.elbarody.base.mvi.UiState
import com.elbarody.domain.model.CitiesListDataModel
import com.elbarody.domain.model.ForecastModel

class ForecastContract {

    sealed class Event : UiEvent {
        data class OnCityClicked(val cityName: String, val lat: Double, val lon: Double) : Event()
        object OnStart : Event()
    }

    data class State(
        val forecastState: ForecastUiState = ForecastUiState.Idle,
        val cities: CitiesListDataModel? = null,
    ) : UiState

    sealed class ForecastUiState {
        object Idle : ForecastUiState()
        data class Loading(val isLoading: Boolean) : ForecastUiState()
        data class DisplayForecast(val forecast: ForecastModel) : ForecastUiState()
        data class DisplayCities(val cities: CitiesListDataModel) : ForecastUiState()
        data class DisplayError(val errorMessage: String? = null) : ForecastUiState()
    }
}
