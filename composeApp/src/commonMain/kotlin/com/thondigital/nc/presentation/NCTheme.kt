package com.thondigital.nc.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun NCTheme(
    darkTheme: Boolean = false,
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit,
)
