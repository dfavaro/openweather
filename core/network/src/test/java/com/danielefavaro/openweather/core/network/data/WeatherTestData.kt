package com.danielefavaro.openweather.core.network.data

import com.danielefavaro.openweather.core.model.WeatherDetail

val weatherTestData: WeatherDetail = WeatherDetail(
    cityName = "Stockholm",
    main = WeatherDetail.Main(
        temp = 10.0,
        feelsLike = 7.0,
        tempMin = 5.0,
        tempMax = 12.0,
        humidity = 40
    ),
    visibility = 1000,
    wind = WeatherDetail.Wind(
        speed = 10.0,
        deg = 180L,
        gust = 12.0
    ),
    weather = listOf(
        WeatherDetail.Weather(
            id = 501,
            main = "Rain",
            description = "moderate rain",
            icon = "10d"
        )
    )
)
