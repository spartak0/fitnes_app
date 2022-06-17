package com.example.fitness.ui.login

import android.widget.Toast
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
import com.example.fitness.ui.main.GradientView
import com.example.fitness.ui.main.navigate
import com.example.fitness.ui.theme.Typography
import com.example.fitness.ui.theme.myFontFamily
import com.example.utils.Constant
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

@Composable
fun LoginScreen(navController: NavController) {
    val user = User()
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
            text = "Login",
            modifier = Modifier
                .padding(horizontal = 30.dp)
                .height(dimensionResource(id = R.dimen.view_height))
                .fillMaxWidth()
                .clickable {
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
}

