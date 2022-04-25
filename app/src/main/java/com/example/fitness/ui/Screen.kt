package com.example.fitness.ui

sealed class Screen(val route:String){
    object WelcomeScreen:Screen("welcome_screen")
}
