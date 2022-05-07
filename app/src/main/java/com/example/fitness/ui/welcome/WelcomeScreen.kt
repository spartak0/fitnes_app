package com.example.fitness.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitness.R
import com.example.fitness.ui.Screen
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.main.GradientView

@Composable
fun WelcomeScreen(navController: NavController) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        val logo = painterResource(id = R.drawable.ic_fitnessx)
        Image(
            painter = logo,
            contentDescription = "",
            modifier = Modifier
                .align(Alignment.Center)
                .padding(bottom = 100.dp)
        )
        Spacer(modifier = Modifier.size(20.dp))
        GradientView(
            text = "Get Started",
            gradient = Gradient.blue,
            modifier = Modifier
                .fillMaxWidth()
                .height(55.dp)
                .align(Alignment.BottomCenter)
                .offset(y= (-30).dp)
                .padding(horizontal = 30.dp)
                .clickable { navController.navigate(Screen.FirstRegScreen.route) }
        )
    }
}