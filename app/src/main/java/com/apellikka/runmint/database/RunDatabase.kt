package com.apellikka.runmint.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.apellikka.runmint.database.converters.DateConverter
import com.apellikka.runmint.database.dao.RunDao
import com.apellikka.runmint.database.entity.Run

@Database(entities = [Run::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class RunDatabase : RoomDatabase() {
    abstract fun runDao(): RunDao

    companion object {
        @Volatile
        private var INSTANCE: RunDatabase? = null

        fun getDatabase(context: Context): RunDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RunDatabase::class.java,
                    "run_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}