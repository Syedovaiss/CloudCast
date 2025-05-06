package com.ovais.cloudcast.home.presentation

import com.ovais.cloudcast.home.domain.Weather
import com.ovais.cloudcast.utils.orEmpty
import com.ovais.cloudcast.utils.orZero

fun interface WeatherUiDataMapper {
    fun map(data: Weather, isCEnabled: Boolean, isKPHEnabled: Boolean): HomeUiData
}

class DefaultWeatherUiDataMapper : WeatherUiDataMapper {
    override fun map(
        data: Weather,
        isCEnabled: Boolean,
        isKPHEnabled: Boolean
    ): HomeUiData {
        return HomeUiData(
            weatherType = data.current.condition.text.orEmpty,
            weatherIcon = data.current.condition.icon.orEmpty,
            currentTemperature = if (isCEnabled) {
                data.current.temperatureInC.orZero.toString()
            } else data.current.temperatureInF.orZero.toString(),
            windDirection = data.current.windDirection,
            wind = if (isKPHEnabled) {
                data.current.windInKPH.toString()
            } else data.current.windInMPH.toString()
        )
    }
}