package nam.tran.home.assignment.jetpack.compose.domain.usecase

import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.ProductDetailResponse

interface ProductDetailUseCase : UseCase<String?,ProductDetailResponse?>

class ProductDetailUseCaseImpl(
    private val productRepository: ProductRepository
) : ProductDetailUseCase {
    override suspend fun execute(params: String?): ProductDetailResponse {
        return productRepository.getProductDetail(params)
    }
}