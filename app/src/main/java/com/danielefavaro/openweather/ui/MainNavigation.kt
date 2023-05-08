package com.danielefavaro.openweather.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.danielefavaro.openweather.core.navigation.route.MainDestination
import com.danielefavaro.openweather.feature.main.ui.MainScreen

@Composable
fun MainNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = MainDestination.route
    ) {
        composable(MainDestination.route) {
            MainScreen()
        }
    }
}
