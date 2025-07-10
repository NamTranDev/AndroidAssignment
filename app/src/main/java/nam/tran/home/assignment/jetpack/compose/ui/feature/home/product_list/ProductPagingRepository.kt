package nam.tran.home.assignment.jetpack.compose.ui.feature.home.product_list

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface ProductPagingRepository {
    fun getProducts(category: String?): Flow<PagingData<ProductResponse>>
}