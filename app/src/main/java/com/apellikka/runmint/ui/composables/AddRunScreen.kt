package com.apellikka.runmint.ui.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apellikka.runmint.viewmodels.AddRunViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRunScreen(
    addRunViewModel: AddRunViewModel = viewModel())
{
    //TODO: Next order of business is to validate the inputs and add
    // the remaining fields.

    var selectedRunType by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }) {
            OutlinedTextField(
                modifier = Modifier
                    .menuAnchor(),
                value = selectedRunType,
                onValueChange = {},
                readOnly = true,
                trailingIcon = {
                    ExposedDropdownMenuDefaults.TrailingIcon(
                        expanded = expanded
                    ) },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = MaterialTheme.colorScheme.background
                ),
                textStyle = MaterialTheme.typography.bodyMedium,
                label = { Text(
                    text = "Type of run",
                    style = MaterialTheme.typography.bodyMedium
                )}
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                addRunViewModel.runTypes.forEach { item ->
                    DropdownMenuItem(
                        text = { Text(
                            text = stringResource(id = item),
                            style = MaterialTheme.typography.bodyMedium
                        ) },
                        onClick = {
                            selectedRunType = context.getString(item)
                            expanded = false
                        }
                    )
                }
            }
        }
        OutlinedTextField(
            value = distance,
            onValueChange = {
                distance = addRunViewModel.validateDistanceInput(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            label = { Text(
                text = "Distance",
                style = MaterialTheme.typography.bodyMedium
            )}
        )
        OutlinedTextField(
            value = hours,
            onValueChange = {
                hours = addRunViewModel.validateHourInput(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            label = { Text(
                text = "Hours",
                style = MaterialTheme.typography.bodyMedium
            ) }
        )
        OutlinedTextField(
            value = minutes,
            onValueChange = { minutes = addRunViewModel.validateMinuteInput(it) },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal,
                imeAction = ImeAction.Next
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    focusManager.moveFocus(FocusDirection.Down)
                }
            ),
            label = { Text(
                text = "Minutes",
                style = MaterialTheme.typography.bodyMedium
            ) }
        )
    }
}