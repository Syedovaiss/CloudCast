package com.ovais.cloudcast

import androidx.compose.ui.window.ComposeUIViewController
import com.ovais.cloudcast.core.presentation.app.CloudCast

fun MainViewController() = ComposeUIViewController { CloudCast() }