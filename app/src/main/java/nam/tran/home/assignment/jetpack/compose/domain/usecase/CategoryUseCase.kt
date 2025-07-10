package nam.tran.home.assignment.jetpack.compose.domain.usecase

import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse

interface LoadCategoryUseCase : UseCase<Unit,List<CategoryResponse>>

class LoadCategoryUseCaseImpl(
    private val categoryRepository: CategoryRepository,
): LoadCategoryUseCase{
    override suspend fun execute(params: Unit): List<CategoryResponse> {
        return categoryRepository.getCategories()
    }
}