package com.ovais.cloudcast.core.presentation.datetime

sealed class Month(val name: String) {
    data object January : Month("Jan")
    data object February : Month("Feb")
    data object March : Month("Mar")
    data object April : Month("Apr")
    data object May : Month("May")
    data object June : Month("Jun")
    data object July : Month("Jul")
    data object August : Month("Aug")
    data object September : Month("Sep")
    data object October : Month("Oct")
    data object November : Month("Nov")
    data object December : Month("Dec")
}