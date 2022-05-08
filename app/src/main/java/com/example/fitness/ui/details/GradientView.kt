package com.example.fitness.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.fitness.ui.theme.Typography


@Composable
fun GradientView(text: String, modifier: Modifier, gradient: Brush) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(20.dp),
        elevation = 5.dp
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(brush = gradient),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = Color.White,
                style= Typography.body1
            )
        }
    }

}

