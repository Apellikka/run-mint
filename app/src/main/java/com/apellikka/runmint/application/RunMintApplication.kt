package com.apellikka.runmint.application

import android.app.Application
import com.apellikka.runmint.database.RunDatabase
import com.apellikka.runmint.repositories.RunRepository

class RunMintApplication : Application() {
    val database by lazy { RunDatabase.getDatabase(this) }
    val repository by lazy { RunRepository(database.runDao()) }
}