package nam.tran.home.assignment.jetpack.compose.data.repository

import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.data.network.api.ProductApi
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class ProductRepositoryImpl(
    private val productApi: ProductApi,
) : ProductRepository {
    override suspend fun getProductByCategory(
        category: String?,
        idFrom: Int,
    ): List<ProductResponse> {
        return productApi.getProductByCategory(category = category, skip = idFrom).products
    }


}