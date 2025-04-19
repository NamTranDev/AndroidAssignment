package nam.tran.home.assignment.jetpack.compose.domain.repository

import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.data.local.model.ProductEntity
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