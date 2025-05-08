package com.ovais.cloudcast.home.data.repository

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.core.data.network.map
import com.ovais.cloudcast.home.data.network.HomeClient
import com.ovais.cloudcast.home.domain.Weather
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

fun interface HomeRepository {
    suspend fun fetchWeatherForecast(
        city: String,
        canFetchAQI: Boolean
    ): Result<Weather, DataError.Remote>
}

class DefaultHomeRepository(
    private val homeClient: HomeClient,
    private val dispatcherIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatcherDefault: CoroutineDispatcher = Dispatchers.Default
) : HomeRepository {
    override suspend fun fetchWeatherForecast(
        city: String,
        canFetchAQI: Boolean
    ): Result<Weather, DataError.Remote> {
        return withContext(dispatcherIO) {
            homeClient.fetchWeatherInformation(city, canFetchAQI).map { response ->
                withContext(dispatcherDefault) {
                    response.toWeather
                }
            }
        }
    }
}