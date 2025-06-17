package com.ovais.cloudcast.fake

import com.ovais.cloudcast.home.domain.UpdateCityUseCase

class FakeUpdateCityUseCase : UpdateCityUseCase {
    override suspend fun invoke(param: String): Boolean {
        return true
    }
}