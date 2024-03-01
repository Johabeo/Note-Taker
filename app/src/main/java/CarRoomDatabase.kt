import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Car class
@Database(entities = arrayOf(Car::class), version = 1, exportSchema = false)
abstract class CarRoomDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    private class CarDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    var carDao = database.carDao()

                    // Delete all content here.
                    carDao.deleteAll()

                    // Add sample words.
                    var car = Car(null,"Maserati")
                    carDao.insert(car)
                    car = Car(null, "Bugatti!")
                    carDao.insert(car)
                    car = Car(null, "Honda!")
                    carDao.insert(car)
                }
            }
        }
    }

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
    }
}