package com.apellikka.runmint.ui.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.apellikka.runmint.database.entity.Run
import java.time.format.DateTimeFormatter
import kotlin.math.floor

@Composable
fun LogScreenRunCard(
    run: Run
)
{
    // Calculate hours, minutes and seconds from total duration seconds
    val totalHours = run.durationInSeconds / 3600.0
    val hours = floor(totalHours).toInt()
    val totalMinutes = ((totalHours - hours) * 60)
    val minutes = floor(totalMinutes).toInt()
    val seconds = ((totalMinutes - minutes) * 60)

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
    )
    {
        Text(text = String.format("%s", run.date.format(DateTimeFormatter.ofPattern("EEEE dd.MM.yyyy"))))
        Text(text = String.format(run.runType))
        Text(text = String.format("Distance: %.2f", run.distance))
        Text(text = String.format("Time: %d:%d:%02.0f", hours, minutes, seconds))
    }
}