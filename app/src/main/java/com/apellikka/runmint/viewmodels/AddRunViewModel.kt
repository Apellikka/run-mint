package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import com.apellikka.runmint.InputValidator

class AddRunViewModel : ViewModel() {

    private val inputValidator = InputValidator()

    fun validateDistanceInput(input: String): String {
        return inputValidator.validateDistanceInput(input)
    }

    fun validateHourInput(input: String): String {
        return inputValidator.validateHourInput(input)
    }

    fun validateMinuteInput(input: String): String {
        return inputValidator.validateMinuteInput(input)
    }
}