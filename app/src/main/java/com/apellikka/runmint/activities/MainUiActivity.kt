package com.apellikka.runmint.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apellikka.runmint.R
import com.apellikka.runmint.ui.composables.navigation.RunNavGraph
import com.apellikka.runmint.ui.composables.navigation.Screen
import com.apellikka.runmint.ui.theme.RunMintTheme

class MainUiActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val screens = listOf(
            Screen.Home,
            Screen.Log,
            Screen.Plan,
            Screen.Stats
        )
        setContent {
            val navController = rememberNavController()
            RunMintTheme {
                Scaffold(
                    topBar = { TopAppBar(
                        title = { Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            style = MaterialTheme.typography.displayLarge,
                            text = stringResource(id = R.string.app_name),
                            textAlign = TextAlign.Center) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            titleContentColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.secondary)) },
                    bottomBar = {
                        NavigationBar(
                            containerColor = MaterialTheme.colorScheme.secondary
                        ) {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination = navBackStackEntry?.destination
                            screens.forEach { screen ->
                                NavigationBarItem(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.secondary)
                                        .fillMaxWidth(),
                                    label =
                                    {
                                        Text(
                                        text = stringResource(id = screen.resourceId),
                                        style = MaterialTheme.typography.labelSmall)
                                    },
                                    selected = currentDestination?.hierarchy?.any {it.route == screen.route } == true,
                                    icon = {},
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
                    },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                        ) {
                            RunNavGraph(navController)
                        }
                    })
            }
        }
    }
}