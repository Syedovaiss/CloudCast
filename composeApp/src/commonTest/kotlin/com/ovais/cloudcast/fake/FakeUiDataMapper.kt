package com.ovais.cloudcast.fake

import com.ovais.cloudcast.home.domain.Weather
import com.ovais.cloudcast.home.presentation.HomeUiData
import com.ovais.cloudcast.home.presentation.WeatherUiDataMapper

class FakeWeatherUiDataMapper : WeatherUiDataMapper {
    var mappedData = homeUiData

    override fun map(
        data: Weather,
        isCEnabled: Boolean,
        isKPHEnabled: Boolean
    ): HomeUiData = mappedData
}