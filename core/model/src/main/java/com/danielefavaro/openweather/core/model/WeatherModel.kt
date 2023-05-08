package com.danielefavaro.openweather.core.model

import com.google.gson.annotations.SerializedName

data class WeatherModel(
    val id: Int,
    val cityName: String,
    val lat: Double,
    val lon: Double
)

data class WeatherDetail(
    val cityName: String,
    val weather: List<Weather>,
    val visibility: Long,
    val wind: Wind,
    val main: Main
) {
    data class Main(
        val temp: Double,
        @SerializedName("feels_like")
        val feelsLike: Double,
        @SerializedName("temp_min")
        val tempMin: Double,
        @SerializedName("temp_max")
        val tempMax: Double,
        val humidity: Int
    )

    data class Weather(
        val id: Long,
        val main: String,
        val description: String,
        val icon: String
    )

    data class Wind(
        val speed: Double,
        val deg: Long,
        val gust: Double
    )
}
