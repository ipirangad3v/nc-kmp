import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.thondigital.nc.presentation.NCTheme

@Composable
fun App(darkTheme: Boolean, dynamicColor: Boolean) {
    NCTheme(
        darkTheme = darkTheme,
        dynamicColor = dynamicColor,
        content = {
        }
    )
}