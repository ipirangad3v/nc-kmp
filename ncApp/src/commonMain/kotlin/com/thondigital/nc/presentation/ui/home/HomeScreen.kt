package com.thondigital.nc.presentation.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.PullRefreshState
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.audioplayer.PlayerState
import com.thondigital.nc.data.remote.responses.HomeResponse
import com.thondigital.nc.presentation.ui.auth.signin.SignInScreen
import com.thondigital.nc.presentation.ui.components.EventsList
import com.thondigital.nc.presentation.ui.components.Loading
import com.thondigital.nc.presentation.ui.components.Menu
import com.thondigital.nc.presentation.ui.components.Player
import com.thondigital.nc.presentation.ui.components.TopBar
import com.thondigital.nc.presentation.ui.event.EventDetailsScreen
import com.thondigital.nc.presentation.ui.home.HomeScreenModel.State.Init
import com.thondigital.nc.presentation.ui.home.HomeScreenModel.State.Loading
import com.thondigital.nc.presentation.ui.home.HomeScreenModel.State.Result
import com.thondigital.nc.presentation.ui.theme.primaryBlue

class HomeScreen(
    private val playstate: PlayerState,
    private val onPause: () -> Unit,
    private val onPlay: () -> Unit
) : Screen {
    @OptIn(ExperimentalMaterialApi::class, ExperimentalVoyagerApi::class)
    @Composable
    override fun Content() {
        val isPlaying = playstate.isPlaying

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<HomeScreenModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(screenModel) {
            screenModel.getHome()
        }

        val pullRefreshState =
            rememberPullRefreshState(
                refreshing = screenModel.state.value is Loading,
                onRefresh = screenModel::getHome
            )

        when (state) {
            is Loading -> Loading()
            is Result ->
                HomeContent(
                    (state as Result).result,
                    pullRefreshState,
                    screenModel,
                    isPlaying
                )

            is Init -> screenModel.getHome()
        }
    }

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    private fun HomeContent(
        result: HomeResponse,
        pullRefreshState: PullRefreshState,
        screenModel: HomeScreenModel,
        isPlaying: Boolean
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Box(
            modifier = Modifier.fillMaxSize().pullRefresh(pullRefreshState)
        ) {
            LazyColumn {
                item {
                    TopBar()
                }
                if (result.isUserAuthenticated) {
                    item {
                        Text("Welcome")
                    }
                } else {
                    item {
                        LoginButton {
                            navigator.push(
                                SignInScreen()
                            )
                        }
                    }
                }
                item {
                    if (result.events.isNotEmpty()) {
                        EventsList(result.events) { event ->
                            navigator.push(
                                EventDetailsScreen(event.id) {
                                    navigator.pop()
                                }
                            )
                        }
                    }
                }
                item {
                    Player(
                        modifier = Modifier.fillMaxWidth(),
                        isPlaying = isPlaying,
                        onPause = onPause,
                        onPlay = onPlay
                    )
                }
                item {
                    Menu()
                }
            }
            PullRefreshIndicator(
                refreshing = screenModel.state.value is Loading,
                state = pullRefreshState,
                contentColor = primaryBlue,
                modifier =
                    Modifier.align(
                        Alignment.TopCenter
                    ),
                backgroundColor = Color.Transparent
            )
        }
    }

    @Composable
    private fun LoginButton(onClick: () -> Unit) {
        Button(onClick = onClick) {
            Text("Login")
        }
    }
}
