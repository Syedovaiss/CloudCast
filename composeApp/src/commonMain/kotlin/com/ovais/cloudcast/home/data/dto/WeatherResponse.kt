package com.ovais.cloudcast.home.data.dto

import com.ovais.cloudcast.home.domain.AirQuality
import com.ovais.cloudcast.home.domain.Condition
import com.ovais.cloudcast.home.domain.Current
import com.ovais.cloudcast.home.domain.Location
import com.ovais.cloudcast.home.domain.Weather
import kotlinx.serialization.Serializable

/**
{
"location": {
"name": "Karachi",
"region": "Sindh",
"country": "Pakistan",
"lat": 24.8667,
"lon": 67.05,
"tz_id": "Asia/Karachi",
"localtime_epoch": 1746196985,
"localtime": "2025-05-02 19:43"
},
"current": {
"last_updated_epoch": 1746196200,
"last_updated": "2025-05-02 19:30",
"temp_c": 30.2,
"temp_f": 86.4,
"is_day": 0,
"condition": {
"text": "Mist",
"icon": "//cdn.weatherapi.com/weather/64x64/night/143.png",
"code": 1030
},
"wind_mph": 17.0,
"wind_kph": 27.4,
"wind_degree": 270,
"wind_dir": "W",
"pressure_mb": 1004.0,
"pressure_in": 29.65,
"precip_mm": 0.0,
"precip_in": 0.0,
"humidity": 66,
"cloud": 25,
"feelslike_c": 36.7,
"feelslike_f": 98.1,
"windchill_c": 28.5,
"windchill_f": 83.2,
"heatindex_c": 32.6,
"heatindex_f": 90.7,
"dewpoint_c": 23.7,
"dewpoint_f": 74.7,
"vis_km": 5.0,
"vis_miles": 3.0,
"uv": 0.0,
"gust_mph": 23.0,
"gust_kph": 37.0,
"air_quality": {
"co": 592.0,
"no2": 7.03,
"o3": 103.0,
"so2": 19.055,
"pm2_5": 29.97,
"pm10": 54.39,
"us-epa-index": 2,
"gb-defra-index": 3
}
}
}

 * **/
@Serializable
data class WeatherResponse(
    val location: LocationResponse,
    val current: CurrentState
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
                )
            )
        }
}

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