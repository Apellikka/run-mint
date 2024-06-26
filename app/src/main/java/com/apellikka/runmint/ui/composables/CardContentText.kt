package com.apellikka.runmint.ui.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.apellikka.runmint.R
import com.apellikka.runmint.domain.model.WeeklyStats
import kotlin.math.floor

@Composable
fun CardContentText(
    @StringRes infoTitle: Int,
    weeklyStats: WeeklyStats
) {
    // Convert to show hours, minutes and seconds correctly between 0-60
    // instead of 0-99
    val totalHours = weeklyStats.durationInSeconds / 3600.0
    val hours = floor(totalHours).toInt()
    val totalMinutes = ((totalHours - hours) * 60)
    val minutes = floor(totalMinutes).toInt()
    val seconds = ((totalMinutes - minutes) * 60)
    val totalAvgPaceMins = (weeklyStats.avgPace / 60)
    val avgPaceMinutes = floor(weeklyStats.avgPace / 60).toInt()
    val avgPaceSeconds = ((totalAvgPaceMins - avgPaceMinutes) * 60)

    Column {
        Text(
            modifier = Modifier
                .padding(start = 15.dp),
            style = MaterialTheme.typography.bodyLarge,
            text = stringResource(id = infoTitle),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = String.format("%s %.2f", stringResource(id = R.string.distance), weeklyStats.distance),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = String.format("%s %02d:%02d:%02.0f", stringResource(id = R.string.duration), hours, minutes, seconds),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = String.format("%s %d:%02.0f", stringResource(id = R.string.avg_pace), avgPaceMinutes, avgPaceSeconds),
            textAlign = TextAlign.Start
        )
        }
}
