package com.thondigital.nc.data.remote

import com.thondigital.nc.domain.models.EventDetailsResponse
import com.thondigital.nc.domain.repository.EventRepository

class FakeEventRepository : EventRepository {
    override suspend fun getEventById(eventId: Long): EventDetailsResponse {
        return EventDetailsResponse(
            id = 1,
            title = "Evento 1",
            date = "2021-01-01",
            description = "Descrição do evento 1",
            image = "https://picsum.photos/200/300",
            location = "Local do evento 1"
        )
    }
}