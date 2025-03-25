package nam.tran.home.assignment.jetpack.compose.domain.usecase

import io.lifestyle.plus.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse
import nam.tran.home.assignment.jetpack.compose.model.response.ProductResponse

class HomeUseCaseImpl(
    private val categoryRepository: CategoryRepository,
    private val productRepository: ProductRepository,
): HomeUseCase {
    override fun loadCategories(): Flow<List<CategoryResponse>> {
        return flow {
            val categories = categoryRepository.getCategories()
            emit(categories)
        }.flowOn(Dispatchers.IO)
    }
}