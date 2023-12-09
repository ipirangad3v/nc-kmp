package com.thondigital.nc.network.apiservice

import com.thondigital.nc.data.source.preferences.PreferencesDataSource
import com.thondigital.nc.network.model.NetworkConstants
import com.thondigital.nc.network.model.request.PasswordRequest
import com.thondigital.nc.network.model.response.AccountNetworkModel
import com.thondigital.nc.network.model.response.GenericResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.contentType

class AccountApiServiceImpl(
    private val httpClient: HttpClient,
    private val preferencesDataSource: PreferencesDataSource
) : AccountApiService {
    override suspend fun getAccount(): AccountNetworkModel {
        return httpClient.get("${NetworkConstants.BASE_ENDPOINT}/${NetworkConstants.ACCOUNT_ENDPOINT}") {
            contentType(ContentType.Application.Json)
            headers {
                append("Authorization", "Bearer ${preferencesDataSource.getAccessToken()}")
            }
        }.body() as AccountNetworkModel
    }

    override suspend fun updatePassword(passwordRequest: PasswordRequest): GenericResponse {
        return httpClient.post("${NetworkConstants.BASE_ENDPOINT}/${NetworkConstants.PASSWORD_ENDPOINT}") {
            contentType(ContentType.Application.Json)
            headers {
                append("Authorization", "Bearer ${preferencesDataSource.getAccessToken()}")
            }
        }.body() as GenericResponse
    }

    override suspend fun deleteAccount(): GenericResponse {
        return httpClient.post("${NetworkConstants.BASE_ENDPOINT}/${NetworkConstants.DELETE_ACCOUNT_ENDPOINT}") {
            contentType(ContentType.Application.Json)
            headers {
                append("Authorization", "Bearer ${preferencesDataSource.getAccessToken()}")
            }
        }.body() as GenericResponse
    }
}
