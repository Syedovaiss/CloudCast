package com.ovais.cloudcast.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.cloudcast.core.data.network.onError
import com.ovais.cloudcast.core.data.network.onSuccess
import com.ovais.cloudcast.home.domain.GetWeatherConfigurationUseCase
import com.ovais.cloudcast.home.domain.GetWeatherForecastUseCase
import com.ovais.cloudcast.home.domain.Weather
import com.ovais.cloudcast.home.domain.WeatherConfiguration
import com.ovais.cloudcast.utils.DEFAULT_CITY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getWeatherForecastUseCase: GetWeatherForecastUseCase,
    private val getWeatherConfigurationUseCase: GetWeatherConfigurationUseCase,
    private val weatherUiDataMapper: WeatherUiDataMapper
) : ViewModel() {

    private val _uiState by lazy { MutableStateFlow<HomeUIState>(HomeUIState.Loading) }
    val uiState: StateFlow<HomeUIState>
        get() = _uiState.asStateFlow()


    fun getLatestWeather() {
        updateState(HomeUIState.Loading)
        fetchCurrentWeather()
    }

    private fun fetchCurrentWeather() {
        viewModelScope.launch {
            val settings = getWeatherConfigurationUseCase()
            getWeatherForecastUseCase(DEFAULT_CITY)
                .onSuccess { data ->
                    mapWeatherInfoToUiData(data, settings)
                }.onError { error ->
                    updateState(HomeUIState.Error(error.name))
                }
        }
    }

    private fun mapWeatherInfoToUiData(
        data: Weather,
        settings: WeatherConfiguration
    ) {
        val uiData = weatherUiDataMapper.map(
            data = data,
            isCEnabled = settings.isUnitTypeC,
            isKPHEnabled = settings.isKPHEnabled
        )
        updateState(HomeUIState.Loaded(uiData))
    }

    private fun updateState(state: HomeUIState) {
        _uiState.update { state }
    }
}