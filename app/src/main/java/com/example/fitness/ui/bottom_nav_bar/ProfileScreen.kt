package com.example.fitness.ui.bottom_nav_bar

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.fitness.ui.Screen
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navControllerBottomBar: NavController, navControllerScreens:NavController) {
    val mAuth = FirebaseAuth.getInstance()
    Column() {
        Button(onClick = {
            mAuth.signOut()
            navControllerScreens.navigate(Screen.LoginScreen.route)
        }) {
        }
    }
}