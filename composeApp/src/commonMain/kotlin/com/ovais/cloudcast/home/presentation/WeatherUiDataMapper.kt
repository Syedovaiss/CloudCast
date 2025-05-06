package com.ovais.cloudcast.home.presentation

import com.ovais.cloudcast.home.domain.ForecastDay
import com.ovais.cloudcast.home.domain.Weather
import com.ovais.cloudcast.utils.asImageUrl
import com.ovais.cloudcast.utils.orEmpty
import com.ovais.cloudcast.utils.orZero

fun interface WeatherUiDataMapper {
    fun map(data: Weather, isCEnabled: Boolean, isKPHEnabled: Boolean): HomeUiData
}

class DefaultWeatherUiDataMapper : WeatherUiDataMapper {
    override fun map(
        data: Weather,
        isCEnabled: Boolean,
        isKPHEnabled: Boolean
    ): HomeUiData {
        return HomeUiData(
            weatherType = data.current.condition.text,
            weatherIcon = data.current.condition.icon.asImageUrl,
            currentTemperature = if (isCEnabled) {
                data.current.temperatureInC.orZero.toString()
            } else data.current.temperatureInF.orZero.toString(),
            feelsLike = if (isCEnabled) {
                data.current.feelsLikeInC.orZero.toString()
            } else data.current.feelsLikeInF.orZero.toString(),
            windDirection = data.current.windDirection,
            humidity = data.current.humidity.toString(),
            dew = if(isCEnabled) {
                data.current.dewPointInC.toString()
            } else data.current.dewPointInF.toString(),
            wind = if (isKPHEnabled) {
                data.current.windInKPH.toString()
            } else data.current.windInMPH.toString(),
            locationName = "${data.location.name},${data.location.region}",
            forecast = getWeeklyForecast(data, isCEnabled, isKPHEnabled)
        )
    }

    private fun getWeeklyForecast(
        data: Weather,
        isCEnabled: Boolean,
        isKPHEnabled: Boolean
    ): List<WeeklyForecast> {
        return data.forecast.forecastDay.map { forecastDay ->
            WeeklyForecast(
                date = forecastDay.date,
                minTemperature = if (isCEnabled) {
                    forecastDay.day.minTemperatureInC.toString().orEmpty
                } else forecastDay.day.minTemperatureInF.toString().orEmpty,
                maxTemperature = if (isCEnabled) {
                    forecastDay.day.maxTemperatureInC.toString().orEmpty
                } else forecastDay.day.maxTemperatureInC.toString().orEmpty,
                averageTemperature = if (isCEnabled) {
                    forecastDay.day.averageTemperatureInC.toString().orEmpty
                } else forecastDay.day.averageTemperatureInC.toString().orEmpty,
                wind = if (isKPHEnabled) forecastDay.day.maxWindKPH.toString() else forecastDay.day.maxWindMPH.toString(),
                humidity = forecastDay.day.averageHumidity.toString().orEmpty,
                chanceOfRain = forecastDay.day.chanceOfRain != 0,
                chanceOfSnow = forecastDay.day.chanceOfSnow != 0,
                hour = getHourlyForecast(forecastDay, isCEnabled),
                weatherIcon = forecastDay.day.condition.icon.asImageUrl,
                weatherType = forecastDay.day.condition.text,
            )
        }
    }

    private fun getHourlyForecast(
        forecastDay: ForecastDay,
        isCEnabled: Boolean
    ): List<HourlyForecast> {
        return forecastDay.hour.map { forecastHour ->
            HourlyForecast(
                temperature = if (isCEnabled) {
                    forecastHour.temperatureInC.toString().orEmpty
                } else forecastHour.temperatureInF.toString().orEmpty,
                time = forecastHour.time.split(" ").get(1),
                weatherType = forecastHour.condition.text,
                weatherIcon = forecastHour.condition.icon.asImageUrl,
                humidity = forecastHour.humidity.toString(),
                feelsLike = if (isCEnabled) {
                    forecastHour.feelsLikeInC.toString().orEmpty
                } else forecastHour.feelsLikeInF.toString().orEmpty
            )
        }
    }
}