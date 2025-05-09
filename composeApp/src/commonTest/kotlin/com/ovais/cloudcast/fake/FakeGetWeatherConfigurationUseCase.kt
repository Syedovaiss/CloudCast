package com.ovais.cloudcast.fake

import com.ovais.cloudcast.home.domain.GetWeatherConfigurationUseCase
import com.ovais.cloudcast.home.domain.WeatherConfiguration

class FakeGetWeatherConfigurationUseCase : GetWeatherConfigurationUseCase {
    var config: WeatherConfiguration = WeatherConfiguration(isKPHEnabled = true, isUnitTypeC = true)

    override suspend fun invoke(): WeatherConfiguration = config
}