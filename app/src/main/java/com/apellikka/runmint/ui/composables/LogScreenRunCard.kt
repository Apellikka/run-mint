package com.apellikka.runmint.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.apellikka.runmint.R
import com.apellikka.runmint.database.entity.Run
import com.apellikka.runmint.viewmodels.LogScreenViewModel
import java.time.format.DateTimeFormatter
import kotlin.math.floor

@Composable
fun LogScreenRunCard(
    run: Run,
    logScreenViewModel: LogScreenViewModel
)
{
    // Calculate hours, minutes and seconds from total duration seconds
    val totalHours = run.durationInSeconds / 3600.0
    val hours = floor(totalHours).toInt()
    val totalMinutes = ((totalHours - hours) * 60)
    val minutes = floor(totalMinutes).toInt()
    val seconds = ((totalMinutes - minutes) * 60)
    val totalAvgPaceMins = ((run.durationInSeconds / run.distance) / 60)
    val avgPaceMinutes = floor(totalAvgPaceMins).toInt()
    val avgPaceSeconds = ((totalAvgPaceMins - avgPaceMinutes) * 60)

    var showConfirmationDialog by remember { mutableStateOf(false) }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column()
            {
                Text(
                    text = String.format("%s",
                        run.date.format(DateTimeFormatter.ofPattern(
                            stringResource(id = R.string.formatted_date_pattern)))))
                Text(text = String.format(run.runType))
                Text(
                    text = String.format("%s %.2f %s",
                    stringResource(id = R.string.distance),
                    run.distance,
                    stringResource(id = R.string.km)))
                Text(
                    text = String.format("%s %d:%d:%02.0f",
                        stringResource(id = R.string.time),
                        hours,
                        minutes,
                        seconds))
                Text(
                    text = String.format("%s %d:%02.0f %s",
                        stringResource(id = R.string.avg_pace),
                        avgPaceMinutes,
                        avgPaceSeconds,
                        stringResource(id = R.string.mins_per_km)))
            }
            IconButton(modifier = Modifier
                .align(Alignment.CenterVertically),
                onClick = { showConfirmationDialog = true }) {
                    Icon(
                        Icons.Rounded.Delete,
                        contentDescription = null,
                        tint = Color.Black)
            }
        }
    }

    if(showConfirmationDialog)
    {
        Dialog(
            onDismissRequest = { showConfirmationDialog = false },
            properties = DialogProperties(dismissOnBackPress = true, dismissOnClickOutside = true)
        )
        {
            Surface(shape = MaterialTheme.shapes.extraLarge, color = MaterialTheme.colorScheme.secondary)
            {
                Column(modifier = Modifier
                    .fillMaxWidth()
                    .padding(25.dp))
                {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        text = stringResource(id = R.string.delete_run)
                        )
                    Spacer(modifier = Modifier.height(15.dp))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        Button(onClick = {
                            showConfirmationDialog = false
                            logScreenViewModel.deleteRun(run)
                        })
                        {
                            Text(
                                text = stringResource(id = R.string.yes),
                                style = MaterialTheme.typography.bodyLarge)
                        }
                        Button(onClick = { showConfirmationDialog = false })
                        {
                            Text(
                                text = stringResource(id = R.string.no),
                                style = MaterialTheme.typography.bodyLarge)
                        }
                    }
                }
            }
        }
    }
}