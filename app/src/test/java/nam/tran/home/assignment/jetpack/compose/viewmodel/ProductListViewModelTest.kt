package nam.tran.home.assignment.jetpack.compose.viewmodel

import androidx.paging.PagingData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nam.tran.domain.model.entity.CategoryEntity
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.usecase.LoadCategoryUseCase
import nam.tran.home.assignment.jetpack.compose.MainDispatcherRule
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductListViewModel
import nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list.ProductPagingRepositoryImpl
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val categoryUseCase: LoadCategoryUseCase = mock()
    private val productPagingRepositoryImpl: ProductPagingRepositoryImpl = mock()
    private lateinit var viewModel: ProductListViewModel

    @Before
    fun setUp() {
        viewModel = ProductListViewModel(categoryUseCase, productPagingRepositoryImpl)
    }

    @Test
    fun `loadCategories should load categories and set status to success`() = runTest {
        val categories = listOf(
            CategoryEntity(slug = "electronics", name = "Electronics"),
            CategoryEntity(slug = "fashion", name = "Fashion")
        )

        Mockito.`when`(categoryUseCase.execute(Unit)).thenReturn(categories)

        viewModel.loadCategories()

        advanceUntilIdle()

        assertEquals(StatusState.Success, viewModel.statusStateCategory.value)
        assertEquals(categories, viewModel.categoriesDataState.value)
        assertEquals(categories.first(), viewModel.selectedCategoryState.value)
    }

    @Test
    fun `loadCategories should set error status when exception occurs`() = runTest {
        val exception = RuntimeException("Error loading categories")
        Mockito.`when`(categoryUseCase.execute(Unit)).thenThrow(exception)

        viewModel.loadCategories()

        val status = viewModel.statusStateCategory.value
        assertTrue(status is StatusState.Error)
        assertEquals(exception, (status as StatusState.Error).error)
    }

    @Test
    fun `selectCategory should update selectedCategoryState`() {
        val category = CategoryEntity(slug = "home", name = "Home")

        viewModel.selectCategory(category)

        assertEquals(category, viewModel.selectedCategoryState.value)
    }

    @Test
    fun `productsState should return product list when category selected`() = runTest {
        val products = listOf(
            ProductEntity(
                id = 1,
                title = "Phone",
                description = "Latest phone",
                brand = "Brand X",
                category = "electronics",
                price = 999.99,
                thumbnail = "https://image.jpg"
            )
        )
        val fakeFlowPaging = flowOf(PagingData.from(products))

        Mockito.`when`(productPagingRepositoryImpl.getProducts("electronics")).thenReturn(fakeFlowPaging)

        val category = CategoryEntity(slug = "electronics", name = "Electronics")
        viewModel.selectCategory(category)

        val result = async {
            viewModel.productsState.first()
        }

        advanceUntilIdle()

        val pagingData = result.await()
        assertNotNull(pagingData)
    }

    @Test
    fun `productsState should emit empty when slug is null or empty`() = runTest {
        val categoryWithEmptySlug = CategoryEntity(slug = "", name = "Empty")
        val categoryWithNullSlug = CategoryEntity(slug = null, name = "Null")

        viewModel.selectCategory(categoryWithEmptySlug)

        val collected = mutableListOf<PagingData<ProductEntity>>()
        val job = launch {
            viewModel.productsState.toList(collected)
        }

        advanceUntilIdle()

        assertTrue(collected.isEmpty())

        viewModel.selectCategory(categoryWithNullSlug)

        advanceUntilIdle()

        assertTrue(collected.isEmpty())

        job.cancel()
    }

    @Test
    fun `productsState should cache products for each category`() = runTest {
        val category = CategoryEntity(slug = "electronics", name = "Electronics")

        val products = listOf(
            ProductEntity(
                id = 1,
                title = "Phone",
                description = "Latest phone",
                brand = "Brand X",
                category = "electronics",
                price = 999.99,
                thumbnail = "https://image.jpg"
            )
        )
        val pagingData = PagingData.from(products)

        Mockito.`when`(productPagingRepositoryImpl.getProducts("electronics")).thenReturn(flowOf(pagingData))

        viewModel.selectCategory(category)

        val collectedFirst = mutableListOf<PagingData<ProductEntity>>()
        val job1 = launch {
            viewModel.productsState.toList(collectedFirst)
        }

        advanceUntilIdle()

        assertEquals(1, collectedFirst.size)
        verify(productPagingRepositoryImpl).getProducts("electronics")

        val collectedSecond = mutableListOf<PagingData<ProductEntity>>()
        val job2 = launch {
            viewModel.productsState.toList(collectedSecond)
        }

        advanceUntilIdle()

        assertEquals(1, collectedSecond.size)
        verify(productPagingRepositoryImpl, times(1)).getProducts("electronics")

        job1.cancel()
        job2.cancel()
    }

    @Test
    fun `scrollState should provide default LazyListState when no scroll state`() = runTest {
        val category = CategoryEntity(slug = "electronics", name = "Electronics")
        viewModel.selectCategory(category)

        val scrollState = viewModel.scrollState.first()
        assertEquals(0, scrollState.firstVisibleItemIndex)
    }
}