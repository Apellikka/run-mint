package com.apellikka.runmint.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "runs")
data class Run(
    @PrimaryKey val date: Date,
    val runType: String,
    val distance: Double,
    val hours: Int,
    val minutes: Int,
    val pace: Double?,
    val speed: Double?,
    val cadence: Int?,
    val stride: Int?,
    val hrMax: Int?,
    val hrAvg: Int?
)