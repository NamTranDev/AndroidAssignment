package nam.tran.home.assignment.jetpack.compose.viewmodel

import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.home.assignment.jetpack.compose.MainDispatcherRule
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark.BookmarkViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock

class BookmarkViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val loadBookmarkProductUseCase: LoadBookmarkProductUseCase = mock()

    @Before
    fun setUp() {
    }

    @Test
    fun `load list product bookmark`() = runTest {
        val bookmarkedProducts = listOf(
            ProductEntity(1, "Title1", "Desc1", "Brand1", "Category1", 10.0, "thumb1"),
            ProductEntity(2, "Title2", "Desc2", "Brand2", "Category2", 20.0, "thumb2")
        )

        Mockito.`when`(loadBookmarkProductUseCase.execute(Unit)).thenReturn(flowOf(bookmarkedProducts))

        val viewModel = BookmarkViewModel(loadBookmarkProductUseCase)

        val product = viewModel.bookmarkedProducts.first()

        assertEquals(bookmarkedProducts, product)
    }
}