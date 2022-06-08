@file:JvmName("BottomBarItemScreenKt")

package com.example.fitness.ui.bottom_nav_bar

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.fitness.ui.theme.Pink1


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomBarScreen(navController: NavController) {
    val navControllerBottomBar = rememberNavController()
    Scaffold(bottomBar = { BottomBar(navController = navControllerBottomBar) }) {
        BottomNavGraph(navController = navControllerBottomBar)
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarItemScreen.Home,
        BottomBarItemScreen.Timers,
        BottomBarItemScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    BottomNavigation(backgroundColor = Color.White, elevation = 5.dp) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarItemScreen,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(id = screen.icon),
                contentDescription = "Navigation Icon"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        selectedContentColor = Pink1,
        onClick = { navController.navigate(screen.route) }
    )
}


@Composable
fun BottomNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = BottomBarItemScreen.Home.route) {
        composable(BottomBarItemScreen.Home.route) { HomeScreen(navController = navController) }
        composable(BottomBarItemScreen.Timers.route) { TimersScreen(navController = navController) }
        composable(BottomBarItemScreen.Profile.route) { ProfileScreen(navController = navController) }
    }
}