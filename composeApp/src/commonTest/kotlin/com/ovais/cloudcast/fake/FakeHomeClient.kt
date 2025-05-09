package com.ovais.cloudcast.fake

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.home.data.dto.WeatherResponse
import com.ovais.cloudcast.home.data.network.HomeClient

class FakeHomeClient : HomeClient {
    var mockResponse: Result<WeatherResponse, DataError.Remote> =
        Result.Success(fakeWeatherResponse)

    override suspend fun fetchWeatherInformation(
        city: String,
        canFetchAQI: Boolean
    ): Result<WeatherResponse, DataError.Remote> {
        return mockResponse
    }
}