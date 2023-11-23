package com.thondigital.nc.presentation.ui.home

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.domain.models.HomeResponse
import com.thondigital.nc.domain.repository.HomeRepository
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val repository: HomeRepository,
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
