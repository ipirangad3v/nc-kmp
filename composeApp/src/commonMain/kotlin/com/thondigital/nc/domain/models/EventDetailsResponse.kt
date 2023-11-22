package com.thondigital.nc.domain.models

data class EventDetailsResponse(
    val id: Long,
    val title: String,
    val date: String,
    val description: String,
    val image: String,
    val location: String,
)
