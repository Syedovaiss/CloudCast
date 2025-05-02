package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.core.network.DataError
import com.ovais.cloudcast.core.network.Result
import com.ovais.cloudcast.core.usecase.SuspendParameterizedUseCase
import com.ovais.cloudcast.home.data.repository.HomeRepository

interface GetCurrentWeatherUseCase :
    SuspendParameterizedUseCase<String, Result<Weather, DataError.Remote>>


class DefaultGetCurrentWeatherUseCase(
    private val homeRepository: HomeRepository
) : GetCurrentWeatherUseCase {
    override suspend fun invoke(param: String): Result<Weather, DataError.Remote> {
        return homeRepository.fetchWeatherForecast(param, true)
    }
}