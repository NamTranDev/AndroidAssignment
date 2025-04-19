package nam.tran.home.assignment.jetpack.compose.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import nam.tran.home.assignment.jetpack.compose.data.local.model.ProductEntity

@Database(
    entities = [ProductEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): ProductDao
}