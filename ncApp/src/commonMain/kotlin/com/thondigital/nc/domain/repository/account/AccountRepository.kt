package com.thondigital.nc.domain.repository.account

import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.model.AccountDomainModel
import kotlinx.coroutines.flow.Flow

interface AccountRepository {
    suspend fun requestAccount(): DataResult<AccountDomainModel>

    suspend fun storeAccount(account: AccountDomainModel)

    fun getAccount(): Flow<AccountDomainModel>

    suspend fun updatePassword(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): DataResult<String>

    suspend fun deleteAccount(): DataResult<String>
}
