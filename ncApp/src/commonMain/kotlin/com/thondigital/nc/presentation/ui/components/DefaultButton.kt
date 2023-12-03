package com.thondigital.nc.presentation.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.thondigital.nc.presentation.ui.theme.Yellow
import com.thondigital.nc.presentation.ui.theme.primaryBlue

@Composable
fun DefaultButton(text: String, onClick: () -> Unit) {
    Button(onClick = onClick, colors = ButtonDefaults.buttonColors(containerColor = primaryBlue)) {
        Text(text, color = Yellow)
    }

}
