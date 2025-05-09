package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.fake.FakeHomeClient
import com.ovais.cloudcast.fake.FakeSettingsManager
import com.ovais.cloudcast.fake.fakeWeatherResponse
import com.ovais.cloudcast.home.data.repository.DefaultHomeRepository
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetWeatherForecastUseCaseTest {

    private lateinit var homeClient: FakeHomeClient
    private lateinit var settingsManager: FakeSettingsManager
    private lateinit var useCase: DefaultGetWeatherForecastUseCase

    @BeforeTest
    fun setup() {
        homeClient = FakeHomeClient()
        settingsManager = FakeSettingsManager()
        useCase = DefaultGetWeatherForecastUseCase(
            homeRepository = DefaultHomeRepository(homeClient),
            settingsManager = settingsManager
        )
    }

    @Test
    fun `when AQI is enabled and weather fetch succeeds returns success with weather data`() =
        runTest {
            // Given
            settingsManager.mockHasAQIEnabled = true
            homeClient.mockResponse = Result.Success(fakeWeatherResponse)

            // When
            val result = useCase("Karachi")

            // Then
            assertTrue(result is Result.Success)
            assertEquals(fakeWeatherResponse.toWeather, result.data)
        }

    @Test
    fun `when AQI is disabled and weather fetch succeeds returns success with weather data`() =
        runTest {
            // Given
            settingsManager.mockHasAQIEnabled = false
            homeClient.mockResponse = Result.Success(fakeWeatherResponse)

            // When
            val result = useCase("Karachi")

            // Then
            assertTrue(result is Result.Success)
            assertEquals(fakeWeatherResponse.toWeather, result.data)
        }

    @Test
    fun `when weather fetch fails returns error`() = runTest {
        // Given
        settingsManager.mockHasAQIEnabled = true
        homeClient.mockResponse = Result.Error(DataError.Remote.SERVER)

        // When
        val result = useCase("Karachi")

        // Then
        assertTrue(result is Result.Error)
        assertEquals(DataError.Remote.SERVER, result.error)
    }
} 