package nam.tran.domain.usecase

import nam.tran.domain.model.entity.CategoryEntity
import nam.tran.domain.repository.CategoryRepository

interface LoadCategoryUseCase : UseCase<Unit,List<CategoryEntity>>

class LoadCategoryUseCaseImpl(
    private val categoryRepository: CategoryRepository,
): LoadCategoryUseCase{
    override suspend fun execute(params: Unit): List<CategoryEntity> {
        return categoryRepository.getCategories()
    }
}