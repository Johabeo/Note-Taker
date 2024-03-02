package com.example.project0note_taker

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class CarsApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy {
        CarRoomDatabase.getDatabase(this, applicationScope)
    }
    val repository by lazy { CarRepository(database.carDao()) }
}