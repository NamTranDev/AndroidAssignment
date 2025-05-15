package nam.tran.home.assignment.jetpack.compose.fakes

import kotlinx.coroutines.delay
import nam.tran.home.assignment.jetpack.compose.domain.repository.CategoryRepository
import nam.tran.home.assignment.jetpack.compose.model.response.CategoryResponse

class FakeCategoryRepository(
    private val case : CaseTest
): CategoryRepository {
    override suspend fun getCategories(): List<CategoryResponse> {
        delay(1000)
        if (case.isCategorySuccess){
            return listOf(
                CategoryResponse(slug = "category1", name = "Category 1"),
                CategoryResponse(slug = "category2", name = "Category 2")
            )
        }else{
            throw RuntimeException()
        }
    }
}