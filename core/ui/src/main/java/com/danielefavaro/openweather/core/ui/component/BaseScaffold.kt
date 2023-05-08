package com.danielefavaro.openweather.core.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.danielefavaro.openweather.core.ui.model.BaseUiEvent
import com.danielefavaro.openweather.core.ui.viewmodel.BaseViewModel
import kotlinx.coroutines.launch

/**
 * Base Scaffold that should be used as the root component for every screen that needs to handle
 * errors or loading states.
 *
 * @param viewModel the injected viewModel into the composable screen
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BaseScaffold(
    viewModel: BaseViewModel,
    onContentRefresh: (() -> Unit)? = null,
    topBar: @Composable () -> Unit = {},
    content: @Composable () -> Unit
) {
    val loadingState by viewModel.loadingState.collectAsState()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    val isRefreshing by produceState(initialValue = false, producer = {
        viewModel.refreshEvent.collect { event ->
            value = event.isRefreshing
        }
    })

    LaunchedEffect(key1 = Unit, block = {
        launch {
            viewModel.errorEvent.collect { event ->
                when (event) {
                    is BaseUiEvent.ErrorEvent.OnError -> snackbarHostState.showSnackbar(
                        message = event.data
                    )

                    is BaseUiEvent.ErrorEvent.OnErrorRes -> snackbarHostState.showSnackbar(
                        message = context.getString(event.data)
                    )
                }
            }
        }
    })

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = topBar
    ) {
        Box(modifier = Modifier.padding(it)) {
            onContentRefresh?.let { onRefresh ->
                PullRefreshPlaceholder(
                    isRefreshing = isRefreshing,
                    onRefresh = onRefresh,
                    content = {
                        InnerContent(
                            isLoading = loadingState,
                            content = content
                        )
                    }
                )
            } ?: InnerContent(
                isLoading = loadingState,
                content = content
            )
        }
    }
}

@Composable
private fun InnerContent(
    isLoading: Boolean,
    content: @Composable () -> Unit
) {
    Column {
        if (isLoading) {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }

        content.invoke()
    }
}
