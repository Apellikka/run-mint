package com.apellikka.runmint.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.apellikka.runmint.WeeklyStats
import com.apellikka.runmint.database.entity.Run
import kotlinx.coroutines.flow.Flow
import java.time.LocalDate

@Dao
interface RunDao {
    @Query("SELECT * FROM runs")
    fun getAllRuns(): List<Run>

    @Query("SELECT" +
            " distance, " +
            " duration, " +
            "(duration / distance) AS avgPace " +
            "FROM (" +
            " SELECT " +
            " SUM(distance) AS distance," +
            " SUM(hours * 60) + SUM(minutes) AS duration" +
            " FROM runs " +
            " WHERE runtype = 'Easy' AND date >= :weekStartDate AND date <= :weekEndDate)"
            )
    fun getWeeklyEasyRunStats(weekStartDate: LocalDate, weekEndDate: LocalDate): Flow<WeeklyStats>

    @Query("SELECT" +
            " distance, " +
            " duration, " +
            "(duration / distance) AS avgPace " +
            "FROM (" +
            " SELECT " +
            " SUM(distance) AS distance," +
            " SUM(hours * 60) + SUM(minutes) AS duration" +
            " FROM runs " +
            " WHERE runtype = 'Tempo')"
            )
    fun getWeeklyTempoRunStats(): Flow<WeeklyStats>

    @Query("SELECT" +
            " distance, " +
            " duration, " +
            "(duration / distance) AS avgPace " +
            "FROM (" +
            " SELECT " +
            " SUM(distance) AS distance," +
            " SUM(hours * 60) + SUM(minutes) AS duration" +
            " FROM runs " +
            " WHERE runtype = 'Interval')"
            )
    fun getWeeklyIntervalRunStats(): Flow<WeeklyStats>

    @Query("SELECT" +
            " distance, " +
            " duration, " +
            "(duration / distance) AS avgPace " +
            "FROM (" +
            " SELECT " +
            " SUM(distance) AS distance," +
            " SUM(hours * 60) + SUM(minutes) AS duration" +
            " FROM runs " +
            " WHERE runtype = 'Long')"
    )
    fun getWeeklyLongRunStats(): Flow<WeeklyStats>

    @Query("SELECT" +
            " distance, " +
            " duration, " +
            "(duration / distance) AS avgPace " +
            "FROM (" +
            " SELECT " +
            " SUM(distance) AS distance," +
            " SUM(hours * 60) + SUM(minutes) AS duration" +
            " FROM runs)"
            )
    fun getWeeklyTotalRunStats(): Flow<WeeklyStats>

    @Insert
    suspend fun insertRun(run: Run)

    @Delete
    suspend fun deleteRun(run: Run)
}