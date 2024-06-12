package com.apellikka.runmint.ui.composables.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.apellikka.runmint.ui.composables.navigation.RunScreens.ADD_RUN_SCREEN
import com.apellikka.runmint.ui.composables.navigation.RunScreens.HOME_SCREEN
import com.apellikka.runmint.ui.composables.navigation.RunScreens.LOG_SCREEN
import com.apellikka.runmint.ui.composables.navigation.RunScreens.PLAN_SCREEN
import com.apellikka.runmint.ui.composables.navigation.RunScreens.STATS_SCREEN

private object RunScreens {
    const val HOME_SCREEN = "home"
    const val ADD_RUN_SCREEN = "add_run"
    const val LOG_SCREEN = "log"
    const val PLAN_SCREEN = "plan"
    const val STATS_SCREEN = "stats"
}

object RunDestinations {
    const val HOME_ROUTE = HOME_SCREEN
    const val ADD_RUN_ROUTE = ADD_RUN_SCREEN
    const val LOG_ROUTE = LOG_SCREEN
    const val PLAN_ROUTE = PLAN_SCREEN
    const val STATS_ROUTE = STATS_SCREEN
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
















