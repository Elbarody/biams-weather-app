package com.elbarody.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elbarody.base.compose.annotatedString
import com.elbarody.base.utils.Dimens
import com.elbarody.domain.model.CityWeatherData
import com.elbarody.presentation.R

@Composable
fun GeneralCityDataLayout(cityWeatherData: CityWeatherData) {
    Column(
        modifier = Modifier
            .padding(top = Dimens.threeLevelPadding)
            .fillMaxWidth()
            .background(
                color = Color.White, shape = RoundedCornerShape(size = Dimens.fourLevelPadding)
            )
            .border(
                width = 2.dp,
                color = Color.Black,
                shape = RoundedCornerShape(size = Dimens.fourLevelPadding)
            )
            .padding(Dimens.twoLevelPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.twoLevelPadding)
    ) {
        // Country and City Row
        WeatherRowItem(
            annotatedString(stringResource(R.string.country_name), cityWeatherData.countryName),
            annotatedString(stringResource(R.string.city_name), cityWeatherData.cityName)
        )

        // Sunrise and Sunset Row
        WeatherRowItem(
            annotatedString(stringResource(R.string.sunrise), cityWeatherData.sunrise),
            annotatedString(stringResource(R.string.sunset), cityWeatherData.sunset)
        )
    }
}