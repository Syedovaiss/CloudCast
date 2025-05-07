package com.ovais.cloudcast.settings.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.width
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Switch
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cloudcast.composeapp.generated.resources.Res
import cloudcast.composeapp.generated.resources.ic_back
import com.ovais.cloudcast.core.presentation.appBackground
import com.ovais.cloudcast.core.presentation.composables.BodyText
import com.ovais.cloudcast.core.presentation.composables.HeaderText
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onBackPressed: () -> Unit
) {
    val currentSettings by viewModel.currentSettings.collectAsStateWithLifecycle()
    var aqiState by remember {
        mutableStateOf(false)
    }
    var measureInFState by remember {
        mutableStateOf(false)
    }
    var measureInKPH by remember {
        mutableStateOf(false)
    }
    LaunchedEffect(currentSettings) {
        aqiState = currentSettings.hasAQIEnabled
        measureInFState = currentSettings.isFEnabled
        measureInKPH = currentSettings.isKPHEnabled
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.statusBars.asPaddingValues())
            .background(appBackground)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(Res.drawable.ic_back),
                contentDescription = null,
                modifier = Modifier
                    .padding(
                        all = 16.dp
                    )
                    .width(40.dp)
                    .height(40.dp)
                    .clickable { onBackPressed() }
            )
            HeaderText(
                text = "App Setting",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        vertical = 40.dp,
                        horizontal = 8.dp
                    ),
                fontSize = 42.sp
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyText(
                "Air Quality Index Enabled"
            )
            Spacer(Modifier.weight(1f))
            Switch(
                checked = aqiState,
                onCheckedChange = { isChecked ->
                    aqiState = isChecked
                    viewModel.updateAQI(isChecked)
                },
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 24.dp
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyText(
                "Measure in Fahrenheit"
            )
            Spacer(Modifier.weight(1f))
            Switch(
                checked = measureInFState,
                onCheckedChange = { isChecked ->
                    measureInFState = isChecked
                    viewModel.updateTemperature(isChecked)
                },
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
        }
        HorizontalDivider(
            modifier = Modifier.padding(
                vertical = 4.dp,
                horizontal = 24.dp
            )
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 16.dp,
                    vertical = 8.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            BodyText(
                "Measure in KPH"
            )
            Spacer(Modifier.weight(1f))
            Switch(
                checked = measureInKPH,
                onCheckedChange = { isChecked ->
                    measureInKPH = isChecked
                    viewModel.updateMeasuringUnit(isChecked)
                },
                modifier = Modifier.padding(
                    horizontal = 16.dp
                )
            )
        }


    }
}