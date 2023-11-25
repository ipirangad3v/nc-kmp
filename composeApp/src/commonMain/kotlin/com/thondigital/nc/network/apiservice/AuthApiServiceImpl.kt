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
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.InternalAPI
import kotlinx.serialization.json.Json

@OptIn(InternalAPI::class)
class AuthApiServiceImpl : AuthApiService {
    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
                useAlternativeNames = false
            })
        }
    }

    override suspend fun signIn(signInRequest: SignInRequest): TokensNetworkModel {
        return httpClient.post("$BASE_ENDPOINT/$SIGNIN_ENDPOINT") {
            contentType(ContentType.Application.Json)
            body = signInRequest
        }.body()
    }

    override suspend fun signUp(signUpRequest: SignUpRequest): TokensNetworkModel {
        return httpClient.post("$BASE_ENDPOINT/$SIGNUP_ENDPOINT") {
            contentType(ContentType.Application.Json)
            body = signUpRequest
        }.body()
    }

    override suspend fun updateToken(updateTokenRequest: UpdateTokenRequest): TokensNetworkModel {
        return httpClient.put("$BASE_ENDPOINT/$REFRESH_TOKEN_ENDPOINT") {
            contentType(ContentType.Application.Json)
            body = updateTokenRequest
        }.body()
    }

    override suspend fun revokeToken(updateTokenRequest: UpdateTokenRequest): TokensNetworkModel {
        return httpClient.put("$BASE_ENDPOINT/$REVOKE_TOKEN_ENDPOINT") {
            contentType(ContentType.Application.Json)
            body = updateTokenRequest
        }.body()
    }
}