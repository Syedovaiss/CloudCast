package com.ovais.cloudcast.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import cloudcast.composeapp.generated.resources.Res
import cloudcast.composeapp.generated.resources.evening
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        when (state) {
            is HomeUIState.Loading -> {
                Text("Loading.....")
            }

            is HomeUIState.Error -> {
                Text("Error.....:${(state as HomeUIState.Error).message}")

            }

            is HomeUIState.Loaded -> {
                Image(
                    painter = painterResource(Res.drawable.evening),
                    contentDescription = null
                )
                Text((state as HomeUIState.Loaded).data.location.name)
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}