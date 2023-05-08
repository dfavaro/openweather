package com.danielefavaro.openweather.core.network.source;

import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.network.api.OpenWeatherService
import com.danielefavaro.openweather.core.network.data.weatherTestData
import com.danielefavaro.openweather.core.network.domain.WeatherDataSource
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class WeatherDataSourceTest {

    private lateinit var source: WeatherDataSource

    private val testDispatcher = StandardTestDispatcher()

    private val openWeatherService: OpenWeatherService = mockk {
        coEvery {
            getWeatherByLatLon(
                lat = any(),
                lon = any()
            )
        } returns weatherTestData
    }

    @Before
    fun setUp() {
        source = WeatherDataSourceImpl(
            dispatcher = testDispatcher,
            openWeatherService = openWeatherService
        )
    }

    @Test
    fun getWeatherByLatLon() = runTest(testDispatcher) {
        Assert.assertEquals(
            WeatherDetail(
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
            ),
            source.getWeatherByLatLon(lon = 100000.0, lat = 100000.0)
        )
    }
}
