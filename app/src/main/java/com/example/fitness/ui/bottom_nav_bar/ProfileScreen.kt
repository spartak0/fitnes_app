package com.example.fitness.ui.bottom_nav_bar

import androidx.compose.foundation.clickable
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ProfileScreen(navController: NavController) {
    Text(text = "Profile", modifier = Modifier.clickable { FirebaseAuth.getInstance().signOut() })
}