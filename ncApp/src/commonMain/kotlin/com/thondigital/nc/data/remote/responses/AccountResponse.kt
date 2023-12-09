package com.thondigital.nc.data.remote.responses

import com.thondigital.nc.domain.model.AccountDomainModel
import kotlinx.serialization.Serializable

@Serializable
data class AccountResponse(
    val isUserAuthenticated: Boolean = false,
    val account: AccountDomainModel?,
    val message: String? = null
)
