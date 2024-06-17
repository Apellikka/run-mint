package com.apellikka.runmint.viewmodels

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apellikka.runmint.WeeklyStats
import com.apellikka.runmint.repositories.RunRepository
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters

class HomeScreenViewModel(private val runRepository: RunRepository) : ViewModel() {

    val easyStats: Flow<WeeklyStats> = runRepository.getWeeklyEasyRunStats()
    val tempoStats: Flow<WeeklyStats> = runRepository.getWeeklyTempoRunStats()
    val intervalStats: Flow<WeeklyStats> = runRepository.getWeeklyIntervalRunStats()
    val longStats: Flow<WeeklyStats> = runRepository.getWeeklyLongRunStats()
    val totalStats: Flow<WeeklyStats> = runRepository.getWeeklyTotalRunStats()

    @RequiresApi(Build.VERSION_CODES.O)
    fun getCurrentWeekStartAndEnd(): Pair<String, String> {
        val today = LocalDate.now()
        val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))

        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        val formattedStartOfWeek = startOfWeek.format(formatter)
        val formattedEndOfWeek = endOfWeek.format(formatter)

        return Pair(formattedStartOfWeek, formattedEndOfWeek)
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