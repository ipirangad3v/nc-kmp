import androidx.compose.runtime.Composable
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
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


        Navigator(
            HomeScreen
        ) { navigator ->
            SlideTransition(navigator)
        }
    }
}
