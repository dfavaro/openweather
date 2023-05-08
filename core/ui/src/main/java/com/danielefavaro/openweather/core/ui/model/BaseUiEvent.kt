package com.danielefavaro.openweather.core.ui.model

import androidx.annotation.StringRes

sealed class BaseUiEvent {
    sealed class ErrorEvent {
        data class OnError(val data: String) : ErrorEvent()
        data class OnErrorRes(@StringRes val data: Int) : ErrorEvent()
    }

    data class OnRefresh(val isRefreshing: Boolean)
}
