package com.ovais.cloudcast.home.presentation

data class HomeUiData(
    val weatherType: String,
    val weatherIcon: String,
    val currentTemperature: String,
    val feelsLike:String,
    val wind: String,
    val dew:String,
    val humidity: String,
    val windDirection: String,
    val locationName: String,
    val forecast: List<WeeklyForecast>,
)

data class WeeklyForecast(
    val date: String,
    val minTemperature: String,
    val maxTemperature: String,
    val averageTemperature: String,
    val wind: String,
    val humidity: String,
    val chanceOfRain: Boolean,
    val chanceOfSnow: Boolean,
    val weatherType: String,
    val weatherIcon: String,
    val hour: List<HourlyForecast>
)

data class HourlyForecast(
    val temperature:String,
    val time:String,
    val weatherType:String,
    val weatherIcon:String,
    val humidity: String,
    val feelsLike:String
)