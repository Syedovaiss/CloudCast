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

    override suspend fun fetchWeatherInformation(
        city: String,
        canFetchAQI: Boolean
    ): Result<WeatherResponse, DataError.Remote> {
        return safeCall<WeatherResponse> {
            httpClient.get("http://api.weatherapi.com/v1/$GET_WEATHER_FORECAST") {
                parameter("key", "6f071b0321a74bbcb30152108252704")
                parameter("q", city)
                parameter("aqi", getAQIParams(canFetchAQI))
                parameter("days", 7)
            }
        }
    }

    private fun getAQIParams(isAQIEnabled: Boolean): String {
        return if (isAQIEnabled) "yes" else "no"
    }
}