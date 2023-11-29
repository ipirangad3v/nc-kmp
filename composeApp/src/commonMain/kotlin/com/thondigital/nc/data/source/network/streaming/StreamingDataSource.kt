package com.thondigital.nc.data.source.network.streaming

import kotlinx.coroutines.flow.Flow

interface StreamingDataSource {
    suspend fun getRbnStream(): Flow<ByteArray>

    suspend fun stopRbnStream()
}
