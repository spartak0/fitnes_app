package com.example.fitness.ui.bottom_nav_bar

import android.content.res.Resources.getSystem
import com.example.fitness.R

sealed class BottomBarItemScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarItemScreen(
        route = getSystem().getString(R.string.homeRoute),
        title = getSystem().getString(R.string.homeTitle),
        icon = R.drawable.ic_home
    )

    object Timers : BottomBarItemScreen(
        route = getSystem().getString(R.string.timersRoute),
        title = getSystem().getString(R.string.timersTitle),
        icon = R.drawable.ic_timer
    )

    object Profile : BottomBarItemScreen(
        route = getSystem().getString(R.string.profileRoute),
        title = getSystem().getString(R.string.profileTitle),
        icon = R.drawable.ic_profile
    )
}
