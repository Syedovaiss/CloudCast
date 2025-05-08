package com.ovais.cloudcast.home.domain

import com.ovais.cloudcast.home.data.dto.CurrentCondition

data class Weather(
    val location: Location,
    val current: Current,
    val forecast: Forecast
)

data class Forecast(
    val forecastDay: List<ForecastDay> = emptyList()

)

data class ForecastDay(
    val date: String,
    val day: Day,
    val hour: List<Hour>
)

data class Day(
    val maxTemperatureInC: Float,
    val maxTemperatureInF: Float,
    val minTemperatureInC: Float,
    val minTemperatureInF: Float,
    val averageTemperatureInC: Float,
    val averageTemperatureInF: Float,
    val maxWindMPH: Float,
    val maxWindKPH: Float,
    val averageHumidity: Float,
    val chanceOfRain: Int,
    val chanceOfSnow: Int,
    val condition: CurrentCondition
)

data class Hour(
    val time: String,
    val temperatureInC: Float,
    val temperatureInF: Float,
    val condition: CurrentCondition,
    val windMPH: Float,
    val windKPH: Float,
    val windDegree: Int,
    val windDirection: String,
    val humidity: Float,
    val feelsLikeInC: Float,
    val feelsLikeInF: Float,
)

data class Location(
    val name: String,
    val region: String,
    val country: String,
    val latitude: Double,
    val longitude: Double
)

data class Current(
    val temperatureInC: Float,
    val temperatureInF: Float,
    val condition: Condition,
    val windInMPH: Float,
    val windInKPH: Float,
    val windDegree: Int,
    val windDirection: String,
    val humidity: Int,
    val cloud: Int,
    val feelsLikeInC: Float,
    val feelsLikeInF: Float,
    val windChillInC: Float,
    val windChillInF: Float,
    val heatIndexInC: Float,
    val heatIndexInF: Float,
    val dewPointInC: Float,
    val dewPointInF: Float,
    val airQuality: AirQuality?
)

data class Condition(
    val text: String,
    val icon: String
)

data class AirQuality(
    val co: Float?,
    val no2: Float?,
    val o3: Float?,
    val so2: Float?,
    val pm25: Float?,
    val pm10: Float?
)