package nam.tran.home.assignment.jetpack.compose.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.home.assignment.jetpack.compose.MainDispatcherRule
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.ProductSearchPagingRepositoryImpl
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.search.SearchViewModel
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class SearchViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val productSearchPagingRepositoryImpl: ProductSearchPagingRepositoryImpl = mock()

    @Before
    fun setup() {}

    @Test
    fun `updateSearch should update searchState`() = runTest {
        val viewModel = SearchViewModel(productSearchPagingRepositoryImpl)
        viewModel.updateSearch("phone")

        assertEquals("phone", viewModel.searchState.value)
    }

    @Test
    fun `productState should call searchProducts with debounced input`() = runTest {

        val products = listOf(
            ProductEntity(
                id = 1,
                title = "Horizontal Product",
                description = "This is shown in horizontal layout.",
                brand = "Brand A",
                category = "Category A",
                price = 12.34,
                thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
            ),
            ProductEntity(
                id = 2,
                title = "Horizontal Product",
                description = "This is shown in horizontal layout.",
                brand = "Brand A",
                category = "Category A",
                price = 12.34,
                thumbnail = "https://cdn.dummyjson.com/product-image.jpg"
            )
        )

        val fakeFlow = flowOf(PagingData.from(products))

        val query = "laptop"
        Mockito.`when`(productSearchPagingRepositoryImpl.searchProducts(query)).thenReturn(fakeFlow)

        val viewModel = SearchViewModel(productSearchPagingRepositoryImpl)

        val result = async {
            viewModel.productState.first()
        }

        viewModel.updateSearch("abc")
        advanceTimeBy(200)

        viewModel.updateSearch(query)
        advanceTimeBy(300)
        advanceUntilIdle()

        verify(productSearchPagingRepositoryImpl, times(1)).searchProducts(query)
        verify(productSearchPagingRepositoryImpl, never()).searchProducts("abc")

        val pagingData = result.await()
        assertNotNull(pagingData)
    }
}