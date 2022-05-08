package com.example.fitness.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness.R
import com.example.fitness.ui.details.Email
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.details.Password
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.myFontFamily

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(R.string.heyThere),
            style = Typography.body2,
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = stringResource(R.string.createAccount),
            style = Typography.body1,
            modifier = Modifier.height(30.dp)
        )
        Spacer(modifier = Modifier.size(15.dp))
        Email()
        Spacer(modifier = Modifier.size(15.dp))
        Password()
        Spacer(modifier = Modifier.size(350.dp))
        GradientView(
            text = "Register",
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(dimensionResource(id = R.dimen.view_height))
                .fillMaxWidth()
                .clickable {},
            gradient = Gradient.blue

        )
        Spacer(modifier = Modifier.size(15.dp))
        Row() {
            Text(
                text = stringResource(R.string.dontHaveAccount),
                fontFamily = myFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(R.string.register),
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