package com.thondigital.nc.data.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountDataModel(
    val pk: Int,
    val email: String,
    val username: String,
    val isAdmin: Boolean,
)
