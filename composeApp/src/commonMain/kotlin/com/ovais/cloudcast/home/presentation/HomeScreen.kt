package com.ovais.cloudcast.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = koinViewModel(),
    onSettingsClicked:() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(WindowInsets.statusBars.asPaddingValues())
    ) {
        val state by viewModel.uiState.collectAsStateWithLifecycle()
        when (state) {
            is HomeUIState.Loading -> HomeLoadingView()

            is HomeUIState.Error -> HomeErrorView(
                message = (state as HomeUIState.Error).message
            )

            is HomeUIState.Loaded -> HomeScreenView((state as HomeUIState.Loaded).data,onSettingsClicked)
        }

    }
}