package com.thondigital.nc.network.apiservice

import com.thondigital.nc.network.model.request.SignInRequest
import com.thondigital.nc.network.model.request.SignUpRequest
import com.thondigital.nc.network.model.request.UpdateTokenRequest
import com.thondigital.nc.network.model.response.TokensNetworkModel

interface AuthApiService {
    suspend fun signIn(signInRequest: SignInRequest): TokensNetworkModel

    suspend fun signUp(signUpRequest: SignUpRequest): TokensNetworkModel

    suspend fun updateToken(updateTokenRequest: UpdateTokenRequest): TokensNetworkModel

    suspend fun revokeToken(updateTokenRequest: UpdateTokenRequest): TokensNetworkModel
}
