package com.thondigital.nc.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class HomeResponse(
    val events: List<EventDetailsResponse>,
    val isUserAuthenticated: Boolean = false,
)
