package com.ovais.cloudcast.core.presentation.app

sealed class Routes(val routeId: String) {

    data object Home : Routes("home")
    data object Settings : Routes("settings")
}