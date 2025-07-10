package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface ProductSearchPagingRepository {
    fun searchProducts(query: String?): Flow<PagingData<ProductResponse>>
}