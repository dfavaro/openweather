package com.danielefavaro.openweather.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danielefavaro.openweather.core.ui.model.BaseUiEvent
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val _loadingState: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val loadingState = _loadingState.asStateFlow()

    private val _refreshEvent: MutableSharedFlow<BaseUiEvent.OnRefresh> = MutableSharedFlow()
    val refreshEvent = _refreshEvent.asSharedFlow()

    // Can even create a custom model for handling different data, such as a Retry callback op
    private val _errorEvent: MutableSharedFlow<BaseUiEvent.ErrorEvent> = MutableSharedFlow()
    val errorEvent = _errorEvent.asSharedFlow()

    fun onLoadingState(isLoading: Boolean) {
        _loadingState.update {
            isLoading
        }
    }

    fun onErrorEvent(data: BaseUiEvent.ErrorEvent) {
        viewModelScope.launch {
            _errorEvent.emit(data)
        }
    }

    fun onRefreshEvent(isRefreshing: Boolean) {
        viewModelScope.launch {
            _refreshEvent.emit(BaseUiEvent.OnRefresh(isRefreshing = isRefreshing))
        }
    }
}
