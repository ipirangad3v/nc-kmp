package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thondigital.nc.presentation.ui.theme.blueMenuItem

@Composable
fun MenuItem(name: String, icon: String) {
    Column(
        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Box(
            contentAlignment = androidx.compose.ui.Alignment.Center,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .background(color = blueMenuItem, shape = RoundedCornerShape(size = 10.dp))
        ) {
            Text(icon, modifier = Modifier.background(Color.Red))
        }
        Text(
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
            text = name,
            style = TextStyle(
                fontSize = 12.sp,
                fontWeight = FontWeight(300),
                color = Color(0xFF494A59),
                textAlign = TextAlign.Center,
            )
        )
    }
}
