package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.param.ProductByCategoryParam
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoadProductByCategoryUseCaseImplTest {

    private lateinit var usecase: LoadProductByCategoryUseCaseImpl
    private var repository = Mockito.mock<ProductRepository>()

    @Before
    fun setUp() {
        usecase = LoadProductByCategoryUseCaseImpl(repository)
    }

    @Test
    fun `should return product list by category when execute is called`() = runTest {
        val expectedProducts = listOf(
            ProductResponse(
                id = 1,
                title = "Horizontal Product",
                description = "This is shown in horizontal layout.",
                brand = "Brand A",
                category = "Category A",
                price = 12.34,
                thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
            ),
            ProductResponse(
                id = 2,
                title = "Horizontal Product",
                description = "This is shown in horizontal layout.",
                brand = "Brand A",
                category = "Category A",
                price = 12.34,
                thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
            )
        )

        val category = "category"
        val limit = 2
        val offset = 1

        Mockito.`when`(repository.getProductByCategory(category, offset, limit))
            .thenReturn(expectedProducts)

        val result = usecase.execute(ProductByCategoryParam(category, offset, limit))
        Mockito.verify(repository).getProductByCategory(category, offset, limit)

        assertEquals(expectedProducts, result)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw exception when repository fails`() = runTest {
        val category = "category"
        val limit = 2
        val offset = 1
        Mockito.`when`(repository.getProductByCategory(category, offset, limit))
            .thenThrow(RuntimeException("Repository Error"))

        usecase.execute(ProductByCategoryParam(category, offset, limit))
    }
}