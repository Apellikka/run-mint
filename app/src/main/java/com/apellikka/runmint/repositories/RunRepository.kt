package com.apellikka.runmint.repositories

import androidx.annotation.WorkerThread
import com.apellikka.runmint.dao.RunDao
import com.apellikka.runmint.data.entity.Run

class RunRepository(
    private val runDao: RunDao
) {
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertRun(run: Run) {
        runDao.insertRun(run)
    }
}