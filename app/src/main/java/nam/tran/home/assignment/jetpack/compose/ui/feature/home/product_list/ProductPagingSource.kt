package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.lifestyle.plus.utils.Logger
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.domain.usecase.LoadProductByCategoryUsecase
import nam.tran.home.assignment.jetpack.compose.model.param.ProductByCategoryParam
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class ProductPagingSource(
    private val loadProductByCategoryUsecase: LoadProductByCategoryUsecase,
    private val category: String?
) : PagingSource<Int, ProductResponse>() {

    override fun getRefreshKey(state: PagingState<Int, ProductResponse>): Int? {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ProductResponse> {
        return try {
            val offset = params.key ?: 0
            val limit = params.loadSize
            val response = loadProductByCategoryUsecase.execute(
                ProductByCategoryParam(category = category, offset = offset,limit = limit)
            )

            LoadResult.Page(
                data = response,
                prevKey = null,
                nextKey = if (response.isEmpty()) null else offset + response.size
            )
        } catch (e: Exception) {
            Logger.debug(e)
            LoadResult.Error(e)
        }
    }
}