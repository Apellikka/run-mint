package com.apellikka.runmint.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.apellikka.runmint.database.entity.Run

@Dao
interface RunDao {
    @Query("SELECT * FROM runs")
    fun getAllRuns(): List<Run>

    @Insert
    fun insertRun(run: Run)

    @Delete
    fun deleteRun(run: Run)
}