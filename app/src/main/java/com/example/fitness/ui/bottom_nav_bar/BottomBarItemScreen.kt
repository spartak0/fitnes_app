package com.example.fitness.ui.bottom_nav_bar

import com.example.fitness.R

sealed class BottomBarItemScreen(
    val route: String,
    val icon: Int,
) {
    object Home : BottomBarItemScreen(
        route = "home_route",
        icon = R.drawable.ic_home,
    )

    object Timers : BottomBarItemScreen(
        route = "timers_route",
        icon = R.drawable.ic_timer
    )

    object Profile : BottomBarItemScreen(
        route = "profile_route",
        icon = R.drawable.ic_profile
    )
}
