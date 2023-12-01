package com.thondigital.nc.domain.repository.auth

import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.model.TokensDomainModel

interface AuthRepository {
    suspend fun signIn(
        email: String,
        password: String
    ): DataResult<TokensDomainModel>

    suspend fun signUp(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): DataResult<TokensDomainModel>

    suspend fun fetchTokens(): TokensDomainModel

    suspend fun storeTokens(tokens: TokensDomainModel)

    suspend fun deleteTokens()

    suspend fun revokeToken(token: String)

    fun authenticationStatus(): Boolean
}
