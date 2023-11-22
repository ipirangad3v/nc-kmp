package com.thondigital.nc.presentation.ui.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.domain.models.HomeResponse
import com.thondigital.nc.presentation.ui.components.Loading
import com.thondigital.nc.presentation.ui.components.Menu
import com.thondigital.nc.presentation.ui.components.TopBar
import com.thondigital.nc.presentation.ui.components.EventsList
import com.thondigital.nc.presentation.ui.event.EventScreen
import com.thondigital.nc.presentation.ui.home.HomeScreenModel.State.Init
import com.thondigital.nc.presentation.ui.home.HomeScreenModel.State.Loading
import com.thondigital.nc.presentation.ui.home.HomeScreenModel.State.Result


object HomeScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()

        when (state) {
            is Loading -> Loading()
            is Result  -> HomeContent((state as Result).result)
            is Init    -> screenModel.getHome()

        }
    }

    @Composable
    private fun HomeContent(result: HomeResponse) {
        val navigator = LocalNavigator.currentOrThrow
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                TopBar()
            }
            item {
                EventsList(result.events) { event ->
                    navigator.push(EventScreen(event.id))
                }

            }
            item {
                Menu()
            }
        }


    }
}