package com.danielefavaro.openweather.core.network.domain

import com.danielefavaro.openweather.core.model.WeatherDetail

interface WeatherDataSource {
    suspend fun getWeatherByLatLon(
        lat: Double,
        lon: Double
    ): WeatherDetail
}
