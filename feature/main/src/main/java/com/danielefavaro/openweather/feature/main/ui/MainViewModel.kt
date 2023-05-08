package com.danielefavaro.openweather.feature.main.ui

import androidx.lifecycle.viewModelScope
import com.danielefavaro.openweather.core.data.domain.WeatherRepository
import com.danielefavaro.openweather.core.ui.component.drawerscaffold.DrawerItemModel
import com.danielefavaro.openweather.core.ui.model.BaseUiEvent
import com.danielefavaro.openweather.core.ui.viewmodel.BaseViewModel
import com.danielefavaro.openweather.feature.main.R
import com.danielefavaro.openweather.feature.main.ui.model.MainUiState
import com.danielefavaro.openweather.feature.weather.ui.model.WeatherUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val weatherRepository: WeatherRepository
) : BaseViewModel() {

    private val _mainState: MutableStateFlow<MainUiState?> = MutableStateFlow(null)
    val mainState = _mainState.asStateFlow()

    private val _weatherState: MutableStateFlow<WeatherUiState?> = MutableStateFlow(null)
    val weatherState = _weatherState.asStateFlow()

    /**
     * This is going to happen only at the beginning. Meaning we can trigger a loading state and
     * let the next operations to disable it.
     */
    init {
        onLoadingState(isLoading = true)

        viewModelScope.launch {
            weatherRepository.updateCities()

            try {
                weatherRepository.getCities().collect { weatherModelList ->
                    _mainState.update {
                        MainUiState(
                            data = weatherModelList.map {
                                DrawerItemModel(
                                    id = it.id,
                                    title = it.cityName
                                )
                            }
                        )
                    }
                    onItemSelect(id = weatherModelList.first().id)
                }
            } catch (_: Exception) {
                // TODO check for different errors
                onErrorEvent(
                    BaseUiEvent.ErrorEvent.OnErrorRes(R.string.generic_error)
                )
            }
        }
    }

    fun onItemSelect(id: Int, isRefreshing: Boolean = false) {
        viewModelScope.launch {
            if (isRefreshing) {
                onRefreshEvent(isRefreshing = true)
            } else {
                onLoadingState(isLoading = true)
            }

            try {
                val weatherDetail = weatherRepository.onSelectedCity(id = id)

                _weatherState.update {
                    WeatherUiState(
                        data = weatherDetail
                    )
                }
                if (isRefreshing) {
                    onRefreshEvent(isRefreshing = false)
                }
                onLoadingState(isLoading = false)
            } catch (_: Exception) {
                // TODO check for different errors
                onErrorEvent(
                    BaseUiEvent.ErrorEvent.OnErrorRes(R.string.generic_error)
                )
            }
        }
    }
}
