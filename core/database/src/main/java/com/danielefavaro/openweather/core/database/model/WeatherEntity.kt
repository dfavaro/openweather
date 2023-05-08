package com.danielefavaro.openweather.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.danielefavaro.openweather.core.model.WeatherModel

@Entity
data class WeatherEntity(
    @PrimaryKey var uid: Int,
    val name: String,
    val lon: Double,
    val lat: Double
)

fun WeatherEntity.toBusinessModel() = WeatherModel(
    id = uid,
    cityName = name,
    lat = lat,
    lon = lon
)
