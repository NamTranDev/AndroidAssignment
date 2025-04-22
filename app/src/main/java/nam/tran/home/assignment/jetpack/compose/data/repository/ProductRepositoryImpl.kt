package nam.tran.home.assignment.jetpack.compose.data.repository

import nam.tran.home.assignment.jetpack.compose.data.network.api.ProductApi
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class ProductRepositoryImpl(
    private val productApi: ProductApi,
) : ProductRepository {

    override suspend fun getProductByCategory(
        category: String?,
        offset: Int,
        limit: Int
    ): List<ProductResponse> {
        return productApi.getProductByCategory(
            category = category,
            offset = offset,
            limit = limit
        ).products
    }

    override suspend fun searchProduct(query: String?, offset: Int, limit: Int) : List<ProductResponse>{
        return productApi.searchProduct(
            query = query,
            offset = offset,
            limit = limit
        ).products
    }

    override suspend fun getProductDetail(productId : String?): ProductDetailResponse {
        return productApi.productDetail(productId)
    }
}