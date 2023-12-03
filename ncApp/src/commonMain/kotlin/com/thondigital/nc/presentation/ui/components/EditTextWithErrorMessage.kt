package com.thondigital.nc.presentation.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.thondigital.nc.presentation.ui.theme.BluePrimaryDark

@Composable
fun EditTextWithErrorMessage(
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    value: String,
    error: String,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    rightIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    onValueChanged: (String) -> Unit
) {
    val textFieldColors =
        OutlinedTextFieldDefaults.colors(
            cursorColor = BluePrimaryDark,
            focusedBorderColor = BluePrimaryDark,
            unfocusedBorderColor = Color.Black
        )
    OutlinedTextField(
        keyboardOptions = keyboardOptions.copy(keyboardType = keyboardType),
        singleLine = true,
        isError = error.isNotBlank(),
        value = value,
        onValueChange = onValueChanged,
        trailingIcon = rightIcon,
        visualTransformation = visualTransformation,
        label = { Text(label) },
        colors = textFieldColors
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
