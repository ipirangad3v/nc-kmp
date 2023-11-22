package com.thondigital.nc.presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.thondigital.nc.presentation.ui.components.Menu
import com.thondigital.nc.presentation.ui.components.TopBar


object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeScreenModel>()
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                TopBar()
            }
            item {
                Menu()
            }
        }
    }
}