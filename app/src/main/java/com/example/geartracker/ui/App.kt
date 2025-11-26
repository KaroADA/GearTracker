package com.example.geartracker.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.geartracker.data.model.StravaActivity
import com.example.geartracker.ui.components.StravaActivityCard
import com.example.geartracker.ui.navigation.Screen
import com.example.geartracker.ui.screens.LoginScreen

val example = StravaActivity(
    id = 12345678987654321,
    name = "Lunch Ride",
    distance = 28099.0,
    moving_time = 4250,
    elapsed_time = 4410,
    total_elevation_gain = 516.0,
    type = "Ride",
    start_date = "2018-02-16T14:52:54Z"
)

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun App(
    onLoginClick: () -> Unit
) {
    val navController = rememberNavController()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarScreens = listOf(Screen.Activities, Screen.Profile)

    val showBottomBar = currentDestination?.route != Screen.Login.route

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                NavigationBar {
                    bottomBarScreens.forEach { screen ->
                        NavigationBarItem(
                            icon = { Icon(screen.icon, contentDescription = null) },
                            label = { Text(screen.title) },
                            selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                            onClick = {
                                navController.navigate(screen.route) {
                                    popUpTo(navController.graph.findStartDestination().id) {
                                        saveState = true
                                    }
                                    launchSingleTop = true
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Login.route) {
                LoginScreen(
                    onLoginClick = {
                        onLoginClick()
                    }
                )
            }

            composable(Screen.Activities.route) {
                androidx.compose.foundation.lazy.LazyColumn {
                    items(100) {
                        StravaActivityCard(example)
                    }
                }
            }

            composable(Screen.Profile.route) {
                Text("User Profile")
            }
        }
    }
}