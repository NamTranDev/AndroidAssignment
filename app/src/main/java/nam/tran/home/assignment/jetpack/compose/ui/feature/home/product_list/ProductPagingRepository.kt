package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class ProductPagingRepository(
    private val productRepository: ProductRepository,
){
    fun getProducts(category: String?): Flow<PagingData<ProductResponse>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductPagingSource(productRepository,category) }
        ).flow
    }
}