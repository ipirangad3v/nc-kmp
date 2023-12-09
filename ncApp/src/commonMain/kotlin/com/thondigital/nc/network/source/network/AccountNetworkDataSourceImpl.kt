package com.thondigital.nc.network.source.network

import com.thondigital.nc.common.exception.NetworkException
import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.data.connectivity.ConnectivityChecker
import com.thondigital.nc.data.model.AccountDataModel
import com.thondigital.nc.data.source.network.account.AccountNetworkDataSource
import com.thondigital.nc.network.apiservice.AccountApiService
import com.thondigital.nc.network.mapper.AccountNetworkDataMapper
import com.thondigital.nc.network.model.request.PasswordRequest

class AccountNetworkDataSourceImpl(
    private val accountApiService: AccountApiService,
    private val accountNetworkDataMapper: AccountNetworkDataMapper,
    private val connectivityChecker: ConnectivityChecker
) : AccountNetworkDataSource {
    override suspend fun getAccount(): DataResult<AccountDataModel> {
        if (!connectivityChecker.isNetworkAvailable()) {
            return DataResult.Error(NetworkException.NetworkUnavailable)
        }
        return try {
            DataResult.Success(
                accountApiService.getAccount().let {
                    accountNetworkDataMapper.from(it)
                }
            )
        } catch (exception: Exception) {
            DataResult.Error(exception)
        }
    }

    override suspend fun updatePassword(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): DataResult<String> {
        if (!connectivityChecker.isNetworkAvailable()) {
            return DataResult.Error(NetworkException.NetworkUnavailable)
        }
        return try {
            DataResult.Success(
                accountApiService.updatePassword(
                    PasswordRequest(
                        currentPassword,
                        newPassword,
                        confirmNewPassword
                    )
                ).message
            )
        } catch (exception: Exception) {
            DataResult.Error(exception)
        }
    }

    override suspend fun deleteAccount(): DataResult<String> {
        if (!connectivityChecker.isNetworkAvailable()) {
            return DataResult.Error(NetworkException.NetworkUnavailable)
        }
        return try {
            DataResult.Success(
                accountApiService.deleteAccount().message
            )
        } catch (exception: Exception) {
            DataResult.Error(exception)
        }
    }
}
