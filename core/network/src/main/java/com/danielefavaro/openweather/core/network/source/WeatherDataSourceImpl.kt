package com.danielefavaro.openweather.core.network.source

import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.network.api.OpenWeatherService
import com.danielefavaro.openweather.core.network.domain.WeatherDataSource
import com.danielefavaro.openweather.core.network.helper.safeApiCall
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher

class WeatherDataSourceImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val openWeatherService: OpenWeatherService
) : WeatherDataSource {

    override suspend fun getWeatherByLatLon(lat: Double, lon: Double): WeatherDetail =
        safeApiCall(dispatcher = dispatcher) {
            openWeatherService.getWeatherByLatLon(
                lat = lat,
                lon = lon
            )
        }
}
