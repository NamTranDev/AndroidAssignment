package nam.tran.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import nam.tran.data.local.model.ProductDb

@Database(
    entities = [ProductDb::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}