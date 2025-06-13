package nam.tran.data.repository

import nam.tran.data.model.response.ProductResponse
import nam.tran.data.network.api.ProductApi
import nam.tran.domain.model.entity.ProductDetailEntity
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.repository.ProductRepository
import nam.tran.utils.Logger

class ProductRepositoryImpl(
    private val productApi: ProductApi,
) : ProductRepository {

    override suspend fun getProductByCategory(
        category: String?,
        offset: Int,
        limit: Int
    ): List<ProductEntity> {
        Logger.debug(category,offset,limit)
        return productApi.getProductByCategory(
            category = category,
            offset = offset,
            limit = limit
        ).products.map {
            ProductEntity(id = it.id, title = it.title, description = it.description, brand = it.brand,category = it.category, price = it.price, thumbnail = it.thumbnail)
        }
    }

    override suspend fun searchProduct(query: String?, offset: Int, limit: Int) : List<ProductEntity>{
        return productApi.searchProduct(
            query = query,
            offset = offset,
            limit = limit
        ).products.map {
            ProductEntity(id = it.id, title = it.title, description = it.description, brand = it.brand,category = it.category, price = it.price, thumbnail = it.thumbnail)
        }
    }

    override suspend fun getProductDetail(productId : String?): ProductDetailEntity {
        return productApi.productDetail(productId).toProductDetailEntity()
    }
}