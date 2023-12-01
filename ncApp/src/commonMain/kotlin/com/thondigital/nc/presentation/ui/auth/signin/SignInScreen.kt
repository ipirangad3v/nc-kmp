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
import androidx.compose.material3.Button
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.annotation.ExperimentalVoyagerApi
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.Init
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToHome
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToSignUp
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.ShowSnackBarError
import com.thondigital.nc.presentation.ui.auth.signup.SignUpScreen
import com.thondigital.nc.presentation.ui.components.Loading
import com.thondigital.nc.presentation.ui.components.TopBar
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

class SignInScreen : Screen {
    @OptIn(ExperimentalResourceApi::class, ExperimentalVoyagerApi::class)
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

            is NavigateToHome -> navigator.pop()
            else -> Unit
        }
        Column(
            Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = CenterHorizontally
        ) {
            if (viewState.value.loading) {
                Loading()
            } else {
                TopBar(true) { navigator.pop() }
                Image(
                    modifier =
                        Modifier
                            .width(200.dp)
                            .height(200.dp),
                    painter = painterResource("images/logonegativa.png"),
                    contentDescription = "logo"
                )
                Spacer(modifier = Modifier.height(20.dp))
                TextField(
                    singleLine = true,
                    isError = viewState.value.emailError.isNotBlank(),
                    value = viewState.value.email,
                    onValueChange = {
                        screenModel.setEvent(
                            SignInContract.SignInEvent.EmailChanged(
                                it
                            )
                        )
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                    label = { Text("Email") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (viewState.value.emailError.isNotBlank()) {
                    Text(
                        text = viewState.value.emailError,
                        color = androidx.compose.ui.graphics.Color.Red
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                TextField(
                    trailingIcon = {
                        Image(
                            modifier =
                                Modifier
                                    .width(30.dp)
                                    .height(30.dp).clickable {
                                        showPassword.value = !showPassword.value
                                    },
                            painter =
                                painterResource(
                                    if (showPassword.value) "images/eye.png" else "images/eyeoff.png"
                                ),
                            contentDescription = "logo"
                        )
                    },
                    visualTransformation =
                        if (
                            showPassword.value
                        ) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                    singleLine = true,
                    isError = viewState.value.passwordError.isNotBlank(),
                    value = viewState.value.password,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                    onValueChange = {
                        screenModel.setEvent(
                            SignInContract.SignInEvent.PasswordChanged(
                                it
                            )
                        )
                    },
                    label = { Text("Senha") }
                )
                Spacer(modifier = Modifier.height(8.dp))
                if (viewState.value.passwordError.isNotBlank()) {
                    Text(
                        text = viewState.value.passwordError,
                        color = androidx.compose.ui.graphics.Color.Red
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                }
                Button(
                    onClick = { screenModel.setEvent(SignInContract.SignInEvent.SignInButtonClicked) },
                    modifier = Modifier.height(50.dp)
                ) {
                    Text("Entrar")
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { screenModel.setEvent(SignInContract.SignInEvent.SignUpTextViewClicked) },
                    modifier = Modifier.height(50.dp)
                ) {
                    Text("Cadastrar")
                }
            }
        }
    }
}
