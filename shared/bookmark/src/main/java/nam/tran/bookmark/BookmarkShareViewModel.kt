package nam.tran.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.domain.usecase.ToggleBookmarkProductUseCase
import javax.inject.Inject

@HiltViewModel
class BookmarkShareViewModel @Inject constructor(
    loadBookmarkProductUseCase: LoadBookmarkProductUseCase,
    private val toggleBookmarkProductUseCase: ToggleBookmarkProductUseCase
) : ViewModel() {

    val bookmarkedIds = loadBookmarkProductUseCase.execute(Unit).map { it ->
        it.map { it.id }.toSet()
    }.stateIn(viewModelScope, SharingStarted.Companion.Lazily, emptyList())

    fun toggleBookmark(product: ProductEntity?) {
        viewModelScope.launch(Dispatchers.IO) {
            toggleBookmarkProductUseCase.execute(product)
        }
    }
}