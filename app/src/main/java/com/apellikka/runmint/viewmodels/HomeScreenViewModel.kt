package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apellikka.runmint.WeeklyStats
import com.apellikka.runmint.repositories.RunRepository
import kotlinx.coroutines.flow.Flow

class HomeScreenViewModel(private val runRepository: RunRepository) : ViewModel() {

    val easyStats: Flow<WeeklyStats> = runRepository.getWeeklyEasyRunStats()
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