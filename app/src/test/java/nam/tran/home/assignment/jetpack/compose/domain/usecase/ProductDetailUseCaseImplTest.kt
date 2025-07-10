package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class ProductDetailUseCaseImplTest {

    private lateinit var useCase : ProductDetailUseCaseImpl
    private var repository = Mockito.mock<ProductRepository>()

    @Before
    fun setUp() {
        useCase = ProductDetailUseCaseImpl(repository)
    }

    @Test
    fun `should return product detail when execute is called`() = runTest {
        val productDetail = ProductDetailResponse(
            id = 1,
            title = "Essence Mascara Lash Princess",
            description = "The Essence Mascara Lash Princess is a popular mascara known for its volumizing and lengthening effects. Achieve dramatic lashes with this long-lasting and cruelty-free formula.",
            brand = "Essence",
            price = 8.0,
            discountPercentage = 7.7
        )

        val id = "1"

        Mockito.`when`(repository.getProductDetail(id)).thenReturn(productDetail)

        val result = useCase.execute(id)

        assertEquals(productDetail, result)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw exception when repository fails`() = runTest {
        val id = "1"
        Mockito.`when`(repository.getProductDetail(id)).thenThrow(RuntimeException("Repository Error"))

        useCase.execute(id)
    }
}