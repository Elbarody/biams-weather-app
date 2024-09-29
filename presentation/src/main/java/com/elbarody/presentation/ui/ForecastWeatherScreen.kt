package com.elbarody.presentation.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.elbarody.presentation.ForecastViewModel

@Composable
fun ForecastWeatherScreen(viewModel: ForecastViewModel = hiltViewModel()){

    Text("Elbarody")
}

@Preview
@Composable
private fun ForecastWeatherScreenPreview(){
    ForecastWeatherScreen()
}