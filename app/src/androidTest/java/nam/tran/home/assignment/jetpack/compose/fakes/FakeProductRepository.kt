package nam.tran.home.assignment.jetpack.compose.fakes

import kotlinx.coroutines.delay
import nam.tran.domain.model.entity.ProductDetailEntity
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.repository.ProductRepository

class FakeProductRepository(
    private val case: CaseTest,
) : ProductRepository {

    override suspend fun getProductByCategory(
        category: String?,
        offset: Int,
        limit: Int,
    ): List<ProductEntity> {
        nam.tran.utils.Logger.debug("Fetching products | category=$category, offset=$offset, limit=$limit")

        delay(1000)

        if (case.productType == CaseTest.ProductType.Success) {
            return when (category) {
                "category1" -> {
                    val products = when (offset) {
                        0 -> generateDummyProducts(1, 10)
                        10 -> generateDummyProducts(11, 20)
                        else -> emptyList()
                    }
                    nam.tran.utils.Logger.debug("Loaded ${products.size} products for category1 at offset $offset")
                    products
                }

                "category2" -> {
                    val products = when (offset) {
                        0 -> generateDummyProducts(30, 40)
                        10 -> generateDummyProducts(40, 50)
                        else -> emptyList()
                    }
                    nam.tran.utils.Logger.debug("Loaded ${products.size} products for category1 at offset $offset")
                    products
                }

                else -> {
                    nam.tran.utils.Logger.debug("Unknown category: $category")
                    emptyList()
                }
            }
        } else if(case.productType == CaseTest.ProductType.SuccessButEmpty){
            return emptyList()
        } else if (case.productType == CaseTest.ProductType.LoadMoreError) {
            if (offset == 0) {
                val products = when (category) {
                    "category1" -> generateDummyProducts(1, 10)
                    "category2" -> listOf(
                        ProductEntity(3, "Product 2", "This is shown in horizontal layout.", "Brand A", "Category A", 12.34, "https://cdn.dummyjson.com/product-image.jpg"),
                        ProductEntity(4, "Product 2-2", "This is shown in horizontal layout.", "Brand A", "Category A", 12.34, "https://cdn.dummyjson.com/product-image.jpg")
                    )
                    else -> emptyList()
                }
                nam.tran.utils.Logger.debug("Loaded ${products.size} products for productType=2 at offset 0")
                return products
            } else {
                nam.tran.utils.Logger.debug("Error: Invalid offset=$offset for productType=2")
                throw RuntimeException("Unsupported offset for productType=2")
            }
        } else {
            nam.tran.utils.Logger.debug("Error: Unsupported productType=${case.productType}")
            throw RuntimeException("Unsupported productType")
        }
    }

    override suspend fun searchProduct(
        query: String?,
        offset: Int,
        limit: Int,
    ): List<ProductEntity> {
        if (case.productType == CaseTest.ProductType.Success) {
            return when (query) {
                "" -> {
                    val products = when (offset) {
                        0 -> generateDummyProducts(1, 10)
                        10 -> generateDummyProducts(11, 20)
                        20 -> generateDummyProducts(21, 30)
                        else -> emptyList()
                    }
                    products
                }
                "a" -> {
                    val products = when (offset) {
                        0 -> generateDummyProducts(1, 10, search = "a")
                        else -> emptyList()
                    }
                    products
                }

                else -> emptyList()
            }
        } else if (case.productType == CaseTest.ProductType.SuccessButEmpty){
            return emptyList()
        } else if (case.productType == CaseTest.ProductType.LoadMoreError) {
            if (offset == 0) {
                return generateDummyProducts(1, 10,search = query ?: "")
            } else {
                throw RuntimeException("Unsupported offset == 2")
            }
        } else {
            throw RuntimeException()
        }
    }

    override suspend fun getProductDetail(productId: String?): ProductDetailEntity {
        if (case.productType == CaseTest.ProductType.Success) {
            return ProductDetailEntity(
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

    private fun generateDummyProducts(start: Int, end: Int,search : String = ""): List<ProductEntity> {
        return (start..end).map { i ->
            ProductEntity(
                id = i,
                title = "Product $i$search",
                description = "This is shown in horizontal layout.",
                brand = "Brand A",
                category = "Category A",
                price = 12.34,
                thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
            )
        }
    }
}