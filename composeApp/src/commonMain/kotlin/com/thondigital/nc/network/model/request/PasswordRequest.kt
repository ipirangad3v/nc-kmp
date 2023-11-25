package com.thondigital.nc.network.model.request

import kotlinx.serialization.Serializable

@Serializable
data class PasswordRequest(
    val currentPassword: String,
    val newPassword: String,
    val confirmNewPassword: String,
)
