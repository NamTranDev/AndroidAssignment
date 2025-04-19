package nam.tran.home.assignment.jetpack.compose.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.domain.repository.ProductRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse

class HomeUseCaseImpl(
    private val categoryRepository: CategoryRepository,
): HomeUseCase {
    override fun loadCategories(): Flow<List<CategoryResponse>> {
        return flow {
            val categories = categoryRepository.getCategories()
            emit(categories)
        }.flowOn(Dispatchers.IO)
    }
}