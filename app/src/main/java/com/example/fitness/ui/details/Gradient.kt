package com.example.fitness.ui.details

import androidx.compose.ui.graphics.Brush
import com.example.fitness.ui.theme.blue_1
import com.example.fitness.ui.theme.blue_2
import com.example.fitness.ui.theme.pink_1
import com.example.fitness.ui.theme.pink_2

object Gradient {
    val blue = Brush.horizontalGradient(
        colors = listOf(
            blue_2, blue_1
        )
    )
    val pink = Brush.horizontalGradient(
        colors = listOf(
            pink_2, pink_1
        )
    )

}