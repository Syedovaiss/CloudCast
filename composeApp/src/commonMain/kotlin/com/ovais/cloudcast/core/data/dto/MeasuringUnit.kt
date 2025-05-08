package com.ovais.cloudcast.core.data.dto

sealed interface MeasuringUnit {
    data object KPH : MeasuringUnit
    data object MPH : MeasuringUnit
}

private const val KPH = "KPH"
val String.asMeasuringUnit: MeasuringUnit
    get() = if (this == KPH) MeasuringUnit.KPH else MeasuringUnit.MPH