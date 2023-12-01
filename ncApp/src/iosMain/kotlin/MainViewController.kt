import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

// ktlint-disable standard:function-naming
@Suppress("unused")
fun MainViewController() =
    ComposeUIViewController {
        val isDarkTheme =
            UIScreen.mainScreen.traitCollection.userInterfaceStyle ==
                UIUserInterfaceStyle.UIUserInterfaceStyleDark
        App(
            darkTheme = isDarkTheme,
            dynamicColor = false
        )
    }
// ktlint-enable standard:function-naming
