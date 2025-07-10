package nam.tran.domain.usecase

import nam.tran.domain.model.entity.ProductEntity
import nam.tran.domain.model.param.ProductByCategoryParam
import nam.tran.domain.model.param.ProductByQueryParam
import nam.tran.domain.repository.ProductRepository

interface LoadProductByCategoryUsecase : UseCase<ProductByCategoryParam,List<ProductEntity>>
interface LoadProductByQueryUsecase : UseCase<ProductByQueryParam,List<ProductEntity>>

class LoadProductByCategoryUseCaseImpl(
   private val productRepository: ProductRepository
): LoadProductByCategoryUsecase{
    override suspend fun execute(params: ProductByCategoryParam): List<ProductEntity> {
        return productRepository.getProductByCategory(params.category,params.offset,params.limit)
    }
}

class LoadProductByQueryUseCaseImpl(
    private val productRepository: ProductRepository
) : LoadProductByQueryUsecase{
    override suspend fun execute(params: ProductByQueryParam): List<ProductEntity> {
        return productRepository.searchProduct(query = params.query, offset = params.offset, limit = params.limit)
    }

}