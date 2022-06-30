package com.example.fitness.ui.reg.firstReg


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
import androidx.core.os.bundleOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.domain.models.User
import com.example.fitness.R
import com.example.fitness.ui.Screen
import com.example.fitness.ui.details.*
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.main.navigate
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.myFontFamily
import com.example.utils.Constant

@Composable
fun FirstRegScreen(
    navController: NavController,
    user: User = User(),
    viewModel: FirstRegViewModel = viewModel()
) {
    val error by viewModel.error.collectAsState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.size(40.dp))
        Text(
            text = stringResource(id = R.string.heyThere),
            style = Typography.body2,
            modifier = Modifier
                .height(24.dp)
        )
        Text(
            text = stringResource(id = R.string.createAccount),
            style = Typography.body1,
            modifier = Modifier.height(30.dp)
        )
        Spacer(modifier = Modifier.size(30.dp))
        FirstName(user)
        Spacer(modifier = Modifier.size(15.dp))
        LastName(user)
        Spacer(modifier = Modifier.size(15.dp))
        Email(user)
        Spacer(modifier = Modifier.size(15.dp))
        Password(user)
        Spacer(modifier = Modifier.size(180.dp))
        GradientView(
            text = stringResource(id = R.string.register),
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(dimensionResource(id = R.dimen.view_height))
                .fillMaxWidth()
                .clickable {
                    viewModel.onClickNext(user) {
                        navController.navigate(
                            Screen.SecondRegScreen.route,
                            bundleOf(Constant.USER_KEY to user)
                        )
                    }
                },
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
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable { navController.navigate(Screen.LoginScreen.route) },
                color = Color.Blue,
                fontFamily = myFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )

        }
    }
    if (error.first) errorDialog(text = error.second) {
        viewModel.onErrorClick()
    }
}

@Composable
fun FirstName(user: User) {
    var firstName by remember { mutableStateOf("") }
    EditText(
        value = firstName,
        onValueChange = { newText ->
            firstName = newText
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholderText = stringResource(R.string.firstname),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null
            )
        },
    )
    user.firstname = firstName
}

@Composable
fun LastName(user: User) {
    var lastName by remember { mutableStateOf("") }
    EditText(
        value = lastName,
        onValueChange = { newText ->
            run {
                lastName = newText
                user.lastname = newText
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 30.dp),
        placeholderText = stringResource(R.string.lastName),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_profile),
                contentDescription = null
            )
        },
    )
    user.lastname = lastName
}