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
import com.apellikka.runmint.WeeklyStats
import kotlin.math.floor
import kotlin.math.roundToInt

@Composable
fun CardContentText(
    @StringRes infoTitle: Int,
    weeklyStats: WeeklyStats
) {
    // Convert to show hours, minutes and seconds correctly between 0-60
    // instead of 0-99
    val durationHours = floor((weeklyStats.duration / 60)).toInt()
    val durationMinutes = ((weeklyStats.duration % 60)).roundToInt()
    val avgPaceMinutes = weeklyStats.avgPace.toInt()
    val avgPaceSeconds = ((weeklyStats.avgPace - avgPaceMinutes) * 60).roundToInt()

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
            text = String.format("%s %.1f", stringResource(id = R.string.distance), weeklyStats.distance),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = String.format("%s %d:%02d", stringResource(id = R.string.duration), durationHours, durationMinutes),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = String.format("%s %d:%02d", stringResource(id = R.string.avg_pace), avgPaceMinutes, avgPaceSeconds),
            textAlign = TextAlign.Start
        )
        }
}
