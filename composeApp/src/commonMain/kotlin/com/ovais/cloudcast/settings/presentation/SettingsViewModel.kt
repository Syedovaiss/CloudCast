package com.ovais.cloudcast.settings.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ovais.cloudcast.core.data.dto.MeasuringUnit
import com.ovais.cloudcast.core.data.dto.UnitType
import com.ovais.cloudcast.core.data.dto.asMeasuringUnit
import com.ovais.cloudcast.core.data.dto.asUnitType
import com.ovais.cloudcast.settings.domain.GetCurrentSettingsUseCase
import com.ovais.cloudcast.settings.domain.SettingType
import com.ovais.cloudcast.settings.domain.UpdateSettingsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val updateSettingsUseCase: UpdateSettingsUseCase,
    private val currentSettingsUseCase: GetCurrentSettingsUseCase
) : ViewModel() {

    private val _currentSettings by lazy { MutableStateFlow(SettingsUIData.default) }
    val currentSettings: StateFlow<SettingsUIData>
        get() = _currentSettings.asStateFlow()

    init {
        initCurrentSettings()
    }

    private fun initCurrentSettings() {
        viewModelScope.launch {
            val settings = currentSettingsUseCase()
            _currentSettings.update {
                SettingsUIData(
                    isFEnabled = settings.unitType.asUnitType == UnitType.F,
                    isKPHEnabled = settings.measuringUnit.asMeasuringUnit == MeasuringUnit.KPH,
                    hasAQIEnabled = settings.hasAQIEnabled
                )
            }
        }
    }

    fun updateTemperature(isF: Boolean) {
        updateSetting(
            SettingType.TemperatureSetting(
                if (isF) UnitType.F else UnitType.C
            )
        )
    }

    fun updateAQI(enabled: Boolean) {
        updateSetting(
            SettingType.AQISettings(enabled)
        )
    }

    fun updateMeasuringUnit(isKPH: Boolean) {
        updateSetting(
            SettingType.MeasuringUnitSetting(
                if (isKPH) MeasuringUnit.KPH else MeasuringUnit.MPH
            )
        )
    }


    private fun updateSetting(
        settingType: SettingType
    ) {
        viewModelScope.launch {
            updateSettingsUseCase(settingType)
        }
        initCurrentSettings()
    }

}