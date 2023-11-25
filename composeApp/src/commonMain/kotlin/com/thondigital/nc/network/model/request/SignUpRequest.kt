package com.thondigital.nc.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class SignUpRequest(
    val email: String,
    val username: String,
    val password: String,
    val confirmPassword: String,
)
