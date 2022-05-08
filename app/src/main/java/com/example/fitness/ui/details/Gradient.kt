package com.example.fitness.ui.details

import androidx.compose.ui.graphics.Brush
import com.example.fitness.ui.theme.Blue1
import com.example.fitness.ui.theme.Blue2
import com.example.fitness.ui.theme.Pink1
import com.example.fitness.ui.theme.Pink2

object Gradient {
    val blue = Brush.horizontalGradient(
        colors = listOf(
            Blue2, Blue1
        )
    )
    val pink = Brush.horizontalGradient(
        colors = listOf(
            Pink2, Pink1
        )
    )

}