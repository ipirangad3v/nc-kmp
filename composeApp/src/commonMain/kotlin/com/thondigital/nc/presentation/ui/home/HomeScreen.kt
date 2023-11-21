package com.thondigital.nc.presentation.ui.home

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.thondigital.nc.presentation.ui.components.Menu


object HomeScreen : Screen {
    @Composable
    override fun Content() {
//        val screenModel = getScreenModel<HomeScreenModel>()
        Menu()
    }
}