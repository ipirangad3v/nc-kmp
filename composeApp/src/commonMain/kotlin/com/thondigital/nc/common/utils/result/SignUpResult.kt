package com.thondigital.nc.common.utils.result

import com.thondigital.nc.common.utils.AuthError
import com.thondigital.nc.common.wrapper.SimpleResource

data class SignUpResult(
    val emailError: AuthError? = null,
    val usernameError: AuthError? = null,
    val passwordError: AuthError? = null,
    val confirmPasswordError: AuthError? = null,
    val result: SimpleResource? = null,
)
