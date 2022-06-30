package com.example.fitness.ui.details.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness.R

@Composable
fun waitVerificationDialog(onClickOk: () -> Unit, onClickCancel: () -> Unit) {

    AlertDialog(onDismissRequest = {},
        title = {
            Text(
                text = stringResource(R.string.verifyEmail),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        buttons = {
            Row(
                modifier = Modifier.width(300.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(onClick = {
                    onClickOk()
                }) {
                    Text(text = stringResource(id = R.string.ok), fontSize = 16.sp)
                }
                Button(onClick = {
                    onClickCancel()
                }) {
                    Text(text = stringResource(id = R.string.cancel), fontSize = 16.sp)
                }
            }

        })
}