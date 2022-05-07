package com.example.fitness.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitness.R
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.main.GradientView

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
    ) {
        val logo = painterResource(id = R.drawable.ic_fitnessx)
        Image(
            painter = logo,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 100.dp)
        )
        GradientView(
            text = "Get Started",
            gradient = Gradient.blue,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
                .clickable { }
        )
    }
}