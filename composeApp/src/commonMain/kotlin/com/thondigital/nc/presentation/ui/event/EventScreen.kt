package com.thondigital.nc.presentation.ui.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.seiko.imageloader.rememberImagePainter
import com.thondigital.nc.domain.models.EventDetailsResponse
import com.thondigital.nc.presentation.ui.components.Loading
import com.thondigital.nc.presentation.ui.components.TopBar

class EventScreen(private val eventId: Long, private val onBackClick: () -> Unit) : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<EventScreenModel>()
        val state by screenModel.state.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is EventScreenModel.State.Loading -> Loading()
                is EventScreenModel.State.Result  -> EventScreenContent((state as EventScreenModel.State.Result).result)
                is EventScreenModel.State.Init    -> screenModel.getEventInfo(eventId)

            }
        }
    }

    @Composable
    private fun EventScreenContent(result: EventDetailsResponse) {
        val painter =
            rememberImagePainter(
                result.image,
                contentScale = ContentScale.Crop,
            )
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item { TopBar(showBackButton = true) { onBackClick() } }
            item {
                Image(
                    modifier = Modifier.fillMaxWidth(),
                    painter = painter,
                    contentDescription = "image",
                )
            }
            item {
                EventInfo(result)
            }
        }

    }

    @Composable
    private fun EventInfo(result: EventDetailsResponse) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = result.title, fontSize = TextUnit(24f, TextUnitType.Sp))
            Text(text = result.description)
        }

    }
}