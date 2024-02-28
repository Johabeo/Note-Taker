import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

//Data Access Objects must be interfaces or abstract classes
@Dao
interface CarDao {

    //annotation w/ SQL for READ/getter method
    //Flow observes in real time async on background thread
    @Query("SELECT * FROM car_table ORDER BY car ASC")
    fun getAlphabetizedCars(): Flow<List<Car>>

    //annotation auto-generates SQL commands, On Conflict Strategy
    //ignores two cases of the same word
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(car: Car)

    //Annotation w/ SQL for DELETE method
    @Query("DELETE FROM car_table")
    suspend fun deleteAll()
}