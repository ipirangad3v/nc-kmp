package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thondigital.nc.presentation.ui.theme.BluePrimaryDark
import com.thondigital.nc.presentation.ui.theme.Yellow
import com.thondigital.nc.presentation.ui.theme.primaryBlue
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun RadioPlayer(
    isPlaying: Boolean,
    onPause: () -> Unit,
    onPlay: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier =
            modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        color = primaryBlue
    ) {
        Row(
            modifier = Modifier.fillMaxSize().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Assuming you have a drawable resource for your logo
            Image(
                modifier =
                    Modifier
                        .width(100.dp)
                        .height(100.dp),
                painter = painterResource("images/boasnovas.jpeg"),
                contentDescription = "logo"
            )
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Rádio Boas Novas",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )
                Text(
                    text = "104,9 FM",
                    color = Color.White.copy(alpha = 0.7f),
                    fontWeight = FontWeight.Light,
                    fontSize = 14.sp
                )
            }
            Controls(
                isPlaying = isPlaying,
                onPause = onPause,
                onPlay = onPlay
            )
        }
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Controls(
    isPlaying: Boolean,
    onPause: () -> Unit,
    onPlay: () -> Unit
) {
    Box(Modifier.size(50.dp)) {
        Button(
            onClick = { if (isPlaying) onPause() else onPlay() },
            modifier = Modifier.wrapContentSize(Alignment.Center),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(backgroundColor = Yellow)
        ) {
            Icon(
                painter =
                    if (isPlaying) {
                        painterResource("images/pause.png")
                    } else {
                        painterResource(
                            "images/play.png"
                        )
                    },
                contentDescription = "",
                modifier = Modifier.size(50.dp),
                tint = BluePrimaryDark
            )
        }
    }
}
