package com.thondigital.nc.presentation.ui.account

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.data.remote.responses.AccountResponse
import com.thondigital.nc.domain.usecase.account.delete.DeleteAccountUseCase
import com.thondigital.nc.domain.usecase.account.detail.GetAccountUseCase
import com.thondigital.nc.domain.usecase.account.detail.SyncAccountUseCase
import com.thondigital.nc.domain.usecase.auth.status.AuthenticationStatusUseCase
import kotlinx.coroutines.launch

class AccountScreenModel(
    private val authenticationStatusUseCase: AuthenticationStatusUseCase,
    private val getAccountUseCase: GetAccountUseCase,
    private val deleteAccountUseCase: DeleteAccountUseCase,
    private val syncAccountUseCase: SyncAccountUseCase
) : StateScreenModel<AccountScreenModel.State>(State.Loading) {

    sealed class State {
        data object Loading : State()

        data class Result(
            val result: AccountResponse
        ) : State()
    }

    init {
        getAccount()
    }

    private fun getAccount() {
        screenModelScope.launch {
            val userAuthenticated = authenticationStatusUseCase()
            val user = getAccountUseCase()

            val isUserSync = user?.username?.isNotEmpty() == true && user.email.isNotEmpty()

            if (!isUserSync) {
                syncAccountUseCase()
            }

            mutableState.value = State.Loading
            mutableState.value =
                State.Result(
                    AccountResponse(
                        userAuthenticated,
                        user
                    )
                )
        }
    }

    fun deleteAccount() {
        screenModelScope.launch {
            val userAuthenticated = authenticationStatusUseCase()
            val user = getAccountUseCase()
            mutableState.value = State.Loading
            mutableState.value =
                State.Result(
                    AccountResponse(
                        userAuthenticated,
                        user,
                        message = deleteAccountUseCase().toString()
                    )
                )
        }
    }

}
