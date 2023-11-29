package com.thondigital.nc.di

import androidx.annotation.OptIn
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.C.USAGE_MEDIA
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.thondigital.nc.network.model.NetworkConstants.RBN_STREAMING
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

@OptIn(UnstableApi::class)
actual fun streamingModule() =
    module {
        single {
            ExoPlayer.Builder(androidContext())
                .build()
                .apply {
                    val dataSourceFactory = DefaultHttpDataSource.Factory()
                    val media =
                        MediaItem.Builder()
                            .setUri(RBN_STREAMING)
                            .setLiveConfiguration(
                                MediaItem.LiveConfiguration.Builder()
                                    .build().apply {
                                        setAudioAttributes(
                                            AudioAttributes.Builder()
                                                .setUsage(USAGE_MEDIA)
                                                .setContentType(C.AUDIO_CONTENT_TYPE_SPEECH)
                                                .build(),
                                            true,
                                        )
                                        setForegroundMode(true)
                                    },
                            )
                            .build()
                    val source =
                        ProgressiveMediaSource.Factory(dataSourceFactory)
                            .createMediaSource(media)

                    setMediaSource(source)
                    prepare()
                }
        }
    }
