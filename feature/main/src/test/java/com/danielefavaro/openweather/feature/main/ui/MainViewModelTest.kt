package com.danielefavaro.openweather.feature.main.ui

import app.cash.turbine.test
import com.danielefavaro.openweather.core.data.domain.WeatherRepository
import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.model.WeatherModel
import com.danielefavaro.openweather.core.testing.utils.MainDispatcherRule
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MainViewModel

    private val cityList = listOf(
        WeatherModel(
            id = 0,
            lon = 11.986500,
            lat = 57.696991,
            cityName = "Gothenburg"
        ),
        WeatherModel(
            id = 1,
            lon = 18.064489,
            lat = 59.332790,
            cityName = "Stockholm"
        ),
        WeatherModel(
            id = 2,
            lon = -122.119232,
            lat = 38.009838,
            cityName = "Mountain View"
        )
    )

    private val firstCityDetail = mockk<WeatherDetail> {
        every { cityName } returns cityList.first().cityName
    }
    private val secondCityDetail = mockk<WeatherDetail> {
        every { cityName } returns cityList[1].cityName
    }

    private val weatherRepository: WeatherRepository = mockk {
        coEvery { updateCities() } returns Unit
        coEvery { getCities() } returns flowOf(cityList)
        coEvery { onSelectedCity(id = 0) } returns firstCityDetail
        coEvery { onSelectedCity(id = 1) } returns secondCityDetail
    }

    @Before
    fun setup() {
        viewModel = MainViewModel(
            weatherRepository = weatherRepository
        )
    }

    @Test
    fun initialState() = runTest {
        Assert.assertFalse(
            viewModel.loadingState.value
        )

        // check drawer state
        viewModel.mainState.test {
            Assert.assertEquals(
                cityList.size,
                awaitItem()?.data?.size
            )
        }

        // check weather screen state
        viewModel.weatherState.test {
            val item = awaitItem()?.data
            Assert.assertEquals(
                firstCityDetail,
                item
            )
            Assert.assertEquals(
                cityList.first().cityName,
                item?.cityName
            )
        }

        // should not be loading anymore
        Assert.assertFalse(
            viewModel.loadingState.value
        )
    }

    @Test
    fun onSecondCitySelection() = runTest {
        // should not be loading
        Assert.assertFalse(
            viewModel.loadingState.value
        )

        // second city
        viewModel.onItemSelect(
            id = cityList[1].id
        )

        // check weather screen state
        viewModel.weatherState.test {
            val item = awaitItem()?.data
            Assert.assertEquals(
                secondCityDetail,
                item
            )
            Assert.assertEquals(
                cityList[1].cityName,
                item?.cityName
            )
        }

        // should not be loading anymore
        Assert.assertFalse(
            viewModel.loadingState.value
        )
    }
}
