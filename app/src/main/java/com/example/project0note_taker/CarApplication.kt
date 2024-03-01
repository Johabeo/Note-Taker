package com.example.project0note_taker

import CarRepository
import CarRoomDatabase
import android.app.Application

class CarsApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { CarRoomDatabase.getDatabase(this) }
    val repository by lazy { CarRepository(database.carDao()) }
}