package com.ovais.cloudcast.home.data.dto

import com.ovais.cloudcast.home.domain.AirQuality
import com.ovais.cloudcast.home.domain.Condition
import com.ovais.cloudcast.home.domain.Current
import com.ovais.cloudcast.home.domain.Day
import com.ovais.cloudcast.home.domain.Forecast
import com.ovais.cloudcast.home.domain.ForecastDay
import com.ovais.cloudcast.home.domain.Hour
import com.ovais.cloudcast.home.domain.Location
import com.ovais.cloudcast.home.domain.Weather
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    val location: LocationResponse,
    val current: CurrentState,
    val forecast: ForecastResponse
) {
    val toWeather: Weather
        get() {
            return Weather(
                location = Location(
                    name = this.location.name,
                    region = this.location.region,
                    country = this.location.country,
                    latitude = this.location.lat,
                    longitude = this.location.lon
                ),
                current = Current(
                    temperatureInC = this.current.temp_c,
                    temperatureInF = this.current.temp_f,
                    condition = Condition(
                        text = this.current.condition.text,
                        icon = this.current.condition.icon
                    ),
                    windDirection = this.current.wind_dir,
                    windDegree = this.current.wind_degree,
                    windInKPH = this.current.wind_kph,
                    windInMPH = this.current.wind_mph,
                    windChillInC = this.current.windchill_c,
                    windChillInF = this.current.windchill_f,
                    humidity = this.current.humidity,
                    feelsLikeInC = this.current.feelslike_c,
                    feelsLikeInF = this.current.feelslike_f,
                    cloud = this.current.cloud,
                    heatIndexInC = this.current.heatindex_c,
                    heatIndexInF = this.current.heatindex_f,
                    dewPointInC = this.current.dewpoint_c,
                    dewPointInF = this.current.dewpoint_f,
                    airQuality = AirQuality(
                        co = this.current.air_quality?.co,
                        no2 = this.current.air_quality?.no2,
                        o3 = this.current.air_quality?.o3,
                        so2 = this.current.air_quality?.so2,
                        pm10 = this.current.air_quality?.pm10,
                        pm25 = this.current.air_quality?.pm2_5
                    )
                ),
                forecast = Forecast(
                    forecastDay = this.forecast.forecastday.map {
                        ForecastDay(
                            date = it.date,
                            day = Day(
                                maxTemperatureInC = it.day.maxtemp_c,
                                maxTemperatureInF = it.day.maxtemp_f,
                                minTemperatureInC = it.day.mintemp_c,
                                minTemperatureInF = it.day.mintemp_f,
                                averageTemperatureInC = it.day.avgtemp_c,
                                averageHumidity = it.day.avgtemp_f,
                                maxWindKPH = it.day.maxwind_kph,
                                maxWindMPH = it.day.maxwind_mph,
                                averageTemperatureInF = it.day.avgtemp_f,
                                chanceOfRain = it.day.daily_chance_of_rain,
                                chanceOfSnow = it.day.daily_chance_of_snow,
                                condition = it.day.condition
                            ),
                            hour = it.hour.map { hour ->
                                Hour(
                                    time = hour.time,
                                    temperatureInC = hour.temp_c,
                                    temperatureInF = hour.temp_f,
                                    condition = hour.condition,
                                    windDirection = hour.wind_dir,
                                    windDegree = hour.wind_degree,
                                    windKPH = hour.wind_kph,
                                    windMPH = hour.wind_mph,
                                    feelsLikeInF = hour.feelslike_f,
                                    feelsLikeInC = hour.feelslike_c,
                                    humidity = hour.humidity
                                )
                            }
                        )
                    }
                )
            )
        }
}

@Serializable
data class ForecastResponse(
    val forecastday: List<ForecastDayResponse> = emptyList()
)

@Serializable
data class ForecastDayResponse(
    val date: String,
    val day: DayResponse,
    val hour: List<HourResponse>
)

@Serializable
data class HourResponse(
    val time: String,
    val temp_c: Float,
    val temp_f: Float,
    val condition: CurrentCondition,
    val wind_mph: Float,
    val wind_kph: Float,
    val wind_degree: Int,
    val wind_dir: String,
    val humidity: Float,
    val feelslike_c: Float,
    val feelslike_f: Float,
)

@Serializable
data class DayResponse(
    val maxtemp_c: Float,
    val maxtemp_f: Float,
    val mintemp_c: Float,
    val mintemp_f: Float,
    val avgtemp_c: Float,
    val avgtemp_f: Float,
    val maxwind_mph: Float,
    val maxwind_kph: Float,
    val avghumidity: Float,
    val daily_chance_of_rain: Int,
    val daily_chance_of_snow: Int,
    val condition: CurrentCondition

)

@Serializable
data class LocationResponse(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double
)

@Serializable
data class CurrentState(
    val temp_c: Float,
    val temp_f: Float,
    val condition: CurrentCondition,
    val wind_mph: Float,
    val wind_kph: Float,
    val wind_degree: Int,
    val wind_dir: String,
    val humidity: Int,
    val cloud: Int,
    val feelslike_c: Float,
    val feelslike_f: Float,
    val windchill_c: Float,
    val windchill_f: Float,
    val heatindex_c: Float,
    val heatindex_f: Float,
    val dewpoint_c: Float,
    val dewpoint_f: Float,
    val air_quality: CurrentAirQuality? = null
)

@Serializable
data class CurrentCondition(
    val text: String,
    val icon: String
)

@Serializable
data class CurrentAirQuality(
    val co: Float? = null,
    val no2: Float? = null,
    val o3: Float? = null,
    val so2: Float? = null,
    val pm2_5: Float? = null,
    val pm10: Float? = null
)