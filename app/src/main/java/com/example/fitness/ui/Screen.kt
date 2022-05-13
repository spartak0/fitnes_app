package com.example.fitness.ui

sealed class Screen(val route:String){
    object WelcomeScreen:Screen("welcome_screen")
    object FirstRegScreen:Screen("first_reg_screen")
    object SecondRegScreen:Screen("second_reg_screen")
    object MainScreen:Screen("main_screen")
}
