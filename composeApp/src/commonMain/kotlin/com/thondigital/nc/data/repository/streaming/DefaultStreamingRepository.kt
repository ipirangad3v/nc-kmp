package com.thondigital.nc.data.repository.streaming

import com.thondigital.nc.data.source.network.streaming.StreamingDataSource
import com.thondigital.nc.domain.repository.streaming.StreamingRepository
import kotlinx.coroutines.flow.Flow

class DefaultStreamingRepository(
    private val streamingDataSource: StreamingDataSource,
) : StreamingRepository {
    override suspend fun getRbnStream(): Flow<ByteArray> = streamingDataSource.getRbnStream()

    override suspend fun stopRbnStream() = streamingDataSource.stopRbnStream()
}
