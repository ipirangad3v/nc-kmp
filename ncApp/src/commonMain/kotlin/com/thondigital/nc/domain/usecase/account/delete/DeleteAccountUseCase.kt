package com.thondigital.nc.domain.usecase.account.delete

import com.thondigital.nc.domain.repository.account.AccountRepository

class DeleteAccountUseCase(
    private val accountRepository: AccountRepository
) {
    suspend operator fun invoke() = accountRepository.deleteAccount()
}
