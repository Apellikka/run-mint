package com.apellikka.runmint

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.apellikka.runmint.RunScreens.ADD_RUN_SCREEN
import com.apellikka.runmint.RunScreens.HOME_SCREEN

private object RunScreens {
    const val HOME_SCREEN = "home"
    const val ADD_RUN_SCREEN = "add_run"
}

object RunDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val ADD_RUN_ROUTE = ADD_RUN_SCREEN
}

class RunNavigationActions(private val navController: NavController) {
    fun navigateToAddRunScreen() {
        navController.navigate(
            ADD_RUN_SCREEN
        ) {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = false
            }
            launchSingleTop = true
        }
    }
}
















