package com.ovais.cloudcast

import androidx.compose.ui.window.ComposeUIViewController
import com.ovais.cloudcast.home.presentation.HomeScreen

fun MainViewController() = ComposeUIViewController { HomeScreen() }