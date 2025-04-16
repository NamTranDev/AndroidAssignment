package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class ProductSearchPagingRepository(
    private val productRepository: ProductRepository,
){
    fun searchProducts(query: String?): Flow<PagingData<ProductResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductSearchPagingSource(productRepository,query) }
        ).flow
    }
}