package nam.tran.domain.model.param

data class ProductByQueryParam(
    val query: String?,
    val offset: Int,
    val limit: Int
)