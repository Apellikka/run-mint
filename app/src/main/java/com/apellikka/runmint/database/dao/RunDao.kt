package com.apellikka.runmint.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.apellikka.runmint.database.entity.Run
import com.apellikka.runmint.domain.model.WeeklyStats
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface RunDao {
    @Query("SELECT * FROM runs")
    fun getAllRuns(): Flow<List<Run>>

    @Query("SELECT" +
            " distance, " +
            " duration, " +
            "(duration / distance) AS avgPace " +
            "FROM (" +
            " SELECT " +
            " SUM(distance) AS distance," +
            " SUM(hours * 60) + SUM(minutes) AS duration" +
            " FROM runs " +
            " WHERE runtype = :runType AND date >= :weekStartDate AND date <= :weekEndDate)"
    )
    fun getWeeklyRunTypeStats(weekStartDate: LocalDate, weekEndDate: LocalDate, runType: String): Flow<WeeklyStats>

    @Query("SELECT" +
            " distance, " +
            " duration, " +
            "(duration / distance) AS avgPace " +
            "FROM (" +
            " SELECT " +
            " SUM(distance) AS distance," +
            " SUM(hours * 60) + SUM(minutes) AS duration" +
            " FROM runs " +
            "WHERE date >= :weekStartDate AND date <= :weekEndDate)"
            )
    fun getWeeklyTotalRunStats(weekStartDate: LocalDate, weekEndDate: LocalDate): Flow<WeeklyStats>

    @Insert
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)
}