package com.example.fitness.ui.reg

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fitness.R
import com.example.fitness.ui.main.GradientBtn
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.gray_light
import com.example.fitness.ui.theme.myFontFamily

@Composable
fun RegScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var firstName by remember {
            mutableStateOf("")
        }
        var lastName by remember {
            mutableStateOf("")
        }
        var email by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }

        Text(
            text = "Hey there,",
            style = Typography.body2,
            modifier = Modifier
                .padding(top = 40.dp)
                .height(24.dp)
        )
        Text(text = "Create an Account", style = Typography.body1, modifier=Modifier.height(30.dp))

        CustomEditText(
            value = firstName,
            onValueChange = { newText -> firstName = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp)
                .padding(horizontal = 30.dp),
            placeholderText = "First Name",
            idIcon = R.drawable.ic_profile
        )

        CustomEditText(
            value = lastName,
            onValueChange = { newText -> lastName = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .padding(horizontal = 30.dp),
            placeholderText = "Last Name",
            idIcon = R.drawable.ic_profile
        )

        CustomEditText(
            value = email,
            onValueChange = { newText -> email = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .padding(horizontal = 30.dp),
            placeholderText = "Email",
            idIcon = R.drawable.ic_message,
            keyboardType = KeyboardType.Email
        )
        CustomEditText(
            value = password,
            onValueChange = { newText -> password = newText },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 15.dp)
                .padding(horizontal = 30.dp),
            placeholderText = "Password",
            idIcon = R.drawable.ic_lock,
            keyboardType = KeyboardType.Password
        )

        GradientBtn(
            text = "Register", modifier = Modifier
                .padding(top = 180.dp)
                .padding(horizontal = 30.dp)
        ) {
        }
        Row(modifier = Modifier.padding(top = 30.dp)) {
            Text(
                text = "Already have an account?", fontFamily = myFontFamily, fontSize = 14.sp
            )
            Text(text = "Login", modifier = Modifier.padding(start = 5.dp), color = Color.Blue)

        }

    }

}

@Composable
fun CustomEditText(
    value: String,
    modifier: Modifier,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    idIcon: Int,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        shape = RoundedCornerShape(14.dp),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = gray_light,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        placeholder = { Text(text = placeholderText) },
        singleLine = true,
        leadingIcon = { Icon(painter = painterResource(id = idIcon), contentDescription = null) },
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType)
    )

}

@Preview
@Composable
fun preview() {
    RegScreen()
}