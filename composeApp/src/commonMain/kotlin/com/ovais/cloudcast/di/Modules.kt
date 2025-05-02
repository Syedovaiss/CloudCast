package com.ovais.cloudcast.di

import com.ovais.cloudcast.core.network.HttpClientFactory
import com.ovais.cloudcast.home.data.network.DefaultHomeClient
import com.ovais.cloudcast.home.data.network.HomeClient
import com.ovais.cloudcast.home.data.repository.DefaultHomeRepository
import com.ovais.cloudcast.home.data.repository.HomeRepository
import com.ovais.cloudcast.home.domain.DefaultGetCurrentWeatherUseCase
import com.ovais.cloudcast.home.domain.GetCurrentWeatherUseCase
import com.ovais.cloudcast.home.presentation.HomeViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }
    singleOf(::DefaultHomeClient).bind<HomeClient>()
    singleOf(::DefaultHomeRepository).bind<HomeRepository>()
    singleOf(::DefaultGetCurrentWeatherUseCase).bind<GetCurrentWeatherUseCase>()

    viewModelOf(::HomeViewModel)

}