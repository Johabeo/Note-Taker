import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Entity class outlines the database structure
//Annotations create table, each parameter is a field
@Entity(tableName = "car_table")
class Car (

    //first field is auto-generated upon creating object
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "car") val word: String

)