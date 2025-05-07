package com.ovais.cloudcast.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ovais.cloudcast.core.presentation.appBackground
import com.ovais.cloudcast.core.presentation.composables.BodyText

@Composable
fun HomeLoadingView() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(appBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.White
            )
            BodyText(
                text = "Updating....",
                modifier = Modifier.padding(
                    vertical = 16.dp
                )
            )
        }
    }
}