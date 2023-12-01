import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIScreen
import platform.UIKit.UIUserInterfaceStyle

@Suppress("unused")
// ktlint-disable standard:function-naming
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
