package com.thondigital.nc.domain.repository

import com.thondigital.nc.domain.models.EventDetailsResponse

interface EventRepository {
    suspend fun getEventById(eventId: Long): EventDetailsResponse
}
