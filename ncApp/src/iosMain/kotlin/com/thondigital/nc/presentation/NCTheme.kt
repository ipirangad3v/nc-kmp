package com.thondigital.nc.presentation

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.thondigital.nc.presentation.ui.theme.LightColorScheme

@Composable
actual fun NCTheme(
    darkTheme: Boolean,
    dynamicColor: Boolean,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colors = LightColorScheme,
        content = content
    )
}
