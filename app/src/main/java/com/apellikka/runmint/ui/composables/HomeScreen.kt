package com.apellikka.runmint.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.apellikka.runmint.ui.theme.RunMintTheme
import com.apellikka.runmint.ui.theme.Stalinist

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 20.dp)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    )
    {
        Button(
            modifier = Modifier
                .size(150.dp)
                .shadow(15.dp, CircleShape),
            shape = CircleShape,
            onClick = {},
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                style = MaterialTheme.typography.labelLarge,
                text = "Add run",
                textAlign = TextAlign.Center
            )
        }
        ElevatedCard(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 20.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
        )
        {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    style = MaterialTheme.typography.titleLarge,
                    text = "This week",
                    fontFamily = Stalinist,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .padding(start = 15.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    text = "Easy",
                    textAlign = TextAlign.Start
                )
            }
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