package nam.tran.home.assignment.jetpack.compose.domain.usecase

import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.param.ProductByCategoryParam
import nam.tran.home.assignment.jetpack.compose.model.param.ProductByQueryParam
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

interface LoadProductByCategoryUsecase : UseCase<ProductByCategoryParam,List<ProductResponse>>
interface LoadProductByQueryUsecase : UseCase<ProductByQueryParam,List<ProductResponse>>

class LoadProductByCategoryUseCaseImpl(
   private val productRepository: ProductRepository
): LoadProductByCategoryUsecase{
    override suspend fun execute(params: ProductByCategoryParam): List<ProductResponse> {
        return productRepository.getProductByCategory(params.category,params.offset,params.limit)
    }
}

class LoadProductByQueryUseCaseImpl(
    private val productRepository: ProductRepository
) : LoadProductByQueryUsecase{
    override suspend fun execute(params: ProductByQueryParam): List<ProductResponse> {
        return productRepository.searchProduct(query = params.query, offset = params.offset, limit = params.limit)
    }

}