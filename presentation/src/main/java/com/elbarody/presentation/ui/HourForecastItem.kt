package com.elbarody.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.elbarody.base.compose.annotatedString
import com.elbarody.base.utils.Dimens
import com.elbarody.domain.model.ForecastHourItem
import com.elbarody.presentation.R

@Composable
fun HourForecastItem(forecastHourItem: ForecastHourItem) {
    Column(
        modifier = Modifier
            .padding(top = Dimens.threeLevelPadding)
            .fillMaxWidth()
            .background(
                color = Color.White, shape = RoundedCornerShape(size = Dimens.fourLevelPadding)
            )
            .border(
                width = 2.dp,
                color = Color.Gray,
                shape = RoundedCornerShape(size = Dimens.fourLevelPadding)
            )
            .padding(Dimens.twoLevelPadding),
        verticalArrangement = Arrangement.spacedBy(Dimens.twoLevelPadding)
    ) {
        WeatherRowItem(
            annotatedString(stringResource(R.string.time), forecastHourItem.time),
            annotatedString(stringResource(R.string.temperature), forecastHourItem.temp)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = annotatedString(
                    stringResource(R.string.condition),
                    forecastHourItem.condition
                )
            )
            Image(
                modifier = Modifier.size(50.dp),
                painter = rememberAsyncImagePainter(forecastHourItem.icon),
                contentDescription = null
            )
        }
    }
}
