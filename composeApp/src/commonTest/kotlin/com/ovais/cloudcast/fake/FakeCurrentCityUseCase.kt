package com.ovais.cloudcast.fake

import com.ovais.cloudcast.home.domain.GetCurrentCityUseCase
import com.ovais.cloudcast.utils.DEFAULT_CITY

class FakeCurrentCityUseCase : GetCurrentCityUseCase {
    override suspend fun invoke(): String {
        return DEFAULT_CITY
    }
}