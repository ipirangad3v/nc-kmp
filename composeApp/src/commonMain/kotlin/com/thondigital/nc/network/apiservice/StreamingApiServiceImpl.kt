package com.thondigital.nc.network.apiservice

import com.thondigital.nc.network.model.NetworkConstants.RBN_STREAMING_ENDPOINT
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.request.prepareGet
import io.ktor.http.contentType
import io.ktor.util.InternalAPI
import io.ktor.utils.io.ByteReadChannel
import io.ktor.utils.io.core.isEmpty
import io.ktor.utils.io.core.readBytes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StreamingApiServiceImpl : StreamingApiService {
    @OptIn(InternalAPI::class)
    private val streamingFlow =
        flow {
            client.prepareGet(RBN_STREAMING_ENDPOINT) {
                contentType(
                    io.ktor.http.ContentType.parse("audio/mpeg"),
                )
            }.execute { httpResponse ->
                val channel: ByteReadChannel = httpResponse.content
                while (!channel.isClosedForRead) {
                    val packet = channel.readPacket(300)
                    while (!packet.isEmpty) {
                        val bytes = packet.readBytes()
                        emit(bytes)
                    }
                }
            }
        }
    private val client =
        HttpClient(CIO) {
            install(Logging) {
                logger = Logger.SIMPLE
                level = LogLevel.HEADERS
            }
        }

    override suspend fun startRbnStream(): Flow<ByteArray> = streamingFlow

    override suspend fun stopRbnStream() {
        client.close()
    }
}
