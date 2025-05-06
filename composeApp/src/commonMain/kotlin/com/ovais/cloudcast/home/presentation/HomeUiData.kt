package com.ovais.cloudcast.home.presentation

data class HomeUiData(
    val weatherType: String,
    val weatherIcon: String,
    val currentTemperature: String,
    val wind: String,
    val windDirection: String
)