package com.ovais.cloudcast.home.data.repository

import com.ovais.cloudcast.core.network.DataError
import com.ovais.cloudcast.core.network.Result
import com.ovais.cloudcast.core.network.map
import com.ovais.cloudcast.home.data.network.HomeClient
import com.ovais.cloudcast.home.domain.Weather

fun interface HomeRepository {
    suspend fun fetchWeatherForecast(
        city: String,
        canFetchAQI: Boolean
    ): Result<Weather, DataError.Remote>
}

class DefaultHomeRepository(
    private val homeClient: HomeClient
) : HomeRepository {
    override suspend fun fetchWeatherForecast(
        city: String,
        canFetchAQI: Boolean
    ): Result<Weather, DataError.Remote> {
        return homeClient.fetchWeatherInformation(city, canFetchAQI).map { response ->
            response.toWeather
        }
    }
}