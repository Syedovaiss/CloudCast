package com.ovais.cloudcast.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ForecastView(forecast: List<WeeklyForecast>) {
    forecast.forEach { item ->
        ForecastItemView(item)
    }
    /*LazyColumn {
        items(forecast) { item ->
            ForecastItemView(item)
        }
    }*/
}

@Composable
fun ForecastItemView(item: WeeklyForecast) {
    Column {
        Row {
            Text(item.date)
            Text(item.minTemperature)
            Text(item.maxTemperature)
        }
        LazyRow {
            items(item.hour) { item ->
                HourlyItemView(item)
            }
        }
    }
}

@Composable
fun HourlyItemView(item: HourlyForecast) {
    Column {
        Text(item.time)
        Text(item.feelsLike)
        Text(item.weatherType)
    }
}