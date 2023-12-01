package com.thondigital.nc.network.source

import com.thondigital.nc.data.source.network.streaming.StreamingDataSource
import com.thondigital.nc.network.apiservice.StreamingApiService
import kotlinx.coroutines.flow.Flow

class StreamingDataSourceImpl(
    private val streamingApiService: StreamingApiService
) : StreamingDataSource {
    override suspend fun getRbnStream(): Flow<ByteArray> = streamingApiService.startRbnStream()

    override suspend fun stopRbnStream() = streamingApiService.stopRbnStream()
}
