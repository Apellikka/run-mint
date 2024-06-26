package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apellikka.runmint.database.entity.Run
import com.apellikka.runmint.repositories.RunRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class LogScreenViewModel(private val runRepository: RunRepository) : ViewModel() {

    val allRuns: Flow<List<Run>> = runRepository.getAllRuns()

    fun deleteRun(run: Run)
    {
        viewModelScope.launch {
            runRepository.deleteRun(run)
        }
    }
}

class LogScreenViewModelFactory(private val repository: RunRepository)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}