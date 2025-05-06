package com.ovais.cloudcast.core.presentation

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val primary = Color(0xFF3F7CD7)
private val appBackgroundOne = Color(0xFf08244F)
private val appBackgroundTwo = Color(0xFf134CB5)
val primaryText = Color.White

val appBackground = Brush.linearGradient(
    colors = listOf(appBackgroundOne, appBackgroundTwo),
)