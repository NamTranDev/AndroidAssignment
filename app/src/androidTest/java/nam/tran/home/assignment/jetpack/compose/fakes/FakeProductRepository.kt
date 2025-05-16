package nam.tran.home.assignment.jetpack.compose.fakes

import kotlinx.coroutines.delay
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import nam.tran.home.assignment.jetpack.compose.utils.Logger

class FakeProductRepository(
    private val case: CaseTest,
) : ProductRepository {

    override suspend fun getProductByCategory(
        category: String?,
        offset: Int,
        limit: Int,
    ): List<ProductResponse> {
        Logger.debug("Fetching products | category=$category, offset=$offset, limit=$limit")

        delay(1000)

        if (case.productType == 0) {
            return when (category) {
                "category1" -> {
                    val products = when (offset) {
                        0 -> generateDummyProducts(1, 10)
                        10 -> generateDummyProducts(11, 20)
                        else -> emptyList()
                    }
                    Logger.debug("Loaded ${products.size} products for category1 at offset $offset")
                    products
                }

                "category2" -> {
                    val products = listOf(
                        ProductResponse(3, "Product 2", "This is shown in horizontal layout.", "Brand A", "Category A", 12.34, "https://cdn.dummyjson.com/product-image.jpg"),
                        ProductResponse(4, "Product 2-2", "This is shown in horizontal layout.", "Brand A", "Category A", 12.34, "https://cdn.dummyjson.com/product-image.jpg")
                    )
                    Logger.debug("Loaded ${products.size} products for category2")
                    products
                }

                else -> {
                    Logger.debug("Unknown category: $category")
                    emptyList()
                }
            }
        } else if (case.productType == 2) {
            if (offset == 0) {
                val products = when (category) {
                    "category1" -> generateDummyProducts(1, 10)
                    "category2" -> listOf(
                        ProductResponse(3, "Product 2", "This is shown in horizontal layout.", "Brand A", "Category A", 12.34, "https://cdn.dummyjson.com/product-image.jpg"),
                        ProductResponse(4, "Product 2-2", "This is shown in horizontal layout.", "Brand A", "Category A", 12.34, "https://cdn.dummyjson.com/product-image.jpg")
                    )
                    else -> emptyList()
                }
                Logger.debug("Loaded ${products.size} products for productType=2 at offset 0")
                return products
            } else {
                Logger.debug("Error: Invalid offset=$offset for productType=2")
                throw RuntimeException("Unsupported offset for productType=2")
            }
        } else {
            Logger.debug("Error: Unsupported productType=${case.productType}")
            throw RuntimeException("Unsupported productType")
        }
    }

    override suspend fun searchProduct(
        query: String?,
        offset: Int,
        limit: Int,
    ): List<ProductResponse> {
        if (case.productType == 0) {
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
        } else {
            throw RuntimeException()
        }
    }

    override suspend fun getProductDetail(productId: String?): ProductDetailResponse {
        if (case.isProductDetailSuccess) {
            return ProductDetailResponse(
                id = 4,
                title = "category2-1",
                description = "This is shown in horizontal layout.",
                brand = "Brand A",
                category = "Category A",
                price = 12.34,
                thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
            )
        } else {
            throw RuntimeException()
        }
    }

    private fun generateDummyProducts(start: Int, end: Int): List<ProductResponse> {
        return (start..end).map { i ->
            ProductResponse(
                id = i,
                title = "Product $i",
                description = "This is shown in horizontal layout.",
                brand = "Brand A",
                category = "Category A",
                price = 12.34,
                thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
            )
        }
    }
}