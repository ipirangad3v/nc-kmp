package com.thondigital.nc.presentation.ui.auth.signin

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.presentation.extensions.painterResourceFromPath
import com.thondigital.nc.presentation.extensions.resourceFromShowPassword
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.Init
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToHome
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToSignUp
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.ShowSnackBarError
import com.thondigital.nc.presentation.ui.auth.signup.SignUpScreen
import com.thondigital.nc.presentation.ui.components.DefaultButton
import com.thondigital.nc.presentation.ui.components.EditTextWithErrorMessage
import com.thondigital.nc.presentation.ui.components.Loading
import com.thondigital.nc.presentation.ui.components.TopBar
import kotlinx.coroutines.launch

object SignInScreen : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val showPassword = remember { mutableStateOf(false) }

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<SignInScreenModel>()

        val viewState = screenModel.viewState.collectAsState()
        val viewEffect = screenModel.viewEffect.collectAsState(Init)

        when (viewEffect.value) {
            is ShowSnackBarError ->
                scope.launch {
                    snackbarHostState.showSnackbar(
                        (viewEffect.value as ShowSnackBarError).message,
                        duration = SnackbarDuration.Short
                    )
                }

            is NavigateToSignUp ->
                navigator.push(
                    SignUpScreen
                )

            is NavigateToHome -> {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        "Login efetuado com sucesso",
                        duration = SnackbarDuration.Short
                    )
                }
                navigator.pop()
            }

            else -> Unit
        }
        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) },
            topBar = {
                TopBar(showBackButton = true, showLogo = false) { navigator.pop() }
            }
        ) {
            Column(
                Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = CenterHorizontally
            ) {
                if (viewState.value.loading) {
                    Loading()
                } else {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier =
                            Modifier
                                .width(200.dp)
                                .height(200.dp),
                            painter = painterResourceFromPath("images/logonegativa.png"),
                            contentDescription = "logo"
                        )
                        Spacer(modifier = Modifier.height(20.dp))
                        Text(
                            text = "Login",
                            style = MaterialTheme.typography.headlineMedium
                        )
                        EditTextWithErrorMessage(
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                            error = viewState.value.emailError,
                            value = viewState.value.email,
                            onValueChanged = {
                                screenModel.setEvent(
                                    SignInContract.SignInEvent.EmailChanged(
                                        it
                                    )
                                )
                            },
                            keyboardType = KeyboardType.Email,
                            label = "Email"
                        )
                        EditTextWithErrorMessage(
                            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                            error = viewState.value.passwordError,
                            value = viewState.value.password,
                            onValueChanged = {
                                screenModel.setEvent(
                                    SignInContract.SignInEvent.PasswordChanged(
                                        it
                                    )
                                )
                            },
                            keyboardType = KeyboardType.Password,
                            label = "Senha",
                            rightIcon = {
                                Image(
                                    modifier =
                                    Modifier
                                        .width(30.dp)
                                        .height(30.dp).clickable {
                                            showPassword.value = !showPassword.value
                                        },
                                    painter =
                                    resourceFromShowPassword(showPassword),
                                    contentDescription = "logo"
                                )
                            },
                            visualTransformation =
                            if (showPassword.value) {
                                VisualTransformation.None
                            } else {
                                PasswordVisualTransformation()
                            }
                        )
                        DefaultButton("Entrar") {
                            screenModel.setEvent(SignInContract.SignInEvent.SignInButtonClicked)
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            "Novo usuário? Clique aqui para se cadastrar",
                            modifier =
                            Modifier.clickable {
                                screenModel.setEvent(
                                    SignInContract.SignInEvent.SignUpTextViewClicked
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}
