package com.thondigital.nc.data.repository.account

import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.data.mapper.AccountDataDomainMapper
import com.thondigital.nc.data.source.cache.account.AccountCacheDataSource
import com.thondigital.nc.data.source.network.account.AccountNetworkDataSource
import com.thondigital.nc.domain.model.AccountDomainModel
import com.thondigital.nc.domain.repository.account.AccountRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DefaultAccountRepository(
    private val accountNetworkDataSource: AccountNetworkDataSource,
    private val accountCacheDataSource: AccountCacheDataSource,
    private val accountDataDomainMapper: AccountDataDomainMapper
) : AccountRepository {
    override suspend fun requestAccount(): DataResult<AccountDomainModel> {
        return when (val result = accountNetworkDataSource.getAccount()) {
            is DataResult.Success -> {
                storeAccount(accountDataDomainMapper.from(result.data))

                DataResult.Success(
                    accountDataDomainMapper.from(result.data)
                )
            }

            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun storeAccount(account: AccountDomainModel) {
        accountDataDomainMapper.to(account).let {
            accountCacheDataSource.storeAccount(it)
        }
    }

    override fun getAccount(): Flow<AccountDomainModel> {
        return accountCacheDataSource.fetchAccount().map {
            accountDataDomainMapper.from(it)
        }
    }

    override suspend fun updatePassword(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): DataResult<String> {
        return when (
            val result =
                accountNetworkDataSource.updatePassword(
                    currentPassword,
                    newPassword,
                    confirmNewPassword
                )
        ) {
            is DataResult.Success -> DataResult.Success(result.data)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }

    override suspend fun deleteAccount(): DataResult<String> {
        accountCacheDataSource.deleteAccount()
        return when (
            val result =
                accountNetworkDataSource.deleteAccount()
        ) {
            is DataResult.Success -> DataResult.Success(result.data)
            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }
}
