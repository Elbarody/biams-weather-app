package com.elbarody.baimsweatherapp.ui

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.elbarody.baimsweatherapp.ui.MainDestinations.FORECAST_SCREEN
import com.elbarody.base.theme.WeatherAppTheme
import com.elbarody.presentation.ui.ForecastWeatherScreen

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun WeatherApp(
    appNavController: NavHostController
) {
    SharedTransitionLayout {
        WeatherAppTheme {
            NavHost(
                modifier = Modifier.fillMaxSize(),
                navController = appNavController,
                startDestination = FORECAST_SCREEN,
            ) {

                composable(route = FORECAST_SCREEN) {
                    ForecastWeatherScreen()
                }
            }
        }
    }
}