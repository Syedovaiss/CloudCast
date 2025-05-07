package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.core.domain.usecase.SuspendParameterizedUseCase
import com.ovais.cloudcast.home.data.repository.HomeRepository
import com.ovais.cloudcast.settings.data.SettingsManager

interface GetWeatherForecastUseCase :
    SuspendParameterizedUseCase<String, Result<Weather, DataError.Remote>>


class DefaultGetWeatherForecastUseCase(
    private val homeRepository: HomeRepository,
    private val settingsManager: SettingsManager
) : GetWeatherForecastUseCase {
    override suspend fun invoke(param: String): Result<Weather, DataError.Remote> {
        return homeRepository.fetchWeatherForecast(param, settingsManager.hasAQIEnabled())
    }
}