package com.ovais.cloudcast.di

import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import com.ovais.cloudcast.core.data.database.CloudCastDatabase
import com.ovais.cloudcast.core.data.database.DatabaseFactory
import com.ovais.cloudcast.core.data.network.HttpClientFactory
import com.ovais.cloudcast.home.data.network.DefaultHomeClient
import com.ovais.cloudcast.home.data.network.HomeClient
import com.ovais.cloudcast.home.data.repository.DefaultHomeRepository
import com.ovais.cloudcast.home.data.repository.HomeRepository
import com.ovais.cloudcast.home.domain.DefaultGetWeatherConfigurationUseCase
import com.ovais.cloudcast.home.domain.DefaultGetWeatherForecastUseCase
import com.ovais.cloudcast.home.domain.GetWeatherConfigurationUseCase
import com.ovais.cloudcast.home.domain.GetWeatherForecastUseCase
import com.ovais.cloudcast.home.presentation.DefaultWeatherUiDataMapper
import com.ovais.cloudcast.home.presentation.HomeViewModel
import com.ovais.cloudcast.home.presentation.WeatherUiDataMapper
import com.ovais.cloudcast.settings.data.DefaultSettingsManager
import com.ovais.cloudcast.settings.data.SettingsManager
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.bind
import org.koin.dsl.module

expect val platformModule: Module

val sharedModule = module {
    single { HttpClientFactory.create(get()) }

    single {
        get<DatabaseFactory>().create()
            .setDriver(BundledSQLiteDriver())
            .build()
    }
    single { get<CloudCastDatabase>().appSettingDao }

    singleOf(::DefaultHomeClient).bind<HomeClient>()
    singleOf(::DefaultSettingsManager).bind<SettingsManager>()
    singleOf(::DefaultHomeRepository).bind<HomeRepository>()
    singleOf(::DefaultGetWeatherForecastUseCase).bind<GetWeatherForecastUseCase>()
    singleOf(::DefaultGetWeatherConfigurationUseCase).bind<GetWeatherConfigurationUseCase>()
    singleOf(::DefaultWeatherUiDataMapper).bind<WeatherUiDataMapper>()
    viewModelOf(::HomeViewModel)

}