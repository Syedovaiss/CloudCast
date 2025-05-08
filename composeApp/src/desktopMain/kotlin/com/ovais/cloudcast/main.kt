package com.ovais.cloudcast

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import com.ovais.cloudcast.core.presentation.app.CloudCast
import com.ovais.cloudcast.di.initKoin

private const val DESKTOP_APP_TITLE = "Cloud Cast"

fun main() {
    initKoin()
    application {
        Window(
            onCloseRequest = ::exitApplication,
            title = DESKTOP_APP_TITLE,
        ) {
            CloudCast()
        }
    }
}