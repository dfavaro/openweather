package com.danielefavaro.openweather.feature.main.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.danielefavaro.openweather.core.ui.R
import com.danielefavaro.openweather.core.ui.component.BaseScaffold
import com.danielefavaro.openweather.core.ui.component.drawerscaffold.DrawerScaffold
import com.danielefavaro.openweather.core.ui.component.toolbar.ToolBar
import com.danielefavaro.openweather.core.ui.component.toolbar.ToolbarActionModel
import com.danielefavaro.openweather.feature.weather.ui.WeatherScreen
import com.danielefavaro.openweather.feature.weather.ui.model.WeatherUiState

@Composable
fun MainScreen(viewModel: MainViewModel = hiltViewModel()) {
    val drawerState by viewModel.mainState.collectAsState()
    val weatherState by viewModel.weatherState.collectAsState()

    var selectedItemId by rememberSaveable(drawerState) {
        mutableStateOf(
            drawerState?.data?.firstOrNull()?.id
        )
    }

    DrawerScaffold(
        selectedItemId = selectedItemId,
        drawerItems = drawerState?.data ?: emptyList(),
        onItemSelect = { id ->
            selectedItemId = id
            viewModel.onItemSelect(id = id)
        }
    ) { onDrawerTrigger ->
        BaseScaffold(
            viewModel = viewModel,
            onContentRefresh = {
                selectedItemId?.let { id ->
                    viewModel.onItemSelect(
                        isRefreshing = true,
                        id = id
                    )
                }
            },
            topBar = {
                ToolBar(
                    title = weatherState?.data?.cityName.orEmpty(),
                    navActionModel = ToolbarActionModel(
                        icon = Icons.Default.Menu,
                        caption = stringResource(id = R.string.toolbar_drawer_icon_caption),
                        onClick = onDrawerTrigger
                    )
                )
            },
        ) {
            when (val state = weatherState) {
                is WeatherUiState -> WeatherScreen(
                    weatherDetail = state.data
                )

                else -> Unit
            }
        }
    }
}

