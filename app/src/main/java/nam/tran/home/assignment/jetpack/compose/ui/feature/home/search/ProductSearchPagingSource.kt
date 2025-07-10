package nam.tran.home.assignment.jetpack.compose.ui.feature.home.search

import androidx.paging.PagingSource
import androidx.paging.PagingState
import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.model.param.ProductByQueryParam
import nam.tran.domain.usecase.LoadProductByQueryUsecase

class ProductSearchPagingSource(
    private val loadProductByQueryUsecase: LoadProductByQueryUsecase,
    private val query: String?
) : PagingSource<Int, ProductEntity>() {

    override fun getRefreshKey(state: PagingState<Int, ProductEntity>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductEntity> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize
            val response = loadProductByQueryUsecase.execute(
                ProductByQueryParam(query = query, offset = offset, limit = limit)
            )

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else offset + response.size
            )
        } catch (e: Exception) {
            nam.tran.utils.Logger.debug(e)
            LoadResult.Error(e)
        }
    }
}