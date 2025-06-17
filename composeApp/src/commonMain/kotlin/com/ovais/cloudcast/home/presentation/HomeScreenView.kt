package com.ovais.cloudcast.home.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cloudcast.composeapp.generated.resources.Res
import cloudcast.composeapp.generated.resources.ic_dew
import cloudcast.composeapp.generated.resources.ic_humidity
import cloudcast.composeapp.generated.resources.ic_search
import cloudcast.composeapp.generated.resources.ic_setting
import cloudcast.composeapp.generated.resources.ic_wind
import coil3.compose.AsyncImage
import com.ovais.cloudcast.core.presentation.appBackground
import com.ovais.cloudcast.core.presentation.composables.SearchBar
import com.ovais.cloudcast.core.presentation.composables.TemperatureText
import com.ovais.cloudcast.core.presentation.composables.TextIcon
import com.ovais.cloudcast.core.presentation.composables.TitleText
import com.ovais.cloudcast.utils.EMPTY_STRING
import org.jetbrains.compose.resources.painterResource


@Composable
fun HomeScreenView(
    data: HomeUiData,
    onSettingsClicked: () -> Unit,
    onSearchQuerySubmitted: (String) -> Unit
) {
    var isSearchVisible by remember { mutableStateOf(false) }
    var searchQuery by remember { mutableStateOf(EMPTY_STRING) }
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
            verticalAlignment = Alignment.CenterVertically
        ) {
            TitleText(
                data.locationName,
                modifier = Modifier.weight(1f)
            )

            IconButton(onClick = { isSearchVisible = !isSearchVisible }) {
                Icon(
                    painter = painterResource(Res.drawable.ic_search),
                    contentDescription = "Search"
                )
            }

            IconButton(onClick = onSettingsClicked) {
                Icon(
                    painter = painterResource(Res.drawable.ic_setting),
                    contentDescription = "Settings"
                )
            }
        }
        AnimatedVisibility(visible = isSearchVisible) {
            SearchBar(
                query = searchQuery,
                onQueryChange = { searchQuery = it },
                onSearch = {
                    onSearchQuerySubmitted(searchQuery)
                    searchQuery = EMPTY_STRING
                    isSearchVisible = false
                }
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
        ForecastView(data.forecast)
    }
}