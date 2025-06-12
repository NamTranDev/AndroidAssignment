package nam.tran.domain.repository

import nam.tran.domain.model.entity.ProductDetailEntity
import nam.tran.domain.model.entity.ProductEntity

interface ProductRepository {
    suspend fun getProductByCategory(
        category: String?,
        offset: Int,
        limit: Int
    ): List<ProductEntity>

    suspend fun searchProduct(
        query : String?,
        offset: Int,
        limit: Int
    ) : List<ProductEntity>

    suspend fun getProductDetail(productId : String?) : ProductDetailEntity
}