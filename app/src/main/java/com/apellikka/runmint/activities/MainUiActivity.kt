package com.apellikka.runmint.activities

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.apellikka.runmint.R
import com.apellikka.runmint.ui.composables.navigation.RunNavGraph
import com.apellikka.runmint.ui.theme.RunMintTheme
import com.apellikka.runmint.viewmodels.MainViewModel

class MainUiActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                            mainViewModel.screens.forEach { screen ->
                                NavigationBarItem(
                                    modifier = Modifier
                                        .background(MaterialTheme.colorScheme.secondary)
                                        .fillMaxWidth(),
                                    label =
                                    {},
                                    selected = currentDestination?.hierarchy?.any {it.route == screen.route } == true,
                                    icon = {
                                        when(screen.route)
                                        {
                                            "home" ->
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(30.dp),
                                                        painter = painterResource(id = R.drawable.home_24px),
                                                        contentDescription = stringResource(id = R.string.home))
                                                    Text(
                                                        text = stringResource(id = R.string.home),
                                                        style = MaterialTheme.typography.labelMedium)
                                                }
                                            "log" ->
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(30.dp),
                                                        painter = painterResource(id = R.drawable.browse_activity_24px),
                                                        contentDescription = stringResource(id = R.string.log))
                                                    Text(
                                                        text = stringResource(id = R.string.log),
                                                        style = MaterialTheme.typography.labelMedium)
                                                }
                                            "plan" ->
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(30.dp),
                                                        painter = painterResource(id = R.drawable.description_24px),
                                                        contentDescription = stringResource(id = R.string.plan))
                                                    Text(
                                                        text = stringResource(id = R.string.plan),
                                                        style = MaterialTheme.typography.labelMedium)
                                                }
                                            "stats" ->
                                                Column(
                                                    horizontalAlignment = Alignment.CenterHorizontally
                                                ) {
                                                    Icon(
                                                        modifier = Modifier
                                                            .size(30.dp),
                                                        painter = painterResource(id = R.drawable.query_stats_24px),
                                                        contentDescription = stringResource(id = R.string.stats))
                                                    Text(
                                                        text = stringResource(id = R.string.stats),
                                                        style = MaterialTheme.typography.labelMedium)
                                                }
                                        }
                                    },
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