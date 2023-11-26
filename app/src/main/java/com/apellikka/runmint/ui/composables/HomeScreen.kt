package com.apellikka.runmint.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apellikka.runmint.ui.theme.RunMintTheme

@Composable
fun HomeScreen(
    paddingValues: PaddingValues
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        .padding(paddingValues),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Button(
            modifier = Modifier
                .size(150.dp),
            onClick = {},
            shape = RoundedCornerShape(100)
        ) {
            Text(text = "Add run")
        }
    }
}

@Preview
@Composable
fun PreviewHomeScreen() {
    RunMintTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Transparent)
        ) {
            Button(
                modifier = Modifier
                    .size(150.dp)
                    .background(MaterialTheme.colorScheme.primary),
                onClick = {},
                shape = RoundedCornerShape(100)
            ) {
                Text(text = "Add run")
            }
        }
    }
}