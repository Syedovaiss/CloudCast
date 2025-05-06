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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cloudcast.composeapp.generated.resources.Res
import cloudcast.composeapp.generated.resources.ic_current_location
import cloudcast.composeapp.generated.resources.ic_setting
import coil3.compose.AsyncImage
import com.ovais.cloudcast.core.presentation.composables.TemperatureText
import com.ovais.cloudcast.core.presentation.composables.TitleText
import com.ovais.cloudcast.core.presentation.primary
import com.ovais.cloudcast.home.domain.Weather
import com.ovais.cloudcast.utils.asImageUrl
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeScreenView(
    data: Weather
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(primary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TitleText("${data.location.name},${data.location.region}")
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
                    16.dp
                )
                .width(200.dp)
                .height(200.dp),
            model = data.current.condition.icon.asImageUrl,
            contentDescription = null
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            TemperatureText(
                digitText = data.current.temperatureInC.toString(),
                weatherType = data.current.condition.text
            )
        }

    }
}