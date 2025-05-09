package com.ovais.cloudcast.fake

import com.ovais.cloudcast.home.data.dto.CurrentAirQuality
import com.ovais.cloudcast.home.data.dto.CurrentCondition
import com.ovais.cloudcast.home.data.dto.CurrentState
import com.ovais.cloudcast.home.data.dto.DayResponse
import com.ovais.cloudcast.home.data.dto.ForecastDayResponse
import com.ovais.cloudcast.home.data.dto.ForecastResponse
import com.ovais.cloudcast.home.data.dto.HourResponse
import com.ovais.cloudcast.home.data.dto.LocationResponse
import com.ovais.cloudcast.home.data.dto.WeatherResponse


val fakeWeatherResponse = WeatherResponse(
    location = LocationResponse(
        name = "Karachi",
        region = "Sindh",
        country = "Pakistan",
        lat = 22.23,
        lon = 22.22
    ),
    current = CurrentState(
        temp_c = 1f,
        temp_f = 1f,
        condition = CurrentCondition(
            text = "Windy",
            icon = ""
        ),
        wind_dir = "NW",
        wind_kph = 1f,
        wind_mph = 1f,
        wind_degree = 90,
        windchill_c = 1f,
        windchill_f = 1f,
        dewpoint_c = 1f,
        dewpoint_f = 1f,
        humidity = 12,
        cloud = 11,
        feelslike_c = 1f,
        feelslike_f = 1f,
        heatindex_c = 1f,
        heatindex_f = 1f,
        air_quality = CurrentAirQuality(
            1f,
            1f,
            1f,
            1f,
            1f
        ),
    ),
    forecast = ForecastResponse(
        listOf(
            ForecastDayResponse(
                date = "12-12-12",
                day = DayResponse(
                    1f,
                    1f,
                    1f,
                    1f,
                    1f,
                    1f,
                    1f,
                    1f,
                    1f,
                    1,
                    1,
                    condition = CurrentCondition(
                        text = "Windy",
                        icon = ""
                    )
                ),
                hour = listOf(
                    HourResponse(
                        "12:00",
                        1f,
                        1f,
                        CurrentCondition(
                            text = "Windy",
                            icon = ""
                        ),
                        1f,
                        1f,
                        1,
                        "",
                        1f,
                        1f,
                        1f
                    )
                )
            )
        )
    )
)