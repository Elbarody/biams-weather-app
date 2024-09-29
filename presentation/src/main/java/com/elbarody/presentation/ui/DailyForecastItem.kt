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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.elbarody.base.compose.annotatedString
import com.elbarody.base.utils.Dimens
import com.elbarody.domain.model.ForecastDailyItem
import com.elbarody.presentation.R

@Composable
fun DailyForecastItem(forecastDailyItem: ForecastDailyItem) {
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
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = annotatedString(stringResource(R.string.day_of), forecastDailyItem.date))
            Image(
                modifier = Modifier.size(50.dp),
                painter = painterResource(R.drawable.ic_arrow_down),
                contentDescription = null
            )
        }

        WeatherRowItem(
            annotatedString(stringResource(R.string.min_temp), forecastDailyItem.tempMin),
            annotatedString(stringResource(R.string.max_temp), forecastDailyItem.tempMax)
        )

        Column(modifier = Modifier.fillMaxWidth().wrapContentHeight()) {
            forecastDailyItem.forecastHourList.forEach { forecastHourItem ->
                HourForecastItem(forecastHourItem = forecastHourItem)
            }

        }
    }
}