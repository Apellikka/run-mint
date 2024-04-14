package com.apellikka.runmint.ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apellikka.runmint.R
import com.apellikka.runmint.viewmodels.AddRunViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRunScreen(
    addRunViewModel: AddRunViewModel = viewModel()
) {

    var selectedRunType by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var pace by remember { mutableStateOf("") }
    var speed by remember { mutableStateOf("") }
    var cadence by remember { mutableStateOf("") }
    var strideLength by remember { mutableStateOf("") }
    var heartRateMax by remember { mutableStateOf("") }
    var heartRateAvg by remember { mutableStateOf("") }
    var runTypeDropdownExpanded by remember { mutableStateOf(false) }
    var toggleOptionalInputs by remember { mutableStateOf(false) }
    var showDatePicker by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val source = remember { MutableInteractionSource() }
    if ( source.collectIsPressedAsState().value) {
        showDatePicker = true
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (showDatePicker) {
            RunDatePickerDialog(
                onDateSelected = { date = it },
                onDismiss = { showDatePicker = false }
            )
        }
        Button(
            modifier = Modifier
                .width(TextFieldDefaults.MinWidth)
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            onClick = { toggleOptionalInputs = !toggleOptionalInputs },
        ) {
            Text(
                text =
                if (toggleOptionalInputs) stringResource(id = R.string.hide_optional_inputs)
                else stringResource(id = R.string.show_optional_inputs),
                style = MaterialTheme.typography.labelSmall
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState(), enabled = true),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            OutlinedTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
                interactionSource = source,
                value = date,
                onValueChange = { date = it },
                readOnly = true,
                label = {
                    Text(
                        text = stringResource(id = R.string.date_label),
                        style = MaterialTheme.typography.bodyMedium
                    )
                })
            ExposedDropdownMenuBox(
                expanded = runTypeDropdownExpanded,
                onExpandedChange = {
                    runTypeDropdownExpanded = !runTypeDropdownExpanded
                }) {
                OutlinedTextField(
                    modifier = Modifier
                        .menuAnchor(),
                    value = selectedRunType,
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(
                            expanded = runTypeDropdownExpanded
                        )
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colorScheme.background
                    ),
                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = {
                        Text(
                            text = stringResource(id = R.string.type_of_run_label),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
                ExposedDropdownMenu(
                    expanded = runTypeDropdownExpanded,
                    onDismissRequest = { runTypeDropdownExpanded = false }
                ) {
                    addRunViewModel.runTypes.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = stringResource(id = item),
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onClick = {
                                selectedRunType = context.getString(item)
                                runTypeDropdownExpanded = false
                            }
                        )
                    }
                }
            }
            OutlinedTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
                value = distance,
                onValueChange = {
                    distance = addRunViewModel.validateDistanceInput(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.distance_label),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
            OutlinedTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
                value = hours,
                onValueChange = {
                    hours = addRunViewModel.validateHourInput(it)
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                label = {
                    Text(
                        text = stringResource(id = R.string.hours_label),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
            OutlinedTextField(
                textStyle = MaterialTheme.typography.bodyMedium,
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
                label = {
                    Text(
                        text = stringResource(id = R.string.minutes_label),
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            )
            AnimatedVisibility(
                visible = toggleOptionalInputs
            ) {
                Column {
                    OutlinedTextField(
                        textStyle = MaterialTheme.typography.bodyMedium,
                        value = pace,
                        onValueChange = { pace = addRunViewModel.validatePaceInput(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(
                                text = stringResource(id = R.string.pace_label),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )
                    OutlinedTextField(
                        textStyle = MaterialTheme.typography.bodyMedium,
                        value = speed,
                        onValueChange = { speed = addRunViewModel.validateSpeedInput(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(
                                text = stringResource(id = R.string.speed_label),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )
                    OutlinedTextField(
                        textStyle = MaterialTheme.typography.bodyMedium,
                        value = cadence,
                        onValueChange = { cadence = addRunViewModel.validateIntUnderThreeHundred(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(
                                text = stringResource(id = R.string.cadence_label),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )
                    OutlinedTextField(
                        textStyle = MaterialTheme.typography.bodyMedium,
                        value = strideLength,
                        onValueChange = { strideLength = addRunViewModel.validateIntUnderThreeHundred(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(
                                text = stringResource(id = R.string.stride_length_label),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )
                    OutlinedTextField(
                        textStyle = MaterialTheme.typography.bodyMedium,
                        value = heartRateMax,
                        onValueChange = { heartRateMax = addRunViewModel.validateIntUnderThreeHundred(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(
                                text = stringResource(id = R.string.hr_max_label),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )
                    OutlinedTextField(
                        textStyle = MaterialTheme.typography.bodyMedium,
                        value = heartRateAvg,
                        onValueChange = { heartRateAvg = addRunViewModel.validateIntUnderThreeHundred(it) },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                focusManager.moveFocus(FocusDirection.Down)
                            }
                        ),
                        label = {
                            Text(
                                text = stringResource(id = R.string.hr_avg_label),
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    )
                }
            }
            Button(
                modifier = Modifier
                    .defaultMinSize(
                        minWidth = TextFieldDefaults.MinWidth,
                        minHeight = TextFieldDefaults.MinHeight
                    )
                    .padding(vertical = 8.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = {},
            ) {
                Text(
                    text = stringResource(id = R.string.add_run),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    }
}