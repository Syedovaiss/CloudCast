package com.ovais.cloudcast

import androidx.compose.ui.window.ComposeUIViewController
import com.ovais.cloudcast.core.presentation.app.CloudCast
import com.ovais.cloudcast.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { CloudCast() }