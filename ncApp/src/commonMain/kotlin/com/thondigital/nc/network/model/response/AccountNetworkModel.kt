package com.thondigital.nc.network.model.response

import kotlinx.serialization.Serializable

@Serializable
data class AccountNetworkModel(
    val id: Int?,
    val email: String?,
    val username: String?
)
