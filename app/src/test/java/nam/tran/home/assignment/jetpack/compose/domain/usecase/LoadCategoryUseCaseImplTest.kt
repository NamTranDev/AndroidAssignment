package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoadCategoryUseCaseImplTest {

    private lateinit var useCase : LoadCategoryUseCaseImpl
    private var repository = Mockito.mock<CategoryRepository>()

    @Before
    fun setUp() {
        useCase = LoadCategoryUseCaseImpl(repository)
    }

    @Test
    fun `should return categories when execute is called`() = runTest {
        val expectedCategories = listOf(
            CategoryResponse(slug = "1", name = "Category 1"),
            CategoryResponse(slug = "2", name = "Category 2")
        )

        Mockito.`when`(repository.getCategories()).thenReturn(expectedCategories)

        val result = useCase.execute(Unit)

        assertEquals(expectedCategories, result)
    }

    @Test(expected = Exception::class)
    fun `should throw exception when repository fails`() = runTest {
        Mockito.`when`(repository.getCategories()).thenThrow(Exception("Repository Error"))

        useCase.execute(Unit)
    }
}