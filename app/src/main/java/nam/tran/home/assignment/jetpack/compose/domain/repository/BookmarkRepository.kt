package nam.tran.home.assignment.jetpack.compose.domain.repository

import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.data.local.model.ProductEntity
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface BookmarkRepository{
    val bookmarkedProducts : Flow<List<ProductEntity>>
    suspend fun toggleBookmark(productResponse: ProductResponse?)
}