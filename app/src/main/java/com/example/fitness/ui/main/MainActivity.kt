package com.example.fitness.ui.main

import android.os.Bundle
import android.os.Parcelable
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.domain.models.User
import com.example.fitness.ui.Screen
import com.example.fitness.ui.bottom_nav_bar.BottomBarScreen
import com.example.fitness.ui.login.LoginScreen
import com.example.fitness.ui.login.LoginViewModel
import com.example.fitness.ui.reg.FirstRegScreen
import com.example.fitness.ui.reg.RegViewModel
import com.example.fitness.ui.reg.SecondRegScreen
import com.example.fitness.ui.theme.FitnesSTheme
import com.example.fitness.ui.welcome.WelcomeScreen
import com.example.utils.Constant
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FitnesSTheme {
                Navigation()
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    val user = Firebase.auth.currentUser
    NavHost(
        navController = navController,
        startDestination = if (user == null) Screen.LoginScreen.route else Screen.BottomBarScreen.route
    ) {
        composable(Screen.WelcomeScreen.route) { WelcomeScreen(navController) }
        composable(Screen.LoginScreen.route) {
            val viewModel: LoginViewModel = hiltViewModel<LoginViewModel>()
            LoginScreen(navController, viewModel)
        }
        composable(Screen.FirstRegScreen.route) {
            val viewModel: RegViewModel = hiltViewModel<RegViewModel>()
            FirstRegScreen(navController = navController, viewModel = viewModel)
        }
        composable(Screen.SecondRegScreen.route) {
            val viewModel: RegViewModel = hiltViewModel<RegViewModel>()
            navController.previousBackStackEntry?.arguments?.getParcelable<User>(Constant.USER_KEY)
                ?.let {
                    SecondRegScreen(navController, it, viewModel)
                }
        }
        composable(Screen.BottomBarScreen.route) { BottomBarScreen(navController) }
    }
}

fun NavController.navigate(
    route: String,
    param: Pair<String, Parcelable>?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    param?.let { this.currentBackStackEntry?.arguments?.putParcelable(param.first, param.second) }

    navigate(route, builder)
}

fun NavController.navigate(
    route: String,
    params: List<Pair<String, Parcelable>>?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    params?.let {
        val arguments = this.currentBackStackEntry?.arguments
        params.forEach { arguments?.putParcelable(it.first, it.second) }
    }

    navigate(route, builder)
}

fun NavController.navigate(
    route: String,
    params: Bundle?,
    builder: NavOptionsBuilder.() -> Unit = {}
) {
    this.currentBackStackEntry?.arguments?.putAll(params)

    navigate(route, builder)
}
