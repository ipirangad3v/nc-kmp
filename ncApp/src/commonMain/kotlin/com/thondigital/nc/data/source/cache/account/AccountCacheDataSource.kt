package com.thondigital.nc.data.source.cache.account

import com.thondigital.nc.data.model.AccountDataModel
import kotlinx.coroutines.flow.Flow

interface AccountCacheDataSource {
    fun fetchAccount(): Flow<AccountDataModel>

    suspend fun storeAccount(account: AccountDataModel)

    suspend fun deleteAccount()
}
