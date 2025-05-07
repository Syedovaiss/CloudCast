package com.ovais.cloudcast.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.ovais.cloudcast.core.presentation.appBackground
import com.ovais.cloudcast.core.presentation.composables.BodyText


@Composable
fun HomeErrorView(message: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackground),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BodyText(message)
    }
}