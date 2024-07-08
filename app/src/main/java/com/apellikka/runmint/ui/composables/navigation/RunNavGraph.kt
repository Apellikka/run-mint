package com.apellikka.runmint.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.apellikka.runmint.ui.composables.AddRunScreen
import com.apellikka.runmint.ui.composables.HomeScreen
import com.apellikka.runmint.ui.composables.LogScreen
import com.apellikka.runmint.ui.composables.PlanScreen
import com.apellikka.runmint.ui.composables.StatsScreen

@Composable
fun RunNavGraph(
    navController: NavHostController,
    navActions: RunNavigationActions = remember(navController) {
        RunNavigationActions(navController)
    }
    )
{
    NavHost(
        navController = navController ,
        startDestination = RunDestinations.HOME_ROUTE
    ) {
        composable(RunDestinations.HOME_ROUTE) {
            HomeScreen(navActions)
      }
        composable(RunDestinations.ADD_RUN_ROUTE) {
            AddRunScreen()
      }
        composable(RunDestinations.LOG_ROUTE) {
            LogScreen()
        }
        composable(RunDestinations.PLAN_ROUTE) {
            PlanScreen()
        }
        composable(RunDestinations.STATS_ROUTE) {
            StatsScreen()
        }
    }
}