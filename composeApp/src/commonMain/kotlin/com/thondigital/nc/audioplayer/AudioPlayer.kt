package com.thondigital.nc.audioplayer

expect class AudioPlayer(playerState: PlayerState) {
    fun play()

    fun pause()

    fun cleanUp()
}
