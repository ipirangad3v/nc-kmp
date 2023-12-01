package com.thondigital.nc.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class EventDetailsResponse(
    var id: String,
    val title: String,
    val date: String,
    val description: String,
    val imageUrl: String? = null,
    val location: String,
    val startTime: String,
    val endTime: String
)
