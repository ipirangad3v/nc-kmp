package com.thondigital.nc.audioplayer

import android.os.Handler
import android.os.Looper
import androidx.media3.common.AudioAttributes
import androidx.media3.common.C
import androidx.media3.common.C.USAGE_MEDIA
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import com.thondigital.nc.NCApp
import com.thondigital.nc.network.model.NetworkConstants.RBN_STREAMING_ENDPOINT

actual class AudioPlayer actual constructor(private val playerState: PlayerState) : Runnable {
    private val handler = Handler(Looper.getMainLooper())

    private val mediaPlayer =
        ExoPlayer.Builder(NCApp.appContext)
            .build()
            .apply {
                val dataSourceFactory = DefaultHttpDataSource.Factory()
                val media =
                    MediaItem.Builder()
                        .setUri(RBN_STREAMING_ENDPOINT)
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
    private val listener =
        object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_IDLE -> {
                    }

                    Player.STATE_ENDED -> {
                        if (playerState.isPlaying) {
                        }
                    }

                    Player.STATE_READY -> {
                    }
                }
            }

            override fun onIsPlayingChanged(isPlaying: Boolean) {
                playerState.isPlaying = isPlaying
                if (isPlaying) {
                    scheduleUpdate()
                } else {
                    stopUpdate()
                }
            }
        }

    private fun stopUpdate() {
        handler.removeCallbacks(this)
    }

    private fun scheduleUpdate() {
        stopUpdate()
        handler.postDelayed(this, 100)
    }

    actual fun play() {
        mediaPlayer.play()
        playerState.isPlaying = true
    }

    actual fun pause() {
        mediaPlayer.pause()
        playerState.isPlaying = false
    }

    override fun run() {
        handler.postDelayed(this, 1000)
    }

    actual fun cleanUp() {
        mediaPlayer.release()
        mediaPlayer.removeListener(listener)
    }
}
