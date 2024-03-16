package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import com.apellikka.runmint.DecimalFormatter

class AddRunViewModel : ViewModel() {

    private val decimalFormatter = DecimalFormatter()

    fun validateDistanceInput(input: String): String {
        return decimalFormatter.validateDecimalInput(input)
    }

    fun validateHourInput(input: String): String {
        return ""
    }

    fun validateMinuteInput(input: String): String {
        return ""
    }
}