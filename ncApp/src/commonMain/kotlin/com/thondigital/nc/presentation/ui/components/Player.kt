package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Player(
    modifier: Modifier,
    isPlaying: Boolean,
    onPause: () -> Unit,
    onPlay: () -> Unit
) {
    Row(
        modifier = modifier.background(Color.Blue).padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            modifier =
                Modifier
                    .width(100.dp)
                    .height(100.dp),
            painter = painterResource("images/boasnovas.jpeg"),
            contentDescription = "logo"
        )
        Column {
            Text(
                text = "Rádio Boas Novas",
                color = Color.White
            )
            Text(
                text = "104,9 FM",
                color = Color.White
            )
        }
        Controls(
            modifier = Modifier.padding(vertical = 10.dp),
            isPlaying = isPlaying,
            onPause = onPause,
            onPlay = onPlay
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
private fun Controls(
    modifier: Modifier,
    isPlaying: Boolean,
    onPause: () -> Unit,
    onPlay: () -> Unit
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(15.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(Modifier.size(70.dp)) {
            Button(
                onClick = { if (isPlaying) onPause() else onPlay() },
                modifier = Modifier.size(70.dp),
                shape = RoundedCornerShape(25.dp),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue)
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
                    tint = Color.White
                )
            }
        }
    }
}
