package com.apellikka.runmint.repositories

import androidx.annotation.WorkerThread
import com.apellikka.runmint.database.dao.RunDao
import com.apellikka.runmint.database.entity.Run
import com.apellikka.runmint.domain.model.WeeklyStats
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

class RunRepository(
    private val runDao: RunDao
) {
    @WorkerThread
    suspend fun insertRun(run: Run)
    {
        runDao.insertRun(run)
    }

    @WorkerThread
    suspend fun deleteRun(run: Run)
    {
        runDao.deleteRun(run)
    }

    fun getAllRuns() : Flow<List<Run>>
    {
        return runDao.getAllRuns()
    }

    fun getWeeklyRunTypeStats(weekStartDate: LocalDate, weekEndDate: LocalDate, runType: String)
    : Flow<WeeklyStats>
    {
        return runDao.getWeeklyRunTypeStats(weekStartDate, weekEndDate, runType)
    }

    fun getWeeklyTotalRunStats(weekStartDate: LocalDate, weekEndDate: LocalDate)
    : Flow<WeeklyStats>
    {
        return runDao.getWeeklyTotalRunStats(weekStartDate, weekEndDate)
    }
}