package com.ovais.cloudcast.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.cloudcast.core.network.onError
import com.ovais.cloudcast.core.network.onSuccess
import com.ovais.cloudcast.home.domain.GetCurrentWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCurrentWeatherUseCase: GetCurrentWeatherUseCase
) : ViewModel() {

    private val _uiState by lazy { MutableStateFlow<HomeUIState>(HomeUIState.Loading) }
    val uiState: StateFlow<HomeUIState>
        get() = _uiState.asStateFlow()

    init {
        fetchCurrentWeather()
    }

    private fun fetchCurrentWeather() {
        viewModelScope.launch {
            getCurrentWeatherUseCase("Karachi")
                .onSuccess { data ->
                    updateState(HomeUIState.Loaded(data))
                }.onError { error ->
                    updateState(HomeUIState.Error(error.name))
                }
        }
    }

    private fun updateState(state: HomeUIState) {
        _uiState.update { state }
    }
}