package com.thondigital.nc.domain.usecase.auth.status

import com.thondigital.nc.domain.repository.auth.AuthRepository

class AuthenticationStatusUseCase(
    private val authRepository: AuthRepository,
) {
    operator fun invoke(): Boolean {
        return authRepository.authenticationStatus()
    }
}
