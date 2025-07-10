package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.runTest
import nam.tran.home.assignment.jetpack.compose.data.local.model.ProductEntity
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito

class LoadBookmarkProductUseCaseImplTest {

    private lateinit var usecase : LoadBookmarkProductUseCaseImpl
    private var repository = Mockito.mock<BookmarkRepository>()

    @Before
    fun setUp() {
        usecase = LoadBookmarkProductUseCaseImpl(repository)
    }

    @Test
    fun `should return mapped list of product responses when execute is called`() = runTest {
        // Arrange: Prepare mock data and mock the repository's response
        val mockBookmarkedProducts = listOf(
            ProductEntity(
                id = 1,
                title = "Product 1",
                description = "Description 1",
                brand = "Brand 1",
                category = "Category 1",
                price = 100.0,
                thumbnail = "thumbnail_1.jpg"
            ),
            ProductEntity(
                id = 2,
                title = "Product 2",
                description = "Description 2",
                brand = "Brand 2",
                category = "Category 2",
                price = 200.0,
                thumbnail = "thumbnail_2.jpg"
            )
        )

        Mockito.`when`(repository.bookmarkedProducts).thenReturn(flowOf(mockBookmarkedProducts))

        val result = usecase.execute(Unit).first()

        val expected = listOf(
            ProductResponse(
                id = 1,
                title = "Product 1",
                description = "Description 1",
                brand = "Brand 1",
                category = "Category 1",
                price = 100.0,
                thumbnail = "thumbnail_1.jpg"
            ),
            ProductResponse(
                id = 2,
                title = "Product 2",
                description = "Description 2",
                brand = "Brand 2",
                category = "Category 2",
                price = 200.0,
                thumbnail = "thumbnail_2.jpg"
            )
        )

        assertEquals(expected, result)
    }

    @Test(expected = RuntimeException::class)
    fun `should throw exception when repository fails`() = runTest {
        Mockito.`when`(repository.bookmarkedProducts).thenThrow(RuntimeException("Repository Error"))

        usecase.execute(Unit)
    }
}