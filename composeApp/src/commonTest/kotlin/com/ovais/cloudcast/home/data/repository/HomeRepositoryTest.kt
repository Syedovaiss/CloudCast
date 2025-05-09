package com.ovais.cloudcast.home.data.repository

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.fake.FakeHomeClient
import com.ovais.cloudcast.fake.fakeWeatherResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeRepositoryTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var homeClient: FakeHomeClient
    private lateinit var repository: DefaultHomeRepository

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        homeClient = FakeHomeClient()
        repository = DefaultHomeRepository(
            homeClient = homeClient,
            dispatcherIO = testDispatcher,
            dispatcherDefault = testDispatcher
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `fetchWeatherForecast returns success with weather data`() = runTest {

        val city = "Karachi"
        val canFetchAQI = true

        val result = repository.fetchWeatherForecast(city, canFetchAQI)

        advanceUntilIdle()

        assertTrue(result is Result.Success)
        assertEquals(fakeWeatherResponse.toWeather, result.data)
    }

    @Test
    fun `fetchWeatherForecast returns error`() = runTest {

        val city = "Karachi"
        val canFetchAQI = true
        homeClient.mockResponse = Result.Error(DataError.Remote.SERVER)
        val result = repository.fetchWeatherForecast(city, canFetchAQI)

        advanceUntilIdle()

        assertTrue(result is Result.Error)
        assertEquals(result.error, DataError.Remote.SERVER)
    }
}