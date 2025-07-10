package nam.tran.home.assignment.jetpack.compose.model.response

data class ProductResponse(
    val id : Int,
    val title : String? = null,
    val description : String? = null,
    val brand : String? = null,
    val category : String? = null,
    val price : Double? = null,
    val thumbnail : String? = null,
)