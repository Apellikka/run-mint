package com.apellikka.runmint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import com.apellikka.runmint.ui.composables.HomeScreen
import com.apellikka.runmint.ui.theme.RunMintTheme

class MainUiActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunMintTheme {
                Scaffold(
                    topBar = { TopAppBar(
                        title = { Text(text = "RunLife") },
                        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.secondary)) },
                    content = { innerPadding ->
                        HomeScreen(innerPadding)
                    })
            }
        }
    }
}