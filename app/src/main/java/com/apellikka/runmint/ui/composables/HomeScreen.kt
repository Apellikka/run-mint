package com.apellikka.runmint.ui.composables

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

@Composable
fun HomeScreen(
    navigationActions: RunNavigationActions,
    homeScreenViewModel: HomeScreenViewModel = viewModel(
        factory = HomeScreenViewModelFactory((LocalContext.current.applicationContext as RunMintApplication).repository)
    )
) {
<<<<<<< HEAD
<<<<<<< HEAD

    val easyStats = homeScreenViewModel.getEasyStats()
=======
    var placeHolderStats by remember { mutableStateOf(WeeklyStats(0.0, 0.0, 0.0)) }
    var easyStats by remember { mutableStateOf(WeeklyStats(0.0, 0.0, 0.0)) }


    LaunchedEffect(key1 = easyStats) {
        homeScreenViewModel.easyStats.collectLatest { value ->
            easyStats = value
        }
    }
>>>>>>> c21757e (fixup! Add functionality for getting run stats WIP)
=======
    var easyRunTotalDistance by remember { mutableStateOf("") }
    var tempoRunTotalDistance by remember { mutableStateOf("") }
    var intervalRunTotalDistance by remember { mutableStateOf("") }
    var otherRunTotalDistance by remember { mutableStateOf("") }
    var totalRunDistance by remember { mutableStateOf("") }


    LaunchedEffect(key1 = easyRunTotalDistance) {
        homeScreenViewModel.easyStats.collectLatest { value ->
            easyRunTotalDistance = value.toString()
        }
    }
>>>>>>> 503a982 (Add mutableStateOf variables for runstats and collect flow WIP)

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
        // TODO: Wrap card and content into it's own composable as well?
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
                        .padding(vertical = 20.dp),
                    style = MaterialTheme.typography.titleLarge,
                    text = stringResource(id = R.string.this_week),
                    fontFamily = Stalinist,
                    textAlign = TextAlign.Center
                )
<<<<<<< HEAD
<<<<<<< HEAD
                CardContentText(infoTitle = R.string.title_easy, true)
=======
                CardContentText(infoTitle = R.string.title_easy, easyRunTotalDistance,true)
>>>>>>> 503a982 (Add mutableStateOf variables for runstats and collect flow WIP)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_tempo, tempoRunTotalDistance, true)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_interval, intervalRunTotalDistance, true)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_other, otherRunTotalDistance, true)
                Spacer(modifier = Modifier.height(10.dp))
<<<<<<< HEAD
                CardContentText(infoTitle = R.string.title_total, false)
=======
                CardContentText(infoTitle = R.string.title_easy, easyStats,true)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_tempo, placeHolderStats, true)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_interval, placeHolderStats, true)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_other, placeHolderStats, true)
                Spacer(modifier = Modifier.height(10.dp))
                CardContentText(infoTitle = R.string.title_total, placeHolderStats, false)
>>>>>>> c21757e (fixup! Add functionality for getting run stats WIP)
=======
                CardContentText(infoTitle = R.string.title_total, totalRunDistance, false)
>>>>>>> 503a982 (Add mutableStateOf variables for runstats and collect flow WIP)
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