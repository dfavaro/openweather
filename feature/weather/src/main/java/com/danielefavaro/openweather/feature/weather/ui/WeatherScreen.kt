package com.danielefavaro.openweather.feature.weather.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.ui.MyApplicationTheme
import com.danielefavaro.openweather.core.ui.defaultMargin
import com.danielefavaro.openweather.feature.weather.ui.component.Body
import com.danielefavaro.openweather.feature.weather.ui.component.Header

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherScreen(
    weatherDetail: WeatherDetail,
    viewModel: WeatherViewModel = hiltViewModel()
) {
    Scaffold {
        Column(
            Modifier
                .padding(it)
                .padding(vertical = defaultMargin)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(defaultMargin)
        ) {
            Header(
                temp = weatherDetail.main,
                wind = weatherDetail.wind,
                windDirectionLabel = viewModel.getWindDirectionLabel(
                    wind = weatherDetail.wind
                )
            )
            Box(
                Modifier.padding(horizontal = defaultMargin)
            ) {
                Text(text = "Template for much more data here...")
            }
            Body(weather = weatherDetail.weather.first())
        }
    }
}

@Preview
@Composable
private fun DefaultPreview() {
    MyApplicationTheme {
        WeatherScreen(
            weatherDetail = WeatherDetail(
                cityName = "Stockholm",
                weather = listOf(
                    WeatherDetail.Weather(
                        id = 800,
                        main = "Clear",
                        description = "clear sky",
                        icon = "01d"
                    )
                ),
                visibility = 10000,
                wind = WeatherDetail.Wind(
                    speed = 7.0,
                    deg = 260,
                    gust = 10.0
                ),
                main = WeatherDetail.Main(
                    feelsLike = 8.9,
                    tempMin = 9.44,
                    tempMax = 12.0,
                    humidity = 54,
                    temp = 10.34
                )
            )
        )
    }
}

@Preview(widthDp = 480)
@Composable
private fun Preview() {
    MyApplicationTheme {
        WeatherScreen(
            weatherDetail = WeatherDetail(
                cityName = "Stockholm",
                weather = listOf(
                    WeatherDetail.Weather(
                        id = 800,
                        main = "Clear",
                        description = "clear sky",
                        icon = "01d"
                    )
                ),
                visibility = 10000,
                wind = WeatherDetail.Wind(
                    speed = 7.0,
                    deg = 260,
                    gust = 10.0
                ),
                main = WeatherDetail.Main(
                    feelsLike = 8.9,
                    tempMin = 9.44,
                    tempMax = 12.0,
                    humidity = 54,
                    temp = 10.34
                )
            )
        )
    }
}
