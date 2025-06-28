package nam.tran.home.product_list

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.domain.model.entity.ProductEntity

interface ProductPagingRepository {
    fun getProducts(category: String?): Flow<PagingData<ProductEntity>>
}