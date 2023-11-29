package com.thondigital.nc.audioplayer

import com.thondigital.nc.ObserverProtocol
import com.thondigital.nc.network.model.NetworkConstants.RBN_STREAMING_ENDPOINT
import kotlinx.cinterop.*
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.AVFoundation.*
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.*
import platform.darwin.NSEC_PER_SEC
import platform.darwin.NSObject

actual class AudioPlayer actual constructor(private val playerState: PlayerState) {
    private val avAudioPlayer: AVPlayer =
        AVPlayer().apply {
            val url = NSURL.URLWithString(RBN_STREAMING_ENDPOINT)
            val playerItem = url?.let { AVPlayerItem.playerItemWithURL(it) }
            replaceCurrentItemWithPlayerItem(playerItem)
            startTimeObserver()
        }
    private lateinit var timeObserver: Any

    @OptIn(ExperimentalForeignApi::class)
    private val observer: (CValue<CMTime>) -> Unit = {
        playerState.isPlaying = avAudioPlayer.timeControlStatus == AVPlayerTimeControlStatusPlaying
    }

    init {
        setUpAudioSession()
        playerState.isPlaying = avAudioPlayer.timeControlStatus == AVPlayerTimeControlStatusPlaying
    }

    actual fun play() {
        avAudioPlayer.play()
        playerState.isPlaying = true
    }

    actual fun pause() {
        avAudioPlayer.pause()
        playerState.isPlaying = false
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun setUpAudioSession() {
        try {
            val audioSession = AVAudioSession.sharedInstance()
            audioSession.setCategory(AVAudioSessionCategoryPlayback, null)
            audioSession.setActive(true, null)
        } catch (e: Exception) {
            println("Error setting up audio session: ${e.message}")
        }
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun startTimeObserver() {
        val interval = CMTimeMakeWithSeconds(1.0, NSEC_PER_SEC.toInt())
        timeObserver = avAudioPlayer.addPeriodicTimeObserverForInterval(interval, null, observer)
        NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemDidPlayToEndTimeNotification,
            `object` = avAudioPlayer.currentItem,
            queue = NSOperationQueue.mainQueue,
            usingBlock = {
            },
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    private fun stop() {
        if (::timeObserver.isInitialized) avAudioPlayer.removeTimeObserver(timeObserver)
        avAudioPlayer.pause()
        avAudioPlayer.currentItem?.seekToTime(CMTimeMakeWithSeconds(0.0, NSEC_PER_SEC.toInt()))
    }

    actual fun cleanUp() {
        stop()
    }
}

@OptIn(ExperimentalForeignApi::class)
class AudioObserver : ObserverProtocol, NSObject() {
    @OptIn(ExperimentalForeignApi::class)
    override fun observeValueForKeyPath(
        keyPath: String?,
        ofObject: Any?,
        change: Map<Any?, *>?,
        context: COpaquePointer?,
    ) {
        println("keyPath $keyPath")
        println("ofObject $ofObject")
        println("change $change")
        println("context $context")
    }
}
