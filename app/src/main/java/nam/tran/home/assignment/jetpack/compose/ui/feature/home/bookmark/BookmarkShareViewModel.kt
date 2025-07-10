package nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.home.assignment.jetpack.compose.domain.usecase.ToggleBookmarkProductUseCase
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import javax.inject.Inject

@HiltViewModel
class BookmarkShareViewModel @Inject constructor(
    loadBookmarkProductUseCase: LoadBookmarkProductUseCase,
    private val toggleBookmarkProductUseCase: ToggleBookmarkProductUseCase
) : ViewModel() {

    val bookmarkedIds = loadBookmarkProductUseCase.execute(Unit).map { it ->
        it.map { it.id }.toSet()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleBookmark(product: ProductResponse?) {
        viewModelScope.launch(Dispatchers.IO) {
            toggleBookmarkProductUseCase.execute(product)
        }
    }
}