package nam.tran.home.assignment.jetpack.compose.ui.feature.home.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nam.tran.home.assignment.jetpack.compose.domain.repository.BookmarkRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    bookmarkRepository: BookmarkRepository
) : ViewModel() {

    val bookmarkedProducts = bookmarkRepository.bookmarkedProducts.map { it ->
        it.map {
            ProductResponse(
                id = it.id,
                title = it.title,
                description = it.description,
                brand = it.brand,
                category = it.category,
                price = it.price,
                thumbnail = it.thumbnail
            )
        }
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}