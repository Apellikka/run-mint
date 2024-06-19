package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apellikka.runmint.database.entity.Run
import com.apellikka.runmint.repositories.RunRepository
import kotlinx.coroutines.flow.Flow

class LogScreenViewModel(runRepository: RunRepository) : ViewModel() {

    val allRuns: Flow<List<Run>> = runRepository.getAllRuns()

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