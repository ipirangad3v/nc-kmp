package com.thondigital.nc.domain.repository

import com.thondigital.nc.data.remote.responses.EventDetailsResponse
import com.thondigital.nc.data.remote.responses.HomeResponse

interface FirestoreRepository {
    suspend fun getHome(): HomeResponse
    suspend fun getEventById(eventId: String): EventDetailsResponse
}
