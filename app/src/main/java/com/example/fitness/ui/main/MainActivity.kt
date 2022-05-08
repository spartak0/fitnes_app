package com.example.fitness.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.fitness.ui.Screen
import com.example.fitness.ui.reg.FirstRegScreen
import com.example.fitness.ui.reg.SecondRegScreen
import com.example.fitness.ui.theme.FitnesSTheme
import com.example.fitness.ui.welcome.WelcomeScreen


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
    NavHost(navController = navController, startDestination = Screen.WelcomeScreen.route) {
        composable(Screen.WelcomeScreen.route) { WelcomeScreen(navController) }
        composable(Screen.FirstRegScreen.route) { FirstRegScreen(navController) }
        composable(Screen.SecondRegScreen.route) { SecondRegScreen(navController) }
    }
}
