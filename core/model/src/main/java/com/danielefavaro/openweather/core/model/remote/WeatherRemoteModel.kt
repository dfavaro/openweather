package com.danielefavaro.openweather.core.model.remote

data class WeatherRemoteModel(
    val id: Int,
    val cityName: String,
    val lon: Double,
    val lat: Double
)
