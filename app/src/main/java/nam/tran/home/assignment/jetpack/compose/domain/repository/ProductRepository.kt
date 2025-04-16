package nam.tran.home.assignment.jetpack.compose.domain.repository

import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface ProductRepository {
    suspend fun getProductByCategory(
        category: String?,
        offset: Int,
        limit: Int
    ): List<ProductResponse>

    suspend fun searchProduct(
        query : String?,
        offset: Int,
        limit: Int
    ) : List<ProductResponse>
}