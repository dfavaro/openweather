package com.danielefavaro.openweather.core.navigation.route

import com.danielefavaro.openweather.core.navigation.BaseDestination
import com.danielefavaro.openweather.core.navigation.toRoute

private const val WEATHER_ROUTE = "weather"

object WeatherDestination : BaseDestination() {
    override val route: String = toRoute(WEATHER_ROUTE)
}
