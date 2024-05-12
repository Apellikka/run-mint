package com.apellikka.runmint.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.apellikka.runmint.data.entity.Run

@Dao
interface RunDao {
    @Query("SELECT * FROM runs")
    fun getAllRuns(): List<Run>

    @Insert
    fun insertRun(run: Run)

    @Delete
    fun deleteRun(run: Run)
}