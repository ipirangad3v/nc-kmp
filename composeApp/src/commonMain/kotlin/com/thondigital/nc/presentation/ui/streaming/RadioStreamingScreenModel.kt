package com.thondigital.nc.presentation.ui.streaming

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.thondigital.nc.domain.usecase.streaming.radiostream.StartRadioStreamUseCase
import com.thondigital.nc.domain.usecase.streaming.radiostream.StopRadioStreamUseCase
import kotlinx.coroutines.launch

class RadioStreamingScreenModel(
    private val radioStreamUseCase: StartRadioStreamUseCase,
    private val stopRadioStreamUseCase: StopRadioStreamUseCase,
) : ScreenModel {
    init {
        screenModelScope.launch {
            radioStreamUseCase().collect {
                println("$it")
            }
        }
    }

    fun stopRadioStream() {
        screenModelScope.launch {
            stopRadioStreamUseCase()
        }
    }
}
