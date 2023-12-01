package com.thondigital.nc.audioplayer

import com.thondigital.nc.ObserverProtocol
import com.thondigital.nc.network.model.NetworkConstants.RBN_STREAMING_ENDPOINT
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFAudio.AVAudioSession
import platform.AVFAudio.AVAudioSessionCategoryPlayback
import platform.AVFAudio.setActive
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.AVPlayerItem
import platform.AVFoundation.AVPlayerItemDidPlayToEndTimeNotification
import platform.AVFoundation.AVPlayerTimeControlStatusPlaying
import platform.AVFoundation.addPeriodicTimeObserverForInterval
import platform.AVFoundation.currentItem
import platform.AVFoundation.pause
import platform.AVFoundation.play
import platform.AVFoundation.removeTimeObserver
import platform.AVFoundation.replaceCurrentItemWithPlayerItem
import platform.AVFoundation.seekToTime
import platform.AVFoundation.timeControlStatus
import platform.CoreMedia.CMTime
import platform.CoreMedia.CMTimeMakeWithSeconds
import platform.Foundation.NSNotificationCenter
import platform.Foundation.NSOperationQueue
import platform.Foundation.NSURL
import platform.darwin.NSEC_PER_SEC
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual class AudioPlayer actual constructor(private val playerState: PlayerState) {
    private val avAudioPlayer: AVPlayer =
        AVPlayer().apply {
            val url = NSURL.URLWithString(RBN_STREAMING_ENDPOINT)
            val playerItem = url?.let { AVPlayerItem.playerItemWithURL(it) }
            replaceCurrentItemWithPlayerItem(playerItem)
            startTimeObserver()
        }
    private lateinit var timeObserver: Any

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

    private fun setUpAudioSession() {
        try {
            val audioSession = AVAudioSession.sharedInstance()
            audioSession.setCategory(AVAudioSessionCategoryPlayback, null)
            audioSession.setActive(true, null)
        } catch (e: Exception) {
            println("Error setting up audio session: ${e.message}")
        }
    }

    private fun startTimeObserver() {
        val interval = CMTimeMakeWithSeconds(1.0, NSEC_PER_SEC.toInt())
        timeObserver = avAudioPlayer.addPeriodicTimeObserverForInterval(interval, null, observer)
        NSNotificationCenter.defaultCenter.addObserverForName(
            name = AVPlayerItemDidPlayToEndTimeNotification,
            `object` = avAudioPlayer.currentItem,
            queue = NSOperationQueue.mainQueue,
            usingBlock = {
            }
        )
    }

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
    @ExperimentalForeignApi
    override fun observeValueForKeyPath(
        keyPath: String?,
        ofObject: Any?,
        change: Map<Any?, *>?,
        context: COpaquePointer?
    ) {
        println("keyPath $keyPath")
        println("ofObject $ofObject")
        println("change $change")
        println("context $context")
    }
}
