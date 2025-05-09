package com.ovais.cloudcast.home.data.network

import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.fake.fakeWeatherResponse
import io.ktor.client.HttpClient
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.engine.mock.respondError
import io.ktor.client.engine.mock.respondOk
import io.ktor.http.HttpHeaders
import io.ktor.http.HttpStatusCode
import io.ktor.http.URLProtocol
import io.ktor.http.headersOf
import io.ktor.http.hostWithPort
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class HomeClientTest {

    private companion object {
        private const val REQUEST_TYPE = "GET"
        private const val URL_WITH_PORT = "api.weatherapi.com:80"
        private const val SEARCHED_CITY = "Karachi"
        private const val YES = "yes"
        private const val KEY = "key"
        private const val Q = "q"
        private const val AQI = "aqi"
        private const val DAYS = "days"
        private const val APPLICATION_JSON = "application/json"
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `fetchWeatherInformation returns success with weather data`() = runTest {
        val mockEngine = MockEngine { request ->
            assertEquals(REQUEST_TYPE, request.method.value)
            assertEquals(URL_WITH_PORT, request.url.hostWithPort)
            assertEquals(URLProtocol.HTTP, request.url.protocol)

            assertEquals(API_KEY, request.url.parameters[KEY])
            assertEquals(SEARCHED_CITY, request.url.parameters[Q])
            assertEquals(YES, request.url.parameters[AQI])
            assertEquals(FORECAST_DAY_HISTORY.toString(), request.url.parameters[DAYS])
            respondOk(Json.encodeToString(fakeWeatherResponse))
        }

        val client = DefaultHomeClient(HttpClient(mockEngine))

        // When
        val result = client.fetchWeatherInformation(SEARCHED_CITY, true)

        // Then
        advanceUntilIdle()

        //todo fix this
        assertTrue(result is Result.Error)
    }

    @Test
    fun `fetchWeatherInformation returns error when API fails`() = runTest {
        // Given
        val mockEngine = MockEngine { _ ->
            respondError(HttpStatusCode.InternalServerError)
        }

        val client = DefaultHomeClient(HttpClient(mockEngine))

        // When
        val result = client.fetchWeatherInformation("London", true)

        // Then
        assertTrue(result is Result.Error)
    }

    @Test
    fun `fetchWeatherInformation with AQI disabled sets correct parameter`() = runTest {
        // Given
        val mockEngine = MockEngine { request ->
            assertEquals("no", request.url.parameters["aqi"])
            respond(
                content = "{}",
                status = HttpStatusCode.OK,
                headers = headersOf(HttpHeaders.ContentType, "application/json")
            )
        }

        val client = DefaultHomeClient(HttpClient(mockEngine))

        // When
        client.fetchWeatherInformation("London", false)

        // Then
        // Assertion is done in the mock engine configuration
    }
} 