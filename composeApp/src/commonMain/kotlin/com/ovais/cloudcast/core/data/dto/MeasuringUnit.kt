package com.ovais.cloudcast.core.data.dto

sealed interface MeasuringUnit {
    data object KPH : MeasuringUnit
    data object MPH : MeasuringUnit
}

val String.asMeasuringUnit: MeasuringUnit
    get() = if (this == "KPH") MeasuringUnit.KPH else MeasuringUnit.MPH