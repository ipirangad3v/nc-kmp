package com.thondigital.nc

import App
import android.content.Context
import android.media.AudioManager
import android.media.audiofx.LoudnessEnhancer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.OptIn
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import com.google.firebase.Firebase
import com.google.firebase.initialize
import org.koin.android.ext.android.inject
import kotlin.math.log10
import kotlin.math.roundToInt

class MainActivity : ComponentActivity(), AudioManager.OnAudioFocusChangeListener {
    private val player: ExoPlayer by inject()

    private val audioManager by lazy {
        getSystemService(Context.AUDIO_SERVICE) as AudioManager
    }

    private lateinit var loudnessEnhancer: LoudnessEnhancer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Firebase.initialize(this)
        setContent {
            var isPlaying by rememberSaveable { mutableStateOf(false) }
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
            ) {
                App(
//                    darkTheme = isSystemInDarkTheme(),
                    darkTheme = true,
                    dynamicColor = true,
                )
            }
        }
        audioManager.requestAudioFocus(
            this,
            AudioManager.STREAM_MUSIC,
            AudioManager.AUDIOFOCUS_GAIN,
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()
        loudnessEnhancer.release()
    }

    override fun onAudioFocusChange(focusState: Int) {
        when (focusState) {
            AudioManager.AUDIOFOCUS_LOSS_TRANSIENT -> {}
            AudioManager.AUDIOFOCUS_GAIN -> {
                player.play()
                enhanceAudio()
            }
        }
    }

    @OptIn(UnstableApi::class)
    private fun enhanceAudio() {
        loudnessEnhancer =
            LoudnessEnhancer(player.audioSessionId).apply {
                val audioPct = 1.2
                val gainMB = (log10(audioPct) * 10000).roundToInt()
                setTargetGain(gainMB)
                enabled = true
            }
    }

    override fun onResume() {
        super.onResume()
        player.play()
    }
}
