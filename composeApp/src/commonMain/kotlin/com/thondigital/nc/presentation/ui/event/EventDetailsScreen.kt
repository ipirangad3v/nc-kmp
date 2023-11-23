package com.thondigital.nc.presentation.ui.event

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.seiko.imageloader.rememberImagePainter
import com.thondigital.nc.data.remote.responses.EventDetailsResponse
import com.thondigital.nc.presentation.ui.components.Loading
import com.thondigital.nc.presentation.ui.components.TopBar

class EventDetailsScreen(private val eventId: String, private val onBackClick: () -> Unit) :
    Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<EventDetailsScreenModel>()
        val state by screenModel.state.collectAsState()

        Column(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is EventDetailsScreenModel.State.Loading -> Loading()
                is EventDetailsScreenModel.State.Result  ->
                    EventScreenContent(
                        (state as EventDetailsScreenModel.State.Result).result,
                    )

                is EventDetailsScreenModel.State.Init    -> screenModel.getEventInfo(eventId)
            }
        }
    }

    @Composable
    private fun EventScreenContent(result: EventDetailsResponse) {
        val painter =
            result.imageUrl?.let {
                rememberImagePainter(
                    it,
                    contentScale = ContentScale.Crop,
                )
            }
        LazyColumn(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            item { TopBar(showBackButton = true) { onBackClick() } }
            item {
                if (painter != null) {
                    Image(
                        modifier = Modifier.fillMaxWidth(),
                        painter = painter,
                        contentDescription = "image",
                    )
                }
            }
            item {
                EventInfo(result)
            }
        }
    }

    @Composable
    private fun EventInfo(result: EventDetailsResponse) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = result.title, fontSize = TextUnit(24f, TextUnitType.Sp), fontWeight = Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Quando:",
                    fontWeight = Bold,
                    fontStyle = FontStyle.Italic,
                )
                Text(
                    text = "${result.date} das ${result.startTime} às ${result.endTime}.",
                )
            }
            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(
                    text = "Local: ",
                    fontWeight = Bold,
                    fontStyle = FontStyle.Italic,
                )
                Text(text = "${result.location}.")
            }
            Spacer(modifier = Modifier.height(8.dp))

            Text(text = result.description)
        }
    }
}
