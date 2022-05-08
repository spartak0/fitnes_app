package com.example.fitness.ui.reg

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness.R
import com.example.fitness.ui.Screen
import com.example.fitness.ui.details.EditText
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.details.Email
import com.example.fitness.ui.details.Password
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.myFontFamily

@Composable
fun FirstRegScreen(navController: NavController) {
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
        Spacer(modifier = Modifier.size(30.dp))
        FirstName()
        Spacer(modifier = Modifier.size(15.dp))
        LastName()
        Spacer(modifier = Modifier.size(15.dp))
        Email()
        Spacer(modifier = Modifier.size(15.dp))
        Password()
        Spacer(modifier = Modifier.size(180.dp))
        GradientView(
            text = stringResource(id = R.string.register),
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(dimensionResource(id = R.dimen.view_height))
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.SecondRegScreen.route) },
            gradient = Gradient.blue

        )
        Spacer(modifier = Modifier.size(15.dp))
        Row() {
            Text(
                text = stringResource(id = R.string.haveAccount),
                fontFamily = myFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = stringResource(R.string.login),
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
fun FirstName() {
    var firstName by remember { mutableStateOf("") }
    EditText(
        value = firstName,
        onValueChange = { newText -> firstName = newText },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholderText = "First Name",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null
            )
        },
    )
}

@Composable
fun LastName() {
    var lastName by remember { mutableStateOf("") }
    EditText(
        value = lastName,
        onValueChange = { newText -> lastName = newText },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholderText = "Last Name",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null
            )
        },
    )
}
