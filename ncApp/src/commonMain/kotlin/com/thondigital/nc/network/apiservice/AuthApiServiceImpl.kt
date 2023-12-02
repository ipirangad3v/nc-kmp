package com.thondigital.nc.network.apiservice

import com.thondigital.nc.network.model.NetworkConstants.BASE_ENDPOINT
import com.thondigital.nc.network.model.NetworkConstants.REFRESH_TOKEN_ENDPOINT
import com.thondigital.nc.network.model.NetworkConstants.REVOKE_TOKEN_ENDPOINT
import com.thondigital.nc.network.model.NetworkConstants.SIGNIN_ENDPOINT
import com.thondigital.nc.network.model.NetworkConstants.SIGNUP_ENDPOINT
import com.thondigital.nc.network.model.request.SignInRequest
import com.thondigital.nc.network.model.request.SignUpRequest
import com.thondigital.nc.network.model.request.UpdateTokenRequest
import com.thondigital.nc.network.model.response.TokensNetworkModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
class AuthApiServiceImpl(
    private val httpClient: HttpClient
) : AuthApiService {
    override suspend fun signIn(signInRequest: SignInRequest): TokensNetworkModel {
        return httpClient.post("$BASE_ENDPOINT/$SIGNIN_ENDPOINT") {
            body = Json.encodeToString(SignInRequest.serializer(), signInRequest)
            contentType(ContentType.Application.Json)
        }.body() as TokensNetworkModel
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): TokensNetworkModel {
        return httpClient.post("$BASE_ENDPOINT/$SIGNUP_ENDPOINT") {
            body = Json.encodeToString(SignUpRequest.serializer(), signUpRequest)
            contentType(ContentType.Application.Json)
        }.body() as TokensNetworkModel
    }

    override suspend fun updateToken(updateTokenRequest: UpdateTokenRequest): TokensNetworkModel {
        return httpClient.put("$BASE_ENDPOINT/$REFRESH_TOKEN_ENDPOINT") {
            body = Json.encodeToString(UpdateTokenRequest.serializer(), updateTokenRequest)
            contentType(ContentType.Application.Json)
        }.body() as TokensNetworkModel
    }

    override suspend fun revokeToken(updateTokenRequest: UpdateTokenRequest): TokensNetworkModel {
        return httpClient.put("$BASE_ENDPOINT/$REVOKE_TOKEN_ENDPOINT") {
            body = Json.encodeToString(UpdateTokenRequest.serializer(), updateTokenRequest)
            contentType(ContentType.Application.Json)
        }.body() as TokensNetworkModel
    }
}
