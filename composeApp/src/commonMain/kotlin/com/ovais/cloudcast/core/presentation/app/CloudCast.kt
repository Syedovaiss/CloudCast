package com.ovais.cloudcast.core.presentation.app

import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ovais.cloudcast.home.presentation.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun CloudCast() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController,
            startDestination = Routes.Home.routeId
        ) {
            composable(
                Routes.Home.routeId,
                enterTransition = { slideInHorizontally() },
                exitTransition = { slideOutHorizontally() },
                popEnterTransition = { slideInHorizontally() },
                popExitTransition = { slideOutHorizontally() }
            ) {
                HomeScreen()
            }
        }
    }
}