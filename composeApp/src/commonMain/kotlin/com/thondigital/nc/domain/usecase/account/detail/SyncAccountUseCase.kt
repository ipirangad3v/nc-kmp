package com.thondigital.nc.domain.usecase.account.detail

import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.repository.account.AccountRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SyncAccountUseCase(
    private val accountRepository: AccountRepository,
    private val dispatcher: CoroutineDispatcher,
) {
    suspend operator fun invoke(): DataResult<Unit> {
        return when (
            val result =
                withContext(dispatcher) {
                    accountRepository.requestAccount()
                }
        ) {
            is DataResult.Success -> {
                accountRepository.storeAccount(result.data)
                DataResult.Success(Unit)
            }

            is DataResult.Error -> DataResult.Error(result.exception)
        }
    }
}
