package com.ovais.cloudcast.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cloudcast.composeapp.generated.resources.Res
import cloudcast.composeapp.generated.resources.ic_current_location
import cloudcast.composeapp.generated.resources.ic_dew
import cloudcast.composeapp.generated.resources.ic_humidity
import cloudcast.composeapp.generated.resources.ic_setting
import cloudcast.composeapp.generated.resources.ic_wind
import coil3.compose.AsyncImage
import com.ovais.cloudcast.core.presentation.appBackground
import com.ovais.cloudcast.core.presentation.composables.TemperatureText
import com.ovais.cloudcast.core.presentation.composables.TextIcon
import com.ovais.cloudcast.core.presentation.composables.TitleText
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeScreenView(
    data: HomeUiData
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackground)
            .verticalScroll(scrollState)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleText(data.locationName)
            Image(
                painter = painterResource(Res.drawable.ic_current_location),
                contentDescription = null
            )
            Spacer(modifier = Modifier.weight(1f))
            Image(
                painter = painterResource(Res.drawable.ic_setting),
                contentDescription = null
            )
        }

        AsyncImage(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    all = 16.dp
                )
                .width(200.dp)
                .height(200.dp),
            model = data.weatherIcon,
            contentDescription = null
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            TemperatureText(
                digitText = data.currentTemperature,
                weatherType = data.weatherType
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 24.dp
                )
                .background(
                    brush = Brush.sweepGradient(listOf(Color.Black, Color.Black)),
                    alpha = 0.1f,
                    shape = RoundedCornerShape(12.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextIcon(
                resource = Res.drawable.ic_dew,
                title = data.dew,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            TextIcon(
                resource = Res.drawable.ic_humidity,
                title = data.humidity,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            TextIcon(
                resource = Res.drawable.ic_wind,
                title = data.wind,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
        }
    }
}