package com.thondigital.nc.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class UpdateTokenRequest(val token: String)
