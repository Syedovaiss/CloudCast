package com.ovais.cloudcast.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cloudcast.composeapp.generated.resources.Res
import cloudcast.composeapp.generated.resources.ic_feels_like
import cloudcast.composeapp.generated.resources.ic_max_temperature
import cloudcast.composeapp.generated.resources.ic_min_temperature
import coil3.compose.AsyncImage
import com.ovais.cloudcast.core.presentation.composables.BodyText
import com.ovais.cloudcast.core.presentation.composables.TextIcon

@Composable
fun ForecastView(forecast: List<WeeklyForecast>) {
    forecast.forEach { item ->
        ForecastItemView(item)
    }
}

@Composable
fun ForecastItemView(item: WeeklyForecast) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                all = 16.dp
            )
            .background(
                brush = Brush.sweepGradient(listOf(Color.Black, Color.Black)),
                alpha = 0.1f,
                shape = RoundedCornerShape(12.dp)

            )
    ) {
        Row(
            modifier = Modifier.padding(
                all = 16.dp
            ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyText(
                item.date,
                modifier = Modifier.padding(
                    horizontal = 8.dp
                )
            )
            Spacer(Modifier.weight(1f))

            TextIcon(
                resource = Res.drawable.ic_min_temperature,
                title = item.minTemperature,
                modifier = Modifier.padding(
                    horizontal = 8.dp
                )
            )
            TextIcon(
                resource = Res.drawable.ic_max_temperature,
                title = item.maxTemperature,
                modifier = Modifier.padding(
                    horizontal = 8.dp
                )
            )
        }
        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(
                horizontal = 8.dp
            )
        )

        LazyRow {
            items(item.hour) { item ->
                HourlyItemView(item)
            }
        }
    }
}

@Composable
fun HourlyItemView(item: HourlyForecast) {
    Column(
        modifier = Modifier.padding(
            all = 16.dp
        )
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(
                    all = 16.dp
                )
                .width(50.dp)
                .height(50.dp),
            model = item.weatherIcon,
            contentDescription = null,
            contentScale = ContentScale.Fit
        )
        BodyText(item.time)
        TextIcon(
            resource = Res.drawable.ic_feels_like,
            title = item.feelsLike
        )
    }
}