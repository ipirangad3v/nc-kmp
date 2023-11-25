package com.thondigital.nc.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class TokensNetworkModel(
    val accessToken: String?,
    val refreshToken: String?,
)
