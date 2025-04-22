package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse

class ProductDetailUseCaseImpl(
    private val productRepository: ProductRepository
) : ProductDetailUseCase {

    override fun produceDetail(productId: String?): Flow<ProductDetailResponse> {
        return flow {
            delay(2000)
            val productDetail = productRepository.getProductDetail(productId)
            emit(productDetail)
        }
    }
}