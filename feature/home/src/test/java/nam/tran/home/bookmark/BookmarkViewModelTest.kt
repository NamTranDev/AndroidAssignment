package nam.tran.home.bookmark

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.rules.MainDispatcherRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class BookmarkViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val loadBookmarkProductUseCase: LoadBookmarkProductUseCase = Mockito.mock()

    @Before
    fun setUp() {
    }

    @Test
    fun `load list product bookmark`() = runTest {
        val bookmarkedProducts = listOf(
            ProductEntity(1, "Title1", "Desc1", "Brand1", "Category1", 10.0, "thumb1"),
            ProductEntity(2, "Title2", "Desc2", "Brand2", "Category2", 20.0, "thumb2")
        )

        Mockito.`when`(loadBookmarkProductUseCase.execute(Unit))
            .thenReturn(flowOf(bookmarkedProducts))

        val viewModel = BookmarkViewModel(loadBookmarkProductUseCase)

        val product = viewModel.bookmarkedProducts.first()

        Assert.assertEquals(bookmarkedProducts, product)
    }
}