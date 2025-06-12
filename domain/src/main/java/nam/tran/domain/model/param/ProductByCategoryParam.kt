package nam.tran.domain.model.param

data class ProductByCategoryParam(
    val category: String?,
    val offset: Int,
    val limit: Int
)