package com.thondigital.nc.common.utils.result

import com.thondigital.nc.common.utils.AuthError
import com.thondigital.nc.common.wrapper.SimpleResource
import kotlinx.serialization.Serializable

@Serializable
data class SignInResult(
    @Serializable
    val emailError: AuthError? = null,
    @Serializable
    val passwordError: AuthError? = null,
    @Serializable
    val result: SimpleResource? = null,
)
