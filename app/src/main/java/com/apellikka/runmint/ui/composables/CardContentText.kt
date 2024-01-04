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

@Composable
fun CardContentText(
    @StringRes infoTitle: Int,
    showAvgPace: Boolean?
) {
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
            text = stringResource(id = R.string.distance),
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .padding(start = 30.dp),
            style = MaterialTheme.typography.bodyMedium,
            text = stringResource(id = R.string.duration),
            textAlign = TextAlign.Start
        )
        if (showAvgPace == true) {
            Text(
                modifier = Modifier
                    .padding(start = 30.dp),
                style = MaterialTheme.typography.bodyMedium,
                text = stringResource(id = R.string.avg_pace),
                textAlign = TextAlign.Start
            )
        }
    }

}