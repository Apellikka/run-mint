package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.apellikka.runmint.database.entity.Run
import com.apellikka.runmint.repositories.RunRepository
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val runRepository: RunRepository) : ViewModel() {

    fun getEasyStats(): List<Run>
    {
        var allRuns: List<Run> = emptyList()
        viewModelScope.launch {
            allRuns = runRepository.getAllRuns()
        }
        return allRuns
    }
}

class HomeScreenViewModelFactory(private val repository: RunRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}