package nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadBookmarkProductUseCase
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    loadBookmarkProductUseCase: LoadBookmarkProductUseCase
) : ViewModel() {

    val bookmarkedProducts = loadBookmarkProductUseCase.execute(Unit)
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}