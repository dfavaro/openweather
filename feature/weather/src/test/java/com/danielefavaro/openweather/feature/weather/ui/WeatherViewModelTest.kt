package com.danielefavaro.openweather.feature.weather.ui

import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.feature.weather.R
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * For wind and directions reference, check this out http://snowfence.umn.edu/Components/winddirectionanddegrees.htm
 */
@OptIn(ExperimentalCoroutinesApi::class)
class WeatherViewModelTest {

    private lateinit var viewModel: WeatherViewModel

    @Before
    fun setup() {
        viewModel = WeatherViewModel()
    }

    @Test
    fun getWindDirectionLabelFromNorth() = runTest {
        val labelRes = viewModel.getWindDirectionLabel(
            wind = WeatherDetail.Wind(
                speed = 10.0,
                deg = 0L,
                gust = 12.0
            )
        )

        Assert.assertEquals(
            labelRes,
            R.string.north
        )
    }

    @Test
    fun getWindDirectionLabelFromEast() = runTest {
        val labelRes = viewModel.getWindDirectionLabel(
            wind = WeatherDetail.Wind(
                speed = 10.0,
                deg = 90L,
                gust = 12.0
            )
        )

        Assert.assertEquals(
            labelRes,
            R.string.east
        )
    }

    @Test
    fun getWindDirectionLabelFromSouth() = runTest {
        val labelRes = viewModel.getWindDirectionLabel(
            wind = WeatherDetail.Wind(
                speed = 10.0,
                deg = 180L,
                gust = 12.0
            )
        )

        Assert.assertEquals(
            labelRes,
            R.string.south
        )
    }

    @Test
    fun getWindDirectionLabelFromWest() = runTest {
        val labelRes = viewModel.getWindDirectionLabel(
            wind = WeatherDetail.Wind(
                speed = 10.0,
                deg = 270L,
                gust = 12.0
            )
        )

        Assert.assertEquals(
            labelRes,
            R.string.west
        )
    }

    @Test
    fun getWindDirectionLabelFromNorthAgain() = runTest {
        val labelRes = viewModel.getWindDirectionLabel(
            wind = WeatherDetail.Wind(
                speed = 10.0,
                deg = 360L,
                gust = 12.0
            )
        )

        Assert.assertEquals(
            labelRes,
            R.string.north
        )
    }
}
