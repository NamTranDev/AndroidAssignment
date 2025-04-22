package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.flow.Flow
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse

interface ProductDetailUseCase {
    fun produceDetail(productId : String?) : Flow<ProductDetailResponse>
}