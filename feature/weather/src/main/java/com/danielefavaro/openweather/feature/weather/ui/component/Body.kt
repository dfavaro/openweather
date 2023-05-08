package com.danielefavaro.openweather.feature.weather.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.core.ui.component.NetworkImage
import com.danielefavaro.openweather.core.ui.defaultMargin
import com.danielefavaro.openweather.feature.weather.R
import com.danielefavaro.openweather.feature.weather.ui.weatherImageWidth

@Composable
internal fun Body(weather: WeatherDetail.Weather) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NetworkImage(
            imageUrl = stringResource(id = R.string.weather_icon_url, weather.icon),
            contentDescription = weather.description,
            weatherImageWidth = weatherImageWidth
        )
        Box(modifier = Modifier.padding(horizontal = defaultMargin)) {
            Text(
                text = weather.description,
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}
