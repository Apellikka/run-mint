package com.apellikka.runmint.ui.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.input.KeyboardType

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AddRunScreen() {
    var distance by remember { mutableStateOf("") }
    var distances = arrayOf("0.1","0.2","0.3","0.4","0.5","0.6","0.7","0.8")
    var selectedDistance by remember { mutableStateOf(distances[0]) }
    var expanded by remember { mutableStateOf(false) }
    Column {
        OutlinedTextField(
            value = distance,
            onValueChange = { distance = it },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Decimal
            ),
            label = { Text(text = "Distance")}
        )

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {expanded = !expanded}
        ) {
            TextField(
                value = selectedDistance,
                onValueChange = {},
                readOnly = true
            )
            
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                distances.forEach { distance ->
                    DropdownMenuItem(
                        onClick = {
                            selectedDistance = distance
                            expanded = false }
                    ) {}
                }
            }
        }
    }
}