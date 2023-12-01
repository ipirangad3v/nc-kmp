package com.thondigital.nc.presentation.ui.auth.signup

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.common.utils.AuthError
import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.usecase.auth.signup.SignUpUseCase
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignUpScreenModel(
    private val signUpUseCase: SignUpUseCase
) : ScreenModel {
    private val currentState: SignUpContract.SignUpViewState
        get() = viewState.value

    val viewState: StateFlow<SignUpContract.SignUpViewState> get() = _viewState
    private val _viewState = MutableStateFlow(SignUpContract.SignUpViewState())

    private val _viewEffect: Channel<SignUpContract.SignUpViewEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    private var job: Job = Job()

    /**
     * Handle events
     */
    fun setEvent(event: SignUpContract.SignUpEvent) {
        when (event) {
            is SignUpContract.SignUpEvent.EmailChanged -> setState { copy(email = event.email) }
            is SignUpContract.SignUpEvent.UsernameChanged -> setState { copy(username = event.username) }
            is SignUpContract.SignUpEvent.PasswordChanged -> setState { copy(password = event.password) }
            is SignUpContract.SignUpEvent.ConfirmPasswordChanged ->
                setState { copy(confirmPassword = event.confirmPassword) }

            is SignUpContract.SignUpEvent.SignUpButtonClicked -> onSignUp()
        }
    }

    /**
     * Set new ui state
     */
    private fun setState(reduce: SignUpContract.SignUpViewState.() -> SignUpContract.SignUpViewState) {
        val newState = currentState.reduce()
        _viewState.value = newState
    }

    /**
     * Set new effect
     */
    private fun setEffect(builder: () -> SignUpContract.SignUpViewEffect) {
        val effectValue = builder()
        setState { copy(loading = false) }
        screenModelScope.launch { _viewEffect.send(effectValue) }
    }

    /**
     * Handle email sign up event
     */
    private fun onSignUp() {
        job.cancel()
        currentState.let {
            job =
                screenModelScope.launch {
                    setState { copy(loading = true) }
                    val signUpResult =
                        signUpUseCase(it.email, it.username, it.password, it.confirmPassword)
                    setState { copy(loading = false) }

                    setEmailErrorState(signUpResult.emailError)
                    setUsernameErrorState(signUpResult.usernameError)
                    setPasswordErrorState(signUpResult.passwordError)
                    setConfirmPasswordErrorState(signUpResult.confirmPasswordError)

                    when (signUpResult.result) {
                        is DataResult.Success<*> -> setEffect { SignUpContract.SignUpViewEffect.NavigateToHome }
                        is DataResult.Error<*> ->
                            setEffect {
                                SignUpContract.SignUpViewEffect.ShowSnackBarError(
                                    (signUpResult.result as DataResult.Error<Unit>).exception.message.toString()
                                )
                            }

                        null -> return@launch
                    }
                }
        }
    }

    private fun setEmailErrorState(emailError: AuthError?) {
        emailError?.let {
            if (it == AuthError.EmptyField) {
                setState { copy(emailError = "Campo vazio") }
            } else {
                setState { copy(emailError = "Por favor insira um email valido") }
            }
        } ?: setState { copy(emailError = "") }
    }

    private fun setUsernameErrorState(usernameError: AuthError?) {
        usernameError?.let {
            when (it) {
                is AuthError.EmptyField -> setState { copy(usernameError = "Campo vazio") }
                is AuthError.InputTooShort ->
                    setState {
                        copy(usernameError = "Nome de usuário precisa ter ao menos 3 caracteres")
                    }

                else -> setState { copy(usernameError = "caracteres especiais não permitidos") }
            }
        } ?: setState { copy(usernameError = "") }
    }

    private fun setPasswordErrorState(passwordError: AuthError?) {
        passwordError?.let {
            if (it == AuthError.EmptyField) {
                setState { copy(passwordError = "Campo vazio") }
            } else {
                setState { copy(passwordError = "A senha precisa ter ao menos 8 digitos") }
            }
        } ?: setState { copy(passwordError = "") }
    }

    private fun setConfirmPasswordErrorState(confirmPasswordError: AuthError?) {
        confirmPasswordError?.let {
            when (it) {
                is AuthError.EmptyField -> setState { copy(confirmPasswordError = "Campo vazio") }
                is AuthError.InputTooShort ->
                    setState {
                        copy(confirmPasswordError = "A senha precisa ter ao menos 8 digitos")
                    }

                else -> setState { copy(confirmPasswordError = "As senhas precisam ser iguais") }
            }
        } ?: setState { copy(confirmPasswordError = "") }
    }
}
