package com.apellikka.runmint.ui.composables

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apellikka.runmint.application.RunMintApplication
import com.apellikka.runmint.database.entity.Run
import com.apellikka.runmint.viewmodels.LogScreenViewModel
import com.apellikka.runmint.viewmodels.LogScreenViewModelFactory
import kotlinx.coroutines.flow.collectLatest

@Composable
fun LogScreen(
    logScreenViewModel: LogScreenViewModel = viewModel(
        factory = LogScreenViewModelFactory((LocalContext.current.applicationContext as RunMintApplication).repository)
    )
)
{

    var allRuns by remember { mutableStateOf(listOf<Run>()) }

    LaunchedEffect(allRuns)
    {
        logScreenViewModel.allRuns.collectLatest { value ->
            allRuns = value
        }
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize(),
        contentPadding = PaddingValues(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        items(allRuns) { run ->
            LogScreenRunCard(run, logScreenViewModel)
        }
    }
}