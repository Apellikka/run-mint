package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apellikka.runmint.R
import com.apellikka.runmint.data.entity.Run
import com.apellikka.runmint.repositories.RunRepository
import com.apellikka.runmint.viewmodels.viewmodelutils.InputValidator
import kotlinx.coroutines.launch

class AddRunViewModel(private val runRepository: RunRepository) : ViewModel() {

    private val inputValidator = InputValidator()

    val runTypes = arrayOf(
        R.string.run_walk_type,
        R.string.easy_run_type,
        R.string.long_run_type,
        R.string.tempo_run_type,
        R.string.interval_run_type
    )

    fun insertRun(run: Run) {
        viewModelScope.launch {
            runRepository.insertRun(run)
        }
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

class AddRunViewModelFactory(private val repository: RunRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddRunViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddRunViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}











