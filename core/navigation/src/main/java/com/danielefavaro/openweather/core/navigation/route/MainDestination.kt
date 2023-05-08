package com.danielefavaro.openweather.core.navigation.route

import com.danielefavaro.openweather.core.navigation.BaseDestination
import com.danielefavaro.openweather.core.navigation.toRoute

private const val MAIN_ROUTE = "main"

object MainDestination : BaseDestination() {
    override val route: String = toRoute(MAIN_ROUTE)
}
