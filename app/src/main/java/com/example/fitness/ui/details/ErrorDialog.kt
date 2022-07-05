package com.example.fitness.ui.details

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.Typography
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.fitness.R
import com.example.fitness.ui.theme.Orange
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.myFontFamily


@Composable
fun errorDialog(text: String, click: () -> Unit) {
    var openErrorDialog by remember { mutableStateOf(true) }
    if (openErrorDialog) {
        AlertDialog(
            onDismissRequest = {
                openErrorDialog = false
                click()
            },
            title = { Text(text = stringResource(R.string.warning),fontWeight = FontWeight.Bold) },
            text = { Text(text = text, fontWeight = FontWeight.Medium) },
            confirmButton = {
                Button(onClick = {
                    openErrorDialog = false
                    click()
                }) {
                    Text(text = stringResource(id = R.string.ok), fontSize = 16.sp)
                }
            }
        )
    }
}