package com.example.fitness.ui.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.fitness.R
import com.example.fitness.ui.main.GradientBtn

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
        GradientBtn(
            text = "Get Started",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 40.dp)
        ) {
            //todo
        }
    }
}