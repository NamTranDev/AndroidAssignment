package nam.tran.data.local.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import nam.tran.data.local.model.ProductEntityDB

@Dao
interface ProductDao {

    @Query("SELECT * FROM product")
    fun getAllProducts(): Flow<List<ProductEntityDB>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(product: ProductEntityDB)

    @Query("SELECT EXISTS(SELECT 1 FROM product WHERE id = :id)")
    suspend fun isBookmarked(id: Int): Boolean

    @Delete
    suspend fun delete(product: ProductEntityDB)
}