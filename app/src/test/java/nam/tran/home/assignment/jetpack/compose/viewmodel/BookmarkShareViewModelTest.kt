package nam.tran.home.assignment.jetpack.compose.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.domain.usecase.ToggleBookmarkProductUseCase
import nam.tran.home.assignment.jetpack.compose.MainDispatcherRule
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark.BookmarkShareViewModel
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


class BookmarkShareViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val loadBookmarkProductUseCase: LoadBookmarkProductUseCase = mock()
    private val toggleBookmarkProductUseCase: ToggleBookmarkProductUseCase = mock()

    @Before
    fun setup() {}

    @Test
    fun `bookmarkedIds should emit set of ids`() = runTest {
        val bookmarkedProducts = listOf(
            ProductEntity(1, "Title1", "Desc1", "Brand1", "Category1", 10.0, "thumb1"),
            ProductEntity(2, "Title2", "Desc2", "Brand2", "Category2", 20.0, "thumb2")
        )

        Mockito.`when`(loadBookmarkProductUseCase.execute(Unit)).thenReturn(flowOf(bookmarkedProducts))

        val viewModel = BookmarkShareViewModel(loadBookmarkProductUseCase, toggleBookmarkProductUseCase)

        val ids = viewModel.bookmarkedIds.first()

        assertEquals(setOf(1, 2), ids)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `toggleBookmark should call toggleBookmarkProductUseCase`() = runTest {
        val product = ProductEntity(1, "Title1", "Desc1", "Brand1", "Category1", 10.0, "thumb1")

        val viewModel = BookmarkShareViewModel(loadBookmarkProductUseCase, toggleBookmarkProductUseCase)
        viewModel.toggleBookmark(product)

        advanceUntilIdle()

        verify(toggleBookmarkProductUseCase).execute(product)
    }
}