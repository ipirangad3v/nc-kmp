package com.thondigital.nc.presentation.ui.account

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.thondigital.nc.domain.model.AccountDomainModel
import com.thondigital.nc.presentation.ui.components.DefaultButton
import com.thondigital.nc.presentation.ui.components.Loading
import com.thondigital.nc.presentation.ui.components.TopBar
import kotlinx.coroutines.launch

object AccountScreen : Screen {
    @Composable
    override fun Content() {

        val scope = rememberCoroutineScope()

        val navigator = LocalNavigator.currentOrThrow
        val screenModel = navigator.getNavigatorScreenModel<AccountScreenModel>()
        val state by screenModel.state.collectAsState()
        val snackbarHostState = remember { SnackbarHostState() }

        when (state) {
            is AccountScreenModel.State.Loading -> Loading()
            is AccountScreenModel.State.Result ->
                AnimatedVisibility(true) {
                    val result = (state as AccountScreenModel.State.Result).result
                    Scaffold(
                        snackbarHost = { SnackbarHost(snackbarHostState) },
                        topBar = {
                            TopBar(showBackButton = true, showLogo = true) { navigator.pop() }
                        }
                    ) {
                        result.message?.let {
                            scope.launch {
                                snackbarHostState.showSnackbar(it)
                            }
                        }
                        result.account?.let {
                            AccountDetails(it, screenModel)
                        }
                    }
                }
        }


    }

    @Composable
    private fun AccountDetails(account: AccountDomainModel, screenModel: AccountScreenModel) {
        Column {
            Text(text = "Account Details")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "ID: ${account.pk}")
            Text(text = "Email: ${account.email}")
            Text(text = "Username: ${account.username}")
            Text(text = "Is Admin: ${account.isAdmin}")
            DefaultButton(
                text = "Deletar Conta"
            ) {
                screenModel.deleteAccount()
            }
        }
    }

}
