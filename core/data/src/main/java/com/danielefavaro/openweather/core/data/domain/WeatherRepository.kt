package com.danielefavaro.openweather.core.data.domain

import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.model.WeatherModel
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {
    suspend fun getCities(): Flow<List<WeatherModel>>
    suspend fun updateCities()
    suspend fun onSelectedCity(id: Int): WeatherDetail
}
