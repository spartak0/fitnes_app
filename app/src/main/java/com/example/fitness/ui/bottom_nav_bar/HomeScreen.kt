package com.example.fitness.ui.bottom_nav_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController

@Composable
fun HomeScreen(navControllerBottomBar: NavController) {
    Column(){
        Text(text = "Home")
    }
}