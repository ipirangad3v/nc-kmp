package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun EditTextWithErrorMessage(
    value: String,
    error: String,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    rightIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit
) {
    TextField(
        singleLine = true,
        isError = error.isNotBlank(),
        value = value,
        onValueChange = onValueChanged,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        trailingIcon = rightIcon,
        visualTransformation = visualTransformation,
        label = { Text(label) }
    )
    Spacer(modifier = Modifier.height(8.dp))
    if (error.isNotBlank()) {
        Text(
            text = error,
            color = Color.Red
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}
