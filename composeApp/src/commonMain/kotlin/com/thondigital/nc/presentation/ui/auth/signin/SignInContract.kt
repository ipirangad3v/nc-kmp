package com.thondigital.nc.presentation.ui.auth.signin

class SignInContract {
    sealed class SignInEvent {
        data class EmailChanged(val email: String) : SignInEvent()

        data class PasswordChanged(val password: String) : SignInEvent()

        data object SignInButtonClicked : SignInEvent()

        data object SignUpTextViewClicked : SignInEvent()
    }

    sealed class SignInViewEffect {
        data class ShowSnackBarError(val message: String) : SignInViewEffect()

        data object NavigateToSignUp : SignInViewEffect()

        data object NavigateToHome : SignInViewEffect()

        data object Init : SignInViewEffect()
    }

    data class SignInViewState(
        val email: String = "",
        val password: String = "",
        val loading: Boolean = false,
        val emailError: String = "",
        val passwordError: String = "",
    )
}
