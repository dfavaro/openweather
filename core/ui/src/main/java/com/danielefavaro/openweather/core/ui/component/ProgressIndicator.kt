package com.danielefavaro.openweather.core.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ProgressIndicator() {
    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
}
