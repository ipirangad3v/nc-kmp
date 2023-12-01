package com.thondigital.nc.domain.usecase.auth.deletetokens

import com.thondigital.nc.domain.repository.auth.AuthRepository

class DeleteTokensUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke() {
        authRepository.fetchTokens().refreshToken.let {
            authRepository.revokeToken(it)
        }
        authRepository.deleteTokens()
    }
}
