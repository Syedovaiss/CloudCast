package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType
import com.ovais.cloudcast.fake.FakeSettingsManager
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetWeatherConfigurationUseCaseTest {

    private lateinit var settingsManager: FakeSettingsManager
    private lateinit var useCase: DefaultGetWeatherConfigurationUseCase

    @BeforeTest
    fun setup() {
        settingsManager = FakeSettingsManager()
        useCase = DefaultGetWeatherConfigurationUseCase(settingsManager)
    }

    @Test
    fun `when unit type is C and measuring unit is KPH returns correct configuration`() = runTest {
        // Given
        settingsManager.mockUnitType = UnitType.C
        settingsManager.mockMeasuringUnit = MeasuringUnit.KPH

        // When
        val result = useCase()

        // Then
        assertEquals(
            WeatherConfiguration(
                isUnitTypeC = true,
                isKPHEnabled = true
            ),
            result
        )
    }

    @Test
    fun `when unit type is F and measuring unit is MPH returns correct configuration`() = runTest {
        // Given
        settingsManager.mockUnitType = UnitType.F
        settingsManager.mockMeasuringUnit = MeasuringUnit.MPH

        // When
        val result = useCase()

        // Then
        assertEquals(
            WeatherConfiguration(
                isUnitTypeC = false,
                isKPHEnabled = false
            ),
            result
        )
    }

    @Test
    fun `when unit type is C and measuring unit is MPH returns correct configuration`() = runTest {
        // Given
        settingsManager.mockUnitType = UnitType.C
        settingsManager.mockMeasuringUnit = MeasuringUnit.MPH

        // When
        val result = useCase()

        // Then
        assertEquals(
            WeatherConfiguration(
                isUnitTypeC = true,
                isKPHEnabled = false
            ),
            result
        )
    }

    @Test
    fun `when unit type is F and measuring unit is KPH returns correct configuration`() = runTest {
        // Given
        settingsManager.mockUnitType = UnitType.F
        settingsManager.mockMeasuringUnit = MeasuringUnit.KPH

        // When
        val result = useCase()

        // Then
        assertEquals(
            WeatherConfiguration(
                isUnitTypeC = false,
                isKPHEnabled = true
            ),
            result
        )
    }
}