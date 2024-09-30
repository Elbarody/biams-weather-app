package com.elbarody.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.elbarody.domain.model.CitiesListDataModel
import com.elbarody.domain.model.CityDataModel

@Composable
fun DropDownCities(cities: CitiesListDataModel, onCityClicked: (String, Double, Double) -> Unit) {
    val citiesList = cities.citiesList
    var expanded by remember { mutableStateOf(false) }
    var selectedItem by remember { mutableStateOf<CityDataModel?>(null) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp)
    ) {
        Text(
            text = selectedItem?.cityName ?: "Select city, please",
            modifier = Modifier
                .clickable {
                    expanded = !expanded
                }
                .background(Color.LightGray, shape = RoundedCornerShape(8.dp))
                .padding(16.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(8.dp)),
            color = Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.White)
                .clip(RoundedCornerShape(8.dp))
                .padding(8.dp)
        ) {
            citiesList.forEachIndexed { index, city ->
                DropdownMenuItem(
                    text = {
                        Text(
                            text = city.cityName,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Normal
                        )
                    },
                    onClick = {
                        selectedItem = city
                        onCityClicked(city.cityName, city.lat, city.lon)
                        expanded = false
                    }
                )

                if (index < citiesList.lastIndex) {
                    Divider(color = Color.Gray, thickness = 1.dp)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SpinnerExamplePreview() {
    DropDownCities(cities = CitiesListDataModel(emptyList())) { cityName, lat, lon ->
    }
}
