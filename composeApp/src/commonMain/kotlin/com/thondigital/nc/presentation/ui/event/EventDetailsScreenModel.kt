package com.thondigital.nc.presentation.ui.event

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.domain.models.EventDetailsResponse
import com.thondigital.nc.domain.repository.EventRepository
import kotlinx.coroutines.launch

class EventDetailsScreenModel(
    private val repository: EventRepository,
) : StateScreenModel<EventDetailsScreenModel.State>(State.Init) {
    sealed class State {
        data object Init : State()

        data object Loading : State()

        data class Result(
            val result: EventDetailsResponse,
        ) : State()
    }

    fun getEventInfo(eventId: Long) {
        screenModelScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result(repository.getEventById(eventId = eventId))
        }
    }
}
