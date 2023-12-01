package com.thondigital.nc.domain.validator

import com.thondigital.nc.common.utils.AuthError

object AuthValidator {
    private const val USERNAME_LENGTH = 3
    private const val PASSWORD_LENGTH = 7

    fun emailError(email: String): AuthError? {
        return when {
            email.isEmpty() -> AuthError.EmptyField
            !isValidEmail(email) -> AuthError.InvalidEmail
            else -> null
        }
    }

    fun usernameError(username: String): AuthError? {
        return when {
            username.isEmpty() -> AuthError.EmptyField
            username.count() < USERNAME_LENGTH -> AuthError.InputTooShort
            !username.isValidName() -> AuthError.InvalidUsername
            else -> null
        }
    }

    fun passwordError(password: String): AuthError? {
        return when {
            password.isEmpty() -> AuthError.EmptyField
            !isValidPassword(password) -> AuthError.InputTooShort
            else -> null
        }
    }

    fun confirmPasswordError(
        password: String,
        confirmPassword: String
    ): AuthError? {
        return when {
            confirmPassword.isEmpty() -> AuthError.EmptyField
            !isValidPassword(confirmPassword) -> AuthError.InputTooShort
            !passwordMatches(
                password,
                confirmPassword
            ) -> AuthError.UnmatchedPassword

            else -> null
        }
    }

    private fun isValidEmail(email: String): Boolean = Regex(MAIL_REGEX).matches(email)

    private fun String.isValidName() = matches("^[a-zA-ZÀ-ÿ]+(?: [a-zA-ZÀ-ÿ]+)+$".toRegex())

    private fun isValidPassword(password: String): Boolean = password.count() > PASSWORD_LENGTH

    private fun passwordMatches(
        newPassword: String,
        confirmNewPassword: String
    ): Boolean = newPassword == confirmNewPassword

    private const val MAIL_REGEX = (
        "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@" +
            "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
            "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\." +
            "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?" +
            "[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|" +
            "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$"
        )
}
