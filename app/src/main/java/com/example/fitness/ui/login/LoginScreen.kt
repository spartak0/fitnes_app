package com.example.fitness.ui.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.os.bundleOf
import androidx.navigation.NavController
import com.example.domain.models.User
import com.example.fitness.R
import com.example.fitness.ui.Screen
import com.example.fitness.ui.details.Email
import com.example.fitness.ui.details.Gradient
import com.example.fitness.ui.details.Password
import com.example.fitness.ui.details.errorDialog
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.main.navigate
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.myFontFamily
import com.example.utils.Constant
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(navController: NavController, viewModel: LoginViewModel) {
    if (viewModel.getCurrentUser() != null) navController.navigate(Screen.BottomBarScreen.route)
    val user = User()
    val ok = stringResource(id = R.string.ok)
    var validation by remember { mutableStateOf(Pair(true, ok)) }
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
            text = stringResource(R.string.welcomeBack),
            style = Typography.body1,
            modifier = Modifier.height(30.dp)
        )
        Spacer(modifier = Modifier.size(15.dp))
        Email(user)
        Spacer(modifier = Modifier.size(15.dp))
        Password(user)
        Spacer(modifier = Modifier.size(350.dp))
        GradientView(
            text = stringResource(id = R.string.login),
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(dimensionResource(id = R.dimen.view_height))
                .fillMaxWidth()
                .clickable {
                    validation = viewModel.validationEmailPassword(user.email, user.password)
                    if (validation.first) {
                        CoroutineScope(Dispatchers.IO).launch { viewModel.loginUser(user.email, user.password) }
                        viewModel.successLogin.onEach {
                            Log.d("AAA", "LoginScreen: $it")
                            if (it) navController.navigate(Screen.BottomBarScreen.route)
                            else Toast
                                .makeText(
                                    navController.context,
                                    navController.context.getString(R.string.failedLogin),
                                    Toast.LENGTH_LONG
                                )
                                .show()
                        }
                    } else Toast
                        .makeText(
                            navController.context,
                            validation.second,
                            Toast.LENGTH_LONG
                        )
                        .show()
                },
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
                modifier = Modifier
                    .padding(start = 5.dp)
                    .clickable {
                        navController.navigate(
                            Screen.FirstRegScreen.route,
                            bundleOf(Constant.USER_KEY to user)
                        )
                    },
                color = Color.Blue,
                fontFamily = myFontFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp,
            )
        }
    }
    if (!validation.first)
        errorDialog(text = validation.second) {
            validation = Pair(true, ok)
        }
}

