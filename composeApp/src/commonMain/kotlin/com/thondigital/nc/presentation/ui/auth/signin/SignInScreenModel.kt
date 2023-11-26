package com.thondigital.nc.presentation.ui.auth.signin

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.common.utils.AuthError
import com.thondigital.nc.common.wrapper.DataResult
import com.thondigital.nc.domain.usecase.auth.signin.email.SignInUseCase
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInEvent
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInEvent.EmailChanged
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInEvent.PasswordChanged
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInEvent.SignInButtonClicked
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInEvent.SignUpTextViewClicked
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToHome
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToSignUp
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class SignInScreenModel(
    private val signInUseCase: SignInUseCase,
) : ScreenModel {
    private val currentState: SignInContract.SignInViewState
        get() = viewState.value

    val viewState: StateFlow<SignInContract.SignInViewState> get() = _viewState
    private val _viewState = MutableStateFlow(SignInContract.SignInViewState())

    private val _viewEffect: Channel<SignInContract.SignInViewEffect> = Channel()
    val viewEffect = _viewEffect.receiveAsFlow()

    private var job: Job = Job()

    /**
     * Handle events
     */
    fun setEvent(event: SignInEvent) {
        when (event) {
            is EmailChanged -> setState { copy(email = event.email) }
            is PasswordChanged -> setState { copy(password = event.password) }
            is SignUpTextViewClicked -> setEffect { NavigateToSignUp }
            is SignInButtonClicked -> onSignIn()
        }
    }

    /**
     * Set new ui state
     */
    private fun setState(reduce: SignInContract.SignInViewState.() -> SignInContract.SignInViewState) {
        val newState = currentState.reduce()
        _viewState.value = newState
    }

    /**
     * Set new effect
     */
    private fun setEffect(builder: () -> SignInContract.SignInViewEffect) {
        val effectValue = builder()
        setState { copy(loading = false) }
        screenModelScope.launch { _viewEffect.send(effectValue) }
    }

    private fun onSignIn() {
        val (email, password) = currentState
        job.cancel()
        job =
            screenModelScope.launch {
                setState { copy(loading = true) }
                val signInResult = signInUseCase(email, password)
                setState { copy(loading = false) }

                setEmailErrorState(signInResult.emailError)
                setPasswordErrorState(signInResult.passwordError)

                when (signInResult.result) {
                    is DataResult.Success -> setEffect { NavigateToHome }
                    is DataResult.Error ->
                        setEffect {
                            SignInContract.SignInViewEffect.ShowSnackBarError(
                                signInResult.result.exception.message.toString(),
                            )
                        }

                    null -> return@launch
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

    private fun setPasswordErrorState(passwordError: AuthError?) {
        passwordError?.let {
            if (it == AuthError.EmptyField) {
                setState { copy(passwordError = "Campo vazio") }
            } else {
                setState { copy(passwordError = "a senha precisa ter no minimo 8 digitos") }
            }
        } ?: setState { copy(passwordError = "") }
    }
}
