package com.apellikka.runmint.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.apellikka.runmint.domain.model.RunType
import com.apellikka.runmint.domain.model.WeeklyStats
import com.apellikka.runmint.repositories.RunRepository
import kotlinx.coroutines.flow.Flow
import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAdjusters
class HomeScreenViewModel(runRepository: RunRepository) : ViewModel() {

    private val weekStartAndEnd = getCurrentWeekStartAndEnd()

    val easyStats: Flow<WeeklyStats> =
        runRepository.getWeeklyRunTypeStats(weekStartAndEnd.first, weekStartAndEnd.second, RunType.EASY.type)

    val tempoStats: Flow<WeeklyStats> =
        runRepository.getWeeklyRunTypeStats(weekStartAndEnd.first, weekStartAndEnd.second, RunType.TEMPO.type)

    val intervalStats: Flow<WeeklyStats> =
        runRepository.getWeeklyRunTypeStats(weekStartAndEnd.first, weekStartAndEnd.second, RunType.INTERVAL.type)

    val longStats: Flow<WeeklyStats> =
        runRepository.getWeeklyRunTypeStats(weekStartAndEnd.first, weekStartAndEnd.second, RunType.LONG.type)

    val totalStats: Flow<WeeklyStats> =
        runRepository.getWeeklyTotalRunStats(weekStartAndEnd.first, weekStartAndEnd.second)



    fun getCurrentWeekStartAndEnd(): Pair<LocalDate, LocalDate>
    {
        val today = LocalDate.now()
        val startOfWeek = today.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
        val endOfWeek = today.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY))
        return Pair(startOfWeek, endOfWeek)
    }

    fun getFormattedCurrentWeekStartAndEndDate(weekStartAndEnd: Pair<LocalDate, LocalDate>)
    : Pair<String, String>
    {
        val formatter = DateTimeFormatter.ofPattern("dd.MM")
        val formattedStartOfWeek = weekStartAndEnd.first.format(formatter)
        val formattedEndOfWeek = weekStartAndEnd.second.format(formatter)
        return Pair(formattedStartOfWeek, formattedEndOfWeek)
    }
}

class HomeScreenViewModelFactory(private val repository: RunRepository)
    : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeScreenViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HomeScreenViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}