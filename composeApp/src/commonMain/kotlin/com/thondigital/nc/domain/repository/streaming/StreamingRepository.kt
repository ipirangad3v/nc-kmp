package com.thondigital.nc.domain.repository.streaming

import kotlinx.coroutines.flow.Flow

interface StreamingRepository {
    suspend fun getRbnStream(): Flow<ByteArray>

    suspend fun stopRbnStream()
}
