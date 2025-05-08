package com.ovais.cloudcast.core.data.dto

sealed interface UnitType {
    data object C : UnitType
    data object F : UnitType
}

private const val C = "C"
val String.asUnitType: UnitType
    get() = if (this == C) UnitType.C else UnitType.F