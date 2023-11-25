package com.thondigital.nc.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class TokensDomainModel(
    val accessToken: String,
    val refreshToken: String,
)
