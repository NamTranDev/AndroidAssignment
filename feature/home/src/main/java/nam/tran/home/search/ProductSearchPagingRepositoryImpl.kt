package nam.tran.home.search

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.usecase.LoadProductByQueryUsecase

class ProductSearchPagingRepositoryImpl(
    private val loadProductByQueryUsecase: LoadProductByQueryUsecase
) : ProductSearchPagingRepository{
    override fun searchProducts(query: String?): Flow<PagingData<ProductEntity>> {
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