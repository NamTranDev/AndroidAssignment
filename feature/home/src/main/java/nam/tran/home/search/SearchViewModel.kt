package nam.tran.home.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val productSearchPagingRepository: ProductSearchPagingRepository,
) : ViewModel() {

    private val _searchState = MutableStateFlow("")
    val searchState: StateFlow<String> = _searchState

    @OptIn(FlowPreview::class)
    val productState = searchState
        .debounce(300)
        .distinctUntilChanged()
        .flatMapLatest {
            productSearchPagingRepository.searchProducts(it)
        }
        .cachedIn(viewModelScope)

    fun updateSearch(it: String) {
        _searchState.value = it
    }
}