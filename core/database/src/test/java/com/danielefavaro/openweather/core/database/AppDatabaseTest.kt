package com.danielefavaro.openweather.core.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.danielefavaro.openweather.core.database.dao.WeatherModelDao
import com.danielefavaro.openweather.core.database.model.WeatherEntity
import com.danielefavaro.openweather.core.model.remote.WeatherRemoteModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(RobolectricTestRunner::class)
class AppDatabaseTest {

    private lateinit var weatherModelDao: WeatherModelDao
    private lateinit var appDatabase: AppDatabase

    private val cityList = listOf(
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

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        appDatabase = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java,
        ).build()
        weatherModelDao = appDatabase.weatherModelDao()
    }

    @Test
    fun fetchCitiesAndCacheThem() = runTest {
        weatherModelDao.insertCities(
            items = cityList.map {
                WeatherEntity(
                    uid = it.id,
                    name = it.cityName,
                    lat = it.lat,
                    lon = it.lon
                )
            }
        )

        weatherModelDao.getCities().test {
            val list = awaitItem()

            Assert.assertEquals(
                list.size,
                cityList.size
            )

            Assert.assertEquals(
                list.first().name,
                cityList.first().cityName
            )

            Assert.assertEquals(
                list.last().name,
                cityList.last().cityName
            )
        }
    }

    @Test
    fun fetchCitiesAndReplaceCached() = runTest {
        val droppedItems = 1
        weatherModelDao.insertCities(
            items = cityList.toMutableList().drop(droppedItems).map {
                WeatherEntity(
                    uid = it.id,
                    name = it.cityName,
                    lat = it.lat,
                    lon = it.lon
                )
            }
        )

        weatherModelDao.getCities().test {
            val list = awaitItem()

            Assert.assertTrue(
                list.size == cityList.size - droppedItems
            )

            Assert.assertTrue(
                list.first().name != cityList.first().cityName
            )

            Assert.assertTrue(
                list.last().name == cityList.last().cityName
            )
        }
    }
}
