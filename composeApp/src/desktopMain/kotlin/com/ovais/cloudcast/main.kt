package com.ovais.cloudcast

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ovais.cloudcast.home.presentation.HomeScreen

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CloudCast",
    ) {
        HomeScreen()
    }
}