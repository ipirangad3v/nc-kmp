package com.thondigital.nc.domain.usecase.auth.signin.email

import com.thondigital.nc.common.utils.result.SignInResult
import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.model.ResponseState
import com.thondigital.nc.domain.repository.auth.AuthRepository
import com.thondigital.nc.domain.validator.AuthValidator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignInUseCase(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        email: String,
        password: String
    ): SignInResult {
        val emailError = AuthValidator.emailError(email)
        val passwordError = AuthValidator.passwordError(password)

        if (emailError != null || passwordError != null) {
            return SignInResult(emailError, passwordError)
        }

        return when (
            val result =
                withContext(dispatcher) {
                    authRepository.signIn(email, password)
                }
        ) {
            is DataResult.Success -> {
                when (ResponseState.fromString(result.data.status)) {
                    ResponseState.SUCCESS -> {
                        authRepository.storeTokens(result.data)
                        SignInResult(result = DataResult.Success(Unit))
                    }

                    else -> {
                        SignInResult(result = DataResult.Error(Exception(result.data.message)))
                    }
                }
            }

            is DataResult.Error -> {
                SignInResult(result = DataResult.Error(result.exception))
            }
        }
    }
}
