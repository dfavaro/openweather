package com.danielefavaro.openweather.feature.weather.ui

import androidx.lifecycle.ViewModel
import com.danielefavaro.openweather.core.model.WeatherDetail
import com.danielefavaro.openweather.feature.weather.R
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlin.math.floor
import kotlin.math.roundToInt

private const val ANGLE_TRIGGER = 45

@HiltViewModel
class WeatherViewModel @Inject constructor() : ViewModel() {

    /**
     * For wind and directions reference, check this out http://snowfence.umn.edu/Components/winddirectionanddegrees.htm
     */
    fun getWindDirectionLabel(wind: WeatherDetail.Wind): Int {
        val degreeLabelRef = floor((wind.deg / ANGLE_TRIGGER).toDouble()).roundToInt()
        val labels = listOf(
            R.string.north,
            R.string.north_east,
            R.string.east,
            R.string.south_east,
            R.string.south,
            R.string.south_west,
            R.string.west,
            R.string.north_west
        )
        return labels[degreeLabelRef % labels.size]
    }
}
