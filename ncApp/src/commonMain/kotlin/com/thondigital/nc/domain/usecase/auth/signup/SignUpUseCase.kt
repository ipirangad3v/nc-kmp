package com.thondigital.nc.domain.usecase.auth.signup

import com.thondigital.nc.common.utils.result.SignUpResult
import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.model.ResponseState
import com.thondigital.nc.domain.repository.auth.AuthRepository
import com.thondigital.nc.domain.validator.AuthValidator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SignUpUseCase(
    private val authRepository: AuthRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        email: String,
        username: String,
        password: String,
        confirmPassword: String
    ): SignUpResult {
        val emailError = AuthValidator.emailError(email)
        val usernameError = AuthValidator.usernameError(username)
        val passwordError = AuthValidator.passwordError(password)
        val confirmPasswordError = AuthValidator.confirmPasswordError(password, confirmPassword)

        if (emailError != null || usernameError != null || passwordError != null || confirmPasswordError != null) {
            return SignUpResult(emailError, usernameError, passwordError, confirmPasswordError)
        }

        return when (
            val result =
                withContext(dispatcher) {
                    authRepository.signUp(
                        email,
                        username,
                        password,
                        confirmPassword
                    )
                }
        ) {
            is DataResult.Success -> {
                when (ResponseState.fromString(result.data.status)) {
                    ResponseState.SUCCESS -> {
                        authRepository.storeTokens(result.data)
                        SignUpResult(result = DataResult.Success(Unit))
                    }

                    else -> {
                        SignUpResult(result = DataResult.Error(Exception(result.data.message)))
                    }
                }
            }

            is DataResult.Error -> {
                SignUpResult(result = DataResult.Error(result.exception))
            }
        }
    }
}
