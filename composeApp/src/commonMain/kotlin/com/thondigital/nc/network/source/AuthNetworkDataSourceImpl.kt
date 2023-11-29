package com.thondigital.nc.network.source

import com.thondigital.nc.common.exception.NetworkException
import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.data.connectivity.ConnectivityChecker
import com.thondigital.nc.data.model.TokensDataModel
import com.thondigital.nc.data.source.network.auth.AuthNetworkDataSource
import com.thondigital.nc.network.apiservice.AuthApiService
import com.thondigital.nc.network.mapper.TokensNetworkDataMapper
import com.thondigital.nc.network.model.request.SignInRequest
import com.thondigital.nc.network.model.request.SignUpRequest
import com.thondigital.nc.network.model.request.UpdateTokenRequest

class AuthNetworkDataSourceImpl(
    private val authApiService: AuthApiService,
    private val tokensNetworkDataMapper: TokensNetworkDataMapper,
    private val connectivityChecker: ConnectivityChecker,
) : AuthNetworkDataSource {
    override suspend fun signIn(
        email: String,
        password: String,
    ): DataResult<TokensDataModel> {
        if (!connectivityChecker.isNetworkAvailable()) {
            return DataResult.Error(NetworkException.NetworkUnavailable)
        }
        return try {
            DataResult.Success(
                authApiService.signIn(SignInRequest(email, password)).let {
                    tokensNetworkDataMapper.from(it)
                },
            )
        } catch (exception: Exception) {
            DataResult.Error(exception)
        }
    }

    override suspend fun signUp(
        email: String,
        username: String,
        password: String,
        confirmPassword: String,
    ): DataResult<TokensDataModel> {
        if (!connectivityChecker.isNetworkAvailable()) {
            return DataResult.Error(NetworkException.NetworkUnavailable)
        }
        return try {
            DataResult.Success(
                authApiService.signUp(SignUpRequest(email, username, password, confirmPassword))
                    .let {
                        tokensNetworkDataMapper.from(it)
                    },
            )
        } catch (exception: Exception) {
            DataResult.Error(exception)
        }
    }

    override suspend fun revokeToken(token: String) {
        authApiService.revokeToken(UpdateTokenRequest(token))
    }
}
