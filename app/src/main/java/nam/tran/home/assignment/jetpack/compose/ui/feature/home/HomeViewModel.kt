package nam.tran.home.assignment.jetpack.compose.ui.feature.home

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lifestyle.plus.utils.Logger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nam.tran.home.assignment.jetpack.compose.domain.usecase.HomeUseCase
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase: HomeUseCase,
    private val productPagingRepository: ProductPagingRepository
) : ViewModel() {

    private val _isLoadingCategoryState = MutableStateFlow(true)
    val isLoadingCategoryState: StateFlow<Boolean> = _isLoadingCategoryState

    private val _selectedCategoryState = MutableStateFlow<CategoryResponse?>(null)
    val selectedCategoryState: StateFlow<CategoryResponse?> = _selectedCategoryState

    private val productCache = mutableMapOf<String, Flow<PagingData<ProductResponse>>>()

    private val _scrollStates = mutableMapOf<String, MutableStateFlow<LazyListState>>()
    val scrollState = _selectedCategoryState.map {
        it?.slug
    }.filter {
        it?.isNotEmpty() == true
    }.flatMapLatest {
        _scrollStates.getOrPut(it!!){
            MutableStateFlow(LazyListState())
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, LazyListState())

    val categoriesState = useCase.loadCategories()
        .onStart {
            _isLoadingCategoryState.value = true
        }
        .catch {
            _isLoadingCategoryState.value = false
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val productsState = _selectedCategoryState.filter {
        it?.slug?.isNotEmpty() == true
    }.flatMapLatest {
        val slug = it?.slug ?: return@flatMapLatest flowOf<PagingData<ProductResponse>>(PagingData.empty())

        productCache.getOrPut(slug){
            productPagingRepository.getProducts(slug).cachedIn(viewModelScope)
        }
    }

    init {
        viewModelScope.launch {
            categoriesState.collect { categories ->
                _isLoadingCategoryState.value = false
                if (categories.isNotEmpty() && selectedCategoryState.value == null) {
                    selectCategory(categories.first())
                }
            }
        }
    }

    fun selectCategory(category: CategoryResponse) {
        _selectedCategoryState.value = category
    }
}