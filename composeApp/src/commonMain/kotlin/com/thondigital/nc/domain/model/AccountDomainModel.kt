package com.thondigital.nc.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class AccountDomainModel(
    val pk: Int,
    val email: String,
    val username: String,
)
