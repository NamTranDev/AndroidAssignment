package nam.tran.home.assignment.jetpack.compose.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "product")
data class ProductEntity(
    @PrimaryKey val id : Int,
    val title : String? = null,
    val description : String? = null,
    val brand : String? = null,
    val category : String? = null,
    val price : Double? = null,
    val thumbnail : String? = null,
)