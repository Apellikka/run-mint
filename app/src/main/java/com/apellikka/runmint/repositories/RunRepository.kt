package com.apellikka.runmint.repositories

import androidx.annotation.WorkerThread
import com.apellikka.runmint.WeeklyStats
import com.apellikka.runmint.database.dao.RunDao
import com.apellikka.runmint.database.entity.Run
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class RunRepository(
    private val runDao: RunDao
) {
    @WorkerThread
    suspend fun insertRun(run: Run) {
        runDao.insertRun(run)
    }

    fun getWeeklyEasyRunStats(weekStartDate: LocalDate, weekEndDate: LocalDate): Flow<WeeklyStats>
    {
        return runDao.getWeeklyEasyRunStats(weekStartDate, weekEndDate)
    }

    fun getWeeklyTempoRunStats(): Flow<WeeklyStats>
    {
        return runDao.getWeeklyTempoRunStats()
    }

    fun getWeeklyIntervalRunStats(): Flow<WeeklyStats>
    {
        return runDao.getWeeklyIntervalRunStats()
    }

    fun getWeeklyLongRunStats(): Flow<WeeklyStats>
    {
        return runDao.getWeeklyLongRunStats()
    }

    fun getWeeklyTotalRunStats(): Flow<WeeklyStats>
    {
        return runDao.getWeeklyTotalRunStats()
    }
}