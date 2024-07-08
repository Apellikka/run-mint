package com.apellikka.runmint.ui.composables

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.apellikka.runmint.R
import com.apellikka.runmint.application.RunMintApplication
import com.apellikka.runmint.viewmodels.AddRunViewModel
import com.apellikka.runmint.viewmodels.AddRunViewModelFactory
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRunScreen(
    addRunViewModel: AddRunViewModel = viewModel(
        factory = AddRunViewModelFactory((LocalContext.current.applicationContext as RunMintApplication).repository)
    )
) {

    var selectedRunType by remember { mutableStateOf("") }
    var distance by remember { mutableStateOf("") }
    var hours by remember { mutableStateOf("") }
    var minutes by remember { mutableStateOf("") }
    var seconds by remember { mutableStateOf("") }
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
    var requiredFieldIndicator by remember { mutableStateOf((false)) }

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

                style = MaterialTheme.typography.labelLarge
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
                colors =
                if(requiredFieldIndicator && date.isBlank())
                    OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Red)
                else OutlinedTextFieldDefaults.colors(),

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
                    colors =
                        if(requiredFieldIndicator && selectedRunType.isEmpty())
                            OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                                unfocusedBorderColor = Color.Red)
                        else OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = MaterialTheme.colorScheme.background,
                            unfocusedContainerColor = MaterialTheme.colorScheme.background),

                    textStyle = MaterialTheme.typography.bodyMedium,
                    label = {
                        Text(
                            text = stringResource(id = R.string.type_of_run_label),
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                )
                ExposedDropdownMenu(
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background),
                    expanded = runTypeDropdownExpanded,
                    onDismissRequest = { runTypeDropdownExpanded = false },
                ) {
                    addRunViewModel.runTypes.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            },
                            onClick = {
                                selectedRunType = item
                                runTypeDropdownExpanded = false
                            }
                        )
                    }
                }
            }
            OutlinedTextField(
                colors =
                if(requiredFieldIndicator && distance.isBlank())
                    OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Red)
                else OutlinedTextFieldDefaults.colors(),

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
                colors =
                if(requiredFieldIndicator && hours.isBlank())
                    OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Red)
                else OutlinedTextFieldDefaults.colors(),

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
                colors =
                if(requiredFieldIndicator && minutes.isBlank())
                    OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Red)
                else OutlinedTextFieldDefaults.colors(),

                textStyle = MaterialTheme.typography.bodyMedium,
                value = minutes,
                onValueChange = { minutes = addRunViewModel.validateMinutesAndSecondsInput(it) },
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
            OutlinedTextField(
                colors =
                if(requiredFieldIndicator && seconds.isBlank())
                    OutlinedTextFieldDefaults.colors(unfocusedBorderColor = Color.Red)
                else OutlinedTextFieldDefaults.colors(),

                textStyle = MaterialTheme.typography.bodyMedium,
                value = seconds,
                onValueChange = { seconds = addRunViewModel.validateMinutesAndSecondsInput(it) },
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
                        text = stringResource(id = R.string.seconds_label),
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
                onClick = {
                    if(addRunViewModel.requiredFieldsAreFilled(
                            date = date,
                            runType = selectedRunType,
                            distance = distance,
                            hours = hours,
                            minutes = minutes,
                            seconds = seconds
                    )) {
                        addRunViewModel.insertRun(
                            date = LocalDate.parse(date, DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                            runType = selectedRunType,
                            distance = distance.toDouble(),
                            hours = hours.toInt(),
                            minutes = minutes.toInt(),
                            seconds = seconds.toInt(),
                            pace = if(pace.isNotEmpty()) pace.toDouble() else null,
                            speed = if(speed.isNotEmpty()) speed.toDouble() else null,
                            cadence = if(cadence.isNotEmpty()) cadence.toInt() else null,
                            stride = if(strideLength.isNotEmpty()) strideLength.toInt() else null,
                            hrMax = if(heartRateMax.isNotEmpty()) heartRateMax.toInt() else null,
                            hrAvg = if(heartRateAvg.isNotEmpty()) heartRateAvg.toInt() else null
                        )
                        date = ""
                        selectedRunType = ""
                        distance = ""
                        hours = ""
                        minutes = ""
                        seconds = ""
                        pace = ""
                        speed = ""
                        cadence = ""
                        strideLength = ""
                        heartRateMax = ""
                        heartRateAvg = ""
                        requiredFieldIndicator = false
                    }
                    else {
                        Toast.makeText(context, "Fill all required fields!", Toast.LENGTH_LONG).show()
                        requiredFieldIndicator = true
                    }
                },
            ) {
                Text(
                    text = stringResource(id = R.string.add_run),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}