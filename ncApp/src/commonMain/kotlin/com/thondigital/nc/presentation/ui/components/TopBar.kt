package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.thondigital.nc.presentation.ui.theme.primaryBlue
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    showBackButton: Boolean = false,
    showLogo: Boolean = true,
    onBackClick: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                if (showLogo) {
                    Image(
                        modifier =
                            Modifier
                                .width(30.dp)
                                .height(30.dp),
                        painter = painterResource("images/logonegativa.png"),
                        contentDescription = "logo"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(text = "Nação da Cruz", color = primaryBlue)
                    Spacer(modifier = Modifier.width(48.dp))
                }
            }
        },
        navigationIcon = {
            if (showBackButton) {
                IconButton(onClick = onBackClick) {
                    Icon(Icons.Rounded.ArrowBack, contentDescription = "Back")
                }
            }
        },
        colors =
            topAppBarColors(
                containerColor = Color.Transparent,
                scrolledContainerColor =
                    MaterialTheme.colorScheme.surfaceColorAtElevation(
                        elevation = 8.dp
                    ),
                titleContentColor = MaterialTheme.colorScheme.onBackground
            )
    )
}
