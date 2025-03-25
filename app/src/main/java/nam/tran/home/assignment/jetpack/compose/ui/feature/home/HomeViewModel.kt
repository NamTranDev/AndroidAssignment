package nam.tran.home.assignment.jetpack.compose.ui.feature.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.lifestyle.plus.utils.Logger
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
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
) : ViewModel() {

    private val _isLoadingCategoryState = MutableStateFlow(true)
    val isLoadingCategoryState: StateFlow<Boolean> = _isLoadingCategoryState

    private val _isLoadingProductState = MutableStateFlow(false)
    val isLoadingProductState: StateFlow<Boolean> = _isLoadingProductState

    private val _selectedCategoryState = MutableStateFlow<CategoryResponse?>(null)
    val selectedCategoryState: StateFlow<CategoryResponse?> = _selectedCategoryState

    private val _productsState = MutableStateFlow<List<ProductResponse>>(emptyList())
    val productsState: StateFlow<List<ProductResponse>> = _productsState

    val categoriesState: StateFlow<List<CategoryResponse>> = useCase.loadCategories()
        .onStart {
            _isLoadingCategoryState.value = true
        }
        .catch {
            _isLoadingCategoryState.value = false
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

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
        loadProducts(category.name)
    }

    private fun loadProducts(categoryName: String?) {
        viewModelScope.launch {
            useCase.loadProductByCategory(categoryName, 0)
                .onStart {
                    _isLoadingProductState.value = true
                }
                .catch { e ->
                    Logger.debug(e)
                    _isLoadingProductState.value = false
                }
                .collect { response ->
                    _isLoadingProductState.value = false
                    _productsState.value = response
                }
        }
    }
}