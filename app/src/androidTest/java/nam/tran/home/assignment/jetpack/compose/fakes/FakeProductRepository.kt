package nam.tran.home.assignment.jetpack.compose.fakes

import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class FakeProductRepository : ProductRepository {

    override suspend fun getProductByCategory(
        category: String?,
        offset: Int,
        limit: Int,
    ): List<ProductResponse> {
        return when (category) {
            "category1" -> listOf(
                ProductResponse(
                    id = 1,
                    title = "Product 1",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                ),
                ProductResponse(
                    id = 2,
                    title = "Product 1-1",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                )
            )

            "category2" -> listOf(
                ProductResponse(
                    id = 3,
                    title = "Product 2",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                ),
                ProductResponse(
                    id = 4,
                    title = "Product 2-2",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                )
            )

            else -> emptyList()
        }
    }

    override suspend fun searchProduct(
        query: String?,
        offset: Int,
        limit: Int,
    ): List<ProductResponse> {
        return when (query) {
            "a" -> listOf(
                ProductResponse(
                    id = 1,
                    title = "category1",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                ),
                ProductResponse(
                    id = 2,
                    title = "category1-1",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                )
            )

            "b" -> listOf(
                ProductResponse(
                    id = 3,
                    title = "category2",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                ),
                ProductResponse(
                    id = 4,
                    title = "category2-1",
                    description = "This is shown in horizontal layout.",
                    brand = "Brand A",
                    category = "Category A",
                    price = 12.34,
                    thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
                )
            )

            else -> emptyList()
        }
    }

    override suspend fun getProductDetail(productId: String?): ProductDetailResponse {
        return ProductDetailResponse(
            id = 4,
            title = "category2-1",
            description = "This is shown in horizontal layout.",
            brand = "Brand A",
            category = "Category A",
            price = 12.34,
            thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
        )
    }
}