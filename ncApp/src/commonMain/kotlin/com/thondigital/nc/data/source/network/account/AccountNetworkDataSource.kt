package com.thondigital.nc.data.source.network.account

import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.data.model.AccountDataModel

interface AccountNetworkDataSource {
    suspend fun getAccount(): DataResult<AccountDataModel>

    suspend fun updatePassword(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): DataResult<String>

    suspend fun deleteAccount(): DataResult<String>
}
