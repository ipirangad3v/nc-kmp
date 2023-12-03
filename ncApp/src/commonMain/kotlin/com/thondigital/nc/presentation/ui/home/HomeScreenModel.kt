package com.thondigital.nc.presentation.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.audioplayer.AudioPlayer
import com.thondigital.nc.audioplayer.PlayerState
import com.thondigital.nc.data.remote.responses.HomeResponse
import com.thondigital.nc.domain.usecase.account.detail.GetAccountUseCase
import com.thondigital.nc.domain.usecase.auth.status.AuthenticationStatusUseCase
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.remoteconfig.FirebaseRemoteConfigValue
import dev.gitlive.firebase.remoteconfig.remoteConfig
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val authenticationStatusUseCase: AuthenticationStatusUseCase,
    private val getAccountUseCase: GetAccountUseCase
) : StateScreenModel<HomeScreenModel.State>(State.Init) {
    private val playerState = PlayerState()
    var isPlaying: Boolean by mutableStateOf(playerState.isPlaying)
        private set
    private val player = AudioPlayer(playerState)

    private val remoteConfig = Firebase.remoteConfig
    var showRadioState by mutableStateOf(false)
        private set

    init {
        screenModelScope.launch {
            remoteConfig.fetchAndActivate()
            "show_radio_at_home".let {
                setRemoteConfig(it, remoteConfig.getValue(it))
            }
        }
    }

    private fun setRemoteConfig(
        key: String,
        value: FirebaseRemoteConfigValue
    ) {
        when (key) {
            "show_radio_at_home" -> {
                showRadioState =
                    value.asBoolean()
            }
        }
    }

    sealed class State {
        data object Init : State()

        data object Loading : State()

        data class Result(
            val result: HomeResponse
        ) : State()
    }

    fun play() {
        player.play()
        isPlaying = true
    }

    fun pause() {
        player.pause()
        isPlaying = false
    }

    fun getHome() {
        screenModelScope.launch {
            val userAuthenticated = authenticationStatusUseCase()
            val user = getAccountUseCase()

            mutableState.value = State.Loading
            mutableState.value =
                State.Result(
                    HomeResponse(
                        emptyList(),
                        userAuthenticated,
                        user
                    )
                )
        }
    }
}
