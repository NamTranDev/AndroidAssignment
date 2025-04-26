package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.snapshotFlow
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadCategoryUseCase
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import nam.tran.home.assignment.jetpack.compose.model.ui.StatusState
import javax.inject.Inject

@HiltViewModel
class ProductListViewModel @Inject constructor(
    private val categoryUseCase: LoadCategoryUseCase,
    private val productPagingRepository: ProductPagingRepository
) : ViewModel() {

    private val _statusStateCategory = MutableStateFlow<StatusState>(StatusState.Loading)
    val statusStateCategory: StateFlow<StatusState> = _statusStateCategory

    private val _categoriesDataState = MutableStateFlow<List<CategoryResponse>>(emptyList())
    val categoriesDataState: StateFlow<List<CategoryResponse>> = _categoriesDataState

    private val _selectedCategoryState = MutableStateFlow<CategoryResponse?>(null)
    val selectedCategoryState: StateFlow<CategoryResponse?> = _selectedCategoryState

    private val productCache = mutableMapOf<String, Flow<PagingData<ProductResponse>>>()

    private val _scrollStates = mutableMapOf<String, MutableStateFlow<LazyListState>>()
    val scrollState = _selectedCategoryState.map {
        it?.slug
    }.filter {
        it?.isNotEmpty() == true
    }.flatMapLatest {
        _scrollStates.getOrPut(it!!) {
            MutableStateFlow(LazyListState())
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, LazyListState())

    val currentIndicator = scrollState.flatMapLatest { state ->
        snapshotFlow {
            val firstVisibleIndex = state.firstVisibleItemIndex
            val layoutInfo = state.layoutInfo
            val visibleItems = layoutInfo.visibleItemsInfo
            val lastFullyVisibleItem =
                visibleItems.lastOrNull { it.offset >= 0 && it.offset + it.size <= layoutInfo.viewportEndOffset }
            val index = lastFullyVisibleItem?.index?.plus(1) ?: (firstVisibleIndex + 1)
            index - 1
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, 0)

    val productsState = _selectedCategoryState.filter {
        it?.slug?.isNotEmpty() == true
    }.flatMapLatest {
        val slug = it?.slug ?: return@flatMapLatest flowOf<PagingData<ProductResponse>>(PagingData.empty())

        productCache.getOrPut(slug){
            productPagingRepository.getProducts(slug).cachedIn(viewModelScope)
        }
    }

    init {
        loadCategories()
    }

    fun loadCategories() {
        viewModelScope.launch {
            try {
                _statusStateCategory.value = StatusState.Loading
                val categories = categoryUseCase.execute(Unit)
                _categoriesDataState.value = categories
                selectCategory(categories.get(0))
                _statusStateCategory.value = StatusState.Success
            } catch (e: Exception) {
                _statusStateCategory.value = StatusState.Error(error = e)
            }
        }
    }

    fun selectCategory(category: CategoryResponse) {
        _selectedCategoryState.value = category
    }
}