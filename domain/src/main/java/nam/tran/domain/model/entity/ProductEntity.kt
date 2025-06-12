package nam.tran.domain.model.entity

data class ProductEntity(
    val id : Int,
    val title : String? = null,
    val description : String? = null,
    val brand : String? = null,
    val category : String? = null,
    val price : Double? = null,
    val thumbnail : String? = null,
)