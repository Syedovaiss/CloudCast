package com.ovais.cloudcast

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ovais.cloudcast.core.presentation.app.CloudCast

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "CloudCast",
    ) {
        CloudCast()
    }
}