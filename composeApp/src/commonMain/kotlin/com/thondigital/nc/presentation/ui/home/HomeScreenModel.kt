package com.thondigital.nc.presentation.ui.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.data.remote.responses.HomeResponse
import com.thondigital.nc.domain.repository.FirestoreRepository
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: FirestoreRepository,
) : StateScreenModel<HomeScreenModel.State>(State.Init) {
    sealed class State {
        data object Init : State()

        data object Loading : State()

        data class Result(
            val result: HomeResponse,
        ) : State()
    }

    fun getHome() {
        screenModelScope.launch {
            mutableState.value = State.Loading
            mutableState.value = State.Result(repository.getHome())
        }
    }
}
