package com.thondigital.nc.presentation.ui.event

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import com.thondigital.nc.domain.models.EventDetailsResponse
import com.thondigital.nc.presentation.ui.components.Loading

class EventScreen(private val eventId: Long) : Screen {
    @Composable
    override fun Content() {
        val screenModel = getScreenModel<EventScreenModel>()
        val state by screenModel.state.collectAsState()

        when (state) {
            is EventScreenModel.State.Loading -> Loading()
            is EventScreenModel.State.Result  -> EventScreenContent((state as EventScreenModel.State.Result).result)
            is EventScreenModel.State.Init    -> screenModel.getEventInfo(eventId)

        }

    }

    @Composable
    private fun EventScreenContent(result: EventDetailsResponse) {

    }
}