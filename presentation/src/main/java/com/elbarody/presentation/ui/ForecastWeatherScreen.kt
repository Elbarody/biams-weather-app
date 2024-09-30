package com.elbarody.presentation.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.elbarody.base.compose.AppScaffold
import com.elbarody.base.compose.ShowUserMessage
import com.elbarody.base.compose.SmallCenteredCircularProgressIndicator
import com.elbarody.base.utils.Dimens
import com.elbarody.presentation.ForecastContract
import com.elbarody.presentation.ForecastViewModel

@Composable
fun ForecastWeatherScreen(viewModel: ForecastViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    AppScaffold(
        modifier = Modifier
            .padding(Dimens.fourLevelPadding)
            .fillMaxWidth(),
        topBar = {
            uiState.cities
            // Add your top bar implementation here
        }
    ) {paddingValues ->
        when (val state = uiState.forecastState) {
            is ForecastContract.ForecastUiState.Loading -> {
                SmallCenteredCircularProgressIndicator()
            }

            is ForecastContract.ForecastUiState.DisplayError -> {
                ShowUserMessage(message = state.errorMessage ?: "")
            }

            is ForecastContract.ForecastUiState.DisplayForecast -> {
                DisplayForecast(uiState,paddingValues)
            }

            is ForecastContract.ForecastUiState.Idle -> {
                viewModel.handleEvent(ForecastContract.Event.OnCityClicked("Cairo", 0.0, 0.0))
            }

        }
    }
}

@Composable
private fun DisplayForecast(uiState: ForecastContract.State, paddingValues: PaddingValues) {
    val forecastModel =
        (uiState.forecastState as? ForecastContract.ForecastUiState.DisplayForecast)?.forecast
    val forecastDailyList = forecastModel?.forecastDailyList ?: emptyList()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = paddingValues)
            .verticalScroll(rememberScrollState())
    ) {
        forecastModel?.let { model ->
            GeneralCityLayout(cityWeatherData = model.cityWeatherData)
        }
        forecastDailyList.forEach { forecastDailyItem ->
            DailyForecastItem(forecastDailyItem = forecastDailyItem)
        }
    }
}

@Preview
@Composable
private fun ForecastWeatherScreenPreview() {
    ForecastWeatherScreen()
}
