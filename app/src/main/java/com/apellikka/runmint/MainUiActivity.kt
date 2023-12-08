package com.apellikka.runmint

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.apellikka.runmint.ui.theme.RunMintTheme

class MainUiActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RunMintTheme {
                Scaffold(
                    topBar = { TopAppBar(
                        title = { Text(
                            modifier = Modifier
                                .fillMaxWidth(),
                            text = "RunMint",
                            textAlign = TextAlign.Center,
                            fontFamily = MaterialTheme.typography.displayLarge.fontFamily) },
                        colors = TopAppBarDefaults.topAppBarColors(
                            titleContentColor = Color.White,
                            containerColor = MaterialTheme.colorScheme.secondary)) },
                    bottomBar = {
                        BottomAppBar(containerColor = MaterialTheme.colorScheme.secondary) {
                        }
                    },
                    content = { innerPadding ->
                        Column(
                            modifier = Modifier
                                .padding(innerPadding)
                        ) {
                            RunNavGraph()
                        }
                    })
            }
        }
    }
}