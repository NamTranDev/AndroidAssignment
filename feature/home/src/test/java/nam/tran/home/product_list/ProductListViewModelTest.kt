package nam.tran.home.product_list

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
import nam.tran.rules.MainDispatcherRule
import nam.tran.ui_state.StatusState
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@OptIn(ExperimentalCoroutinesApi::class)
class ProductListViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val categoryUseCase: LoadCategoryUseCase = Mockito.mock()
    private val productPagingRepositoryImpl: ProductPagingRepositoryImpl = Mockito.mock()
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

        Assert.assertEquals(StatusState.Success, viewModel.statusStateCategory.value)
        Assert.assertEquals(categories, viewModel.categoriesDataState.value)
        Assert.assertEquals(categories.first(), viewModel.selectedCategoryState.value)
    }

    @Test
    fun `loadCategories should set error status when exception occurs`() = runTest {
        val exception = RuntimeException("Error loading categories")
        Mockito.`when`(categoryUseCase.execute(Unit)).thenThrow(exception)

        viewModel.loadCategories()

        val status = viewModel.statusStateCategory.value
        Assert.assertTrue(status is StatusState.Error)
        Assert.assertEquals(exception, (status as StatusState.Error).error)
    }

    @Test
    fun `selectCategory should update selectedCategoryState`() {
        val category = CategoryEntity(slug = "home", name = "Home")

        viewModel.selectCategory(category)

        Assert.assertEquals(category, viewModel.selectedCategoryState.value)
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
        val fakeFlowPaging = flowOf(PagingData.Companion.from(products))

        Mockito.`when`(productPagingRepositoryImpl.getProducts("electronics"))
            .thenReturn(fakeFlowPaging)

        val category = CategoryEntity(slug = "electronics", name = "Electronics")
        viewModel.selectCategory(category)

        val result = async {
            viewModel.productsState.first()
        }

        advanceUntilIdle()

        val pagingData = result.await()
        Assert.assertNotNull(pagingData)
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

        Assert.assertTrue(collected.isEmpty())

        viewModel.selectCategory(categoryWithNullSlug)

        advanceUntilIdle()

        Assert.assertTrue(collected.isEmpty())

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
        val pagingData = PagingData.Companion.from(products)

        Mockito.`when`(productPagingRepositoryImpl.getProducts("electronics"))
            .thenReturn(flowOf(pagingData))

        viewModel.selectCategory(category)

        val collectedFirst = mutableListOf<PagingData<ProductEntity>>()
        val job1 = launch {
            viewModel.productsState.toList(collectedFirst)
        }

        advanceUntilIdle()

        Assert.assertEquals(1, collectedFirst.size)
        Mockito.verify(productPagingRepositoryImpl).getProducts("electronics")

        val collectedSecond = mutableListOf<PagingData<ProductEntity>>()
        val job2 = launch {
            viewModel.productsState.toList(collectedSecond)
        }

        advanceUntilIdle()

        Assert.assertEquals(1, collectedSecond.size)
        Mockito.verify(productPagingRepositoryImpl, Mockito.times(1)).getProducts("electronics")

        job1.cancel()
        job2.cancel()
    }

    @Test
    fun `scrollState should provide default LazyListState when no scroll state`() = runTest {
        val category = CategoryEntity(slug = "electronics", name = "Electronics")
        viewModel.selectCategory(category)

        val scrollState = viewModel.scrollState.first()
        Assert.assertEquals(0, scrollState.firstVisibleItemIndex)
    }
}