package com.thondigital.nc.data.repository.auth

import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.data.mapper.TokensDataDomainMapper
import com.thondigital.nc.data.source.network.auth.AuthNetworkDataSource
import com.thondigital.nc.data.source.preferences.PreferencesDataSource
import com.thondigital.nc.domain.model.TokensDomainModel
import com.thondigital.nc.domain.repository.auth.AuthRepository

class DefaultAuthRepository(
    private val authNetworkDataSource: AuthNetworkDataSource,
    private val preferencesDataSource: PreferencesDataSource,
    private val tokensDataDomainMapper: TokensDataDomainMapper
) : AuthRepository {
    override suspend fun signIn(
        email: String,
        password: String
    ): DataResult<TokensDomainModel> {
        return when (val result = authNetworkDataSource.signIn(email, password)) {
            is DataResult.Success -> DataResult.Success(tokensDataDomainMapper.from(result.data))
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun signUp(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): DataResult<TokensDomainModel> {
        return when (
            val result =
                authNetworkDataSource.signUp(email, username, password, confirmPassword)
        ) {
            is DataResult.Success -> DataResult.Success(tokensDataDomainMapper.from(result.data))
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun fetchTokens(): TokensDomainModel {
        return with(preferencesDataSource) {
            TokensDomainModel(getAccessToken(), getRefreshToken())
        }
    }

    override suspend fun storeTokens(tokens: TokensDomainModel) {
        with(preferencesDataSource) {
            storeTokens(tokensDataDomainMapper.to(tokens))
        }
    }

    override suspend fun deleteTokens() {
        return with(preferencesDataSource) {
            deleteTokens()
        }
    }

    override suspend fun revokeToken(token: String) {
        authNetworkDataSource.revokeToken(token)
    }

    override fun authenticationStatus(): Boolean {
        return preferencesDataSource.getAccessToken().isNotEmpty()
    }
}
