package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByQueryUsecase
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class ProductSearchPagingRepositoryImpl(
    private val loadProductByQueryUsecase: LoadProductByQueryUsecase
) : ProductSearchPagingRepository{
    override fun searchProducts(query: String?): Flow<PagingData<ProductResponse>> {
        return Pager(
            config = PagingConfig(
                initialLoadSize = 10,
                pageSize = 10,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ProductSearchPagingSource(loadProductByQueryUsecase,query) }
        ).flow
    }
}