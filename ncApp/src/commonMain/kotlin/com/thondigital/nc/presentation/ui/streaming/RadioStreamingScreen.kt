package com.thondigital.nc.presentation.ui.streaming

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.presentation.extensions.painterResourceFromPath
import com.thondigital.nc.presentation.ui.components.TopBar

object RadioStreamingScreen : Screen {
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<RadioStreamingScreenModel>()

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally
        ) {
            TopBar(showBackButton = true) { navigator.pop() }
            Image(
                modifier =
                    Modifier
                        .width(100.dp)
                        .height(100.dp),
                painter = painterResourceFromPath("images/boasnovas.jpeg"),
                contentDescription = "logo"
            )
            VideoPlayer(
                modifier = Modifier.fillMaxWidth().wrapContentWidth(),
                url = "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
            )
        }
    }
}
