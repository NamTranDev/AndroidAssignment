package nam.tran.domain.usecase

import nam.tran.domain.model.entity.ProductDetailEntity
import nam.tran.domain.repository.ProductRepository

interface ProductDetailUseCase : UseCase<String?, ProductDetailEntity?>

class ProductDetailUseCaseImpl(
    private val productRepository: ProductRepository
) : ProductDetailUseCase {
    override suspend fun execute(params: String?): ProductDetailEntity {
        return productRepository.getProductDetail(params)
    }
}