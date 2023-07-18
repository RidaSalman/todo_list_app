import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todos")
class Todos {
    @PrimaryKey(autoGenerate = true)
    var ID: Int = 0

    @ColumnInfo(name = "title")
    var title: String = ""
}
