package com.ovais.cloudcast.home.presentation

import com.ovais.cloudcast.home.domain.Weather

sealed interface HomeUIState {
    data object Loading : HomeUIState
//    data class Loaded(val data: HomeUiData) : HomeUIState
    data class Loaded(val data: Weather) : HomeUIState
    data class Error(val message: String) : HomeUIState
}