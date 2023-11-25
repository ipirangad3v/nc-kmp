package com.thondigital.nc.presentation.ui.event

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.data.remote.responses.EventDetailsResponse
import kotlinx.coroutines.launch

class EventDetailsScreenModel : StateScreenModel<EventDetailsScreenModel.State>(State.Init) {
    sealed class State {
        data object Init : State()

        data object Loading : State()

        data class Result(
            val result: EventDetailsResponse,
        ) : State()
    }

    fun getEventInfo(eventId: String) {
        screenModelScope.launch {
            mutableState.value = State.Loading
            mutableState.value =
                State.Result(
                    EventDetailsResponse(
                        id = eventId,
                        title = "Event title",
                        description = "Event description",
                        date = "2021-10-10",
                        startTime = "10:00",
                        endTime = "12:00",
                        location = "Event location",
                        imageUrl = "https://picsum.photos/200/300",
                    ),
                )
        }
    }
}
