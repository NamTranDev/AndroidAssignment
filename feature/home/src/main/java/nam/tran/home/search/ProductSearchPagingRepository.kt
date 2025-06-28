package nam.tran.home.search

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.domain.model.entity.ProductEntity

interface ProductSearchPagingRepository {
    fun searchProducts(query: String?): Flow<PagingData<ProductEntity>>
}