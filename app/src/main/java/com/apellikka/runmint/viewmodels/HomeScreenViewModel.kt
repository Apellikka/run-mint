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
@RequiresApi(Build.VERSION_CODES.O)
class HomeScreenViewModel(runRepository: RunRepository) : ViewModel() {


    private val weekStartAndEnd = getCurrentWeekStartAndEnd()

    val easyStats: Flow<WeeklyStats> = runRepository.getWeeklyEasyRunStats(weekStartAndEnd.first, weekStartAndEnd.second)
    val tempoStats: Flow<WeeklyStats> = runRepository.getWeeklyTempoRunStats()
    val intervalStats: Flow<WeeklyStats> = runRepository.getWeeklyIntervalRunStats()
    val longStats: Flow<WeeklyStats> = runRepository.getWeeklyLongRunStats()
    val totalStats: Flow<WeeklyStats> = runRepository.getWeeklyTotalRunStats()

    fun getCurrentWeekStartAndEnd(): Pair<LocalDate, LocalDate> {
        val today = LocalDate.now()
        val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        return Pair(startOfWeek, endOfWeek)
    }

    fun getFormattedCurrentWeekStartAndEndDate(weekStartAndEnd: Pair<LocalDate, LocalDate>): Pair<String, String> {
        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        val formattedStartOfWeek = weekStartAndEnd.first.format(formatter)
        val formattedEndOfWeek = weekStartAndEnd.second.format(formatter)
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