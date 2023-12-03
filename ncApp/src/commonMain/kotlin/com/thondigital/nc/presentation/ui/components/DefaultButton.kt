package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.thondigital.nc.presentation.ui.theme.Yellow
import com.thondigital.nc.presentation.ui.theme.primaryBlue

@Composable
fun DefaultButton(text: String, onClick: () -> Unit) {
    Button(
        modifier = Modifier.wrapContentSize(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = primaryBlue)
    ) {
        Text(text, color = Yellow, fontSize = 16.sp)
    }

}
