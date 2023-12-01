package com.thondigital.nc.network.apiservice

import kotlinx.coroutines.flow.Flow

interface StreamingApiService {
    suspend fun startRbnStream(): Flow<ByteArray>

    suspend fun stopRbnStream()
}
