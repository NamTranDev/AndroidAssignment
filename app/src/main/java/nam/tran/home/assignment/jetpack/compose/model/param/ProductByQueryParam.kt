package nam.tran.home.assignment.jetpack.compose.model.param

data class ProductByQueryParam(
    val query: String?,
    val offset: Int,
    val limit: Int
)