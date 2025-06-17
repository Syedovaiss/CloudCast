package com.ovais.cloudcast.home.presentation

import com.ovais.cloudcast.core.data.network.DataError
import com.ovais.cloudcast.core.data.network.Result
import com.ovais.cloudcast.fake.FakeCurrentCityUseCase
import com.ovais.cloudcast.fake.FakeGetWeatherConfigurationUseCase
import com.ovais.cloudcast.fake.FakeGetWeatherForecastUseCase
import com.ovais.cloudcast.fake.FakeUpdateCityUseCase
import com.ovais.cloudcast.fake.FakeWeatherUiDataMapper
import com.ovais.cloudcast.fake.fakeWeatherResponse
import com.ovais.cloudcast.fake.homeUiData
import com.ovais.cloudcast.home.domain.WeatherConfiguration
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {

    private val testDispatcher = StandardTestDispatcher()
    private lateinit var getWeatherForecastUseCase: FakeGetWeatherForecastUseCase
    private lateinit var getWeatherConfigurationUseCase: FakeGetWeatherConfigurationUseCase
    private lateinit var weatherUiDataMapper: FakeWeatherUiDataMapper
    private lateinit var viewModel: HomeViewModel
    private lateinit var updateCityUseCase: FakeUpdateCityUseCase
    private lateinit var currentCityUseCase: FakeCurrentCityUseCase

    @BeforeTest
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        getWeatherForecastUseCase = FakeGetWeatherForecastUseCase()
        getWeatherConfigurationUseCase = FakeGetWeatherConfigurationUseCase()
        weatherUiDataMapper = FakeWeatherUiDataMapper()
        updateCityUseCase = FakeUpdateCityUseCase()
        currentCityUseCase = FakeCurrentCityUseCase()
        viewModel = HomeViewModel(
            getWeatherForecastUseCase = getWeatherForecastUseCase,
            getWeatherConfigurationUseCase = getWeatherConfigurationUseCase,
            weatherUiDataMapper = weatherUiDataMapper,
            updateCityUseCase = updateCityUseCase,
            currentCityUseCase = currentCityUseCase
        )
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `initial state is Loading`() = runTest {
        assertTrue(viewModel.uiState.value is HomeUIState.Loading)
    }

    @Test
    fun `get latest weather success case updates state to Loaded`() = runTest {
        // Given
        val mockConfig = WeatherConfiguration(
            isUnitTypeC = true,
            isKPHEnabled = true
        )


        getWeatherForecastUseCase.result = Result.Success(fakeWeatherResponse.toWeather)
        getWeatherConfigurationUseCase.config = mockConfig
        weatherUiDataMapper.mappedData = homeUiData

        // When
        viewModel.getLatestWeather()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.uiState.value is HomeUIState.Loaded)
        assertEquals(homeUiData, (viewModel.uiState.value as HomeUIState.Loaded).data)
    }

    @Test
    fun `get latest weather error case updates state to Error`() = runTest {
        // Given
        getWeatherForecastUseCase.result = Result.Error(DataError.Remote.SERVER)

        // When
        viewModel.getLatestWeather()
        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.uiState.value is HomeUIState.Error)
        assertEquals(
            DataError.Remote.SERVER.name,
            (viewModel.uiState.value as HomeUIState.Error).message
        )
    }
    @Test
    fun `search weather`() = runTest {
        //Given
        val mockConfig = WeatherConfiguration(
            isUnitTypeC = true,
            isKPHEnabled = true
        )


        getWeatherForecastUseCase.result = Result.Success(fakeWeatherResponse.toWeather)
        getWeatherConfigurationUseCase.config = mockConfig
        weatherUiDataMapper.mappedData = homeUiData

        // When

        viewModel.searchWeather("Karachi")

        testDispatcher.scheduler.advanceUntilIdle()

        // Then
        assertTrue(viewModel.uiState.value is HomeUIState.Loaded)
        assertEquals(homeUiData, (viewModel.uiState.value as HomeUIState.Loaded).data)
    }
}
