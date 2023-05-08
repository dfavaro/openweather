package com.danielefavaro.openweather.core.data

import com.danielefavaro.openweather.core.data.domain.WeatherRepository
import com.danielefavaro.openweather.core.database.dao.WeatherModelDao
import com.danielefavaro.openweather.core.database.model.WeatherEntity
import com.danielefavaro.openweather.core.database.model.toBusinessModel
import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.model.WeatherModel
import com.danielefavaro.openweather.core.model.remote.WeatherRemoteModel
import com.danielefavaro.openweather.core.network.domain.WeatherDataSource
import javax.inject.Inject
import kotlinx.coroutines.flow.map

// TODO In real project, cities should be fetched from BE. For so WeatherListEnum.kt is just temporary.
class WeatherRepositoryImpl @Inject constructor(
    private val weatherModelDao: WeatherModelDao,
    private val weatherDataSource: WeatherDataSource
) : WeatherRepository {

    /**
     * Listen for changes on db
     *
     * @return the cities list business model [WeatherModel] flow without weather details
     */
    override suspend fun getCities() = weatherModelDao.getCities().map {
        it.map { entity ->
            entity.toBusinessModel()
        }
    }

    /**
     * Get cities from fake BE [WeatherRemoteModel] and cache them
     */
    override suspend fun updateCities() {
        cities.map {
            WeatherEntity(
                uid = it.id,
                name = it.cityName,
                lat = it.lat,
                lon = it.lon
            )
        }.also {
            weatherModelDao.insertCities(it)
        }
    }

    /**
     * On city selection we get weather info from BE
     *
     * @return the [WeatherModel] with weather details
     */
    override suspend fun onSelectedCity(id: Int): WeatherDetail {
        val city = weatherModelDao.getCity(id = id)

        return weatherDataSource.getWeatherByLatLon(
            lat = city.lat,
            lon = city.lon
        ).copy(
            cityName = city.name
        )
    }
}

private val cities = listOf(
    WeatherRemoteModel(
        id = 0,
        lon = 11.986500,
        lat = 57.696991,
        cityName = "Gothenburg"
    ),
    WeatherRemoteModel(
        id = 1,
        lon = 18.064489,
        lat = 59.332790,
        cityName = "Stockholm"
    ),
    WeatherRemoteModel(
        id = 2,
        lon = -122.119232,
        lat = 38.009838,
        cityName = "Mountain View"
    ),
    WeatherRemoteModel(
        id = 3,
        lon = -0.1015987,
        lat = 51.5286416,
        cityName = "London"
    ),
    WeatherRemoteModel(
        id = 4,
        lon = -74.0060152,
        lat = 40.7127281,
        cityName = "New York"
    ),
    WeatherRemoteModel(
        id = 5,
        lon = 13.3888599,
        lat = 52.5170365,
        cityName = "Berlin"
    )
)
