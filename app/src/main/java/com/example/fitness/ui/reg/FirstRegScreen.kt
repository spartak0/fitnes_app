package com.example.fitness.ui.reg

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.fitness.R
import com.example.fitness.ui.Screen
import com.example.fitness.ui.details.EditText
import com.example.fitness.ui.details.Gradient
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
        firstName()
        Spacer(modifier = Modifier.size(15.dp))
        lastName()
        Spacer(modifier = Modifier.size(15.dp))
        email()
        Spacer(modifier = Modifier.size(15.dp))
        password()
        Spacer(modifier = Modifier.size(180.dp))
        GradientView(
            text = "Register",
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(55.dp)
                .fillMaxWidth()
                .clickable { navController.navigate(Screen.SecondRegScreen.route) },
            gradient = Gradient.blue

        )
        Spacer(modifier = Modifier.size(15.dp))
        Row() {
            Text(
                text = "Already have an account?",
                fontFamily = myFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
            Text(
                text = "Login",
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
fun password() {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }
    EditText(
        value = password,
        onValueChange = { newText -> password = newText },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholderText = "Password",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_lock),
                contentDescription = null
            )
        },
        keyboardType = KeyboardType.Password,
        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
        trailingIcon = {
            val image = if (passwordVisible)
                Icons.Filled.Visibility
            else Icons.Filled.VisibilityOff
            val description = if (passwordVisible) "Hide password" else "Show password"
            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                Icon(imageVector = image, description)
            }
        }
    )
}

@Composable
fun email() {
    var email by remember { mutableStateOf("") }
    EditText(
        value = email,
        onValueChange = { newText -> email = newText },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholderText = "Email",
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_message),
                contentDescription = null
            )
        },
        keyboardType = KeyboardType.Email
    )

}

@Composable
fun firstName() {
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
fun lastName() {
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
