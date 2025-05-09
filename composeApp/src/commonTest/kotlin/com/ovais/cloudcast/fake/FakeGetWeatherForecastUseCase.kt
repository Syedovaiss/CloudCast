package com.ovais.cloudcast.fake

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.home.domain.GetWeatherForecastUseCase
import com.ovais.cloudcast.home.domain.Weather

class FakeGetWeatherForecastUseCase : GetWeatherForecastUseCase {
    var result: Result<Weather, DataError.Remote> = Result.Success(fakeWeatherResponse.toWeather)

    override suspend fun invoke(param: String): Result<Weather, DataError.Remote> = result
}
