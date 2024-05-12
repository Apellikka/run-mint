package com.apellikka.runmint.ui.composables.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.apellikka.runmint.application.RunMintApplication
import com.apellikka.runmint.ui.composables.AddRunScreen
import com.apellikka.runmint.ui.composables.HomeScreen
import com.apellikka.runmint.viewmodels.AddRunViewModel
import com.apellikka.runmint.viewmodels.AddRunViewModelFactory

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
          val addRunViewModel: AddRunViewModel = viewModel(
              factory = AddRunViewModelFactory((LocalContext.current.applicationContext as RunMintApplication).repository)
          )
          AddRunScreen(addRunViewModel)
      }
    }
}