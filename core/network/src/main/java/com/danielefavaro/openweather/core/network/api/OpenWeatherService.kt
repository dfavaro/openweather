package com.danielefavaro.openweather.core.network.api

import com.danielefavaro.openweather.core.model.WeatherDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherService {
    @GET("weather")
    suspend fun getWeatherByLatLon(
        @Query("lat") lat: Double,
        @Query("lon") lon: Double,
        @Query("units") units: String = "metric"
    ): WeatherDetail
}
