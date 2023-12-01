package com.thondigital.nc.domain.usecase.account.update

import com.thondigital.nc.common.utils.result.UpdatePasswordResult
import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.repository.account.AccountRepository
import com.thondigital.nc.domain.validator.AuthValidator
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class UpdatePasswordUseCase(
    private val accountRepository: AccountRepository,
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(
        currentPassword: String,
        newPassword: String,
        confirmNewPassword: String
    ): UpdatePasswordResult {
        val currentPasswordError = AuthValidator.passwordError(currentPassword)
        val newPasswordError = AuthValidator.passwordError(newPassword)
        val confirmNewPasswordError =
            AuthValidator.confirmPasswordError(newPassword, confirmNewPassword)

        if (currentPasswordError != null || newPasswordError != null || confirmNewPasswordError != null) {
            return UpdatePasswordResult(
                currentPasswordError,
                newPasswordError,
                confirmNewPasswordError
            )
        }

        return when (
            val result =
                withContext(dispatcher) {
                    accountRepository.updatePassword(
                        currentPassword,
                        newPassword,
                        confirmNewPassword
                    )
                }
        ) {
            is DataResult.Success -> {
                UpdatePasswordResult(result = DataResult.Success(Unit))
            }
            is DataResult.Error -> {
                UpdatePasswordResult(result = DataResult.Error(result.exception))
            }
        }
    }
}
