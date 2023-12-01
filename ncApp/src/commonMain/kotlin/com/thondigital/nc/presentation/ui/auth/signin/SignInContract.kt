package com.thondigital.nc.presentation.ui.auth.signin

import kotlinx.serialization.Serializable

@Serializable
class SignInContract {
    @Serializable
    sealed class SignInEvent {
        @Serializable
        data class EmailChanged(val email: String) : SignInEvent()

        @Serializable
        data class PasswordChanged(val password: String) : SignInEvent()

        @Serializable
        data object SignInButtonClicked : SignInEvent()

        @Serializable
        data object SignUpTextViewClicked : SignInEvent()
    }

    @Serializable
    sealed class SignInViewEffect {
        @Serializable
        data class ShowSnackBarError(val message: String) : SignInViewEffect()

        @Serializable
        data object NavigateToSignUp : SignInViewEffect()

        @Serializable
        data object NavigateToHome : SignInViewEffect()

        @Serializable
        data object Init : SignInViewEffect()
    }

    @Serializable
    data class SignInViewState(
        val email: String = "",
        val password: String = "",
        val loading: Boolean = false,
        val emailError: String = "",
        val passwordError: String = ""
    )
}
