package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.thondigital.nc.presentation.ui.theme.primaryBlue
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun TopBar(
    showBackButton: Boolean = false,
    onBackClick: () -> Unit = {},
) {
    Box(
        modifier =
            Modifier
                .fillMaxWidth()
                .height(60.dp).padding(8.dp),
    ) {
        if (showBackButton) {
            Box(
                modifier =
                    Modifier
                        .height(60.dp)
                        .padding(8.dp)
                        .clickable { onBackClick() },
            ) {
                Text(
                    text = "Voltar",
                    modifier = Modifier.clickable { onBackClick() },
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                modifier =
                    Modifier
                        .width(50.dp)
                        .height(50.dp),
                painter = painterResource("images/logonegativa.png"),
                contentDescription = "logo",
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Nação da Cruz", color = primaryBlue)
        }
    }
}
