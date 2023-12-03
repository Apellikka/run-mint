package com.apellikka.runmint

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apellikka.runmint.ui.composables.HomeScreen

@Composable
fun RunNavGraph(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    )
{
    NavHost(
        navController = navController ,
        startDestination = RunDestinations.HOME_ROUTE
    ) {
      composable(RunDestinations.HOME_ROUTE) {
          HomeScreen()
      }
    }
}