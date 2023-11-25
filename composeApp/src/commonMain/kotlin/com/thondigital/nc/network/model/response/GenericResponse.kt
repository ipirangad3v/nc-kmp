package com.thondigital.nc.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class GenericResponse(
    val status: String,
    val message: String,
)
