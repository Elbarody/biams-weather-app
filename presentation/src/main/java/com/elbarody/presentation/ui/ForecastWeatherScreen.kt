package com.elbarody.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
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

    val isDataLoaded = uiState.forecastState is ForecastContract.ForecastUiState.DisplayForecast


    when (val state = uiState.forecastState) {
        is ForecastContract.ForecastUiState.Loading -> {
            SmallCenteredCircularProgressIndicator()
        }

        is ForecastContract.ForecastUiState.DisplayError -> ShowUserMessage(
            message = state.errorMessage ?: ""
        )

        is ForecastContract.ForecastUiState.DisplayForecast -> {
        }

        is ForecastContract.ForecastUiState.Idle -> {
            viewModel.handleEvent(ForecastContract.Event.onCityClicked("Cairo",0.0,0.0))
        }

    }

    AppScaffold(modifier = Modifier
        .padding(Dimens.fourLevelPadding)
        .fillMaxWidth(), topBar = {

    }) {
        if (isDataLoaded) {
            val forecastModel =
                (uiState.forecastState as? ForecastContract.ForecastUiState.DisplayForecast)?.forecast
            val forecastDailyList = forecastModel?.forecastDailyList ?: emptyList()

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(it)
            ) {
                forecastModel?.let { forecastModel ->
                    GeneralCityLayout(cityData = forecastModel.cityData)
                }
                Column(modifier = Modifier.fillMaxWidth().wrapContentHeight().verticalScroll(rememberScrollState())) {
                    forecastDailyList.forEach { forecastDailyItem ->
                        DailyForecastItem(forecastDailyItem = forecastDailyItem)
                    }
                }

            }
        }
    }
}


@Preview
@Composable
private fun ForecastWeatherScreenPreview() {
    ForecastWeatherScreen()
}