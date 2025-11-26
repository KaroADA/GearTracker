package com.example.geartracker.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen(val route: String, val title: String, val icon: ImageVector) {
    data object Login : Screen("login", "Login", Icons.Default.Person)

    data object Activities : Screen("activities", "Activities", Icons.Default.Home)
    data object Profile : Screen("profile", "Profile", Icons.Default.Person)
}