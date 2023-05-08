package com.danielefavaro.openweather.core.ui.component.toolbar

import androidx.compose.ui.graphics.vector.ImageVector

data class ToolbarActionModel(
    val icon: ImageVector,
    val caption: String,
    val onClick: () -> Unit
)
