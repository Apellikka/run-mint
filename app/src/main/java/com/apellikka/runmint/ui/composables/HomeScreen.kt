package com.apellikka.runmint.ui.composables

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apellikka.runmint.R
import com.apellikka.runmint.WeeklyStats
import com.apellikka.runmint.application.RunMintApplication
import com.apellikka.runmint.ui.composables.navigation.RunNavigationActions
import com.apellikka.runmint.ui.theme.RunMintTheme
import com.apellikka.runmint.ui.theme.Stalinist
import com.apellikka.runmint.viewmodels.HomeScreenViewModel
import com.apellikka.runmint.viewmodels.HomeScreenViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    navigationActions: RunNavigationActions,
    homeScreenViewModel: HomeScreenViewModel = viewModel(
        factory = HomeScreenViewModelFactory((LocalContext.current.applicationContext as RunMintApplication).repository)
    )
) {
    var easyStats by remember { mutableStateOf(WeeklyStats(0.0, 0.0, 0.0)) }
    var tempoStats by remember { mutableStateOf(WeeklyStats(0.0, 0.0, 0.0)) }
    var intervalStats by remember { mutableStateOf(WeeklyStats(0.0, 0.0, 0.0)) }
    var longStats by remember { mutableStateOf(WeeklyStats(0.0, 0.0, 0.0)) }
    var totalStats by remember { mutableStateOf(WeeklyStats(0.0, 0.0, 0.0)) }

    val scope = rememberCoroutineScope()
    val currentWeekStartAndEnd = homeScreenViewModel.getCurrentWeekStartAndEnd()

    LaunchedEffect(easyStats, tempoStats, intervalStats, longStats, totalStats) {
        scope.launch {
            homeScreenViewModel.easyStats.collectLatest { value ->
                easyStats = value
            }
        }
        scope.launch {
            homeScreenViewModel.tempoStats.collectLatest { value ->
                tempoStats = value
            }
        }
        scope.launch {
            homeScreenViewModel.intervalStats.collectLatest { value ->
                intervalStats = value
            }
        }
        scope.launch {
            homeScreenViewModel.longStats.collectLatest { value ->
                longStats = value
            }
        }
        scope.launch {
            homeScreenViewModel.totalStats.collectLatest { value ->
                totalStats = value
            }
        }
    }

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
            onClick = { navigationActions.navigateToAddRunScreen() },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
        ) {
            Text(
                style = MaterialTheme.typography.labelLarge,
                text = stringResource(id = R.string.add_run),
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
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize()
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = String.format("%s", stringResource(id = R.string.current_week)),
                    fontFamily = Stalinist,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = String.format("%s-%s",
                        currentWeekStartAndEnd.first,
                        currentWeekStartAndEnd.second
                    ),
                    textAlign = TextAlign.Center
                )
                CardContentText(infoTitle = R.string.title_easy, easyStats)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_tempo, tempoStats)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_interval, intervalStats)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_long, longStats)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_total, totalStats)
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
                Text(text = stringResource(id = R.string.add_run))
            }
        }
    }
}