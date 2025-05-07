package com.ovais.cloudcast.settings.data

sealed interface UpdateSettingsResult {
    data object Updated : UpdateSettingsResult
    data class Failure(val message: String) : UpdateSettingsResult
}