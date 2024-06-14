package com.apellikka.runmint.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.apellikka.runmint.database.entity.Run
import kotlinx.coroutines.flow.Flow

@Dao
interface RunDao {
    @Query("SELECT * FROM runs")
    fun getAllRuns(): List<Run>

    @Query("SELECT SUM(distance) FROM runs WHERE runtype = 'Easy'")
    fun getEasyDistanceTotal(): Flow<Double>

    @Insert
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)
}