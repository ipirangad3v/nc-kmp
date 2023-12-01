package com.thondigital.nc.data.source.network.auth

import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.data.model.TokensDataModel

interface AuthNetworkDataSource {
    suspend fun signIn(
        email: String,
        password: String
    ): DataResult<TokensDataModel>

    suspend fun signUp(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): DataResult<TokensDataModel>

    suspend fun revokeToken(token: String)
}
