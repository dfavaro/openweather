package com.danielefavaro.openweather.core.ui.component.toolbar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(
    title: String,
    navActionModel: ToolbarActionModel
) {
    TopAppBar(
        title = {
            Text(text = title)
        },
        navigationIcon = {
            IconButton(
                onClick = navActionModel.onClick
            ) {
                Icon(
                    imageVector = navActionModel.icon,
                    contentDescription = navActionModel.caption
                )
            }
        }
    )
}
