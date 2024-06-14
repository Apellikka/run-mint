package com.apellikka.runmint.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.apellikka.runmint.database.entity.Run

@Dao
interface RunDao {
    @Query("SELECT * FROM runs")
    suspend fun getAllRuns(): List<Run>

    @Insert
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)
}