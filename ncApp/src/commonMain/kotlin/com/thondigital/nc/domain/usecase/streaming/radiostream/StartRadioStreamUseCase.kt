package com.thondigital.nc.domain.usecase.streaming.radiostream

import com.thondigital.nc.domain.repository.streaming.StreamingRepository

class StartRadioStreamUseCase(private val streamingRepository: StreamingRepository) {
    suspend operator fun invoke() = streamingRepository.getRbnStream()
}
