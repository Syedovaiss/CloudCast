package com.ovais.cloudcast.home.data.network

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.core.data.network.safeCall
import com.ovais.cloudcast.home.data.dto.WeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter

fun interface HomeClient {
    suspend fun fetchWeatherInformation(
        city: String,
        canFetchAQI: Boolean
    ): Result<WeatherResponse, DataError.Remote>
}

class DefaultHomeClient(
    private val httpClient: HttpClient
) : HomeClient {

    companion object {
        private const val KEY_CITY = "q"
        private const val KEY_AQI = "aqi"
        private const val KEY_DAYS = "days"
        private const val KEY_API_KEY = "key"
        private const val YES = "yes"
        private const val NO = "no"
    }

    override suspend fun fetchWeatherInformation(
        city: String,
        canFetchAQI: Boolean
    ): Result<WeatherResponse, DataError.Remote> {
        return safeCall<WeatherResponse> {
            httpClient.get(GET_WEATHER_FORECAST) {
                parameter(KEY_API_KEY, API_KEY)
                parameter(KEY_CITY, city)
                parameter(KEY_AQI, getAQIParams(canFetchAQI))
                parameter(KEY_DAYS, FORECAST_DAY_HISTORY)
            }
        }
    }

    private fun getAQIParams(
        isAQIEnabled: Boolean
    ): String {
        return if (isAQIEnabled) YES else NO
    }
}