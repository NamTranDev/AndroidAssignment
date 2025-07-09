package nam.tran.fake

import kotlinx.coroutines.delay
import nam.tran.domain.model.entity.CategoryEntity
import nam.tran.domain.repository.CategoryRepository

class FakeCategoryRepository(
    private val case : CaseTest
): CategoryRepository {
    override suspend fun getCategories(): List<CategoryEntity> {
        delay(1000)
        if (case.isCategorySuccess){
            return listOf(
                CategoryEntity(slug = "category1", name = "Category 1"),
                CategoryEntity(slug = "category2", name = "Category 2")
            )
        }else{
            throw RuntimeException()
        }
    }
}