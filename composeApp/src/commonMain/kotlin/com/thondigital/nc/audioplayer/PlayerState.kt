package com.thondigital.nc.audioplayer

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Stable
class PlayerState {
    var isPlaying by mutableStateOf(false)
        internal set
}

@Composable
fun rememberPlayerState(): PlayerState {
    return remember {
        PlayerState()
    }
}
