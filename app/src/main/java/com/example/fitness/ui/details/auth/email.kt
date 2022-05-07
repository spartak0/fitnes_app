package com.example.fitness.ui.details

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.fitness.R

@Composable
fun email() {
    var email by remember { mutableStateOf("") }
    EditText(
        value = email,
        onValueChange = { newText -> email = newText },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholderText = "Email",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_message),
                contentDescription = null
            )
        },
        keyboardType = KeyboardType.Email
    )

}