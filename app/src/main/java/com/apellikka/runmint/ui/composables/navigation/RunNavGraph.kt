package com.apellikka.runmint.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apellikka.runmint.ui.composables.AddRunScreen
import com.apellikka.runmint.ui.composables.HomeScreen

@Composable
fun RunNavGraph(
    navController: NavHostController = rememberNavController(),
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
    }
}