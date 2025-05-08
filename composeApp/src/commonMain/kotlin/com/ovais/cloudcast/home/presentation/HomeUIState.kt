package com.ovais.cloudcast.home.presentation

sealed interface HomeUIState {
    data object Loading : HomeUIState
    data class Loaded(val data: HomeUiData) : HomeUIState
    data class Error(val message: String) : HomeUIState
}