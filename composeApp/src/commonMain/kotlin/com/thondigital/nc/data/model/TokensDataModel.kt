package com.thondigital.nc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class TokensDataModel(
    val accessToken: String,
    val refreshToken: String,
)
