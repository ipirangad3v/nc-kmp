package com.thondigital.nc.network.model.request

data class PasswordRequest(
    val currentPassword: String,
    val newPassword: String,
    val confirmNewPassword: String,
)
