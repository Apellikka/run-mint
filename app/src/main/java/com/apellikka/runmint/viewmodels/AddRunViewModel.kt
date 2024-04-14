package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import com.apellikka.runmint.InputValidator
import com.apellikka.runmint.R
import java.text.DateFormat
import java.util.Date

class AddRunViewModel : ViewModel() {

    private val inputValidator = InputValidator()

    val runTypes = arrayOf(
        R.string.run_walk_type,
        R.string.easy_run_type,
        R.string.long_run_type,
        R.string.tempo_run_type,
        R.string.interval_run_type
    )
    fun convertMillisToDate(millis: Long): String {
        val formatter = DateFormat.getDateInstance()
        return formatter.format(Date(millis))
    }
    fun validateIntUnderThreeHundred(input: String): String {
        return inputValidator.validateIntegerUnderThreeHundred(input)
    }

    fun validateDistanceInput(input: String): String {
        return inputValidator.validateDistanceInput(input)
    }

    fun validateSpeedInput(input: String): String {
        return inputValidator.validateSpeedInput(input)
    }

    fun validatePaceInput(input: String): String {
        return inputValidator.validatePaceInput(input)
    }

    fun validateHourInput(input: String): String {
        return inputValidator.validateHourInput(input)
    }

    fun validateMinuteInput(input: String): String {
        return inputValidator.validateMinuteInput(input)
    }
}