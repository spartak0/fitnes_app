package com.example.fitness.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitness.ui.theme.blue_1
import com.example.fitness.ui.theme.blue_2

val gradient = Brush.horizontalGradient(
    colors = listOf(
        blue_2, blue_1
    )
)

@Composable
fun GradientBtn(text: String, modifier: Modifier, onClick: () -> Unit) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = 30.dp)
            .clickable { onClick() }, shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(brush = gradient),
            contentAlignment = Alignment.Center
        ) {
            Text(text = text, color = Color.White, style = MaterialTheme.typography.button)
        }
    }

}

