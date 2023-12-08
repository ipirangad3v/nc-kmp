package com.thondigital.nc.presentation.ui.external

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow

data class ExternalScreen(
    val externalCommand: () -> Unit
) : Screen {
    @Composable
    override fun Content() {
        externalCommand()
        LocalNavigator.currentOrThrow.pop()
    }
}
