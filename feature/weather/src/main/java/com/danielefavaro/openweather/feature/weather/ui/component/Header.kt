package com.danielefavaro.openweather.feature.weather.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.ui.MyApplicationTheme
import com.danielefavaro.openweather.core.ui.defaultMargin
import com.danielefavaro.openweather.core.ui.defaultMarginSmall
import com.danielefavaro.openweather.feature.weather.R

@Composable
internal fun Header(
    temp: WeatherDetail.Main,
    wind: WeatherDetail.Wind,
    @StringRes windDirectionLabel: Int
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(defaultMarginSmall),
            horizontalAlignment = Alignment.Start
        ) {
            Box(modifier = Modifier.padding(horizontal = defaultMargin)) {
                Text(
                    text = temp.temp.toString(),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Box(modifier = Modifier.padding(horizontal = defaultMargin)) {
                Text(
                    text = stringResource(
                        id = R.string.feels_like,
                        temp.feelsLike
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(defaultMarginSmall),
            horizontalAlignment = Alignment.End
        ) {
            Box(modifier = Modifier.padding(horizontal = defaultMargin)) {
                Text(
                    text = wind.speed.toString(),
                    style = MaterialTheme.typography.headlineLarge
                )
            }
            Box(modifier = Modifier.padding(horizontal = defaultMargin)) {
                Text(
                    text = stringResource(
                        id = R.string.wind_direction,
                        stringResource(id = windDirectionLabel)
                    ),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MyApplicationTheme {
        Header(
            temp = WeatherDetail.Main(
                feelsLike = 8.9,
                tempMin = 9.44,
                tempMax = 12.0,
                humidity = 54,
                temp = 10.34
            ),
            wind = WeatherDetail.Wind(
                speed = 7.0,
                deg = 260,
                gust = 10.0
            ),
            windDirectionLabel = R.string.north
        )
    }
}
