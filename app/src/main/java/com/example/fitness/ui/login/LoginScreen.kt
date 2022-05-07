package com.example.fitness.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.details.email
import com.example.fitness.ui.details.password
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.myFontFamily

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = "Hey there,",
            style = Typography.body2,
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = "Create an Account",
            style = Typography.body1,
            modifier = Modifier.height(30.dp)
        )
        Spacer(modifier = Modifier.size(15.dp))
        email()
        Spacer(modifier = Modifier.size(15.dp))
        password()
        Spacer(modifier = Modifier.size(350.dp))
        GradientView(
            text = "Register",
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(55.dp)
                .fillMaxWidth()
                .clickable {},
            gradient = Gradient.blue

        )
        Spacer(modifier = Modifier.size(15.dp))
        Row() {
            Text(
                text = "Don't have an account yet?",
                fontFamily = myFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = "Register",
                modifier = Modifier.padding(start = 5.dp),
                color = Color.Blue,
                fontFamily = myFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )

        }

    }
}

@Composable
@Preview
fun LoginPreview() {
    LoginScreen()
}