package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

data class SearchUiState(
    val query: Flow<String> = flowOf(""),
    val productsFlow: Flow<PagingData<ProductResponse>> = emptyFlow()
)