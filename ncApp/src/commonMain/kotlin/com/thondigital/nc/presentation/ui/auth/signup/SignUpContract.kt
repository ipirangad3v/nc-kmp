package com.thondigital.nc.presentation.ui.auth.signup

class SignUpContract {
    sealed class SignUpEvent {
        data class EmailChanged(val email: String) : SignUpEvent()

        data class UsernameChanged(val username: String) : SignUpEvent()

        data class PasswordChanged(val password: String) : SignUpEvent()

        data class ConfirmPasswordChanged(val confirmPassword: String) : SignUpEvent()

        object SignUpButtonClicked : SignUpEvent()
    }

    sealed class SignUpViewEffect {
        data class ShowSnackBarError(val message: String) : SignUpViewEffect()

        object NavigateToHome : SignUpViewEffect()
    }

    data class SignUpViewState(
        val email: String = "",
        val username: String = "",
        val password: String = "",
        val confirmPassword: String = "",
        val loading: Boolean = false,
        val emailError: String = "",
        val usernameError: String = "",
        val passwordError: String = "",
        val confirmPasswordError: String = ""
    )
}
