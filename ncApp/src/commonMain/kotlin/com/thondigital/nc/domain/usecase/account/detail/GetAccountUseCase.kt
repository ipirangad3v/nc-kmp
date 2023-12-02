package com.thondigital.nc.domain.usecase.account.detail

import com.thondigital.nc.domain.repository.account.AccountRepository
import kotlinx.coroutines.flow.firstOrNull

class GetAccountUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() = accountRepository.getAccount().firstOrNull()
}
