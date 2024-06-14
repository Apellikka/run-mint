package com.apellikka.runmint.repositories

import androidx.annotation.WorkerThread
import com.apellikka.runmint.database.dao.RunDao
import com.apellikka.runmint.database.entity.Run
import kotlinx.coroutines.flow.Flow

class RunRepository(
    private val runDao: RunDao
) {
    @WorkerThread
    suspend fun insertRun(run: Run) {
        runDao.insertRun(run)
    }

    fun getEasyDistanceTotal(): Flow<Double>
    {
        return runDao.getEasyDistanceTotal()
    }
}