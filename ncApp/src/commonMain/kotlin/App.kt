import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.thondigital.nc.audioplayer.AudioPlayer
import com.thondigital.nc.audioplayer.rememberPlayerState
import com.thondigital.nc.presentation.NCTheme
import com.thondigital.nc.presentation.ui.home.HomeScreen

@Composable
fun App(
    darkTheme: Boolean,
    dynamicColor: Boolean
) {
    NCTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor
    ) {
        val playerState = rememberPlayerState()
        val player = remember { AudioPlayer(playerState) }

        Navigator(
            HomeScreen(
                playstate = playerState,
                onPause = { player.pause() }
            ) { player.play() }
        ) { navigator ->
            SlideTransition(navigator)
        }
        DisposableEffect(Unit) {
            onDispose {
                player.cleanUp()
            }
        }
    }
}
