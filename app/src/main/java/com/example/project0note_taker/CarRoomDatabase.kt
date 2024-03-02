package com.example.project0note_taker

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Car class
@Database(entities = [Car::class], version = 1)
abstract class CarRoomDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {
        @Volatile
        private var INSTANCE: CarRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CarRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarRoomDatabase::class.java,
                    "car_database"
                )
                    .addCallback(CarDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance
            }
        }


        private class CarDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {
            /**
             * Override the onCreate method to populate the database.
             */
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                // If you want to keep the data through app restarts,
                // comment out the following line.
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.carDao())
                    }
                }
            }
        }

        /**
         * Populate the database in a new coroutine.
         * If you want to start with more words, just add them.
         */
        suspend fun populateDatabase(carDao: CarDao) {
            // Start the app with a clean database every time.
            // Not needed if you only populate on creation.
            carDao.deleteAll()

            var car = Car("Hello")
            carDao.insert(car)
            car = Car("World!")
            carDao.insert(car)
        }
    }
}

