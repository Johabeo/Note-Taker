import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

// Annotates class to be a Room Database with a table (entity) of the Car class
@Database(entities = arrayOf(Car::class), version = 1, exportSchema = false)
public abstract class CarRoomDatabase : RoomDatabase() {

    abstract fun carDao(): CarDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        // modifying schema requires a migration strategy, especially w/ live users
        @Volatile
        private var INSTANCE: CarRoomDatabase? = null

        fun getDatabase(context: Context): CarRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CarRoomDatabase::class.java,
                    "word_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}