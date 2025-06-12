package nam.tran.domain.repository

import kotlinx.coroutines.flow.Flow
import nam.tran.domain.model.entity.ProductEntity

interface BookmarkRepository{
    val bookmarkedProducts : Flow<List<ProductEntity>>
    suspend fun toggleBookmark(productResponse: ProductEntity?)
}