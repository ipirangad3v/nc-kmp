package com.thondigital.nc.common.utils.result

import com.thondigital.nc.common.utils.AuthError
import com.thondigital.nc.common.wrapper.SimpleResource

data class UpdatePasswordResult(
    val currentPasswordError: AuthError? = null,
    val newPasswordError: AuthError? = null,
    val confirmPasswordError: AuthError? = null,
    val result: SimpleResource? = null,
)
