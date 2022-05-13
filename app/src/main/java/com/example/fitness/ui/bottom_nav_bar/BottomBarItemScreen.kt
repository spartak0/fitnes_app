package com.example.fitness.ui.bottom_nav_bar

import com.example.fitness.R

sealed class BottomBarItemScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarItemScreen(
        route = "home",
        title = "Home",
        icon = R.drawable.ic_home
    )

    object Timers : BottomBarItemScreen(
        route = "timers",
        title = "Timers",
        icon = R.drawable.ic_timer
    )

    object Profile : BottomBarItemScreen(
        route = "profile",
        title = "Profile",
        icon = R.drawable.ic_profile
    )
}
