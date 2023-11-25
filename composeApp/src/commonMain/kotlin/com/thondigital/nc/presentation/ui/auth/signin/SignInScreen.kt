package com.thondigital.nc.presentation.ui.auth.signin

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.Init
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToHome
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.NavigateToSignUp
import com.thondigital.nc.presentation.ui.auth.signin.SignInContract.SignInViewEffect.ShowSnackBarError
import com.thondigital.nc.presentation.ui.auth.signup.SignUpScreen
import kotlinx.coroutines.launch

object SignInScreen : Screen {
    @Composable
    override fun Content() {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = getScreenModel<SignInScreenModel>()

        val viewState = screenModel.viewState.collectAsState()
        val viewEffect = screenModel.viewEffect.collectAsState(Init)

        when (viewEffect.value) {
            is ShowSnackBarError ->
                scope.launch {
                    snackbarHostState.showSnackbar(
                        (viewEffect.value as ShowSnackBarError).message,
                        duration = SnackbarDuration.Short,
                    )
                }

            is NavigateToSignUp ->
                navigator.push(
                    SignUpScreen(),
                )

            is NavigateToHome -> navigator.pop()
            else -> Unit
        }

        Button(onClick = {
            navigator.pop()
        }) {
            Text("SignInScreen")
        }
    }
}
